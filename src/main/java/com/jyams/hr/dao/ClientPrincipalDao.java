package com.jyams.hr.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.hr.model.ClientPrincipal;
import com.jyams.util.DataPage;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
@SuppressWarnings("unchecked")
public class ClientPrincipalDao extends IBatisEntityDao<ClientPrincipal> {

    public int addPriority(long clientPrincipalId) {
        return getSqlMapClientTemplate().update(
                "ClientPrincipalDao.addPriority", clientPrincipalId);
    }

    public DataPage<ClientPrincipal> listClientPrincipals(Long clientId,
            String clientNameLike, String clientPrincipalNameLike,
            Integer pageNo, Integer pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("clientId", clientId);
        map.put("clientNameLike", clientNameLike);
        map.put("clientPrincipalNameLike", clientPrincipalNameLike);
        return pagedQuery("ClientPrincipalDao.listClientPrincipals", map,
                pageNo, pageSize);
    }

}
