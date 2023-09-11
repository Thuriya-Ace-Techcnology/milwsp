package org.ace.insurance.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "plans")
@XmlEnum
public enum Plans {
	
	Plan1("Plan1 10,000,000"), 
	Plan2("Plan1 20,000,000"),
	Plan3("Plan1 40,000,000"),
	Plan4("Plan1 60,000,000"),
	Plan5("Plan1 80,000,000"),
	Plan6("Plan1 100,000,000");
	
	private String label;

	private Plans(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
