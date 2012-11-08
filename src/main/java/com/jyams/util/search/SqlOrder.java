package com.jyams.util.search;

/**
 * 
 * @author zhanglong
 * 
 */
public class SqlOrder {
    private String orderBy;
    private boolean isAsc = true;

    public SqlOrder() {
    }

    public SqlOrder(String orderBy, boolean isAsc) {
        super();
        this.orderBy = orderBy;
        this.isAsc = isAsc;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isAsc() {
        return isAsc;
    }

    public void setAsc(boolean isAsc) {
        this.isAsc = isAsc;
    }

    @Override
    public String toString() {
        return orderBy + " " + (isAsc ? " ASC " : " DESC ");
    }
}
