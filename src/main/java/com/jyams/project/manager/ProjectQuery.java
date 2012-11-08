package com.jyams.project.manager;

import com.jyams.project.model.Project;
import com.jyams.util.search.Query;
import com.jyams.util.search.SearchFilter;
import com.jyams.util.search.SqlOrder;

/**
 * 施工流程查询对象
 * @author zhanglong
 *
 */
public class ProjectQuery extends Query<Project> {

	private Long projectId;
	private Long projectIdStart;
	private Long projectIdEnd;
	private String projectName;
	private String companyPrincipalName;
	private String clientName;
	private String clientPrincipalName;
	private String constructPlace;
	private String quoteId;
	private String orderId;
	private Integer orderDate;
	private Integer requiredCompletionDate;
	private Integer canDelayDay;
	private String orderContent;

	public ProjectQuery() {
		super();
		init();
	}

	public ProjectQuery(int pageNo, int pageSize) {
		super(pageNo, pageSize);
		init();
	}

	public ProjectQuery(SearchFilter filter) {
		super(filter);
		init();
	}

	private void init() {
		//this.excludeStatus.add((short) BuildingProject.STATUS_HIDDEN);
		this.addOrder(new SqlOrder("projectId", false));
	}

	public Long getProjectId() {
		return projectId;
	}

	public ProjectQuery setProjectId(Long projectId) {
		this.projectId = projectId;
		return this;
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

	public String getCompanyPrincipalName() {
		return companyPrincipalName;
	}

	public ProjectQuery setCompanyPrincipalName(String companyPrincipalName) {
		this.companyPrincipalName = companyPrincipalName;
		return this;
	}

	public String getClientName() {
		return clientName;
	}

	public ProjectQuery setClientName(String clientName) {
		this.clientName = clientName;
		return this;
	}

	public String getClientPrincipalName() {
		return clientPrincipalName;
	}

	public ProjectQuery setClientPrincipalName(String clientPrincipalName) {
		this.clientPrincipalName = clientPrincipalName;
		return this;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getConstructPlace() {
		return constructPlace;
	}

	public void setConstructPlace(String constructPlace) {
		this.constructPlace = constructPlace;
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

	public Integer getRequiredCompletionDate() {
		return requiredCompletionDate;
	}

	public Integer getCanDelayDay() {
		return canDelayDay;
	}

	public String getOrderContent() {
		return orderContent;
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

	public void setRequiredCompletionDate(Integer requiredCompletionDate) {
		this.requiredCompletionDate = requiredCompletionDate;
	}

	public void setCanDelayDay(Integer canDelayDay) {
		this.canDelayDay = canDelayDay;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

}
