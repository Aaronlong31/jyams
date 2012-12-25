package com.jyams.dispatch.query;

import com.jyams.dispatch.model.DispatchWork;
import com.jyams.util.DateTimeUtils;
import com.jyams.util.search.Query;
import com.jyams.util.search.SearchFilter;

public class DispatchWorkQuery extends Query<DispatchWork> {

    private String personName;
    private Integer startTime;
    private Integer endTime;
    private Long personId;

    // Dispatch
    private Long projectId;
    private String projectName;
    private Integer dispatchDay;
    private Integer dispatchDayStart;
    private Integer dispatchDayEnd;
    private Short dispatchType;

    public DispatchWorkQuery() {
        super();
    }

    public DispatchWorkQuery(int pageNo, int pageSize) {
        super(pageNo, pageSize);
    }

    public DispatchWorkQuery(SearchFilter filter) {
        super(filter);
    }

    public String getPersonName() {
        return personName;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public Long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public Integer getDispatchDay() {
        return dispatchDay;
    }

    public Short getDispatchType() {
        return dispatchType;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public void setStartTimeString(String startTimeString) {
        this.startTime = DateTimeUtils.convertStringToMinute(startTimeString);
    }

    public void setEndTimeString(String endTimeString) {
        this.endTime = DateTimeUtils.convertStringToMinute(endTimeString);
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setDispatchDay(Integer dispatchDay) {
        this.dispatchDay = dispatchDay;
    }

    public void setDispatchType(Short dispatchType) {
        this.dispatchType = dispatchType;
    }

    public Integer getDispatchDayStart() {
        return dispatchDayStart;
    }

    public void setDispatchDayStart(Integer dispatchDayStart) {
        this.dispatchDayStart = dispatchDayStart;
    }

    public Integer getDispatchDayEnd() {
        return dispatchDayEnd;
    }

    public void setDispatchDayEnd(Integer dispatchDayEnd) {
        this.dispatchDayEnd = dispatchDayEnd;
    }

}
