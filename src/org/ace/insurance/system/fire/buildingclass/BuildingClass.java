/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.fire.buildingclass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.ace.insurance.common.TableName;
import org.ace.java.component.FormatID;

@Entity
@Table(name = TableName.BUILDINGCLASS)
@TableGenerator(name = "BUILDINGCLASS_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "BUILDINGCLASS_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "BuildingClass.findAll", query = "SELECT b FROM BuildingClass b ORDER BY b.name ASC"),
		@NamedQuery(name = "BuildingClass.findById", query = "SELECT b FROM BuildingClass b WHERE b.id = :id"),
		@NamedQuery(name = "BuildingClass.findByRoofWallFloor", query = "SELECT b FROM BuildingClass b JOIN b.classValueList cv WHERE cv.roof = :roof AND cv.wall = :wall AND cv.floor = :floor"),
		@NamedQuery(name = "BuildingClass.findByRoofWallFloorId", query = "SELECT b FROM BuildingClass b JOIN b.classValueList cv WHERE cv.roof.id = :roof AND cv.wall.id = :wall AND cv.floor.id = :floor") })
@Access(value = AccessType.FIELD)
public class BuildingClass implements Serializable {
	@Transient
	private String id;
	@Transient
	private String prefix;
	private String name;

	private int distance;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "buildingClass", orphanRemoval = true)
	private List<ClassValue> classValueList;

	@Version
	private int version;

	public BuildingClass() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BUILDINGCLASS_GEN")
	@Access(value = AccessType.PROPERTY)
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
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDistance() {
		return this.distance;
	}

	public void setDistance(int range) {
		this.distance = range;
	}

	public List<ClassValue> getClassValueList() {
		return this.classValueList;
	}

	public void setClassValueList(List<ClassValue> classValueList) {
		this.classValueList = classValueList;
	}

	public void addClassValue(ClassValue classValue) {
		if (classValueList == null) {
			classValueList = new ArrayList<ClassValue>();
		}
		classValue.setBuildingClass(this);
		classValueList.add(classValue);
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + distance;
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
		BuildingClass other = (BuildingClass) obj;
		if (distance != other.distance)
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