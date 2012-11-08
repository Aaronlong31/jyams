/**
 * 
 */
package com.jyams.project.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.hr.model.DailySalary;
import com.jyams.hr.model.MonthlySalary;
import com.jyams.hr.model.Salary;
import com.jyams.util.dao.IBatisEntityDao;

/**
 * @author zhanglong<br>
 * 2012-2-18 下午12:50:59<br>
 */
@Repository
@SuppressWarnings("unchecked")
public class SalaryDao extends IBatisEntityDao<Salary> {

	/**
	 * @param month
	 * @return
	 */
	public List<MonthlySalary> listMonthlySalary(int month) {
		return getSqlMapClientTemplate().queryForList(
				"com.jyams.project.dao.SalaryDao.listMonthlySalary", month);
	}

	/**
	 * @param personId
	 * @param month
	 * @return
	 */
	public List<DailySalary> listDailySalary(long personId, int month) {
		Map<String, Object> hashMap = Maps.newHashMap();
		hashMap.put("personId", personId);
		hashMap.put("month", month);
		return getSqlMapClientTemplate().queryForList(
				"com.jyams.project.dao.SalaryDao.listDailySalary", hashMap);
	}

}
