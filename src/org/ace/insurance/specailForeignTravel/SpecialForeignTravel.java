package org.ace.insurance.specailForeignTravel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.common.CoveragePlan;
import org.ace.insurance.common.Packages;
import org.ace.insurance.common.PaymentGateway;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.SaleChannelType;
import org.ace.insurance.common.TableName;
import org.ace.insurance.common.TourismType;
import org.ace.insurance.common.UserRecorder;
import org.ace.insurance.medical.surveyAnswer.SurveyQuestionAnswer;
import org.ace.java.component.FormatID;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.specialForeignTravel.SpecialForeignTravelDTO;

@Entity
@Table(name = TableName.SPECIAL_FOREIGN_TRAVEL)
@TableGenerator(name = "MOBILE_SPECIAL_FOREIGN_TRAVEL_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "MOBILE_SPECIAL_FOREIGN_TRAVEL_GEN", allocationSize = 10)
@NamedQueries(value = {
		@NamedQuery(name = "SpecialForeignTravel.findByOrderId", query = "SELECT t FROM SpecialForeignTravel t WHERE t.orderId = :orderId and t.deleteStatus = :deleteStatus"),
		@NamedQuery(name = "SpecialForeignTravel.findById", query = "SELECT t FROM SpecialForeignTravel t WHERE t.id = :id and t.deleteStatus = :deleteStatus"),
		@NamedQuery(name = "SpecialForeignTravel.findByPassportNoAndCountryCode", query = "SELECT t FROM SpecialForeignTravel t WHERE t.travellerInfo.passportNo = :passportNo and t.deleteStatus = :deleteStatus and t.travellerInfo.countryCode = :countryCode and t.proposalStatus = :proposalStatus and t.tourismType = :tourismType order by t.paymentDate desc"),
		@NamedQuery(name = "SpecialForeignTravel.findByfromDatetoDate", query = "SELECT t FROM SpecialForeignTravel t WHERE t.paymentDate >= :fromDate and t.paymentDate <= :toDate and t.deleteStatus = :deleteStatus and t.proposalStatus = :proposalStatus order by t.paymentDate desc"),
		@NamedQuery(name = "SpecialForeignTravel.findByProposalStatus", query = "SELECT o FROM SpecialForeignTravel o WHERE  o.proposalStatus = :proposalStatus and o.isConvert = :isCovert"),
		@NamedQuery(name = "SpecialForeignTravel.findByProposalStatusAndTourismType", query = "SELECT o FROM SpecialForeignTravel o WHERE  o.proposalStatus = :proposalStatus and o.isConvert = :isCovert and o.tourismType = :tourismType"),
		@NamedQuery(name = "SpecialForeignTravel.updateConvertStatusByOrderId", query = "UPDATE SpecialForeignTravel o SET o.isConvert = :convert where o.orderId = :orderId"),
		@NamedQuery(name = "SpecialForeignTravel.updateByPaymentStatus", query = "UPDATE SpecialForeignTravel t SET t.proposalStatus = :proposalStatus, t.paymentDate = :paymentDate,t.policyNo = :policyNo where t.orderId = :orderId"),
		@NamedQuery(name = "SpecialForeignTravel.updateByNative", query = "UPDATE SpecialForeignTravel t SET \r\n"
				+ "t.registrationNo = :registrationNo, t.transactionFees = :transactionFees,t.premium = :premium,t.periodMonth = :periodMonth, t.policyNo = :policyNo,t.currencyId = :currencyId,t.sumInsured = :sumInsured, t.deleteStatus = :deleteStatus,t.isConvert = :isConvert,t.tpaFee = :tpaFee, t.agentCommission = :agentCommission,t.agentId = :agentId,\r\n"
				+ "t.agentName = :agentName, t.orderId = :orderId,t.submittedDate = :submittedDate,t.paymentDate = :paymentDate, t.activedPolicyStartDate = :activedPolicyStartDate,t.activedPolicyEndDate = :activedPolicyEndDate,t.saleChannelType = :saleChannelType, t.responseStatus = :responseStatus,t.paymentGateway = :paymentGateway,t.proposalStatus = :proposalStatus, t.buyerPlatForm = :buyerPlatForm,t.tourismType = :tourismType,\r\n"
				+ "t.version = :version, t.travellerInfo.countryCode = :countryCode,t.travellerInfo.fullName = :fullName,t.travellerInfo.dateOfBirth = :dateOfBirth, t.travellerInfo.gender = :gender,t.travellerInfo.passportNo = :passportNo,t.travellerInfo.contactNo = :contactNo, t.travellerInfo.myanmarAddress = :myanmarAddress,t.travellerInfo.residentAddress = :residentAddress,t.travellerInfo.age = :age, t.travellerInfo.email = :email,t.travellerInfo.passportExpireDate = :passportExpireDate,\r\n"
				+ "t.travellerInfo.residentCountry = :residentCountry,t.travellerInfo.journeyFrom = :journeyFrom,t.travellerInfo.journeyTo = :journeyTo, t.travellerInfo.fatherName = :fatherName,t.travellerInfo.race = :race,t.travellerInfo.maritalStatus = :maritalStatus, t.travellerInfo.departureDate = :departureDate,t.travellerInfo.occupation = :occupation,t.travellerInfo.foreignContactNo = :foreignContactNo, t.beneficaryInfo.benName = :benName,t.beneficaryInfo.benDateOfBirth = :benDateOfBirth,\r\n"
				+ "t.beneficaryInfo.benNIDNo = :benNIDNo,t.beneficaryInfo.benRelationship = :benRelationship,t.beneficaryInfo.benResidentAddress = :benResidentAddress,t.beneficaryInfo.benContactNo = :benContactNo,t.beneficaryInfo.benEmail = :benEmail,t.beneficaryInfo.benCode = :benCode,t.beneficaryInfo.benResidentCountry = :benResidentCountry,t.childInfo.cAge = :cAge,t.childInfo.cName = :cName, t.childInfo.cGender = :cGender,t.childInfo.cRelation = :cRelation,t.childInfo.cBirthDate = :cBirthDate,t.childInfo.cOtherName1 = :cOtherName1, t.childInfo.cOtherName2 = :cOtherName2,t.childInfo.cStatus = :cStatus,t.recorder = :recorder\r\n"
				+ "where t.id = :id")})
@Access(value = AccessType.FIELD)
public class SpecialForeignTravel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	@Transient
	private String prefix;

	private String passportIssuedCountry;
	
	private Date passportIssuedDate;

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
	
	private String cancellationReason;
	
	private boolean ulinkStatus;
	
	private boolean ulinkEdit;
	
	private boolean onlineEdit;
	
	@Enumerated(EnumType.STRING)
	private CoveragePlan coveragePlan;
	
	@Enumerated(EnumType.STRING)
	private Packages packages;
	
	@Column(name = "AGENTID")
	private String agentId;

	@Column(name="AGENTNAME")
	private String agentName;
	
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

	private String associationAgentId;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "INSUREDPERSONID", referencedColumnName = "ID")
	private List<SurveyQuestionAnswer> surveyQuestionAnswerList;

	private String productId;
	
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

	public SpecialForeignTravel() {

	}

	public SpecialForeignTravel(SpecialForeignTravelDTO dto) {
		this.transactionFees = dto.getTransactionFees();
		this.premium = dto.getPremium();
		this.periodMonth = dto.getPeriodMonth();
		this.policyNo = dto.getPolicyNo();
		this.currencyId = dto.getCurrencyId();
		this.orderId = dto.getOrderId();
		this.buyerPlatForm = dto.getBuyerPlatForm();
		this.activedPolicyStartDate = dto.getActivedPolicyStartDate() == 0 ? null
				: new Date(dto.getActivedPolicyStartDate());
		this.activedPolicyEndDate = dto.getActivedPolicyEndDate() == 0 ? null : new Date(dto.getActivedPolicyEndDate());
		this.travellerInfo = new SpecialForeignTravellerInfo(dto);
		this.beneficaryInfo = new SpecialTravellerBeneficaryInfo(dto);
		this.childInfo = new SpecialTravellerChildInfo(dto);
		this.tourismType = dto.getTourismType();
		this.sumInsured = dto.getSumInsured();
		this.registrationNo = dto.getRegistrationNo();
		this.agentId = dto.getAgentId();
		this.saleChannelType = SaleChannelType.CUSTOMER_DIRECT.equals(dto.getSaleChannelType()) ? SaleChannelType.WALKIN : SaleChannelType.AGENT;
		this.tpaFee = dto.getTpaFee();
		this.agentCommission = dto.getAgentCommission();
		this.agentName = dto.getAgentName();
		this.agentLicenseNo = dto.getAgentLicenseNo();

		this.passportIssuedCountry = dto.getPassportIssuedCountry();
		this.passportIssuedDate = dto.getPassportIssuedDate() == 0 ? null : new Date(dto.getPassportIssuedDate());
		this.cancellationReason = dto.getCancellationReason();
		this.ulinkStatus = dto.isUlinkStatus();
		this.ulinkEdit = dto.isUlinkEdit();
		this.onlineEdit = dto.isOnlineEdit();
		this.coveragePlan = dto.getCoveragePlan();
		this.packages = dto.getPackages();
		this.productId = dto.getProductId();
		this.associationAgentId = dto.getAssociationAgentId();

	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MOBILE_SPECIAL_FOREIGN_TRAVEL_GEN")
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

	public String getPassportIssuedCountry() {
		return passportIssuedCountry;
	}

	public void setPassportIssuedCountry(String passportIssuedCountry) {
		this.passportIssuedCountry = passportIssuedCountry;
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

	public void setBuyerPlatForm(BuyerPlatForm buyerPlatForm) {
		this.buyerPlatForm = buyerPlatForm;
	}

	public List<SurveyQuestionAnswer> getSurveyQuestionAnswerList() {
		return surveyQuestionAnswerList;
	}

	public void setSurveyQuestionAnswerList(List<SurveyQuestionAnswer> surveyQuestionAnswerList) {
		this.surveyQuestionAnswerList = surveyQuestionAnswerList;
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
	
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	
	public String getAgentLicenseNo() {
		return agentLicenseNo;
	}

	public void setAgentLicenseNo(String agentLicenseNo) {
		this.agentLicenseNo = agentLicenseNo;
	}
	
	public Date getPassportIssuedDate() {
		return passportIssuedDate;
	}

	public void setPassportIssuedDate(Date passportIssuedDate) {
		this.passportIssuedDate = passportIssuedDate;
	}

	public String getCancellationReason() {
		return cancellationReason;
	}

	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}

	public boolean isUlinkStatus() {
		return ulinkStatus;
	}

	public void setUlinkStatus(boolean ulinkStatus) {
		this.ulinkStatus = ulinkStatus;
	}

	public boolean isUlinkEdit() {
		return ulinkEdit;
	}

	public void setUlinkEdit(boolean ulinkEdit) {
		this.ulinkEdit = ulinkEdit;
	}

	public boolean isOnlineEdit() {
		return onlineEdit;
	}

	public void setOnlineEdit(boolean onlineEdit) {
		this.onlineEdit = onlineEdit;
	}

	public CoveragePlan getCoveragePlan() {
		return coveragePlan;
	}

	public void setCoveragePlan(CoveragePlan coveragePlan) {
		this.coveragePlan = coveragePlan;
	}

	public Packages getPackages() {
		return packages;
	}

	public void setPackages(Packages packages) {
		this.packages = packages;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getAssociationAgentId() {
		return associationAgentId;
	}

	public void setAssociationAgentId(String associationAgentId) {
		this.associationAgentId = associationAgentId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activedPolicyEndDate == null) ? 0 : activedPolicyEndDate.hashCode());
		result = prime * result + ((activedPolicyStartDate == null) ? 0 : activedPolicyStartDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(agentCommission);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((agentId == null) ? 0 : agentId.hashCode());
		result = prime * result + ((agentLicenseNo == null) ? 0 : agentLicenseNo.hashCode());
		result = prime * result + ((agentName == null) ? 0 : agentName.hashCode());
		result = prime * result + ((associationAgentId == null) ? 0 : associationAgentId.hashCode());
		result = prime * result + ((beneficaryInfo == null) ? 0 : beneficaryInfo.hashCode());
		result = prime * result + ((buyerPlatForm == null) ? 0 : buyerPlatForm.hashCode());
		result = prime * result + ((cancellationReason == null) ? 0 : cancellationReason.hashCode());
		result = prime * result + ((childInfo == null) ? 0 : childInfo.hashCode());
		result = prime * result + ((coveragePlan == null) ? 0 : coveragePlan.hashCode());
		result = prime * result + ((currencyId == null) ? 0 : currencyId.hashCode());
		result = prime * result + (deleteStatus ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isConvert ? 1231 : 1237);
		result = prime * result + (onlineEdit ? 1231 : 1237);
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((packages == null) ? 0 : packages.hashCode());
		result = prime * result + ((passportIssuedCountry == null) ? 0 : passportIssuedCountry.hashCode());
		result = prime * result + ((passportIssuedDate == null) ? 0 : passportIssuedDate.hashCode());
		result = prime * result + ((paymentDate == null) ? 0 : paymentDate.hashCode());
		result = prime * result + ((paymentGateway == null) ? 0 : paymentGateway.hashCode());
		result = prime * result + periodMonth;
		result = prime * result + ((policyNo == null) ? 0 : policyNo.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		temp = Double.doubleToLongBits(premium);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((proposalStatus == null) ? 0 : proposalStatus.hashCode());
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
		result = prime * result + ((registrationNo == null) ? 0 : registrationNo.hashCode());
		result = prime * result + ((responseStatus == null) ? 0 : responseStatus.hashCode());
		result = prime * result + ((saleChannelType == null) ? 0 : saleChannelType.hashCode());
		result = prime * result + ((submittedDate == null) ? 0 : submittedDate.hashCode());
		temp = Double.doubleToLongBits(sumInsured);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((surveyQuestionAnswerList == null) ? 0 : surveyQuestionAnswerList.hashCode());
		result = prime * result + ((tourismType == null) ? 0 : tourismType.hashCode());
		temp = Double.doubleToLongBits(tpaFee);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(transactionFees);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((travellerInfo == null) ? 0 : travellerInfo.hashCode());
		result = prime * result + (ulinkEdit ? 1231 : 1237);
		result = prime * result + (ulinkStatus ? 1231 : 1237);
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
		SpecialForeignTravel other = (SpecialForeignTravel) obj;
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
		if (Double.doubleToLongBits(agentCommission) != Double.doubleToLongBits(other.agentCommission))
			return false;
		if (agentId == null) {
			if (other.agentId != null)
				return false;
		} else if (!agentId.equals(other.agentId))
			return false;
		if (agentLicenseNo == null) {
			if (other.agentLicenseNo != null)
				return false;
		} else if (!agentLicenseNo.equals(other.agentLicenseNo))
			return false;
		if (agentName == null) {
			if (other.agentName != null)
				return false;
		} else if (!agentName.equals(other.agentName))
			return false;
		if (associationAgentId == null) {
			if (other.associationAgentId != null)
				return false;
		} else if (!associationAgentId.equals(other.associationAgentId))
			return false;
		if (beneficaryInfo == null) {
			if (other.beneficaryInfo != null)
				return false;
		} else if (!beneficaryInfo.equals(other.beneficaryInfo))
			return false;
		if (buyerPlatForm != other.buyerPlatForm)
			return false;
		if (cancellationReason == null) {
			if (other.cancellationReason != null)
				return false;
		} else if (!cancellationReason.equals(other.cancellationReason))
			return false;
		if (childInfo == null) {
			if (other.childInfo != null)
				return false;
		} else if (!childInfo.equals(other.childInfo))
			return false;
		if (coveragePlan != other.coveragePlan)
			return false;
		if (currencyId == null) {
			if (other.currencyId != null)
				return false;
		} else if (!currencyId.equals(other.currencyId))
			return false;
		if (deleteStatus != other.deleteStatus)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isConvert != other.isConvert)
			return false;
		if (onlineEdit != other.onlineEdit)
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (packages != other.packages)
			return false;
		if (passportIssuedCountry == null) {
			if (other.passportIssuedCountry != null)
				return false;
		} else if (!passportIssuedCountry.equals(other.passportIssuedCountry))
			return false;
		if (passportIssuedDate == null) {
			if (other.passportIssuedDate != null)
				return false;
		} else if (!passportIssuedDate.equals(other.passportIssuedDate))
			return false;
		if (paymentDate == null) {
			if (other.paymentDate != null)
				return false;
		} else if (!paymentDate.equals(other.paymentDate))
			return false;
		if (paymentGateway != other.paymentGateway)
			return false;
		if (periodMonth != other.periodMonth)
			return false;
		if (policyNo == null) {
			if (other.policyNo != null)
				return false;
		} else if (!policyNo.equals(other.policyNo))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (Double.doubleToLongBits(premium) != Double.doubleToLongBits(other.premium))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (proposalStatus != other.proposalStatus)
			return false;
		if (recorder == null) {
			if (other.recorder != null)
				return false;
		} else if (!recorder.equals(other.recorder))
			return false;
		if (registrationNo == null) {
			if (other.registrationNo != null)
				return false;
		} else if (!registrationNo.equals(other.registrationNo))
			return false;
		if (responseStatus != other.responseStatus)
			return false;
		if (saleChannelType != other.saleChannelType)
			return false;
		if (submittedDate == null) {
			if (other.submittedDate != null)
				return false;
		} else if (!submittedDate.equals(other.submittedDate))
			return false;
		if (Double.doubleToLongBits(sumInsured) != Double.doubleToLongBits(other.sumInsured))
			return false;
		if (surveyQuestionAnswerList == null) {
			if (other.surveyQuestionAnswerList != null)
				return false;
		} else if (!surveyQuestionAnswerList.equals(other.surveyQuestionAnswerList))
			return false;
		if (tourismType != other.tourismType)
			return false;
		if (Double.doubleToLongBits(tpaFee) != Double.doubleToLongBits(other.tpaFee))
			return false;
		if (Double.doubleToLongBits(transactionFees) != Double.doubleToLongBits(other.transactionFees))
			return false;
		if (travellerInfo == null) {
			if (other.travellerInfo != null)
				return false;
		} else if (!travellerInfo.equals(other.travellerInfo))
			return false;
		if (ulinkEdit != other.ulinkEdit)
			return false;
		if (ulinkStatus != other.ulinkStatus)
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	
}
