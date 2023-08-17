package org.ace.insurance.system.twoCtwoP;
/*
 * @author Kyaw Yea Lwin
 * @date 06/10/2020
 */
import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.java.component.FormatID;
import org.ace.java.component.idgen.service.IDInterceptor;

@Entity
@Table(name = TableName.TWOCTWOP_RECORDS)
@TableGenerator(name = "TWOCTWOP_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "TWOCTWOP_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "TwoCTwoPRecords.findByOrderId", query = "SELECT t FROM TwoCTwoPRecords t where t.order_id = :orderId "),})
@Access(value = AccessType.FIELD)
@EntityListeners(IDInterceptor.class)
public class TwoCTwoPRecords implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	@Transient
	private String prefix;
	
	@Column(name = "TWOCTWOPVERSION")
	private String twoCtwoPVersion;
	
	@Column(name = "REQUEST_TIMESTAMP")
	private String request_timestamp;
	
	@Column(name = "MERCHANT_ID")
	private String merchant_id;
	
	@Column(name = "CURRENCY")
	private String currency;
	
	@Column(name = "INVOICE_NO")
	private String invoice_no;
	
	@Column(name = "TRANSACTION_REF")
	private String transaction_ref;
	
	@Column(name = "APPROVAL_CODE")
	private String approval_code;
	
	@Column(name = "ECI")
	private String eci;
	
	@Column(name = "ORDER_ID")
	private String order_id;
	
	@Column(name = "AMOUNT")
	private String amount;
	
	@Column(name = "TRANSACTION_DATETIME")
	private String transaction_datetime;
	
	@Column(name = "PAYMENT_CHANNEL")
	private String payment_channel;
	
	@Column(name = "PAYMENT_STATUS")
	private String payment_status;
	
	@Column(name = "CHANNEL_RESPONSE_CODE")
	private String channel_response_code;
	
	@Column(name = "CHANNEL_RESPONSE_DESC")
	private String channel_response_desc;
	
	@Column(name = "STORED_CARD_UNIQUE_ID")
	private String stored_card_unique_id;
	
	@Column(name = "BACKEND_INVOICE")
	private String backend_invoice;
	
	@Column(name = "PAID_CHANNEL")
	private String paid_channel;
	
	@Column(name = "RECURRING_UNIQUE_ID")
	private String recurring_unique_id;
	
	@Column(name = "PAID_AGENT")
	private String paid_agent;
	
	@Column(name = "PAYMENT_SCHEME")
	private String payment_scheme;
	
	@Column(name = "BROWSER_INFO")
	private String browser_info;
	
	@Column(name = "IPPPERIOD")
	private String ippPeriod;
	
	@Column(name = "SUB_MERCHANT_LIST")
	private String sub_merchant_list;
	
	@Column(name = "IPPINTERESTTYPE")
	private String ippInterestType;
	
	@Column(name = "IPPINTERESTRATE")
	private String ippInterestRate;
	
	@Column(name = "MASKED_PAN")
	private String masked_pan;
	
	@Column(name = "USER_DEFINED_1")
	private String user_defined_1;
	
	@Column(name = "USER_DEFINED_2")
	private String user_defined_2;
	
	@Column(name = "IPPMERCHANTABSORBRATE")
	private String ippMerchantAbsorbRate;

	@Column(name = "PROCESS_BY")
	private String process_by;
	
	@Column(name = "HASH_VALUE")
	private String hash_value;
	
	@Column(name = "CHECKHASHSTATUS")
	private String checkHashStatus;
	@Version
	private int version;

	@Embedded
	private UserRecorder recorder;

	public TwoCTwoPRecords() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TWOCTWOP_GEN")
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
		this.currency = currency;
	}

	
	public String getCheckHashStatus() {
		return checkHashStatus;
	}

	public void setCheckHashStatus(String checkHashStatus) {
		this.checkHashStatus = checkHashStatus;
	}

	public String getProcess_by() {
		return process_by;
	}

	public void setProcess_by(String process_by) {
		this.process_by = process_by;
	}

	public String getHash_value() {
		return hash_value;
	}

	public void setHash_value(String hash_value) {
		this.hash_value = hash_value;
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
		this.approval_code = approval_code;
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
		this.amount = amount;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((approval_code == null) ? 0 : approval_code.hashCode());
		result = prime * result + ((backend_invoice == null) ? 0 : backend_invoice.hashCode());
		result = prime * result + ((browser_info == null) ? 0 : browser_info.hashCode());
		result = prime * result + ((channel_response_code == null) ? 0 : channel_response_code.hashCode());
		result = prime * result + ((channel_response_desc == null) ? 0 : channel_response_desc.hashCode());
		result = prime * result + ((checkHashStatus == null) ? 0 : checkHashStatus.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((eci == null) ? 0 : eci.hashCode());
		result = prime * result + ((hash_value == null) ? 0 : hash_value.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((invoice_no == null) ? 0 : invoice_no.hashCode());
		result = prime * result + ((ippInterestRate == null) ? 0 : ippInterestRate.hashCode());
		result = prime * result + ((ippInterestType == null) ? 0 : ippInterestType.hashCode());
		result = prime * result + ((ippMerchantAbsorbRate == null) ? 0 : ippMerchantAbsorbRate.hashCode());
		result = prime * result + ((ippPeriod == null) ? 0 : ippPeriod.hashCode());
		result = prime * result + ((masked_pan == null) ? 0 : masked_pan.hashCode());
		result = prime * result + ((merchant_id == null) ? 0 : merchant_id.hashCode());
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
		result = prime * result + ((paid_agent == null) ? 0 : paid_agent.hashCode());
		result = prime * result + ((paid_channel == null) ? 0 : paid_channel.hashCode());
		result = prime * result + ((payment_channel == null) ? 0 : payment_channel.hashCode());
		result = prime * result + ((payment_scheme == null) ? 0 : payment_scheme.hashCode());
		result = prime * result + ((payment_status == null) ? 0 : payment_status.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		result = prime * result + ((process_by == null) ? 0 : process_by.hashCode());
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
		result = prime * result + ((recurring_unique_id == null) ? 0 : recurring_unique_id.hashCode());
		result = prime * result + ((request_timestamp == null) ? 0 : request_timestamp.hashCode());
		result = prime * result + ((stored_card_unique_id == null) ? 0 : stored_card_unique_id.hashCode());
		result = prime * result + ((sub_merchant_list == null) ? 0 : sub_merchant_list.hashCode());
		result = prime * result + ((transaction_datetime == null) ? 0 : transaction_datetime.hashCode());
		result = prime * result + ((transaction_ref == null) ? 0 : transaction_ref.hashCode());
		result = prime * result + ((twoCtwoPVersion == null) ? 0 : twoCtwoPVersion.hashCode());
		result = prime * result + ((user_defined_1 == null) ? 0 : user_defined_1.hashCode());
		result = prime * result + ((user_defined_2 == null) ? 0 : user_defined_2.hashCode());
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
		TwoCTwoPRecords other = (TwoCTwoPRecords) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (approval_code == null) {
			if (other.approval_code != null)
				return false;
		} else if (!approval_code.equals(other.approval_code))
			return false;
		if (backend_invoice == null) {
			if (other.backend_invoice != null)
				return false;
		} else if (!backend_invoice.equals(other.backend_invoice))
			return false;
		if (browser_info == null) {
			if (other.browser_info != null)
				return false;
		} else if (!browser_info.equals(other.browser_info))
			return false;
		if (channel_response_code == null) {
			if (other.channel_response_code != null)
				return false;
		} else if (!channel_response_code.equals(other.channel_response_code))
			return false;
		if (channel_response_desc == null) {
			if (other.channel_response_desc != null)
				return false;
		} else if (!channel_response_desc.equals(other.channel_response_desc))
			return false;
		if (checkHashStatus == null) {
			if (other.checkHashStatus != null)
				return false;
		} else if (!checkHashStatus.equals(other.checkHashStatus))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (eci == null) {
			if (other.eci != null)
				return false;
		} else if (!eci.equals(other.eci))
			return false;
		if (hash_value == null) {
			if (other.hash_value != null)
				return false;
		} else if (!hash_value.equals(other.hash_value))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (invoice_no == null) {
			if (other.invoice_no != null)
				return false;
		} else if (!invoice_no.equals(other.invoice_no))
			return false;
		if (ippInterestRate == null) {
			if (other.ippInterestRate != null)
				return false;
		} else if (!ippInterestRate.equals(other.ippInterestRate))
			return false;
		if (ippInterestType == null) {
			if (other.ippInterestType != null)
				return false;
		} else if (!ippInterestType.equals(other.ippInterestType))
			return false;
		if (ippMerchantAbsorbRate == null) {
			if (other.ippMerchantAbsorbRate != null)
				return false;
		} else if (!ippMerchantAbsorbRate.equals(other.ippMerchantAbsorbRate))
			return false;
		if (ippPeriod == null) {
			if (other.ippPeriod != null)
				return false;
		} else if (!ippPeriod.equals(other.ippPeriod))
			return false;
		if (masked_pan == null) {
			if (other.masked_pan != null)
				return false;
		} else if (!masked_pan.equals(other.masked_pan))
			return false;
		if (merchant_id == null) {
			if (other.merchant_id != null)
				return false;
		} else if (!merchant_id.equals(other.merchant_id))
			return false;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (paid_agent == null) {
			if (other.paid_agent != null)
				return false;
		} else if (!paid_agent.equals(other.paid_agent))
			return false;
		if (paid_channel == null) {
			if (other.paid_channel != null)
				return false;
		} else if (!paid_channel.equals(other.paid_channel))
			return false;
		if (payment_channel == null) {
			if (other.payment_channel != null)
				return false;
		} else if (!payment_channel.equals(other.payment_channel))
			return false;
		if (payment_scheme == null) {
			if (other.payment_scheme != null)
				return false;
		} else if (!payment_scheme.equals(other.payment_scheme))
			return false;
		if (payment_status == null) {
			if (other.payment_status != null)
				return false;
		} else if (!payment_status.equals(other.payment_status))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (process_by == null) {
			if (other.process_by != null)
				return false;
		} else if (!process_by.equals(other.process_by))
			return false;
		if (recorder == null) {
			if (other.recorder != null)
				return false;
		} else if (!recorder.equals(other.recorder))
			return false;
		if (recurring_unique_id == null) {
			if (other.recurring_unique_id != null)
				return false;
		} else if (!recurring_unique_id.equals(other.recurring_unique_id))
			return false;
		if (request_timestamp == null) {
			if (other.request_timestamp != null)
				return false;
		} else if (!request_timestamp.equals(other.request_timestamp))
			return false;
		if (stored_card_unique_id == null) {
			if (other.stored_card_unique_id != null)
				return false;
		} else if (!stored_card_unique_id.equals(other.stored_card_unique_id))
			return false;
		if (sub_merchant_list == null) {
			if (other.sub_merchant_list != null)
				return false;
		} else if (!sub_merchant_list.equals(other.sub_merchant_list))
			return false;
		if (transaction_datetime == null) {
			if (other.transaction_datetime != null)
				return false;
		} else if (!transaction_datetime.equals(other.transaction_datetime))
			return false;
		if (transaction_ref == null) {
			if (other.transaction_ref != null)
				return false;
		} else if (!transaction_ref.equals(other.transaction_ref))
			return false;
		if (twoCtwoPVersion == null) {
			if (other.twoCtwoPVersion != null)
				return false;
		} else if (!twoCtwoPVersion.equals(other.twoCtwoPVersion))
			return false;
		if (user_defined_1 == null) {
			if (other.user_defined_1 != null)
				return false;
		} else if (!user_defined_1.equals(other.user_defined_1))
			return false;
		if (user_defined_2 == null) {
			if (other.user_defined_2 != null)
				return false;
		} else if (!user_defined_2.equals(other.user_defined_2))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	

}
