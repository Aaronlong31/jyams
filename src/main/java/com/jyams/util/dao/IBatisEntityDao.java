package com.jyams.util.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import com.jyams.exception.BusinessException;
import com.jyams.util.GenericsUtils;

/**
 * 负责为单个Entity 提供CRUD操作的IBatis DAO基类.
 * <p/>
 * 子类只要在类定义时指定所管理Entity的Class, 即拥有对单个Entity对象的CRUD操作.
 * 
 * <pre>
 * public class UserManagerIbatis extends IBatisEntityDao&lt;User&gt; {
 * }
 * </pre>
 * 
 * @author zhanglong
 * @see IBatisGenericDao
 */
public class IBatisEntityDao<T> extends IBatisGenericDao {

    /**
     * DAO所管理的Entity类型.
     */
    private final Class<T> entityClass;

    private String primaryKeyName;

    /**
     * 在构造函数中将泛型T.class赋给entityClass.
     */
    @SuppressWarnings("unchecked")
    public IBatisEntityDao() {
        entityClass = (Class<T>) GenericsUtils
                .getSuperClassGenricType(getClass());
    }

    /**
     * 根据属性名和属性值查询对象.
     * 
     * @return 符合条件的对象列表
     * @throws SQLException
     */
    public List<T> findBy(String name, Object value) {
        return findBy(getEntityClass(), name, value);
    }

    /**
     * 根据属性的名值对查询对象
     * 
     * @param map
     * @return
     * @throws SQLException
     */
    public List<T> find(Map<String, Object> map) {
        return find(getEntityClass(), map);
    }

    /**
     * 根据属性的名值对查询唯一对象
     *
     * @param map
     * @return   T t
     * @throws SQLException
     */
    public T findUniqueByMap(Map<String, Object> map) {
        List<T> list = find(getEntityClass(), map);
        if (list == null || list.size() <= 0) {
            return null;
        } else if (list.size() > 1) {
            throw new RuntimeException("不只一个对象");
        }
        return list.get(0);
    }

    /**
     * 根据属性名和属性值以Like AnyWhere方式查询对象.
     * 
     * @throws SQLException
     */
    public List<T> findByLike(String name, String value) {
        return findByLike(getEntityClass(), name, value);
    }

    /**
     * 根据属性名和属性值查询单个对象.
     * 
     * @return 符合条件的唯一对象
     */
    public T findUniqueBy(String name, Object value) {
        return findUniqueBy(getEntityClass(), name, value);
    }

    /**
     * 根据ID获取对象.
     * 
     * @throws BusinessException
     * @throws SQLException
     */
    public T get(Serializable id) {
        return get(getEntityClass(), id);
    }

    /**
     * 获取全部对象.
     * 
     * @throws SQLException
     */
    public List<T> getAll() {
        return getAll(getEntityClass());
    }

    /**
     * 取得entityClass.
     * <p/>
     * JDK1.4不支持泛型的子类可以抛开Class<T> entityClass,重载此函数达到相同效果。
     */
    protected Class<T> getEntityClass() {
        return entityClass;
    }

    public String getPrimaryKeyName() {
        if (StringUtils.isEmpty(primaryKeyName)) {
            primaryKeyName = "id";
        }
        return primaryKeyName;
    }

    protected Object getPrimaryKeyValue(Object o) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException,
            InstantiationException {
        return PropertyUtils.getProperty(entityClass.newInstance(),
                getPrimaryKeyName());
    }

    /**
     * 根据ID移除对象.
     * 
     * @throws SQLException
     */
    public int removeById(Serializable id) {
        return removeById(getEntityClass(), id);
    }

    /**
     * 保存对象. 为了实现IEntityDao 我在内部使用了insert和upate 2个方法.
     * 
     * @throws SQLException
     */
    public void saveOrUpdate(Object o) {
        Object primaryKey;
        try {
            primaryKey = getPrimaryKeyValue(o);
        } catch (Exception e) {
            // TODO ObjectRetrievalFailureException(entityClass, e);
            return;
        }

        if (primaryKey == null) {
            insert(o);
        } else {
            update(o);
        }
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }

    public String getIdName(Class<?> clazz) {
        return "id";
    }

}
