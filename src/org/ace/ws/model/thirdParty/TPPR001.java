package org.ace.ws.model.thirdParty;
import org.ace.insurance.product.PaymentStatus;
import org.ace.insurance.system.productTypeRecords.dto.ProductTypeRecordsDTO;

public class TPPR001 {

	private String id;	
	private String owner_name;	
	private String nrc_no;	
	private String address;	
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
	private String type;	
	private String rta_branch;	
	private String buyDate;	
	private String order_id;	
	private PaymentStatus paymentStatus;
	private ProductTypeRecordsDTO recordsDTO;
	public TPPR001() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRta_branch() {
		return rta_branch;
	}
	public void setRta_branch(String rta_branch) {
		this.rta_branch = rta_branch;
	}
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public ProductTypeRecordsDTO getRecordsDTO() {
		return recordsDTO;
	}
	public void setRecordsDTO(ProductTypeRecordsDTO recordsDTO) {
		this.recordsDTO = recordsDTO;
	}
	
	
}
