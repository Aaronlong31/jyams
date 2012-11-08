package com.jyams.purchase.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyams.project.manager.BuildingProjectManager;
import com.jyams.purchase.dao.PurchaseDaoIbatis;
import com.jyams.purchase.dao.PurchaseItemDaoIbatis;
import com.jyams.purchase.manager.PurchaseItemManager;
import com.jyams.purchase.model.Purchase;
import com.jyams.purchase.model.PurchaseItem;

/**
 * 
 * @author zhanglong
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseItemManagerImpl implements PurchaseItemManager {

	@Autowired
	private PurchaseDaoIbatis purchaseDao;
	@Autowired
	private PurchaseItemDaoIbatis purchaseItemDao;
	@Autowired
	private BuildingProjectManager buildingProjectManager;

	@Override
	public boolean addPurchaseItems(List<PurchaseItem> purchaseItems,
			long purchaseId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reviewPurchaseItem(PurchaseItem purchaseItem) {
		// status 有以下几种：
		// 1.STATUS_PASS_REVIEW
		// 2.STATUS_SUBSPENDED
		// 3.STATUS_DISCARDED
		// 4.STATUS_NOTPASS_REVIEW

		// 1.修改采购单项
		purchaseItem.setReviewTimestamp(System.currentTimeMillis());

		purchaseItemDao.review(purchaseItem);

		// 2.修改采购单状态
		Purchase purchase = purchaseDao.get(purchaseItem.getPurchaseId());
		short newStatus = purchase.getStatusFromItems();
		if (newStatus != purchase.getStatus()) {
			purchaseDao.modifyStatus(purchase.getPurchaseId(), newStatus);
		}

		// 3.重新计算采购单总金额，并和在建项目明细关联，计算项目成本
		// 3.1 如果采购单之前没有添加到在建项目明细，则新增一条
		// 3.2 否则修改在建项目明细
		if (purchaseItem.getStatus() != PurchaseItem.STATUS_SUBMITED) {
			PurchaseItem pi = purchaseItemDao.get(purchaseItem
					.getPurchaseItemId());
			purchaseItem.setProjectId(pi.getProjectId());
			purchaseItem.setCount(pi.getCount());
			purchaseItem.setSpecifications(pi.getSpecifications());
			purchaseItem.setUnit(pi.getUnit());
			purchaseItem.setUnitPrice(pi.getUnitPrice());
			buildingProjectManager.updateBuildingProjectDetail(purchaseItem,
					purchaseItem.getPurchaseItemId(), purchase.getType());
		}
		return true;
	}

	@Override
	public boolean arrivePurchaseItem(long purchaseItemId) {
		PurchaseItem pi = purchaseItemDao.get(purchaseItemId);
		purchaseItemDao.arrive(purchaseItemId, PurchaseItem.STATUS_ARRIVAL);
		// 修改采购单状态
		Purchase purchase = purchaseDao.get(pi.getPurchaseId());
		short newStatus = purchase.getStatusFromItems();
		if (newStatus != purchase.getStatus()) {
			purchaseDao.modifyStatus(purchase.getPurchaseId(), newStatus);
		}
		return true;
	}

}
