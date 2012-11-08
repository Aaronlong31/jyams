package com.jyams.hr.model;

/**
 * 
 * @author zhanglong
 *
 */
public class Salary {

	protected long personId;
	protected String personName;
	protected float cost;

	public String getPersonId() {
		return personId + "";
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

}
