package org.ace.ws.model.premiumSeamen;

public class ResultSeamenPremium {

	private String id;
	private String name;
	private double premium;
	private String sumInsuredAmount;

	public ResultSeamenPremium(String id, String name, double premium, String sumInsuredAmount) {
		super();
		this.id = id;
		this.name = name;
		this.premium = premium;
		this.sumInsuredAmount = sumInsuredAmount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public String getSumInsuredAmount() {
		return sumInsuredAmount;
	}

	public void setSumInsuredAmount(String sumInsuredAmount) {
		this.sumInsuredAmount = sumInsuredAmount;
	}

}
