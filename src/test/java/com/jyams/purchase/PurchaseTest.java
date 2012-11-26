package com.jyams.purchase;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.jyams.purchase.model.Purchase;
import com.jyams.purchase.model.PurchaseItem;

public class PurchaseTest {

    @Test
    public void testGetStatusFromItems1() {
        List<PurchaseItem> purchaseItems = Lists.newArrayList();
        PurchaseItem pi1 = new PurchaseItem();
        pi1.setStatus(PurchaseItem.STATUS_ARRIVAL);
        purchaseItems.add(pi1);
        PurchaseItem pi2 = new PurchaseItem();
        pi2.setStatus(PurchaseItem.STATUS_ARRIVAL);
        purchaseItems.add(pi2);
        PurchaseItem pi3 = new PurchaseItem();
        pi3.setStatus(PurchaseItem.STATUS_ARRIVAL);
        purchaseItems.add(pi3);
        PurchaseItem pi4 = new PurchaseItem();
        pi4.setStatus(PurchaseItem.STATUS_ARRIVAL);
        purchaseItems.add(pi4);
        Purchase p = new Purchase();
        p.setStatus(Purchase.STATUS_SUBMITED);
        p.setPurchaseItems(purchaseItems);
        assertEquals(Purchase.STATUS_COMPLETED, p.getStatusFromItems());
    }

    @Test
    public void testGetStatusFromItems2() {
        List<PurchaseItem> purchaseItems = Lists.newArrayList();
        PurchaseItem pi1 = new PurchaseItem();
        pi1.setStatus(PurchaseItem.STATUS_SUBMITED);
        purchaseItems.add(pi1);
        PurchaseItem pi2 = new PurchaseItem();
        pi2.setStatus(PurchaseItem.STATUS_SUBMITED);
        purchaseItems.add(pi2);
        PurchaseItem pi3 = new PurchaseItem();
        pi3.setStatus(PurchaseItem.STATUS_SUBMITED);
        purchaseItems.add(pi3);
        PurchaseItem pi4 = new PurchaseItem();
        pi4.setStatus(PurchaseItem.STATUS_SUBMITED);
        purchaseItems.add(pi4);
        Purchase p = new Purchase();
        p.setStatus(Purchase.STATUS_CANCEL);
        p.setPurchaseItems(purchaseItems);
        assertEquals(Purchase.STATUS_SUBMITED, p.getStatusFromItems());
    }

    @Test
    public void testGetStatusFromItems3() {
        List<PurchaseItem> purchaseItems = Lists.newArrayList();
        PurchaseItem pi1 = new PurchaseItem();
        pi1.setStatus(PurchaseItem.STATUS_PASS_REVIEW);
        purchaseItems.add(pi1);
        PurchaseItem pi2 = new PurchaseItem();
        pi2.setStatus(PurchaseItem.STATUS_PASS_REVIEW);
        purchaseItems.add(pi2);
        PurchaseItem pi3 = new PurchaseItem();
        pi3.setStatus(PurchaseItem.STATUS_PASS_REVIEW);
        purchaseItems.add(pi3);
        PurchaseItem pi4 = new PurchaseItem();
        pi4.setStatus(PurchaseItem.STATUS_PASS_REVIEW);
        purchaseItems.add(pi4);
        Purchase p = new Purchase();
        p.setStatus(Purchase.STATUS_SUBMITED);
        p.setPurchaseItems(purchaseItems);
        assertEquals(Purchase.STATUS_PASS_REVIEW, p.getStatusFromItems());
    }

    @Test
    public void testGetStatusFromItems4() {
        List<PurchaseItem> purchaseItems = Lists.newArrayList();
        PurchaseItem pi1 = new PurchaseItem();
        pi1.setStatus(PurchaseItem.STATUS_DISCARDED);
        purchaseItems.add(pi1);
        PurchaseItem pi2 = new PurchaseItem();
        pi2.setStatus(PurchaseItem.STATUS_DISCARDED);
        purchaseItems.add(pi2);
        PurchaseItem pi3 = new PurchaseItem();
        pi3.setStatus(PurchaseItem.STATUS_DISCARDED);
        purchaseItems.add(pi3);
        PurchaseItem pi4 = new PurchaseItem();
        pi4.setStatus(PurchaseItem.STATUS_DISCARDED);
        purchaseItems.add(pi4);
        Purchase p = new Purchase();
        p.setStatus(Purchase.STATUS_SUBMITED);
        p.setPurchaseItems(purchaseItems);
        assertEquals(Purchase.STATUS_DISCARDED, p.getStatusFromItems());
    }

