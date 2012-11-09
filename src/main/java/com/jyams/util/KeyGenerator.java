package com.jyams.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * 使用 IdUtil 替代
 * 
 * @author zhanglong
 * 
 */
@Component
public class KeyGenerator {

    private static DataSource dataSource;
    private static Logger logger = LoggerFactory.getLogger(KeyGenerator.class);

    private static long projectId = 0;
    private static long quoteId = 0;
    private static long purchaseNo = 0;
    private static long orderId = 0;

    private static void init() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        long min = year % 100 * 10000;
        long max = year % 100 * 10000 + 9999;
        projectId = getLong("Project", "projectId", min, max);
        quoteId = getLong("Quote", "quoteId", min, max);
        purchaseNo = getLong("Purchase", "purchaseNo",
                (long) (year % 100 * 1000), (long) (year % 100 * 1000 + 999));
        orderId = getLong("Purchase", "purchaseNo",
                Long.parseLong(((int) 'W' + "0000")),
                Long.parseLong(((int) 'W' + "9999")));
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        KeyGenerator.dataSource = dataSource;
        init();
    }

    public static synchronized long getProjectId() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        long min = year % 100 * 10000;
        long max = year % 100 * 10000 + 9999;
        if (projectId >= min && projectId <= max) {
            return ++projectId;
        }
        projectId = getLong("Project", "projectId", min, max);
        return ++projectId;
    }

    public static synchronized void setProjectId(long projectId) {
        KeyGenerator.projectId = projectId;
    }

    public static synchronized long getQuoteId() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        long min = year % 100 * 10000;
        long max = year % 100 * 10000 + 9999;
        if (quoteId >= min && quoteId <= max) {
            return ++quoteId;
        }
        return ++quoteId;
    }

    public static synchronized long getPurchaseNo() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        long min = year % 100 * 1000;
        long max = year % 100 * 1000 + 999;
        if (purchaseNo >= min && purchaseNo <= max) {
            return ++purchaseNo;
        }
        purchaseNo = getLong("Purchase", "purchaseNo", min, max);
        return ++purchaseNo;
    }

    public static synchronized long getOrderId() {
        return ++orderId;
    }

    private static synchronized long getLong(String tableName,
            String primaryKeyName, Long min, Long max) {
        Connection connection = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder(200);
        sql.append("SELECT MAX(");
        sql.append(primaryKeyName);
        sql.append(") FROM ");
        sql.append(tableName);
        if (min != null && max != null) {
            sql.append(" WHERE " + primaryKeyName + " >= " + min + " AND "
                    + primaryKeyName + " <= " + max);
        }
        long key = 0;
        try {
            pstmt = connection.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                key = rs.getLong(1);
            }
            if (min != null && key <= min) {
                return min;
            }
        } catch (SQLException e) {
            logger.error("执行SQL语句：[{}] 异常，异常原因:{}",
                    new Object[] { sql, e.getMessage() });
        } finally {
            try {
                connection.close();
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                logger.error("", e);
            }
        }
        return key;
    }

    private static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("获取Connection失败：失败原因:{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
