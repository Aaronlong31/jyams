package com.jyams.util.search;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.jyams.util.DataPage;
import com.jyams.util.SQLUtils;

/**
 * 查询对象抽象父类
 * 
 * @author zhanglong
 * 
 * @param <T>
 */
public abstract class Query<T> {

    private static Logger logger = LoggerFactory.getLogger("query");

    protected static final String SEARCH_FIELD = "searchField";
    protected static final String SEARCH_STRING = "searchString";
    private Integer pageNo = 1; // 页码
    private Integer pageSize = 20; // 每页记录数,默认值为20
    private List<SqlOrder> orders = Lists.newArrayList();
    private List<Short> includeStatus = Lists.newArrayList();// 包含的采购单状态
    private List<Short> excludeStatus = Lists.newArrayList();// 排除的采购单状态

    public Query() {
    }

    public Query(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Query(SearchFilter filter) {
        initPager(filter.getRequest());
        initSearcher(filter);
    }

    public Integer getStartIndex() {
        return DataPage.getStartOfPage(pageNo, pageSize);
    }

    private Query<T> initSearcher(SearchFilter filter) {
        List<SearchRule> rules = filter.getRules();
        Method[] methods = this.getClass().getMethods();
        for (SearchRule rule : rules) {
            String field = rule.getField();
            String data = rule.getData();
            for (Method method : methods) {
                if (method.getName().equals(
                        "set" + StringUtils.capitalize(field))) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    try {
                        method.invoke(this, parseT(data, parameterTypes[0]));
                    } catch (Exception e) {
                        logger.error("{} do not have the field : [{}]", this
                                .getClass().getName(), field);
                    }
                    break;
                }
            }
        }
        return this;
    }

    public Object parseT(String value, Class<?> t) {
        if (t.isAssignableFrom(Integer.class)) {
            return Integer.parseInt(value);
        }
        if (t.isAssignableFrom(String.class)) {
            try {
                return SQLUtils.escapeBadSqlPatternChars(new String(value
                        .getBytes("ISO-8859-1"), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                return value;
            }
        }
        if (t.isAssignableFrom(Long.class)) {
            return Long.parseLong(value);
        }
        if (t.isAssignableFrom(Short.class)) {
            return Short.parseShort(value);
        }
        if (t.isAssignableFrom(Float.class)) {
            return Float.parseFloat(value);
        }

        return null;

    }

    private Query<T> initPager(ServletRequest request) {
        String rowsPara = request.getParameter("rows");
        if (StringUtils.isNotBlank(rowsPara)) {
            this.setPageSize(Integer.parseInt(rowsPara));
        }

        String pagePara = request.getParameter("page");
        if (StringUtils.isNotBlank(pagePara)) {
            this.setPageNo(Integer.parseInt(pagePara));
        }

        String sidx = request.getParameter("sidx");
        if (StringUtils.isNotBlank(sidx)) {
            String sord = request.getParameter("sord");
            boolean isAsc = true;
            if (sord != null) {
                isAsc = sord.toString().equalsIgnoreCase("asc");
            }
            this.setOrder(new SqlOrder(sidx, isAsc));
        }
        return this;
    }

    /**
     * 添加排序字段
     * 
     * @param order
     * @return
     */
    public Query<T> addOrder(SqlOrder order) {
        this.orders.add(order);
        return this;
    }

    /**
     * 设置排序字段，和addOrder不同的是，本方法会清空已有的排序字段
     * 
     * @param order
     * @return
     */
    public Query<T> setOrder(SqlOrder order) {
        this.orders.clear();
        this.orders.add(order);
        return this;
    }

    /**
     * 添加状态查询条件
     * 
     * @param status
     * @return
     */
    public Query<T> addStatus(Short status) {
        this.includeStatus.add(status);
        this.excludeStatus.clear();
        return this;
    }

    /**
     * 设置状态查询条件，会清空之前的状态条件列表
     * 
     * @param status
     * @return
     */
    public Query<T> setStatus(Short status) {
        this.includeStatus = Lists.newArrayList(status);
        this.excludeStatus.clear();
        return this;
    }

    /**
     * 通过状态字符串方式设置状态查询条件
     * 
     * @param statuses
     *            格式为以“,”分隔的数字
     * @return
     */
    public Query<T> setStatuses(String statuses) {

        // 判断参数格式是否正确
        statuses = statuses.replaceAll("\\s", "");
        if (!statuses.matches("^(\\d)+(,\\d+)*,?$")) {
            return this;
        }

        String[] statusArray = statuses.split(",");
        for (String status : statusArray) {
            this.addStatus(Short.parseShort(status));
        }
        return this;
    }

    public Query<T> setStatuses(short... statuses) {
        this.includeStatus.clear();
        this.excludeStatus.clear();
        for (short s : statuses) {
            this.includeStatus.add(s);
        }
        return this;
    }

    /**
     * 返回以“,”分隔的状态字符串
     * 
     * @return
     */
    public String getStatuses() {
        return this.includeStatus.toString().replaceAll("\\[|\\]|\\s", "");
    }

    /**
     * 获取排序的SQL语句
     * 
     * @return
     */
    public String getOrderString() {

        if (this.orders == null || this.orders.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder(" ORDER BY ");
        for (SqlOrder order : this.orders) {
            sb.append(order);
            sb.append(",");
        }

        return sb.toString().substring(0, sb.length() - 1);
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Query<T> setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Query<T> setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public List<SqlOrder> getOrders() {
        return orders;
    }

    public Query<T> setOrders(List<SqlOrder> orders) {
        this.orders = orders;
        return this;
    }

    public List<Short> getIncludeStatus() {
        return includeStatus;
    }

    public Query<T> setIncludeStatus(List<Short> includeStatus) {
        this.includeStatus = includeStatus;
        return this;
    }

    public List<Short> getExcludeStatus() {
        return excludeStatus;
    }

    public Query<T> setExcludeStatus(List<Short> excludeStatus) {
        this.excludeStatus = excludeStatus;
        return this;
    }

}