    @Test
    public void testGetStatusFromItems5() {
        List<PurchaseItem> purchaseItems = Lists.newArrayList();
        PurchaseItem pi1 = new PurchaseItem();
        pi1.setStatus(PurchaseItem.STATUS_SUBSPENDED);
        purchaseItems.add(pi1);
        PurchaseItem pi2 = new PurchaseItem();
        pi2.setStatus(PurchaseItem.STATUS_ARRIVAL);
        purchaseItems.add(pi2);
        PurchaseItem pi3 = new PurchaseItem();
        pi3.setStatus(PurchaseItem.STATUS_ARRIVAL);
        purchaseItems.add(pi3);
        PurchaseItem pi4 = new PurchaseItem();
        pi4.setStatus(PurchaseItem.STATUS_ARRIVAL);
        purchaseItems.add(pi4);
        Purchase p = new Purchase();
        p.setStatus(Purchase.STATUS_SUBMITED);
        p.setPurchaseItems(purchaseItems);
        assertEquals(Purchase.STATUS_IN_REVIEW, p.getStatusFromItems());
    }

    @Test
    public void testGetStatusFromItems6() {
        List<PurchaseItem> purchaseItems = Lists.newArrayList();
        PurchaseItem pi1 = new PurchaseItem();
        pi1.setStatus(PurchaseItem.STATUS_SUBMITED);
        purchaseItems.add(pi1);
        PurchaseItem pi2 = new PurchaseItem();
        pi2.setStatus(PurchaseItem.STATUS_ARRIVAL);
        purchaseItems.add(pi2);
        PurchaseItem pi3 = new PurchaseItem();
        pi3.setStatus(PurchaseItem.STATUS_ARRIVAL);
        purchaseItems.add(pi3);
        PurchaseItem pi4 = new PurchaseItem();
        pi4.setStatus(PurchaseItem.STATUS_ARRIVAL);
        purchaseItems.add(pi4);
        Purchase p = new Purchase();
        p.setStatus(Purchase.STATUS_SUBMITED);
        p.setPurchaseItems(purchaseItems);
        assertEquals(Purchase.STATUS_IN_REVIEW, p.getStatusFromItems());
    }

    @Test
    public void testGetStatusFromItems7() {
        List<PurchaseItem> purchaseItems = Lists.newArrayList();
        PurchaseItem pi1 = new PurchaseItem();
        pi1.setStatus(PurchaseItem.STATUS_PASS_REVIEW);
        purchaseItems.add(pi1);
        PurchaseItem pi2 = new PurchaseItem();
        pi2.setStatus(PurchaseItem.STATUS_ARRIVAL);
        purchaseItems.add(pi2);
        PurchaseItem pi3 = new PurchaseItem();
        pi3.setStatus(PurchaseItem.STATUS_ARRIVAL);
        purchaseItems.add(pi3);
        PurchaseItem pi4 = new PurchaseItem();
        pi4.setStatus(PurchaseItem.STATUS_ARRIVAL);
        purchaseItems.add(pi4);
        Purchase p = new Purchase();
        p.setStatus(Purchase.STATUS_SUBMITED);
        p.setPurchaseItems(purchaseItems);
        assertEquals(Purchase.STATUS_PASS_REVIEW, p.getStatusFromItems());
    }

    @Test
    public void testGetStatusFromItems8() {
        List<PurchaseItem> purchaseItems = Lists.newArrayList();
        PurchaseItem pi1 = new PurchaseItem();
        pi1.setStatus(PurchaseItem.STATUS_ARRIVAL);
        purchaseItems.add(pi1);
        PurchaseItem pi2 = new PurchaseItem();
        pi2.setStatus(PurchaseItem.STATUS_PASS_APPROVAL);
        purchaseItems.add(pi2);
        PurchaseItem pi3 = new PurchaseItem();
        pi3.setStatus(PurchaseItem.STATUS_PASS_APPROVAL);
        purchaseItems.add(pi3);
        PurchaseItem pi4 = new PurchaseItem();
        pi4.setStatus(PurchaseItem.STATUS_PASS_APPROVAL);
        purchaseItems.add(pi4);
        Purchase p = new Purchase();
        p.setStatus(Purchase.STATUS_SUBMITED);
        p.setPurchaseItems(purchaseItems);
        assertEquals(Purchase.STATUS_PART_ARRIVAL, p.getStatusFromItems());
    }

}
