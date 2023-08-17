package org.ace.insurance.life.enums;

public enum CancellationReason {

	OPERATOR_FAULT("Operator's faults"), 
	CUSTOMER_REQUEST("Customer's Request ");
	

	private String label;

	private CancellationReason(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
