package org.ace.insurance.thirdPartyDriverLicense;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;

@Entity
@Table(name = TableName.TYPE_OF_DRIVER)
@NamedQueries(value = {
		@NamedQuery(name = "TypeOfDriver.findAll", query = "SELECT d FROM TypeOfDriver d ORDER BY d.name ASC"),
		@NamedQuery(name = "TypeOfDriver.findById", query = "SELECT d FROM TypeOfDriver d WHERE d.id = :id"),
		@NamedQuery(name = "TypeOfDriver.findPremiumRateById", query = "SELECT d.premiumRate FROM TypeOfDriver d WHERE d.id = :id"),
		@NamedQuery(name = "TypeOfDriver.findYearById", query = "SELECT d.year FROM TypeOfDriver d WHERE d.id = :id") })
public class TypeOfDriver implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;
	private int year;
	private double premiumRate;

	@Embedded
	private UserRecorder userRecorder;

	@Version
	private int version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPremiumRate() {
		return premiumRate;
	}

	public void setPremiumRate(double premiumRate) {
		this.premiumRate = premiumRate;
	}

	public UserRecorder getUserRecorder() {
		return userRecorder;
	}

	public void setUserRecorder(UserRecorder userRecorder) {
		this.userRecorder = userRecorder;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
