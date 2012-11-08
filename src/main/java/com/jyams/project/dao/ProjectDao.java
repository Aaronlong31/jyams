package com.jyams.project.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.common.DataPage;
import com.jyams.project.manager.ProjectQuery;
import com.jyams.project.model.Project;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
@SuppressWarnings("unchecked")
public class ProjectDao extends IBatisEntityDao<Project> {

	public DataPage<Project> selectProjects(Long projectId,
			String companyPrincipalName, String clientName,
			String clientPrincipalName, String orderString, Integer pageNo,
			Integer pageSize) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("projectId", projectId);
		map.put("companyPrincipalName", companyPrincipalName);
		map.put("clientName", clientName);
		map.put("clientPrincipalName", clientPrincipalName);
		map.put("orderString", orderString);
		return pagedQuery("com.jyams.project.dao.ProjectDao.selectProjects",
				map, pageNo, pageSize);
	}

	/**
	 * 简单查询项目，只包含项目标识和名称
	 * @param status
	 * @return
	 */
	public List<Project> listProjectsSimple(int status, Long clientId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("status", status);
		map.put("clientId", clientId);
		return getSqlMapClientTemplate().queryForList(
				"com.jyams.project.dao.ProjectDao.listProjectsSimple", map);
	}

	public DataPage<Project> pageQueryBasic(ProjectQuery projectQuery) {
		return pageQuery("com.jyams.project.dao.ProjectDao.pageQueryBasic",
				projectQuery);
	}

}
