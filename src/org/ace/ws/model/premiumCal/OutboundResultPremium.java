package org.ace.ws.model.premiumCal;

public class OutboundResultPremium extends ResultPremium {

	private double tpaFee;
	private double agentCommission;
	public OutboundResultPremium() {}
	
	public OutboundResultPremium(String id, String name, double premium, double tpaFee, double agentCommission,double mainPremium) {
		super(id,name,premium,mainPremium);
		this.tpaFee = tpaFee;
		this.agentCommission = agentCommission;
	}
	
	public double getTpaFee() {
		return tpaFee;
	}
	public void setTpaFee(double tpaFee) {
		this.tpaFee = tpaFee;
	}
	public double getAgentCommission() {
		return agentCommission;
	}
	public void setAgentCommission(double agentCommission) {
		this.agentCommission = agentCommission;
	}
	
	
}
