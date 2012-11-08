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
        return pagedQuery("com.jyams.hr.dao.PersonDao.selectPersons", map,
                pageNo, pageSize);
    }

    public List<String> getJobTypes() {
        return getSqlMapClientTemplate().queryForList(
                "com.jyams.hr.dao.PersonDao.getJobTypes");
    }

    public int updatePersonStates(long personId, short newSatus)
            throws SQLException {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("personId", personId);
        hashMap.put("status", newSatus);
        return getSqlMapClient().update(
                "com.jyams.hr.dao.PersonDao.updatePersonStates", hashMap);
    }

    public int updatePersonStatus(long personId, short status) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("personId", personId);
        map.put("status", status);
        return getSqlMapClientTemplate().update(
                "com.jyams.hr.dao.PersonDao.updatePersonStatus", map);
    }

    public void insertPersonJobs(Long personId, List<Job> jobs) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("personId", personId);
        map.put("jobs", jobs);
        getSqlMapClientTemplate().insert(
                "com.jyams.hr.dao.PersonDao.insertPersonJobs", map);
    }

    public void deletePersonJob(Long personId) {
        getSqlMapClientTemplate().delete(
                "com.jyams.hr.dao.PersonDao.deletePersonJob", personId);
    }

    public List<Person> listPersonsHasRole(String roleName) {
        return getSqlMapClientTemplate().queryForList(
                "com.jyams.hr.dao.PersonDao.listPersonsHasRole", roleName);
    }

    public List<Person> listNotUserPersons() {
        return getSqlMapClientTemplate().queryForList(
                "com.jyams.hr.dao.PersonDao.listNotUserPersons");
    }

    public List<Person> listPersonByJob(String jobName) {
        return getSqlMapClientTemplate().queryForList(
                "com.jyams.hr.dao.PersonDao.listPersonByJob", jobName);
    }

    public List<Person> listSimplePersons(short status) {
        return getSqlMapClientTemplate().queryForList(
                "com.jyams.hr.dao.PersonDao.listSimplePersons", status);
    }

}
