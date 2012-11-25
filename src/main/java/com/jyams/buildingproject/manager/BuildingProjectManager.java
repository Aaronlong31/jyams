package com.jyams.buildingproject.manager;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jyams.buildingproject.query.BuildingProjectQuery;
import com.jyams.exception.BusinessException;
import com.jyams.project.model.BuildingProject;
import com.jyams.project.model.Project;
import com.jyams.purchase.model.PurchaseItem;
import com.jyams.util.DataPage;

@Transactional(rollbackFor = Exception.class)
public interface BuildingProjectManager {

    /**
     * 添加在建项目
     */
    long addBuildingProject(Project project);

    boolean modifyBuildingProject(BuildingProject buildingProject);

    /**
     * 开启项目
     */
    boolean openProject(long projectId, long personId, String personName);

    /**
     * 关闭项目
     */
    boolean closeProject(long projectId, long personId, String personName);

    /**
     * 开票
     */
    boolean invoice(long projectId, long personId, String personName);

    /**
     * 收款
     */
    boolean collection(long projectId, long personId, String personName);

    /**
     * 取消开票
     */
    boolean clearInvoice(long projectId, long personId, String personName);

    /**
     * 取消收款
     */
    boolean clearCollection(long projectId, long personId, String personName);

    /**
     * 隐藏项目
     */
    boolean hidden(long projectId, long personId, String personName);

    boolean cancelHidden(Long projectId, long loginUserId, String loginUsername);

    /**
     * 获取在建项目 在建项目中的项目对象也要得到
     * 
     * @throws BusinessException
     */
    @Transactional(readOnly = true)
    BuildingProject getBuildingProject(long buildingProjectId)
            throws BusinessException;

    @Transactional(readOnly = true)
    DataPage<BuildingProject> listAlarmProjects();

    @Transactional(readOnly = true)
    DataPage<BuildingProject> listDelayProjects();

    @Transactional(readOnly = true)
    DataPage<BuildingProject> listOverrunProjects();

    @Transactional(readOnly = true)
    List<BuildingProject> listAllBuildingProject();

    /**
     * 根据状态获取项目标识
     */
    @Transactional(readOnly = true)
    List<Long> listProjectIds(Integer status);

    @Transactional(readOnly = true)
    DataPage<BuildingProject> listBuildingProject(BuildingProjectQuery query);

    void updateBuildingProjectDetail(PurchaseItem purchaseItem,
            Long oldPurchaseItemId, short purchaseType);
}
