package com.jyams.security.model;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jyams.util.json.LongToStringJsonSerializer;

/**
 * 用户
 * 
 * @author zhanglong
 * 
 */
public class User {

    private static Logger logger = LoggerFactory.getLogger(User.class);

    /**
     * 状态_激活
     */
    public static final short STATUS_ACTIVE = 1;

    /**
     * 状态_未激活
     */
    public static final short STATUS_INACTIVE = 0;

    public static final Map<Short, String> STATUS_MAP = Maps.newHashMap();

    static {
        STATUS_MAP.put(STATUS_ACTIVE, "激活");
        STATUS_MAP.put(STATUS_INACTIVE, "未激活");
    }

    private long userId;
    private String username;
    private String password;
    private short status;
    private List<Authority> authorities;
    private List<String> permissions;

    @JsonSerialize(using = LongToStringJsonSerializer.class)
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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getStatus() {
        return status;
    }

    @JsonIgnore
    public void setStatus(short status) {
        this.status = status;
    }

    @JsonIgnore
    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
        this.permissions = Lists.newArrayList();
        if (authorities == null) {
            return;
        }
        for (Authority authority : authorities) {
            this.permissions.add(authority.getName());
        }
        logger.info(this.permissions.toString());
    }

    @JsonIgnore
    public String getStatusString() {
        return STATUS_MAP.get(this.status);
    }

    public List<String> getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (userId != other.userId)
            return false;
        return true;
    }

}
