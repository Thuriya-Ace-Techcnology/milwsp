package org.ace.ws.model.mobiletravelproposal;

import org.ace.insurance.common.IdType;
import org.ace.insurance.common.Utils;
import org.ace.insurance.travel.MobileTravelInsuredPerson;

public class MIP001 {
	private String id;
	private String firstName;
	private String lastName;
	private IdType idType;
	private String idNo;
	private String departureDate;
	private String arrivalDate;
	private String route;
	private int unit;
	private double premium;
	private int overSeaAmount;
	private boolean flightCover;
	private int flightCoverAmount;
	private boolean govPerson;
	private String govDate;
	private String govRefNo;
	private String govDepart;
	
	public MIP001() {
	}

	public MIP001(MobileTravelInsuredPerson insuredPerson) {
		this.id = insuredPerson.getId();
		this.firstName = insuredPerson.getFirstName();
		this.lastName = insuredPerson.getLastName();
		this.idNo = insuredPerson.getIdNo();
		this.departureDate = Utils.getDateFormatString(insuredPerson.getDepartureDate());
		this.arrivalDate = Utils.getDateFormatString(insuredPerson.getArrivalDate());
		this.route = insuredPerson.getRoute();
		this.unit = insuredPerson.getUnit();
		this.premium = insuredPerson.getPremium();
		this.overSeaAmount = insuredPerson.getOverSeaAmount();
		this.idType = insuredPerson.getIdType();
		this.govPerson = insuredPerson.isGovPerson();
		this.govDate = insuredPerson.getGovDate();
		this.govRefNo = insuredPerson.getGovRefNo();
		this.govDepart = insuredPerson.getGovDepart();		
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	public int getOverSeaAmount() {
		return overSeaAmount;
	}

	public void setOverSeaAmount(int overSeaAmount) {
		this.overSeaAmount = overSeaAmount;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getDepartureDate() {
		return departureDate;
	}

	/**
	 * @param departureDate
	 *            the departureDate to set
	 */
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	/**
	 * @return the arrivalDate
	 */
	public String getArrivalDate() {
		return arrivalDate;
	}

	/**
	 * @param arrivalDate
	 *            the arrivalDate to set
	 */
	public void setArrivalDate(String arrivalDate) {
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
	
}
