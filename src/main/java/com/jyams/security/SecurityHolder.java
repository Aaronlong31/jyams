package com.jyams.security;

import com.google.common.collect.Maps;
import com.jyams.security.manager.UserManager;
import com.jyams.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * User: zhanglong
 * Date: 12-12-10
 * Time: 下午9:54
 */
@Component
public class SecurityHolder {

    private List<User> users;

    private Map<String, User> loginUsers = Maps.newHashMap();

    @Autowired
    private UserManager userManager;

    @PostConstruct
    public void loadUsers(){
        users = this.userManager.getAllUser();
    }

    public User getUser(String username){
        for (User user: users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public void addLoginUser(String session, User user){
        loginUsers.put(session, user);
    }

    public void removeLoginUser(String session){
        loginUsers.remove(session);
    }

    public Map<String, User> getLoginUsers() {
        return loginUsers;
    }

    public User getLoginUser(String session){
       return loginUsers.get(session);
    }

}
