package org.ace.insurance.specailForeignTravel;

public enum OutboundPercent {
	TPAFEE("TPAFee"), AGENTCOMMISSION("AgentCommission");

	private String label;

	private OutboundPercent(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
