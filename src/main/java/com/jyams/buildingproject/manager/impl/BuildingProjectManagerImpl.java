package com.jyams.buildingproject.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyams.buildingproject.manager.BuildingProjectManager;
import com.jyams.buildingproject.model.BuildingProject;
import com.jyams.buildingproject.model.BuildingProjectDetail;
import com.jyams.buildingproject.query.BuildingProjectQuery;
import com.jyams.exception.BusinessException;
import com.jyams.project.dao.BuildingProjectDao;
import com.jyams.project.dao.BuildingProjectDetailDao;
import com.jyams.project.model.ChangeStatusType;
import com.jyams.project.model.Project;
import com.jyams.purchase.model.Purchase;
import com.jyams.purchase.model.PurchaseItem;
import com.jyams.util.DataPage;
import com.jyams.util.IdUtil;

/**
 * 在建项目管理
 * 
 * @author zhanglong
 * 
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BuildingProjectManagerImpl implements BuildingProjectManager {

    @Autowired
    private BuildingProjectDao buildingProjectDao;
    @Autowired
    private BuildingProjectDetailDao buildingProjectDetailDao;

    @Override
    public long addBuildingProject(Project project) {
        // 项目和在建项目是主键一对一关联
        BuildingProject buildingProject = new BuildingProject();
        buildingProject.setProjectId(project.getProjectId());
        buildingProject.setStatus(BuildingProject.STATUS_BUILDING);
        buildingProjectDao.insert(buildingProject);
        return project.getProjectId();
    }

    @Override
    public boolean modifyBuildingProject(BuildingProject buildingProject) {
        return buildingProjectDao.update(buildingProject) > 0;
    }

    @Override
    public boolean openProject(long projectId, long personId, String personName) {
        buildingProjectDao.updateStatus(projectId, personId, personName,
                BuildingProject.STATUS_COMPLETED, ChangeStatusType.REMOVE);
        return buildingProjectDao.updateStatus(projectId, personId, personName,
                BuildingProject.STATUS_BUILDING, ChangeStatusType.ADD) > 0;
    }

    @Override
    public boolean closeProject(long projectId, long personId, String personName) {
        return buildingProjectDao.completeProject(projectId, personId, personName) > 0;
    }

    @Override
    public boolean invoice(long projectId, long personId, String personName) {
        return buildingProjectDao.invoice(projectId, personId, personName) > 0;
    }

    @Override
    public boolean collection(long projectId, long personId, String personName) {
        return buildingProjectDao.collection(projectId, personId, personName) > 0;
    }

    @Override
    public boolean clearInvoice(long projectId, long personId, String personName) {
        return buildingProjectDao.clearInvoice(projectId, personId, personName) > 0;
    }

    @Override
    public boolean clearCollection(long projectId, long personId, String personName) {
        return buildingProjectDao.clearCollection(projectId, personId, personName) > 0;
    }

    @Override
    public boolean hidden(long projectId, long personId, String personName) {
        return buildingProjectDao.updateStatus(projectId, personId, personName,
                BuildingProject.STATUS_HIDDEN, ChangeStatusType.ADD) > 0;
    }

    @Override
    public boolean cancelHidden(Long projectId, long personId, String personName) {
        return buildingProjectDao.updateStatus(projectId, personId, personName,
                BuildingProject.STATUS_HIDDEN, ChangeStatusType.REMOVE) > 0;
    }

    @Override
    public BuildingProject getBuildingProject(long buildingProjectId) throws BusinessException {

        BuildingProject buildingProject = buildingProjectDao.get(buildingProjectId);

        if (buildingProject == null) {
            throw new BusinessException("您要查看的在建项目不存在！");
        }

        // 将在建项目明细一起查出
        buildingProject.setBuildingProjectDetails(buildingProjectDetailDao.findBy("projectId",
                buildingProjectId));

        return buildingProject;
    }

    @Scheduled(cron = "0 01 22 * * ?")
    public void checkDelayProject() {
        buildingProjectDao.checkDelayProject();
    }

    @Override
    public List<Long> listProjectIds(Integer status) {
        return buildingProjectDao.listProjectIds(status);
    }

    @Override
    public DataPage<BuildingProject> listAlarmProjects() {
        BuildingProjectQuery query = new BuildingProjectQuery();
        query.setStatus((short) (BuildingProject.STATUS_ALARM | BuildingProject.STATUS_BUILDING));
        return this.listBuildingProject(query);
    }

    @Override
    public DataPage<BuildingProject> listDelayProjects() {
        BuildingProjectQuery query = new BuildingProjectQuery();
        query.setStatus((short) (BuildingProject.STATUS_DELAYED | BuildingProject.STATUS_BUILDING));
        return this.listBuildingProject(query);
    }

    @Override
    public DataPage<BuildingProject> listOverrunProjects() {
        BuildingProjectQuery query = new BuildingProjectQuery();
        query.setStatus((short) (BuildingProject.STATUS_OVERRUN | BuildingProject.STATUS_BUILDING));
        return this.listBuildingProject(query);
    }

    @Override
    public List<BuildingProject> listAllBuildingProject() {
        return buildingProjectDao.getAll();
    }

    @Override
    public DataPage<BuildingProject> listBuildingProject(BuildingProjectQuery query) {
        return buildingProjectDao.pageQuery(query);
    }

    @Override
    public void updateBuildingProjectDetail(PurchaseItem purchaseItem, Long oldPurchaseItemId,
            short purchaseType) {

        Long oldPorjectId = null;
        String referId = purchaseItem.getPurchaseItemId() + "";

        // 1.如果purchaseItem的状态为删除，则删除BuildingProjectDetail表中，referId为
        // purchaesItemId且costType 为1或2的记录
        if (purchaseItem.getStatus() == PurchaseItem.STATUS_DELETED
                || purchaseItem.getStatus() == PurchaseItem.STATUS_SUBSPENDED) {
            buildingProjectDetailDao.removeByReferIdAndTypes(referId,
                    BuildingProjectDetail.COSTTYPE_MATERIAL, BuildingProjectDetail.COSTTYPE_ORDER);
        } else if (purchaseItem.getStatus() == PurchaseItem.STATUS_PASS_REVIEW) {
            // 2. 如果purchaseItem的状态不为删除，根据referId=oldPurchaseItemId
            // 查找BuildingProjectDetail表
            BuildingProjectDetail detail = buildingProjectDetailDao.findUniqueBy("referId",
                    oldPurchaseItemId + "");

            // 如果修改前后项目标识有变动，则要修改两个项目的成本和状态
            if (detail != null && detail.getProjectId() != purchaseItem.getProjectId()) {
                oldPorjectId = detail.getProjectId();
            }

            // 删除之前的detail
            buildingProjectDetailDao.removeByReferIdAndTypes(referId,
                    BuildingProjectDetail.COSTTYPE_MATERIAL, BuildingProjectDetail.COSTTYPE_ORDER);

            // 新增detail
            addBuildingProjectDetail(purchaseItem, purchaseType);
        }

        // 如果修改前后项目标识有变动，则要修改两个项目的成本和状态
        if (oldPorjectId != null) {
            updateCostAndStatus(oldPorjectId);
        }

        updateCostAndStatus(purchaseItem.getProjectId());
    }

    private void addBuildingProjectDetail(PurchaseItem purchaseItem, short purchaseType) {
        BuildingProjectDetail detail = new BuildingProjectDetail();
        detail.setDetailId(IdUtil.nextLong());
        detail.setCost(purchaseItem.getCost());
        short costType = BuildingProjectDetail.COSTTYPE_OTHER;
        if (purchaseType == Purchase.TYPE_MATERIAL) {
            costType = BuildingProjectDetail.COSTTYPE_MATERIAL;
        } else if (purchaseType == Purchase.TYPE_ORDER) {
            costType = BuildingProjectDetail.COSTTYPE_ORDER;
        }
        detail.setCostType(costType);
        detail.setCreatedTimestamp(System.currentTimeMillis());
        detail.setPersonId(purchaseItem.getReviewerId());
        detail.setPersonName(purchaseItem.getReviewerName());
        detail.setCreatorId(purchaseItem.getReviewerId());
        detail.setCreatorName(purchaseItem.getReviewerName());
        detail.setDescription(purchaseItem.getDescription());
        detail.setProjectId(purchaseItem.getProjectId());
        detail.setReferId(purchaseItem.getPurchaseItemId() + "");
        buildingProjectDetailDao.insert(detail);
    }

    /**
     * 修改项目的成本和状态
     * 
     * @param projectId
     * @throws BusinessException
     */
    private void updateCostAndStatus(long projectId) throws BusinessException {
        BuildingProject bp = getBuildingProject(projectId);
        bp.setActualCost(bp.getCostFromDetail());

        // 若项目的实际成本大于预估成本，则将项目状态加上报警和超支
        if (bp.getActualCost() > bp.getEstimateCost()) {
            bp.setStatus((short) (bp.getStatus() | BuildingProject.STATUS_OVERRUN));
        } else {
            bp.setStatus((short) (bp.getStatus() & ~BuildingProject.STATUS_OVERRUN));
        }
        buildingProjectDao.updateAcualCostAndStatus(projectId, bp.getActualCost(), bp.getStatus());
    }
}
