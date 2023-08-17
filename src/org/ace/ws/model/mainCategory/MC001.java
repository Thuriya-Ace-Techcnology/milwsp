package org.ace.ws.model.mainCategory;

import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.system.productinformation.maincategory.MainCategory;
import org.ace.insurance.system.productinformation.subcategory.SubCategory;
import org.ace.insurance.system.productinformation.text.NameText;
import org.ace.ws.model.nameText.NT001;
import org.ace.ws.model.subCategory.SC001;

public class MC001 {

	private String id;
	private String productId;
	private int order;
	private int version;
	private List<NT001> textList;
	private List<SC001> subCategoryList;

	public MC001() {
	}

	public MC001(MainCategory m) {
		id = m.getId();
		order = m.getOrder();
		version = m.getVersion();
		productId = m.getProduct().getId();
		textList = new ArrayList<>();
		for (NameText text : m.getTextList()) {
			NT001 nameText001 = new NT001(text);
			textList.add(nameText001);
		}
		subCategoryList = new ArrayList<>();
		for (SubCategory subCategory : m.getSubCategoryList()) {
			SC001 subCategory001 = new SC001(subCategory);
			subCategoryList.add(subCategory001);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public List<NT001> getTextList() {
		return textList;
	}

	public void setTextList(List<NT001> textList) {
		this.textList = textList;
	}

	public List<SC001> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(List<SC001> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

}
