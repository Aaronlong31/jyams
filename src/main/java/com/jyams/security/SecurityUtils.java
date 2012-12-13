package com.jyams.security;

import com.jyams.security.model.User;
import com.jyams.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: zhanglong
 * Date: 12-12-10
 * Time: 下午11:03
 */
@Component
public class SecurityUtils {

    private static SecurityHolder securityHolder;

    public static final String SESSION_COOKIE_NAME = "JSESSIONID";

    public static User getCurrentUser(){
        return securityHolder.getLoginUser(WebUtils.getCookie(SESSION_COOKIE_NAME));
    }

    public static User getUser(String username){
        return securityHolder.getUser(username);
    }

    public static long getCurrentUserId(){
        return getCurrentUser().getUserId();
    }

    public static String getCurrentUsername(){
        return getCurrentUser().getUsername();
    }

    @Autowired
    public void setSecurityHolder(SecurityHolder securityHolder) {
        SecurityUtils.securityHolder = securityHolder;
    }
}
