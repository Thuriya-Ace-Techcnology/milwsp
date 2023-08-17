package org.ace.insurance.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AgentType")
@XmlEnum
public enum AgentType {
	DIRECT_SALE("DIRECT_SALE"), AGENT("AGENT"),STAFF("STAFF"),CUSTOMERSERVICE("CUSTOMERSERVICE");

	private String label;

	private AgentType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
