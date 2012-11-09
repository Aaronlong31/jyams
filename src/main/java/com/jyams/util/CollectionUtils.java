package com.jyams.util;

import java.util.Collection;

import org.apache.commons.lang.ArrayUtils;

import com.google.common.collect.Lists;

/**
 * 集合工具类
 * 
 * @author zhanglong
 * 
 */
public final class CollectionUtils {

    private CollectionUtils() {
    }

    /**
     * 判断一个集合是否只包含给定的元素
     * 
     * @param <E>
     * @param collection
     * @param elements
     * @return
     */
    public static <E> boolean containsOnly(Collection<E> collection,
            E... elements) {
        for (E e : collection) {
            if (!ArrayUtils.contains(elements, e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 从一个集合中移除非指定的元素<br/>
     * 如collection为[2,3,4,5,6,7]，elements为[1,2,3,4] 则collection的处理结果为[2,3,4]
     * 
     * @param <E>
     * @param collection
     * @param elements
     */
    public static <E> void removeNot(Collection<E> collection, E... elements) {
        Collection<E> needRemove = Lists.newArrayList();

        for (E e : collection) {
            if (!ArrayUtils.contains(elements, e)) {
                needRemove.add(e);
            }
        }
        collection.removeAll(needRemove);
    }
}
