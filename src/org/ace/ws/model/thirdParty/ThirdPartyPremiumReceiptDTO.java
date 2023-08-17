package org.ace.ws.model.thirdParty;

import java.util.List;

import org.ace.insurance.system.productTypeRecords.dto.ProductTypeRecordsDTO;
import org.ace.insurance.system.thirdparty.ThirdPartyPremiumReceipt;
import org.ace.ws.model.premiumCal.ResultPremium;

/*
 * @author Kyaw Yea Lwin
 * @date 29/06/2020
 */
public class ThirdPartyPremiumReceiptDTO {
	
	private String id;
	private String prefix;
	private String owner_name;
	private String nrc_no;
	private String address;
	private String type;
	private String vehicle_no;
	private String book_no;
	private String vehicle_name;
	private String vehicle_type;
	private String capacity;
	private String period_from;
	private String period_to;
	private String premium_amount;
	private String receipt_no;
	private String receipt_date;
	private int version;
	private List<ResultPremium> resultPremium;
	private double premiumTotalAmount;
	private int total_month;
	private String rta_branch;
	private String buy_date;
	private String nextPremiumBuyDate="";
	private String orderId;
	private ProductTypeRecordsDTO recordsDTO;
	private boolean isConvert;
	
	
	public ThirdPartyPremiumReceiptDTO() {
		
	}
	public ThirdPartyPremiumReceiptDTO(ThirdPartyPremiumReceipt receipt) {
		this.id = receipt.getId();
		this.prefix = receipt.getPrefix();
		this.owner_name=receipt.getOwner_name();
		this.nrc_no = receipt.getNrc_no();
		this.address = receipt.getAddress();
		this.type = receipt.getType();
		this.vehicle_no = receipt.getVehicle_no();
		this.book_no = receipt.getBook_no();
		this.vehicle_name = receipt.getVehicle_name();
		this.vehicle_type = receipt.getVehicle_type();
		this.capacity = receipt.getCapacity();
		this.period_from = receipt.getPeriod_from();
		this.period_to = receipt.getPeriod_to();
		this.premium_amount =receipt.getPremium_amount();
		this.receipt_no = receipt.getReceipt_no();
		this.receipt_date = receipt.getReceipt_date();
		this.version = receipt.getVersion();
		this.rta_branch = receipt.getRta_branch();
		this.buy_date = receipt.getBuyDate();
		this.orderId = receipt.getOrder_id();
		this.isConvert=receipt.isConvert();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	
	public boolean isConvert() {
		return isConvert;
	}
	public void setConvert(boolean isConvert) {
		this.isConvert = isConvert;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getNrc_no() {
		return nrc_no;
	}
	public void setNrc_no(String nrc_no) {
		this.nrc_no = nrc_no;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getVehicle_no() {
		return vehicle_no;
	}
	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	public String getBook_no() {
		return book_no;
	}
	public void setBook_no(String book_no) {
		this.book_no = book_no;
	}
	public String getVehicle_name() {
		return vehicle_name;
	}
	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
	}
	public String getVehicle_type() {
		return vehicle_type;
	}
	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
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
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public List<ResultPremium> getResultPremium() {
		return resultPremium;
	}
	public void setResultPremium(List<ResultPremium> resultPremium) {
		this.resultPremium = resultPremium;
	}
	public double getPremiumTotalAmount() {
		return premiumTotalAmount;
	}
	public void setPremiumTotalAmount(double premiumTotalAmount) {
		this.premiumTotalAmount = premiumTotalAmount;
	}
	public int getTotal_month() {
		return total_month;
	}
	public void setTotal_month(int total_month) {
		this.total_month = total_month;
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
	public String getNextPremiumBuyDate() {
		return nextPremiumBuyDate;
	}
	public void setNextPremiumBuyDate(String nextPremiumBuyDate) {
		this.nextPremiumBuyDate = nextPremiumBuyDate;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public ProductTypeRecordsDTO getRecordsDTO() {
		return recordsDTO;
	}
	public void setRecordsDTO(ProductTypeRecordsDTO recordsDTO) {
		this.recordsDTO = recordsDTO;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
