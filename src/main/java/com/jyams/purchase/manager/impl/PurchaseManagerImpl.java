/**
 * 
 */
package com.jyams.purchase.manager.impl;

import static com.jyams.util.KeyGenerator.getOrderId;
import static com.jyams.util.KeyGenerator.getPurchaseNo;

import java.util.List;

import com.jyams.security.SecurityUtils;
import com.jyams.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyams.buildingproject.manager.BuildingProjectManager;
import com.jyams.purchase.dao.PurchaseDaoIbatis;
import com.jyams.purchase.dao.PurchaseItemDaoIbatis;
import com.jyams.purchase.model.Purchase;
import com.jyams.purchase.model.PurchaseItem;
import com.jyams.purchase.query.PurchaseQuery;
import com.jyams.util.DataPage;
import com.jyams.util.IdUtil;
import com.jyams.util.RestrictModifyException;

/**
 * @author zhanglong
 * 
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseManagerImpl implements
        com.jyams.purchase.manager.PurchaseManager {

    @Autowired
    private PurchaseDaoIbatis purchaseDao;
    @Autowired
    private PurchaseItemDaoIbatis purchaseItemDao;
    @Autowired
    private BuildingProjectManager buildingProjectManager;

    @Override
    public long savePurchase(Purchase purchase) {
        purchase.setStatus(Purchase.STATUS_INIT);
        return add(purchase);
    }

    @Override
    public long submitPurchase(Purchase purchase) {
        purchase.setStatus(Purchase.STATUS_SUBMITED);

        // 外包订单的格式为："W0001"
        if (purchase.getType() == Purchase.TYPE_ORDER) {
            purchase.setPurchaseNo(getOrderId());
        } else {
            // 物料采购单和公司采购单的编号都是"11-001A"格式
            purchase.setPurchaseNo(getPurchaseNo());
        }
        purchase.setVersion('A');
        purchase.setApplyTimestamp(System.currentTimeMillis());
        return add(purchase);
    }

    private long add(Purchase purchase) {

        long purchaseId = IdUtil.nextLong();

        short itemStatus = PurchaseItem.STATUS_INIT;
        if (purchase.getStatus() == Purchase.STATUS_SUBMITED) {
            itemStatus = PurchaseItem.STATUS_SUBMITED;
        }

        purchase.setPurchaseId(purchaseId);
        purchase.setCreatedTimestamp(System.currentTimeMillis());
        purchaseDao.insert(purchase);

        // 循环插入采购单项
        List<PurchaseItem> purchaseItems = purchase.getPurchaseItems();
        if (purchaseItems != null) {
            long sequence = System.currentTimeMillis();
            for (PurchaseItem purchaseItem : purchaseItems) {
                purchaseItem.setPurchaseItemId(IdUtil.nextLong());
                purchaseItem.setPurchaseId(purchaseId);
                purchaseItem.setStatus(itemStatus);
                purchaseItem.setSequence(sequence++);
                purchaseItemDao.insert(purchaseItem);
            }
        }

        return purchaseId;
    }

    @Override
    public boolean updatePurchase(Purchase purchase)
            throws RestrictModifyException {

        // 对于已提交的采购单，提交人不能修改
        if (purchase.getStatus() != Purchase.STATUS_INIT) {
            throw new RestrictModifyException("该采购单已提交，不能修改！");
        }

        // 修改采购单
        purchaseDao.update(purchase);
        long sequence = System.currentTimeMillis();
        for (PurchaseItem purchaseItem : purchase.getPurchaseItems()) {

            if (purchaseItem.getPurchaseItemId() == null) {
                // 若purchaseItemId 为空，则新增
                purchaseItem.setPurchaseId(purchase.getPurchaseId());
                purchaseItem.setPurchaseItemId(IdUtil.nextLong());
                purchaseItem.setStatus(PurchaseItem.STATUS_INIT);
                purchaseItem.setSequence(sequence++);
                purchaseItemDao.insert(purchaseItem);
            } else if (purchaseItem.getStatus() == PurchaseItem.STATUS_DELETED) {
                // 若status为标记删除，则删除
                purchaseItemDao.removeById(purchaseItem.getPurchaseItemId());
            } else {
                // 否则修改
                purchaseItemDao.update(purchaseItem);
            }
        }

        return true;
    }

    @Override
    public boolean upgradePurchase(Purchase purchase)
            throws RestrictModifyException {

        Purchase oldPurchase = getPurchase(purchase.getPurchaseId());

        if (oldPurchase.getStatus() != Purchase.STATUS_SUBMITED
                && oldPurchase.getStatus() != Purchase.STATUS_IN_REVIEW) {
            throw new RestrictModifyException("只有已提交和复核中的物料采购单才能由复核人员修改！");
        }

        if (oldPurchase.getType() != Purchase.TYPE_MATERIAL) {
            throw new RestrictModifyException("只有物料采购单才能由复核人员修改！");
        }

        long oldPurchaseId = purchase.getPurchaseId();

        // 1.插入新的采购单记录，编号不变，版本+1
        purchase.setApplierId(oldPurchase.getApplierId());
        purchase.setApplierName(oldPurchase.getApplierName());
        purchase.setApplyTimestamp(oldPurchase.getApplyTimestamp());
        purchase.setCreatorId(oldPurchase.getCreatorId());
        purchase.setCreatorName(oldPurchase.getCreatorName());
        purchase.setCreatedTimestamp(System.currentTimeMillis());
        purchase.setStatus(Purchase.STATUS_IN_REVIEW);
        purchase.setPurchaseId(IdUtil.nextLong());
        purchase.setPurchaseNo(oldPurchase.getPurchaseNo());
        purchase.setVersion(oldPurchase.getVersion() + 1);
        purchase.setType(oldPurchase.getType());

        purchaseDao.insert(purchase);

        long sequence = System.currentTimeMillis();
        for (PurchaseItem purchaseItem : purchase.getPurchaseItems()) {

            Long oldPurchaseItemId = purchaseItem.getPurchaseItemId();

            // 2.插入新的采购单项，若status为标记删除，则不插入
            if (purchaseItem.getStatus() == PurchaseItem.STATUS_DELETED) {
                buildingProjectManager.updateBuildingProjectDetail(
                        purchaseItem, oldPurchaseItemId, purchase.getType());
                continue;
            }

            if (purchaseItem.getPurchaseItemId() == null) {
                // 若purchaseItemId 为空，则表示为新增项，状态设置为提交
                purchaseItem.setStatus(PurchaseItem.STATUS_SUBMITED);
            }

            purchaseItem.setPurchaseId(purchase.getPurchaseId());
            purchaseItem.setPurchaseItemId(IdUtil.nextLong());
            purchaseItem.setSequence(sequence++);
            purchaseItemDao.insert(purchaseItem);
            buildingProjectManager.updateBuildingProjectDetail(purchaseItem,
                    oldPurchaseItemId, purchase.getType());
        }

        // 将旧版本的采购单状态修改为已废弃
        purchaseDao.modifyStatus(oldPurchaseId, Purchase.STATUS_DISCARDED);
        // 旧版本的采购单项状态不变

        return true;
    }

    @Override
    public boolean submitDraft(long purchaseId) {

        Purchase purchase = purchaseDao.get(purchaseId);

        // 设置编号和版本
        if (purchase.getType() == Purchase.TYPE_ORDER) {
            purchase.setPurchaseNo(getOrderId());
        } else {
            // 物料采购单和公司采购单的编号都是"11-001A"格式
            purchase.setPurchaseNo(getPurchaseNo());
        }
        purchase.setVersion('A');

        // 设置提交时间
        purchase.setApplyTimestamp(System.currentTimeMillis());

        // 设置状态为已提交
        purchase.setStatus(Purchase.STATUS_SUBMITED);
        boolean result = purchaseDao.submitDraft(purchase) > 0;

        // 修改采购单项的状态为已提交
        purchaseItemDao.batchModifyStatus(purchaseId,
                PurchaseItem.STATUS_SUBMITED);
        return result;
    }

    @Override
    public Purchase getPurchase(long purchaseId) {
        return purchaseDao.get(purchaseId);
    }

    @Override
    public DataPage<Purchase> listPurchases(PurchaseQuery purchaseQuery) {
        return purchaseDao.pageQuery(purchaseQuery);
    }

    @Override
    public boolean cancelPurchase(long purchaseId)
            throws RestrictModifyException {

        Purchase purchase = purchaseDao.get(purchaseId);
        if (purchase.getStatus() != Purchase.STATUS_INIT) {
            throw new RestrictModifyException();
        }

        // 修改采购单状态
        boolean result = purchaseDao.modifyStatus(purchaseId,
                Purchase.STATUS_CANCEL) > 0;

        // 修改采购单项状态
        purchaseItemDao.batchModifyStatus(purchaseId,
                PurchaseItem.STATUS_CANCEL);
        return result;
    }

    @Override
    public boolean approvedPurchase(short approvalType, long purchaseId,
            String approvalOpinion) throws RestrictModifyException {

        User currentUser = SecurityUtils.getCurrentUser();
        Purchase purchase = getPurchase(purchaseId);
        short initStatus = Purchase.STATUS_SUBMITED;
        if (purchase.getType() == Purchase.TYPE_MATERIAL) {
            initStatus = Purchase.STATUS_PASS_REVIEW;
        }

        checkCondition(approvalType, initStatus, purchase.getStatus());

        short status;
        short itemStatus = PurchaseItem.STATUS_PASS_APPROVAL;
        if (approvalType == Purchase.APPROVAL_TYPE_DIS) {
            // 如果审批类型为不通过，则修改状态为未通过审批，返回
            status = Purchase.STATUS_NOTPASS_APPROVAL;
            itemStatus = PurchaseItem.STATUS_NOTPASS_APPROVAL;
        } else if (purchase.getTotalCost() < 50000) {
            // 如果采购单总金额<50000，则修改状态为通过审批，返回
            status = Purchase.STATUS_PASS_APPROVAL;
        } else if (purchase.getStatus() == Purchase.STATUS_PASS_FIRST_APPROVAL) {
            // 如果采购单状态为一审通过，则修改状态为通过审批，返回
            status = Purchase.STATUS_PASS_APPROVAL;
        } else if (purchase.getStatus() == initStatus) {
            // 如果采购单状态为已提交，则修改状态为通过一审，返回
            status = Purchase.STATUS_PASS_FIRST_APPROVAL;
        } else {
            throw new RestrictModifyException("");
        }

        // 修改采购单记录
        boolean result = purchaseDao.approvePurchase(purchaseId, status,
                currentUser.getUserId(), currentUser.getUsername(), approvalOpinion,
                System.currentTimeMillis()) > 0;

        // 修改采购单项状态
        purchaseItemDao.batchModifyStatus(purchaseId, itemStatus);
        return result;
    }

    /**
     * 检查条件
     * @throws RestrictModifyException
     */
    private void checkCondition(short approvalType, short initStatus,
            short status) throws RestrictModifyException {

        if (status == initStatus
                || status == Purchase.STATUS_PASS_FIRST_APPROVAL
                || (approvalType == Purchase.APPROVAL_TYPE_FIRST && status == initStatus)
                || (approvalType == Purchase.APPROVAL_TYPE_SECOND && status == Purchase.STATUS_PASS_FIRST_APPROVAL)) {
            return;
        }
        throw new RestrictModifyException("");
    }

    @Override
    public Purchase getPurchaseFromPurchaeItem(long purchaseItemId) {
        PurchaseItem pi = purchaseItemDao.get(purchaseItemId);
        return getPurchase(pi.getPurchaseId());
    }

}
