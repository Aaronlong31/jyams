package com.jyams.dispatch;

import java.util.concurrent.ExecutionException;

import com.jyams.security.SecurityUtils;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.jyams.dispatch.model.Dispatch;

/**
 * @author zhangString
 * 
 *         2012-11-26 下午10:03:23
 */
@Component
public class DispatchCache {
    private final LoadingCache<String, Dispatch> cache = CacheBuilder.newBuilder().maximumSize(500)
            .build(new CacheLoader<String, Dispatch>() {
                @Override
                public Dispatch load(String key) throws Exception {
                    Dispatch dispatch = new Dispatch();
                    dispatch.setPrincipalId(SecurityUtils.getCurrentUserId());
                    dispatch.setPrincipalName(SecurityUtils.getCurrentUsername());
                    dispatch.setProjectType(Dispatch.PROJECT_TYPE_BUILDING);
                    return dispatch;
                }
            });

    public Dispatch get(String key) {
        try {
            return cache.get(key);
        } catch (ExecutionException e) {
            return null;
        }
    }

    public void put(String key, Dispatch dispatch) {
        cache.put(key, dispatch);
    }

    public void refresh(String key) {
        cache.refresh(key);
    }

    public void delete(String key) {
        cache.invalidate(key);
    }

}
