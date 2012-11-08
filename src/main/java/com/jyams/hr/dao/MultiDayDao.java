package com.jyams.hr.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.hr.model.MultiDay;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
public class MultiDayDao extends IBatisEntityDao<MultiDay> {

    public void insertAll(List<String> days, int times) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("days", days);
        map.put("times", times);
        getSqlMapClientTemplate().insert(
                "com.jyams.hr.dao.MultiDayDao.insertAll", map);
    }

    public void deleteAll() {
        getSqlMapClientTemplate().delete(
                "com.jyams.hr.dao.MultiDayDao.deleteAll");
    }
}
