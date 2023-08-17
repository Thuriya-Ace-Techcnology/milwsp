package org.ace.ws.model.transaction;

public class TwoC2PInfo {
	String merchantId;
	String secrectKey;
	String androidPrivateKey;
	String iosPrivateKey;

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getSecrectKey() {
		return secrectKey;
	}

	public void setSecrectKey(String secrectKey) {
		this.secrectKey = secrectKey;
	}

	public String getAndroidPrivateKey() {
		return androidPrivateKey;
	}

	public void setAndroidPrivateKey(String androidPrivateKey) {
		this.androidPrivateKey = androidPrivateKey;
	}

	public String getIosPrivateKey() {
		return iosPrivateKey;
	}

	public void setIosPrivateKey(String iosPrivateKey) {
		this.iosPrivateKey = iosPrivateKey;
	}

}
