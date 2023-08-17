package org.ace.ws.model.premiumCal;

import org.ace.insurance.system.fire.buildingclass.ClassValue;

public class ClassValueDTO {

	String id;
	String floorId;
	String roofId;
	String wallId;
	String buildingClassId;

	public ClassValueDTO() {

	}

	public ClassValueDTO(ClassValue classVal) {
		this.id = classVal.getId();
		this.floorId = classVal.getFloor().getId();
		this.roofId = classVal.getRoof().getId();
		this.wallId = classVal.getWall().getId();
		this.buildingClassId = classVal.getBuildingClass().getId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	public String getRoofId() {
		return roofId;
	}

	public void setRoofId(String roofId) {
		this.roofId = roofId;
	}

	public String getWallId() {
		return wallId;
	}

	public void setWallId(String wallId) {
		this.wallId = wallId;
	}

	public String getBuildingClassId() {
		return buildingClassId;
	}

	public void setBuildingClassId(String buildingClassId) {
		this.buildingClassId = buildingClassId;
	}

}
