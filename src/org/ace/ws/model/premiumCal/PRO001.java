package org.ace.ws.model.premiumCal;

import java.util.List;
import java.util.Map;

public class PRO001 {
	private String productId;
	private Map<String, String> keyFactorMap;
	private List<ADO001> addOnList;
	private int periodTerm;
	private double sumInsured;
	private int unit;
	private String paymentType;
	private int feet;
	private int inches;
	private int weight;

	public PRO001() {
		super();
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Map<String, String> getKeyFactorMap() {
		return keyFactorMap;
	}

	public void setKeyFactorMap(Map<String, String> keyFactorMap) {
		this.keyFactorMap = keyFactorMap;
	}

	public List<ADO001> getAddOnList() {
		return addOnList;
	}

	public void setAddOnList(List<ADO001> addOnList) {
		this.addOnList = addOnList;
	}

	public int getPeriodTerm() {
		return periodTerm;
	}

	public void setPeriodTerm(int periodTerm) {
		this.periodTerm = periodTerm;
	}

	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public int getFeet() {
		return feet;
	}

	public void setFeet(int feet) {
		this.feet = feet;
	}

	public int getInches() {
		return inches;
	}

	public void setInches(int inches) {
		this.inches = inches;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}
