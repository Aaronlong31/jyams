package com.jyams.hr.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyams.exception.BusinessException;
import com.jyams.hr.manager.DepartmentManager;
import com.jyams.hr.model.Department;
import com.jyams.project.dao.DepartmentDao;
import com.jyams.util.IdUtil;

@Transactional(rollbackFor = Exception.class)
@Service
public class DepartmentManagerImpl implements DepartmentManager {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public long addDepartment(Department department) throws BusinessException {
		Department dept = departmentDao.findUniqueBy("departmentName", department
				.getDepartmentName());
		if (dept != null) {
			throw new BusinessException("部门名称已存在");
		}
		long departmentId = IdUtil.nextLong();
		department.setDepartmentId(departmentId);
		departmentDao.insert(department);

		return departmentId;
	}

	@Override
	public boolean modifyDepartment(Department department) {
		return departmentDao.update(department) > 0;
	}

	@Override
	public boolean deleteDepartment(long departmentId) {
		return departmentDao.removeById(departmentId) > 0;
	}

	@Override
	public Department getDepartment(long departmentId) {
		return departmentDao.get(departmentId);
	}

	@Override
	public List<Department> listDepartments(String superName) {
		return departmentDao.findBy("superName", superName);
	}

	@Override
	public List<Department> listAll() {
		return departmentDao.getAll();
	}

	@Override
	public List<Department> listWithPersons(Short salaryType) {
		return departmentDao.getWithPersons(salaryType);
	}

	@Override
	public boolean assertExistsDepartmentName(String departmentName) {
		Department dept = departmentDao.findUniqueBy("departmentName", departmentName);
		return dept != null;
	}
}
