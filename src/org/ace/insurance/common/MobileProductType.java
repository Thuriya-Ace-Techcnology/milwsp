package org.ace.insurance.common;

public enum MobileProductType {
	THIRDPARTY("THIRDPARTY"), PA("PA"), SPECAILFOREIGNTRAVELLER("SpecailForeignTraveller"),OUTBOUNDSPECAILFOREIGNTRAVELLER("OutboundSpecailForeignTraveller"),
	THIRDPARTYDRIVER("THIRDPARTYDRIVER");

	private String label;

	private MobileProductType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
