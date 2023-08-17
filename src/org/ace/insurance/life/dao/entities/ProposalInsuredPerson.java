package org.ace.insurance.life.dao.entities;

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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;


import org.ace.insurance.common.Gender;
import org.ace.insurance.common.IdType;
import org.ace.insurance.common.Name;
import org.ace.insurance.common.Qualification;
import org.ace.insurance.common.ResidentAddress;
import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.insurance.common.Utils;
import org.ace.insurance.life.dto.BeneficiariesInfoDTO;
import org.ace.insurance.life.dto.InsuredPersonAddOnDTO;
import org.ace.insurance.life.dto.InsuredPersonInfoDTO;
import org.ace.insurance.life.enums.ClassificationOfHealth;
import org.ace.insurance.life.enums.EndorsementStatus;
import org.ace.insurance.life.enums.SumInsuredType;
import org.ace.insurance.medical.surveyAnswer.SurveyQuestionAnswer;
import org.ace.insurance.product.Product;
import org.ace.insurance.system.common.country.Country;
import org.ace.insurance.system.common.currency.Currency;
import org.ace.insurance.system.common.occupation.Occupation;
import org.ace.insurance.system.common.relationship.RelationShip;
import org.ace.java.component.idgen.service.IDInterceptor;

@Entity
@Table(name = TableName.PROPOSALINSUREDPERSON)
@TableGenerator(name = "PRLINSURPERSON_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "PRLINSURPERSON_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "ProposalInsuredPerson.findAll", query = "SELECT s FROM ProposalInsuredPerson s "),
		@NamedQuery(name = "ProposalInsuredPerson.findById", query = "SELECT s FROM ProposalInsuredPerson s WHERE s.id = :id") })
@EntityListeners(IDInterceptor.class)
public class ProposalInsuredPerson {
	private boolean approved;
	private boolean needMedicalCheckup;
	@Column(name = "AGE")
	private int age;
	private int unit;
	private double proposedSumInsured;
	private double proposedPremium;
	private double approvedSumInsured;
	private int approvedUnit;
	private double basicTermPremium;
	private double addOnTermPremium;
	private double endorsementNetBasicPremium;
	private double endorsementNetAddonPremium;
	private double interest;
	private int weight;
	private int height;
	private double premiumRate;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PRLINSURPERSON_GEN")
	private String id;

	private String rejectReason;
	@Column(name = "INPERSONCODENO")
	private String insPersonCodeNo;
	@Column(name = "INPERSONGROUPCODENO")
	private String inPersonGroupCodeNo;
	private String initialId;
	private String idNo;
	private String fatherName;
	private String phone;
	private String parentName;
	private String parentIdNo;
	@Enumerated(value = EnumType.STRING)
	private IdType parentIdType;
	private Date parentDOB;
	
	private String birthAddress;
	private String personalId;
	private String email;
	private double salary;
	private String cdcNo;
	private String position;
	private String seamanNrc;
	private String idNoMM;
	private String parentIdNoMM;

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

	@Transient
	private Boolean isPaidPremiumForPaidup = false;
	
	@Transient
	private int insuredId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfBirth;

	@Enumerated(EnumType.STRING)
	private EndorsementStatus endorsementStatus;

	@Enumerated(value = EnumType.STRING)
	private ClassificationOfHealth clsOfHealth;

	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	@Enumerated(value = EnumType.STRING)
	private IdType idType;
	
	@Enumerated(value = EnumType.STRING)
	private SumInsuredType sumInsuredType;

	@Embedded
	private ResidentAddress residentAddress;
	
	private String oceanlinerName;
	private String vesselName;

