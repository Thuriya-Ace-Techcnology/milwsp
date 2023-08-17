package org.ace.ws.model.mobilePersonalAccidentproposal;

import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.common.Gender;
import org.ace.insurance.common.IdType;
import org.springframework.web.multipart.MultipartFile;

public class PAIP001 {

	private String id;
	private String initialId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String fatherName;
	private String idNo;
	private long dateOfBirth;
	private int age;
	private double sumInsured;
	private double premium;
	private Gender gender;
	private IdType idType;
	private String address;
	private String townshipId;
	private String productId;
	private String occupationId;
	private List<MBP001> insuredPersonBeneficiariesList;
	private List<MIPA001> insuredPersonAddOnList;
	private MultipartFile nrcFront;
	private MultipartFile nrcBack;
	private String nrcFrontName;
	private String nrcBackName;
	private String nrcFrontfilePath;
	private String nrcBackfilePath;
	private String phoneNumber;
	private byte[] nrcFrontImage;
	private byte[] nrcBackImage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTownshipId() {
		return townshipId;
	}

	public void setTownshipId(String townshipId) {
		this.townshipId = townshipId;
	}

	public byte[] getNrcFrontImage() {
		return nrcFrontImage;
	}

	public void setNrcFrontImage(byte[] nrcFrontImage) {
		this.nrcFrontImage = nrcFrontImage;
	}

	public byte[] getNrcBackImage() {
		return nrcBackImage;
	}

	public void setNrcBackImage(byte[] nrcBackImage) {
		this.nrcBackImage = nrcBackImage;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<MBP001> getInsuredBeneficiaryList() {
		return insuredPersonBeneficiariesList;
	}

	public void setInsuredBeneficiaryList(List<MBP001> insuredBeneficiaryList) {
		this.insuredPersonBeneficiariesList = insuredBeneficiaryList;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public String getAddress() {
		return address;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(String occupationId) {
		this.occupationId = occupationId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<MIPA001> getInsuredPersonAddOnList() {
		if (insuredPersonAddOnList == null) {
			insuredPersonAddOnList = new ArrayList<MIPA001>();
		}
		return insuredPersonAddOnList;
	}

	public List<MBP001> getInsuredPersonBeneficiariesList() {
		if (insuredPersonBeneficiariesList == null) {
			insuredPersonBeneficiariesList = new ArrayList<MBP001>();
		}
		return insuredPersonBeneficiariesList;
	}

	public void setInsuredPersonBeneficiariesList(List<MBP001> insuredPersonBeneficiariesList) {
		this.insuredPersonBeneficiariesList = insuredPersonBeneficiariesList;
	}

	public void setInsuredPersonAddOnList(List<MIPA001> insuredPersonAddOnList) {
		this.insuredPersonAddOnList = insuredPersonAddOnList;
	}

	public String getInitialId() {
		return initialId;
	}

	public void setInitialId(String initialId) {
		this.initialId = initialId;
	}

	public MultipartFile getNrcFront() {
		return nrcFront;
	}

	public void setNrcFrant(MultipartFile nrcFrant) {
		this.nrcFront = nrcFrant;
	}

	public MultipartFile getNrcBack() {
		return nrcBack;
	}

	public void setNrcBack(MultipartFile nrcBack) {
		this.nrcBack = nrcBack;
	}

	public String getNrcFrontName() {
		return nrcFrontName;
	}

	public void setNrcFrontName(String nrcFrontName) {
		this.nrcFrontName = nrcFrontName;
	}

	public String getNrcBackName() {
		return nrcBackName;
	}

	public void setNrcBackName(String nrcBackName) {
		this.nrcBackName = nrcBackName;
	}

	public String getNrcFrontfilePath() {
		return nrcFrontfilePath;
	}

	public void setNrcFrontfilePath(String nrcFrontfilePath) {
		this.nrcFrontfilePath = nrcFrontfilePath;
	}

	public String getNrcBackfilePath() {
		return nrcBackfilePath;
	}

	public void setNrcBackfilePath(String nrcBackfilePath) {
		this.nrcBackfilePath = nrcBackfilePath;
	}

	public void setNrcFront(MultipartFile nrcFront) {
		this.nrcFront = nrcFront;
	}

}
