package com.jyams.hr.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.hr.model.Client;
import com.jyams.util.DataPage;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
@SuppressWarnings("unchecked")
public class ClientDao extends IBatisEntityDao<Client> {

	public int addClientPriority(long clientId) {
		return getSqlMapClientTemplate().update("com.jyams.hr.dao.ClientDao.addClientPriority",
				clientId);
	}

	public List<Client> getClients() {
		return getSqlMapClientTemplate().queryForList("com.jyams.hr.dao.ClientDao.getClients");
	}

	public DataPage<Client> listClients(String clientNameLike, Integer pageNo, Integer pageSize) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("clientNameLike", clientNameLike);
		return pagedQuery("com.jyams.hr.dao.ClientDao.listClients", map, pageNo, pageSize);
	}
}
