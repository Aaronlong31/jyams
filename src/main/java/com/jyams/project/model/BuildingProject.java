package com.jyams.project.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.common.collect.Lists;

/**
 * 在建项目
 * 
 * @author zhanglong
 * 
 */
public class BuildingProject {

    /** 项目状态 - 在建 */
    public static final short STATUS_BUILDING = 1;
    /** 项目状态 - 延后 */
    public static final short STATUS_DELAYED = 2;
    /** 项目状态 - 报警 */
    public static final short STATUS_ALARM = 4;
    /** 项目状态 - 超支 */
    public static final short STATUS_OVERRUN = 8;
    /** 项目状态 - 完工 */
    public static final short STATUS_COMPLETED = 16;
    /** 项目状态 - 已开票 */
    public static final short STATUS_INVOICED = 32;
    /** 项目状态 - 已收款 */
    public static final short STATUS_COLLECTED = 64;
    /** 项目状态 - 隐藏 */
    public static final short STATUS_HIDDEN = 128;

    private Long projectId; // 项目标识
    private float estimateCost; // 预估成本
    private float actualCost; // 实际成本
    private float contractPrice; // 合同总价
    private float paidMoney; // 已付货款
    private float unpaidMoney; // 未付货款
    private int marginControl; // 利润率控制
    private Long invoiceTimestamp; // 开票时间
    private Long invoicerId; // 开票人标识
    private String invoicerName; // 开票人姓名
    private Long completionTimestamp; // 完工时间
    private Long completionPersonId; // 完工人标识
    private String completionPersonName; // 完工人姓名
    private Long payeeId; // 收款人标识
    private String payeeName; // 收款人姓名
    private Long collectionTimestamp; // 收款时间戳
    private Long lastModifierId; // 最后修改人标识
    private String lastModifierName; // 最后修改人姓名
    private Long lastModifiedTimestamp; // 最后修改时戳
    private short status; // 状态

    private Project project; // 项目
    private List<BuildingProjectDetail> BuildingProjectDetails = Lists
            .newArrayList();

    /**
     * @return the projectId
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     *            the projectId to set
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the estimateCost
     */
    public float getEstimateCost() {
        return estimateCost;
    }

    /**
     * @param estimateCost
     *            the estimateCost to set
     */
    public void setEstimateCost(float estimateCost) {
        this.estimateCost = estimateCost;
    }

    /**
     * @return the actualCost
     */
    public float getActualCost() {
        return actualCost;
    }

    /**
     * @param actualCost
     *            the actualCost to set
     */
    public void setActualCost(float actualCost) {
        this.actualCost = actualCost;
    }

    /**
     * @return the contractPrice
     */
    public float getContractPrice() {
        return contractPrice;
    }

    /**
     * @param contractPrice
     *            the contractPrice to set
     */
    public void setContractPrice(float contractPrice) {
        this.contractPrice = contractPrice;
    }

    /**
     * @return the paidMoney
     */
    public float getPaidMoney() {
        return paidMoney;
    }

    /**
     * @param paidMoney
     *            the paidMoney to set
     */
    public void setPaidMoney(float paidMoney) {
        this.paidMoney = paidMoney;
    }

    /**
     * @return the unpaidMoney
     */
    public float getUnpaidMoney() {
        return unpaidMoney;
    }

    /**
     * @param unpaidMoney
     *            the unpaidMoney to set
     */
    public void setUnpaidMoney(float unpaidMoney) {
        this.unpaidMoney = unpaidMoney;
    }

    /**
     * @return the marginControl
     */
    public int getMarginControl() {
        return marginControl;
    }

    /**
     * @param marginControl
     *            the marginControl to set
     */
    public void setMarginControl(int marginControl) {
        this.marginControl = marginControl;
    }

    /**
     * @return the invoiceTimestamp
     */
    public Long getInvoiceTimestamp() {
        return invoiceTimestamp;
    }

    /**
     * @param invoiceTimestamp
     *            the invoiceTimestamp to set
     */
    public void setInvoiceTimestamp(Long invoiceTimestamp) {
        this.invoiceTimestamp = invoiceTimestamp;
    }

    /**
     * @return the invoicerId
     */
    public Long getInvoicerId() {
        return invoicerId;
    }

    /**
     * @param invoicerId
     *            the invoicerId to set
     */
    public void setInvoicerId(Long invoicerId) {
        this.invoicerId = invoicerId;
    }

    /**
     * @return the invoicerName
     */
    public String getInvoicerName() {
        return invoicerName;
    }

