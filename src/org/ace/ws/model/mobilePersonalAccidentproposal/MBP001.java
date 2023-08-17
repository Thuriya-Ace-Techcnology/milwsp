package org.ace.ws.model.mobilePersonalAccidentproposal;

import java.util.Date;

import org.ace.insurance.common.Gender;
import org.ace.insurance.common.IdType;
import org.springframework.web.multipart.MultipartFile;

public class MBP001 {
	private String id;
	private String initialId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String idNo;
	private String address;
	private Gender gender;
	private IdType idType;
	private long dateOfBirth;
	// for DOB Report
	private Date dateOFBirth;
	private String ageAndDateOfBirth;
	private String townshipId;
	private String relationshipId;
	private float percentage;
	private MultipartFile nrcFront;
	private MultipartFile nrcBack;
	private String nrcFrontName;
	private String nrcBackName;
	private String nrcFrontfilePath;
	private String nrcBackfilePath;
	private byte[] nrcFrontImage;
	private byte[] nrcBackImage;

	private String phoneNumber;

	// for Report Pdf
	private Integer age;
	private String fullName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInitialId() {
		return initialId;
	}

	public void setInitialId(String initialId) {
		this.initialId = initialId;
	}

	public Date getDateOFBirth() {
		return dateOFBirth;
	}

	public void setDateOFBirth(Date dateOFBirth) {
		this.dateOFBirth = dateOFBirth;
	}

	public String getAgeAndDateOfBirth() {
		return ageAndDateOfBirth;
	}

	public void setAgeAndDateOfBirth(String ageAndDateOfBirth) {
		this.ageAndDateOfBirth = ageAndDateOfBirth;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public MultipartFile getNrcFront() {
		return nrcFront;
	}

	public void setNrcFront(MultipartFile nrcFront) {
		this.nrcFront = nrcFront;
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

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getTownshipId() {
		return townshipId;
	}

	public void setTownshipId(String townshipId) {
		this.townshipId = townshipId;
	}

	public String getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(String relationshipId) {
		this.relationshipId = relationshipId;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
