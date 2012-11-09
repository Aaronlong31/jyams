package com.jyams.project.query;

import com.jyams.project.model.BuildingProject;
import com.jyams.util.search.Query;
import com.jyams.util.search.SearchFilter;
import com.jyams.util.search.SqlOrder;

public class BuildingProjectQuery extends Query<BuildingProject> {

    private Long projectId;
    private Long projectIdStart;
    private Long projectIdEnd;
    private Float actualCost;
    private Float contractPrice;
    private Float paidMoney;
    private Float unpaidMoney;
    private Integer marginControl;
    private Long invoiceTimestamp;
    private String invoicerName;
    private Long completionTimestamp;
    private String completionPersonName;
    private String payeeName;
    private Long collectionTimestamp;

    // Project
    private String projectName;
    private String companyPrincipalName;
    private String clientPrincipalName;
    private String clientName;
    private String constructPlace;
    private String orderId;

    public BuildingProjectQuery() {
        super();
        init();
    }

    public BuildingProjectQuery(int pageNo, int pageSize) {
        super(pageNo, pageSize);
        init();
    }

    public BuildingProjectQuery(SearchFilter filter) {
        super(filter);
        init();
    }

    private void init() {
        getExcludeStatus().add(BuildingProject.STATUS_HIDDEN);
        this.addOrder(new SqlOrder("bp.projectId", false));
    }

    public Long getProjectId() {
        return projectId;
    }

    public Long getProjectIdStart() {
        return projectIdStart;
    }

    public Long getProjectIdEnd() {
        return projectIdEnd;
    }

    public void setProjectIdStart(Long projectIdStart) {
        this.projectIdStart = projectIdStart;
    }

    public void setProjectIdEnd(Long projectIdEnd) {
        this.projectIdEnd = projectIdEnd;
    }

    public String getProjectName() {
        return projectName;
    }

    public Float getActualCost() {
        return actualCost;
    }

    public Float getContractPrice() {
        return contractPrice;
    }

    public Float getPaidMoney() {
        return paidMoney;
    }

    public Float getUnpaidMoney() {
        return unpaidMoney;
    }

    public Integer getMarginControl() {
        return marginControl;
    }

    public Long getInvoiceTimestamp() {
        return invoiceTimestamp;
    }

    public String getInvoicerName() {
        return invoicerName;
    }

    public Long getCompletionTimestamp() {
        return completionTimestamp;
    }

    public String getCompletionPersonName() {
        return completionPersonName;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public Long getCollectionTimestamp() {
        return collectionTimestamp;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setActualCost(Float actualCost) {
        this.actualCost = actualCost;
    }

    public void setContractPrice(Float contractPrice) {
        this.contractPrice = contractPrice;
    }

    public void setPaidMoney(Float paidMoney) {
        this.paidMoney = paidMoney;
    }

    public void setUnpaidMoney(Float unpaidMoney) {
        this.unpaidMoney = unpaidMoney;
    }

    public void setMarginControl(Integer marginControl) {
        this.marginControl = marginControl;
    }

    public void setInvoiceTimestamp(Long invoiceTimestamp) {
        this.invoiceTimestamp = invoiceTimestamp;
    }

    public void setInvoicerName(String invoicerName) {
        this.invoicerName = invoicerName;
    }

    public void setCompletionTimestamp(Long completionTimestamp) {
        this.completionTimestamp = completionTimestamp;
    }

    public void setCompletionPersonName(String completionPersonName) {
        this.completionPersonName = completionPersonName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public void setCollectionTimestamp(Long collectionTimestamp) {
        this.collectionTimestamp = collectionTimestamp;
    }

    public String getClientPrincipalName() {
        return clientPrincipalName;
    }

    public String getClientName() {
        return clientName;
    }

    public String getConstructPlace() {
        return constructPlace;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setClientPrincipalName(String clientPrincipalName) {
        this.clientPrincipalName = clientPrincipalName;
    }

    public String getCompanyPrincipalName() {
        return companyPrincipalName;
    }

    public void setCompanyPrincipalName(String companyPrincipalName) {
        this.companyPrincipalName = companyPrincipalName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setConstructPlace(String constructPlace) {
        this.constructPlace = constructPlace;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String getOrderString() {
        return super.getOrderString().replaceAll(" projectId", " bp.projectId");
    }

}
