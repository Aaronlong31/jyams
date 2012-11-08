package com.jyams.purchase.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jyams.util.DateTimeUtils;

/**
 * 采购(物料)单
 * 
 * @author chenlongming
 * 
 */
public class Purchase {

    public static final Map<Short, String> STATUS_STRING_MAP = Maps
            .newHashMap();
    public static final Map<Short, String> TYPE_STRING_MAP = Maps.newHashMap();

    /**
     * 采购单状态
     */
    public static final short STATUS_INIT = 0;// 草稿
    public static final short STATUS_SUBMITED = 1;// 已提交
    public static final short STATUS_IN_REVIEW = 2;// 复核中
    public static final short STATUS_PASS_REVIEW = 3;// 通过复核
    public static final short STATUS_NOTPASS_REVIEW = 4;// 未通过复核
    public static final short STATUS_PASS_FIRST_APPROVAL = 5;// 通过一审

    /**
     * 使用{@link #STATUS_NOTPASS_APPROVAL}代替
     */
    @Deprecated
    public static final short STATUS_NOTPASS_FIRST_APPROVAL = 6;// 未通过一审
    public static final short STATUS_PASS_APPROVAL = 7;// 通过审批
    public static final short STATUS_NOTPASS_APPROVAL = 8;// 未通过审批
    public static final short STATUS_CANCEL = 9;// 已取消
    public static final short STATUS_PART_ARRIVAL = 10;// 部分到货
    public static final short STATUS_COMPLETED = 11;// 完成采购
    public static final short STATUS_DISCARDED = 12;// 已废弃
    public static final short STATUS_DELETED = 13;// 标记删除

    public static final short APPROVAL_TYPE_FIRST = 1;// 一审通过
    public static final short APPROVAL_TYPE_SECOND = 2;// 二审通过
    public static final short APPROVAL_TYPE_DIS = 3;// 不通过

    public static final short TYPE_MATERIAL = 1;// 物料采购单
    public static final short TYPE_COMPANY = 2;// 公司采购单
    public static final short TYPE_ORDER = 3;// 外包订单

    static {
        STATUS_STRING_MAP.put(STATUS_INIT, "草稿");
        STATUS_STRING_MAP.put(STATUS_SUBMITED, "已提交");
        STATUS_STRING_MAP.put(STATUS_IN_REVIEW, "复核中");
        STATUS_STRING_MAP.put(STATUS_PASS_REVIEW, "通过复核");
        STATUS_STRING_MAP.put(STATUS_NOTPASS_REVIEW, "未通过复核");
        STATUS_STRING_MAP.put(STATUS_PASS_FIRST_APPROVAL, "通过一审");
        STATUS_STRING_MAP.put(STATUS_PASS_APPROVAL, "通过审批");
        STATUS_STRING_MAP.put(STATUS_NOTPASS_APPROVAL, "未通过审批");
        STATUS_STRING_MAP.put(STATUS_CANCEL, "已取消");
        STATUS_STRING_MAP.put(STATUS_PART_ARRIVAL, "部分到货");
        STATUS_STRING_MAP.put(STATUS_COMPLETED, "完成采购");
        STATUS_STRING_MAP.put(STATUS_DISCARDED, "已废弃");
        // statusString.put(STATUS_DELETED, "标记删除");

        TYPE_STRING_MAP.put(TYPE_MATERIAL, "物料采购单");
        TYPE_STRING_MAP.put(TYPE_COMPANY, "公司采购单");
        TYPE_STRING_MAP.put(TYPE_ORDER, "外包订单");
    }

    private Long purchaseId;

    private long purchaseNo; // 采购(物料)单标识
    private int version;// 采购(物料)单版本号
    private short status; // 状态
    private short type;// 类型

    private Long applyTimestamp; // 申请时戳
    private Long applierId;// 申购人标识
    private String applierName;// 申购人姓名

    private long arrivalDate;// 指定到货日期
    private String deliveryAddress;// 送货地
    private long consigneeId;// 收货人标识
    private String consigneeName;// 收货人姓名

    private Long purchaseTimestamp; // 实际采购时戳
    private Long purchaserId;// 采购经办人标识
    private String purchaserName;// 采购经办人姓名

    private String approvalOpinion;// 审批意见
    private Long approvedTimestamp; // 审批时戳
    private Long approverId;// 审批人标识
    private String approverName;// 审批人姓名

    private long createdTimestamp; // 创建时戳
    private long creatorId;
    private String creatorName;

    private List<PurchaseItem> purchaseItems = Lists.newArrayList();

    /**
     * 物料采购单和公司采购单编号格式为：11-102A 外包订单编号格式为：A0001
     * 
     * @return
     */
    public String getPurchaseCode() {
        if (status == STATUS_INIT) {
            return "草稿";
        }
        if (type == TYPE_ORDER) {
            return (char) (purchaseNo / 10000) + ("" + purchaseNo).substring(2);
        } else {
            return (purchaseNo / 1000) + "-" + ("" + purchaseNo).substring(2)
                    + (char) version;
        }
    }

    public static Purchase parseCode(String purchaseCode) {
        Purchase purchase = new Purchase();
        if (purchaseCode.startsWith("W")) {
            purchase.purchaseNo = 870000 + Integer.parseInt(purchaseCode
                    .substring(1));
        } else if (purchaseCode.equals("草稿")) {
            purchase.purchaseNo = 0;
            purchase.version = 0;
        } else if (StringUtils.length(purchaseCode) == 7) {
            purchase.purchaseNo = Integer
                    .parseInt(purchaseCode.substring(0, 2))
                    * 1000
                    + Integer.parseInt(purchaseCode.substring(3, 6));
            purchase.version = (purchaseCode.charAt(6));
        }
        return purchase;
    }

