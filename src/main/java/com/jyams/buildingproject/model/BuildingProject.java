package com.jyams.buildingproject.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;
import com.jyams.project.model.Project;
import com.jyams.util.json.DateToStringJsonSerializer;
import com.jyams.util.json.LongToStringJsonSerializer;

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
    private List<BuildingProjectDetail> BuildingProjectDetails = Lists.newArrayList();

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public float getEstimateCost() {
        return estimateCost;
    }

    public void setEstimateCost(float estimateCost) {
        this.estimateCost = estimateCost;
    }

    public float getActualCost() {
        return actualCost;
    }

    public void setActualCost(float actualCost) {
        this.actualCost = actualCost;
    }

    public float getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(float contractPrice) {
        this.contractPrice = contractPrice;
    }

    public float getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(float paidMoney) {
        this.paidMoney = paidMoney;
    }

    public float getUnpaidMoney() {
        return unpaidMoney;
    }

    public void setUnpaidMoney(float unpaidMoney) {
        this.unpaidMoney = unpaidMoney;
    }

    public int getMarginControl() {
        return marginControl;
    }

    public void setMarginControl(int marginControl) {
        this.marginControl = marginControl;
    }

    public Long getInvoiceTimestamp() {
        return invoiceTimestamp;
    }

    public void setInvoiceTimestamp(Long invoiceTimestamp) {
        this.invoiceTimestamp = invoiceTimestamp;
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public Long getInvoicerId() {
        return invoicerId;
    }

    public void setInvoicerId(Long invoicerId) {
        this.invoicerId = invoicerId;
    }

    public String getInvoicerName() {
        return invoicerName;
    }

    public void setInvoicerName(String invoicerName) {
        this.invoicerName = invoicerName;
    }

    @JsonSerialize(using = DateToStringJsonSerializer.class)
    public Long getCompletionTimestamp() {
        return completionTimestamp;
    }

    public void setCompletionTimestamp(Long completionTimestamp) {
        this.completionTimestamp = completionTimestamp;
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public Long getCompletionPersonId() {
        return completionPersonId;
    }

    public void setCompletionPersonId(Long completionPersonId) {
        this.completionPersonId = completionPersonId;
    }

    public String getCompletionPersonName() {
        return completionPersonName;
    }

    public void setCompletionPersonName(String completionPersonName) {
        this.completionPersonName = completionPersonName;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<BuildingProjectDetail> getBuildingProjectDetails() {
        return BuildingProjectDetails;
    }

    public void setBuildingProjectDetails(List<BuildingProjectDetail> buildingProjectDetails) {
        BuildingProjectDetails = buildingProjectDetails;
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public Long getLastModifierId() {
        return lastModifierId;
    }

    public void setLastModifierId(Long lastModifierId) {
        this.lastModifierId = lastModifierId;
    }

    public String getLastModifierName() {
        return lastModifierName;
    }

    public void setLastModifierName(String lastModifierName) {
        this.lastModifierName = lastModifierName;
    }

    @JsonSerialize(using = DateToStringJsonSerializer.class)
    public Long getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    public void setLastModifiedTimestamp(Long lastModifiedTimestamp) {
        this.lastModifiedTimestamp = lastModifiedTimestamp;
    }

    public String getMargin() {
        if (project == null || getContractPrice() <= 0) {
            return "";
        }
        double i = Math.floor(((getContractPrice() - actualCost) / getContractPrice()) * 10000);
        return i / 100 + "%";
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public Long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    @JsonSerialize(using = DateToStringJsonSerializer.class)
    public Long getCollectionTimestamp() {
        return collectionTimestamp;
    }

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
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public float getCostFromDetail() {
        float totalCost = 0.0F;
        for (BuildingProjectDetail detail : BuildingProjectDetails) {
            totalCost += detail.getCost();
        }
        return totalCost;
    }
}
