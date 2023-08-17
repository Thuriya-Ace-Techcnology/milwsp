package org.ace.insurance.system.paymentTransaction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.ace.insurance.common.TableName;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = TableName.TRANSACTIONS)
public class Transactions {
	@Id
	@JsonProperty("TransactionId")
	private String transactionId;
	@JsonProperty("ResponseCode")
	private String responseCode;
	@JsonProperty("Destination")
	private String destination;
	@JsonProperty("Source")
	private String source;
	@JsonProperty("Amount")
	private String amount;
	@JsonProperty("TransactionTime")
	private String transactionTime;
	@JsonProperty("AgentName")
	private String agentName;
	@JsonProperty("KickValue")
	private String kickValue;
	@JsonProperty("Loyaltypoints")
	private String loyaltypoints;
	@JsonProperty("Description")
	private String description;
	@JsonProperty("MerRefNo")
	private String merRefNo;
	@JsonProperty("CustomerNumber")
	private String customerNumber;

	public Transactions() {
		super();
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getKickValue() {
		return kickValue;
	}

	public void setKickValue(String kickValue) {
		this.kickValue = kickValue;
	}

	public String getLoyaltypoints() {
		return loyaltypoints;
	}

	public void setLoyaltypoints(String loyaltypoints) {
		this.loyaltypoints = loyaltypoints;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMerRefNo() {
		return merRefNo;
	}

	public void setMerRefNo(String merRefNo) {
		this.merRefNo = merRefNo;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentName == null) ? 0 : agentName.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((customerNumber == null) ? 0 : customerNumber.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((kickValue == null) ? 0 : kickValue.hashCode());
		result = prime * result + ((loyaltypoints == null) ? 0 : loyaltypoints.hashCode());
		result = prime * result + ((merRefNo == null) ? 0 : merRefNo.hashCode());
		result = prime * result + ((responseCode == null) ? 0 : responseCode.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
		result = prime * result + ((transactionTime == null) ? 0 : transactionTime.hashCode());
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
		Transactions other = (Transactions) obj;
		if (agentName == null) {
			if (other.agentName != null)
				return false;
		} else if (!agentName.equals(other.agentName))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (customerNumber == null) {
			if (other.customerNumber != null)
				return false;
		} else if (!customerNumber.equals(other.customerNumber))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (kickValue == null) {
			if (other.kickValue != null)
				return false;
		} else if (!kickValue.equals(other.kickValue))
			return false;
		if (loyaltypoints == null) {
			if (other.loyaltypoints != null)
				return false;
		} else if (!loyaltypoints.equals(other.loyaltypoints))
			return false;
		if (merRefNo == null) {
			if (other.merRefNo != null)
				return false;
		} else if (!merRefNo.equals(other.merRefNo))
			return false;
		if (responseCode == null) {
			if (other.responseCode != null)
				return false;
		} else if (!responseCode.equals(other.responseCode))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		if (transactionTime == null) {
			if (other.transactionTime != null)
				return false;
		} else if (!transactionTime.equals(other.transactionTime))
			return false;
		return true;
	}

}
