package org.ace.insurance.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "coveragePlan")
@XmlEnum
public enum CoveragePlan {

	Day5("5 Days"),
	Day10("10 Days"),
	Day15("15 Days"), 
	Day30("30 Days"),
	Day60("60 Days"),
	Day90("90 Days"),
	Day120("120 Days"),
	Day150("150 Days"),
	Day180("180 Days");
	
	private String label;

	private CoveragePlan(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
