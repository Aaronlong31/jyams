package com.jyams.secure.manager.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;

/**
 * Models core user information retrieved by a {@link UserDetailsService}.
 * <p>
 * Implemented with value object semantics (immutable after construction, like a <code>String</code>).
 * Developers may use this class directly, subclass it, or write their own {@link UserDetails} implementation from
 * scratch.
 *
 * @author zhanglong
 */
public class BaseUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	//~ Instance fields ================================================================================================
	private final String password;
	private final String username;
	private final Set<GrantedAuthority> authorities;
	private final boolean accountNonExpired;
	private final boolean accountNonLocked;
	private final boolean credentialsNonExpired;
	private final boolean enabled;

	//~ Constructors ===================================================================================================

	/**
	 * Construct the <code>User</code> with the details required by
	 * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}.
	 *
	 * @param username the username presented to the
	 *        <code>DaoAuthenticationProvider</code>
	 * @param password the password that should be presented to the
	 *        <code>DaoAuthenticationProvider</code>
	 * @param enabled set to <code>true</code> if the user is enabled
	 * @param accountNonExpired set to <code>true</code> if the account has not
	 *        expired
	 * @param credentialsNonExpired set to <code>true</code> if the credentials
	 *        have not expired
	 * @param accountNonLocked set to <code>true</code> if the account is not
	 *        locked
	 * @param authorities the authorities that should be granted to the caller
	 *        if they presented the correct username and password and the user
	 *        is enabled. Not null.
	 *
	 * @throws IllegalArgumentException if a <code>null</code> value was passed
	 *         either as a parameter or as an element in the
	 *         <code>GrantedAuthority</code> collection
	 */
	public BaseUserDetails(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<GrantedAuthority> authorities) {

		if (((username == null) || "".equals(username)) || (password == null)) {
			throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
		}

		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
	}

	//~ Methods ========================================================================================================

	public boolean equals(Object rhs) {
		if (!(rhs instanceof BaseUserDetails) || (rhs == null)) {
			return false;
		}

		BaseUserDetails baseUserDetails = (BaseUserDetails) rhs;

		// We rely on constructor to guarantee any User has non-null
		// authorities
		if (!authorities.equals(baseUserDetails.authorities)) {
			return false;
		}

		// We rely on constructor to guarantee non-null username and password
		return (this.getPassword().equals(baseUserDetails.getPassword())
				&& this.getUsername().equals(baseUserDetails.getUsername())
				&& (this.isAccountNonExpired() == baseUserDetails.isAccountNonExpired())
				&& (this.isAccountNonLocked() == baseUserDetails.isAccountNonLocked())
				&& (this.isCredentialsNonExpired() == baseUserDetails.isCredentialsNonExpired()) && (this
				.isEnabled() == baseUserDetails.isEnabled()));
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public int hashCode() {
		int code = 9792;

		for (GrantedAuthority authority : getAuthorities()) {
			code = code * (authority.hashCode() % 7);
		}

		if (this.getPassword() != null) {
			code = code * (this.getPassword().hashCode() % 7);
		}

		if (this.getUsername() != null) {
			code = code * (this.getUsername().hashCode() % 7);
		}

		if (this.isAccountNonExpired()) {
			code = code * -2;
		}

		if (this.isAccountNonLocked()) {
			code = code * -3;
		}

		if (this.isCredentialsNonExpired()) {
			code = code * -5;
		}

		if (this.isEnabled()) {
			code = code * -7;
		}

		return code;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	private static SortedSet<GrantedAuthority> sortAuthorities(
			Collection<GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		// Ensure array iteration order is predictable (as per UserDetails.getAuthorities() contract and SEC-717)
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(
				new AuthorityComparator());

		for (GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority,
					"GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}

		return sortedAuthorities;
	}

	protected static class AuthorityComparator implements Comparator<GrantedAuthority>,
			Serializable {
		private static final long serialVersionUID = 1L;

		public int compare(GrantedAuthority g1, GrantedAuthority g2) {
			// Neither should ever be null as each entry is checked before adding it to the set.
			// If the authority is null, it is a custom authority and should precede others.
			if (g2.getAuthority() == null) {
				return -1;
			}

			if (g1.getAuthority() == null) {
				return 1;
			}

			return g1.getAuthority().compareTo(g2.getAuthority());
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(": ");
		sb.append("Username: ").append(this.username).append("; ");
		sb.append("Password: [PROTECTED]; ");
		sb.append("Enabled: ").append(this.enabled).append("; ");
		sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
		sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
		sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");

		if (!authorities.isEmpty()) {
			sb.append("Granted Authorities: ");

			boolean first = true;
			for (GrantedAuthority auth : authorities) {
				if (!first) {
					sb.append(",");
				}
				first = false;

				sb.append(auth);
			}
		} else {
			sb.append("Not granted any authorities");
		}

		return sb.toString();
	}
}
