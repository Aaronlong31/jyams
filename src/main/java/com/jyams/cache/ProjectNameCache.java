package com.jyams.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheLoader;
import com.jyams.project.manager.ProjectManager;
import com.jyams.project.model.Project;

/**
 * @author zhanglong
 * 
 *         2012-12-6 下午9:59:10
 */
@Component
public class ProjectNameCache extends BaseCache<Long, String> {

    @Autowired
    public ProjectNameCache(final ProjectManager projectManager) {
        CacheLoader<Long, String> cacheLoader = new CacheLoader<Long, String>() {
            @Override
            public String load(Long key) throws Exception {
                Project project = projectManager.getProject(key);
                if (project == null) {
                    return null;
                }
                return project.getProjectName();
            }
        };
        super.setCacheLoader(cacheLoader);
    }
}
