package com.jyams.project.manager;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jyams.project.model.BuildingProject;
import com.jyams.project.model.Project;
import com.jyams.purchase.model.PurchaseItem;
import com.jyams.util.DataPage;

@Transactional(rollbackFor = Exception.class)
public interface BuildingProjectManager {

    /*-------------------------------在建项目管理--------------------------------*/

    /**
     * 添加在建项目
     * 
     * @param project
     */
    long addBuildingProject(Project project);

    boolean modifyBuildingProject(BuildingProject buildingProject);

    /**
     * 开启项目
     * 
     * @param projectId
     * @return
     */
    boolean openProject(long projectId, long personId, String personName);

    /**
     * 关闭项目
     * 
     * @param projectId
     * @param personId
     * @param personName
     * @return
     */
    boolean closeProject(long projectId, long personId, String personName);

    /**
     * 开票
     * 
     * @param projectId
     * @param personId
     * @param personName
     * @return
     */
    boolean invoice(long projectId, long personId, String personName);

    /**
     * 收款
     * 
     * @param projectId
     * @param personId
     * @param personName
     * @return
     */
    boolean collection(long projectId, long personId, String personName);

    /**
     * 取消开票
     * 
     * @param projectId
     * @param personId
     * @param personName
     * @return
     */
    boolean clearInvoice(long projectId, long personId, String personName);

    /**
     * 取消收款
     * 
     * @param projectId
     * @param personId
     * @param personName
     * @return
     */
    boolean clearCollection(long projectId, long personId, String personName);

    /**
     * 隐藏项目
     * 
     * @param projectId
     * @param personId
     * @param personName
     * @return
     */
    boolean hidden(long projectId, long personId, String personName);

    /**
     * 获取在建项目 在建项目中的项目对象也要得到
     * 
     * @param buildingProjectId
     * @return
     */
    BuildingProject getBuildingProject(long buildingProjectId);

    DataPage<BuildingProject> listBuildingProject(Long projectId,
            String companyPrincipalName, String clientName,
            String clientPrincipalName, Integer status, Integer order,
            Integer pageNo, Integer pageSize, boolean hidden);

    List<BuildingProject> listAlarmProjects();

    List<BuildingProject> listDelayProjects();

    List<BuildingProject> listOverrunProjects();

    List<BuildingProject> listAllBuildingProject();

    /**
     * 根据状态获取项目标识
     * 
     * @param status
     * @return
     */
    List<Long> listProjectIds(Integer status);

    DataPage<BuildingProject> listBuildingProject(BuildingProjectQuery query);

    boolean cancelHidden(Long projectId, long loginUserId, String loginUsername);

    void updateBuildingProjectDetail(PurchaseItem purchaseItem,
            Long oldPurchaseItemId, short purchaseType);
}
