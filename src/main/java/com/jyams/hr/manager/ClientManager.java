package com.jyams.hr.manager;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jyams.exception.BusinessException;
import com.jyams.hr.model.Client;
import com.jyams.hr.model.ClientPrincipal;
import com.jyams.util.DataPage;

@Transactional(rollbackFor = Exception.class)
public interface ClientManager {

    /**
     * 添加客户
     * 
     * @param client
     * @return
     * @throws BusinessException
     *             当客户已存在时抛出
     */
    Client addClient(Client client) throws BusinessException;

    /**
     * 增加客户商 1.查询数据库中是否存在该客户商，有则返回该客户商 2.不存在时，新增客户商，返回新的客户商
     * 
     * @param clientName
     * @return
     */
    Client addClient(String clientName);

    boolean modifyClient(Client client);

    boolean deleteClient(long clientId);

    @Transactional(readOnly = true)
    Client getClient(long clientId);

    @Transactional(readOnly = true)
    boolean assertExistsClientName(String clientName);

    /**
     * 增加客户商热度
     * 
     * @param clientId
     */
    boolean addClientPriority(long clientId);

    @Transactional(readOnly = true)
    List<Client> listClients(String clientNameLike);

    @Transactional(readOnly = true)
    List<Client> listAllClients();

    @Transactional(readOnly = true)
    DataPage<Client> listClients(String clientNameLike, Integer pageNo,
            Integer pageSize);

    ClientPrincipal addClientPrincipal(ClientPrincipal clientPrincipal);

    ClientPrincipal addClientPrincipal(long clientId, String clientPrincipalName);

    boolean modifyClientPrincipal(ClientPrincipal clientPrincipal);

    @Transactional(readOnly = true)
    ClientPrincipal getClientPrincipal(long clientPrincipalId);

    @Transactional(readOnly = true)
    List<ClientPrincipal> listPrincipalsOfClient(String clientName);

    /**
     * 增加客户负责人热度
     * 
     * @param clientPrincipalId
     */
    boolean addClienPrincipalPriority(long clientPrincipalId);

    @Transactional(readOnly = true)
    DataPage<ClientPrincipal> listClientPrincipals(Long clientId,
            String clientNameLike, String clientPrincipalNameLike,
            Integer pageNo, Integer pageSize);

    @Transactional(readOnly = true)
    List<ClientPrincipal> listClientPrincipals(String clientPrincipalNameLike);

    @Transactional(readOnly = true)
    ClientPrincipal getClientPrincipal(String clientPrincipalName);
}
