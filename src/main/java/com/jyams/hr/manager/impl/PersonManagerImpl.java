package com.jyams.hr.manager.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyams.hr.dao.JobDao;
import com.jyams.hr.dao.PersonDao;
import com.jyams.hr.manager.PersonManager;
import com.jyams.hr.model.Job;
import com.jyams.hr.model.Person;
import com.jyams.util.DataPage;
import com.jyams.util.IdUtil;

@Transactional(rollbackFor = Exception.class)
@Service
public class PersonManagerImpl implements PersonManager {

	@Autowired
	private PersonDao personDao;
	@Autowired
	private JobDao jobDao;

	/**
	 * <p>添加人员</p>
	 * 
	 * @param person
	 */
	@Override
	public long addPerson(Person person) {
		long personId = IdUtil.nextLong();
		person.setPersonId(personId);
		person.setCreatedTimestamp(System.currentTimeMillis());
		person.setStatus(Person.STATUS_ACTIVE);
		personDao.insert(person);

		List<Job> roles = person.getJobs();
		if (roles != null && roles.size() > 0) {
			personDao.insertPersonJobs(personId, roles);
		}
		return personId;
	}

	@Override
	public List<Person> listActivityPersons() {
		return personDao.findBy("status", Person.STATUS_ACTIVE);
	}

	@Override
	public List<Person> listActivitySimplePersons() {
		return personDao.listSimplePersons(Person.STATUS_ACTIVE);
	}
	
	@Override
	public List<Person> listDismissPersons() {
		return personDao.findBy("status", Person.STATUS_DIMISSION);
	}

	/**
	 * 获取所有工种
	 */
	@Override
	public List<String> listJobTypes() {
		return personDao.getJobTypes();
	}

	@Override
	public boolean modifyPerson(Person person) {
		// 修改员工基本信息
		boolean result = personDao.update(person) > 0;

		// 删除旧的角色
		personDao.deletePersonJob(person.getPersonId());

		// 添加新的角色
		List<Job> roles = person.getJobs();
		if (roles != null && roles.size() > 0) {
			personDao.insertPersonJobs(person.getPersonId(), roles);
		}
		return result;
	}

	@Override
	public boolean deletePerson(long personId) {
		return personDao.removeById(personId) > 0;
	}

	@Override
	public Person getPerson(long personId) {
		List<Job> roles = jobDao.findBy("personId", personId);
		Person person = personDao.get(personId);
		person.setJobs(roles);
		return person;
	}

	@Override
	public DataPage<Person> listPersons(String departmentName, String personNameLike, Short status,
			Short salaryType, Integer pageNo, Integer pageSize) {
		return personDao.selectPersons(departmentName, personNameLike, status, salaryType, pageNo,
				pageSize);
	}

	@Override
	public Person findPersonByName(String name) {
		Person person = personDao.findUniqueBy("personName", name);
		if (person == null) {
			return null;
		}
		List<Job> roles = jobDao.findBy("personId", person.getPersonId());
		person.setJobs(roles);
		return person;
	}

	@Override
	public boolean assertUsernameExists(String personName) {
		return personDao.findUniqueBy("personName", personName) != null;
	}

	@Override
	public boolean dismissPerson(long personId) {
		return personDao.updatePersonStatus(personId, Person.STATUS_DIMISSION) > 0;
	}

	@Override
	public List<Person> listPersonsHasRole(String roleName) {
		return personDao.listPersonsHasRole(roleName);
	}

	@Override
	public List<Person> listNotUserPersons() {
		return personDao.listNotUserPersons();
	}

	@Override
	public long addJob(Job job) {
		long jobId = IdUtil.nextLong();
		job.setJobId(jobId);
		jobDao.insert(job);
		return jobId;
	}

	@Override
	public boolean deleteJob(long jobId) {
		return jobDao.removeById(jobId) > 0;
	}

	@Override
	public List<Job> listJobs() {
		return jobDao.getAll();
	}

	@Override
	public boolean modifyJob(Job job) {
		return jobDao.update(job) > 0;
	}

	@Override
	public List<Person> listPersonByJob(String jobName) {
		// TODO 根据职位名查找员工
		return personDao.listPersonByJob(jobName);
	}

}
