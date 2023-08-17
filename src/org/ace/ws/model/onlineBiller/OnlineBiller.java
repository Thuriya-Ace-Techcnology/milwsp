/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.ws.model.onlineBiller;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.insurance.common.Utils;

@Entity
@Table(name = TableName.ONLINEBILLER)
@NamedQueries(value = { 
		@NamedQuery(name = "OnlineBiller.findByInvoiceNo", query = "SELECT o FROM OnlineBiller o WHERE  o.invoiceNo = :findValue or o.policyNo = :findValue"),
		@NamedQuery(name = "OnlineBiller.updateBuyerStatus", query = "UPDATE OnlineBiller o SET o.bought = :boughtStatus,o.paymentDate = :paymentDate  WHERE o.invoiceNo = :findValue or o.policyNo = :findValue"),
})
public class OnlineBiller implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Column(name = "INVOICENO")
	private String invoiceNo;

	@Column(name = "POLICYNO")
	private String policyNo;

	@Column(name = "POLICYOWNERNAME")
	private String policyOwnerName;

	@Column(name = "AGENTNAME")
	private String agentName;
	
	@Column(name="AGENTID")
	private String agentId;

	@Column(name = "LISCENECENO")
	private String lisceneceNo;
	
	@Column(name = "INTEREST")
	private String interest;
	
	@Column(name = "DEPARTMENT")
	private String department;
	
	@Column(name = "PREMIUM")
	private double premium;
	
	@Column(name = "SUMINSURED")
	private double sumInsured;
	
	@Column(name = "LOCKED")
	private boolean locked;
	
	@Column(name = "STAMPFEES")
	private double stampFees;
	
	@Column(name="BOUGHT")
	private boolean bought;
	
	@Column(name="ISCONFIRM")
	private boolean isConfirm;
	
	@Column(name="REMARK")
	private String remark;
	
	@Column(name="DELETESTATUS")
	private boolean deleteStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVEDPOLICYSTARTDATE")
	private Date activedPolicyStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVEDPOLICYENDDATE")
	private Date activedPolicyEndDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "SUBMITTEDDATE")
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PAYMENTDATE")
	private Date paymentDate;
	
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;
	

	public OnlineBiller() {
		
	}

	public OnlineBiller(OnlineBillerDTO dto) {
		this.id = dto.getId();
		this.invoiceNo = dto.getInvoiceNo();
		this.policyNo = dto.getPolicyNo();
		this.policyOwnerName = dto.getPolicyOwnerName();
		this.agentName = dto.getAgentName();
		this.lisceneceNo = dto.getLisceneceNo();
		this.interest = dto.getInterest();
		this.department = dto.getDepartment();
		this.premium = dto.getPremium();
		this.sumInsured = dto.getSumInsured();
		this.stampFees = dto.getStampFees();
		this.locked = dto.isLocked();
		this.activedPolicyStartDate = Utils.getDate(dto.getActivedPolicyStartDate());
		this.activedPolicyEndDate = Utils.getDate(dto.getActivedPolicyEndDate());
		this.submittedDate = Utils.getDate(dto.getSubmittedDate());
	}

	/******************************************************
	 * getter / setter
	 **********************************************************/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public boolean isConfirm() {
		return isConfirm;
	}

	public void setConfirm(boolean isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getPolicyOwnerName() {
		return policyOwnerName;
	}

	public void setPolicyOwnerName(String policyOwnerName) {
		this.policyOwnerName = policyOwnerName;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getLisceneceNo() {
		return lisceneceNo;
	}

	public void setLisceneceNo(String lisceneceNo) {
		this.lisceneceNo = lisceneceNo;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public Date getActivedPolicyStartDate() {
		return activedPolicyStartDate;
	}

	public void setActivedPolicyStartDate(Date activedPolicyStartDate) {
		this.activedPolicyStartDate = activedPolicyStartDate;
	}

	public Date getActivedPolicyEndDate() {
		return activedPolicyEndDate;
	}

	public void setActivedPolicyEndDate(Date activedPolicyEndDate) {
		this.activedPolicyEndDate = activedPolicyEndDate;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
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

	public boolean isBought() {
		return bought;
	}

	public void setBought(boolean bought) {
		this.bought = bought;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getStampFees() {
		return stampFees;
	}

	public void setStampFees(double stampFees) {
		this.stampFees = stampFees;
	}
	
	

}