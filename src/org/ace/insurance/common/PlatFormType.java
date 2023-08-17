package org.ace.insurance.common;

public enum PlatFormType {
	MOBILE("MOBILE"),
	CORE("CORE");
	
	private String label;

	private PlatFormType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
