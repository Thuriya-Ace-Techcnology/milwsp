package org.ace.insurance.life.dto;


import org.ace.insurance.common.ResidentAddress;
import org.ace.insurance.life.dao.entities.Acquaintance;


public class AcquaintanceDTO {
	private boolean existEntity;
	private String name;
	private String phone;
	private int acquaintanceYear;
	private String email;
	private ResidentAddress residentAddress;
	private int version;
	private String tempId;

	


	public AcquaintanceDTO() {
		tempId = System.nanoTime() + "";
	}

	public AcquaintanceDTO(Acquaintance acquaintance) {
		if (acquaintance.getId() == null) {
			this.tempId = System.nanoTime() + "";
		} else {
			existEntity = true;
			this.tempId = acquaintance.getId();
			this.version = acquaintance.getVersion();
		}
		this.residentAddress = acquaintance.getResidentAddress();
		this.acquaintanceYear = acquaintance.getAcquaintanceYear();
		this.name = acquaintance.getName();
		this.phone = acquaintance.getPhone();
		this.email = acquaintance.getEmail();
	}

	public AcquaintanceDTO(AcquaintanceDTO acquaintance) {
		this.tempId = acquaintance.getTempId();
		this.existEntity = acquaintance.isExistEntity();
		this.version = acquaintance.getVersion();
		this.residentAddress = acquaintance.getResidentAddress();
		this.acquaintanceYear = acquaintance.getAcquaintanceYear();
		this.name = acquaintance.getName();
		this.phone = acquaintance.getPhone();
		this.email = acquaintance.getEmail();
	}





	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public ResidentAddress getResidentAddress() {
		if (residentAddress == null) {
			residentAddress = new ResidentAddress();
		}
		return residentAddress;
	}

	public void setResidentAddress(ResidentAddress residentAddress) {
		this.residentAddress = residentAddress;
	}
	public boolean isExistEntity() {
		return existEntity;
	}

	public void setExistEntity(boolean existEntity) {
		this.existEntity = existEntity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAcquaintanceYear() {
		return acquaintanceYear;
	}

	public void setAcquaintanceYear(int acquaintanceYear) {
		this.acquaintanceYear = acquaintanceYear;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	


}
