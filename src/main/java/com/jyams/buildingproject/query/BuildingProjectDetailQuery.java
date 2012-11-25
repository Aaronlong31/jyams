package com.jyams.buildingproject.query;

import com.jyams.project.model.BuildingProjectDetail;
import com.jyams.util.search.Query;
import com.jyams.util.search.SearchFilter;

/**
 * @author zhanglong
 * 
 *         Nov 25, 2012 1:05:09 AM
 */
public class BuildingProjectDetailQuery extends Query<BuildingProjectDetail> {

    private long projectId;

    public BuildingProjectDetailQuery(int pageNo, int pageSize) {
        super(pageNo, pageSize);
    }

    public BuildingProjectDetailQuery(SearchFilter filter) {
        super(filter);
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

}
