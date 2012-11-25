package com.jyams.dispatch.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.dispatch.model.Dispatch;
import com.jyams.util.DataPage;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
@SuppressWarnings("unchecked")
public class DispatchDao extends IBatisEntityDao<Dispatch> {

    public DataPage<Dispatch> selectDispatchs(Long projectId,
            String personName, Integer month, Integer day, Integer pageNo,
            Integer pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("projectId", projectId);
        map.put("personName", personName);
        if (month != null) {
            map.put("dispatchDayStart", month * 100 + 1);
            map.put("dispatchDayEnd", month * 100 + 31);
        }
        map.put("dispatchDay", day);
        return pagedQuery("DispatchDao.selectDispatchs", map, pageNo, pageSize);
    }

}
