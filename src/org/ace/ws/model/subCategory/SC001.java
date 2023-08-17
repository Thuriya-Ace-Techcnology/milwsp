package org.ace.ws.model.subCategory;

import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.system.productinformation.subcategory.SubCategory;
import org.ace.insurance.system.productinformation.text.NameText;
import org.ace.ws.model.nameText.NT001;

public class SC001 {

	private String id;
	private int order;
	private int version;
	private List<NT001> textList;

	public SC001() {
	}

	public SC001(SubCategory subCategory) {
		id = subCategory.getId();
		order = subCategory.getOrder();
		version = subCategory.getVersion();
		textList = new ArrayList<>();
		for (NameText nameText : subCategory.getTextList()) {
			NT001 nameText001 = new NT001(nameText);
			textList.add(nameText001);
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

	public List<NT001> getTextList() {
		return textList;
	}

	public void setTextList(List<NT001> textList) {
		this.textList = textList;
	}

}
