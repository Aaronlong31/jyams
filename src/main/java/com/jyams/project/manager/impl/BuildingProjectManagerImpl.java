package com.jyams.project.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyams.project.dao.BuildingProjectDao;
import com.jyams.project.dao.BuildingProjectDetailDao;
import com.jyams.project.manager.BuildingProjectManager;
import com.jyams.project.manager.BuildingProjectQuery;
import com.jyams.project.model.BuildingProject;
import com.jyams.project.model.BuildingProjectDetail;
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
        return buildingProjectDao.completeProject(projectId, personId,
                personName) > 0;
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
    public boolean clearCollection(long projectId, long personId,
            String personName) {
        return buildingProjectDao.clearCollection(projectId, personId,
                personName) > 0;
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
    public BuildingProject getBuildingProject(long buildingProjectId) {

        BuildingProject buildingProject = buildingProjectDao
                .get(buildingProjectId);
        // 将在建项目明细一起查出
        buildingProject.setBuildingProjectDetails(buildingProjectDetailDao
                .findBy("projectId", buildingProjectId));

        return buildingProject;
    }

    @Scheduled(cron = "0 01 22 * * ?")
    public void checkDelayProject() {
        buildingProjectDao.checkDelayProject();
    }

    @Override
    public DataPage<BuildingProject> listBuildingProject(Long projectId,
            String companyPrincipalName, String clientName,
            String clientPrincipalName, Integer status, Integer order,
            Integer pageNo, Integer pageSize, boolean hidden) {
        return buildingProjectDao.listBuildingProject(projectId,
                companyPrincipalName, clientName, clientPrincipalName, status,
                ProjectManagerImpl.getOrderBySql(order), pageNo, pageSize,
                hidden);
    }

    @Override
    public List<Long> listProjectIds(Integer status) {
        return buildingProjectDao.listProjectIds(status);
    }

    @Override
    public List<BuildingProject> listAlarmProjects() {
        DataPage<BuildingProject> dataPage = listBuildingProject(null, null,
                null, null, BuildingProject.STATUS_ALARM
                        | BuildingProject.STATUS_BUILDING, null, null, null,
                false);
        return dataPage.getData();
    }

    @Override
    public List<BuildingProject> listDelayProjects() {
        DataPage<BuildingProject> dataPage = listBuildingProject(null, null,
                null, null, BuildingProject.STATUS_DELAYED
                        | BuildingProject.STATUS_BUILDING, null, null, null,
                false);
        return dataPage.getData();
    }

    @Override
    public List<BuildingProject> listOverrunProjects() {
        DataPage<BuildingProject> dataPage = listBuildingProject(null, null,
                null, null, BuildingProject.STATUS_OVERRUN
                        | BuildingProject.STATUS_BUILDING, null, null, null,
                false);
        return dataPage.getData();
    }

    @Override
    public List<BuildingProject> listAllBuildingProject() {
        return buildingProjectDao.getAll();
    }

    @Override
    public DataPage<BuildingProject> listBuildingProject(
            BuildingProjectQuery query) {
        return buildingProjectDao.pageQuery(query);
    }

    @Override
    public void updateBuildingProjectDetail(PurchaseItem purchaseItem,
            Long oldPurchaseItemId, short purchaseType) {

        Long oldPorjectId = null;
        short[] costTypes = { BuildingProjectDetail.COSTTYPE_MATERIAL,
                BuildingProjectDetail.COSTTYPE_ORDER };
        String referId = purchaseItem.getPurchaseItemId() + "";

        // 1.如果purchaseItem的状态为删除，则删除BuildingProjectDetail表中，referId为
        // purchaesItemId且costType 为1或2的记录
        if (purchaseItem.getStatus() == PurchaseItem.STATUS_DELETED
                || purchaseItem.getStatus() == PurchaseItem.STATUS_SUBSPENDED) {
            buildingProjectDetailDao
                    .removeByReferIdAndTypes(referId, costTypes);
        } else if (purchaseItem.getStatus() == PurchaseItem.STATUS_PASS_REVIEW) {
            // 2. 如果purchaseItem的状态不为删除，根据referId=oldPurchaseItemId
            // 查找BuildingProjectDetail表
            BuildingProjectDetail detail = buildingProjectDetailDao
                    .findUniqueBy("referId", oldPurchaseItemId + "");

            // 如果修改前后项目标识有变动，则要修改两个项目的成本和状态
            if (detail != null
                    && detail.getProjectId() != purchaseItem.getProjectId()) {
                oldPorjectId = detail.getProjectId();
            }

            // 删除之前的detail
            buildingProjectDetailDao
                    .removeByReferIdAndTypes(referId, costTypes);

            // 新增detail
            addBuildingProjectDetail(purchaseItem, purchaseType);
        }

        // 如果修改前后项目标识有变动，则要修改两个项目的成本和状态
        if (oldPorjectId != null) {
            updateCostAndStatus(oldPorjectId);
        }

        updateCostAndStatus(purchaseItem.getProjectId());
    }

    private void addBuildingProjectDetail(PurchaseItem purchaseItem,
            short purchaseType) {
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
     */
    private void updateCostAndStatus(long projectId) {
        BuildingProject bp = getBuildingProject(projectId);
        bp.setActualCost(bp.getCostFromDetail());

        // 若项目的实际成本大于预估成本，则将项目状态加上报警和超支
        if (bp.getActualCost() > bp.getEstimateCost()) {
            bp.setStatus((short) (bp.getStatus() | BuildingProject.STATUS_OVERRUN));
        } else {
            bp.setStatus((short) (bp.getStatus() & ~BuildingProject.STATUS_OVERRUN));
        }
        buildingProjectDao.updateAcualCostAndStatus(projectId,
                bp.getActualCost(), bp.getStatus());
    }
}
