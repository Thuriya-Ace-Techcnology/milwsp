package org.ace.ws.model.premiumCal;

public class ContentDTO {

	String id;
	String name;

	public ContentDTO() {

	}

	// public ContentDTO(Floor floor) {
	// this.id = floor.getId();
	// this.name = floor.getName();
	// }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
