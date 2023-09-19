package org.ace.insurance.life.dto;

import org.ace.insurance.common.plans.Plans;

public class PlansDTO {
	
	private String id;
	private String planType;
	private double SI;
	private String description;
	
	public PlansDTO(Plans plans) {
		this.id = plans.getId();
		this.planType = plans.getPlanType();
		this.SI = plans.getSI();
		this.description = plans.getDescription();
		
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public double getSI() {
		return SI;
	}
	public void setSI(double sI) {
		SI = sI;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
