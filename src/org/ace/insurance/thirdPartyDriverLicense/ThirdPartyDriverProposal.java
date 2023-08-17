package org.ace.insurance.thirdPartyDriverLicense;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.insurance.system.common.branch.Branch;
import org.ace.insurance.system.common.currency.Currency;

@Entity
@Table(name = TableName.THIRDPARTYDRIVER_PROPOSAL)
@NamedQueries(value = {
		@NamedQuery(name = "ThirdPartyDriverProposal.findAll", query = "SELECT m FROM ThirdPartyDriverProposal m"),
		@NamedQuery(name = "ThirdPartyDriverProposal.findById", query = "SELECT m FROM ThirdPartyDriverProposal m WHERE m.id = :id"),
		@NamedQuery(name = "ThirdPartyDriverProposal.findByOrderId", query = "SELECT m FROM ThirdPartyDriverProposal m WHERE m.orderId = :orderId"),
		@NamedQuery(name = "ThirdPartyDriverProposal.findByDriverCodeNo", query = "SELECT m FROM ThirdPartyDriverProposal m INNER JOIN m.thirdPartyDriverInfo t WHERE t.driverCodeNo = :driverCodeNo and m.proposalStatus=:proposalStatus"),
		@NamedQuery(name = "ThirdPartyDriverProposal.findByFromToDate", query = "SELECT t FROM ThirdPartyDriverProposal t WHERE t.paymentDate>=:fromDate and t.paymentDate<=:toDate and t.proposalStatus=:proposalStatus"),
		@NamedQuery(name = "ThirdPartyDriverProposal.updateByPaymentStatus", query = "UPDATE ThirdPartyDriverProposal t SET t.policyNo = :policyNo, t.proposalStatus = :proposalStatus, t.paymentDate = :paymentDate where t.orderId = :orderId"), })
public class ThirdPartyDriverProposal implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String proposalNo;
	private String policyNo;
	private String submittedDate;
	private String orderId;
	private String paymentDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCYID", referencedColumnName = "ID")
	private Currency currency;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCHID", referencedColumnName = "ID")
	private Branch branch;

	@Enumerated(EnumType.STRING)
	private ProposalStatus proposalStatus;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "THIRDPARTYPROPOSALID", referencedColumnName = "ID")
	private List<ThirdPartyDriverInfo> thirdPartyDriverInfo;

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;

	public ThirdPartyDriverProposal() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(String submittedDate) {
		this.submittedDate = submittedDate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
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

	public List<ThirdPartyDriverInfo> getThirdPartyDriverInfo() {
		return thirdPartyDriverInfo;
	}

	public void setThirdPartyDriverInfo(List<ThirdPartyDriverInfo> thirdPartyDriverInfo) {
		this.thirdPartyDriverInfo = thirdPartyDriverInfo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

}
