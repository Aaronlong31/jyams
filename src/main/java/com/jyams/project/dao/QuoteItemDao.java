package com.jyams.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jyams.project.model.QuoteItem;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
public class QuoteItemDao extends IBatisEntityDao<QuoteItem> {

	public void batchInsert(List<QuoteItem> quoteItems) {
		try {
			getSqlMapClient().startBatch();
			for (QuoteItem quoteItem : quoteItems) {
				getSqlMapClient().insert("com.jyams.project.model.QuoteItem.insert", quoteItem);
			}
			getSqlMapClient().executeBatch();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
