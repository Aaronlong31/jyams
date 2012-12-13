package com.jyams.security;

import com.jyams.exception.InActiveUserException;
import com.jyams.exception.UnLoginException;
import com.jyams.security.model.Authority;
import com.jyams.security.model.User;
import com.jyams.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * User: zhanglong
 * Date: 12-12-12
 * Time: 下午8:52
 */
@Service
public class SecurityService {

    @Autowired
    private SecurityHolder securityHolder;

    public boolean login(String username, String password){
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        String encodedPassword = md5.encodePassword(password, null);

        User user = securityHolder.getUser(username);

        if (user == null || !user.getPassword().equals(encodedPassword)){
              return false;
        }

        if (user.getStatus() == User.STATUS_INACTIVE) {
            throw new InActiveUserException();
        }

        String jsessionid = WebUtils.getCookie(SecurityUtils.SESSION_COOKIE_NAME);
        securityHolder.addLoginUser(jsessionid, user);
        return true;
    }

    public boolean logout(){

        String jsessionid = WebUtils.getCookie(SecurityUtils.SESSION_COOKIE_NAME);
        securityHolder.removeLoginUser(jsessionid);

        return true;
    }

    public boolean checkPermission(String username, String permission){

        User user = SecurityUtils.getUser(username);

        if(user == null){
            throw new UnLoginException();
        }

        for (Authority authority : user.getAuthorities()) {
            if(authority.getName().equals(permission)){
                return true;
            }
        }
        return false;
    }
}
