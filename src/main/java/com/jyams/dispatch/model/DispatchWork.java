package com.jyams.dispatch.model;

import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Maps;
import com.jyams.util.DateTimeUtils;
import com.jyams.util.SalaryCalculator;
import com.jyams.util.json.LongToStringJsonSerializer;

/**
 * 派工工作
 * 
 * @author zhanglong
 * 
 */
public class DispatchWork {

    /*** 施工人员类型 - 公司内部人员 */
    public static final short PERSON_TYPE_COMPANY = 1;
    /*** 施工人员类型 - 临时人员 */
    public static final short PERSON_TYPE_TEMP = 2;

    public static final Map<Short, String> PERSON_TYPE_MAP = Maps.newHashMap();

    static {
        PERSON_TYPE_MAP.put(PERSON_TYPE_COMPANY, "公司员工");
        PERSON_TYPE_MAP.put(PERSON_TYPE_TEMP, "临时员工");
    }

    private Long dispatchWorkId; // 工作标识
    private long dispatchId; // 派工标识
    private Long personId; // 施工人编号
    private String personName; // 施工人姓名
    private short personType; // 施工人类型
    private String idCardCode; // 身份证号码
    private float salary; // 员工8小时制工资
    private int startTime; // 施工开始时间
    private int endTime; // 施工结束时间
    private float cost;// 员工当日实际工资

    private Dispatch dispatch;

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public Long getDispatchWorkId() {
        return dispatchWorkId;
    }

    public void setDispatchWorkId(Long dispatchWorkId) {
        this.dispatchWorkId = dispatchWorkId;
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public long getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(long dispatchId) {
        this.dispatchId = dispatchId;
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public short getPersonType() {
        return personType;
    }

    public void setPersonType(short personType) {
        this.personType = personType;
    }

    public String getIdCardCode() {
        return idCardCode;
    }

    public void setIdCardCode(String idCardCode) {
        this.idCardCode = idCardCode;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setStartTimeString(String startTimeString) {
        startTime = DateTimeUtils.convertStringToMinute(startTimeString);
    }

    public void setEndTimeString(String endTimeString) {
        endTime = DateTimeUtils.convertStringToMinute(endTimeString);
    }

    public String getStartTimeString() {
        return DateTimeUtils.convertMinuteToString(startTime);
    }

    public String getEndTimeString() {
        return DateTimeUtils.convertMinuteToString(endTime);
    }

    public int getTotalTime() {
        return endTime - startTime;
    }

    public String getPersonTypeString() {
        return PERSON_TYPE_MAP.get(this.personType);
    }

    public Dispatch getDispatch() {
        return dispatch;
    }

    public void setDispatch(Dispatch dispatch) {
        this.dispatch = dispatch;
    }

    public float getCost() {
        return cost;
    }

    public float getHours() {
        return SalaryCalculator.getValidHours(startTime, endTime);
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * 计算员工工资
     */
    public void calculateCost() {
        cost = SalaryCalculator.calculate(startTime, endTime, salary);
    }
}
