package com.jyams.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jyams.security.model.User;
import com.jyams.util.WebUtils;

/**
 * User: zhanglong Date: 12-12-10 Time: 下午11:03
 */
@Component
public class SecurityUtils {

    private static SecurityHolder securityHolder;

    public static final String SESSION_COOKIE_NAME = "JSESSIONID";

    public static User getCurrentUser() {
        return securityHolder.getLoginUser(WebUtils.getCookie(SESSION_COOKIE_NAME));
    }

    public static boolean isLogin() {
        return getCurrentUser() != null;
    }

    public static boolean isActive() {
        User user = getCurrentUser();
        return user != null && user.getStatus() == User.STATUS_ACTIVE;
    }

    public static User getUser(String username) {
        return securityHolder.getUser(username);
    }

    public static long getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    public static String getCurrentUsername() {
        return getCurrentUser().getUsername();
    }

    public static boolean hasPermission(String permission) {
        User user = getCurrentUser();
        return user.getPermissions().contains(permission);
    }

    public static boolean hasAnyPermissions(String[] permissions) {
        User user = getCurrentUser();
        for (String permission : permissions) {
            if (user.getPermissions().contains(permission)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasAllPermissions(String[] permissions) {
        User user = getCurrentUser();
        for (String permission : permissions) {
            if (!user.getPermissions().contains(permission)) {
                return false;
            }
        }
        return true;
    }

    @Autowired
    public void setSecurityHolder(SecurityHolder securityHolder) {
        SecurityUtils.securityHolder = securityHolder;
    }
}
