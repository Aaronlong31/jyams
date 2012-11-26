/**
 * 
 */
package com.jyams.hr.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyams.hr.manager.SalaryManager;
import com.jyams.hr.model.DailySalary;
import com.jyams.hr.model.MonthlySalary;
import com.jyams.project.dao.SalaryDao;

/**
 * @author zhanglong<br>
 * 2012-2-18 下午12:24:06<br>
 */
@Service
public class SalaryManagerImpl implements SalaryManager {

	@Autowired
	private SalaryDao salaryDao;

	@Override
	public List<MonthlySalary> listMonthlySalary(int month) {
		return salaryDao.listMonthlySalary(month);
	}

	@Override
	public List<DailySalary> listDailySalary(long personId, int month) {
		return salaryDao.listDailySalary(personId, month);
	}

}
