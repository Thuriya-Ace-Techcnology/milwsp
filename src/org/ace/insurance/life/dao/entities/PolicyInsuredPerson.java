package org.ace.insurance.life.dao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.ManyToOne;
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


import org.ace.insurance.common.Gender;
import org.ace.insurance.common.IdType;
import org.ace.insurance.common.Name;
import org.ace.insurance.common.Plans;
import org.ace.insurance.common.Qualification;
import org.ace.insurance.common.ResidentAddress;
import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.insurance.common.Utils;
import org.ace.insurance.life.dto.BeneficiariesInfoDTO;
import org.ace.insurance.life.dto.InsuredPersonAddOnDTO;
import org.ace.insurance.life.dto.InsuredPersonInfoDTO;
import org.ace.insurance.life.enums.ClaimStatus;
import org.ace.insurance.life.enums.ClassificationOfHealth;
import org.ace.insurance.life.enums.EndorsementStatus;
import org.ace.insurance.life.enums.SumInsuredType;
import org.ace.insurance.life.interfaces.IInsuredItem;
import org.ace.insurance.product.Product;
import org.ace.insurance.system.common.country.Country;
import org.ace.insurance.system.common.currency.Currency;
import org.ace.insurance.system.common.occupation.Occupation;
import org.ace.insurance.system.common.relationship.RelationShip;
import org.ace.java.component.idgen.service.IDInterceptor;

@Entity
@Table(name = TableName.LIFEPOLICYINSUREDPERSON)
@TableGenerator(name = "LPOLINSURPERSON_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "LPOLINSURPERSON_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "PolicyInsuredPerson.findAll", query = "SELECT s FROM PolicyInsuredPerson s "),
		@NamedQuery(name = "PolicyInsuredPerson.findById", query = "SELECT s FROM PolicyInsuredPerson s WHERE s.id = :id"),
		@NamedQuery(name = "PolicyInsuredPerson.updateClaimStatus", query = "UPDATE PolicyInsuredPerson p SET p.claimStatus = :claimStatus WHERE p.id = :id") })
@EntityListeners(IDInterceptor.class)
public class PolicyInsuredPerson implements IInsuredItem, Serializable {
	private static final long serialVersionUID = -1810680158208016018L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LPOLINSURPERSON_GEN")
	private String id;

	@Column(name = "AGE")
	private int age;
	private double sumInsured;
	private double basicTermPremium;
	private double premium;
	private double addOnTermPremium;
	private double endorsementNetBasicPremium;;
	private double endorsementNetAddonPremium;
	private double interest;
	@Column(name = "INPERSONCODENO")
	private String insPersonCodeNo;
	@Column(name = "INPERSONGROUPCODENO")
	private String inPersonGroupCodeNo;
	private String initialId;
	private String idNo;
	private String fatherName;
	private String parentName;
	private String parentIdNo;
	@Enumerated(value = EnumType.STRING)
	private IdType parentIdType;
	private Date parentDOB;
	private int unit;
	private int weight;
	private int height;
	private String phone;
	private double premiumRate;
	
	private String birthAddress;
	private String personalId;
	private String email;
	private double salary;
	private boolean armyOfficer;
	private String idNoMM;
	private String parentIdNoMM;
	
	@Transient
	private double totalSumInsured;


	@Transient
	private double totalTermPremium;
	
	
	private int groupCount;


	@Column(name = "LABOUR_CARD_NO")
	private String labourCardNo;

	@Column(name = "IDENTIFICATION_CARD_NO")
	private String identificationCardNo;

	@Column(name = "ISSUE_OF_LABOUR_DATE")
	private Date issueOfLabourDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUALIFICATIONID", referencedColumnName = "ID")
	private Qualification qualification;

	@Column(name = "FACTORY_ADDRESS")
	private String factoryAddress;

	@Column(name = "ISSUE_DATE_OF_IDCARD")
	private Date issueDateOfIdCard;

	@Column(name = "PLACE_OF_PASSPORT")
	private String placeOfPassport;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTRYID", referencedColumnName = "ID")
	private Country country;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCYID", referencedColumnName = "ID")
	private Currency currency;

	private String passportNo;

	@Column(name = "ISSUE_DATE_OF_PASSPORT")
	private Date issueDateOfPassport;

	@Column(name = "VISIBLE_IDENTIFICATION")
	private String visibleIdentification;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	@Enumerated(value = EnumType.STRING)
	private IdType idType;

	@Enumerated(EnumType.STRING)
	private EndorsementStatus endorsementStatus;

	@Enumerated(EnumType.STRING)
	private ClaimStatus claimStatus;

	@Enumerated(value = EnumType.STRING)
	private ClassificationOfHealth clsOfHealth;

	@Embedded
	private ResidentAddress residentAddress;
	
	@Enumerated(value = EnumType.STRING)
	private SumInsuredType sumInsuredType;

	@Embedded
	private Name name;
	
	
	private String cdcNo;
	private String position;
	
