package org.ace.insurance.onlineBiller;

/***************************************************************************************
 * @author <<Kyaw Yea Lwin>>
 * @Date 2021-09-20
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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

import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.java.component.FormatID;
import org.ace.java.component.idgen.service.IDInterceptor;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = TableName.ONLINEBILLER_BUYER)
@TableGenerator(name = "ONLINEBUYER_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "ONLINEBUYER_GEN", allocationSize = 10)
@NamedQueries(value = {
		@NamedQuery(name = "OnlineBillerBuyer.findByInvoiceNo", query = "SELECT o FROM OnlineBillerBuyer o WHERE o.invoiceNo = :findValue or o.policyNo = :findValue"),
		@NamedQuery(name = "OnlineBillerBuyer.findByOrderId", query = "SELECT o FROM OnlineBillerBuyer o WHERE  o.orderId = :findValue"),
		@NamedQuery(name = "OnlineBillerBuyer.findByProposalStatus", query = "SELECT o FROM OnlineBillerBuyer o WHERE  o.proposalStatus = :proposalStatus and o.isConvert = :isCovert"),
		@NamedQuery(name = "OnlineBillerBuyer.updateByPaymentStatus", query = "UPDATE OnlineBillerBuyer o SET o.proposalStatus = :proposalStatus, o.paymentDate = :paymentDate where o.orderId = :orderId"),
		@NamedQuery(name = "OnlineBillerBuyer.updateConvertStatusByOrderId", query = "UPDATE OnlineBillerBuyer o SET o.isConvert = :convert where o.orderId = :orderId"),
		@NamedQuery(name = "OnlineBillerBuyer.findByDateAndPlatForm", query = "SELECT o FROM OnlineBillerBuyer o WHERE  o.submittedDate >= :fromDate and o.submittedDate <= :toDate and o.buyerPlatForm = :buyPlatForm"), })
@Access(value = AccessType.FIELD)
@EntityListeners(IDInterceptor.class)
public class OnlineBillerBuyer implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	
	@Column(name = "ONLINEID")
	private String onlineId;

	@Transient
	private String prefix;

	@Column(name = "INVOICENO")
	private String invoiceNo;

	@Column(name = "POLICYNO")
	private String policyNo;

	@Column(name = "POLICYOWNERNAME")
	private String policyOwnerName;

	@Column(name = "AGENTNAME")
	private String agentName;

	@Column(name = "LISCENECENO")
	private String lisceneceNo;

	@Column(name = "STAMPFEES")
	private double stampfees;

	@Column(name = "INTEREST")
	private String interest;

	@Column(name = "DEPARTMENT")
	private String department;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "PROPOSALSTATUS")
	private ProposalStatus proposalStatus;

	@Column(name = "PREMIUM")
	private double premium;

	@Column(name = "SUMINSURED")
	@Expose
	private double sumInsured;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "BUYERPLATFORM")
	private BuyerPlatForm buyerPlatForm;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVEDPOLICYSTARTDATE")
	private Date activedPolicyStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVEDPOLICYENDDATE")
	private Date activedPolicyEndDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "SUBMITTEDDATE")
	private Date submittedDate;

	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	@Column(name = "BUYERTEMPID")
	private String buyerTempId;

	@Column(name = "ORDERID")
	private String orderId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PAYMENTDATE")
	private Date paymentDate;

	@Column(name = "isConvert")
	private boolean isConvert;

	@Column(name = "SERVICECHARGES")
	private double serviceCharges;

	public OnlineBillerBuyer() {

	}

	/******************************************************
	 * getter / setter
	 **********************************************************/

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ONLINEBUYER_GEN")
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

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
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

	public BuyerPlatForm getBuyerPlatForm() {
		return buyerPlatForm;
	}

	public void setBuyerPlatForm(BuyerPlatForm buyerPlatForm) {
		this.buyerPlatForm = buyerPlatForm;
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

	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
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

	public String getBuyerTempId() {
		return buyerTempId;
	}

	public double getStampfees() {
		return stampfees;
	}

	public void setStampfees(double stampfees) {
		this.stampfees = stampfees;
	}

	public boolean isConvert() {
		return isConvert;
	}

	public void setConvert(boolean isConvert) {
		this.isConvert = isConvert;
	}

	public void setBuyerTempId(String buyerTempId) {
		this.buyerTempId = buyerTempId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getServiceCharges() {
		return serviceCharges;
	}

	public void setServiceCharges(double serviceCharges) {
		this.serviceCharges = serviceCharges;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activedPolicyEndDate == null) ? 0 : activedPolicyEndDate.hashCode());
		result = prime * result + ((activedPolicyStartDate == null) ? 0 : activedPolicyStartDate.hashCode());
		result = prime * result + ((agentName == null) ? 0 : agentName.hashCode());
		result = prime * result + ((buyerPlatForm == null) ? 0 : buyerPlatForm.hashCode());
		result = prime * result + ((buyerTempId == null) ? 0 : buyerTempId.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((interest == null) ? 0 : interest.hashCode());
		result = prime * result + ((invoiceNo == null) ? 0 : invoiceNo.hashCode());
		result = prime * result + ((lisceneceNo == null) ? 0 : lisceneceNo.hashCode());
		result = prime * result + ((policyNo == null) ? 0 : policyNo.hashCode());
		result = prime * result + ((policyOwnerName == null) ? 0 : policyOwnerName.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		long temp;
		temp = Double.doubleToLongBits(premium);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
		result = prime * result + ((submittedDate == null) ? 0 : submittedDate.hashCode());
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
		OnlineBillerBuyer other = (OnlineBillerBuyer) obj;
		if (activedPolicyEndDate == null) {
			if (other.activedPolicyEndDate != null)
				return false;
		} else if (!activedPolicyEndDate.equals(other.activedPolicyEndDate))
			return false;
		if (activedPolicyStartDate == null) {
			if (other.activedPolicyStartDate != null)
				return false;
		} else if (!activedPolicyStartDate.equals(other.activedPolicyStartDate))
			return false;
		if (agentName == null) {
			if (other.agentName != null)
				return false;
		} else if (!agentName.equals(other.agentName))
			return false;
		if (buyerPlatForm != other.buyerPlatForm)
			return false;
		if (buyerTempId == null) {
			if (other.buyerTempId != null)
				return false;
		} else if (!buyerTempId.equals(other.buyerTempId))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (interest == null) {
			if (other.interest != null)
				return false;
		} else if (!interest.equals(other.interest))
			return false;
		if (invoiceNo == null) {
			if (other.invoiceNo != null)
				return false;
		} else if (!invoiceNo.equals(other.invoiceNo))
			return false;
		if (lisceneceNo == null) {
			if (other.lisceneceNo != null)
				return false;
		} else if (!lisceneceNo.equals(other.lisceneceNo))
			return false;
		if (policyNo == null) {
			if (other.policyNo != null)
				return false;
		} else if (!policyNo.equals(other.policyNo))
			return false;
		if (policyOwnerName == null) {
			if (other.policyOwnerName != null)
				return false;
		} else if (!policyOwnerName.equals(other.policyOwnerName))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (Double.doubleToLongBits(premium) != Double.doubleToLongBits(other.premium))
			return false;
		if (recorder == null) {
			if (other.recorder != null)
				return false;
		} else if (!recorder.equals(other.recorder))
			return false;
		if (submittedDate == null) {
			if (other.submittedDate != null)
				return false;
		} else if (!submittedDate.equals(other.submittedDate))
			return false;
		if (Double.doubleToLongBits(sumInsured) != Double.doubleToLongBits(other.sumInsured))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}