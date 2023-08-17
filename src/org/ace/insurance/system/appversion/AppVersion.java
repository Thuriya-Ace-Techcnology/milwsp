package org.ace.insurance.system.appversion;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.ace.insurance.common.TableName;
import org.eclipse.persistence.annotations.ReadOnly;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@ReadOnly
@Table(name = TableName.APPVERSION)
@NamedQueries(value = {
		@NamedQuery(name = "AppVersion.updateByAppVersion", query = "UPDATE AppVersion a SET a.appVersion = :appVersion where a.type = :type") })
public class AppVersion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@JsonIgnore
	private String id;
	private String appVersion;
	@Enumerated(EnumType.STRING)
	private MobileType type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public MobileType getType() {
		return type;
	}

	public void setType(MobileType type) {
		this.type = type;
	}

}
