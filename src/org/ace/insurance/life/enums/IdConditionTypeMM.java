package org.ace.insurance.life.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "IdConditionTypeMM")
@XmlEnum
public enum IdConditionTypeMM {
	A("ဧည့်"), C("စီ"), D("D"), E("E"), F("F"), G("G"), N("နိုင်"), P("ပြု"), TH("သ"), YA("ယာယီ") ;

	private String label;

	private IdConditionTypeMM(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
