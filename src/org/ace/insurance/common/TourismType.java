package org.ace.insurance.common;

public enum TourismType {
	OUTBOUND("Outbound"), INBOUND("Inbound"),DOMESTIC("Domestic");

	private String label;

	private TourismType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
