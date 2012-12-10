package com.jyams.security;

import com.jyams.secure.model.User;

import java.util.List;

/**
 * User: zhanglong
 * Date: 12-12-10
 * Time: 下午9:54
 */
public class SecurityHolder {

    private static List<User> users;

    public static void addUser(User user){
        users.add(user);
    }

    public static void removeUser(User user){
        users.remove(user);
    }
}
