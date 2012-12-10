package com.jyams.security;

import com.jyams.secure.model.User;

/**
 * User: zhanglong
 * Date: 12-12-10
 * Time: 下午11:03
 */
public class SecurityUtils {

    public static User getCurrentUser(){
        return null; //TODO
    }

    public static User getUser(String username){
        return null; //TODO
    }

    public static long getCurrentUserId(){
        return getCurrentUser().getUserId();
    }

    public static String getCurrentUsername(){
        return getCurrentUser().getUsername();
    }

}
