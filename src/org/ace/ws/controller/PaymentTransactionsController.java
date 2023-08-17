package org.ace.ws.controller;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.ace.insurance.system.paymentTransaction.Transactions;
import org.ace.insurance.system.paymentTransaction.persistence.interfaces.ITransactionsDAO;
import org.ace.insurance.travel.service.interfaces.IMobileTravelProposalService;
import org.ace.java.component.StatusType;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.interfaces.IDataRepository;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.transaction.OK$PaymentInfo;
import org.ace.ws.model.transaction.TRAN001;
import org.ace.ws.model.transaction.TwoC2P001;
import org.ace.ws.model.transaction.TwoC2PInfo;
import org.bouncycastle.util.encoders.Base64;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccpp.PKCS7;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class PaymentTransactionsController extends BaseController {
	@Resource(name = "DataRepository")
	private IDataRepository<Transactions> transactionsRespository;

	@Resource(name = "TransactionsDAO")
	private ITransactionsDAO transactionsDAO;

	@Resource(name = "MobileTravelProposalService")
	private IMobileTravelProposalService mobileTravelProposalService;

	@RequestMapping(value = URIConstants.OK$_PAYMENT, method = RequestMethod.POST)
	@ResponseBody
	// public String postPaymentStatus(@RequestBody Transactions transaction)
	// throws ServiceException {
	public String postPaymentStatus(@RequestBody String request) throws ServiceException {
		String response = null;
		try {

			System.out.println("Request String :" + request);

			// transaction =
			// "PayMentDet=zSYJOuSw%2FxSDMAi48BGmYu0GkmDn%2F5j1OzZ806XcLGxwCfn7v4Z5L8hHZjiyVk8pjlHsytI0MJIAk5oSGSRgVKTC6SxBMtDCkKRIuoXAZtb%2F4kCe72ZsTniPpRl9%2FqYiI64crjpBAXTJ5601offfPAmgb9aT6ygpxjbwldl2UL%2BZABCtpqFYO%2B3PZsb42HhHYq9UzStKo5FS0CYPm%2BGK9Q%2Bmtl%2B89OPU0FlYc9ADLjVieDidltNPoH4h%2BaPzGwHDBcih25cR7WQ%2Fn4LELsBxfJK8dK1iqJe%2FWVCkclh69E8Rqwm2dibvUJUb9qGlIh0vgRKp29d8FMuC6XfKOONu%2BGNQAWnPbpxoEe3keY4u4ssdQgz3OnE77FVFMGr3ygHS0zPyYgXCIJLhwZzRZZjxaOoEz14Mo69i38CpJOibh62%2FaJvzGKHI4WW9x8IKF5jauJvvG1vpJ0HEjA1nqaZYEn0%2BDxE6hvjBi1ecwMpe2vmGzZZf%2BJ6d4v4KMhAX4EU3IZsCjHfqM1vv%2FgKhDXKUe9gtqicdz41XoHiRreN6CRZHywv57i6uLThe0G2VCR2E5J4If7WljQMD7VfFe7PSWutfep%2F6WUHnIGVWFNnM7Us%3D%2CGGIP000000000002%2C00959768187249";
			// String key = Constants.getOk$secretkey();
			//
			// transaction = transaction.substring(11, transaction.length());
			//
			// String encrypted = java.net.URLDecoder.decode(transaction,
			// "UTF-8");
			//
			// String[] test = encrypted.split(",");
			// String ivector = test[1];
			// encrypted = test[0];
			//
			// System.err.println("key (" + key + ")");
			// System.err.println("ivector (" + ivector + ")");
			// System.err.println("encrypted (" + encrypted + ")");
			// String decrypted = ASE128.decrypt(key, ivector, encrypted);
			// System.err.println("decrypted (" + decrypted + ")");

			// String xmlString = PKCS7.decrypt(Constants.getPrivateKey(),
			// Constants.getPrivateKeyPwd(), Base64.decode(encrypted),
			// Constants.getBksPassword());
			// JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
			// System.out.println(xmlJSONObj);
			// if (transaction != null && transaction.getTransactionId() !=
			// null) {
			// transactionsRespository.insert(transaction);
			// response =
			// responseManager.getResponseString(transaction.getDescription());
			// } else {
			// response =
			// responseManager.getResponseString(ResponseStatus.FAIL.getLabel());
			// }
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.FAIL.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = URIConstants.GET_PAYMENT_STATUS, method = RequestMethod.GET)
	@ResponseBody
	public String getPaymentStatus(@RequestHeader String key, @PathVariable String refNo, @PathVariable String destination) throws ServiceException {
		String response = null;
		try {
			if (key.toString().equals(Constants.getApikey())) {
				if (refNo != null) {
					response = transactionsDAO.findStatusByMerRefNoAndDestination(refNo, destination);
				} else {
					response = responseManager.getResponseString(ResponseStatus.FAIL.getLabel());
				}
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}

		} catch (SystemException e) {
			e.printStackTrace();
			response = ResponseStatus.INTERNAL_SERVER_ERROR.getLabel();
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}

	@RequestMapping(value = URIConstants.GET_PAYMENT_STATUS_BY_REFNO, method = RequestMethod.POST)
	@ResponseBody
	public String getPaymentStatusWithPost(@RequestHeader String key, @RequestBody TRAN001 dto) throws ServiceException {
		String response = null;
		try {
			if (key.toString().equals(Constants.getApikey())) {
				if (dto != null && dto.getRefNo() != null && dto.getDestination() != null) {
					response = transactionsDAO.findStatusByMerRefNoAndDestination(dto.getRefNo(), dto.getDestination());
					response = response.isEmpty() ? ResponseStatus.REQUEST_NOT_FOUND.getLabel() : response;
				} else {
					response = ResponseStatus.INVALID_REQUEST_PARAM.getLabel();
				}
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}

		} catch (SystemException e) {
			e.printStackTrace();
			response = ResponseStatus.INTERNAL_SERVER_ERROR.getLabel();
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}

	@RequestMapping(value = URIConstants.TWOC2P_PAYMENT, method = RequestMethod.POST)
	public @ResponseBody String getTwoC2pPayment(@RequestBody String twoC2P001) throws ServiceException {
		String response = "";
		Gson gson = new GsonBuilder().create();
		try {
			System.out.println(twoC2P001);
			twoC2P001 = twoC2P001.substring(16, twoC2P001.length());
			String encrypted = java.net.URLDecoder.decode(twoC2P001, "UTF-8");
			String xmlString = PKCS7.decrypt(Constants.getPrivateKey(), Constants.getPrivateKeyPwd(), Base64.decode(encrypted), Constants.getBksPassword());
			JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
			TwoC2P001 twoC2P = gson.fromJson(xmlJSONObj.toString(), TwoC2P001.class);
			mobileTravelProposalService.updateTravelProposal(twoC2P);
			response = responseManager.getResponseString(ResponseStatus.SUCCESS.getLabel());
		} catch (SystemException e) {
			e.printStackTrace();
			throw new ServiceException(StatusType.SQL_Exception);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = URIConstants.IOS_PAYMENT_CONSTANTS, method = RequestMethod.POST)
	@ResponseBody
	public String getIOS2C2PInfo(@RequestHeader String key) throws ServiceException {
		String response = null;
		try {
			if (key != null && key.toString().equals(Constants.getApikey())) {
				TwoC2PInfo twoC2PInfo = new TwoC2PInfo();
				twoC2PInfo.setMerchantId(Constants.getMerchantid());
				twoC2PInfo.setIosPrivateKey(Constants.getIosprivatekey());
				twoC2PInfo.setSecrectKey(Constants.getSecrectkey());
				response = responseManager.getResponseString(twoC2PInfo);
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM);
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = ResponseStatus.INTERNAL_SERVER_ERROR.getLabel();
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}

	@RequestMapping(value = URIConstants.ANDORID_PAYMENT_CONSTANTS, method = RequestMethod.POST)
	@ResponseBody
	public String getAndroid2C2PInfo(@RequestHeader String key) throws ServiceException {
		String response = null;
		try {
			if (key != null && key.toString().equals(Constants.getApikey())) {
				TwoC2PInfo twoC2PInfo = new TwoC2PInfo();
				twoC2PInfo.setMerchantId(Constants.getMerchantid());
				twoC2PInfo.setAndroidPrivateKey(Constants.getAndroidprivatekey());
				twoC2PInfo.setSecrectKey(Constants.getSecrectkey());
				response = responseManager.getResponseString(twoC2PInfo);
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM);
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = ResponseStatus.INTERNAL_SERVER_ERROR.getLabel();
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}

	@RequestMapping(value = URIConstants.OK$_PAYMENT_CONSTANTS, method = RequestMethod.POST)
	@ResponseBody
	public String getOk$PaymentInfo(@RequestHeader String key) throws ServiceException {
		String response = null;
		try {
			if (key != null && key.toString().equals(Constants.getApikey())) {
				OK$PaymentInfo ok$PaymentInfo = new OK$PaymentInfo();
				ok$PaymentInfo.setMerchantName(Constants.getOk$merchantname());
				ok$PaymentInfo.setMerchantBackendNumber(Constants.getOk$merchantbackendnumber());
				ok$PaymentInfo.setApiKey(Constants.getOk$apikey());
				ok$PaymentInfo.setSecrectKey(Constants.getOk$secretkey());
				response = responseManager.getResponseString(ok$PaymentInfo);
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM);
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = ResponseStatus.INTERNAL_SERVER_ERROR.getLabel();
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}
}
