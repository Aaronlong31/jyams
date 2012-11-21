package com.jyams.project.controller;

/**
 * @author zhanglong
 * 
 *         Nov 21, 2012 9:33:54 PM
 */
public class AddProjectForm {

    private String projectName;

    private String quoteId;

    private Long companyPrincipalId;

    private String constructPlace;

    private String clientName;

    private String clientPrincipalName;

    private String orderId;

    private String orderDateString;

    private String requiredCompletionDateString;

    private int canDelayDay;

    private String orderContent;

    private boolean isNewYear = false;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public Long getCompanyPrincipalId() {
        return companyPrincipalId;
    }

    public void setCompanyPrincipalId(Long companyPrincipalId) {
        this.companyPrincipalId = companyPrincipalId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPrincipalName() {
        return clientPrincipalName;
    }

    public void setClientPrincipalName(String clientPrincipalName) {
        this.clientPrincipalName = clientPrincipalName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDateString() {
        return orderDateString;
    }

    public void setOrderDateString(String orderDateString) {
        this.orderDateString = orderDateString;
    }

    public String getRequiredCompletionDateString() {
        return requiredCompletionDateString;
    }

    public void setRequiredCompletionDateString(
            String requiredCompletionDateString) {
        this.requiredCompletionDateString = requiredCompletionDateString;
    }

    public int getCanDelayDay() {
        return canDelayDay;
    }

    public void setCanDelayDay(int canDelayDay) {
        this.canDelayDay = canDelayDay;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public boolean isNewYear() {
        return isNewYear;
    }

    public void setNewYear(boolean isNewYear) {
        this.isNewYear = isNewYear;
    }

    public String getConstructPlace() {
        return constructPlace;
    }

    public void setConstructPlace(String constructPlace) {
        this.constructPlace = constructPlace;
    }

}
