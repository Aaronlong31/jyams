package com.jyams.security.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.security.model.Authority;
import com.jyams.security.model.User;
import com.jyams.util.DataPage;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
@SuppressWarnings("unchecked")
public class UserDao extends IBatisEntityDao<User> {

    public DataPage<User> listUsers(String usernameLike, Short status, Integer pageNo,
            Integer pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("usernameLike", usernameLike);
        map.put("status", status);
        return pagedQuery("UserDao.listUsers", map, pageNo, pageSize);
    }

    public void insertUserAuthorities(long userId, List<Authority> authorities) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("authorities", authorities);
        getSqlMapClientTemplate().insert("UserDao.insertUserAuthorities", map);

    }

    public void deleteUserAuthority(long userId) {
        getSqlMapClientTemplate().delete("UserDao.deleteUserAuthority", userId);
    }

    public int resetPassword(Long userId, String password) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("password", password);
        return getSqlMapClientTemplate().update("UserDao.resetPassword", map);
    }

    public User getUserByUsernameAndPassword(String username, String encodedPassword) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("username", username);
        map.put("password", encodedPassword);
        return (User) getSqlMapClientTemplate().queryForObject(
                "UserDao.getUserByUsernameAndPassword", map);
    }

    public List<User> getAllWithAuthorities() {
        return getSqlMapClientTemplate().queryForList("UserDao.getAllWithAuthorities");
    }
}
