package org.ace.ws.model.mobilePersonalAccidentproposal;

import org.ace.ws.model.ResponseStatus;

public class MPAP002 {
	private String transactionId;
	private ResponseStatus responseStatus;

	public MPAP002() {

	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

}
