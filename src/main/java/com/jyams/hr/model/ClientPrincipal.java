package com.jyams.hr.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 客户负责人
 * 
 * @author zhanglong
 * 
 */
public class ClientPrincipal {

    private Long clientPrincipalId; // 负责人标识
    private String name; // 姓名
    private long clientId; // 客户商标识
    private String phone; // 电话
    private int priority = 1; // 热度

    public Long getClientPrincipalId() {
        return clientPrincipalId;
    }

    public void setClientPrincipalId(Long clientPrincipalId) {
        this.clientPrincipalId = clientPrincipalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
