package com.jyams.util.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.google.common.collect.Maps;
import com.jyams.util.DataPage;
import com.jyams.util.search.Query;

/**
 * IBatis Dao的泛型基类.
 * <p/>
 * 继承于Spring的SqlMapClientDaoSupport,提供分页函数和若干便捷查询方法，并对返回值作了泛型类型转换.
 * 
 * @author suwei
 * @see SqlMapClientDaoSupport
 */
@SuppressWarnings("unchecked")
public class IBatisGenericDao extends SqlMapClientDaoSupport {

    private static Logger logger = LoggerFactory
            .getLogger(IBatisGenericDao.class);

    public static final String POSTFIX_INSERT = ".insert";

    public static final String POSTFIX_UPDATE = ".update";

    public static final String POSTFIX_DELETE = ".delete";

    public static final String POSTFIX_DELETE_PRIAMARYKEY = ".deleteByPrimaryKey";

    public static final String POSTFIX_SELECT = ".select";

    public static final String POSTFIX_SELECTMAP = ".selectByMap";

    public static final String POSTFIX_SELECTSQL = ".selectBySql";

    public static final String POSTFIX_COUNT = ".Count";

    public static final String POSTFIX_PAGEQUERY = ".pageQuery";

    /**
     * 根据ID获取对象
     * 
     * @throws BusinessException
     * @throws
     */
    public <T> T get(Class<T> entityClass, Serializable id) {
        T o = (T) getSqlMapClientTemplate().queryForObject(
                entityClass.getSimpleName() + POSTFIX_SELECT, id);
        return o;
    }

    /**
     * 获取全部对象
     * 
     * @throws
     */
    public <T> List<T> getAll(Class<T> entityClass) {
        return getSqlMapClientTemplate().queryForList(
                entityClass.getSimpleName() + POSTFIX_SELECT, null);
    }

    /**
     * 新增对象
     * 
     * @throws
     */
    public void insert(Object o) {
        getSqlMapClientTemplate().insert(
                o.getClass().getSimpleName() + POSTFIX_INSERT, o);
    }

    /**
     * 保存对象
     * 
     * @throws
     */
    public int update(Object o) {
        return getSqlMapClientTemplate().update(
                o.getClass().getSimpleName() + POSTFIX_UPDATE, o);
    }

    /**
     * 删除对象
     * 
     * @throws
     */
    public int remove(Object o) {
        return getSqlMapClientTemplate().delete(
                o.getClass().getSimpleName() + POSTFIX_DELETE, o);
    }

    /**
     * 根据ID删除对象
     * 
     * @throws
     */
    public <T> int removeById(Class<T> entityClass, Serializable id) {
        return getSqlMapClientTemplate().delete(
                entityClass.getSimpleName() + POSTFIX_DELETE_PRIAMARYKEY, id);
    }

    /**
     * map查询.
     * 
     * @param map
     *            包含各种属性的查询
     * @throws
     */
    public <T> List<T> find(Class<T> entityClass, Map<String, Object> map) {
        if (map == null) {
            map = Maps.newHashMap();
        }
        return this.getSqlMapClientTemplate().queryForList(
                entityClass.getSimpleName() + POSTFIX_SELECTMAP, map);
    }

    /**
     * sql 查询.
     * 
     * @param sql
     *            直接sql的语句(需要防止注入式攻击)
     * @throws
     */
    public <T> List<T> find(Class<T> entityClass, String sql) {
        Assert.hasText(sql);
        if (StringUtils.isEmpty(sql)) {
            return this.getSqlMapClientTemplate().queryForList(
                    entityClass.getSimpleName() + POSTFIX_SELECT, null);
        } else {
            return this.getSqlMapClientTemplate().queryForList(
                    entityClass.getSimpleName() + POSTFIX_SELECTSQL, sql);
        }
    }

    /**
     * 根据属性名和属性值查询对象.
     * 
     * @return 符合条件的对象列表
     * @throws
     */
    public <T> List<T> findBy(Class<T> entityClass, String name, Object value) {
        Assert.hasText(name);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(name, value);
        return find(entityClass, map);
    }

    /**
     * 根据属性名和属性值查询对象.
     * 
     * @return 符合条件的唯一对象
     */
    public <T> T findUniqueBy(Class<T> entityClass, String name, Object value) {
        Assert.hasText(name);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            PropertyUtils.getProperty(entityClass.newInstance(), name);
            map.put(name, value);
            map.put("findUniqueBy", "True");
            return (T) getSqlMapClientTemplate().queryForObject(
                    entityClass.getName() + POSTFIX_SELECTMAP, map);
        } catch (Exception e) {
            logger.error("Error when propertie on entity," + e.getMessage(),
                    e.getCause());
            return null;
        }

    }

    /**
     * 根据属性名和属性值以Like AnyWhere方式查询对象.
     * 
     * @throws
     */
    public <T> List<T> findByLike(Class<T> entityClass, String name,
            String value) {
        Assert.hasText(name);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(name, value);
        map.put("findLikeBy", "True");
        return getSqlMapClientTemplate().queryForList(
                entityClass.getName() + POSTFIX_SELECTMAP, map);

    }

    /**
     * 分页查询函数，使用PaginatedList.
     * 
     * @param pageNo
     *            页号,从0开始.
     * @throws
     */
    @SuppressWarnings("rawtypes")
    public DataPage pagedQuery(String sqlName, Map<String, Object> map,
            Integer pageNo, Integer pageSize) {

        if (pageNo == null || pageSize == null) {
            List list = getSqlMapClientTemplate().queryForList(sqlName, map);
            if (list == null || list.size() == 0) {
                return new DataPage();
            } else {
                return new DataPage(0, list.size(), list.size(), list);
            }
        } else {
            if (map == null) {
                map = Maps.newHashMap();
            }
            Assert.hasText(sqlName);
            Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
            // Count查询
            Integer totalCount = (Integer) getSqlMapClientTemplate()
                    .queryForObject(sqlName + ".Count", map);

            if (totalCount < 1) {
                return new DataPage();
            }

            // 实际查询返回分页对象
            int startIndex = DataPage.getStartOfPage(pageNo, pageSize);
            map.put("startIndex", startIndex);
            map.put("pageSize", pageSize);
            List list = getSqlMapClientTemplate().queryForList(sqlName, map);

            return new DataPage(startIndex, totalCount, pageSize, list);
        }
    }

    /**
     * 根据Query对象查询分页对象，默认使用query的全限定名
     * 
     * @param <T>
     * @param query
     * @return
     */
    public <T> DataPage<T> pageQuery(Query<T> query) {
        return pageQuery(query.getClass().getSimpleName(), query);
    }

    public <T> DataPage<T> pageQuery(String statementId, Query<T> query) {
        if (query == null) {
            return new DataPage<T>();
        }

        // 获取Query的泛型参数类型名，构造sql statement id
        String sqlName = statementId + POSTFIX_PAGEQUERY;

        // 查询总的记录数
        Integer totalCount = (Integer) getSqlMapClientTemplate()
                .queryForObject(sqlName + POSTFIX_COUNT, query);

        // 如果总记录数为0，则返回空的分页对象
        if (totalCount < 1) {
            return new DataPage<T>();
        }

        // 查询分页对象数据
        List<T> data = getSqlMapClientTemplate().queryForList(sqlName, query);

        return new DataPage<T>(query.getStartIndex(), totalCount,
                query.getPageSize(), data);
    }

}
