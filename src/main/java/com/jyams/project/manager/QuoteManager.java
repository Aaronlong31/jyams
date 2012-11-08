package com.jyams.project.manager;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jyams.project.model.Quote;
import com.jyams.util.DataPage;

/**
 * 
 * @author zhanglong
 * 
 */
@Transactional(rollbackFor = Exception.class)
public interface QuoteManager {

    Quote addQuote(Quote quote);

    boolean modifyQuote(Quote quote);

    @Transactional(readOnly = true)
    Quote getQuote(long quoteId, int version);

    @Transactional(readOnly = true)
    DataPage<Quote> listQuotes(String id, Long quoteId, String clientName,
            String quoterName, String attnName, Long startTimestamp,
            Long endTimestamp, Integer pageNo, Integer pageSize);

    List<String> listIds();
}
