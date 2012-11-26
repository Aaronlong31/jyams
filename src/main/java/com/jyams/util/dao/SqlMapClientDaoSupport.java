package com.jyams.util.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.util.Assert;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * cpoy自spring的SqlMapClientDaoSupport，将其中的getter/setter方法的final去掉，
 * 并设置织入SqlMapClient
 * 
 * @author zhanglong
 * 
 */
public abstract class SqlMapClientDaoSupport extends DaoSupport {

    private SqlMapClientTemplate sqlMapClientTemplate = new SqlMapClientTemplate();

    private boolean externalTemplate = false;

    /**
     * Set the JDBC DataSource to be used by this DAO. Not required: The
     * SqlMapClient might carry a shared DataSource.
     * 
     * @see #setSqlMapClient
     */
    public void setDataSource(DataSource dataSource) {
        if (!this.externalTemplate) {
            this.sqlMapClientTemplate.setDataSource(dataSource);
        }
    }

    /**
     * Return the JDBC DataSource used by this DAO.
     */
    public DataSource getDataSource() {
        return this.sqlMapClientTemplate.getDataSource();
    }

    /**
     * Set the iBATIS Database Layer SqlMapClient to work with. Either this or a
     * "sqlMapClientTemplate" is required.
     * 
     * @see #setSqlMapClientTemplate
     */
    @Autowired
    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        if (!this.externalTemplate) {
            this.sqlMapClientTemplate.setSqlMapClient(sqlMapClient);
        }
    }

    /**
     * Return the iBATIS Database Layer SqlMapClient that this template works
     * with.
     */
    public SqlMapClient getSqlMapClient() {
        return this.sqlMapClientTemplate.getSqlMapClient();
    }

    /**
     * Set the SqlMapClientTemplate for this DAO explicitly, as an alternative
     * to specifying a SqlMapClient.
     * 
     * @see #setSqlMapClient
     */
    public void setSqlMapClientTemplate(
            SqlMapClientTemplate sqlMapClientTemplate) {
        Assert.notNull(sqlMapClientTemplate,
                "SqlMapClientTemplate must not be null");
        this.sqlMapClientTemplate = sqlMapClientTemplate;
        this.externalTemplate = true;
    }

    /**
     * Return the SqlMapClientTemplate for this DAO, pre-initialized with the
     * SqlMapClient or set explicitly.
     */
    public SqlMapClientTemplate getSqlMapClientTemplate() {
        return this.sqlMapClientTemplate;
    }

    @Override
    protected void checkDaoConfig() {
        if (!this.externalTemplate) {
            this.sqlMapClientTemplate.afterPropertiesSet();
        }
    }

}
