package org.ace.ws.model.thirdParty;

import java.util.List;

public class TestingDTO {
	private String name;
	private List<Insured> insured;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Insured> getInsured() {
		return insured;
	}

	public void setInsured(List<Insured> insured) {
		this.insured = insured;
	}
	
}
