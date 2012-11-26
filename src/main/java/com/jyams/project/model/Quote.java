package com.jyams.project.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.common.collect.Lists;

/**
 * 报价单
 * 
 * @author zhanglong
 * 
 */
public class Quote {

    private Long quoteId; // 报价单编号
    private Long clientId; // 客户商编号
    private String clientName; // 客户商名称
    private int version; // 版本
    private String subject; // 主题
    private long quoterId; // 报价人编号
    private String quoterName; // 报价人姓名
    private String attnName; // 收件人姓名
    private String ccName; // 抄送人姓名
    private long creatorId; // 创建人标识
    private String creatorName; // 创建人姓名
    private long createdTimestamp; // 创建时间
    private float totalPrice; // 总价
    private List<QuoteItem> quoteItems = Lists.newArrayList();

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getQuoterId() {
        return quoterId;
    }

    public void setQuoterId(long quoterId) {
        this.quoterId = quoterId;
    }

    public String getQuoterName() {
        return quoterName;
    }

    public void setQuoterName(String quoterName) {
        this.quoterName = quoterName;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<QuoteItem> getQuoteItems() {
        return quoteItems;
    }

    public void setQuoteItems(List<QuoteItem> quoteItems) {
        this.quoteItems = quoteItems;
    }

    public String getAttnName() {
        return attnName;
    }

    public void setAttnName(String attnName) {
        this.attnName = attnName;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getId() {
        if (quoteId == null) {
            return null;
        }
        return quoteId + "" + (char) version;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}
