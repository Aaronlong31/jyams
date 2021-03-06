package com.jyams.purchase.manager;

import com.jyams.purchase.model.Purchase;
import com.jyams.purchase.query.PurchaseQuery;
import com.jyams.util.DataPage;
import com.jyams.util.RestrictModifyException;

/**
 * 
 * @author zhanglong
 * 
 */
public interface PurchaseManager {

    /**
     * 保存为草稿
     */
    long savePurchase(Purchase purchase);

    /**
     * 提交采购单
     */
    long submitPurchase(Purchase purchase);

    /**
     * 提交草稿
     */
    boolean submitDraft(long purchaseId);

    /**
     * 修改采购单，直接修改已有的记录；只能是提交人修改出于草稿状态的采购单
     * @throws RestrictModifyException
     *             当尝试修改不是草稿状态的采购单时，会抛出此异常
     */
    boolean updatePurchase(Purchase purchase) throws RestrictModifyException;

    /**
     * 升级采购单，重新生成新的采购单和采购单项，采购单编号和之前一样，版本号+1， 是复核人做的操作
     *             当尝试修改不是复核状态的采购单时，会抛出此异常
     */
    boolean upgradePurchase(Purchase purchase) throws RestrictModifyException;

    /**
     * 查看采购单
     */
    Purchase getPurchase(long purchaseId);

    /**
     * 取消采购单，只有草稿状态的采购单才能取消
     * @throws RestrictModifyException
     *             如果采购单不是草稿状态，则抛出此异常
     */
    boolean cancelPurchase(long purchaseId) throws RestrictModifyException;

    /**
     * 审批通过采购单
     * @throws RestrictModifyException
     */
    boolean approvedPurchase(short approvalType, long purchaseId,
            String approvalOpinion) throws RestrictModifyException;

    /**
     * 条件查询采购单列表
     */
    DataPage<Purchase> listPurchases(PurchaseQuery purchaseQuery);

    Purchase getPurchaseFromPurchaeItem(long purchaseItemId);
}
