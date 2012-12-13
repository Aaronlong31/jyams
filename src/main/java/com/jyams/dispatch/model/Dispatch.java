package com.jyams.dispatch.model;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jyams.util.DateTimeUtils;
import com.jyams.util.json.DateToStringJsonSerializer;
import com.jyams.util.json.LongToStringJsonSerializer;

/**
 * 派工信息
 * 
 * @author zhanglong
 * 
 */
public class Dispatch {

    public static final Map<Short, String> DISPATCH_TYPE_MAP = Maps.newHashMap();

    public static final Map<Short, String> PROJECT_TYPE_MAP = Maps.newHashMap();

    /*** 派工类型 - 正常派工 */
    public static final short DISPATCH_TYPE_NORMAL = 1;
    /*** 派工类型 - 加班派工 */
    public static final short DISPATCH_TYPE_OVERTIME = 2;

    /*** 派工项目类型 - 在建项目 */
    public static final short PROJECT_TYPE_BUILDING = 1;
    /*** 派工项目类型 - 临时项目 */
    public static final short PROJECT_TYPE_TEMP = 2;

    static {
        DISPATCH_TYPE_MAP.put(DISPATCH_TYPE_NORMAL, "正常派工");
        DISPATCH_TYPE_MAP.put(DISPATCH_TYPE_OVERTIME, "加班派工");

        PROJECT_TYPE_MAP.put(PROJECT_TYPE_BUILDING, "在建项目");
        PROJECT_TYPE_MAP.put(PROJECT_TYPE_TEMP, "临时项目");
    }

    private Long dispatchId; // 派工编号
    private Long projectId; // 项目标识
    private String projectName; // 项目名称
    private short projectType; // 项目类型
    private long principalId; // 负责人编号
    private String principalName; // 负责人姓名
    private int dispatchDay; // 派工日期
    private short dispatchType; // 派工类型
    private long createdTimestamp; // 创建时戳
    private List<DispatchWork> dispatchWorks = Lists.newArrayList(); // 派工工作

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public Long getDispatchId() {
        return dispatchId;
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public Long getProjectId() {
        return projectId;
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public long getPrincipalId() {
        return principalId;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public short getDispatchType() {
        return dispatchType;
    }

    @JsonSerialize(using = DateToStringJsonSerializer.class)
    public long getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setDispatchId(Long dispatchId) {
        this.dispatchId = dispatchId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setPrincipalId(long principalId) {
        this.principalId = principalId;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public void setDispatchType(short dispatchType) {
        this.dispatchType = dispatchType;
    }

    public void setCreatedTimestamp(long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public short getProjectType() {
        return projectType;
    }

    public void setProjectType(short projectType) {
        this.projectType = projectType;
    }

    @JsonSerialize(using = DateToStringJsonSerializer.class)
    public int getDispatchDay() {
        return dispatchDay;
    }

    public void setDispatchDay(int dispatchDay) {
        this.dispatchDay = dispatchDay;
    }

    public void setDispatchDayString(String dispatchDayString) {
        this.dispatchDay = DateTimeUtils.convertStringToInteger(dispatchDayString);
    }

    public String getDispatchDayString() {
        return DateTimeUtils.convertIntegerDayToString(dispatchDay);
    }

    public String getCreatedTimestampString() {
        return DateTimeUtils.convertLongToString(this.createdTimestamp);
    }

    public List<DispatchWork> getDispatchWorks() {
        return dispatchWorks;
    }

    public void setDispatchWorks(List<DispatchWork> dispatchWorks) {
        this.dispatchWorks = dispatchWorks;
    }

    public String getProjectTypeString() {
        return PROJECT_TYPE_MAP.get(this.projectType);
    }

    public String getDispatchTypeString() {
        return DISPATCH_TYPE_MAP.get(this.dispatchType);
    }

    /**
     * 计算派工总费用
     */
    public float getCost() {
        float totalCost = 0F;
        for (DispatchWork dispatchWork : dispatchWorks) {
            totalCost += dispatchWork.getCost();
        }
        return totalCost;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
