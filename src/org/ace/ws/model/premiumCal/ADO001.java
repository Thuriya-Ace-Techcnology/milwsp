package org.ace.ws.model.premiumCal;

import java.util.Map;

public class ADO001 {
	private String addOnId;
	private Map<String, String> keyFactorMap;
	private String value;

	public ADO001() {
		super();
	}

	public ADO001(String addOnId, String value) {
		super();
		this.addOnId = addOnId;
		this.value = value;
	}

	public String getAddOnId() {
		return addOnId;
	}

	public void setAddOnId(String addOnId) {
		this.addOnId = addOnId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Map<String, String> getKeyFactorMap() {
		return keyFactorMap;
	}

	public void setKeyFactorMap(Map<String, String> keyFactorMap) {
		this.keyFactorMap = keyFactorMap;
	}

}
