package com.jyams.security.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 用户
 * 
 * @author zhanglong
 * 
 */
public class User {

    /**
     * 状态_激活
     */
    public static final short STATUS_ACTIVE = 1;

    /**
     * 状态_未激活
     */
    public static final short STATUS_INACTIVE = 0;

    private long userId;
    private String username;
    private String password;
    private short status;
    private List<Authority> authorities;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getStatusString() {
        switch (status) {
        case STATUS_ACTIVE:
            return "激活";
        case STATUS_INACTIVE:
            return "未激活";
        default:
            return "";
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}
