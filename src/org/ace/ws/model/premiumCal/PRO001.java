package org.ace.ws.model.premiumCal;

import java.util.List;
import java.util.Map;

public class PRO001 {
	private String productId;
	private Map<String, String> keyFactorMap;
	private List<ADO001> addOnList;

	public PRO001() {
		super();
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Map<String, String> getKeyFactorMap() {
		return keyFactorMap;
	}

	public void setKeyFactorMap(Map<String, String> keyFactorMap) {
		this.keyFactorMap = keyFactorMap;
	}

	public List<ADO001> getAddOnList() {
		return addOnList;
	}

	public void setAddOnList(List<ADO001> addOnList) {
		this.addOnList = addOnList;
	}

}
