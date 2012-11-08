package com.jyams.hr.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 部门
 * 
 * @author zhanglong
 * 
 */
public class Department {

    private Long departmentId; // 部门标识
    private String departmentName; // 部门名称
    private Long superId; // 上级部门标识
    private String superName; // 上级部门名称
    private Long principalId; // 部门负责人
    private String principalName; // 部门负责人姓名

    private List<Person> persons;

    public Long getDepartmentId() {
        return departmentId;
    }

    public Department setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Department setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public Long getSuperId() {
        return superId;
    }

    public Department setSuperId(Long superId) {
        this.superId = superId;
        return this;
    }

    public String getSuperName() {
        return superName;
    }

    public Department setSuperName(String superName) {
        this.superName = superName;
        return this;
    }

    public Long getPrincipalId() {
        return principalId;
    }

    public Department setPrincipalId(Long principalId) {
        this.principalId = principalId;
        return this;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public Department setPrincipalName(String principalName) {
        this.principalName = principalName;
        return this;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
