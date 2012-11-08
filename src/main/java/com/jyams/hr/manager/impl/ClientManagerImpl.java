package com.jyams.hr.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jyams.exception.BusinessException;
import com.jyams.hr.dao.ClientDao;
import com.jyams.hr.dao.ClientPrincipalDao;
import com.jyams.hr.manager.ClientManager;
import com.jyams.hr.model.Client;
import com.jyams.hr.model.ClientPrincipal;
import com.jyams.util.DataPage;
import com.jyams.util.IdUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClientManagerImpl implements ClientManager {

	@Autowired
	private ClientDao clientDao;
	@Autowired
	private ClientPrincipalDao clientPrincipalDao;

	@Override
	public boolean addClienPrincipalPriority(long clientPrincipalId) {
		return clientPrincipalDao.addPriority(clientPrincipalId) > 0;
	}

	@Override
	public Client addClient(String clientName) {
		Client client = null;
		client = clientDao.findUniqueBy("clientName", clientName);
		if (client != null) {
			return client;
		}
		client = new Client();
		long clientId = IdUtil.nextLong();
		client.setClientId(clientId);
		client.setClientName(clientName);
		client.setPriority(1);
		clientDao.insert(client);
		return client;
	}

	@Override
	public ClientPrincipal addClientPrincipal(long clientId, String clientPrincipalName) {
		ClientPrincipal cp = null;
		Map<String, Object> map = Maps.newHashMap();
		map.put("clientId", clientId);
		map.put("clientPrincipalName", clientPrincipalName);
		cp = clientPrincipalDao.findUniqueByMap(map);

		if (cp != null) {
			return cp;
		}

		cp = new ClientPrincipal();
		long clientPrincipalId = IdUtil.nextLong();
		cp.setClientPrincipalId(clientPrincipalId);
		cp.setClientId(clientId);
		cp.setName(clientPrincipalName);
		cp.setPriority(1); // 初始热度为1
		clientPrincipalDao.insert(cp);

		return cp;
	}

	@Override
	public Client addClient(Client client) throws BusinessException {
		if (assertExistsClientName(client.getClientName())) {
			throw new BusinessException("该客户已存在");
		}
		long clientId = IdUtil.nextLong();
		client.setClientId(clientId);
		client.setPriority(1);
		clientDao.insert(client);
		return client;
	}

	@Override
	public boolean deleteClient(long clientId) {
		return clientDao.removeById(clientId) > 0;
	}
	
	@Override
	public boolean assertExistsClientName(String clientName) {
		Client client = clientDao.findUniqueBy("clientName", clientName);
		return client != null;
	}

	@Override
	public ClientPrincipal addClientPrincipal(ClientPrincipal clientPrincipal) {
		long clientPrincipalId = IdUtil.nextLong();
		clientPrincipal.setClientPrincipalId(clientPrincipalId);
		clientPrincipalDao.insert(clientPrincipal);
		return clientPrincipal;
	}

	@Override
	public boolean addClientPriority(long clientId) {
		return clientDao.addClientPriority(clientId) > 0;
	}

	@Override
	public Client getClient(long clientId) {
		Client client = clientDao.get(clientId);
		List<ClientPrincipal> clientPrincipals = clientPrincipalDao.findBy("clientId", client
				.getClientId());
		client.setPrincipals(clientPrincipals);
		return client;
	}

	public List<ClientPrincipal> listPrincipalsOfClient(String clientName) {
		Client client = clientDao.findUniqueBy("clientName", clientName);
		if (client != null)
			return clientPrincipalDao.findBy("clientId", client.getClientId());
		return Lists.newArrayList();
	}

	@Override
	public List<Client> listAllClients() {
		List<Client> clients = clientDao.getClients();
		return clients;
	}

	@Override
	public DataPage<Client> listClients(String clientNameLike, Integer pageNo, Integer pageSize) {
		return clientDao.listClients(clientNameLike, pageNo, pageSize);
	}

	@Override
	public List<ClientPrincipal> listClientPrincipals(String clientPrincipalNameLike) {
		return clientPrincipalDao.findBy("nameLike", clientPrincipalNameLike);
	}

	@Override
	public List<Client> listClients(String clientNameLike) {
		return clientDao.findBy("clientNameLike", clientNameLike);
	}

	@Override
	public boolean modifyClient(Client client) {
		return clientDao.update(client) > 0;
	}

	@Override
	public boolean modifyClientPrincipal(ClientPrincipal clientPrincipal) {
		return clientPrincipalDao.update(clientPrincipal) > 0;
	}

	@Override
	public ClientPrincipal getClientPrincipal(long clientPrincipalId) {
		return clientPrincipalDao.get(clientPrincipalId);
	}

	@Override
	public ClientPrincipal getClientPrincipal(String clientPrincipalName) {
		return clientPrincipalDao.findUniqueBy("name", clientPrincipalName);
	}

	@Override
	public DataPage<ClientPrincipal> listClientPrincipals(Long clientId, String clientNameLike,
			String clientPrincipalNameLike, Integer pageNo, Integer pageSize) {
		return clientPrincipalDao.listClientPrincipals(clientId, clientNameLike,
				clientPrincipalNameLike, pageNo, pageSize);
	}
}
