package com.jyams.hr.manager;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jyams.hr.model.Job;
import com.jyams.hr.model.Person;
import com.jyams.util.DataPage;

@Transactional(rollbackFor = Exception.class)
public interface PersonManager {

    /**
     * 添加员工
     * 
     * @param person
     * @return
     */
    long addPerson(Person person);

    /**
     * 修改员工
     * 
     * @param person
     * @return
     */
    boolean modifyPerson(Person person);

    /**
     * 员工离职
     * 
     * @param userId
     * @return
     */
    boolean dismissPerson(long userId);

    /**
     * 删除员工
     * 
     * @param personId
     * @return
     */
    boolean deletePerson(long personId);

    /**
     * 查看员工
     * 
     * @param personId
     * @return
     */
    @Transactional(readOnly = true)
    Person getPerson(long personId);

    /**
     * 查询所有在职员工
     * 
     * @return
     */
    @Transactional(readOnly = true)
    List<Person> listActivityPersons();

    /**
     * 查询所有在职员工
     * 
     * @return
     */
    @Transactional(readOnly = true)
    List<Person> listActivitySimplePersons();

    /**
     * 查询所有离职员工
     * 
     * @return
     */
    @Transactional(readOnly = true)
    List<Person> listDismissPersons();

    /**
     * 查询所有工种
     * 
     * @return
     */
    @Transactional(readOnly = true)
    List<String> listJobTypes();

    /**
     * 根据登录名查询员工
     * 
     * @param username
     * @return
     */
    @Transactional(readOnly = true)
    Person findPersonByName(String username);

    /**
     * 分页查询员工
     * 
     * @param departmentName
     * @param personNameLike
     * @param status
     * @param salaryType
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Transactional(readOnly = true)
    DataPage<Person> listPersons(String departmentName, String personNameLike,
            Short status, Short salaryType, Integer pageNo, Integer pageSize);

    List<Person> listPersonByJob(String jobName);

    /**
     * 判断用户名是否存在
     * 
     * @param username
     * @return 存在，返回true,不存在，返回false
     */
    @Transactional(readOnly = true)
    boolean assertUsernameExists(String personName);

    List<Person> listPersonsHasRole(String roleName);

    /**
     * 列出不是用户的员工
     * 
     * @return
     */
    List<Person> listNotUserPersons();

    // ***********************PersonRole********************************/
    long addJob(Job job);

    boolean deleteJob(long jobId);

    boolean modifyJob(Job job);

    List<Job> listJobs();
}