	@Embedded
	private Name name;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTID", referencedColumnName = "ID")
	private Product product;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPESOFSPORTID", referencedColumnName = "ID")
	private TypesOfSport typesOfSport;

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
	@JoinColumn(name = "RELATIONSHIPID", referencedColumnName = "ID")
	private RelationShip relationship;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIFEPROPOSALID", referencedColumnName = "ID")
	private LifeProposal lifeProposal;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCHOOLID", referencedColumnName = "ID")
	private School school;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GRATEINFOID", referencedColumnName = "ID")
	private GradeInfo gradeInfo;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proposalInsuredPerson", orphanRemoval = true)
	private List<InsuredPersonAttachment> attachmentList;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proposalInsuredPerson", orphanRemoval = true)
	private List<InsuredPersonAddon> insuredPersonAddOnList;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "proposalInsuredPerson", orphanRemoval = true)
	private List<InsuredPersonKeyFactorValue> keyFactorValueList;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proposalInsuredPerson", orphanRemoval = true)
	private List<InsuredPersonBeneficiaries> insuredPersonBeneficiariesList;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "LIFEINSUREDPERSONID", referencedColumnName = "ID")
	private List<SurveyQuestionAnswer> surveyQuestionAnswerList;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "INSUREDPERSONID", referencedColumnName = "ID")
	private List<InsuredPersonPolicyHistoryRecord> insuredPersonPolicyHistoryRecordList;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "HOLDERID", referencedColumnName = "ID")
	private List<Attachment> birthCertificateAttachment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OFFICE_ID")
	private Office office;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "proposalInsuredPerson", orphanRemoval = true)
	private Acquaintance acquaintance;


	
	
	private String generatedPolicyNo;

	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;
	
	
	private boolean armyOfficer;

	public ProposalInsuredPerson() {

	}

	public ProposalInsuredPerson(InsuredPersonInfoDTO dto) {
		this.approved = dto.isApprove();
		this.needMedicalCheckup = dto.isNeedMedicalCheckup();
		this.age = dto.getAgeForNextYear();
		this.proposedPremium = dto.getPremium();
		this.proposedSumInsured = dto.getSumInsuredInfo();
		this.approvedSumInsured = dto.getApprovedSumInsured();
		this.basicTermPremium = dto.getBasicTermPremium();
		this.addOnTermPremium = dto.getAddOnTermPremium();
		this.endorsementNetAddonPremium = dto.getEndorsementAddonPremium();
		this.endorsementNetBasicPremium = dto.getEndorsementBasicPremium();
		this.interest = dto.getInterest();
		this.weight = dto.getWeight();
		this.height = dto.getHeight();
		this.premiumRate = dto.getPremiumRate();
		this.rejectReason = dto.getRejectReason();
		this.insPersonCodeNo = dto.getInsPersonCodeNo();
		this.inPersonGroupCodeNo = dto.getInPersonGroupCodeNo();
		this.initialId = dto.getInitialId();
		this.idNo = dto.getFullIdNo();
		this.idNoMM = dto.getFullIdNoMM();
		this.seamanNrc = dto.getSeamanFullIdNo() == null ? "" : dto.getSeamanFullIdNo();
		this.fatherName = dto.getFatherName();
		this.dateOfBirth = dto.getDateOfBirth();
		this.endorsementStatus = dto.getEndorsementStatus();
		this.clsOfHealth = dto.getClassificationOfHealth();
		this.gender = dto.getGender();
		this.idType = dto.getIdType();
		this.residentAddress = dto.getResidentAddress();
		this.name = dto.getName();
		this.product = dto.getProduct();
		this.typesOfSport = dto.getTypesOfSport();
		this.occupation = dto.getOccupation();
		this.unit = dto.getUnit();
		this.approvedUnit = dto.getApprovedUnit();
		this.riskyOccupation = dto.getRiskyOccupation();
		this.surveyQuestionAnswerList = dto.getSurveyQuestionAnswerList();
		// override
		this.customer = dto.getCustomer();
		this.phone = dto.getPhone();
		this.relationship = dto.getRelationship();
		this.parentName = dto.getParentName();
		this.parentDOB = dto.getParentDOB();
		this.parentIdType = dto.getParentIdType();
		this.parentIdNo = dto.getParentIdNo();
		this.school = dto.getSchool();
		this.gradeInfo = dto.getGradeInfo();
		
		this.birthAddress= dto.getBirthAddress();
		this.personalId = dto.getPersonalId();
		this.email = dto.getEmail();
		this.salary = dto.getSalary();
		this.visibleIdentification = dto.getVisibleIdentification();
		this.sumInsuredType = dto.getSumInsuredType();
		this.cdcNo = dto.getCdcNo();
		this.position = dto.getPosition();
		this.office  = dto.getOffice();

		this.labourCardNo = dto.getLabourCardNo();
		this.identificationCardNo = dto.getIdentificationCardNo();
		this.issueOfLabourDate = dto.getIssueOfLabourDate();
		this.qualification = dto.getQualification();
		this.factoryAddress = dto.getFactoryAddress();
		this.issueDateOfIdCard = dto.getIssueDateOfIdCard();
		this.placeOfPassport = dto.getPlaceOfPassport();
		this.country = dto.getCountry();
		this.currency = dto.getCurrency();
		this.passportNo = dto.getPassportNo();
		this.issueDateOfPassport = dto.getIssueDateOfPassport();
		this.generatedPolicyNo = dto.getGeneratedPolicyNo();
		this.oceanlinerName=dto.getOceanlinerName();
		this.vesselName=dto.getVesselName();
		this.seamanNrc=dto.getSeamanNrc();
		this.armyOfficer = dto.isArmyOfficer();
		this.insuredId = dto.getInsuredId();
		if(dto.getAcquaintanceDTO() != null) {
			this.acquaintance = new Acquaintance(dto.getAcquaintanceDTO());
			this.acquaintance.setProposalInsuredPerson(this);			
		}		
		
		for (Attachment attach : dto.getBirthCertificateAttachments()) {
			addBirthCertificateAttachment(attach);
		}
		for (InsuredPersonAttachment attach : dto.getPerAttachmentList()) {
			addAttachment(attach);
		}
		for (InsuredPersonKeyFactorValue kfv : dto.getKeyFactorValueList()) {
			addLifeKeyFactorValue(new InsuredPersonKeyFactorValue(kfv.getValue(), kfv.getKeyFactor()));
		}
		for (BeneficiariesInfoDTO beneficiary : dto.getBeneficiariesInfoDTOList()) {
			addBeneficiaries(new InsuredPersonBeneficiaries(beneficiary));
		}
		if (dto.getInsuredPersonAddOnDTOList() != null) {
			for (InsuredPersonAddOnDTO addOn : dto.getInsuredPersonAddOnDTOList()) {
				addInsuredPersonAddon(new InsuredPersonAddon(addOn));
			}
		}
		if (dto.getInsuredPersonPolicyHistoryRecordList() != null) {
			for (InsuredPersonPolicyHistoryRecord record : dto.getInsuredPersonPolicyHistoryRecordList()) {
				addInsuredPersonPolicyHistoryRecord(record);
			}
		}

		if (dto.isExistsEntity()) {
			this.id = dto.getTempId();
			this.version = dto.getVersion();
		}
	}

	public ProposalInsuredPerson(InsuredPersonInfoDTO dto, LifeProposal lifeProposal) {
		this.lifeProposal = lifeProposal;
		this.approved = dto.isApprove();
		this.needMedicalCheckup = dto.isNeedMedicalCheckup();
		this.age = dto.getAgeForNextYear();
		this.proposedPremium = dto.getPremium();
		this.proposedSumInsured = dto.getSumInsuredInfo();
		this.approvedSumInsured = dto.getApprovedSumInsured();
		this.basicTermPremium = dto.getBasicTermPremium();
		this.addOnTermPremium = dto.getAddOnTermPremium();
		this.endorsementNetAddonPremium = dto.getEndorsementAddonPremium();
		this.endorsementNetBasicPremium = dto.getEndorsementBasicPremium();
		this.interest = dto.getInterest();
		this.weight = dto.getWeight();
		this.height = dto.getHeight();
		this.premiumRate = dto.getPremiumRate();
		this.rejectReason = dto.getRejectReason();
		this.insPersonCodeNo = dto.getInsPersonCodeNo();
		this.inPersonGroupCodeNo = dto.getInPersonGroupCodeNo();
		this.initialId = dto.getInitialId();
		this.idNo = dto.getFullIdNo();
		this.idNoMM = dto.getFullIdNoMM();
		this.seamanNrc = dto.getSeamanFullIdNo() == null ? "" : dto.getSeamanFullIdNo();
		this.fatherName = dto.getFatherName();
		this.dateOfBirth = dto.getDateOfBirth();
		this.endorsementStatus = dto.getEndorsementStatus();
		this.clsOfHealth = dto.getClassificationOfHealth();
		this.gender = dto.getGender();
		this.idType = dto.getIdType();
		this.residentAddress = dto.getResidentAddress();
		this.name = dto.getName();
		this.product = dto.getProduct();
		this.typesOfSport = dto.getTypesOfSport();
		this.occupation = dto.getOccupation();
		this.unit = dto.getUnit();
		this.approvedUnit = dto.getApprovedUnit();
		this.riskyOccupation = dto.getRiskyOccupation();
		this.surveyQuestionAnswerList = dto.getSurveyQuestionAnswerList();
		this.relationship = dto.getRelationship();
		this.parentName = dto.getParentName();
		this.parentDOB = dto.getParentDOB();
		this.parentIdType = dto.getParentIdType();
		this.parentIdNo = dto.getParentFullIdNo();
		this.parentIdNoMM = dto.getParentFullIdNoMM();
		this.school = dto.getSchool();
		this.gradeInfo = dto.getGradeInfo();
		
		this.birthAddress= dto.getBirthAddress();
		this.personalId = dto.getPersonalId();
		this.email = dto.getEmail();
		this.salary = dto.getSalary();
		this.visibleIdentification = dto.getVisibleIdentification();
		this.sumInsuredType = dto.getSumInsuredType();
		// override
		this.customer = dto.getCustomer();
		this.phone = dto.getPhone();

		this.labourCardNo = dto.getLabourCardNo();
		this.identificationCardNo = dto.getIdentificationCardNo();
		this.issueOfLabourDate = dto.getIssueOfLabourDate();
		this.qualification = dto.getQualification();
		this.factoryAddress = dto.getFactoryAddress();
		this.issueDateOfIdCard = dto.getIssueDateOfIdCard();
		this.placeOfPassport = dto.getPlaceOfPassport();
		this.country = dto.getCountry();
		this.currency = dto.getCurrency();
		this.passportNo = dto.getPassportNo();
		this.issueDateOfPassport = dto.getIssueDateOfPassport();
		this.generatedPolicyNo = dto.getGeneratedPolicyNo();
		this.armyOfficer = dto.isArmyOfficer();
		if(dto.getAcquaintanceDTO() != null) {
			this.acquaintance = new Acquaintance(dto.getAcquaintanceDTO());
			this.acquaintance.setProposalInsuredPerson(this);			
		}

		for (Attachment attach : dto.getBirthCertificateAttachments()) {
			addBirthCertificateAttachment(attach);
		}
		for (InsuredPersonAttachment attach : dto.getPerAttachmentList()) {
			addAttachment(attach);
		}
		for (InsuredPersonKeyFactorValue kfv : dto.getKeyFactorValueList()) {
			addLifeKeyFactorValue(new InsuredPersonKeyFactorValue(kfv.getValue(), kfv.getKeyFactor()));
		}
		for (BeneficiariesInfoDTO beneficiary : dto.getBeneficiariesInfoDTOList()) {
			addBeneficiaries(new InsuredPersonBeneficiaries(beneficiary));
		}
		if (dto.getInsuredPersonAddOnDTOList() != null) {
			for (InsuredPersonAddOnDTO addOn : dto.getInsuredPersonAddOnDTOList()) {
				addInsuredPersonAddon(new InsuredPersonAddon(addOn));
			}
		}
		if (dto.getInsuredPersonPolicyHistoryRecordList() != null) {
			for (InsuredPersonPolicyHistoryRecord record : dto.getInsuredPersonPolicyHistoryRecordList()) {
				addInsuredPersonPolicyHistoryRecord(record);
			}
		}

		if (dto.isExistsEntity()) {
			this.id = dto.getTempId();
			this.version = dto.getVersion();
		}
	}

	// public ProposalInsuredPerson(Date dateOfBirth, double proposedSumInsured,
	// Product product, LifeProposal lifeproposal, double proposedPremium,
	// String initialId, String idNo,
	// IdType idType, Name name, Gender gender, ResidentAddress residentAddress,
	// Occupation occupation, String fatherName, double
	// endorsementNetBasicPremium,
	// double endorsementNetAddonPremium, double interest, int weight, int
	// height, double premiumRate, EndorsementStatus status, String
	// insPersonCodeNo,
	// Boolean isPaidPremiumForPaidup, Customer customer, int age, String
	// inPersonGroupCodeNo) {
	// this.dateOfBirth = dateOfBirth;
	// this.proposedSumInsured = proposedSumInsured;
	// this.product = product;
	// this.lifeProposal = lifeproposal;
	// this.customer = customer;
	// this.proposedPremium = proposedPremium;
	// this.initialId = initialId;
	// this.idNo = idNo;
	// this.idType = idType;
	// this.name = name;
	// this.residentAddress = residentAddress;
	// this.gender = gender;
	// this.occupation = occupation;
	// this.fatherName = fatherName;
	// this.endorsementNetBasicPremium = endorsementNetBasicPremium;
	// this.endorsementNetAddonPremium = endorsementNetAddonPremium;
	// this.interest = interest;
	// this.weight = weight;
	// this.height = height;
	// this.premiumRate = premiumRate;
	// this.endorsementStatus = status;
	// this.insPersonCodeNo = insPersonCodeNo;
	// this.isPaidPremiumForPaidup = isPaidPremiumForPaidup;
	// this.age = age;
	// this.inPersonGroupCodeNo = inPersonGroupCodeNo;
	// }

	// public ProposalInsuredPerson(Date dateOfBirth, double proposedSumInsured,
	// Product product, LifeProposal lifeproposal, double proposedPremium,
	// double endorsementNetBasicPremium,
	// double endorsementNetAddonPremium, double interest, int weight, int
	// height, double premiumRate, EndorsementStatus status, String
	// insPersonCodeNo,
	// String inPersonGroupCodeNo) {
	// this.dateOfBirth = dateOfBirth;
	// this.proposedSumInsured = proposedSumInsured;
	// this.product = product;
	// this.lifeProposal = lifeproposal;
	// this.proposedPremium = proposedPremium;
	// this.endorsementStatus = status;
	// this.endorsementNetBasicPremium = endorsementNetBasicPremium;
	// this.endorsementNetAddonPremium = endorsementNetAddonPremium;
	// this.insPersonCodeNo = insPersonCodeNo;
	// this.interest = interest;
	// this.weight = weight;
	// this.height = height;
	// this.premiumRate = premiumRate;
	// this.inPersonGroupCodeNo = inPersonGroupCodeNo;
	//
	// }

	public ProposalInsuredPerson(PolicyInsuredPerson policyInsuredPerson) {
		this.clsOfHealth = policyInsuredPerson.getClsOfHealth();
		this.dateOfBirth = policyInsuredPerson.getDateOfBirth();
		this.product = policyInsuredPerson.getProduct();
		this.proposedSumInsured = policyInsuredPerson.getSumInsured();
		// this.proposedPremium = policyInsuredPerson.getPremium();
		// this.approvedSumInsured = policyInsuredPerson.getSumInsured();
		this.insPersonCodeNo = policyInsuredPerson.getInsPersonCodeNo();
		this.initialId = policyInsuredPerson.getInitialId();
		this.idNo = policyInsuredPerson.getIdNo();
		this.idNoMM = policyInsuredPerson.getIdNoMM();
		this.idType = policyInsuredPerson.getIdType();
		this.seamanNrc = policyInsuredPerson.getSeamanNrc();
		this.name = policyInsuredPerson.getName();
		this.residentAddress = policyInsuredPerson.getResidentAddress();
		this.gender = policyInsuredPerson.getGender();
		this.occupation = policyInsuredPerson.getOccupation();
		this.fatherName = policyInsuredPerson.getFatherName();
		this.customer = policyInsuredPerson.getCustomer();
		this.age = policyInsuredPerson.getAge();
		this.inPersonGroupCodeNo = policyInsuredPerson.getInPersonGroupCodeNo();
		this.typesOfSport = policyInsuredPerson.getTypesOfSport();
		this.endorsementStatus = policyInsuredPerson.getEndorsementStatus();
		// this.basicTermPremium = policyInsuredPerson.getBasicTermPremium();
		// this.addOnTermPremium = policyInsuredPerson.getAddOnTermPremium();
		// this.endorsementNetBasicPremium =
		// policyInsuredPerson.getEndorsementNetBasicPremium();
		// this.endorsementNetAddonPremium =
		// policyInsuredPerson.getEndorsementNetAddonPremium();
		this.interest = policyInsuredPerson.getInterest();
		this.weight = policyInsuredPerson.getWeight();
		this.height = policyInsuredPerson.getHeight();
		this.premiumRate = policyInsuredPerson.getPremiumRate();
		this.unit = policyInsuredPerson.getUnit();
		this.approvedUnit = policyInsuredPerson.getUnit();
		this.riskyOccupation = policyInsuredPerson.getRiskyOccupation();
		this.phone = policyInsuredPerson.getPhone();
		this.relationship = policyInsuredPerson.getRelationship();
		this.parentName = policyInsuredPerson.getParentName();
		this.parentDOB = policyInsuredPerson.getParentDOB();
		this.parentIdType = policyInsuredPerson.getParentIdType();
		this.parentIdNo = policyInsuredPerson.getParentIdNo();
		this.school = policyInsuredPerson.getSchool();
		this.gradeInfo = policyInsuredPerson.getGradeInfo();
		
		this.birthAddress= policyInsuredPerson.getBirthAddress();
		this.personalId = policyInsuredPerson.getPersonalId();
		this.email = policyInsuredPerson.getEmail();
		this.salary = policyInsuredPerson.getSalary();
		this.visibleIdentification = policyInsuredPerson.getVisibleIdentification();
		this.sumInsuredType = policyInsuredPerson.getSumInsuredType();

		this.labourCardNo = policyInsuredPerson.getLabourCardNo();
		this.identificationCardNo = policyInsuredPerson.getIdentificationCardNo();
		this.issueOfLabourDate = policyInsuredPerson.getIssueOfLabourDate();
		this.qualification = policyInsuredPerson.getQualification();
		this.factoryAddress = policyInsuredPerson.getFactoryAddress();
		this.issueDateOfIdCard = policyInsuredPerson.getIssueDateOfIdCard();
		this.placeOfPassport = policyInsuredPerson.getPlaceOfPassport();
		this.country = policyInsuredPerson.getCountry();
		this.currency = policyInsuredPerson.getCurrency();
		this.passportNo = policyInsuredPerson.getPassportNo();
		this.issueDateOfPassport = policyInsuredPerson.getIssueDateOfPassport();
		this.oceanlinerName=policyInsuredPerson.getOceanlinerName();
		this.vesselName=policyInsuredPerson.getVesselName();
		this.armyOfficer=policyInsuredPerson.isArmyOfficer();

		for (Attachment attach : policyInsuredPerson.getBirthCertificateAttachment()) {
			addBirthCertificateAttachment(attach);
		}
		for (PolicyInsuredPersonKeyFactorValue lifeKFValue : policyInsuredPerson.getPolicyInsuredPersonkeyFactorValueList()) {
			addLifeKeyFactorValue(new InsuredPersonKeyFactorValue(lifeKFValue));
		}
		for (PolicyInsuredPersonBeneficiaries insuredPersonBeneficiaries : policyInsuredPerson.getPolicyInsuredPersonBeneficiariesList()) {
			addBeneficiaries(new InsuredPersonBeneficiaries(insuredPersonBeneficiaries));
		}
		for (PolicyInsuredPersonAddon addon : policyInsuredPerson.getPolicyInsuredPersonAddOnList()) {
			addInsuredPersonAddon(new InsuredPersonAddon(addon));
		}
		for (PolicyInsuredPersonAttachment attachment : policyInsuredPerson.getAttachmentList()) {
			addAttachment(new InsuredPersonAttachment(attachment));
		}
	}

	public ProposalInsuredPerson(Customer customer) {
		this.name = customer.getName();
		this.initialId = customer.getInitialId();
		this.fatherName = customer.getFatherName();
		this.idNo = customer.getFullIdNo();
		this.idType = customer.getIdType();
		this.dateOfBirth = customer.getDateOfBirth();
		this.residentAddress = customer.getResidentAddress();
		this.gender = customer.getGender();
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

	public double getProposedSumInsured() {
		return proposedSumInsured;
	}

	public void setProposedSumInsured(double proposedSumInsured) {
		this.proposedSumInsured = proposedSumInsured;
	}

	public double getProposedPremium() {
		return proposedPremium;
	}

	public void setProposedPremium(double proposedPremium) {
		this.proposedPremium = proposedPremium;
	}

	public double getApprovedSumInsured() {
		return approvedSumInsured;
	}

	public void setApprovedSumInsured(double approvedSumInsured) {
		this.approvedSumInsured = approvedSumInsured;
	}

	public int getApprovedUnit() {
		return approvedUnit;
	}

	public void setApprovedUnit(int approvedUnit) {
		this.approvedUnit = approvedUnit;
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		if (approved) {
			this.needMedicalCheckup = false;
			this.rejectReason = null;
			this.approvedUnit = unit;
			this.approvedSumInsured = proposedSumInsured;
		} else {
			this.approvedUnit = 0;
			this.approvedSumInsured = 0.0;
		}
		this.approved = approved;
	}

	public boolean isNeedMedicalCheckup() {
		return needMedicalCheckup;
	}

	public void setNeedMedicalCheckup(boolean needMedicalCheckup) {
		this.needMedicalCheckup = needMedicalCheckup;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public List<InsuredPersonAttachment> getAttachmentList() {
		if (this.attachmentList == null) {
			this.attachmentList = new ArrayList<InsuredPersonAttachment>();
		}
		return this.attachmentList;
	}

	public void setAttachmentList(List<InsuredPersonAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public List<InsuredPersonPolicyHistoryRecord> getInsuredPersonPolicyHistoryRecordList() {
		if (this.insuredPersonPolicyHistoryRecordList == null) {
			this.insuredPersonPolicyHistoryRecordList = new ArrayList<InsuredPersonPolicyHistoryRecord>();
		}
		return this.insuredPersonPolicyHistoryRecordList;
	}

	public void setInsuredPersonPolicyHistoryRecordList(List<InsuredPersonPolicyHistoryRecord> insuredPersonPolicyHistoryRecordList) {
		this.insuredPersonPolicyHistoryRecordList = insuredPersonPolicyHistoryRecordList;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LifeProposal getLifeProposal() {
		return lifeProposal;
	}

	public void setLifeProposal(LifeProposal lifeProposal) {
		this.lifeProposal = lifeProposal;
	}

	public List<InsuredPersonAddon> getInsuredPersonAddOnList() {
		if (insuredPersonAddOnList == null) {
			insuredPersonAddOnList = new ArrayList<InsuredPersonAddon>();
		}
		return insuredPersonAddOnList;
	}

	public void setInsuredPersonAddOnList(List<InsuredPersonAddon> insuredPersonAddOnList) {
		this.insuredPersonAddOnList = insuredPersonAddOnList;
	}

	public List<InsuredPersonKeyFactorValue> getKeyFactorValueList() {
		if (keyFactorValueList == null) {
			keyFactorValueList = new ArrayList<InsuredPersonKeyFactorValue>();
		}
		return keyFactorValueList;
	}

	public void setKeyFactorValueList(List<InsuredPersonKeyFactorValue> keyFactorValueList) {
		this.keyFactorValueList = keyFactorValueList;
	}

	public String getKeyFactorValueListForDetails() {
		StringBuffer buffer = new StringBuffer();
		if (getKeyFactorValueList().size() > 0) {
			String id = getKeyFactorValueList().get(getKeyFactorValueList().size() - 1).getKeyFactor().getId();
			for (InsuredPersonKeyFactorValue keyfactorValue : getKeyFactorValueList()) {
				if (lifeProposal.getPaymentType().getId().equals(keyfactorValue.getValue())) {
					buffer.append(keyfactorValue.getKeyFactor().getValue()).append(" - ").append(lifeProposal.getPaymentType().getName())
							.append(id == keyfactorValue.getKeyFactor().getId() ? "" : " , ");
				} else {
					buffer.append(keyfactorValue.getKeyFactor().getValue()).append(" - ").append(keyfactorValue.getValue())
							.append(id == keyfactorValue.getKeyFactor().getId() ? "" : " , ");
				}
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
		if (insuredPersonAddOnList != null) {
			for (InsuredPersonAddon iao : insuredPersonAddOnList) {
				premium = Utils.getTwoDecimalPoint(premium + iao.getProposedPremium());
			}
		}
		return premium;
	}

	public double getAddOnSumInsured() {
		double sumInsured = 0.0;
		if (insuredPersonAddOnList != null) {
			for (InsuredPersonAddon iao : insuredPersonAddOnList) {
				sumInsured = sumInsured + iao.getProposedSumInsured();
			}
		}
		return sumInsured;
	}

	public double getTotalPermium() {
		return Utils.getTwoDecimalPoint(proposedPremium + getAddOnPremium());
	}

	public double getCalculateSumInsured() {
		if (approved) {
			return approvedSumInsured;
		} else
			return proposedSumInsured;
	}

	public double getSuminsuredPerUnit() {
		double suminsuredPerUnit = 0.0;
		suminsuredPerUnit = unit * product.getSumInsuredPerUnit();
		return suminsuredPerUnit;
	}

	public double getTermPremium() {
		double termPremium = 0.0;
		termPremium = getBasicTermPremium() + getAddOnTermPremium();
		return termPremium;
	}

	public String getPaymentType() {
		return lifeProposal.getPaymentType().getName();
	}

	public List<InsuredPersonBeneficiaries> getInsuredPersonBeneficiariesList() {
		if (this.insuredPersonBeneficiariesList == null) {
			this.insuredPersonBeneficiariesList = new ArrayList<InsuredPersonBeneficiaries>();
		}
		return this.insuredPersonBeneficiariesList;
	}

	public void setInsuredPersonBeneficiariesList(List<InsuredPersonBeneficiaries> insuredPersonBeneficiariesList) {
		this.insuredPersonBeneficiariesList = insuredPersonBeneficiariesList;
	}

	public void setProposalVehicleList(List<InsuredPersonBeneficiaries> insuredPersonBeneficiariesList) {
		this.insuredPersonBeneficiariesList = insuredPersonBeneficiariesList;
	}

	public List<SurveyQuestionAnswer> getSurveyQuestionAnswerList() {
		if (surveyQuestionAnswerList == null) {
			surveyQuestionAnswerList = new ArrayList<SurveyQuestionAnswer>();
		}
		return surveyQuestionAnswerList;
	}

	public void setSurveyQuestionAnswerList(List<SurveyQuestionAnswer> surveyQuestionAnswerList) {
		this.surveyQuestionAnswerList = surveyQuestionAnswerList;
	}

	public void addLifeKeyFactorValue(InsuredPersonKeyFactorValue insuredPersonKeyFactorValue) {
		insuredPersonKeyFactorValue.setProposalInsuredPerson(this);
		getKeyFactorValueList().add(insuredPersonKeyFactorValue);
	}

	public void addInsuredPersonAddon(InsuredPersonAddon insuredPersonAddon) {
		insuredPersonAddon.setProposalInsuredPerson(this);
		getInsuredPersonAddOnList().add(insuredPersonAddon);
	}

	public void addSurveyQuestionAnswer(SurveyQuestionAnswer surveyQuestion) {
		getSurveyQuestionAnswerList().add(surveyQuestion);
	}

	public void addAttachment(InsuredPersonAttachment attachment) {
		attachment.setProposalInsuredPerson(this);
		getAttachmentList().add(attachment);
	}

	public void addBirthCertificateAttachment(Attachment attachment) {
		getBirthCertificateAttachment().add(attachment);
	}

	public void addInsuranceHistoryRecord(InsuredPersonPolicyHistoryRecord record) {
		getInsuredPersonPolicyHistoryRecordList().add(record);
	}

	public void addBeneficiaries(InsuredPersonBeneficiaries insuredPersonBeneficiaries) {
		insuredPersonBeneficiaries.setProposalInsuredPerson(this);
		getInsuredPersonBeneficiariesList().add(insuredPersonBeneficiaries);
	}


	public void addInsuredPersonPolicyHistoryRecord(InsuredPersonPolicyHistoryRecord record) {
		getInsuredPersonPolicyHistoryRecordList().add(record);
	}

	public ClassificationOfHealth getClsOfHealth() {
		return clsOfHealth;
	}

	public void setClsOfHealth(ClassificationOfHealth clsOfHealth) {
		this.clsOfHealth = clsOfHealth;
	}

	public int getAgeForNextYear() {
		Calendar cal_1 = Calendar.getInstance();
		cal_1.setTime(lifeProposal.getStartDate());
		int currentYear = cal_1.get(Calendar.YEAR);
		Calendar cal_2 = Calendar.getInstance();
		cal_2.setTime(dateOfBirth);
		cal_2.set(Calendar.YEAR, currentYear);

		if (lifeProposal.getStartDate().after(cal_2.getTime())) {
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

	public boolean isValidAttachment() {
		if (attachmentList == null || attachmentList.isEmpty()) {
			return false;
		}
		return true;
	}

	public String getInitialId() {
		return initialId;
	}

	public void setInitialId(String initialId) {
		this.initialId = initialId;
	}

	public String getIdNo() {
		if (idNo == null) {
			return "";
		}
		return idNo;
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

	public String getFullName() {
		String result = "";
		if (name != null) {
			if (initialId != null && !initialId.isEmpty()) {
				result = initialId;
			}
			if (name.getFirstName() != null && !name.getFirstName().isEmpty()) {
				result = result + " " + name.getFirstName();
			}
			if (name.getMiddleName() != null && !name.getMiddleName().isEmpty()) {
				result = result + " " + name.getMiddleName();
			}
			if (name.getLastName() != null && !name.getLastName().isEmpty()) {
				result = result + " " + name.getLastName();
			}
		}
		return result.replaceAll("\\s+", " ");
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

	public Occupation getOccupation() {
		return occupation;
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public String getHeightStr() {
		return height + "\"";
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

	public String getInsPersonCodeNo() {
		return insPersonCodeNo;
	}

	public void setInsPersonCodeNo(String insPersonCodeNo) {
		this.insPersonCodeNo = insPersonCodeNo;
	}

	public Boolean getIsPaidPremiumForPaidup() {
		return isPaidPremiumForPaidup;
	}

	public void setIsPaidPremiumForPaidup(Boolean isPaidPremiumForPaidup) {
		this.isPaidPremiumForPaidup = isPaidPremiumForPaidup;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getInPersonGroupCodeNo() {
		return inPersonGroupCodeNo;
	}

	public void setInPersonGroupCodeNo(String inPersonGroupCodeNo) {
		this.inPersonGroupCodeNo = inPersonGroupCodeNo;
	}

	public double getPremiumForOneThousandKyat() {
		return ((proposedPremium * product.getBasedAmount()) / proposedSumInsured);
	}

	public TypesOfSport getTypesOfSport() {
		return typesOfSport;
	}

	public void setTypesOfSport(TypesOfSport typesOfSport) {
		this.typesOfSport = typesOfSport;
	}

	public RelationShip getRelationship() {
		return relationship;
	}

	public void setRelationship(RelationShip relationship) {
		this.relationship = relationship;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
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

	public List<Attachment> getBirthCertificateAttachment() {
		if (this.birthCertificateAttachment == null) {
			this.birthCertificateAttachment = new ArrayList<Attachment>();
		}
		return birthCertificateAttachment;
	}

	public void setBirthCertificateAttachment(List<Attachment> birthCertificateAttachment) {
		this.birthCertificateAttachment = birthCertificateAttachment;
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

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	

	public String getGeneratedPolicyNo() {
		return generatedPolicyNo;
	}

	public void setGeneratedPolicyNo(String generatedPolicyNo) {
		this.generatedPolicyNo = generatedPolicyNo;
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
		result = prime * result + (approved ? 1231 : 1237);
		temp = Double.doubleToLongBits(approvedSumInsured);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + approvedUnit;
		temp = Double.doubleToLongBits(basicTermPremium);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		result = prime * result + ((isPaidPremiumForPaidup == null) ? 0 : isPaidPremiumForPaidup.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (needMedicalCheckup ? 1231 : 1237);
		result = prime * result + ((occupation == null) ? 0 : occupation.hashCode());
		temp = Double.doubleToLongBits(premiumRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		temp = Double.doubleToLongBits(proposedPremium);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(proposedSumInsured);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
		result = prime * result + ((rejectReason == null) ? 0 : rejectReason.hashCode());
		result = prime * result + ((relationship == null) ? 0 : relationship.hashCode());
		result = prime * result + ((residentAddress == null) ? 0 : residentAddress.hashCode());
		result = prime * result + ((riskyOccupation == null) ? 0 : riskyOccupation.hashCode());
		result = prime * result + ((typesOfSport == null) ? 0 : typesOfSport.hashCode());
		result = prime * result + ((sumInsuredType == null) ? 0 : sumInsuredType.hashCode());
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
		ProposalInsuredPerson other = (ProposalInsuredPerson) obj;
		if (Double.doubleToLongBits(addOnTermPremium) != Double.doubleToLongBits(other.addOnTermPremium))
			return false;
		if (age != other.age)
			return false;
		if (approved != other.approved)
			return false;
		if (Double.doubleToLongBits(approvedSumInsured) != Double.doubleToLongBits(other.approvedSumInsured))
			return false;
		if (approvedUnit != other.approvedUnit)
			return false;
		if (Double.doubleToLongBits(basicTermPremium) != Double.doubleToLongBits(other.basicTermPremium))
			return false;
		if (clsOfHealth != other.clsOfHealth)
			return false;
		if (sumInsuredType != other.sumInsuredType)
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
		if (isPaidPremiumForPaidup == null) {
			if (other.isPaidPremiumForPaidup != null)
				return false;
		} else if (!isPaidPremiumForPaidup.equals(other.isPaidPremiumForPaidup))
			return false;
		if (lifeProposal == null) {
			if (other.lifeProposal != null)
				return false;
		} else if (!lifeProposal.equals(other.lifeProposal))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (needMedicalCheckup != other.needMedicalCheckup)
			return false;
		if (occupation == null) {
			if (other.occupation != null)
				return false;
		} else if (!occupation.equals(other.occupation))
			return false;
		if (Double.doubleToLongBits(premiumRate) != Double.doubleToLongBits(other.premiumRate))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (Double.doubleToLongBits(proposedPremium) != Double.doubleToLongBits(other.proposedPremium))
			return false;
		if (Double.doubleToLongBits(proposedSumInsured) != Double.doubleToLongBits(other.proposedSumInsured))
			return false;
		if (recorder == null) {
			if (other.recorder != null)
				return false;
		} else if (!recorder.equals(other.recorder))
			return false;
		if (rejectReason == null) {
			if (other.rejectReason != null)
				return false;
		} else if (!rejectReason.equals(other.rejectReason))
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

	public void setArmyOfficer(boolean armyOfficer) {
		this.armyOfficer = armyOfficer;
	}

	public String getIdNoMM() {
		return idNoMM;
		
	}

	public void setIdNoMM(String idNoMM) {
		this.idNoMM = idNoMM;
		
	}

	public String getParentIdNoMM() {
		return parentIdNoMM;
		
	}

	public void setParentIdNoMM(String parentIdNoMM) {
		this.parentIdNoMM = parentIdNoMM;
		
	}
	

	public int getInsuredId() {
		return insuredId;
	}

	public void setInsuredId(int insuredId) {
		this.insuredId = insuredId;
	}
	
	
	public Acquaintance getAcquaintance() {
		return acquaintance;
	}

	public void setAcquaintance(Acquaintance acquaintance) {
		this.acquaintance = acquaintance;
	}

	
	

}
