package com.jyams.buildingproject.manager.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.jyams.buildingproject.manager.BuildingProjectDetailManager;
import com.jyams.buildingproject.model.BuildingProject;
import com.jyams.buildingproject.model.BuildingProjectDetail;
import com.jyams.buildingproject.query.BuildingProjectDetailQuery;
import com.jyams.exception.BusinessException;
import com.jyams.project.dao.BuildingProjectDao;
import com.jyams.project.dao.BuildingProjectDetailDao;
import com.jyams.purchase.model.Purchase;
import com.jyams.purchase.model.PurchaseItem;
import com.jyams.util.DataPage;
import com.jyams.util.IdUtil;

/**
 * @author zhanglong
 * 
 *         Nov 8, 2012 10:18:26 PM
 */
@Service
public class BuildingProjectDetailManagerImpl implements BuildingProjectDetailManager {

    @Autowired
    private BuildingProjectDao buildingProjectDao;
    @Autowired
    private BuildingProjectDetailDao buildingProjectDetailDao;

    /**
     * @param buildingProjectDetail
     * @throws BusinessException
     */
    @Override
    public long add(BuildingProjectDetail buildingProjectDetail) throws BusinessException {

        checkProjectIsCompleted(buildingProjectDetail.getProjectId());

        long detailId = IdUtil.nextLong();
        buildingProjectDetail.setDetailId(detailId);
        buildingProjectDetailDao.insert(buildingProjectDetail);
        calculateProjectActualCost(buildingProjectDetail.getProjectId());
        return detailId;
    }

    public void add(Purchase purchase) throws BusinessException {

        // 采购项
        List<PurchaseItem> purchaseItems = purchase.getPurchaseItems();

        // key - 项目标识， value - 项目本次总花费
        Map<Long, Float> projectCosts = Maps.newHashMap();

        // 遍历采购项，将每个采购项作为一个明细存到数据库中
        for (PurchaseItem purchaseItem : purchaseItems) {

            checkProjectIsCompleted(purchaseItem.getProjectId());

            BuildingProjectDetail bpd = new BuildingProjectDetail();
            bpd.setDetailId(IdUtil.nextLong()); // 设置明细标识
            bpd.setCost(purchaseItem.getCost()); // 设置明细花费
            if (purchase.getType() == Purchase.TYPE_MATERIAL) {
                bpd.setCostType(BuildingProjectDetail.COSTTYPE_MATERIAL); // 设置明细花费类型为材料花费
            } else if (purchase.getType() == Purchase.TYPE_ORDER) {
                bpd.setCostType(BuildingProjectDetail.COSTTYPE_ORDER); // 设置明细花费类型为订单花费
            }
            bpd.setCreatedTimestamp(System.currentTimeMillis()); // 设置明细创建时间
            bpd.setCreatorId(purchase.getApproverId()); // 设置审批人为采购经办人
            bpd.setCreatorName(purchase.getApproverName()); //
            bpd.setPersonId(purchase.getApproverId()); // 设置审批人为采购经办人
            bpd.setPersonName(purchase.getApproverName());
            bpd.setProjectId(purchaseItem.getProjectId()); // 关联项目-明细
            bpd.setReferId(purchase.getPurchaseId() + "" + (char) purchase.getVersion()); // 关联明细-采购项
            buildingProjectDetailDao.insert(bpd);

            Long projectId = purchaseItem.getProjectId();

            // 如果项目标识为空，则跳出循环继续
            if (projectId == null) {
                continue;
            }

            // 下面是计算每个项目的本次采购总花费，为防止nullpointException，
            // 这里将之前map中没有存放的项目的花费设置为0
            float cost = projectCosts.get(projectId) != null ? projectCosts.get(projectId) : 0F;
            projectCosts.put(projectId, cost + purchaseItem.getCost());
        }

        // 为项目增加实际成本
        for (Entry<Long, Float> projectCost : projectCosts.entrySet()) {
            calculateProjectActualCost(projectCost.getKey());
        }

    }

    @Override
    public boolean update(BuildingProjectDetail buildingProjectDetail) {
        return buildingProjectDetailDao.update(buildingProjectDetail) > 0;
    }

    @Override
    public BuildingProjectDetail get(int detailId) {
        return buildingProjectDetailDao.get(detailId);
    }

    @Override
    public List<BuildingProjectDetail> list(long projectId) {
        return buildingProjectDetailDao.findBy("projectId", projectId);
    }

    @Override
    public DataPage<BuildingProjectDetail> list(
            BuildingProjectDetailQuery buildingProjectDetailQuery) {
        return buildingProjectDetailDao.pageQuery(buildingProjectDetailQuery);
    }

    @Override
    public void deleteByReferId(long dispatchId, short costType) {
        long projectId = buildingProjectDetailDao.findProjectIdByReferId(dispatchId + "", costType);
        buildingProjectDetailDao.removeByReferIdAndTypes(dispatchId + "", costType);
        calculateProjectActualCost(projectId);
    }

    /**
     * 检查项目是否完工
     * 
     * @param projectId
     * @throws BusinessException
     */
    private void checkProjectIsCompleted(long projectId) throws BusinessException {
        BuildingProject buildingProject = buildingProjectDao.get(projectId);
        if (buildingProject.getStatus() == BuildingProject.STATUS_COMPLETED) {
            throw new BusinessException("该项目已完工，不能再添加明细！");
        }
    }

    /**
     * 增加项目的实际成本，在建项目和项目都要修改
     * 
     * @param projectId
     * @param totalCost
     */
    private void calculateProjectActualCost(long projectId) {
        BuildingProject bp = buildingProjectDao.get(projectId);
        float actualCost = buildingProjectDetailDao.calculateActualCost(projectId);

        // 若项目的实际成本大于预估成本，则将项目状态加上报警和超支
        if (actualCost > bp.getEstimateCost()) {
            bp.setStatus((short) (bp.getStatus() | BuildingProject.STATUS_OVERRUN));
        }
        buildingProjectDao.updateAcualCostAndStatus(projectId, actualCost, bp.getStatus());
    }

}
