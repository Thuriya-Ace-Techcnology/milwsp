package org.ace.ws.model.TwoCTwoPDTO;

import org.ace.insurance.common.UserRecorder;

public class TwoCTwoPResponseDTO {
	private String id;
	private String twoCtwoPVersion;
	private String request_timestamp;
	private String merchant_id;
	private String currency;
	private String invoice_no;
	private String transaction_ref;
	private String approval_code;
	private String eci;
	private String order_id;
	private String amount;
	private String transaction_datetime;
	private String payment_channel;
	private String payment_status;
	private String channel_response_code;
	private String channel_response_desc;
	private String stored_card_unique_id;
	private String backend_invoice;
	private String paid_channel;
	private String recurring_unique_id;
	private String paid_agent;
	private String payment_scheme;
	private String browser_info;
	private String ippPeriod;
	private String sub_merchant_list;
	private String ippInterestType;
	private String ippInterestRate;
	private String masked_pan;
	private String user_defined_1;
	private String user_defined_2;
	private String ippMerchantAbsorbRate;
	private String process_by;
	private String hash_value;
	private String checkHashStatus;
	private int version;
	private UserRecorder recorder;
	private String recordsState;
	private String vehicleNo;
	private boolean printReceipt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTwoCtwoPVersion() {
		return twoCtwoPVersion;
	}
	public void setTwoCtwoPVersion(String twoCtwoPVersion) {
		this.twoCtwoPVersion = twoCtwoPVersion;
	}
	public String getRequest_timestamp() {
		return request_timestamp;
	}
	public void setRequest_timestamp(String request_timestamp) {
		this.request_timestamp = request_timestamp;
	}
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		if(currency != null) {
			switch (currency) {
			case "104":
				this.currency = "MMK";
				break;
			case "840":
				this.currency = "USD";
				break;
			default:
				break;
			}
		}	
	}
	public String getInvoice_no() {
		return invoice_no;
	}
	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}
	public String getTransaction_ref() {
		return transaction_ref;
	}
	public void setTransaction_ref(String transaction_ref) {
		this.transaction_ref = transaction_ref;
	}
	public String getApproval_code() {
		return approval_code;
	}
	public void setApproval_code(String approval_code) {
		if(null != approval_code) {
			switch (approval_code) {
			case "010021":
				this.approval_code = "KBZPay QR";
				break;
			case "FT2022":
				this.approval_code = "CBPay QR";
				break;
			default:
				break;
			}
		}
	}
	public String getEci() {
		return eci;
	}
	public void setEci(String eci) {
		this.eci = eci;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		if(null != amount && amount.length() == 12) {
			amount = String.valueOf(Integer.parseInt(amount));
			String deciVal = amount.substring(amount.length()-2);
			this.amount = String.valueOf(Integer.parseInt(amount)).substring(0,amount.length()-2).concat("."+deciVal);
		}else {
			this.amount = "0";
		}
	}
	public String getTransaction_datetime() {
		return transaction_datetime;
	}
	public void setTransaction_datetime(String transaction_datetime) {
		this.transaction_datetime = transaction_datetime;
	}
	public String getPayment_channel() {
		return payment_channel;
	}
	public void setPayment_channel(String payment_channel) {
		if(null != payment_channel) {
			switch(payment_channel) {
			case "001":
				this.payment_channel = "Credit and debit cards";
				break;
			case "002":
				this.payment_channel = "Cash payment channel";
				break;
			case "003":
				this.payment_channel = "Direct debit";
				break;
			case "004":
				this.payment_channel = "Others";
				break;
			case "005":
				this.payment_channel = "IPP transaction";
				break;	
			}
		}
		
		this.payment_channel = payment_channel;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public String getChannel_response_code() {
		return channel_response_code;
	}
	public void setChannel_response_code(String channel_response_code) {
		this.channel_response_code = channel_response_code;
	}
	public String getChannel_response_desc() {
		return channel_response_desc;
	}
	public void setChannel_response_desc(String channel_response_desc) {
		this.channel_response_desc = channel_response_desc;
	}
	public String getStored_card_unique_id() {
		return stored_card_unique_id;
	}
	public void setStored_card_unique_id(String stored_card_unique_id) {
		this.stored_card_unique_id = stored_card_unique_id;
	}
	public String getBackend_invoice() {
		return backend_invoice;
	}
	public void setBackend_invoice(String backend_invoice) {
		this.backend_invoice = backend_invoice;
	}
	public String getPaid_channel() {
		return paid_channel;
	}
	public void setPaid_channel(String paid_channel) {
		this.paid_channel = paid_channel;
	}
	public String getRecurring_unique_id() {
		return recurring_unique_id;
	}
	public void setRecurring_unique_id(String recurring_unique_id) {
		this.recurring_unique_id = recurring_unique_id;
	}
	public String getPaid_agent() {
		return paid_agent;
	}
	public void setPaid_agent(String paid_agent) {
		this.paid_agent = paid_agent;
	}
	public String getPayment_scheme() {
		return payment_scheme;
	}
	public void setPayment_scheme(String payment_scheme) {
		this.payment_scheme = payment_scheme;
	}
	public String getBrowser_info() {
		return browser_info;
	}
	public void setBrowser_info(String browser_info) {
		this.browser_info = browser_info;
	}
	public String getIppPeriod() {
		return ippPeriod;
	}
	public void setIppPeriod(String ippPeriod) {
		this.ippPeriod = ippPeriod;
	}
	public String getSub_merchant_list() {
		return sub_merchant_list;
	}
	public void setSub_merchant_list(String sub_merchant_list) {
		this.sub_merchant_list = sub_merchant_list;
	}
	public String getIppInterestType() {
		return ippInterestType;
	}
	public void setIppInterestType(String ippInterestType) {
		this.ippInterestType = ippInterestType;
	}
	public String getIppInterestRate() {
		return ippInterestRate;
	}
	public void setIppInterestRate(String ippInterestRate) {
		this.ippInterestRate = ippInterestRate;
	}
	public String getMasked_pan() {
		return masked_pan;
	}
	public void setMasked_pan(String masked_pan) {
		this.masked_pan = masked_pan;
	}
	public String getUser_defined_1() {
		return user_defined_1;
	}
	public void setUser_defined_1(String user_defined_1) {
		this.user_defined_1 = user_defined_1;
	}
	public String getUser_defined_2() {
		return user_defined_2;
	}
	public void setUser_defined_2(String user_defined_2) {
		this.user_defined_2 = user_defined_2;
	}
	public String getIppMerchantAbsorbRate() {
		return ippMerchantAbsorbRate;
	}
	public void setIppMerchantAbsorbRate(String ippMerchantAbsorbRate) {
		this.ippMerchantAbsorbRate = ippMerchantAbsorbRate;
	}
	public String getProcess_by() {
		return process_by;
	}
	public void setProcess_by(String process_by) {
		if(null != process_by) {
			if(process_by.equals("MP")) {
				this.process_by = "MPU";
			}else if(process_by.equals("KB")) {
				this.process_by = "KBZ";
			}else if(process_by.equals("CB")) {
				this.process_by = "CB";
			}else if(process_by.equals("DL")) {
				this.process_by = "OK$";
			}
		}
	}
	public String getHash_value() {
		return hash_value;
	}
	public void setHash_value(String hash_value) {
		this.hash_value = hash_value;
	}
	public String getCheckHashStatus() {
		return checkHashStatus;
	}
	public void setCheckHashStatus(String checkHashStatus) {
		this.checkHashStatus = checkHashStatus;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public UserRecorder getRecorder() {
		return recorder;
	}
	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
	}
	public String getRecordsState() {
		return recordsState;
	}
	public void setRecordsState(String recordsState) {
		this.recordsState = recordsState;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public boolean isPrintReceipt() {
		return printReceipt;
	}
	public void setPrintReceipt(boolean printReceipt) {
		this.printReceipt = printReceipt;
	}
	
}
