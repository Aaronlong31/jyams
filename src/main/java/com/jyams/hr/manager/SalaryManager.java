/**
 * 
 */
package com.jyams.hr.manager;

import java.util.List;

import com.jyams.hr.model.DailySalary;
import com.jyams.hr.model.MonthlySalary;

/**
 * 
 * @author zhanglong<br>
 *         2012-2-18 下午12:14:18<br>
 */
public interface SalaryManager {

    List<MonthlySalary> listMonthlySalary(int month);

    List<DailySalary> listDailySalary(long personId, int month);
}
