package com.jyams.hr.model;

import com.jyams.common.SalaryCalculator;
import com.jyams.util.DateTimeUtils;

/**
 * @author zhanglong<br>
 */
public class DailySalary extends Salary {

    private int day;
    private int startTime;
    private int endTime;

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getDay() {
        return day;
    }

    public String getDayString() {
        return DateTimeUtils.convertIntegerDayToString(day);
    }

    public float getHours() {
        return SalaryCalculator.getValidHours(startTime, endTime);
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getStartTimeString() {
        return DateTimeUtils.convertMinuteToString(this.startTime);
    }

    public String getEndTimeString() {
        return DateTimeUtils.convertMinuteToString(this.endTime);
    }
}
