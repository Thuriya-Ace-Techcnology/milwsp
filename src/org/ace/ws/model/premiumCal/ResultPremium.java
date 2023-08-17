package org.ace.ws.model.premiumCal;

public class ResultPremium {
	private String id;
	private String name;
	private double premium;
	private double mainPremium;
	public ResultPremium() {}

	public ResultPremium(String id, String name, double premium,double mainPremium) {
		super();
		this.id = id;
		this.name = name;
		this.premium = premium;
		this.mainPremium = mainPremium;
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

	public double getMainPremium() {
		return mainPremium;
	}

	public void setMainPremium(double mainPremium) {
		this.mainPremium = mainPremium;
	}

}
