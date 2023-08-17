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

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
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
import javax.persistence.Transient;
import javax.persistence.Version;

import org.ace.insurance.common.TableName;
import org.ace.insurance.system.fire.floor.Floor;
import org.ace.insurance.system.fire.roof.Roof;
import org.ace.insurance.system.fire.wall.Wall;
import org.ace.java.component.FormatID;

@Entity
@Table(name = TableName.CLASSVALUE)
@TableGenerator(name = "CLASSVALUE_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "CLASSVALUE_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "ClassValue.findAll", query = "SELECT c FROM ClassValue c "),
		@NamedQuery(name = "ClassValue.findById", query = "SELECT c FROM ClassValue c WHERE c.id = :id") })
@Access(value = AccessType.FIELD)
public class ClassValue implements Serializable {
	@Transient
	private String id;
	@Transient
	private String prefix;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROOFID", referencedColumnName = "ID")
	private Roof roof;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WALLID", referencedColumnName = "ID")
	private Wall wall;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLOORID", referencedColumnName = "ID")
	private Floor floor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUILDINGCLASSID", referencedColumnName = "ID")
	private BuildingClass buildingClass;

	@Version
	private int version;

	public ClassValue() {
	}

	public ClassValue(Roof roof, Wall wall, Floor floor) {
		this.roof = roof;
		this.wall = wall;
		this.floor = floor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CLASSVALUE_GEN")
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

	public Roof getRoof() {
		return this.roof;
	}

	public void setRoof(Roof roof) {
		this.roof = roof;
	}

	public Wall getWall() {
		return this.wall;
	}

	public void setWall(Wall wall) {
		this.wall = wall;
	}

	public Floor getFloor() {
		return this.floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public BuildingClass getBuildingClass() {
		return this.buildingClass;
	}

	public void setBuildingClass(BuildingClass buildingClass) {
		this.buildingClass = buildingClass;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ClassValue other = (ClassValue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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