package org.ace.insurance.personalAccident;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.ace.insurance.common.PaymentGateway;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.TableName;
import org.ace.java.component.FormatID;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.mobilePersonalAccidentproposal.MPAP001;
import org.ace.ws.model.mobilePersonalAccidentproposal.PAIP001;

@Entity
@Table(name = TableName.MOBILE_PERSONALACCIDENT_PROPOSAL)
@TableGenerator(name = "MOBILE_PERSONALACCIDENT_PROPOSAL_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "MOBILE_PERSONALACCIDENT_PROPOSAL_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "MobilePersonalAccidentProposal.findAll", query = "SELECT m FROM MobilePersonalAccidentProposal m ORDER BY m.proposalNo ASC"),
		@NamedQuery(name = "MobilePersonalAccidentProposal.findById", query = "SELECT m FROM MobilePersonalAccidentProposal m WHERE m.id = :id"),
		@NamedQuery(name = "MobilePersonalAccidentProposal.findByOrderId", query = "SELECT m FROM MobilePersonalAccidentProposal m WHERE m.orderId = :orderId"),
		@NamedQuery(name = "MobilePersonalAccidentProposal.responseStatusByOrderId", query = "UPDATE MobilePersonalAccidentProposal m SET m.responseStatus = :responseStatus where m.orderId = :orderId"),
		@NamedQuery(name = "MobilePersonalAccidentProposal.updateByPaymentStatus", query = "UPDATE MobilePersonalAccidentProposal m SET m.proposalStatus = :proposalStatus, m.paymentDate = :paymentDate where m.orderId = :orderId"),
		@NamedQuery(name = "MobilePersonalAccidentProposal.updateDeleteStatusByPaymentStatus", query = "UPDATE MobilePersonalAccidentProposal m SET m.deleteStatus = :deleteStatus where m.orderId = :orderId"),
		@NamedQuery(name = "MobilePersonalAccidentProposal.findByMobileUser", query = "SELECT m FROM MobilePersonalAccidentProposal m WHERE m.userId = :mobileUserId  ORDER BY m.submittedDate DESC"),
		@NamedQuery(name = "MobilePersonalAccidentProposal.findByFromToDate", query = "SELECT m FROM MobilePersonalAccidentProposal m WHERE m.proposalStatus = :proposalStatus and m.paymentDate >= :fromDate and m.paymentDate <= :toDate and m.isConvert = :tConvert or m.isConvert = :fConvert "),
		@NamedQuery(name = "MobilePersonalAccidentProposal.findByResponseStatus", query = "SELECT m FROM MobilePersonalAccidentProposal m WHERE m.proposalStatus = :proposalStatus and m.deleteStatus = 0 and m.responseStatus != :responseStatus or m.responseStatus IS NULL ORDER BY m.submittedDate DESC"),
		@NamedQuery(name = "MobilePersonalAccidentProposal.updateConvertStatusByOrderId", query = "UPDATE MobilePersonalAccidentProposal m SET m.isConvert = :convert where m.orderId = :orderId")})
@Access(value = AccessType.FIELD)
public class MobilePersonalAccidentProposal implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	@Transient
	private String prefix;
	private int periodMonth;
	private String transactionId;
	private String proposalNo;
	private String policyNo;
	private String currencyId;

	private boolean deleteStatus;

	// MobileUserId
	private String userId;
	private String paymentTypeId;

	@Column(name = "ORDERID")
	private String orderId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "MOBILEPAPROPOSALID", referencedColumnName = "ID")
	private List<MobilePersonalAccidentInsuredPerson> insuredPersonList;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date activedPolicyStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date activedPolicyEndDate;
	
	@Enumerated(EnumType.STRING)
	private ResponseStatus responseStatus;

	@Enumerated(EnumType.STRING)
	private PaymentGateway paymentGateway;

	@Enumerated(EnumType.STRING)
	private ProposalStatus proposalStatus;
	private boolean isConvert;

	private int transactionFees;
	
	@Version
	private int version;

	public MobilePersonalAccidentProposal() {

	}

	public MobilePersonalAccidentProposal(MPAP001 insuredPerson) {
		this.id = insuredPerson.getId();
		this.currencyId = insuredPerson.getCurrencyId();
		this.periodMonth = insuredPerson.getPeriodMonth();
		this.transactionId = insuredPerson.getTransactionId();
		this.proposalNo = insuredPerson.getProposalNo();
		this.policyNo = insuredPerson.getPolicyNo();
		this.userId = insuredPerson.getUserId();
		this.orderId = insuredPerson.getOrderId();
		this.deleteStatus = insuredPerson.isDeleteStatus();
		this.paymentTypeId = insuredPerson.getPaymentTypeId();
		this.submittedDate = new Date(insuredPerson.getSubmittedDate());
		this.proposalStatus = insuredPerson.getProposalStatus();
		this.transactionFees = insuredPerson.getTransactionFees();
		this.activedPolicyStartDate = new Date(insuredPerson.getActivedPolicyStartDate());
		this.activedPolicyEndDate = new Date(insuredPerson.getActivedPolicyEndDate());
		List<MobilePersonalAccidentInsuredPerson> insuredPersonList = new ArrayList<MobilePersonalAccidentInsuredPerson>();
		for (PAIP001 mip001 : insuredPerson.getInsuredPersonList()) {
			insuredPersonList.add(new MobilePersonalAccidentInsuredPerson(mip001));
		}
		this.insuredPersonList = insuredPersonList;
		this.version = insuredPerson.getVersion();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MOBILE_PERSONALACCIDENT_PROPOSAL_GEN")
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

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(String paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public int getPeriodMonth() {
		return periodMonth;
	}

	public void setPeriodMonth(int periodMonth) {
		this.periodMonth = periodMonth;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getTransactionFees() {
		return transactionFees;
	}

	public void setTransactionFees(int transactionFees) {
		this.transactionFees = transactionFees;
	}
	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
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

	public PaymentGateway getPaymentGateway() {
		return paymentGateway;
	}

	public void setPaymentGateway(PaymentGateway paymentGateway) {
		this.paymentGateway = paymentGateway;
	}

	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	public boolean isConvert() {
		return isConvert;
	}

	public void setConvert(boolean isConvert) {
		this.isConvert = isConvert;
	}

	public List<MobilePersonalAccidentInsuredPerson> getInsuredPersonList() {
		return insuredPersonList;
	}

	public void setInsuredPersonList(List<MobilePersonalAccidentInsuredPerson> insuredPersonList) {
		this.insuredPersonList = insuredPersonList;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public boolean isDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

}
