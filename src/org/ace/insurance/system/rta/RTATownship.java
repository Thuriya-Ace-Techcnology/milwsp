package org.ace.insurance.system.rta;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.ace.insurance.common.TableName;

@Entity
@Table(name = TableName.RTA_TOWNSHIP)
@TableGenerator(name = "RTA_TOWNSHIP_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "RTA_TOWNSHIP_GEN", allocationSize = 10)
@NamedQueries(value = {
		@NamedQuery(name = "RTATownship.findAll", query = "SELECT r FROM RTATownship r ORDER BY r.description ASC"),
		@NamedQuery(name = "RTATownship.findByCode", query = "SELECT r FROM RTATownship r WHERE r.code = :code or r.description = :code") })
@Access(value = AccessType.FIELD)
public class RTATownship {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RTA_TOWNSHIP_GEN")
	private String id;

	@Version
	private int version;

	@Column(name = "CODE")
	private String code;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "NRCCODE")
	private String nrcCode;

	@Column(name = "ENGSHORTNAME")
	private String engShortName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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

	public String getNrcCode() {
		return nrcCode;
	}

	public void setNrcCode(String nrcCode) {
		this.nrcCode = nrcCode;
	}

	public String getEngShortName() {
		return engShortName;
	}

	public void setEngShortName(String engShortName) {
		this.engShortName = engShortName;
	}

}
