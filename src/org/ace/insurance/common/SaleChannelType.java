package org.ace.insurance.common;

public enum SaleChannelType {
	AGENT("Agent"), CUSTOMER_DIRECT("Customer_Direct"), WALKIN("Walkin"), DIRECTMARKETING("Direct Marketing");

	private String label;

	private SaleChannelType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
