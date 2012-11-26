package com.jyams.purchase.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.purchase.model.PurchaseItem;
import com.jyams.secure.manager.impl.UserInfo;
import com.jyams.util.SpringSecurityUtils;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
public class PurchaseItemDaoIbatis extends IBatisEntityDao<PurchaseItem> {

    /**
     * 修改采购单中所有项的状态
     * 
     * @param purchaseId
     * @param status
     * @return
     */
    public int batchModifyStatus(long purchaseId, short status) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("purchaseId", purchaseId);
        map.put("status", status);
        return getSqlMapClientTemplate().update(
                "PurchaseItemDaoIbatis.modifyStatus", map);
    }

    /**
     * 修改采购单项的状态
     * 
     * @param purchaseItemId
     * @param status
     * @return
     */
    public int modifyStatus(long purchaseItemId, short status) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("purchaseItemId", purchaseItemId);
        map.put("status", status);
        return getSqlMapClientTemplate().update(
                "PurchaseItemDaoIbatis.modifySingleStatus", map);
    }

    /**
     * 批量修改采购单项状态
     * 
     * @param purchaseItemIds
     * @param status
     */
    public void batchModifyStatus(List<Long> purchaseItemIds, short status) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("purchaseItemIds", purchaseItemIds);
        map.put("status", status);
        getSqlMapClientTemplate().update(
                "PurchaseItemDaoIbatis.batchModifyStatus", map);
    }

    /**
     * 复核采购单项
     * 
     * @param purchaseItem
     */
    public int review(PurchaseItem purchaseItem) {
        return getSqlMapClientTemplate().update("PurchaseItemDaoIbatis.review",
                purchaseItem);
    }

    /**
     * 确认到货
     * 
     * @param purchaseItemId
     * @param status
     */
    public void arrive(long purchaseItemId, short status) {
        UserInfo user = SpringSecurityUtils.getCurrentUser();
        long userId = user.getUserId();
        String userName = user.getUsername();
        Map<String, Object> map = Maps.newHashMap();
        map.put("purchaseItemId", purchaseItemId);
        map.put("purchaseTimestamp", System.currentTimeMillis());
        map.put("userId", userId);
        map.put("userName", userName);
        getSqlMapClientTemplate().update("PurchaseItemDaoIbatis.arrive", map);
    }
}
