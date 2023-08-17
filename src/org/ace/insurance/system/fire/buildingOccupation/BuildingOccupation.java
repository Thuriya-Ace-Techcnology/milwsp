package org.ace.insurance.system.fire.buildingOccupation;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.ace.insurance.common.TableName;
import org.ace.java.component.FormatID;

@Entity
@Table(name = TableName.BUILDINGOCCUPATION)
@TableGenerator(name = "BUILDINGOCCUPATION_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "BUILDINGOCCUPATION_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "BuildingOccupation.findAll", query = "SELECT o FROM BuildingOccupation o ORDER BY o.name ASC"),
		@NamedQuery(name = "BuildingOccupation.findById", query = "SELECT o FROM BuildingOccupation o WHERE o.id = :id") })
@Access(value = AccessType.FIELD)
public class BuildingOccupation implements Serializable {
	@Transient
	private String id;
	@Transient
	private String prefix;

	private String name;
	private String buildingOccupationType;
	private String description;

	@Version
	private int version;

	public BuildingOccupation() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "OCCUPATION_GEN")
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

	public void setName(String month) {
		this.name = month;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBuildingOccupationType() {
		return buildingOccupationType;
	}

	public void setBuildingOccupationType(String buildingOccupationType) {
		this.buildingOccupationType = buildingOccupationType;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buildingOccupationType == null) ? 0 : buildingOccupationType.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		BuildingOccupation other = (BuildingOccupation) obj;
		if (buildingOccupationType == null) {
			if (other.buildingOccupationType != null)
				return false;
		} else if (!buildingOccupationType.equals(other.buildingOccupationType))
			return false;
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