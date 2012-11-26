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
        getSqlMapClientTemplate().insert("MultiDayDao.insertAll", map);
    }

    public void deleteAll() {
        getSqlMapClientTemplate().delete("MultiDayDao.deleteAll");
    }
}
