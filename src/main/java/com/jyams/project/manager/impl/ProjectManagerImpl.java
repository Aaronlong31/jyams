package com.jyams.project.manager.impl;

import static com.jyams.util.KeyGenerator.getProjectId;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyams.hr.manager.ClientManager;
import com.jyams.hr.model.Client;
import com.jyams.hr.model.ClientPrincipal;
import com.jyams.project.dao.ProjectDao;
import com.jyams.project.manager.BuildingProjectManager;
import com.jyams.project.manager.ProjectManager;
import com.jyams.project.manager.ProjectQuery;
import com.jyams.project.model.Project;
import com.jyams.util.DataPage;
import com.jyams.util.KeyGenerator;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectManagerImpl implements ProjectManager {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ClientManager clientManager;
    @Autowired
    private BuildingProjectManager buildingProjectManager;

    @Override
    public long addProject(Project project, boolean isNewYear) {
        setClient(project);

        /* 插入项目 */
        if (isNewYear) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            KeyGenerator.setProjectId((year % 100 + 1) * 10000);
        }
        long projectId = getProjectId();
        project.setProjectId(projectId);
        project.setCreatedTimestamp(System.currentTimeMillis());
        projectDao.insert(project);

        /* 新建在建项目 */
        buildingProjectManager.addBuildingProject(project);

        return projectId;
    }

    /**
     * 修改项目<br>
     * 可修改：完工日期、可延后时间、订单内容、客户负责人、公司负责人、报价单编号、备注 如果客户负责人修改了，且没有记录，则新增客户负责人
     * 
     * @param project
     *            必须包含以下属性：<br>
     *            项目标识：projectId<br>
     *            最后修改人标识：lastModifierId<br>
     *            最后修改人姓名：lastModifierName<br>
     * @throws java.lang.IllegalArgumentException
     *             project 的必要属性没设置时抛出
     */
    @Override
    public boolean modifyProject(Project project) {

        project.setCanDelayDay(Math.max(project.getCanDelayDay(), 0));

        setClient(project);

        project.setLastModifiedTimestamp(System.currentTimeMillis());

        return projectDao.update(project) > 0;
    }

    @Override
    public Project getProject(long projectId) {
        return projectDao.get(projectId);
    }

    @Override
    public List<Project> listAllProjects() {
        DataPage<Project> dataPage = listProjects(null, null, null, null, null,
                null, null);
        return dataPage.getData();
    }

    @Override
    public DataPage<Project> listProjects(Long projectId,
            String companyPrincipalName, String clientName,
            String clientPrincipalName, Integer order, Integer pageNo,
            Integer pageSize) {
        return projectDao.selectProjects(projectId, companyPrincipalName,
                clientName, clientPrincipalName, getOrderBySql(order), pageNo,
                pageSize);
    }

    @Override
    public DataPage<Project> listProjects(ProjectQuery query) {
        return projectDao.pageQuery(query);
    }

    @Override
    public List<Project> listProjectsSimple(int status, Long clientId) {
        return projectDao.listProjectsSimple(status, clientId);
    }

    /**
     * 设置客户商和客户负责人
     * 
     * @param project
     * @return
     */
    private Project setClient(Project project) {
        if (StringUtils.isNotBlank(project.getClientName())) {
            Client client = clientManager.addClient(project.getClientName());
            if (StringUtils.isNotBlank(project.getClientPrincipalName())) {
                ClientPrincipal cp = clientManager.addClientPrincipal(
                        client.getClientId(), project.getClientPrincipalName());
                project.setClientPrincipalId(cp.getClientPrincipalId());
            }
            project.setClientId(client.getClientId());
        }
        return project;
    }

    static String getOrderBySql(Integer order) {
        StringBuilder orderBySql = new StringBuilder(" ORDER BY ");
        String orderColumn = "";
        order = (order == null) ? 0 : order;
        switch (order & 0X000F) {
        case ORDER_ACTUAL_COST:
            orderColumn = "actualCost";
            break;
        case ORDER_CAN_DELAY_DAY:
            orderColumn = "canDelayDay";
            break;
        case ORDER_CONTRACT_PRICE:
            orderColumn = "contractPrice";
            break;
        case ORDER_CREATEDTIMESTAMP:
            orderColumn = "createdTimestamp";
            break;
        case ORDER_ESTIMATE_COST:
            orderColumn = "estimateCost";
            break;
        case ORDER_REQUIRED_COMPLETION_DATE:
            orderColumn = "requiredCompletionDate";
            break;
        default:
            return " ";
        }
        orderBySql.append(orderColumn);
        if ((order & ORDER_DESC) == ORDER_DESC) {
            orderBySql.append(" DESC ");
        }
        return orderBySql.toString();
    }
}
