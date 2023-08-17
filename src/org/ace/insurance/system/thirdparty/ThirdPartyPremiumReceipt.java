package org.ace.insurance.system.thirdparty;

/*
 * @author Kyaw Yea Lwin
 * @date 29/06/2020
 */
import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.insurance.product.PaymentStatus;
import org.ace.java.component.FormatID;
import org.ace.java.component.idgen.service.IDInterceptor;
import org.ace.ws.model.ResponseStatus;

@Entity
@Table(name = TableName.THIRDPARTY_PREMIUM_RECEIPT)
@TableGenerator(name = "ThirdParty_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "ThirdParty_GEN", allocationSize = 10)
@NamedQueries(value = {
		@NamedQuery(name = "ThirdPartyPremiumReceipt.deleteByOrderId", query = "DELETE FROM ThirdPartyPremiumReceipt t where t.order_id = :orderId "),
		@NamedQuery(name = "ThirdPartyPremiumReceipt.findByOrderId", query = "SELECT t FROM ThirdPartyPremiumReceipt t WHERE t.order_id = :orderId"),
		@NamedQuery(name = "ThirdPartyPremiumReceipt.findByDateAndVehicleNo", query = "SELECT t FROM ThirdPartyPremiumReceipt t WHERE t.period_from = :period_from and t.vehicle_no = :vehicle_no"),
		@NamedQuery(name = "ThirdPartyPremiumReceipt.responseStatusByOrderId", query = "UPDATE ThirdPartyPremiumReceipt m SET m.responseStatus = :responseStatus where m.order_id = :orderId"),
		@NamedQuery(name = "ThirdPartyPremiumReceipt.updateByPaymentStatus", query = "UPDATE ThirdPartyPremiumReceipt t SET t.paymentStatus = :paymentStaus where t.order_id = :orderId"),
		@NamedQuery(name = "ThirdPartyPremiumReceipt.findByResponseStatus", query = "SELECT m FROM ThirdPartyPremiumReceipt m WHERE m.paymentStatus = :paymentStatus and m.responseStatus != :responseStatus or m.responseStatus IS NULL"),
		@NamedQuery(name = "ThirdPartyPremiumReceipt.updateConvertStatusByOrderId", query = "UPDATE ThirdPartyPremiumReceipt m SET m.isConvert = :convert where m.order_id = :orderId") })
@Access(value = AccessType.FIELD)
@EntityListeners(IDInterceptor.class)
public class ThirdPartyPremiumReceipt implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	@Transient
	private String prefix;

	@Column(name = "OWNER_NAME")
	private String owner_name;

	@Column(name = "NRC_NO")
	private String nrc_no;

	@Column(name = "ADDRESS")
	private String address;

	@NotNull
	@Column(name = "VEHICLE_NO")
	private String vehicle_no;

	@Column(name = "BOOK_NO")
	private String book_no;

	@Column(name = "VEHICLE_NAME")
	private String vehicle_name;

	// @NotNull
	@Column(name = "VEHICLE_TYPE", nullable = false)
	private String vehicle_type;

	// @NotNull
	@Column(name = "CAPACITY", nullable = false)
	private String capacity;

	@Column(name = "PERIOD_FROM")
	private String period_from;

	// @NotNull
	@Column(name = "PERIOD_TO", nullable = false)
	private String period_to;

	// @NotNull
	@Column(name = "PREMIUM_AMOUNT", nullable = false)
	private String premium_amount;

	@Column(name = "RECEIPT_NO")
	private String receipt_no;

	@Column(name = "RECEIPT_DATE")
	private String receipt_date;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "RESPONSESTATUS")
	@Enumerated(EnumType.STRING)
	private ResponseStatus responseStatus;

	// @NotNull
	@Column(name = "RTA_BRANCH", nullable = false)
	private String rta_branch;

	@Column(name = "BUY_DATE")
	private String buyDate;

	@Column(name = "ORDER_ID")
	private String order_id;

	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	@Column(name = "ISCONVERT")
	private boolean isConvert;

	@Version
	private int version;

	@Embedded
	private UserRecorder recorder;

	public ThirdPartyPremiumReceipt() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ThirdParty_GEN")
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public UserRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
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

	public boolean isConvert() {
		return isConvert;
	}

	public void setConvert(boolean isConvert) {
		this.isConvert = isConvert;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((book_no == null) ? 0 : book_no.hashCode());
		result = prime * result + ((buyDate == null) ? 0 : buyDate.hashCode());
		result = prime * result + ((capacity == null) ? 0 : capacity.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nrc_no == null) ? 0 : nrc_no.hashCode());
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
		result = prime * result + ((owner_name == null) ? 0 : owner_name.hashCode());
		result = prime * result + ((paymentStatus == null) ? 0 : paymentStatus.hashCode());
		result = prime * result + ((period_from == null) ? 0 : period_from.hashCode());
		result = prime * result + ((period_to == null) ? 0 : period_to.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		result = prime * result + ((premium_amount == null) ? 0 : premium_amount.hashCode());
		result = prime * result + ((receipt_date == null) ? 0 : receipt_date.hashCode());
		result = prime * result + ((receipt_no == null) ? 0 : receipt_no.hashCode());
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
		result = prime * result + ((rta_branch == null) ? 0 : rta_branch.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((vehicle_name == null) ? 0 : vehicle_name.hashCode());
		result = prime * result + ((vehicle_no == null) ? 0 : vehicle_no.hashCode());
		result = prime * result + ((vehicle_type == null) ? 0 : vehicle_type.hashCode());
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
		ThirdPartyPremiumReceipt other = (ThirdPartyPremiumReceipt) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (book_no == null) {
			if (other.book_no != null)
				return false;
		} else if (!book_no.equals(other.book_no))
			return false;
		if (buyDate == null) {
			if (other.buyDate != null)
				return false;
		} else if (!buyDate.equals(other.buyDate))
			return false;
		if (capacity == null) {
			if (other.capacity != null)
				return false;
		} else if (!capacity.equals(other.capacity))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nrc_no == null) {
			if (other.nrc_no != null)
				return false;
		} else if (!nrc_no.equals(other.nrc_no))
			return false;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (owner_name == null) {
			if (other.owner_name != null)
				return false;
		} else if (!owner_name.equals(other.owner_name))
			return false;
		if (paymentStatus != other.paymentStatus)
			return false;
		if (period_from == null) {
			if (other.period_from != null)
				return false;
		} else if (!period_from.equals(other.period_from))
			return false;
		if (period_to == null) {
			if (other.period_to != null)
				return false;
		} else if (!period_to.equals(other.period_to))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (premium_amount == null) {
			if (other.premium_amount != null)
				return false;
		} else if (!premium_amount.equals(other.premium_amount))
			return false;
		if (receipt_date == null) {
			if (other.receipt_date != null)
				return false;
		} else if (!receipt_date.equals(other.receipt_date))
			return false;
		if (receipt_no == null) {
			if (other.receipt_no != null)
				return false;
		} else if (!receipt_no.equals(other.receipt_no))
			return false;
		if (recorder == null) {
			if (other.recorder != null)
				return false;
		} else if (!recorder.equals(other.recorder))
			return false;
		if (rta_branch == null) {
			if (other.rta_branch != null)
				return false;
		} else if (!rta_branch.equals(other.rta_branch))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (vehicle_name == null) {
			if (other.vehicle_name != null)
				return false;
		} else if (!vehicle_name.equals(other.vehicle_name))
			return false;
		if (vehicle_no == null) {
			if (other.vehicle_no != null)
				return false;
		} else if (!vehicle_no.equals(other.vehicle_no))
			return false;
		if (vehicle_type == null) {
			if (other.vehicle_type != null)
				return false;
		} else if (!vehicle_type.equals(other.vehicle_type))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}
