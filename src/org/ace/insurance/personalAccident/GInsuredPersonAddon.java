package org.ace.insurance.personalAccident;

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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.ace.insurance.common.TableName;
import org.ace.java.component.FormatID;
import org.ace.ws.model.mobilePersonalAccidentproposal.MIPA001;

@Entity
@Table(name = TableName.GINSUREDPERSONADDON)
@TableGenerator(name = "INSUREDPERSONADDON_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "INSUREDPERSONADDON_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "InsuredPersonAddon.findAll", query = "SELECT v FROM InsuredPersonAddon v "),
		@NamedQuery(name = "InsuredPersonAddon.findById", query = "SELECT v FROM InsuredPersonAddon v WHERE v.id = :id") })
@Access(value = AccessType.FIELD)
public class GInsuredPersonAddon {
	private String id;
	private String prefix;

	private double premium;
	private double sumInsured;
	private String addOnId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MOBILEPROPOSALINSUREDPERSONID", referencedColumnName = "ID")
	private MobilePersonalAccidentInsuredPerson mobileProposalInsuredPerson;

	@Version
	private int version;

	public GInsuredPersonAddon() {
	}

	public GInsuredPersonAddon(MIPA001 dto) {
		this.id = dto.getId();
		this.addOnId = dto.getAddOnId();
		this.sumInsured = dto.getSumInsured();
		this.premium = dto.getPremium();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "INSUREDPERSONADDON_GEN")
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public MobilePersonalAccidentInsuredPerson getMobileProposalInsuredPerson() {
		return mobileProposalInsuredPerson;
	}

	public void setMobileProposalInsuredPerson(MobilePersonalAccidentInsuredPerson mobileProposalInsuredPerson) {
		this.mobileProposalInsuredPerson = mobileProposalInsuredPerson;
	}

	public String getAddOnId() {
		return addOnId;
	}

	public void setAddOnId(String addOnId) {
		this.addOnId = addOnId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addOnId == null) ? 0 : addOnId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mobileProposalInsuredPerson == null) ? 0 : mobileProposalInsuredPerson.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		long temp;
		temp = Double.doubleToLongBits(premium);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(sumInsured);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		GInsuredPersonAddon other = (GInsuredPersonAddon) obj;
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
		if (mobileProposalInsuredPerson == null) {
			if (other.mobileProposalInsuredPerson != null)
				return false;
		} else if (!mobileProposalInsuredPerson.equals(other.mobileProposalInsuredPerson))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (Double.doubleToLongBits(premium) != Double.doubleToLongBits(other.premium))
			return false;
		if (Double.doubleToLongBits(sumInsured) != Double.doubleToLongBits(other.sumInsured))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}
