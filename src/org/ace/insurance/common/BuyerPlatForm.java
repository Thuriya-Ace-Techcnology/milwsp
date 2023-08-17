package org.ace.insurance.common;

public enum BuyerPlatForm {
	WEBSITE("Website"),INAPP("InApp"), WALLET("Wallet"),OTHERS("Others");

	private String label;

	private BuyerPlatForm(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
