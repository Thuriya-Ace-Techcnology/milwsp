package org.ace.insurance.common;

public enum PaymentGateway {

	OK$("OK$"),

	TWOC2P("TwoC2P"),
	
	MA("Master"),
	
	UP("Union"),
	
	CP("China Union"),
	
	JC("JCB"),
	
	VI("Visa");

	private String label;

	private PaymentGateway(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
