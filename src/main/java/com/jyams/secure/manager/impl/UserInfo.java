package com.jyams.secure.manager.impl;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserInfo extends BaseUserDetails {

	private long userId;

	private static final long serialVersionUID = 1L;

	public UserInfo(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