    /**
     * @param invoicerName
     *            the invoicerName to set
     */
    public void setInvoicerName(String invoicerName) {
        this.invoicerName = invoicerName;
    }

    /**
     * @return the completionTimestamp
     */
    public Long getCompletionTimestamp() {
        return completionTimestamp;
    }

    /**
     * @param completionTimestamp
     *            the completionTimestamp to set
     */
    public void setCompletionTimestamp(Long completionTimestamp) {
        this.completionTimestamp = completionTimestamp;
    }

    /**
     * @return the completionPersonId
     */
    public Long getCompletionPersonId() {
        return completionPersonId;
    }

    /**
     * @param completionPersonId
     *            the completionPersonId to set
     */
    public void setCompletionPersonId(Long completionPersonId) {
        this.completionPersonId = completionPersonId;
    }

    /**
     * @return the completionPersonName
     */
    public String getCompletionPersonName() {
        return completionPersonName;
    }

    /**
     * @param completionPersonName
     *            the completionPersonName to set
     */
    public void setCompletionPersonName(String completionPersonName) {
        this.completionPersonName = completionPersonName;
    }

    /**
     * @return the status
     */
    public short getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(short status) {
        this.status = status;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project
     *            the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * @return the buildingProjectDetails
     */
    public List<BuildingProjectDetail> getBuildingProjectDetails() {
        return BuildingProjectDetails;
    }

    /**
     * @param buildingProjectDetails
     *            the buildingProjectDetails to set
     */
    public void setBuildingProjectDetails(
            List<BuildingProjectDetail> buildingProjectDetails) {
        BuildingProjectDetails = buildingProjectDetails;
    }

    /**
     * @return the lastModifierId
     */
    public Long getLastModifierId() {
        return lastModifierId;
    }

    /**
     * @param lastModifierId
     *            the lastModifierId to set
     */
    public void setLastModifierId(Long lastModifierId) {
        this.lastModifierId = lastModifierId;
    }

    /**
     * @return the lastModifierName
     */
    public String getLastModifierName() {
        return lastModifierName;
    }

    /**
     * @param lastModifierName
     *            the lastModifierName to set
     */
    public void setLastModifierName(String lastModifierName) {
        this.lastModifierName = lastModifierName;
    }

    /**
     * @return the lastModifiedTimestamp
     */
    public Long getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    /**
     * @param lastModifiedTimestamp
     *            the lastModifiedTimestamp to set
     */
    public void setLastModifiedTimestamp(Long lastModifiedTimestamp) {
        this.lastModifiedTimestamp = lastModifiedTimestamp;
    }

    public String getMargin() {
        if (project == null || getContractPrice() <= 0)
            return "";
        double i = Math
                .floor(((getContractPrice() - actualCost) / getContractPrice()) * 10000);
        return i / 100 + "%";
    }

    /**
     * @return the payeeId
     */
    public Long getPayeeId() {
        return payeeId;
    }

    /**
     * @param payeeId
     *            the payeeId to set
     */
    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    /**
     * @return the payeeName
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     * @param payeeName
     *            the payeeName to set
     */
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    /**
     * @return the collectionTimestamp
     */
    public Long getCollectionTimestamp() {
        return collectionTimestamp;
    }

    /**
     * @param collectionTimestamp
     *            the collectionTimestamp to set
     */
    public void setColletionTimestamp(Long collectionTimestamp) {
        this.collectionTimestamp = collectionTimestamp;
    }

    public String getStatusString() {
        StringBuilder statusString = new StringBuilder();
        if ((status & STATUS_ALARM) == STATUS_ALARM) {
            statusString.append("报警  ");
        }
        if ((status & STATUS_BUILDING) == STATUS_BUILDING) {
            statusString.append("在建  ");
        }
        if ((status & STATUS_COMPLETED) == STATUS_COMPLETED) {
            statusString.append("已完工  ");
        }
        if ((status & STATUS_OVERRUN) == STATUS_OVERRUN) {
            statusString.append("超支  ");
        }
        if ((status & STATUS_DELAYED) == STATUS_DELAYED) {
            statusString.append("延迟  ");
        }
        if ((status & STATUS_INVOICED) == STATUS_INVOICED) {
            statusString.append("已开票  ");
        }
        if ((status & STATUS_COLLECTED) == STATUS_COLLECTED) {
            statusString.append("已收款  ");
        }
        return statusString.toString();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }

    public float getCostFromDetail() {
        float totalCost = 0.0F;
        for (BuildingProjectDetail detail : this.BuildingProjectDetails) {
            totalCost += detail.getCost();
        }
        return totalCost;
    }
}
