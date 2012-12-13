package com.jyams.project.manager.impl;

import static com.jyams.util.KeyGenerator.getProjectId;

import java.util.Calendar;
import java.util.List;

import com.jyams.security.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyams.buildingproject.manager.BuildingProjectManager;
import com.jyams.cache.PersonCache;
import com.jyams.hr.manager.ClientManager;
import com.jyams.hr.model.Client;
import com.jyams.hr.model.ClientPrincipal;
import com.jyams.hr.model.Person;
import com.jyams.project.dao.ProjectDao;
import com.jyams.project.manager.ProjectManager;
import com.jyams.project.model.Project;
import com.jyams.project.query.ProjectQuery;
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

    @Autowired
    private PersonCache personCache;

    @Override
    public long addProject(Project project, boolean isNewYear) {

        Person companyPrincipalPerson = personCache.get(project
                .getCompanyPrincipalId());
        project.setCompanyPrincipalName(companyPrincipalPerson.getPersonName());
        project.setCreatedTimestamp(SecurityUtils.getCurrentUserId());
        project.setCreatorName(SecurityUtils.getCurrentUsername());

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
     * 可修改：完工日期、可延后时间、订单内容、客户负责人、公司负责人、报价单编号、备注 <br>
     * 如果客户负责人修改了，且没有记录，则新增客户负责人
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
        Person companyPrincipalPerson = personCache.get(project
                .getCompanyPrincipalId());
        project.setCompanyPrincipalName(companyPrincipalPerson.getPersonName());
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

    @Override
    public DataPage<Project> listBasicProject(ProjectQuery projectQuery) {
        return projectDao.pageQueryBasic(projectQuery);
    }

}
