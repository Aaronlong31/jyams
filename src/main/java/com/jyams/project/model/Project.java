package com.jyams.project.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jyams.util.DateTimeUtils;
import com.jyams.util.json.LongToStringJsonSerializer;

/**
 * 项目
 * 
 * @author zhanglong
 * 
 */
public class Project {
    private Long projectId; // 项目标识
    private String projectName; // 项目名称
    private long companyPrincipalId; // 公司负责人标识
    private String companyPrincipalName; // 公司负责人姓名
    private Long clientId; // 客户商标识
    private String clientName; // 客户商名称
    private Long clientPrincipalId; // 客户负责人标识
    private String clientPrincipalName; // 客户负责人姓名
    private String constructPlace; // 施工地点
    private String quoteId; // 报价单编号
    private String orderId; // 订单编号
    private Integer orderDate; // 出订单日
    private int requiredCompletionDate; // 要求完工日
    private int canDelayDay = 0; // 可延后时间
    private String orderContent; // 订单内容
    private long creatorId; // 创建者标识
    private String creatorName; // 创建者名称
    private long createdTimestamp; // 创建时戳
    private Long lastModifierId; // 最后修改人标识
    private String lastModifierName; // 最后修改人姓名
    private Long lastModifiedTimestamp; // 最后修改时戳

    // private int status; // 状态

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public Long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public long getCompanyPrincipalId() {
        return companyPrincipalId;
    }

    public String getCompanyPrincipalName() {
        return companyPrincipalName;
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public Long getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public Long getClientPrincipalId() {
        return clientPrincipalId;
    }

    public String getClientPrincipalName() {
        return clientPrincipalName;
    }

    public String getConstructPlace() {
        return constructPlace;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public String getOrderId() {
        return orderId;
    }

    public Integer getOrderDate() {
        return orderDate;
    }

    public int getRequiredCompletionDate() {
        return requiredCompletionDate;
    }

    public int getCanDelayDay() {
        return canDelayDay;
    }

    public String getOrderContent() {
        return orderContent;
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public long getCreatorId() {
        return creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public long getCreatedTimestamp() {
        return createdTimestamp;
    }

    @JsonSerialize(using = LongToStringJsonSerializer.class)
    public Long getLastModifierId() {
        return lastModifierId;
    }

    public String getLastModifierName() {
        return lastModifierName;
    }

    public Long getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setCompanyPrincipalId(long companyPrincipalId) {
        this.companyPrincipalId = companyPrincipalId;
    }

    public void setCompanyPrincipalName(String companyPrincipalName) {
        this.companyPrincipalName = companyPrincipalName;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientPrincipalId(Long clientPrincipalId) {
        this.clientPrincipalId = clientPrincipalId;
    }

    public void setClientPrincipalName(String clientPrincipalName) {
        this.clientPrincipalName = clientPrincipalName;
    }

    public void setConstructPlace(String constructPlace) {
        this.constructPlace = constructPlace;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(Integer orderDate) {
        this.orderDate = orderDate;
    }

    public void setRequiredCompletionDate(int requiredCompletionDate) {
        this.requiredCompletionDate = requiredCompletionDate;
    }

    public void setCanDelayDay(int canDelayDay) {
        this.canDelayDay = canDelayDay;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setCreatedTimestamp(long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public void setLastModifierId(Long lastModifierId) {
        this.lastModifierId = lastModifierId;
    }

    public void setLastModifierName(String lastModifierName) {
        this.lastModifierName = lastModifierName;
    }

    public void setLastModifiedTimestamp(Long lastModifiedTimestamp) {
        this.lastModifiedTimestamp = lastModifiedTimestamp;
    }

    public void setOrderDateString(String orderDate) {
        this.orderDate = DateTimeUtils.convertStringToInteger(orderDate);
    }

    public String getOrderDateString() {
        return DateTimeUtils.convertIntegerDayToString(orderDate);
    }

    public void setRequiredCompletionDateString(String requiredCompletionDate) {
        this.requiredCompletionDate = DateTimeUtils.convertStringToInteger(requiredCompletionDate);
    }

    public String getRequiredCompletionDateString() {
        return DateTimeUtils.convertIntegerDayToString(requiredCompletionDate);
    }

    public String getCreatedTimeString() {
        return DateTimeUtils.convertLongToString(createdTimestamp);
    }

    public String getLastModifiedTimeString() {
        return DateTimeUtils.convertLongToString(lastModifiedTimestamp);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
