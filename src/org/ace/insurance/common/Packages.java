package org.ace.insurance.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "packages")
@XmlEnum
public enum Packages {

	USD10000("USD 10,000"), 
	USD30000("USD 30,000"),
	USD50000("USD 50,000");
	
	private String label;

	private Packages(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
