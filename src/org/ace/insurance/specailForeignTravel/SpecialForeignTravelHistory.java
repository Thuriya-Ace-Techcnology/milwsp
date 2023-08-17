package org.ace.insurance.specailForeignTravel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.common.PaymentGateway;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.SaleChannelType;
import org.ace.insurance.common.TableName;
import org.ace.insurance.common.TourismType;
import org.ace.insurance.common.UserRecorder;
import org.ace.java.component.idgen.service.IDInterceptor;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.specialForeignTravel.SpecialForeignTravelDTO;

@Entity
@Table(name = TableName.SPECIAL_FOREIGN_TRAVEL_HIST)
@EntityListeners(IDInterceptor.class)
@TableGenerator(name = "SPECIAL_FOREIGN_TRAVEL_HIST_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "SPECIAL_FOREIGN_TRAVEL_HIST_GEN", allocationSize = 10)
public class SpecialForeignTravelHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SPECIAL_FOREIGN_TRAVEL_HIST_GEN")
	private String id;

	private String registrationNo;

	private double transactionFees;

	private double premium;

	private int periodMonth;

	private String policyNo;

	private String currencyId;

	private double sumInsured;

	private boolean deleteStatus;

	private boolean isConvert;
	
	private double tpaFee;
	
	private double agentCommission;
	
	private String agentLicenseNo;
	
	@Column(name = "AGENTID")
	private String agentId;

	@Column(name = "ORDERID")
	private String orderId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDate;

	private Date activedPolicyStartDate;

	private Date activedPolicyEndDate;

	@Enumerated(EnumType.STRING)
	private SaleChannelType saleChannelType;

	@Enumerated(EnumType.STRING)
	private ResponseStatus responseStatus;

	@Enumerated(EnumType.STRING)
	private PaymentGateway paymentGateway;

	@Enumerated(EnumType.STRING)
	private ProposalStatus proposalStatus;

	@Enumerated(EnumType.STRING)
	private BuyerPlatForm buyerPlatForm;

	@Enumerated(EnumType.STRING)
	private TourismType tourismType;

	@Version
	private int version;

	@Embedded
	private SpecialForeignTravellerInfo travellerInfo;

	@Embedded
	private SpecialTravellerBeneficaryInfo beneficaryInfo;

	@Embedded
	private SpecialTravellerChildInfo childInfo;

	@Embedded
	private UserRecorder recorder;

	public SpecialForeignTravelHistory() {

	}

	public SpecialForeignTravelHistory(SpecialForeignTravel sp) {
		this.transactionFees = sp.getTransactionFees();
		this.premium = sp.getPremium();
		this.periodMonth = sp.getPeriodMonth();
		this.policyNo = sp.getPolicyNo();
		this.currencyId = sp.getCurrencyId();
		this.orderId = sp.getOrderId();
		this.buyerPlatForm = sp.getBuyerPlatForm();
		this.activedPolicyStartDate = sp.getActivedPolicyStartDate();
		this.activedPolicyEndDate = sp.getActivedPolicyEndDate();
		this.travellerInfo = sp.getTravellerInfo();
		this.beneficaryInfo = sp.getBeneficaryInfo();
		this.childInfo = sp.getChildInfo();
		this.tourismType = sp.getTourismType();
		this.sumInsured = sp.getSumInsured();
		this.registrationNo = sp.getRegistrationNo();
		this.agentId = sp.getAgentId();
		this.saleChannelType = sp.getSaleChannelType();
	}
	
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public SaleChannelType getSaleChannelType() {
		return saleChannelType;
	}

	public void setSaleChannelType(SaleChannelType saleChannelType) {
		this.saleChannelType = saleChannelType;
	}

	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public double getTransactionFees() {
		return transactionFees;
	}

	public void setTransactionFees(double transactionFees) {
		this.transactionFees = transactionFees;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public int getPeriodMonth() {
		return periodMonth;
	}

	public void setPeriodMonth(int periodMonth) {
		this.periodMonth = periodMonth;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public TourismType getTourismType() {
		return tourismType;
	}

	public void setTourismType(TourismType tourismType) {
		this.tourismType = tourismType;
	}

	public boolean isDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
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

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public SpecialForeignTravellerInfo getTravellerInfo() {
		return travellerInfo;
	}

	public void setTravellerInfo(SpecialForeignTravellerInfo travellerInfo) {
		this.travellerInfo = travellerInfo;
	}

	public SpecialTravellerBeneficaryInfo getBeneficaryInfo() {
		return beneficaryInfo;
	}

	public void setBeneficaryInfo(SpecialTravellerBeneficaryInfo beneficaryInfo) {
		this.beneficaryInfo = beneficaryInfo;
	}

	public SpecialTravellerChildInfo getChildInfo() {
		return childInfo;
	}

	public void setChildInfo(SpecialTravellerChildInfo childInfo) {
		this.childInfo = childInfo;
	}

	public UserRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
	}

	public boolean isConvert() {
		return isConvert;
	}

	public void setConvert(boolean isConvert) {
		this.isConvert = isConvert;
	}

	public BuyerPlatForm getBuyerPlatForm() {
		return buyerPlatForm;
	}
	
	public String getAgentLicenseNo() {
		return agentLicenseNo;
	}

	public void setAgentLicenseNo(String agentLicenseNo) {
		this.agentLicenseNo = agentLicenseNo;
	}

	public void setBuyerPlatForm(BuyerPlatForm buyerPlatForm) {
		this.buyerPlatForm = buyerPlatForm;
	}


	public double getTpaFee() {
		return tpaFee;
	}
	public void setTpaFee(double tpaFee) {
		this.tpaFee = tpaFee;
	}
	public double getAgentCommission() {
		return agentCommission;
	}
	public void setAgentCommission(double agentCommission) {
		this.agentCommission = agentCommission;
	}
	
	
}
