package com.jyams.purchase.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 采购项
 * 
 * @author zhanglong
 * 
 */
public class PurchaseItem {

    public static final short STATUS_INIT = 0;//
    public static final short STATUS_SUBMITED = 1;//
    public static final short STATUS_PASS_REVIEW = 2;//
    public static final short STATUS_SUBSPENDED = 3;//
    public static final short STATUS_DISCARDED = 4;//

    @Deprecated
    public static final short STATUS_NOTPASS_REVIEW = 5;//
    public static final short STATUS_PASS_APPROVAL = 6;//
    public static final short STATUS_NOTPASS_APPROVAL = 7;//
    public static final short STATUS_ARRIVAL = 8;//
    public static final short STATUS_CANCEL = 9;//

    /**
     * 在保存采购单时，会删除标记删除的采购单项
     */
    public static final short STATUS_DELETED = 10;// 标记删除

    public static final short PAYMENTTYPE_CASH = 1;// 现金
    public static final short PAYMENTTYPE_CHECK = 2;// 支票
    public static final short PAYMENTTYPE_PROMISSORYNOTE = 3;// 本票
    public static final short PAYMENTTYPE_TELEGRAPHICTRANSFER = 4;// 电汇
    public static final short PAYMENTTYPE_UNPAID = 5;// 待付
    public static final short PAYMENTTYPE_OTHER = 6;// 其他

    private Long purchaseItemId;// 采购物料单项标识
    private long purchaseId;
    private Long projectId;// 项目标识
    private String projectName; // 项目名称
    private String materialName; // 物料名称/外包工作内容
    private String specifications;// 规格
    private float count; // 数量
    private String unit; // 单位

    private Short paymentType;// 支付类型
    private Float unitPrice;// (支付/待付金额)单价

    private short status; // 状态

    private int stock; // 库存
    private String remark; // 备注
    private String supplier;// 供应商
    private String telephone;// 电话
    private String fax;// 传真

    private Long reviewTimestamp; // 复核时戳
    private Long reviewerId;// 复核人标识
    private String reviewerName;// 复核人姓名
    private Long sequence;// 次序号

    private Long purchaseTimestamp; // 实际采购时戳

    /**
     * 获取单项总金额
     * 
     * @return
     */
    public float getCost() {
        if (unitPrice == null) {
            return 0.0f;
        }
        return count * unitPrice;
    }

    public Long getPurchaseItemId() {
        return purchaseItemId;
    }

    public void setPurchaseItemId(Long purchaseItemId) {
        this.purchaseItemId = purchaseItemId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Short getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Short paymentType) {
        this.paymentType = paymentType;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getReviewTimestamp() {
        return reviewTimestamp;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewTimestamp(Long reviewTimestamp) {
        this.reviewTimestamp = reviewTimestamp;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * 返回采购单项的描述，主要用于添加在建项目明细
     * 
     * @return
     */
    public String getDescription() {
        return this.getMaterialName() + this.getCount() + this.getUnit();
    }

    /**
     * 获取状态文本
     * 
     * @return
     */
    public String getStatusString() {
        switch (this.status) {
        case STATUS_INIT:
            return "草稿";
        case STATUS_SUBMITED:
            return "已提交";
        case STATUS_PASS_REVIEW:
            return "通过复核";
        case STATUS_SUBSPENDED:
            return "暂不复核";
        case STATUS_DISCARDED:
            return "已废弃";
        case STATUS_NOTPASS_REVIEW:
            return "未通过复核";
        case STATUS_PASS_APPROVAL:
            return "通过审批";
        case STATUS_NOTPASS_APPROVAL:
            return "未通过审批";
        case STATUS_ARRIVAL:
            return "已到货";
        case STATUS_CANCEL:
            return "已取消";
        default:
            return "";
        }
    }

    /**
     * 获取支付类型文本
     * 
     * @return
     */
    public String getPaymentTypeString() {
        if (this.paymentType == null) {
            return null;
        }

        switch (this.paymentType) {
        case PAYMENTTYPE_CASH:
            return "现金";
        case PAYMENTTYPE_CHECK:
            return "支票";
        case PAYMENTTYPE_PROMISSORYNOTE:
            return "本票";
        case PAYMENTTYPE_TELEGRAPHICTRANSFER:
            return "电汇";
        case PAYMENTTYPE_UNPAID:
            return "待付";
        case PAYMENTTYPE_OTHER:
            return "其他";
        default:
            return "";
        }
    }

    public Long getPurchaseTimestamp() {
        return purchaseTimestamp;
    }

    public void setPurchaseTimestamp(Long purchaseTimestamp) {
        this.purchaseTimestamp = purchaseTimestamp;
    }

}
