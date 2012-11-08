/**
 * 
 */
package com.jyams.purchase.manager;

import java.util.List;

import com.jyams.purchase.model.PurchaseItem;


/**
 * @author zhanglong
 *
 */
public interface PurchaseItemManager {

	/**
	 * 添加多个采购单项到采购单
	 * @param purchaseItems
	 * @param purchaseId
	 * @return
	 */
	boolean addPurchaseItems(List<PurchaseItem> purchaseItems, long purchaseId);
	
	/**
	 * 复核采购单项
	 * @param purchaseItem
	 * @return
	 */
	boolean reviewPurchaseItem(PurchaseItem purchaseItem);
	
	boolean arrivePurchaseItem(long purchaseItemId);
}
