package org.ace.ws.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.ace.insurance.common.TableName;

@Entity
@Table(name = TableName.MOBILE_COUNTRY)
@NamedQueries(value = { @NamedQuery(name = "MobileCountry.findAll", query = "SELECT m FROM MobileCountry m"),
		@NamedQuery(name = "MobileCountry.findByCountryCode", query = "SELECT m.countryName FROM MobileCountry m where m.shortPhoneNo = :countryCode")
})
public class MobileCountry implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String countryName;
	private String shortTwoCode;
	private String shortThreeCode;
	private String shortPhoneNo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getShortTwoCode() {
		return shortTwoCode;
	}
	public void setShortTwoCode(String shortTwoCode) {
		this.shortTwoCode = shortTwoCode;
	}
	public String getShortThreeCode() {
		return shortThreeCode;
	}
	public void setShortThreeCode(String shortThreeCode) {
		this.shortThreeCode = shortThreeCode;
	}
	public String getShortPhoneNo() {
		return shortPhoneNo;
	}
	public void setShortPhoneNo(String shortPhoneNo) {
		this.shortPhoneNo = shortPhoneNo;
	}
	

	
}
