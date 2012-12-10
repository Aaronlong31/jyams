package com.jyams.security;

import org.springframework.stereotype.Component;

/**
 * User: zhanglong
 * Date: 12-12-10
 * Time: 下午9:47
 */
@Component
public class PermissionChecker {

    public boolean checkLogin(String username){
        return false;
    }

    public boolean checkPermission(String username, String permission){
        return false;
    }

}
