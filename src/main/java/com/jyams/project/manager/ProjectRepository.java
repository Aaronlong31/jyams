package com.jyams.project.manager;

import com.jyams.common.DataPage;
import com.jyams.project.model.Project;

/**
 * 施工流程仓库，只用于查找
 * @author zhanglong
 *
 */
public interface ProjectRepository {

	DataPage<Project> listProject(ProjectQuery projectQuery);

	DataPage<Project> listBasicProject(ProjectQuery projectQuery);
}