package com.jyams.project.manager;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jyams.exception.BusinessException;
import com.jyams.project.model.Project;
import com.jyams.util.DataPage;

/**
 * 项目管理类，管理项目、在建项目、在建项目明细和派工信息
 * 
 * @author zhanglong
 * 
 */
@Transactional(rollbackFor = Exception.class)
public interface ProjectManager {

    /**
     * 要求完工日期升序
     */
    int ORDER_REQUIRED_COMPLETION_DATE = 1;
    /**
     * 可延后时间升序
     */
    int ORDER_CAN_DELAY_DAY = 2;
    /**
     * 合同总价升序
     */
    int ORDER_CONTRACT_PRICE = 3;
    /**
     * 实际成本升序
     */
    int ORDER_ACTUAL_COST = 4;
    /**
     * 预估成本升序
     */
    int ORDER_ESTIMATE_COST = 5;
    /**
     * 创建时间升序
     */
    int ORDER_CREATEDTIMESTAMP = 6;
    /**
     * 降序
     */
    int ORDER_DESC = 0X1000;

    /** ---------------------------------项目管理-------------------------------- **/

    /**
     * 新增项目<br>
     * 
     * @param project
     *            必须包含以下属性：<br>
     *            要求完工日期：requiredCompletionDate<br>
     *            公司负责人标识：companyPrincipalId<br>
     *            公司负责人姓名：companyPrincipalName<br>
     *            创建人标识：creatorId<br>
     *            创建人姓名：creatorName<br>
     * @param isNewYear
     * @return 项目编号
     * @throws BusinessException
     */
    long addProject(Project project, boolean isNewYear);

    /**
     * 修改项目<br>
     * 可修改：完工日期、可延后时间、订单内容、客户负责人、公司负责人、报价单编号、备注 如果客户负责人修改了，且没有记录，则新增客户负责人
     * 
     * @param project
     *            必须包含以下属性：<br>
     *            项目标识：projectId<br>
     *            最后修改人标识：lastModifierId<br>
     *            最后修改人姓名：lastModifierName<br>
     */
    boolean modifyProject(Project project);

    /**
     * 获取项目
     * 
     * @param projectId
     * @return
     */
    @Transactional(readOnly = true)
    Project getProject(long projectId);

    @Transactional(readOnly = true)
    DataPage<Project> listProjects(Long projectId, String companyPrincipalName,
            String clientName, String clientPrincipalName, Integer order,
            Integer pageNo, Integer pageSize);

    @Transactional(readOnly = true)
    DataPage<Project> listProjects(ProjectQuery query);

    @Transactional(readOnly = true)
    List<Project> listAllProjects();

    /**
     * 简单查询项目，只包含项目标识和名称
     * 
     * @param status
     * @return
     */
    List<Project> listProjectsSimple(int status, Long clientId);
    /*-------------------------------派工信息管理--------------------------------*/

}
