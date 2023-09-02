package org.ace.ws.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.common.DateUtils;
import org.ace.insurance.common.TourismType;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.system.productTypeRecords.service.interfaces.IProductTypeRecordsService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.TwoCTwoPDTO.OnlineProductRecordListDTO;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirmDTO;
import org.ace.ws.model.mobilePersonalAccidentproposal.MPAP001;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class ProductTypeController extends BaseController {

	@Resource(name = "ProductTypeRecordsService")
	private IProductTypeRecordsService productTypeRecordsService;

	/*
	 * @Resource(name = "MobilePersonalAccidentProposalService") private
	 * IMobilePersonalAccidentProposalService mobilePersonalAccidentProposalService;
	 */


	/*
	 * @Resource(name = "ThirdPartyPremiumReceiptService") private
	 * IThirdPartyPremiumReceiptSerivce thirdPartyPremiumReceiptSerivce;
	 */

	/*
	 * @Resource(name = "MobileMedicalProposalService") private
	 * IMobileMedicalProposalService mobileMedicalProposalService;
	 */



	AceResponse aceResponse = new AceResponse();
	private RestTemplate restTemplate = new RestTemplate();

	@CrossOrigin
	@GetMapping(value = URIConstants.GET_MI_THIRDPARTY_PREMIUM_BUYERS, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getAllProductRecordsByDate(@RequestParam(name = "fromDate") String fromDate,
			@RequestParam(name = "toDate") String toDate, @RequestParam(name = "branch") String branch,
			@RequestParam(name = "productType") String productType,
			@RequestParam(name = "convert", required = false) String convert) {
		if (!DateUtils.isValidFormat("yyyy-MM-dd", fromDate) || !DateUtils.isValidFormat("yyyy-MM-dd", toDate)) {
			return new ResponseEntity<>("Date format wrong.(eg,'2020-01-30').", HttpStatus.BAD_REQUEST);
		}
		List<OnlineProductRecordListDTO> productRecordDTO = new ArrayList<>();
		
		
		List<MPAP001> personalAccidenDTO = null;
		/* List<MedicalProposalDTO> medicalProposalDTO = null; */
		
		switch (productType) {
		/*
		 * case "TPL Online": premiumDTO =
		 * thirdPartyPremiumReceiptSerivce.findByFromToDate(fromDate, toDate, branch,
		 * convert); break; 
		
		
		 * case "PA": personalAccidenDTO =
		 * mobilePersonalAccidentProposalService.fndByFromToDate(fromDate, toDate,
		 * convert); break;
		 */
		/*
		 * case "Health": medicalProposalDTO =
		 * mobileMedicalProposalService.fndByFromToDate(fromDate, toDate, convert);
		 * break;
		 */
		case "THIRDPARTYDRIVER":
			return getThirdPartyDriverBetweenDateRange(fromDate, toDate);
		default:
			/*
			 * premiumDTO = thirdPartyPremiumReceiptSerivce.findByFromToDate(fromDate,
			 * toDate, branch, convert);
			 */
			/*
			 * personalAccidenDTO =
			 * mobilePersonalAccidentProposalService.fndByFromToDate(fromDate, toDate,
			 * convert);
			 */
			/*
			 * medicalProposalDTO = mobileMedicalProposalService.fndByFromToDate(fromDate,
			 * toDate, convert);
			 */
			break;
		}
		/*
		 * productRecordDTO = productTypeRecordsService.collectAllProduct(premiumDTO,
		 * travelDTO, personalAccidenDTO, specialForeignTravellerDTO,
		 * thirdPartyDriverDTO);
		 */
		aceResponse.setStatus(HttpStatus.OK);
		aceResponse.setMessage("Success");
		aceResponse.setData(productRecordDTO);
		return new ResponseEntity<>(responseManager.getResponseString(aceResponse), HttpStatus.OK);
	}

	private ResponseEntity<String> getThirdPartyDriverBetweenDateRange(String fromDate, String toDate) {
		
		ResponseEntity<AceResponse> result = restTemplate.getForEntity(
				URIConstants.SERVER_PATH+URIConstants.GET_THIRD_PARTY_DRIVER_BETWEEN_DATE_RANGE_CALLER+"?fromDate={fromDate}&toDate={toDate}"
				,AceResponse.class, fromDate, toDate);

		return new ResponseEntity<>(responseManager.getResponseString(result.getBody()), HttpStatus.OK);
		
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.POST_CONFIRM_PAYMENT_BY_2C2P, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> submitPaymentConfirm(@RequestBody PaymentOrderConfirmDTO paymentOrderConfirm) {
		AceResponse response = new AceResponse();
		ProductTypeRecords typeRecords = productTypeRecordsService
				.findByOrderId(paymentOrderConfirm.getPaymentOrder().get(0).getOrderId());
		switch (typeRecords.getProductType()) {
		/*
		 * case "TPL Online":
		 * thirdPartyPremiumReceiptSerivce.updateCovertedStatusByOderId(
		 * paymentOrderConfirm); break;
		 * case "PA":
		 * mobilePersonalAccidentProposalService.updateCovertedStatusByOderId(
		 * paymentOrderConfirm); break;
		 */
		/*
		 * case "Medical":
		 * mobileMedicalProposalService.updateCovertedStatusByOderId(paymentOrderConfirm
		 * ); break;
		 */
		default:
			break;
		}
		response.setStatus(HttpStatus.OK);
		response.setMessage("Successfully inserted!!!");
		return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.OK);
	}
}
