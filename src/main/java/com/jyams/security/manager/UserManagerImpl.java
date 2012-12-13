package com.jyams.security.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.jyams.security.dao.AuthorityDao;
import com.jyams.security.dao.UserDao;
import com.jyams.security.model.Authority;
import com.jyams.security.model.Module;
import com.jyams.security.model.User;
import com.jyams.util.DataPage;

/**
 * 
 * @author zhanglong
 * 
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public User addUser(User user) {
        user.setStatus(User.STATUS_ACTIVE);
        userDao.insert(user);
        List<Authority> authorities = user.getAuthorities();
        if (authorities != null && authorities.size() > 0) {
            userDao.insertUserAuthorities(user.getUserId(), authorities);
        }
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(long userId) {
        List<Authority> authorities = authorityDao.findBy("userId", userId);
        User user = userDao.get(userId);
        user.setAuthorities(authorities);
        return user;
    }

    @Override
    public boolean deleteUser(long userId) {
        return userDao.removeById(userId) > 0;
    }

    @Transactional(readOnly = true)
    @Override
    public DataPage<User> listUsers(String usernameLike, Short status, Integer pageNo,
            Integer pageSize) {
        return userDao.listUsers(usernameLike, status, pageNo, pageSize);
    }

    @Override
    public boolean modifyUser(User user) {
        // 删除旧的角色
        userDao.deleteUserAuthority(user.getUserId());

        // 添加新的角色
        List<Authority> authorities = user.getAuthorities();
        if (authorities != null && authorities.size() > 0) {
            userDao.insertUserAuthorities(user.getUserId(), authorities);
        }
        return userDao.update(user) > 0;
    }

    @Override
    public boolean resetPassword(Long userId, String password) {
        return userDao.resetPassword(userId, password) > 0;
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Module> listAuthorities() {
        List<Module> modules = authorityDao.getModules();
        List<Module> moduleWithAuthority = authorityDao.getModuleWithAuthority();
        List<Module> tempModules = Lists.newArrayList();

        for (Module module : modules) {
            int index = moduleWithAuthority.indexOf(module);
            if (index >= 0) {
                module.setAuthorities(moduleWithAuthority.get(index).getAuthorities());
            }
        }

        for (Module module : modules) {
            for (Module module2 : module.getModules()) {
                if (modules.contains(module2)) {
                    module.getModules().set(module.getModules().indexOf(module2),
                            modules.get(modules.indexOf(module2)));
                    tempModules.add(module2);
                }
            }
        }
        modules.removeAll(tempModules);
        return modules;
    }
}
