package com.jyams.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheLoader;
import com.jyams.hr.manager.PersonManager;
import com.jyams.hr.model.Person;

/**
 * @author zhanglong
 * 
 *         Nov 11, 2012 4:28:21 PM
 */
@Component
public class PersonCache extends BaseCache<Long, Person> {

    @Autowired
    public PersonCache(final PersonManager personManager) {
        CacheLoader<Long, Person> cacheLoader = new CacheLoader<Long, Person>() {
            @Override
            public Person load(Long key) throws Exception {
                return personManager.getPerson(key);
            }
        };
        super.setCacheLoader(cacheLoader);
    }

}
