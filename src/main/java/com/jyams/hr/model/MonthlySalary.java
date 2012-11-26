/**
 * 
 */
package com.jyams.hr.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.jyams.util.DateTimeUtils;

/**
 * @author zhanglong<br>
 *         2012-2-18 下午12:19:29<br>
 */
public class MonthlySalary extends Salary {

    private int month;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getMonthString() {
        return DateTimeUtils.convertIntegerMonthToString(month);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}
