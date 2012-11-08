package com.jyams.project.manager.impl;

import static com.jyams.util.KeyGenerator.getQuoteId;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jyams.hr.manager.ClientManager;
import com.jyams.hr.model.Client;
import com.jyams.project.dao.QuoteDao;
import com.jyams.project.dao.QuoteItemDao;
import com.jyams.project.manager.QuoteManager;
import com.jyams.project.model.Quote;
import com.jyams.project.model.QuoteItem;
import com.jyams.util.DataPage;
import com.jyams.util.IdUtil;

/**
 * 报价管理
 * @author zhanglong
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class QuoteManagerImpl implements QuoteManager {

	@Autowired
	private QuoteDao quoteDao;
	@Autowired
	private QuoteItemDao quoteItemDao;
	@Autowired
	private ClientManager clientManager;

	/**
	 * 添加报价单
	 * 初始版本号为A，将quote中的quoteItem也保存
	 */
	@Override
	public Quote addQuote(Quote quote) {
		long quoteId = getQuoteId();

		// 设置初始版本为A
		char version = 'A';
		quote.setQuoteId(quoteId);
		quote.setVersion(version);
		quote.setCreatedTimestamp(System.currentTimeMillis());

		// 保存客户
		setClient(quote);

		insertQuote(quote);
		return quote;
	}

	private Quote setClient(Quote quote) {
		if (StringUtils.isNotBlank(quote.getClientName())) {
			Client client = clientManager.addClient(quote.getClientName());
			quote.setClientId(client.getClientId());
		}
		return quote;
	}

	/**
	 * 保存报价和报价项
	 * @param quote
	 */
	private void insertQuote(Quote quote) {
		float totalPrice = 0;
		List<QuoteItem> quoteItems = quote.getQuoteItems();
		for (QuoteItem quoteItem : quoteItems) {
			quoteItem.setItemId(IdUtil.nextLong());
			quoteItem.setQuoteId(quote.getQuoteId());
			quoteItem.setVersion(quote.getVersion());
			totalPrice += quoteItem.getUnitPrice() * quoteItem.getCount();
		}
		// 设置报价总额
		quote.setTotalPrice(totalPrice);
		quote.setCreatedTimestamp(System.currentTimeMillis());
		quoteDao.insert(quote);
		quoteItemDao.batchInsert(quoteItems);
	}

	/**
	 * 修改报价
	 */
	@Override
	public boolean modifyQuote(Quote quote) {
		// 设置报价单版本为该系列报价单的最大版本
		quote.setVersion(quoteDao.getNextVersion(quote.getQuoteId()));
		insertQuote(quote);
		return true;
	}

	@Override
	public Quote getQuote(long quoteId, int version) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("quoteId", quoteId);
		map.put("version", version);
		return quoteDao.findUniqueByMap(map);
	}

	@Override
	public DataPage<Quote> listQuotes(String id, Long quoteId, String clientName,
			String quoterName, String attnName, Long startTimestamp, Long endTimestamp,
			Integer pageNo, Integer pageSize) {
		Integer version = null;
		if (id != null && id.length() == 7) {
			quoteId = Long.parseLong(id.substring(0, 6));
			version = (int) id.charAt(6);
		}
		return quoteDao.listQuotes(quoteId, version, clientName, quoterName, attnName,
				startTimestamp, endTimestamp, pageNo, pageSize);
	}

	@Override
	public List<String> listIds() {
		List<Quote> quotes = quoteDao.listIds();
		List<String> ids = Lists.newArrayList();
		for (Quote quote : quotes) {
			ids.add(quote.getId());
		}
		return ids;
	}
}
