package org.ace.insurance.medical;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import javax.persistence.Version;

import org.ace.insurance.common.CustomerType;
import org.ace.insurance.common.HealthType;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.insurance.common.Utils;
import org.ace.java.component.idgen.service.IDInterceptor;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.mobileMedicalproposal.MedicalProposalDTO;
import org.ace.ws.model.mobileMedicalproposal.MedicalProposalInsuredPersonDTO;

@Entity
@Table(name = TableName.MOBILE_MEDICALPROPOSAL)
@TableGenerator(name = "MEDICALPROPOSAL_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "MEDICALPROPOSAL_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "MedicalProposal.findAll", query = "SELECT m FROM MobileMedicalProposal m "),
		@NamedQuery(name = "MobileMedicalProposal.findByOrderId", query = "SELECT m FROM MobileMedicalProposal m WHERE m.orderId = :orderId"),
		@NamedQuery(name = "MobileMedicalProposal.updateByPaymentStatus", query = "UPDATE MobileMedicalProposal m SET m.proposalStatus = :proposalStatus, m.paymentDate = :paymentDate where m.orderId = :orderId"),
		@NamedQuery(name = "MobileMedicalProposal.responseStatusByOrderId", query = "UPDATE MobileMedicalProposal m SET m.responseStatus = :responseStatus where m.orderId = :orderId"),
		@NamedQuery(name = "MobileMedicalProposal.updateDeleteStatusByPaymentStatus", query = "UPDATE MobileMedicalProposal m SET m.deleteStatus = :deleteStatus where m.orderId = :orderId"),
		@NamedQuery(name = "MobileMedicalProposal.findByFromToDate", query = "SELECT m FROM MobileMedicalProposal m WHERE m.proposalStatus = :proposalStatus and m.paymentDate >= :fromDate and m.paymentDate <= :toDate and m.isConvert = :tConvert or m.isConvert = :fConvert "),
		@NamedQuery(name = "MobileMedicalProposal.findByResponseStatus", query = "SELECT m FROM MobileMedicalProposal m WHERE m.proposalStatus = :proposalStatus and m.deleteStatus = 0 and m.responseStatus != :responseStatus ORDER BY m.submittedDate DESC"),
		@NamedQuery(name = "MobileMedicalProposal.updateConvertStatusByOrderId", query = "UPDATE MobileMedicalProposal m SET m.isConvert = :convert where m.orderId = :orderId")})
