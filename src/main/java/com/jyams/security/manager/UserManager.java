package com.jyams.security.manager;

import java.util.List;

import com.jyams.security.model.Module;
import com.jyams.security.model.User;
import com.jyams.util.DataPage;

public interface UserManager {

    User addUser(User user);

    User getUser(long userId);

    boolean deleteUser(long userId);

    boolean modifyUser(User user);

    DataPage<User> listUsers(String usernameLike, Short status, Integer pageNo,
            Integer pageSize);

    User findUserByUsername(String username);

    List<Module> listAuthorities();

    boolean resetPassword(Long userId, String password);

    List<User> getAllUser();

}
