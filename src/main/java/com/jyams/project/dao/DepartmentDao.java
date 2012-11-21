package com.jyams.project.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.hr.model.Department;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
@SuppressWarnings("unchecked")
public class DepartmentDao extends IBatisEntityDao<Department> {

    public List<Department> getWithPersons(Short salaryType) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("salaryType", salaryType);
        return getSqlMapClientTemplate().queryForList(
                "DepartmentDao.getWithPersons", map);
    }

}
