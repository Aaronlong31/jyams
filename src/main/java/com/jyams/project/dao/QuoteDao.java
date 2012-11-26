package com.jyams.project.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.project.model.Quote;
import com.jyams.util.DataPage;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
@SuppressWarnings("unchecked")
public class QuoteDao extends IBatisEntityDao<Quote> {

    public DataPage<Quote> listQuotes(Long quoteId, Integer version,
            String clientName, String quoterName, String attnName,
            Long startTimestamp, Long endTimestamp, Integer pageNo,
            Integer pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("quoteId", quoteId);
        map.put("version", version);
        map.put("clientName", clientName);
        map.put("quoterName", quoterName);
        map.put("attnName", attnName);
        map.put("startTimestamp", startTimestamp);
        map.put("endTimestamp", endTimestamp);
        return pagedQuery("QuoteDao.listQuotes", map, pageNo, pageSize);
    }

    public int getNextVersion(long quoteId) {
        Integer version = (Integer) getSqlMapClientTemplate().queryForObject(
                "QuoteDao.getMaxVersion", quoteId);
        if (version == null) {
            return 'A';
        }
        return version + 1;
    }

    public List<Quote> listIds() {
        return getSqlMapClientTemplate().queryForList("QuoteDao.listIds");
    }
}
