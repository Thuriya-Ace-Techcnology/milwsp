package org.ace.ws.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import javax.annotation.Resource;

import org.ace.insurance.common.KeyFactorIDConfig;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.medical.MobileMedicalProposal;
import org.ace.insurance.medical.service.interfaces.IMobileMedicalProposalService;
import org.ace.insurance.onlineBiller.OnlineBillerBuyer;
import org.ace.insurance.onlineBiller.service.interfaces.IOnlineBillerProposalService;
import org.ace.insurance.personalAccident.MobilePersonalAccidentProposal;
import org.ace.insurance.personalAccident.service.interfaces.IMobilePersonalAccidentProposalService;
import org.ace.insurance.product.PaymentStatus;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravel;
import org.ace.insurance.specailForeignTravel.service.interfaces.ISpecialForeignTravelService;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.system.productTypeRecords.service.interfaces.IProductTypeRecordsService;
import org.ace.insurance.system.thirdparty.ThirdPartyPremiumReceipt;
import org.ace.insurance.system.thirdparty.service.interfaces.IThirdPartyPremiumReceiptSerivce;
import org.ace.insurance.system.twoCtwoP.TwoCTwoPRecords;
import org.ace.insurance.system.twoCtwoP.service.interfaces.ITwoCTwoPRecordsService;
import org.ace.insurance.thirdPartyDriverLicense.ThirdPartyDriverProposal;
import org.ace.insurance.thirdPartyDriverLicense.service.interfaces.IThirdPartyDriverService;
import org.ace.insurance.travel.MobileTravelProposal;
import org.ace.insurance.travel.service.interfaces.IMobileTravelProposalService;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.TwoCTwoPDTO.HmaceGenerator;
import org.ace.ws.model.TwoCTwoPDTO.TwoCTwoPResponseDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TwoCTwoPController extends BaseController {

	@Resource(name = "TwoCTwoPRecordsService")
	private ITwoCTwoPRecordsService twoC2pRecordsService;

	@Resource(name = "ProductTypeRecordsService")
	private IProductTypeRecordsService productTypeRecordsService;

	@Resource(name = "MobilePersonalAccidentProposalService")
	private IMobilePersonalAccidentProposalService mobilePersonalAccidentProposalService;

	@Resource(name = "MobileTravelProposalService")
	private IMobileTravelProposalService mobileTravelProposalService;

	@Resource(name = "ThirdPartyPremiumReceiptService")
	private IThirdPartyPremiumReceiptSerivce thirdPartyPremiumReceiptSerivce;

	@Resource(name = "MobileMedicalProposalService")
	private IMobileMedicalProposalService mobileMedicalProposalService;

	@Resource(name = "SpecialForeignTravelService")
	private ISpecialForeignTravelService specialForeignTravelService;

	@Resource(name = "OnlineBillerProposalService")
	private IOnlineBillerProposalService onlineBillerProposalService;

	@Resource(name = "ThirdPartyDriverService")
	private IThirdPartyDriverService thirdPartyDriverService;

	private static final Logger logger = Logger.getLogger(TwoCTwoPController.class);

	@RequestMapping(value = "/2c2p", method = RequestMethod.POST)
	@ResponseBody
	public void test(@RequestParam(name = "version", required = false) String version,
			@RequestParam(name = "request_timestamp", required = false) String request_timestamp,
			@RequestParam(name = "merchant_id", required = false) String merchant_id,
			@RequestParam(name = "currency", required = false) String currency,
			@RequestParam(name = "invoice_no", required = false) String invoice_no,
			@RequestParam(name = "transaction_ref", required = false) String transaction_ref,
			@RequestParam(name = "approval_code", required = false) String approval_code,
			@RequestParam(name = "eci", required = false) String eci,
			@RequestParam(name = "order_id", required = false) String order_id,
			@RequestParam(name = "amount", required = false) String amount,
			@RequestParam(name = "transaction_datetime", required = false) String transaction_datetime,
			@RequestParam(name = "payment_channel", required = false) String payment_channel,
			@RequestParam(name = "payment_status", required = false) String payment_status,
			@RequestParam(name = "channel_response_code", required = false) String channel_response_code,
			@RequestParam(name = "channel_response_desc", required = false) String channel_response_desc,
			@RequestParam(name = "stored_card_unique_id", required = false) String stored_card_unique_id,
			@RequestParam(name = "backend_invoice", required = false) String backend_invoice,
			@RequestParam(name = "paid_channel", required = false) String paid_channel,
			@RequestParam(name = "recurring_unique_id", required = false) String recurring_unique_id,
			@RequestParam(name = "paid_agent", required = false) String paid_agent,
			@RequestParam(name = "payment_scheme", required = false) String payment_scheme,
			@RequestParam(name = "browser_info", required = false) String browser_info,
			@RequestParam(name = "ippPeriod", required = false) String ippPeriod,
			@RequestParam(name = "sub_merchant_list", required = false) String sub_merchant_list,
			@RequestParam(name = "process_by", required = false) String process_by,
			@RequestParam(name = "hash_value", required = false) String hash_value,
			@RequestParam(name = "ippInterestType", required = false) String ippInterestType,
			@RequestParam(name = "ippInterestRate", required = false) String ippInterestRate,
			@RequestParam(name = "masked_pan", required = false) String masked_pan,
			@RequestParam(name = "user_defined_1", required = false) String user_defined_1,
			@RequestParam(name = "user_defined_2", required = false) String user_defined_2,
			@RequestParam(name = "user_defined_3", required = false) String user_defined_3,
			@RequestParam(name = "user_defined_4", required = false) String user_defined_4,
			@RequestParam(name = "user_defined_5", required = false) String user_defined_5,
			@RequestParam(name = "ippMerchantAbsorbRate", required = false) String ippMerchantAbsorbRate)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		
		logger.info("Start 2c2p server to server API call by orderId : "+order_id);
		String checkHashStr = version + request_timestamp + merchant_id + order_id + invoice_no + currency + amount
				+ transaction_ref + approval_code + eci + transaction_datetime + payment_channel + payment_status
				+ channel_response_code + channel_response_desc + masked_pan + stored_card_unique_id + backend_invoice
				+ paid_channel + paid_agent + recurring_unique_id + user_defined_1 + user_defined_2 + user_defined_3
				+ user_defined_4 + user_defined_5 + browser_info + ippPeriod + ippInterestType + ippInterestRate
				+ ippMerchantAbsorbRate + payment_scheme + process_by + sub_merchant_list;
		String secretKey = KeyFactorIDConfig.getTwoCTwoPSecKey();
		String checkHash = HmaceGenerator.calculateRFC2104HMAC(checkHashStr, secretKey);
		TwoCTwoPRecords twoC2PRecords = new TwoCTwoPRecords();
		twoC2PRecords.setTwoCtwoPVersion(version);
		twoC2PRecords.setRequest_timestamp(request_timestamp);
		twoC2PRecords.setMerchant_id(merchant_id);
		twoC2PRecords.setCurrency(currency);
		twoC2PRecords.setInvoice_no(invoice_no);
		twoC2PRecords.setTransaction_ref(transaction_ref);
		twoC2PRecords.setApproval_code(approval_code);
		twoC2PRecords.setEci(eci);
		twoC2PRecords.setOrder_id(order_id);
		twoC2PRecords.setAmount(amount);
		twoC2PRecords.setTransaction_datetime(transaction_datetime);
		twoC2PRecords.setPayment_channel(payment_channel);
		twoC2PRecords.setPayment_status(payment_status);
		twoC2PRecords.setChannel_response_code(channel_response_code);
		twoC2PRecords.setChannel_response_desc(channel_response_desc);
		twoC2PRecords.setStored_card_unique_id(stored_card_unique_id);
		twoC2PRecords.setBackend_invoice(backend_invoice);
		twoC2PRecords.setPaid_channel(paid_channel);
		twoC2PRecords.setRecurring_unique_id(recurring_unique_id);
		twoC2PRecords.setPaid_agent(paid_agent);
		twoC2PRecords.setPayment_scheme(payment_scheme);
		twoC2PRecords.setBrowser_info(browser_info);
		twoC2PRecords.setIppPeriod(ippPeriod);
		twoC2PRecords.setSub_merchant_list(sub_merchant_list);
		twoC2PRecords.setProcess_by(process_by);
		twoC2PRecords.setHash_value(hash_value);
		twoC2PRecords.setIppInterestType(ippInterestType);
		twoC2PRecords.setIppInterestRate(ippInterestRate);
		twoC2PRecords.setMasked_pan(masked_pan);
		twoC2PRecords.setUser_defined_1(user_defined_1);
		twoC2PRecords.setUser_defined_2(user_defined_2);
		twoC2PRecords.setIppMerchantAbsorbRate(ippMerchantAbsorbRate);
		if (checkHashStr.equals(checkHash)) {
			twoC2PRecords.setCheckHashStatus("Success");
		} else {
			twoC2PRecords.setCheckHashStatus("Fail");
		}
		twoC2pRecordsService.insert(twoC2PRecords);
		ThirdPartyPremiumReceipt receipt = null;
		MobileTravelProposal mobileTravelProposal = null;
		MobilePersonalAccidentProposal mobilePersonalAccidentProposal = null;
		MobileMedicalProposal mobileMedicalProposal = null;
		OnlineBillerBuyer onlineBuyer = null;
		SpecialForeignTravel specailForeignTravel = null;
		ThirdPartyDriverProposal thirdPartyDriver = null;
		ProductTypeRecords typeRecords = productTypeRecordsService.findByOrderId(order_id);
		if (twoC2PRecords.getPayment_status().equals("000")) {
			logger.info("Product Type : " + typeRecords.getProductType());
			switch (typeRecords.getProductType()) {
			case "TPL Online":
				receipt = thirdPartyPremiumReceiptSerivce.updateByPaymentStatus(order_id);
				if (receipt.getPaymentStatus().equals(PaymentStatus.SUCCESS)) {
					Runnable tpRun = () -> thirdPartyPremiumReceiptSerivce.postDataSyncProcess(order_id);
					new Thread(tpRun).start();
				}
				break;
			case "Travel":
				mobileTravelProposal = mobileTravelProposalService.updateByPaymentStatus(order_id);
				if (mobileTravelProposal.getProposalStatus().equals(ProposalStatus.ISSUED)) {
					Runnable tvRun = () -> {
						try {
							mobileTravelProposalService.postDataSyncProcess(order_id);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					};
					new Thread(tvRun).start();
				}
				break;
			case "PA":
				mobilePersonalAccidentProposal = mobilePersonalAccidentProposalService.updateByPaymentStatus(order_id);
				if (mobilePersonalAccidentProposal.getProposalStatus().equals(ProposalStatus.ISSUED)) {
					Runnable paRun = () -> {
						try {
							mobilePersonalAccidentProposalService.postDataSyncProcess(order_id);
						} catch (UnsupportedEncodingException e1) {
							e1.printStackTrace();
						}
					};
					new Thread(paRun).start();
				}
				break;
			case "Medical":
				mobileMedicalProposal = mobileMedicalProposalService.updateByPaymentStatus(order_id);
				if (mobileMedicalProposal.getProposalStatus().equals(ProposalStatus.ISSUED)) {
					Runnable meRun = () -> {
						try {
							mobileMedicalProposalService.postDataSyncProcess(order_id);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					};
					new Thread(meRun).start();
				}
				break;
			case "SPECAILFOREIGNTRAVELLER":
				specailForeignTravel = specialForeignTravelService.updateByPaymentStatus(order_id, process_by);
				break;
			case "OUTBOUNDSPECAILFOREIGNTRAVELLER":
				specailForeignTravel = specialForeignTravelService.outboundUpdateByPaymentStatus(order_id, process_by);
				break;
			case "THIRDPARTYDRIVER":
				thirdPartyDriver = thirdPartyDriverService.updateByPaymentStatus(order_id);
				break;
			default:
				logger.info("Start update Online Biller Product");
				onlineBuyer = onlineBillerProposalService.updateByPaymentStatus(order_id);
				break;
			}
		}
	}

	@RequestMapping(value = "/paymentResult", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> paymentResponse(@RequestParam(name = "order_id") String order_id) {
		logger.info("Start PaymentResult method");
		AceResponse response = new AceResponse();
		if (order_id == null || order_id == "") {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessage("OrderId isn't empty");
			return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.BAD_REQUEST);
		}
		PaymentStatus ps = null;
		TwoCTwoPResponseDTO dto = twoC2pRecordsService.findByOrderId(order_id);
		dto.setPrintReceipt(true);
		ProductTypeRecords typeRecords = productTypeRecordsService.findByOrderId(order_id);
		switch (typeRecords.getProductType()) {
		case "TPL Online":
			ThirdPartyPremiumReceipt tpResult = thirdPartyPremiumReceiptSerivce.findRecordsByOrderId(order_id);
			ps = tpResult.getPaymentStatus();
			if (tpResult != null) {
				dto.setVehicleNo(tpResult.getVehicle_no());
			}
			break;
		case "Travel":
			MobileTravelProposal travelPropo = mobileTravelProposalService.findByOrderId(order_id).get(0);
			ps = travelPropo.getProposalStatus().equals(ProposalStatus.ISSUED) ? PaymentStatus.SUCCESS
					: PaymentStatus.PENDING;
			break;
		case "PA":
			MobilePersonalAccidentProposal pAPropo = mobilePersonalAccidentProposalService.findByOrderId(order_id)
					.get(0);
			ps = pAPropo.getProposalStatus().equals(ProposalStatus.ISSUED) ? PaymentStatus.SUCCESS
					: PaymentStatus.PENDING;
			break;
		case "Medical":
			MobileMedicalProposal medicalPropo = mobileMedicalProposalService.findByOrderId(order_id).get(0);
			ps = medicalPropo.getProposalStatus().equals(ProposalStatus.ISSUED) ? PaymentStatus.SUCCESS
					: PaymentStatus.PENDING;
			break;
		case "SPECAILFOREIGNTRAVELLER":
		case "OUTBOUNDSPECAILFOREIGNTRAVELLER":
			SpecialForeignTravel specialForeignTravel = specialForeignTravelService.findByOrderId(order_id).get(0);
			ps = specialForeignTravel.getProposalStatus().equals(ProposalStatus.ISSUED) ? PaymentStatus.SUCCESS
					: PaymentStatus.PENDING;
			dto.setPayment_channel("");//specialForeignTravel.getPaymentGateway().getLabel()
			dto.setId(specialForeignTravel.getId());
			break;
		case "THIRDPARTYDRIVER":
			ThirdPartyDriverProposal thirdPartyDriver = thirdPartyDriverService.findByOrderId(order_id).get(0);
			ps = thirdPartyDriver.getProposalStatus().equals(ProposalStatus.ISSUED) ? PaymentStatus.SUCCESS
					: PaymentStatus.PENDING;
			dto.setId(String.valueOf(thirdPartyDriver.getId()));
			break;
		default:
			OnlineBillerBuyer onlineBillerPropo = onlineBillerProposalService.findByOrderId(order_id);
			ps = onlineBillerPropo.getProposalStatus().equals(ProposalStatus.ISSUED) ? PaymentStatus.SUCCESS
					: PaymentStatus.PENDING;
			dto.setPrintReceipt(false);
			break;

		}
		if (dto != null && ps != null) {
			logger.info("twoc2precors and thirdpartyPremium is not null");
			if (ps == PaymentStatus.SUCCESS && dto.getChannel_response_code() != null && dto.getPayment_status() != null
					&& dto.getChannel_response_code().equals("00") && dto.getPayment_status().equals("000")) {
				logger.info("Payment Success!!!");
				dto.setRecordsState(PaymentStatus.SUCCESS.toString());
				response.setStatus(HttpStatus.ACCEPTED);
				response.setMessage("Payment Success!!!");
				response.setData(dto);
				return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.OK);
			} else if (ps == PaymentStatus.PENDING) {
				response.setStatus(HttpStatus.NOT_FOUND);
				response.setMessage("Payment process is pending!!!");
				dto.setRecordsState(PaymentStatus.PENDING.toString());
				response.setData(dto);
				return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.NOT_FOUND);
			}
			switch (dto.getPayment_status()) {
			case "001":
				response.setStatus(HttpStatus.PROCESSING);
				response.setMessage("Payment Pending!!!");
				response.setData(dto);
				break;
			case "002":
				response.setStatus(HttpStatus.UNAUTHORIZED);
				response.setMessage("Payment Rejected!!!");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			case "003":
				response.setStatus(HttpStatus.FORBIDDEN);
				response.setMessage("Payment was canceled by user!!!");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			case "999":
				response.setStatus(HttpStatus.NOT_ACCEPTABLE);
				response.setMessage("Payment Failed!!!");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			default:
				break;
			}
			response = checkChannelResponseCode(dto);
		} else {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessage("Payment Fail!!! Please contact to support team.");
		}
		logger.info("End paymentResponse method");
		return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.OK);
	}

	@RequestMapping(value = "/merchantKey", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> getSecretKey() {
		logger.info("start merchant key");
		AceResponse response = new AceResponse();
		String merchantKey = KeyFactorIDConfig.getTwoCTwoPSecKey();
		response.setStatus(HttpStatus.OK);
		response.setMessage("Success");
		logger.info(merchantKey);
		response.setData(merchantKey);
		return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.OK);
	}

	@RequestMapping(value = "/usdMerchantKey", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> getUSDSecretKey() {
		AceResponse response = new AceResponse();
		String merchantKey = KeyFactorIDConfig.getTwoCTwoPUSDSecKey();
		response.setStatus(HttpStatus.OK);
		response.setMessage("Success");
		response.setData(merchantKey);
		return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.OK);
	}

	@RequestMapping(value = "/outboundUsdMerchantKey", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> getOutboundUSDSecretKey() {
		AceResponse response = new AceResponse();
		String merchantKey = KeyFactorIDConfig.getTwoCTwoPOutboundUSDSecKey();
		response.setStatus(HttpStatus.OK);
		response.setMessage("Success");
		response.setData(merchantKey);
		return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.OK);
	}
	@RequestMapping(value = "/outboundMMKMerchantKey", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> getOutboundMMKSecretKey() {
		AceResponse response = new AceResponse();
		String merchantKey = KeyFactorIDConfig.getTwoCTwoPOutboundMMKSecKey();
		response.setStatus(HttpStatus.OK);
		response.setMessage("Success");
		response.setData(merchantKey);
		return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.OK);
	}
	
	private AceResponse checkChannelResponseCode(TwoCTwoPResponseDTO dto) {
		AceResponse response = new AceResponse();
		if (dto.getChannel_response_code() != null) {
			switch (dto.getChannel_response_code()) {
			case "9000":
				response.setStatus(HttpStatus.UNAUTHORIZED);
				response.setMessage("Payment Failed!!!");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			case "9009":
				response.setStatus(HttpStatus.UNAUTHORIZED);
				response.setMessage("Invalid amount!!!");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			case "9010":
				response.setStatus(HttpStatus.UNAUTHORIZED);
				response.setMessage("Invalid email format!!!");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			case "9022":
				response.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
				response.setMessage("Transaction has expired.!!!");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			case "59":
				response.setStatus(HttpStatus.UNAUTHORIZED);
				response.setMessage(
						"Suspected Fraud.So,please contact to 2C2P (supportmm@2c2p.com or +959 79 599 2227)");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			case "81":
				response.setStatus(HttpStatus.UNAUTHORIZED);
				response.setMessage(
						"Authentication Reject.So,please contact to 2C2P (supportmm@2c2p.com or +959 79 599 2227)");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			case "93":
				response.setStatus(HttpStatus.UNAUTHORIZED);
				response.setMessage(
						"Payment Expired.So,please contact to 2C2P (supportmm@2c2p.com or +959 79 599 2227)");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			case "99":
				response.setStatus(HttpStatus.UNAUTHORIZED);
				response.setMessage(
						"System Error/ Failed.So,please contact to 2C2P (supportmm@2c2p.com or +959 79 599 2227)");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			case "57":
				response.setStatus(HttpStatus.UNAUTHORIZED);
				response.setMessage("Function Not Permitted to Cardholder.So,please contact to bank.");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			case "54":
				response.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
				response.setMessage("Expired Card.So,please contact to bank.");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			case "51":
				response.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
				response.setMessage("Insufficient Funds.So,please contact to bank.");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			case "14":
				response.setStatus(HttpStatus.UNAUTHORIZED);
				response.setMessage("Invalid Card Number.");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			case "13":
				response.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
				response.setMessage("Invalid Amount.So,please contact to developer team.");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			case "12":
				response.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
				response.setMessage("Invalid Transaction.So,please contact to bank.");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			case "05":
				response.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
				response.setMessage("Do Not Honour.So,please contact to bank.");
				response.setData(new TwoCTwoPResponseDTO());
				break;
			}
		}

		return response;
	}
}
