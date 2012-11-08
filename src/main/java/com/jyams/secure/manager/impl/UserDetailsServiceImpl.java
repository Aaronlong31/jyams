package com.jyams.secure.manager.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.jyams.secure.manager.UserManager;
import com.jyams.secure.model.Authority;
import com.jyams.secure.model.User;

/**
 * 实现SpringSecurity的UserDetailsService接口，实现获取用户Detail信息的回调函数。
 * 
 * @author Aaron
 * 
 */
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserManager userManager;

    /**
     * 获取用户Details信息的回调函数
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {
        User user = userManager.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户" + username + "不存在");
        }

        Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);

        boolean enabled = user.getStatus() == User.STATUS_ACTIVE;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        UserInfo userInfo = new UserInfo(username, user.getPassword(), enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked,
                grantedAuths);
        userInfo.setUserId(user.getUserId());
        return userInfo;
    }

    /**
     * 获得用户所有角色的权限的集合
     */
    private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        for (Authority authority : user.getAuthorities()) {
            // TODO user SimpleGrantedAuthority
            authSet.add(new SimpleGrantedAuthority(authority.getPrefixedName()));
        }
        return authSet;
    }

}
