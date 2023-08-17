package org.ace.ws.model.mobileMedicalproposal;

import org.ace.insurance.medical.MobileMedicalProposalInsuredPersonAddOn;

public class MedicalAddOnDTO {
	private String id;
	private double premium;
	private double sumInsured;
	private String addOnId;
	private int unit;

	public MedicalAddOnDTO() {

	}

	public MedicalAddOnDTO(MobileMedicalProposalInsuredPersonAddOn addOn) {
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

	public String getAddOnId() {
		return addOnId;
	}

	public void setAddOnId(String addOnId) {
		this.addOnId = addOnId;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

}
