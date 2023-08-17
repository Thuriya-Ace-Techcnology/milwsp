package org.ace.ws.model.RelationShip;

import org.ace.insurance.system.common.relationship.RelationShip;

public class RelationShipDTO {
	private String id;
	private String prefix;
	private String name;
	private String description;
	public RelationShipDTO(RelationShip rs) {
		this.id = rs.getId();
		this.prefix = rs.getPrefix();
		this.name = rs.getName();
		this.description = rs.getDescription();
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
