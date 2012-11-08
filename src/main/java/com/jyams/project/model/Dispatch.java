package com.jyams.project.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.common.collect.Lists;
import com.jyams.util.DateTimeUtils;

/**
 * 派工信息
 * 
 * @author zhanglong
 * 
 */
public class Dispatch {

    /*** 派工类型 - 正常派工 */
    public static final short DISPATCH_TYPE_NORMAL = 1;
    /*** 派工类型 - 加班派工 */
    public static final short DISPATCH_TYPE_OVERTIME = 2;

    /*** 派工项目类型 - 在建项目 */
    public static final short PROJECT_TYPE_BUILDING = 1;
    /*** 派工项目类型 - 临时项目 */
    public static final short PROJECT_TYPE_TEMP = 2;

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

    public Long getDispatchId() {
        return dispatchId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public long getPrincipalId() {
        return principalId;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public short getDispatchType() {
        return dispatchType;
    }

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

    public int getDispatchDay() {
        return dispatchDay;
    }

    public void setDispatchDay(int dispatchDay) {
        this.dispatchDay = dispatchDay;
    }

    public void setDispatchDayString(String dispatchDayString) {
        this.dispatchDay = DateTimeUtils
                .convertStringToInteger(dispatchDayString);
    }

    public List<DispatchWork> getDispatchWorks() {
        return dispatchWorks;
    }

    public void setDispatchWorks(List<DispatchWork> dispatchWorks) {
        this.dispatchWorks = dispatchWorks;
    }

    public String getProjectTypeString() {
        switch (projectType) {
        case PROJECT_TYPE_BUILDING:
            return "在建项目";
        case PROJECT_TYPE_TEMP:
            return "临时项目";
        default:
            return null;
        }
    }

    public String getDispatchTypeString() {
        switch (dispatchType) {
        case DISPATCH_TYPE_NORMAL:
            return "正常派工";
        case DISPATCH_TYPE_OVERTIME:
            return "加班派工";
        default:
            return null;
        }
    }

    /**
     * 计算派工总费用
     * 
     * @return
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
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}
