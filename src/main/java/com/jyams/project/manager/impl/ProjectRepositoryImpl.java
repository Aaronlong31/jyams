package com.jyams.project.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyams.common.DataPage;
import com.jyams.project.dao.ProjectDao;
import com.jyams.project.manager.ProjectQuery;
import com.jyams.project.manager.ProjectRepository;
import com.jyams.project.model.Project;

@Service
public class ProjectRepositoryImpl implements ProjectRepository {

	@Autowired
	private ProjectDao projectDao;

	@Override
	public DataPage<Project> listProject(ProjectQuery projectQuery) {
		return projectDao.pageQuery(projectQuery);
	}

	@Override
	public DataPage<Project> listBasicProject(ProjectQuery projectQuery) {
		return projectDao.pageQueryBasic(projectQuery);
	}

}
