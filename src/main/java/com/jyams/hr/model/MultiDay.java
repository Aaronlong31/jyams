package com.jyams.hr.model;

/**
 * 多工日，现在只有三工日
 * 
 * @author zhanglong
 * 
 */
public class MultiDay {

    private String day;
    private int times = 3;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return day + ":" + times;
    }
}