@EntityListeners(IDInterceptor.class)
public class MobileMedicalProposal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MEDICALPROPOSAL_GEN")
	private String id;
	private String proposalNo;
	
	@Column(name = "policyNo")
	private String policyNo;

	private int periodMonth;
	private String transactionId;
	private int transactionFees;
	private boolean deleteStatus;

	@Column(name = "ORDERID")
	private String orderId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date activedPolicyStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date activedPolicyEndDate;

	@Enumerated(EnumType.STRING)
	private HealthType healthType;

	@Enumerated(EnumType.STRING)
	private ProposalStatus proposalStatus;

	@Enumerated(EnumType.STRING)
	private CustomerType customerType;

	@Enumerated(EnumType.STRING)
	private ResponseStatus responseStatus;
	
	// @Column(name = "RATE")
	// private double rate;
	private boolean isConvert;

	private String userId;

	private String paymentTypeId;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "MEDICALPROPOSALID", referencedColumnName = "ID")
	private List<MobileMedicalProposalInsuredPerson> medicalProposalInsuredPersonList;

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;

	public MobileMedicalProposal() {
	}

	public MobileMedicalProposal(MedicalProposalDTO dto){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		this.id = dto.getId();
		this.periodMonth = dto.getPeriodMonth();
		this.transactionId = dto.getTransactionId();
		this.proposalNo = dto.getProposalNo();
		this.policyNo = dto.getPolicyNo();
		this.userId = dto.getUserId();
		this.orderId = dto.getOrderId();
		this.paymentTypeId = dto.getPaymentTypeId();
		this.submittedDate = dto.getSubmittedDate()!= null ?  Utils.getDate(dto.getSubmittedDate()) : null;
		this.proposalStatus = dto.getProposalStatus();
		try {
			this.activedPolicyStartDate = dto.getActivedPolicyStartDate()!= null ? sdf.parse(dto.getActivedPolicyStartDate()) : null;
			this.activedPolicyEndDate = dto.getActivedPolicyEndDate()!= null ? sdf.parse(dto.getActivedPolicyEndDate()) : null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.healthType = dto.getHealthType();
		this.customerType = dto.getCustomerType();
		this.version = dto.getVersion();
		if (dto.getInsuredPersonList() != null) {
			medicalProposalInsuredPersonList = new ArrayList<MobileMedicalProposalInsuredPerson>();
			for (MedicalProposalInsuredPersonDTO mb001 : dto.getInsuredPersonList()) {
				mb001.setProductId(dto.getProductId());
				medicalProposalInsuredPersonList.add(new MobileMedicalProposalInsuredPerson(mb001));
			}
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public int getTransactionFees() {
		return transactionFees;
	}

	public void setTransactionFees(int transactionFees) {
		this.transactionFees = transactionFees;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public Date getActivedPolicyStartDate() {
		return activedPolicyStartDate;
	}

	public void setActivedPolicyStartDate(Date activedPolicyStartDate) {
		this.activedPolicyStartDate = activedPolicyStartDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getActivedPolicyEndDate() {
		return activedPolicyEndDate;
	}

	public void setActivedPolicyEndDate(Date activedPolicyEndDate) {
		this.activedPolicyEndDate = activedPolicyEndDate;
	}

	public HealthType getHealthType() {
		return healthType;
	}

	public void setHealthType(HealthType healthType) {
		this.healthType = healthType;
	}

	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(String paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public List<MobileMedicalProposalInsuredPerson> getMedicalProposalInsuredPersonList() {
		return medicalProposalInsuredPersonList;
	}

	public void setMedicalProposalInsuredPersonList(List<MobileMedicalProposalInsuredPerson> medicalProposalInsuredPersonList) {
		this.medicalProposalInsuredPersonList = medicalProposalInsuredPersonList;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public UserRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
	}

	public boolean isDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isConvert() {
		return isConvert;
	}

	public void setConvert(boolean isConvert) {
		this.isConvert = isConvert;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activedPolicyEndDate == null) ? 0 : activedPolicyEndDate.hashCode());
		result = prime * result + ((activedPolicyStartDate == null) ? 0 : activedPolicyStartDate.hashCode());
		result = prime * result + ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result + (deleteStatus ? 1231 : 1237);
		result = prime * result + ((healthType == null) ? 0 : healthType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((paymentDate == null) ? 0 : paymentDate.hashCode());
		result = prime * result + ((paymentTypeId == null) ? 0 : paymentTypeId.hashCode());
		result = prime * result + periodMonth;
		result = prime * result + ((policyNo == null) ? 0 : policyNo.hashCode());
		result = prime * result + ((proposalNo == null) ? 0 : proposalNo.hashCode());
		result = prime * result + ((proposalStatus == null) ? 0 : proposalStatus.hashCode());
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
		result = prime * result + ((submittedDate == null) ? 0 : submittedDate.hashCode());
		result = prime * result + transactionFees;
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		MobileMedicalProposal other = (MobileMedicalProposal) obj;
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
		if (customerType != other.customerType)
			return false;
		if (deleteStatus != other.deleteStatus)
			return false;
		if (healthType != other.healthType)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (paymentDate == null) {
			if (other.paymentDate != null)
				return false;
		} else if (!paymentDate.equals(other.paymentDate))
			return false;
		if (paymentTypeId == null) {
			if (other.paymentTypeId != null)
				return false;
		} else if (!paymentTypeId.equals(other.paymentTypeId))
			return false;
		if (periodMonth != other.periodMonth)
			return false;
		if (policyNo == null) {
			if (other.policyNo != null)
				return false;
		} else if (!policyNo.equals(other.policyNo))
			return false;
		if (proposalNo == null) {
			if (other.proposalNo != null)
				return false;
		} else if (!proposalNo.equals(other.proposalNo))
			return false;
		if (proposalStatus != other.proposalStatus)
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
		if (transactionFees != other.transactionFees)
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}
