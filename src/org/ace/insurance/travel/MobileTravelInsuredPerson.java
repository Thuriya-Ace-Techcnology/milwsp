package org.ace.insurance.travel;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.ace.insurance.common.IdType;
import org.ace.insurance.common.TableName;
import org.ace.insurance.common.Utils;
import org.ace.java.component.FormatID;
import org.ace.ws.model.mobiletravelproposal.MIP001;

@Entity
@Table(name = TableName.MOBILE_TRAVEL_INSURED_PERSON)
@TableGenerator(name = "MOBILE_TRAVEL_INSURED_PERSON_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "MOBILE_TRAVEL_INSURED_PERSON_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "MobileTravelInsuredPerson.findAll", query = "SELECT m FROM MobileTravelInsuredPerson m ORDER BY m.firstName ASC"),
		@NamedQuery(name = "MobileTravelInsuredPerson.findById", query = "SELECT m FROM MobileTravelInsuredPerson m WHERE m.id = :id") })
@Access(value = AccessType.FIELD)
public class MobileTravelInsuredPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient
	private String id;
	@Transient
	private String prefix;
	private String firstName;
	private String lastName;
	private String idNo;
	@Temporal(TemporalType.DATE)
	private Date departureDate;
	@Temporal(TemporalType.DATE)
	private Date arrivalDate;
	private String route;
	private int unit;
	private double premium;
	@Version
	private int version;
	private int overSeaAmount;
	private boolean flightCover;
	private int flightCoverAmount;
	private String fatherName;
	
	@Enumerated(EnumType.STRING)
	private IdType idType;
	
	private boolean govPerson;
	private String govDate;
	private String govRefNo;
	private String govDepart;
	
	public MobileTravelInsuredPerson() {

	}

	public MobileTravelInsuredPerson(MIP001 mip001) {
		this.id = mip001.getId();
		this.firstName = mip001.getFirstName();
		this.lastName = mip001.getLastName();
		this.idNo = mip001.getIdNo();
		this.departureDate = Utils.getDate(mip001.getDepartureDate());
		this.arrivalDate = mip001.getArrivalDate() == null ? null : Utils.getDate(mip001.getArrivalDate());
		this.route = mip001.getRoute();
		this.unit = mip001.getUnit();
		this.premium = mip001.getPremium();
		this.overSeaAmount = mip001.getOverSeaAmount();
		this.flightCover = mip001.isFlightCover();
		this.flightCoverAmount = mip001.getFlightCoverAmount();
		this.idType = mip001.getIdType();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MOBILE_TRAVEL_INSURED_PERSON_GEN")
	@Access(value = AccessType.PROPERTY)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id != null) {
			this.id = FormatID.formatId(id, getPrefix(), 10);
		}
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getOverSeaAmount() {
		return overSeaAmount;
	}

	public void setOverSeaAmount(int overSeaAmount) {
		this.overSeaAmount = overSeaAmount;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public IdType getIdType() {
		return idType;
	}

	public void setIdType(IdType idType) {
		this.idType = idType;
	}

	/**
	 * @return the idNo
	 */
	public String getIdNo() {
		return idNo;
	}

	/**
	 * @param idNo
	 *            the idNo to set
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	/**
	 * @return the departureDate
	 */
	public Date getDepartureDate() {
		return departureDate;
	}

	/**
	 * @param departureDate
	 *            the departureDate to set
	 */
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	/**
	 * @return the arrivalDate
	 */
	public Date getArrivalDate() {
		return arrivalDate;
	}

	/**
	 * @param arrivalDate
	 *            the arrivalDate to set
	 */
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	/**
	 * @return the route
	 */
	public String getRoute() {
		return route;
	}

	/**
	 * @param route
	 *            the route to set
	 */
	public void setRoute(String route) {
		this.route = route;
	}

	/**
	 * @return the unit
	 */
	public int getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(int unit) {
		this.unit = unit;
	}

	/**
	 * @return the premium
	 */
	public double getPremium() {
		return premium;
	}

	/**
	 * @param premium
	 *            the premium to set
	 */
	public void setPremium(double premium) {
		this.premium = premium;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isFlightCover() {
		return flightCover;
	}

	public void setFlightCover(boolean flightCover) {
		this.flightCover = flightCover;
	}

	public int getFlightCoverAmount() {
		return flightCoverAmount;
	}

	public void setFlightCoverAmount(int flightCoverAmount) {
		this.flightCoverAmount = flightCoverAmount;
	}

	public boolean isGovPerson() {
		return govPerson;
	}

	public void setGovPerson(boolean govPerson) {
		this.govPerson = govPerson;
	}

	public String getGovDate() {
		return govDate;
	}

	public void setGovDate(String govDate) {
		this.govDate = govDate;
	}

	public String getGovRefNo() {
		return govRefNo;
	}

	public void setGovRefNo(String govRefNo) {
		this.govRefNo = govRefNo;
	}

	public String getGovDepart() {
		return govDepart;
	}

	public void setGovDepart(String govDepart) {
		this.govDepart = govDepart;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalDate == null) ? 0 : arrivalDate.hashCode());
		result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
		result = prime * result + ((fatherName == null) ? 0 : fatherName.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (flightCover ? 1231 : 1237);
		result = prime * result + flightCoverAmount;
		result = prime * result + ((govDate == null) ? 0 : govDate.hashCode());
		result = prime * result + ((govDepart == null) ? 0 : govDepart.hashCode());
		result = prime * result + (govPerson ? 1231 : 1237);
		result = prime * result + ((govRefNo == null) ? 0 : govRefNo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idNo == null) ? 0 : idNo.hashCode());
		result = prime * result + ((idType == null) ? 0 : idType.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + overSeaAmount;
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		long temp;
		temp = Double.doubleToLongBits(premium);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((route == null) ? 0 : route.hashCode());
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
		MobileTravelInsuredPerson other = (MobileTravelInsuredPerson) obj;
		if (arrivalDate == null) {
			if (other.arrivalDate != null)
				return false;
		} else if (!arrivalDate.equals(other.arrivalDate))
			return false;
		if (departureDate == null) {
			if (other.departureDate != null)
				return false;
		} else if (!departureDate.equals(other.departureDate))
			return false;
		if (fatherName == null) {
			if (other.fatherName != null)
				return false;
		} else if (!fatherName.equals(other.fatherName))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (flightCover != other.flightCover)
			return false;
		if (flightCoverAmount != other.flightCoverAmount)
			return false;
		if (govDate == null) {
			if (other.govDate != null)
				return false;
		} else if (!govDate.equals(other.govDate))
			return false;
		if (govDepart == null) {
			if (other.govDepart != null)
				return false;
		} else if (!govDepart.equals(other.govDepart))
			return false;
		if (govPerson != other.govPerson)
			return false;
		if (govRefNo == null) {
			if (other.govRefNo != null)
				return false;
		} else if (!govRefNo.equals(other.govRefNo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idNo == null) {
			if (other.idNo != null)
				return false;
		} else if (!idNo.equals(other.idNo))
			return false;
		if (idType != other.idType)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (overSeaAmount != other.overSeaAmount)
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (Double.doubleToLongBits(premium) != Double.doubleToLongBits(other.premium))
			return false;
		if (route == null) {
			if (other.route != null)
				return false;
		} else if (!route.equals(other.route))
			return false;
		if (unit != other.unit)
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	
	
}
