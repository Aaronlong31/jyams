package com.jyams.dispatch.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.dispatch.model.DispatchWork;
import com.jyams.util.DataPage;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
public class DispatchWorkDao extends IBatisEntityDao<DispatchWork> {

    /**
     * 批量插入
     * 
     * @param dispatchWorks
     */
    public void batchInsert(List<DispatchWork> dispatchWorks) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("dispatchWorks", dispatchWorks);
        getSqlMapClientTemplate().insert("DispatchWork.batchInsert", map);
    }

    @SuppressWarnings("unchecked")
    public DataPage<DispatchWork> listDispatchWorks(Long projectId,
            Long personId, String personName, Integer month, Integer day,
            Integer pageNo, Integer pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("projectId", projectId);
        map.put("personId", personId);
        map.put("personName", personName);
        if (month != null) {
            map.put("dispatchDayStart", month * 100 + 1);
            map.put("dispatchDayEnd", month * 100 + 31);
        }
        map.put("dispatchDay", day);
        return pagedQuery("DispatchWorkDao.listDispatchWorks", map, pageNo,
                pageSize);
    }

    public int checkDuplicateTime(int dispatchDay, int startTime, int endTime,
            long personId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("dispatchDay", dispatchDay);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("personId", personId);
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "DispatchWorkDao.checkDuplicateTime", map);
    }
}
