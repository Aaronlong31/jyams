package com.jyams.purchase;

import org.junit.Test;

import com.jyams.purchase.manager.PurchaseQuery;
import com.jyams.util.search.SqlOrder;

public class PurchaseQueryTest {

    @Test
    public void test1() {
        PurchaseQuery pq = new PurchaseQuery();
        pq.addOrder(new SqlOrder("purchaseId", false)).addOrder(
                new SqlOrder("createdTiemstamp", true));
        System.out.println(pq.getOrderString());
    }
}
