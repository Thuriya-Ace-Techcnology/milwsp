package org.ace.insurance.life.enums;

public enum WorkFlowType {
	PUBLIC_TERM_LIFE("PUBLIC_TERM_LIFE"),

	STUDENT_LIFE("STUDENT_LIFE"),

	LIFE("Life"),

	FIRE("Fire"),

	MOTOR("Motor"),

	AGENT_COMMISSION("Agent Commission"),

	COINSURANCE("Co-insurance"),

	CASH_IN_SAFE("Cash In Safe"),

	FEDILITY("Fidelity"),

	CASHIN_TRANSIT("Cash In Transit"),

	SNAKE_BITE("Snake Bite"),

	MEDICAL_INSURANCE("Medical"),

	SPECIAL_TRAVEL("Special Travel"),

	CARGO("Cargo"),

	OVERSEASCARGO("OverseasCargo"),

	LIFESURRENDER("Life Surrender"),

	LIFE_PAIDUP("Life PaidUp"),

	PERSONAL_ACCIDENT("Personal Accident"),

	SHORT_ENDOWMENT("Short Term Endowment Life"),
	
	GOVERNMENT_SHORT_ENDOWMENT("Government Short Term Endowment Life"),
	
	GOVERNMENT_PERSONNEL_LIFE_ASSURANCE("Government Personnel Life"),
	
	SEAMEN("SeaMen"),
	
	SHOREJOB("Shore Job Life"),
	
	SINGLE_PREMIUM_ENDOWMENT("Single Premium Endowment Life"),
	
	SINGLE_PREMIUM_CREDIT("Single Premium Credit Life"),
	
	SHORT_TERM_SINGLE_PREMIUM_CREDIT("Short Term Single Premium Credit Life"),

	TRAVEL("Travel"),

	FARMER("Farmer"),

	MARINEHULL("Marine Hull"),

	REINSURANCE("Re-insurance"),
	
	OUTBOUND_TRAVEL("Outbound Travel");

	private String label;

	private WorkFlowType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
