package com.jyams.project.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.project.model.BuildingProjectDetail;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
public class BuildingProjectDetailDao extends
        IBatisEntityDao<BuildingProjectDetail> {

    public void removeByReferIdAndTypes(String referId, short[] costTypes) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("referId", referId);
        map.put("costTypes", costTypes);
        getSqlMapClientTemplate().delete(
                "BuildingProjectDetailDao.removeByReferIdAndTypes", map);
    }

}
