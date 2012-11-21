package com.jyams.purchase.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jyams.purchase.model.Purchase;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
public class PurchaseDaoIbatis extends IBatisEntityDao<Purchase> {

    /**
     * 修改状态
     * 
     * @param purchaseId
     * @param status
     * @return
     */
    public int modifyStatus(long purchaseId, short status) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("purchaseId", purchaseId);
        map.put("status", status);
        return getSqlMapClientTemplate().update(
                "PurchaseDaoIbatis.modifyStatus", map);
    }

    /**
     * 审批采购单
     * 
     * @param purchaseId
     * @param status
     * @param approverId
     * @param approverName
     * @param approvalOpinion
     * @param approvedTimestamp
     * @return
     */
    public int approvePurchase(long purchaseId, short status, long approverId,
            String approverName, String approvalOpinion, long approvedTimestamp) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("purchaseId", purchaseId);
        map.put("status", status);
        map.put("approverId", approverId);
        map.put("approverName", approverName);
        map.put("approvalOpinion", approvalOpinion);
        map.put("approvedTimestamp", approvedTimestamp);
        return getSqlMapClientTemplate().update(
                "PurchaseDaoIbatis.approvePurchase", map);
    }

    /**
     * 提交草稿
     * 
     * @param purchase
     * @return
     */
    public int submitDraft(Purchase purchase) {
        return getSqlMapClientTemplate().update(
                "PurchaseDaoIbatis.submitDraft", purchase);
    }

}
