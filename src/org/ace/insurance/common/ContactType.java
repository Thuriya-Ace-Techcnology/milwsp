package org.ace.insurance.common;

public enum ContactType {

	HEAD_BRANCH("Head Office"), REGIONAL_OFFICE("Regional Office"), BRANCH_OFFICE("Branch Office"), CUSTOMERSERVICE_KIOSK("CustomerService Kiosk");

	private String label;

	private ContactType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
