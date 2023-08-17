package org.ace.insurance.system.productTypeRecords.dto;

import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;

public class ProductTypeRecordsDTO {
private String id;
	
	private String twoCtwoPorderId;
	
	private String productType;

	public ProductTypeRecordsDTO(ProductTypeRecords records) {
		this.id = records.getId();
		this.twoCtwoPorderId = records.getTwoCtwoPorderId();
		this.productType = records.getProductType();
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTwoCtwoPorderId() {
		return twoCtwoPorderId;
	}

	public void setTwoCtwoPorderId(String twoCtwoPorderId) {
		this.twoCtwoPorderId = twoCtwoPorderId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	
}
