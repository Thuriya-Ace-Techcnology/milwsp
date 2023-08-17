package org.ace.insurance.medical;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.java.component.idgen.service.IDInterceptor;
import org.ace.ws.model.mobileMedicalproposal.MedicalAddOnDTO;

@Entity
@Table(name = TableName.MOBILE_MEDICALPROPOSALINSUREDPERSONADDON)
@TableGenerator(name = "MEDINSUREDPERSONADDON_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "MEDINSUREDPERSONADDON_GEN", allocationSize = 10)
@EntityListeners(IDInterceptor.class)
public class MobileMedicalProposalInsuredPersonAddOn {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MEDINSUREDPERSONADDON_GEN")
	private String id;

	private int unit;
	private double sumInsured;
	private double premium;
	private String addOnId;

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;

	public MobileMedicalProposalInsuredPersonAddOn() {
	}

	public MobileMedicalProposalInsuredPersonAddOn(MedicalAddOnDTO addOn) {
		this.id = addOn.getId();
		this.premium = addOn.getPremium();
		this.sumInsured = addOn.getSumInsured();
		this.addOnId = addOn.getAddOnId();
		this.unit = addOn.getUnit();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public String getAddOnId() {
		return addOnId;
	}

	public void setAddOnId(String addOnId) {
		this.addOnId = addOnId;
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
		result = prime * result + ((addOnId == null) ? 0 : addOnId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(premium);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
		temp = Double.doubleToLongBits(sumInsured);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + unit;
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
		MobileMedicalProposalInsuredPersonAddOn other = (MobileMedicalProposalInsuredPersonAddOn) obj;
		if (addOnId == null) {
			if (other.addOnId != null)
				return false;
		} else if (!addOnId.equals(other.addOnId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(premium) != Double.doubleToLongBits(other.premium))
			return false;
		if (recorder == null) {
			if (other.recorder != null)
				return false;
		} else if (!recorder.equals(other.recorder))
			return false;
		if (Double.doubleToLongBits(sumInsured) != Double.doubleToLongBits(other.sumInsured))
			return false;
		if (unit != other.unit)
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}