	private String oceanlinerName;
	private String vesselName;
	
	private boolean isEdit;
	private boolean isReplace;
	private String seamanNrc;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTID", referencedColumnName = "ID")
	private Product product;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OCCUPATIONID", referencedColumnName = "ID")
	private Occupation occupation;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RISKYOCCUPATIONID", referencedColumnName = "ID")
	private RiskyOccupation riskyOccupation;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMERID", referencedColumnName = "ID")
	private Customer customer;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPESOFSPORTID", referencedColumnName = "ID")
	private TypesOfSport typesOfSport;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RELATIONSHIPID", referencedColumnName = "ID")
	private RelationShip relationship;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCHOOLID", referencedColumnName = "ID")
	private School school;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GRATEINFOID", referencedColumnName = "ID")
	private GradeInfo gradeInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIFEPOLICYID", referencedColumnName = "ID")
	private LifePolicy lifePolicy;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "policyInsuredPerson", orphanRemoval = true)
	private List<PolicyInsuredPersonAttachment> attachmentList;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "HOLDERID", referencedColumnName = "ID")
	private List<Attachment> birthCertificateAttachment;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "policyInsuredPerson", orphanRemoval = true)
	private List<PolicyInsuredPersonAddon> policyInsuredPersonAddOnList;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "REFERENCEID", referencedColumnName = "ID")
	private List<PolicyInsuredPersonKeyFactorValue> policyInsuredPersonkeyFactorValueList;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "policyInsuredPerson", orphanRemoval = true)
	private List<PolicyInsuredPersonBeneficiaries> policyInsuredPersonBeneficiariesList;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OFFICE_ID")
	private Office office;
	
	@Enumerated(value = EnumType.STRING
			)
	private Plans plans;

	public PolicyInsuredPerson() {
	}

	public PolicyInsuredPerson(ProposalInsuredPerson insuredPerson) {
		this.dateOfBirth = insuredPerson.getDateOfBirth();
		this.sumInsured = insuredPerson.getProposedSumInsured();
		this.product = insuredPerson.getProduct();
		this.premium = insuredPerson.getProposedPremium();
		this.basicTermPremium = insuredPerson.getBasicTermPremium();
		this.idNo = insuredPerson.getIdNo();
		this.idType = insuredPerson.getIdType();
		this.name = insuredPerson.getName();
		this.gender = insuredPerson.getGender();
		this.residentAddress = insuredPerson.getResidentAddress();
		this.fatherName = insuredPerson.getFatherName();
		this.age = insuredPerson.getAge();		
		this.passportNo = insuredPerson.getPassportNo();
		this.cdcNo=insuredPerson.getCdcNo();
		this.position=insuredPerson.getPosition();
		this.oceanlinerName=insuredPerson.getOceanlinerName();
		this.vesselName=insuredPerson.getVesselName();
		this.plans = insuredPerson.getPlans();
		for (InsuredPersonKeyFactorValue keyFactorValue : insuredPerson.getKeyFactorValueList()) {
			addPolicyInsuredPersonKeyFactorValue(new PolicyInsuredPersonKeyFactorValue(keyFactorValue));
		}
		for (InsuredPersonBeneficiaries insuredPersonBeneficiaries : insuredPerson.getInsuredPersonBeneficiariesList()) {
			addInsuredPersonBeneficiaries(new PolicyInsuredPersonBeneficiaries(insuredPersonBeneficiaries));
		}
	}

	public PolicyInsuredPerson(PolicyInsuredPersonHistory history) {
		this.age = history.getAge();
		this.sumInsured = history.getSumInsured();
		this.premium = history.getPremium();
		this.basicTermPremium = history.getBasicTermPremium();
		this.addOnTermPremium = history.getAddOnTermPremium();
		this.endorsementNetBasicPremium = history.getEndorsementNetBasicPremium();
		this.endorsementNetAddonPremium = history.getEndorsementNetAddonPremium();
		this.interest = history.getInterest();
		this.weight = history.getWeight();
		this.height = history.getHeight();
		this.premiumRate = history.getPremiumRate();
		this.insPersonCodeNo = history.getInPersonCodeNo();
		this.inPersonGroupCodeNo = history.getInPersonGroupCodeNo();
		this.initialId = history.getInitialId();
		this.idNo = history.getIdNo();
		this.seamanNrc = history.getSeamanNrc() == null ? "" : history.getSeamanNrc();
		this.fatherName = history.getFatherName();
		this.dateOfBirth = history.getDateOfBirth();
		this.gender = history.getGender();
		this.idType = history.getIdType();
		this.endorsementStatus = history.getEndorsementStatus();
		this.claimStatus = history.getClaimStatus();
		this.clsOfHealth = history.getClsOfHealth();
		this.residentAddress = history.getResidentAddress();
		this.name = history.getName();
		this.product = history.getProduct();
		this.occupation = history.getOccupation();
		this.customer = history.getCustomer();
		this.typesOfSport = history.getTypesOfSport();
		this.relationship = history.getRelationship();
		this.riskyOccupation = history.getRiskyOccupation();
		this.unit = history.getUnit();
		// this.phone = history.getph
		this.relationship = history.getRelationship();
		this.parentName = history.getParentName();
		this.parentDOB = history.getParentDOB();
		this.parentIdType = history.getParentIdType();
		this.parentIdNo = history.getParentIdNo();
		this.school = history.getSchool();
		this.gradeInfo = history.getGradeInfo();
		this.sumInsuredType = history.getSumInsuredType();
		//TODO
//		this.birthAddress= dto.getBirthAddress();
//		this.personalId = dto.getPersonalId();
//		this.email = dto.getEmail();
//		this.salary = dto.getSalary();
//		this.visibleIdentification = dto.getVisibleIdentification();
		for (Attachment attachment : history.getBirthCertificateAttachment()) {
			addBirthCertificateAttachment(new Attachment(attachment));
		}
		for (PolicyInsuredPersonAttachmentHistory attachment : history.getAttachmentList()) {
			addAttachment(new PolicyInsuredPersonAttachment(attachment));
		}

		for (PolicyInsuredPersonKeyFactorValueHistory keyFactorValue : history.getPolicyInsuredPersonkeyFactorValueList()) {
			addPolicyInsuredPersonKeyFactorValue(new PolicyInsuredPersonKeyFactorValue(keyFactorValue));
		}

		for (PolicyInsuredPersonBeneficiariesHistory insuredPersonBeneficiaries : history.getPolicyInsuredPersonBeneficiariesList()) {
			addInsuredPersonBeneficiaries(new PolicyInsuredPersonBeneficiaries(insuredPersonBeneficiaries));
		}

		for (PolicyInsuredPersonAddonHistory addOn : history.getPolicyInsuredPersonAddOnList()) {
			addInsuredPersonAddOn(new PolicyInsuredPersonAddon(addOn));
		}

	}


	public PolicyInsuredPerson(Date dateOfBirth, double sumInsured, Product product, LifePolicy lifePolicy, int periodMonth, Date startDate, Date endDate, double premium,
			double endorsementNetBasicPremium, double endorsementNetAddonPremium, double interest, String insPersonCodeNo, EndorsementStatus endorsementStatus,
			String inPersonGroupCodeNo) {
		this.endorsementStatus = endorsementStatus;
		this.dateOfBirth = dateOfBirth;
		this.sumInsured = sumInsured;
		this.product = product;
		this.lifePolicy = lifePolicy;
		this.premium = premium;
		this.endorsementNetBasicPremium = endorsementNetAddonPremium;
		this.endorsementNetAddonPremium = endorsementNetAddonPremium;
		this.insPersonCodeNo = insPersonCodeNo;
		this.interest = interest;
		this.inPersonGroupCodeNo = inPersonGroupCodeNo;
	}

	public PolicyInsuredPerson(Date dateOfBirth, double sumInsured, Product product, LifePolicy lifePolicy, int periodMonth, Date startDate, Date endDate, double premium,
			String initialId, String idNo, IdType idType, Name name, Gender gender, ResidentAddress residentAddress, Occupation occupation, String fatherName,
			double endorsementNetBasicPremium, double endorsementNetAddonPremium, double interest, EndorsementStatus status, String insPersonCodeNo, Customer customer, int age,
			String inPersonGroupCodeNo, int paymentTerm, double basicTermPremium, double addOnTermPremium, ClaimStatus claimStatus) {
		this.dateOfBirth = dateOfBirth;
		this.sumInsured = sumInsured;
		this.product = product;
		this.lifePolicy = lifePolicy;
		this.premium = premium;
		this.initialId = initialId;
		this.idNo = idNo;
		this.idType = idType;
		this.name = name;
		this.residentAddress = residentAddress;
		this.gender = gender;
		this.occupation = occupation;
		this.fatherName = fatherName;
		this.endorsementNetBasicPremium = endorsementNetBasicPremium;
		this.endorsementNetAddonPremium = endorsementNetAddonPremium;
		this.interest = interest;
		this.endorsementStatus = status;
		this.insPersonCodeNo = insPersonCodeNo;
		this.customer = customer;
		this.age = age;
		this.inPersonGroupCodeNo = inPersonGroupCodeNo;
		this.basicTermPremium = basicTermPremium;
		this.addOnTermPremium = addOnTermPremium;
		this.claimStatus = claimStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdentificationCardNo() {
		return identificationCardNo;
	}

	public void setIdentificationCardNo(String identificationCardNo) {
		this.identificationCardNo = identificationCardNo;
	}

	public void overwriteId(String id) {
		this.id = id;
	}

	public UserRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public double getBasicTermPremium() {
		return basicTermPremium;
	}

	public void setBasicTermPremium(double basicTermPremium) {
		this.basicTermPremium = basicTermPremium;
	}

	public double getAddOnTermPremium() {
		return addOnTermPremium;
	}

	public void setAddOnTermPremium(double addOnTermPremium) {
		this.addOnTermPremium = addOnTermPremium;
	}

	public EndorsementStatus getEndorsementStatus() {
		return endorsementStatus;
	}

	public void setEndorsementStatus(EndorsementStatus endorsementStatus) {
		this.endorsementStatus = endorsementStatus;
	}

	public double getEndorsementNetBasicPremium() {
		return endorsementNetBasicPremium;
	}

	public void setEndorsementNetBasicPremium(double endorsementNetBasicPremium) {
		this.endorsementNetBasicPremium = endorsementNetBasicPremium;
	}

	public double getEndorsementNetAddonPremium() {
		return endorsementNetAddonPremium;
	}

	public void setEndorsementNetAddonPremium(double endorsementNetAddonPremium) {
		this.endorsementNetAddonPremium = endorsementNetAddonPremium;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public String getInsPersonCodeNo() {
		return insPersonCodeNo;
	}

	public void setInsPersonCodeNo(String insPersonCodeNo) {
		this.insPersonCodeNo = insPersonCodeNo;
	}

	public String getInPersonGroupCodeNo() {
		return inPersonGroupCodeNo;
	}

	public void setInPersonGroupCodeNo(String inPersonGroupCodeNo) {
		this.inPersonGroupCodeNo = inPersonGroupCodeNo;
	}

	public List<PolicyInsuredPersonAttachment> getAttachmentList() {
		if (this.attachmentList == null) {
			this.attachmentList = new ArrayList<PolicyInsuredPersonAttachment>();
		}
		return this.attachmentList;
	}

	public void setAttachmentList(List<PolicyInsuredPersonAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LifePolicy getLifePolicy() {
		return lifePolicy;
	}

	public void setLifePolicy(LifePolicy lifePolicy) {
		this.lifePolicy = lifePolicy;
	}

	public List<PolicyInsuredPersonAddon> getPolicyInsuredPersonAddOnList() {
		if (policyInsuredPersonAddOnList == null) {
			policyInsuredPersonAddOnList = new ArrayList<PolicyInsuredPersonAddon>();
		}
		return policyInsuredPersonAddOnList;
	}

	public void setPolicyInsuredPersonAddOnList(List<PolicyInsuredPersonAddon> policyInsuredPersonAddOnList) {
		this.policyInsuredPersonAddOnList = policyInsuredPersonAddOnList;
	}

	public List<PolicyInsuredPersonKeyFactorValue> getPolicyInsuredPersonkeyFactorValueList() {
		if (policyInsuredPersonkeyFactorValueList == null) {
			policyInsuredPersonkeyFactorValueList = new ArrayList<PolicyInsuredPersonKeyFactorValue>();
		}
		return policyInsuredPersonkeyFactorValueList;
	}

	public void setPolicyInsuredPersonkeyFactorValueList(List<PolicyInsuredPersonKeyFactorValue> policyInsuredPersonkeyFactorValueList) {
		this.policyInsuredPersonkeyFactorValueList = policyInsuredPersonkeyFactorValueList;
	}

	public String getKeyFactorValueListForDetails() {
		StringBuffer buffer = new StringBuffer();
		if (getPolicyInsuredPersonkeyFactorValueList().size() > 0) {
			String id = getPolicyInsuredPersonkeyFactorValueList().get(getPolicyInsuredPersonkeyFactorValueList().size() - 1).getKeyFactor().getId();
			for (PolicyInsuredPersonKeyFactorValue keyfactorValue : getPolicyInsuredPersonkeyFactorValueList()) {
				buffer.append(keyfactorValue.getKeyFactor().getValue()).append(" - ").append(keyfactorValue.getValue())
						.append(id == keyfactorValue.getKeyFactor().getId() ? "" : " , ");
			}
		}
		return buffer.toString();
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public double getAddOnPremium() {
		double premium = 0.0;
		if (policyInsuredPersonAddOnList != null && !policyInsuredPersonAddOnList.isEmpty()) {
			for (PolicyInsuredPersonAddon pia : policyInsuredPersonAddOnList) {
				premium = Utils.getTwoDecimalPoint(premium + pia.getPremium());
			}
		}
		return premium;
	}

	public double getTotalPremium() {
		return Utils.getTwoDecimalPoint(premium + getAddOnPremium());
	}

	public double getTotalTermPermium() {
		return Utils.getTwoDecimalPoint(getBasicTermPremium() + getAddOnTermPremium());
	}

	public double getAddOnSumInsure() {
		double premium = 0.0;
		if (policyInsuredPersonAddOnList != null && !policyInsuredPersonAddOnList.isEmpty()) {
			for (PolicyInsuredPersonAddon pia : policyInsuredPersonAddOnList) {
				premium = premium + pia.getSumInsured();
			}
		}
		return premium;
	}

	public double getSuminsuredPerUnit() {
		double suminsuredPerUnit = 0.0;
		suminsuredPerUnit = unit * product.getSumInsuredPerUnit();
		return suminsuredPerUnit;
	}

	public List<PolicyInsuredPersonBeneficiaries> getPolicyInsuredPersonBeneficiariesList() {
		if (this.policyInsuredPersonBeneficiariesList == null) {
			this.policyInsuredPersonBeneficiariesList = new ArrayList<PolicyInsuredPersonBeneficiaries>();
		}
		return this.policyInsuredPersonBeneficiariesList;
	}

	public void setPolicyInsuredPersonBeneficiariesList(List<PolicyInsuredPersonBeneficiaries> policyInsuredPersonBeneficiariesList) {
		this.policyInsuredPersonBeneficiariesList = policyInsuredPersonBeneficiariesList;
	}

	public void addAttachment(PolicyInsuredPersonAttachment attachment) {
		attachment.setPolicyInsuredPerson(this);
		getAttachmentList().add(attachment);
	}

	public void addBirthCertificateAttachment(Attachment attachment) {
		getBirthCertificateAttachment().add(attachment);
	}

	public void addInsuredPersonAddOn(PolicyInsuredPersonAddon policyInsuredPersonAddOn) {
		policyInsuredPersonAddOn.setPolicyInsuredPersonInfo(this);
		getPolicyInsuredPersonAddOnList().add(policyInsuredPersonAddOn);
	}

	public void addPolicyInsuredPersonKeyFactorValue(PolicyInsuredPersonKeyFactorValue keyFactorValue) {
		// keyFactorValue.setPolicyInsuredPersonInfo(this);
		getPolicyInsuredPersonkeyFactorValueList().add(keyFactorValue);
	}

	public void addInsuredPersonBeneficiaries(PolicyInsuredPersonBeneficiaries policyInsuredPersonBeneficiaries) {
		policyInsuredPersonBeneficiaries.setPolicyInsuredPerson(this);
		getPolicyInsuredPersonBeneficiariesList().add(policyInsuredPersonBeneficiaries);
	}

	public ClaimStatus getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(ClaimStatus claimStatus) {
		this.claimStatus = claimStatus;
	}

	public ClassificationOfHealth getClsOfHealth() {
		return clsOfHealth;
	}

	public void setClsOfHealth(ClassificationOfHealth clsOfHealth) {
		this.clsOfHealth = clsOfHealth;
	}

	public void setPaymentTimes(int paymentTimes) {
		// do nothing
	}

	public int getAgeForNextYear() {
		Calendar cal_1 = Calendar.getInstance();
		int currentYear = cal_1.get(Calendar.YEAR);
		Calendar cal_2 = Calendar.getInstance();
		cal_2.setTime(dateOfBirth);
		cal_2.set(Calendar.YEAR, currentYear);

		if (new Date().after(cal_2.getTime())) {
			Calendar cal_3 = Calendar.getInstance();
			cal_3.setTime(dateOfBirth);
			int year_1 = cal_3.get(Calendar.YEAR);
			int year_2 = cal_1.get(Calendar.YEAR) + 1;
			return year_2 - year_1;
		} else {
			Calendar cal_3 = Calendar.getInstance();
			cal_3.setTime(dateOfBirth);
			int year_1 = cal_3.get(Calendar.YEAR);
			int year_2 = cal_1.get(Calendar.YEAR);
			return year_2 - year_1;
		}
	}

	/**
	 * @see org.ace.insurance.common.interfaces.IPolicy#getTotalPremium()
	 */

	public String getInitialId() {
		return initialId;
	}

	public void setInitialId(String initialId) {
		this.initialId = initialId;
	}

	public String getIdNo() {
		return idNo;
	}

	public String getIdNoForView() {
		if (idNo == null || idNo.isEmpty()) {
			return "Still Applying";
		}
		return idNo;
	}
	
	public String getIdNoMMForView() {
		if (IdType.NRCNO.equals(idType)) {
			return idNoMM;
		}else if(IdType.PASSPORTNO.equals(idType) || IdType.FRCNO.equals(idType)) {
			return idNo;
		}else {
			return "-";
		}
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public IdType getIdType() {
		return idType;
	}

	public void setIdType(IdType idType) {
		this.idType = idType;
	}

	public ResidentAddress getResidentAddress() {
		return residentAddress;
	}

	public void setResidentAddress(ResidentAddress residentAddress) {
		this.residentAddress = residentAddress;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getFullAddress() {
		String result = "";
		if (residentAddress != null) {
			if (residentAddress.getResidentAddress() != null && !residentAddress.getResidentAddress().isEmpty()) {
				result = result + residentAddress.getResidentAddress();
			}
			if (residentAddress.getTownship() != null && residentAddress.getTownship().getFullTownShip() != null && !residentAddress.getTownship().getFullTownShip().isEmpty()) {
				result = result + ", " + residentAddress.getTownship().getFullTownShip();
			}
		}
		return result;
	}

	public String getFullName() {
		String result = "";
		if (name != null) {
			if (initialId != null && !initialId.isEmpty()) {
				result = initialId.trim();
			}
			if(initialId != null && !initialId.isEmpty()) {
				if (name.getFirstName() != null && !name.getFirstName().isEmpty()) {
					result = result + " " + name.getFirstName().trim();
				}
			} else {
				if (name.getFirstName() != null && !name.getFirstName().isEmpty()) {
					result = name.getFirstName().trim();
				}
				
			}			
			if (name.getMiddleName() != null && !name.getMiddleName().isEmpty()) {
				result = result + " " + name.getMiddleName().trim();
			}
			if (name.getLastName() != null && !name.getLastName().isEmpty()) {
				result = result + " " + name.getLastName().trim();
			}
		}
		return result;
	}

	public String getJSPName() {
		String result = "";
		if (name != null) {

			if (name.getFirstName() != null && !name.getFirstName().isEmpty()) {
				result = result + " " + name.getFirstName().trim();
			}
			if (name.getMiddleName() != null && !name.getMiddleName().isEmpty()) {
				result = result + " " + name.getMiddleName().trim();
			}
			if (name.getLastName() != null && !name.getLastName().isEmpty()) {
				result = result + " " + name.getLastName().trim();
			}
		}
		return result;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public String getOccupationName() {
		if (occupation != null)
			return occupation.getName();
		else
			return "-";
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public TypesOfSport getTypesOfSport() {
		return typesOfSport;
	}

	public String getTypesOfSportName() {
		if (typesOfSport != null)
			return typesOfSport.getName();
		else
			return "";
	}

	public void setTypesOfSport(TypesOfSport typesOfSport) {
		this.typesOfSport = typesOfSport;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getPremiumRate() {
		return premiumRate;
	}

	public void setPremiumRate(double premiumRate) {
		this.premiumRate = premiumRate;
	}

	public RelationShip getRelationship() {
		return relationship;
	}

	public void setRelationship(RelationShip relationship) {
		this.relationship = relationship;
	}

	public RiskyOccupation getRiskyOccupation() {
		return riskyOccupation;
	}

	public void setRiskyOccupation(RiskyOccupation riskyOccupation) {
		this.riskyOccupation = riskyOccupation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneForView() {
		if (phone != null && !phone.isEmpty())
			return phone;
		else
			return "-";
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentIdNo() {
		return parentIdNo;
	}

	public void setParentIdNo(String parentIdNo) {
		this.parentIdNo = parentIdNo;
	}

	public IdType getParentIdType() {
		return parentIdType;
	}

	public void setParentIdType(IdType parentIdType) {
		this.parentIdType = parentIdType;
	}

	public Date getParentDOB() {
		return parentDOB;
	}

	public void setParentDOB(Date parentDOB) {
		this.parentDOB = parentDOB;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public GradeInfo getGradeInfo() {
		return gradeInfo;
	}

	public void setGradeInfo(GradeInfo gradeInfo) {
		this.gradeInfo = gradeInfo;
	}

	public List<Attachment> getBirthCertificateAttachment() {
		if (this.birthCertificateAttachment == null) {
			this.birthCertificateAttachment = new ArrayList<Attachment>();
		}
		return birthCertificateAttachment;
	}

	public void setBirthCertificateAttachment(List<Attachment> birthCertificateAttachment) {
		this.birthCertificateAttachment = birthCertificateAttachment;
	}

	public String getSchoolName() {
		if (school != null)
			return school.getName();
		return "-";
	}

	public String getSchoolAddress() {
		if (school != null && school.getAddress() != null)
			return school.getFullAddress();
		return "-";
	}

	public String getGradeInfoName() {
		if (gradeInfo != null)
			return gradeInfo.getName();
		return "-";
	}
	
	public String getBirthAddress() {
		return birthAddress;
	}

	public void setBirthAddress(String birthAddress) {
		this.birthAddress = birthAddress;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getLabourCardNo() {
		return labourCardNo;
	}

	public void setLabourCardNo(String labourCardNo) {
		this.labourCardNo = labourCardNo;
	}

	public Date getIssueOfLabourDate() {
		return issueOfLabourDate;
	}

	public void setIssueOfLabourDate(Date issueOfLabourDate) {
		this.issueOfLabourDate = issueOfLabourDate;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public String getFactoryAddress() {
		return factoryAddress;
	}

	public void setFactoryAddress(String factoryAddress) {
		this.factoryAddress = factoryAddress;
	}

	public Date getIssueDateOfIdCard() {
		return issueDateOfIdCard;
	}

	public void setIssueDateOfIdCard(Date issueDateOfIdCard) {
		this.issueDateOfIdCard = issueDateOfIdCard;
	}

	public String getPlaceOfPassport() {
		return placeOfPassport;
	}

	public void setPlaceOfPassport(String placeOfPassport) {
		this.placeOfPassport = placeOfPassport;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public Date getIssueDateOfPassport() {
		return issueDateOfPassport;
	}

	public void setIssueDateOfPassport(Date issueDateOfPassport) {
		this.issueDateOfPassport = issueDateOfPassport;
	}

	public String getVisibleIdentification() {
		return visibleIdentification;
	}

	public void setVisibleIdentification(String visibleIdentification) {
		this.visibleIdentification = visibleIdentification;
	}
	
	public SumInsuredType getSumInsuredType() {
		return sumInsuredType;
	}

	public void setSumInsuredType(SumInsuredType sumInsuredType) {
		this.sumInsuredType = sumInsuredType;
	}

	public String getCdcNo() {
		return cdcNo;
	}

	public void setCdcNo(String cdcNo) {
		this.cdcNo = cdcNo;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public boolean isReplace() {
		return isReplace;
	}

	public void setReplace(boolean isReplace) {
		this.isReplace = isReplace;
	}

	public String getOceanlinerName() {
		return oceanlinerName;
	}

	public void setOceanlinerName(String oceanlinerName) {
		this.oceanlinerName = oceanlinerName;
	}

	public String getVesselName() {
		return vesselName;
	}

	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(addOnTermPremium);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + age;
		temp = Double.doubleToLongBits(basicTermPremium);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((claimStatus == null) ? 0 : claimStatus.hashCode());
		result = prime * result + ((clsOfHealth == null) ? 0 : clsOfHealth.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		temp = Double.doubleToLongBits(endorsementNetAddonPremium);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(endorsementNetBasicPremium);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((endorsementStatus == null) ? 0 : endorsementStatus.hashCode());
		result = prime * result + ((fatherName == null) ? 0 : fatherName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + height;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idNo == null) ? 0 : idNo.hashCode());
		result = prime * result + ((idType == null) ? 0 : idType.hashCode());
		result = prime * result + ((inPersonGroupCodeNo == null) ? 0 : inPersonGroupCodeNo.hashCode());
		result = prime * result + ((initialId == null) ? 0 : initialId.hashCode());
		result = prime * result + ((insPersonCodeNo == null) ? 0 : insPersonCodeNo.hashCode());
		temp = Double.doubleToLongBits(interest);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((lifePolicy == null) ? 0 : lifePolicy.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((occupation == null) ? 0 : occupation.hashCode());
		temp = Double.doubleToLongBits(premium);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(premiumRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
		result = prime * result + ((relationship == null) ? 0 : relationship.hashCode());
		result = prime * result + ((residentAddress == null) ? 0 : residentAddress.hashCode());
		result = prime * result + ((riskyOccupation == null) ? 0 : riskyOccupation.hashCode());
		temp = Double.doubleToLongBits(sumInsured);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((typesOfSport == null) ? 0 : typesOfSport.hashCode());
		result = prime * result + ((labourCardNo == null) ? 0 : labourCardNo.hashCode());
		result = prime * result + ((identificationCardNo == null) ? 0 : identificationCardNo.hashCode());
		result = prime * result + ((qualification == null) ? 0 : qualification.hashCode());
		result = prime * result + ((factoryAddress == null) ? 0 : factoryAddress.hashCode());
		result = prime * result + ((placeOfPassport == null) ? 0 : placeOfPassport.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((issueOfLabourDate == null) ? 0 : issueOfLabourDate.hashCode());
		result = prime * result + ((issueDateOfIdCard == null) ? 0 : issueDateOfIdCard.hashCode());
		result = prime * result + ((issueDateOfPassport == null) ? 0 : issueDateOfPassport.hashCode());
		result = prime * result + ((passportNo == null) ? 0 : passportNo.hashCode());
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + unit;
		result = prime * result + version;
		result = prime * result + weight;
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
		PolicyInsuredPerson other = (PolicyInsuredPerson) obj;
		if (Double.doubleToLongBits(addOnTermPremium) != Double.doubleToLongBits(other.addOnTermPremium))
			return false;
		if (age != other.age)
			return false;
		if (Double.doubleToLongBits(basicTermPremium) != Double.doubleToLongBits(other.basicTermPremium))
			return false;
		if (claimStatus != other.claimStatus)
			return false;
		if (clsOfHealth != other.clsOfHealth)
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (Double.doubleToLongBits(endorsementNetAddonPremium) != Double.doubleToLongBits(other.endorsementNetAddonPremium))
			return false;
		if (Double.doubleToLongBits(endorsementNetBasicPremium) != Double.doubleToLongBits(other.endorsementNetBasicPremium))
			return false;
		if (endorsementStatus != other.endorsementStatus)
			return false;
		if (fatherName == null) {
			if (other.fatherName != null)
				return false;
		} else if (!fatherName.equals(other.fatherName))
			return false;
		if (gender != other.gender)
			return false;
		if (height != other.height)
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
		if (inPersonGroupCodeNo == null) {
			if (other.inPersonGroupCodeNo != null)
				return false;
		} else if (!inPersonGroupCodeNo.equals(other.inPersonGroupCodeNo))
			return false;
		if (initialId == null) {
			if (other.initialId != null)
				return false;
		} else if (!initialId.equals(other.initialId))
			return false;
		if (insPersonCodeNo == null) {
			if (other.insPersonCodeNo != null)
				return false;
		} else if (!insPersonCodeNo.equals(other.insPersonCodeNo))
			return false;
		if (Double.doubleToLongBits(interest) != Double.doubleToLongBits(other.interest))
			return false;
		if (lifePolicy == null) {
			if (other.lifePolicy != null)
				return false;
		} else if (!lifePolicy.equals(other.lifePolicy))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (occupation == null) {
			if (other.occupation != null)
				return false;
		} else if (!occupation.equals(other.occupation))
			return false;
		if (Double.doubleToLongBits(premium) != Double.doubleToLongBits(other.premium))
			return false;
		if (Double.doubleToLongBits(premiumRate) != Double.doubleToLongBits(other.premiumRate))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (recorder == null) {
			if (other.recorder != null)
				return false;
		} else if (!recorder.equals(other.recorder))
			return false;
		if (relationship == null) {
			if (other.relationship != null)
				return false;
		} else if (!relationship.equals(other.relationship))
			return false;
		if (residentAddress == null) {
			if (other.residentAddress != null)
				return false;
		} else if (!residentAddress.equals(other.residentAddress))
			return false;
		if (riskyOccupation == null) {
			if (other.riskyOccupation != null)
				return false;
		} else if (!riskyOccupation.equals(other.riskyOccupation))
			return false;
		if (Double.doubleToLongBits(sumInsured) != Double.doubleToLongBits(other.sumInsured))
			return false;
		if (typesOfSport == null) {
			if (other.typesOfSport != null)
				return false;
		} else if (!typesOfSport.equals(other.typesOfSport))
			return false;
		if (labourCardNo == null) {
			if (other.labourCardNo != null)
				return false;
		} else if (!labourCardNo.equals(other.labourCardNo))
			return false;
		if (identificationCardNo == null) {
			if (other.identificationCardNo != null)
				return false;
		} else if (!identificationCardNo.equals(other.identificationCardNo))
			return false;
		if (placeOfPassport == null) {
			if (other.placeOfPassport != null)
				return false;
		} else if (!placeOfPassport.equals(other.placeOfPassport))
			return false;
		if (factoryAddress == null) {
			if (other.factoryAddress != null)
				return false;
		} else if (!factoryAddress.equals(other.factoryAddress))
			return false;
		if (qualification == null) {
			if (other.qualification != null)
				return false;
		} else if (!qualification.equals(other.qualification))
			return false;
		if (issueOfLabourDate == null) {
			if (other.issueOfLabourDate != null)
				return false;
		} else if (!issueOfLabourDate.equals(other.issueOfLabourDate))
			return false;
		if (issueDateOfIdCard == null) {
			if (other.issueDateOfIdCard != null)
				return false;
		} else if (!issueDateOfIdCard.equals(other.issueDateOfIdCard))
			return false;
		if (placeOfPassport == null) {
			if (other.placeOfPassport != null)
				return false;
		} else if (!placeOfPassport.equals(other.placeOfPassport))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (passportNo == null) {
			if (other.passportNo != null)
				return false;
		} else if (!passportNo.equals(other.passportNo))
			return false;
		if (issueDateOfPassport == null) {
			if (other.issueDateOfPassport != null)
				return false;
		} else if (!issueDateOfPassport.equals(other.issueDateOfPassport))
			return false;
		if (unit != other.unit)
			return false;
		if (version != other.version)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}
	public String getSeamanNrc() {
		return seamanNrc;
		
	}

	public void setSeamanNrc(String seamanNrc) {
		this.seamanNrc = seamanNrc;
		
	}

	public boolean isArmyOfficer() {
		return armyOfficer;
	}

	public void setArmyOfficer(boolean isArmyOfficer) {
		this.armyOfficer = isArmyOfficer;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getIdNoMM() {
		return idNoMM;
		
	}

	public void setIdNOMM(String idNoMM) {
		this.idNoMM = idNoMM;
		
	}
	

	public String getParentIdNoMM() {
		return parentIdNoMM;
	}

	public void setParentIdNoMM(String parentIdNoMM) {
		this.parentIdNoMM = parentIdNoMM;
	}
	
	public double getTotalSumInsured() {
		return totalSumInsured;
	}

	public void setTotalSumInsured(double totoalSumInsured) {
		this.totalSumInsured = totoalSumInsured;
	}

	public double getTotalTermPremium() {
		return totalTermPremium;
	}

	public void setTotalTermPremium(double totalTermPremium) {
		this.totalTermPremium = totalTermPremium;
	}

	public int getGroupCount() {
		return groupCount;
		
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
		
	}

	public Plans getPlans() {
		return plans;
	}

	public void setPlans(Plans plans) {
		this.plans = plans;
	}
	
	

	
	

}
