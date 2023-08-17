package org.ace.ws.model.location;

import java.io.Serializable;

import org.ace.insurance.system.common.township.Township;
import org.ace.java.component.FormatID;

public class TownshipDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;	
	private String prefix ;
	private String name;
	private String shortName;
	private String code;
	private String description;
	private String districtId;
	/* private DistrictDTO district; */
	
	public TownshipDTO() {
		
	}
	
	public TownshipDTO(Township township) {
		this.id = township.getId();
		this.name = township.getName();
		this.shortName = township.getShortName();
		this.code = township.getCode();
		this.description = township.getDescription();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id != null) {
			this.id = FormatID.formatId(id, getPrefix(), 10);
		}
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	
}