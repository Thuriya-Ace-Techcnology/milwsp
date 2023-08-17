package org.ace.ws.model.TwoCTwoPDTO;

import java.util.Date;

public class OnlineProductRecordListDTO {
	private String vehicle_no;
	private String period_from;
	private String period_to;
	private String premium_amount;
	private String receipt_no;
	private String receipt_date;
	private String rta_branch;
	private String buy_date;
	private String orderId;
	private String paymentStatus;
	private String productType;
	private String twoCtwoPCharges;
	private String diffAmount;
	private double transactionFees;
	private String fullName;
	private String journeyFrom;
	private String passportNo;
	private Date   paymentDate;
	private String paymentGateWay;
	private int    periodMonth;
	private String poloicyNo;
	private int age;
	private String dateOfBirth;
	
	public OnlineProductRecordListDTO() {
		super();
	}
	
	public OnlineProductRecordListDTO(String vehicle_no, String period_from, String period_to, String premium_amount,
			String receipt_no, String receipt_date, String rta_branch, String buy_date, String orderId,String paymentStatus,String productType) {
		super();
		this.vehicle_no = vehicle_no;
		this.period_from = period_from;
		this.period_to = period_to;
		this.premium_amount = premium_amount;
		this.receipt_no = receipt_no;
		this.receipt_date = receipt_date;
		this.rta_branch = rta_branch;
		this.buy_date = buy_date;
		this.orderId = orderId;
		this.paymentStatus = paymentStatus;
	}

	public String getVehicle_no() {
		return vehicle_no;
	}
	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	public String getPeriod_from() {
		return period_from;
	}
	public void setPeriod_from(String period_from) {
		this.period_from = period_from;
	}
	public String getPeriod_to() {
		return period_to;
	}
	public void setPeriod_to(String period_to) {
		this.period_to = period_to;
	}
	public String getPremium_amount() {
		return premium_amount;
	}
	public void setPremium_amount(String premium_amount) {
		this.premium_amount = premium_amount;
	}
	public String getReceipt_no() {
		return receipt_no;
	}
	public void setReceipt_no(String receipt_no) {
		this.receipt_no = receipt_no;
	}
	public String getReceipt_date() {
		return receipt_date;
	}
	public void setReceipt_date(String receipt_date) {
		this.receipt_date = receipt_date;
	}
	public String getRta_branch() {
		return rta_branch;
	}
	public void setRta_branch(String rta_branch) {
		this.rta_branch = rta_branch;
	}
	public String getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getTwoCtwoPCharges() {
		return twoCtwoPCharges;
	}

	public void setTwoCtwoPCharges(String twoCtwoPCharges) {
		this.twoCtwoPCharges = twoCtwoPCharges;
	}

	public String getDiffAmount() {
		return diffAmount;
	}

	public void setDiffAmount(String diffAmount) {
		this.diffAmount = diffAmount;
	}

	public double getTransactionFees() {
		return transactionFees;
	}

	public void setTransactionFees(double transactionFees) {
		this.transactionFees = transactionFees;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getJourneyFrom() {
		return journeyFrom;
	}

	public void setJourneyFrom(String journeyFrom) {
		this.journeyFrom = journeyFrom;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentGateWay() {
		return paymentGateWay;
	}

	public void setPaymentGateWay(String paymentGateWay) {
		this.paymentGateWay = paymentGateWay;
	}

	public int getPeriodMonth() {
		return periodMonth;
	}

	public void setPeriodMonth(int periodMonth) {
		this.periodMonth = periodMonth;
	}

	public String getPoloicyNo() {
		return poloicyNo;
	}

	public void setPoloicyNo(String poloicyNo) {
		this.poloicyNo = poloicyNo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
}
