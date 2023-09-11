/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.agent;
import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.Version;


import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.insurance.product.Product;



import org.ace.java.component.idgen.service.IDInterceptor;

@Entity
@Table(name = TableName.OUTBOUND_ASSOCIATION_AGENT)
@TableGenerator(name = "OUTBOUND_ASSOCIATION_AGENT_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "OUTBOUND_ASSOCIATION_AGENT_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "OutboundAssociationAgent.findAll", query = "SELECT a FROM OutboundAssociationAgent a ORDER BY a.name ASC"),
		@NamedQuery(name = "OutboundAssociationAgent.findById", query = "SELECT a FROM OutboundAssociationAgent a WHERE a.id = :id") })
@EntityListeners(IDInterceptor.class)
public class OutboundAssociationAgent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "OUTBOUND_ASSOCIATION_AGENT_GEN")
	private String id;
	private String name;
	private String licenceNo;	
	private String password;
	private String associationName;
	private String phoneNo;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	private String address;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "AUTHORIZE_PRODUCT_LINK", joinColumns = { @JoinColumn(name = "AUTHORIZEID", referencedColumnName = "ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PRODUCTID", referencedColumnName = "ID") })
	private List<Product> productList;


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((licenceNo == null) ? 0 : licenceNo.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((associationName == null) ? 0 : associationName.hashCode());
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());	
		result = prime * result + ((address == null) ? 0 : address.hashCode());	
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
		OutboundAssociationAgent other = (OutboundAssociationAgent) obj;
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
		if (licenceNo == null) {
			if (other.licenceNo != null)
				return false;
		} else if (!licenceNo.equals(other.licenceNo))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (associationName == null) {
			if (other.associationName != null)
				return false;
		} else if (!associationName.equals(other.associationName))
			return false;
		if (phoneNo == null) {
			if (other.phoneNo != null)
				return false;
		} else if (!phoneNo.equals(other.phoneNo))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
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

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAssociationName() {
		return associationName;
	}

	public void setAssociationName(String associationName) {
		this.associationName = associationName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

	public UserRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
	}
	
	@Override
	public String toString() {
		return name ;
	}
	

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}



}