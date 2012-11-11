package com.jyams.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.jyams.hr.manager.PersonManager;
import com.jyams.hr.model.Person;

/**
 * @author zhanglong
 * 
 *         Nov 11, 2012 4:28:21 PM
 */
public class PersonCache {

    private static Logger logger = LoggerFactory.getLogger(PersonCache.class);

    private static PersonManager personManager;

    private static LoadingCache<Long, Person> cache = CacheBuilder.newBuilder()
            .maximumSize(200).build(new CacheLoader<Long, Person>() {
                @Override
                public Person load(Long key) throws Exception {
                    return personManager.getPerson(key);
                }

            });

    public static Person get(long personId) {
        try {
            return PersonCache.cache.get(personId);
        } catch (Exception e) {
            logger.error("GET person BY personId:" + personId, e);
            return null;
        }
    }

    public static void refresh(long personId) {
        PersonCache.cache.refresh(personId);
    }

    @Autowired
    public void setPersonManager(PersonManager personManager) {
        PersonCache.personManager = personManager;
    }

}
