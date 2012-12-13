package com.jyams.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jyams.util.json.DateToStringJsonSerializer;

/**
 * @author zhanglong
 * 
 *         2012-12-13 下午9:28:20
 */
public class LoggedUser extends User {

    private String session;
    private String ip;
    private long loginTime;

    public void setUser(User user) {
        this.setAuthorities(user.getAuthorities());
        this.setPassword(user.getPassword());
        this.setStatus(user.getStatus());
        this.setUserId(user.getUserId());
        this.setUsername(user.getUsername());
    }

    @JsonIgnore
    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @JsonSerialize(using = DateToStringJsonSerializer.class)
    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((session == null) ? 0 : session.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        LoggedUser other = (LoggedUser) obj;
        if (session == null) {
            if (other.session != null)
                return false;
        } else if (!session.equals(other.session))
            return false;
        return true;
    }

}