    /**
     * 获得采购单的总话费
     * 
     * @return
     */
    public float getTotalCost() {
        float totalCost = 0.0F;
        for (PurchaseItem purchaseItem : this.purchaseItems) {
            if (purchaseItem.getStatus() == PurchaseItem.STATUS_PASS_APPROVAL
                    || purchaseItem.getStatus() == PurchaseItem.STATUS_ARRIVAL
                    || purchaseItem.getStatus() == PurchaseItem.STATUS_PASS_REVIEW) {
                totalCost += purchaseItem.getCost();
            }
        }
        return totalCost;
    }

    public long getPurchaseNo() {
        return purchaseNo;
    }

    public void setPurchaseNo(long purchaseNo) {
        this.purchaseNo = purchaseNo;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Long getApplyTimestamp() {
        return applyTimestamp;
    }

    public String getApplyTimestampString() {
        return DateTimeUtils.convertLongToString(applyTimestamp);
    }

    public void setApplyTimestamp(Long applyTimestamp) {
        this.applyTimestamp = applyTimestamp;
    }

    public Long getApplierId() {
        return applierId;
    }

    public void setApplierId(Long applierId) {
        this.applierId = applierId;
    }

    public String getApplierName() {
        return applierName;
    }

    public void setApplierName(String applierName) {
        this.applierName = applierName;
    }

    public long getArrivalDate() {
        return arrivalDate;
    }

    public String getArrivalDateString() {
        return DateTimeUtils.convertLongToString(arrivalDate);
    }

    public void setArrivalDate(long arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public long getConsigneeId() {
        return consigneeId;
    }

    public void setConsigneeId(long consigneeId) {
        this.consigneeId = consigneeId;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public Long getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(Long purchaserId) {
        this.purchaserId = purchaserId;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    public Long getApprovedTimestamp() {
        return approvedTimestamp;
    }

    public String getApprovedTimestampString() {
        return DateTimeUtils.convertLongToString(approvedTimestamp);
    }

    public void setApprovedTimestamp(Long approvedTimestamp) {
        this.approvedTimestamp = approvedTimestamp;
    }

    public Long getApproverId() {
        return approverId;
    }

    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public long getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public List<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public Long getPurchaseTimestamp() {
        return purchaseTimestamp;
    }

    public void setPurchaseTimestamp(Long purchaseTimestamp) {
        this.purchaseTimestamp = purchaseTimestamp;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * 获取状态文本
     * 
     * @return
     */
    public String getStatusString() {
        return STATUS_STRING_MAP.get(this.status);
    }

    /**
     * 获取采购单类型文本
     * 
     * @return
     */
    public String getTypeString() {
        return TYPE_STRING_MAP.get(this.type);
    }

    public short getStatusFromItems() {

        Set<Short> itemStatus = Sets.newHashSet();
        for (PurchaseItem purchaseItem : this.getPurchaseItems()) {
            itemStatus.add(purchaseItem.getStatus());
        }

        if (itemStatus.size() == 0) {
            return this.status;
        }

        if (itemStatus.size() == 1) {
            // 1如果采购单项状态全为STATUS_SUBMITED，则采购单状态为STATUS_SUBMITED
            if (itemStatus.contains(PurchaseItem.STATUS_SUBMITED)) {
                return Purchase.STATUS_SUBMITED;
            }
            // 2如果采购单项状态全为STATUS_PASS_REVIEW，则采购单状态为STATUS_PASS_REVIEW
            if (itemStatus.contains(PurchaseItem.STATUS_PASS_REVIEW)) {
                return Purchase.STATUS_PASS_REVIEW;
            }
            // 3如果采购单项状态全为STATUS_DISCARDED，则采购单状态为STATUS_DISCARDED
            if (itemStatus.contains(PurchaseItem.STATUS_DISCARDED)) {
                return Purchase.STATUS_DISCARDED;
            }
            // 4如果采购单项状态全为STATUS_ARRIVAL，则采购单状态为STATUS_COMPLETED
            if (itemStatus.contains(PurchaseItem.STATUS_ARRIVAL)) {
                return Purchase.STATUS_COMPLETED;
            }
            return this.status;
        }

        // 5如果采购单项状态包含STATUS_SUBSPENDED，则采购单状态为STATUS_IN_REVIEW
        // 6如果采购单项状态包含STATUS_SUBMITED，则采购单状态为STATUS_IN_REVIEW
        if (itemStatus.contains(PurchaseItem.STATUS_SUBSPENDED)
                || itemStatus.contains(PurchaseItem.STATUS_SUBMITED)) {
            return Purchase.STATUS_IN_REVIEW;
        }
        // 7如果采购单项状态包含STATUS_PASS_REVIEW，则采购单状态为STATUS_PASS_REVIEW
        if (itemStatus.contains(PurchaseItem.STATUS_PASS_REVIEW)) {
            return Purchase.STATUS_PASS_REVIEW;
        }
        // 8如果采购单项状态包含STATUS_ARRIVAL，则采购单状态为STATUS_PART_ARRIVAL
        if (itemStatus.contains(PurchaseItem.STATUS_ARRIVAL)) {
            return Purchase.STATUS_PART_ARRIVAL;
        }

        return this.status;
    }
}
