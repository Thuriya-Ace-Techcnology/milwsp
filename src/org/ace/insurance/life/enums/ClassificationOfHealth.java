package org.ace.insurance.life.enums;

public enum ClassificationOfHealth {
	FIRSTCLASS("FirstClass"),SECONDCLASS("SecondClass"), THIRDCLASS("ThirdClass");
	
	private String label;
	
	public String getLabel() {
		return label;
	}
	
	private ClassificationOfHealth(String label){
		this.label= label;
	}
	
}
