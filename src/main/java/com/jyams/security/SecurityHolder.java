package com.jyams.security;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jyams.security.manager.UserManager;
import com.jyams.security.model.LoggedUser;
import com.jyams.security.model.User;

/**
 * User: zhanglong Date: 12-12-10 Time: 下午9:54
 */
@Component
public class SecurityHolder {

    private final Set<User> users = Sets.newHashSet();

    private boolean allowDuplicateLogin;

    private final Map<String, LoggedUser> loggedUsers = Maps.newHashMap();

    @Autowired
    private UserManager userManager;

    @PostConstruct
    public void loadUsers() {
        users.addAll(this.userManager.getAllUser());
    }

    public void reloadUser(long userId) {
        User user = this.userManager.getUser(userId);

        if (user != null) {
            users.add(user);
        }
    }

    public void addLoginUser(LoggedUser user) {

        if (!allowDuplicateLogin) {
            for (Entry<String, LoggedUser> entry : loggedUsers.entrySet()) {
                if (entry.getValue().getUserId() == user.getUserId()) {
                    loggedUsers.remove(entry);
                }
            }
        }

        loggedUsers.put(user.getSession(), user);
    }

    public void removeLoginUser(String session) {
        loggedUsers.remove(session);
    }

    public Map<String, LoggedUser> getLoginUsers() {
        return loggedUsers;
    }

    public LoggedUser getLoginUser(String session) {
        return loggedUsers.get(session);
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
