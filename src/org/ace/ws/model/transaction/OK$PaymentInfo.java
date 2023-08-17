package org.ace.ws.model.transaction;

public class OK$PaymentInfo {

	String merchantName;
	String merchantBackendNumber;
	String apiKey;
	String secrectKey;

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantBackendNumber() {
		return merchantBackendNumber;
	}

	public void setMerchantBackendNumber(String merchantBackendNumber) {
		this.merchantBackendNumber = merchantBackendNumber;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSecrectKey() {
		return secrectKey;
	}

	public void setSecrectKey(String secrectKey) {
		this.secrectKey = secrectKey;
	}

}
