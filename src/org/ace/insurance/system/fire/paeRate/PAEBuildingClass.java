package org.ace.insurance.system.fire.paeRate;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import javax.persistence.Version;

import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.insurance.system.fire.buildingclass.BuildingClass;
import org.ace.java.component.idgen.service.IDInterceptor;

@Entity
@Table(name = TableName.PAEBUILDINGCLASS)
@TableGenerator(name = "PAEBUILDINGCLASS_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "PAEBUILDINGCLASS_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "PAEBuildingClass.findAll", query = "SELECT p FROM PAEBuildingClass p ORDER BY p.name ASC"),
		@NamedQuery(name = "PAEBuildingClass.findById", query = "SELECT p FROM PAEBuildingClass p WHERE p.id = :id") })
@EntityListeners(IDInterceptor.class)
public class PAEBuildingClass implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PAEBUILDINGCLASS_GEN")
	private String id;
	private String name;
	private int fromAge;
	private int toAge;
	private float paeRate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BUILDINGCLASSID", referencedColumnName = "ID")
	private BuildingClass buildingClass;

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;

	public PAEBuildingClass() {
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

	public int getFromAge() {
		return fromAge;
	}

	public void setFromAge(int fromAge) {
		this.fromAge = fromAge;
	}

	public int getToAge() {
		return toAge;
	}

	public void setToAge(int toAge) {
		this.toAge = toAge;
	}

	public float getPaeRate() {
		return paeRate;
	}

	public void setPaeRate(float paeRate) {
		this.paeRate = paeRate;
	}

	public BuildingClass getBuildingClass() {
		return buildingClass;
	}

	public void setBuildingClass(BuildingClass buildingClass) {
		this.buildingClass = buildingClass;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buildingClass == null) ? 0 : buildingClass.hashCode());
		result = prime * result + fromAge;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(paeRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
		result = prime * result + toAge;
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
		PAEBuildingClass other = (PAEBuildingClass) obj;
		if (buildingClass == null) {
			if (other.buildingClass != null)
				return false;
		} else if (!buildingClass.equals(other.buildingClass))
			return false;
		if (fromAge != other.fromAge)
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
		if (Double.doubleToLongBits(paeRate) != Double.doubleToLongBits(other.paeRate))
			return false;
		if (recorder == null) {
			if (other.recorder != null)
				return false;
		} else if (!recorder.equals(other.recorder))
			return false;
		if (toAge != other.toAge)
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}
