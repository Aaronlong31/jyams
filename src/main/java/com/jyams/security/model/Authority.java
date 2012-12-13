package com.jyams.security.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 权限 注释见{@link User}
 * 
 * @author Aaron
 * 
 */
public class Authority {

    /**
     * SpringSecurity中默认的角色/授权名前缀
     */
    public static final String AUTHORITY_PREFIX = "ROLE_";

    private Long authorityId;
    private String name;
    private String description;
    private long moduleId;

    public Authority() {
    }

    public Authority(Long authorityId, String name) {
        this.authorityId = authorityId;
        this.name = name;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefixedName() {
        return AUTHORITY_PREFIX + name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }

}
