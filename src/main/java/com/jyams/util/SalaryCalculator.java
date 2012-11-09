package com.jyams.util;

/**
 * @author zhanglong<br>
 * 
 *         2012-2-18 下午05:06:21<br>
 */
public final class SalaryCalculator {

    private static final int REST_TIME_START = 12 * 60;
    private static final int REST_TIME_END = 13 * 60;

    private SalaryCalculator() {
    }

    public static float getValidHours(int startTime, int endTime) {
        int beforeTime = Math.max(REST_TIME_START - startTime, 0);
        int afterTime = Math.max(endTime - REST_TIME_END, 0);
        int subBeforeTime = Math.max(startTime - REST_TIME_END, 0);
        int subAfterTime = Math.max(REST_TIME_START - endTime, 0);
        return (float) (beforeTime + afterTime - subBeforeTime - subAfterTime) / 60;
    }

    public static float calculate(int startTime, int endTime, float dailySalary) {
        return getValidHours(startTime, endTime) * dailySalary / 8;
    }
}
