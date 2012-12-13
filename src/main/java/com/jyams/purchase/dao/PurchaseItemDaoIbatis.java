package com.jyams.purchase.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.purchase.model.PurchaseItem;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
public class PurchaseItemDaoIbatis extends IBatisEntityDao<PurchaseItem> {

    /**
     * 修改采购单中所有项的状态
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
     */
    public int review(PurchaseItem purchaseItem) {
        return getSqlMapClientTemplate().update("PurchaseItemDaoIbatis.review",
                purchaseItem);
    }

    /**
     * 确认到货
     */
    public void arrive(long purchaseItemId, short status, long userId, String username) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("purchaseItemId", purchaseItemId);
        map.put("purchaseTimestamp", System.currentTimeMillis());
        map.put("userId", userId);
        map.put("userName", username);
        getSqlMapClientTemplate().update("PurchaseItemDaoIbatis.arrive", map);
    }
}
