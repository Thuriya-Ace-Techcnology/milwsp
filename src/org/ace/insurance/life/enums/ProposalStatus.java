package org.ace.insurance.life.enums;

public enum ProposalStatus {
	PENDING("PENDING"),

	PAYMENT_PENDING("PAYMENT_PENDING"),

	PAYMENT_EXPIRED("PAYMENT_EXPIRED"),

	ISSUED("ISSUED"),

	PROPOSAL_EXPIRED("PROPOSAL_EXPIRED"),

	PAYMENT_REJECT("PAYMENT_REJECT"),
	
	INVALID("INVALID"),
	
	DENY("Deny"),
	
	UNDERWRITING("UNDERWRITING"),
	
	CANCEL_PENDING("CANCEL_PENDING"),
	
	CANCEL_COMPLETE("CANCEL_COMPLETE"),
	
	TERMINATE("TERMINATE");
	
	private String label;

	private ProposalStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
