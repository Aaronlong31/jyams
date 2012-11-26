package com.jyams.secure.manager;

import java.util.List;

import com.jyams.secure.model.Module;
import com.jyams.secure.model.User;
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
}
