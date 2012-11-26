package com.jyams.hr.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.hr.model.Job;
import com.jyams.hr.model.Person;
import com.jyams.util.DataPage;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
@SuppressWarnings("unchecked")
public class PersonDao extends IBatisEntityDao<Person> {

    public DataPage<Person> selectPersons(String departmentName,
            String personNameLike, Short status, Short salaryType,
            Integer pageNo, Integer pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("departmentName", departmentName);
        map.put("personNameLike", personNameLike);
        map.put("status", status);
        map.put("salaryType", salaryType);
        return pagedQuery("PersonDao.selectPersons", map, pageNo, pageSize);
    }

    public List<String> getJobTypes() {
        return getSqlMapClientTemplate().queryForList("PersonDao.getJobTypes");
    }

    public int updatePersonStates(long personId, short newSatus)
            throws SQLException {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("personId", personId);
        hashMap.put("status", newSatus);
        return getSqlMapClient()
                .update("PersonDao.updatePersonStates", hashMap);
    }

    public int updatePersonStatus(long personId, short status) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("personId", personId);
        map.put("status", status);
        return getSqlMapClientTemplate().update("PersonDao.updatePersonStatus",
                map);
    }

    public void insertPersonJobs(Long personId, List<Job> jobs) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("personId", personId);
        map.put("jobs", jobs);
        getSqlMapClientTemplate().insert("PersonDao.insertPersonJobs", map);
    }

    public void deletePersonJob(Long personId) {
        getSqlMapClientTemplate().delete("PersonDao.deletePersonJob", personId);
    }

    public List<Person> listPersonsHasRole(String roleName) {
        return getSqlMapClientTemplate().queryForList(
                "PersonDao.listPersonsHasRole", roleName);
    }

    public List<Person> listNotUserPersons() {
        return getSqlMapClientTemplate().queryForList(
                "PersonDao.listNotUserPersons");
    }

    public List<Person> listPersonByJob(String jobName) {
        return getSqlMapClientTemplate().queryForList(
                "PersonDao.listPersonByJob", jobName);
    }

    public List<Person> listSimplePersons(short status) {
        return getSqlMapClientTemplate().queryForList(
                "PersonDao.listSimplePersons", status);
    }

}
