/**
 * 
 */
package com.jyams.purchase.query;

import org.apache.commons.lang.StringUtils;

import com.jyams.purchase.model.Purchase;
import com.jyams.util.DateTimeUtils;
import com.jyams.util.search.Query;
import com.jyams.util.search.SearchFilter;
import com.jyams.util.search.SqlOrder;

/**
 * @author zhanglong
 * 
 */
public class PurchaseQuery extends Query<Purchase> {

    private Long purchaseNo; // 采购单编号
    private Integer version; // 采购单版本
    private Long purchaseId; // 采购单标志
    private Long applierId; // 申请人标志
    private String applierName;// 申请人姓名
    private String consigneeName;// 收货人姓名
    private Short type; // 采购单类型
    private String purchaseCode;// 采购单代码
    private String deliveryAddress;// 送货地址
    private Long applyTimestampStart;
    private Long applyTimestampEnd;
    private Long arrivalDateStart;
    private Long arrivalDateEnd;

    public PurchaseQuery() {
        init();
    }

    /**
     * 使用分页属性初始化
     * 
     * @param pageNo
     * @param pageSize
     */
    public PurchaseQuery(Integer pageNo, Integer pageSize) {
        super(pageNo, pageSize);
        init();
    }

    public PurchaseQuery(SearchFilter filter) {
        super(filter);
        init();
    }

    /**
     * 默认删除、取消、废弃的状态不查出
     */
    private void init() {
        getExcludeStatus().add(Purchase.STATUS_CANCEL);
        getExcludeStatus().add(Purchase.STATUS_DELETED);
        getExcludeStatus().add(Purchase.STATUS_DISCARDED);
        this.addOrder(new SqlOrder("purchaseCode", true));
    }

    /**
     * 设置采购单编号
     * 
     * @param purchaseCode
     * @return
     */
    public PurchaseQuery setPurchaseCode(String purchaseCode) {

        if (StringUtils.isBlank(purchaseCode)) {
            return this;
        }

        this.purchaseCode = purchaseCode;
        Purchase parseCode = Purchase.parseCode(purchaseCode);
        purchaseNo = parseCode.getPurchaseNo();
        version = parseCode.getVersion();
        return this;
    }

    public PurchaseQuery setPurchaseNo(Long purchaseNo) {
        this.purchaseNo = purchaseNo;
        return this;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public Long getPurchaseNo() {
        return purchaseNo;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public PurchaseQuery setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
        return this;
    }

    public Long getApplierId() {
        return applierId;
    }

    public PurchaseQuery setApplierId(Long applierId) {
        this.applierId = applierId;
        return this;
    }

    public Short getType() {
        return type;
    }

    public PurchaseQuery setType(Short type) {
        this.type = type;
        return this;
    }

    @Override
    public Query<Purchase> addOrder(SqlOrder order) {
        if (order.getOrderBy().equals("purchaseCode")) {
            super.addOrder(new SqlOrder("purchaseNo", order.isAsc()));
            super.addOrder(new SqlOrder("version", order.isAsc()));
        } else {
            super.addOrder(order);
        }
        return this;
    }

    @Override
    public Query<Purchase> setOrder(SqlOrder order) {
        if (order.getOrderBy().equals("purchaseCode")) {
            super.setOrder(new SqlOrder("purchaseNo", order.isAsc()));
            super.addOrder(new SqlOrder("version", order.isAsc()));
        } else {
            super.setOrder(order);
        }
        return this;
    }

    public String getApplierName() {
        return applierName;
    }

    public void setApplierName(String applierName) {
        this.applierName = applierName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public Long getApplyTimestampStart() {
        return applyTimestampStart;
    }

    public Long getApplyTimestampEnd() {
        return applyTimestampEnd;
    }

    public Long getArrivalDateStart() {
        return arrivalDateStart;
    }

    public Long getArrivalDateEnd() {
        return arrivalDateEnd;
    }

    public void setApplyTimestamp(String applyTimestamp) {
        applyTimestampStart = DateTimeUtils
                .getDayStartTimestamp(applyTimestamp);
        applyTimestampEnd = DateTimeUtils.getDayEndTimestamp(applyTimestamp);
    }

    public void setArrivalDate(String arrivalDate) {
        arrivalDateStart = DateTimeUtils.getDayStartTimestamp(arrivalDate);
        arrivalDateEnd = DateTimeUtils.getDayEndTimestamp(arrivalDate);
    }
}
