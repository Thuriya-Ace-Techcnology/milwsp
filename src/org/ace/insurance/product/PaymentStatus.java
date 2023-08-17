package org.ace.insurance.product;

public enum PaymentStatus {
	
	
	PREORDER("Pre Order"),
	SUCCESS("Success"),
	FAIL("Fail"),
	PENDING("Pending");
	
	private String label;
	
	
	private PaymentStatus(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	
	
}
