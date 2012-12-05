package com.jyams.project.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.buildingproject.model.BuildingProjectDetail;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
public class BuildingProjectDetailDao extends IBatisEntityDao<BuildingProjectDetail> {

    public void removeByReferIdAndTypes(String referId, short... costTypes) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("referId", referId);
        map.put("costTypes", costTypes);
        getSqlMapClientTemplate().delete("BuildingProjectDetailDao.removeByReferIdAndTypes", map);
    }

    public float calculateActualCost(long projectId) {
        Object result = getSqlMapClientTemplate().queryForObject(
                "BuildingProjectDetailDao.calculateActualCost", projectId);
        if (result == null) {
            return 0.0F;
        }
        return Float.parseFloat(result.toString());
    }

}
