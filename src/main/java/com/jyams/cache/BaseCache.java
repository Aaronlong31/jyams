package com.jyams.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @author zhanglong
 * 
 *         Nov 11, 2012 4:47:48 PM
 */
public abstract class BaseCache<K, T> {
    private static Logger logger = LoggerFactory.getLogger(BaseCache.class);

    private LoadingCache<K, T> cache;

    public T get(K key) {
        try {
            return cache.get(key);
        } catch (Exception e) {
            logger.error(getClass() + " GET by " + key + "faild.", e);
            return null;
        }
    }

    public void refresh(K key) {
        cache.refresh(key);
    }

    public void setCacheLoader(CacheLoader<K, T> cacheLoader) {
        cache = CacheBuilder.newBuilder().maximumSize(200).build(cacheLoader);
    }
}
