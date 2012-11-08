package com.jyams.hr.manager;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jyams.exception.BusinessException;
import com.jyams.hr.model.Department;

@Transactional(rollbackFor = Exception.class)
public interface DepartmentManager {

    /**
     * 添加部门
     * 
     * @param department
     * @return
     * @throws BusinessException
     *             部门名称已存在时抛出
     */
    long addDepartment(Department department) throws BusinessException;

    boolean modifyDepartment(Department department);

    boolean deleteDepartment(long departmentId);

    @Transactional(readOnly = true)
    Department getDepartment(long departmentId);

    @Transactional(readOnly = true)
    List<Department> listDepartments(String superName);

    @Transactional(readOnly = true)
    List<Department> listAll();

    @Transactional(readOnly = true)
    List<Department> listWithPersons(Short salaryType);

    @Transactional(readOnly = true)
    boolean assertExistsDepartmentName(String departmentName);
}
