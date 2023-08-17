/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.common.township;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.ace.insurance.common.TableName;
import org.ace.insurance.system.common.district.District;
import org.ace.insurance.system.common.province.Province;
import org.ace.java.component.FormatID;
import org.ace.ws.model.location.TownshipDTO;

@Entity
@Table(name = TableName.TOWNSHIP)
@TableGenerator(name = "TOWNSHIP_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "TOWNSHIP_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "Township.findAll", query = "SELECT t FROM Township t ORDER BY t.name ASC"),
		@NamedQuery(name = "Township.findById", query = "SELECT t FROM Township t WHERE t.id = :id") })
@Access(value = AccessType.FIELD)
public class Township implements Serializable {
	@Transient
	private String id;
	@Transient
	private String prefix;
	private String name;
	private String shortName;
	private String code;
	private String description;
	
    @OneToOne(fetch = FetchType.LAZY)	  
	@JoinColumn(name = "DISTRICTID", referencedColumnName = "ID") private
	District district;

	@Version
	private int version;

	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	private String createdUserId;
	private String updatedUserId;

	public Township() {
		//province = new Province();
	}
	
	public Township(TownshipDTO townshipDTO) {		
		this.id = townshipDTO.getId();
		this.name = townshipDTO.getName();
		this.shortName = townshipDTO.getShortName();
		this.code = townshipDTO.getCode();
		this.description = townshipDTO.getDescription();		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TOWNSHIP_GEN")
	@Access(value = AccessType.PROPERTY)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id != null) {
			this.id = FormatID.formatId(id, getPrefix(), 10);
		}
	}

	public String getName() {
		return this.name;
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

	
	public District getDistrict() { 
		return district; 	
	}
	  
	public void setDistrict(District district) { 
		this.district = district;
    }
	 
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	
   	 public String getFullTownShip() { 
   		 String fullAddress = name; 
   	     if (district != null && !district.getFullDistrict().isEmpty()) { 
   			 fullAddress = name + ", " +  district.getFullDistrict(); 
   			 }
   		 return fullAddress; 
   	}
	 

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());		
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
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
		Township other = (Township) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
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
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (version != other.version)
			return false;
		return true;
		
		
	}

}