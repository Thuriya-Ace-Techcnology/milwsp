package org.ace.insurance.common;

public enum ProposalStatus {
	PENDING("Pending"),

	PAYMENT_PENDING("PAYMENT_PENDING"),
	
	PAYMENT_EXPIRED("PAYMENT_EXPIRED"),
	
	ISSUED("Issued"),
	
	PROPOSAL_EXPIRED("PROPOSAL_EXPIRED"),

	PAYMENT_REJECT("PAYMENT_REJECT");

	private String label;

	private ProposalStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
