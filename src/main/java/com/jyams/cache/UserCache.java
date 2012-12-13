package com.jyams.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheLoader;
import com.jyams.security.dao.UserDao;
import com.jyams.security.model.User;

/**
 * @author zhanglong
 * 
 *         Nov 11, 2012 4:39:57 PM
 */
@Component
public class UserCache extends BaseCache<Long, User> {

    @Autowired
    public UserCache(final UserDao userDao) {
        CacheLoader<Long, User> cacheLoader = new CacheLoader<Long, User>() {
            @Override
            public User load(Long key) throws Exception {
                return userDao.get(key);
            }
        };
        super.setCacheLoader(cacheLoader);
    }

}
