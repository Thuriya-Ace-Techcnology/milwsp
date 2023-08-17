package org.ace.insurance.life.dao.entities;

import java.util.Date;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.ace.insurance.common.Gender;
import org.ace.insurance.common.IdType;
import org.ace.insurance.common.Name;
import org.ace.insurance.common.ResidentAddress;
import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.insurance.life.dto.AcquaintanceDTO;
import org.ace.insurance.system.common.currency.Currency;
import org.ace.insurance.system.common.relationship.RelationShip;
import org.ace.insurance.system.common.township.Township;
import org.ace.java.component.idgen.service.IDInterceptor;

@Entity
@Table(name = TableName.INSUREDPERSONACQUAINTANCE)
@TableGenerator(name = "INSUREDPERSONACQUAINTANCE_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "INSUREDPERSONACQUAINTANCE_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "Acquaintance.findAll", query = "SELECT a FROM Acquaintance a "),
		@NamedQuery(name = "Acquaintance.findById", query = "SELECT a FROM Acquaintance a WHERE a.id = :id") })
@EntityListeners(IDInterceptor.class)
public class Acquaintance {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "INSUREDPERSONACQUAINTANCE_GEN")
	private String id;
	
	private String name;	
	private String phone;
	private int acquaintanceYear;
	private String email;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSUREDPERSONID", referencedColumnName = "ID")
	private ProposalInsuredPerson proposalInsuredPerson;

	@Embedded
	private ResidentAddress residentAddress;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	public Acquaintance() {
	}

	public Acquaintance(AcquaintanceDTO dto) {
		this.residentAddress = dto.getResidentAddress();
		this.name = dto.getName();
		this.phone = dto.getPhone();
		this.email = dto.getEmail();
		this.acquaintanceYear = dto.getAcquaintanceYear(); 
		if (dto.isExistEntity()) {
			this.id = dto.getTempId();
			this.version = dto.getVersion();
		}
	}


	/**********************
	 * Generated Method
	 */

	public String getFullAddress() {
		String result = "";
		if (residentAddress != null) {
			if (residentAddress.getResidentAddress() != null && !residentAddress.getResidentAddress().isEmpty()) {
				result = result + residentAddress.getResidentAddress();
			}		
			if(residentAddress.getTownship()!= null) {
				if(residentAddress.getTownship().getFullTownShip() != null && residentAddress.getTownship().getFullTownShip() != "") {
					result = result + ", " + residentAddress.getTownship().getFullTownShip();
				}
				
			}

				
			
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;		
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + acquaintanceYear;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((residentAddress == null) ? 0 : residentAddress.hashCode());		
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());	
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acquaintance other = (Acquaintance) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (phone != other.phone)
			return false;
		if (acquaintanceYear != other.acquaintanceYear)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (residentAddress == null) {
			if (other.residentAddress != null)
				return false;
		} else if (!residentAddress.equals(other.residentAddress))
			return false;
		if (recorder == null) {
			if (other.recorder != null)
				return false;
		} else if (!recorder.equals(other.recorder))
			return false;
		if (version != other.version)
			return false;
		return true;
	}
	
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ResidentAddress getResidentAddress() {
		return residentAddress;
	}

	public void setResidentAddress(ResidentAddress residentAddress) {
		this.residentAddress = residentAddress;
	}

	public UserRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public ProposalInsuredPerson getProposalInsuredPerson() {
		return proposalInsuredPerson;
	}

	public void setProposalInsuredPerson(ProposalInsuredPerson proposalInsuredPerson) {
		this.proposalInsuredPerson = proposalInsuredPerson;
	}




}
