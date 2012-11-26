package com.jyams.hr.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.common.collect.Lists;

/**
 * 客户商
 * 
 * @author zhanglong
 * 
 */
public class Client {

    private Long clientId; // 客户商标识
    private String clientName; // 客户商名称
    private String address; // 地址
    private String phone; // 联系电话
    private String email; // 邮箱
    private int priority; // 热度
    private List<ClientPrincipal> principals = Lists.newArrayList();

    public List<ClientPrincipal> getPrincipals() {
        return principals;
    }

    public Client setPrincipals(List<ClientPrincipal> principals) {
        this.principals = principals;
        return this;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getPriority() {
        return priority;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
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
