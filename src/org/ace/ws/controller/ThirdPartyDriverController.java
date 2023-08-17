package org.ace.ws.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

import org.ace.insurance.system.productTypeRecords.service.interfaces.IProductTypeRecordsService;
import org.ace.insurance.thirdPartyDriverLicense.service.interfaces.IThirdPartyDriverService;
import org.ace.insurance.thirdPartyDriverLicense.service.interfaces.ITypeOfDriverService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.AceResponse;
import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import net.sf.jasperreports.engine.JRException;

@Controller
public class ThirdPartyDriverController extends BaseController {

	private static final Logger logger = Logger.getLogger(ThirdPartyDriverController.class);

	@Resource(name = "ThirdPartyDriverService")
	private IThirdPartyDriverService thirdPartyDriverService;

	@Resource(name = "TypeOfDriverService")
	private ITypeOfDriverService typeOfDriverService;

	@Resource(name = "ProductTypeRecordsService")
	private IProductTypeRecordsService productTypeRecordsService;

	AceResponse aceResponse;
	
	private RestTemplate restTemplate = new RestTemplate();

	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_THIRD_PARTY_DRIVER_PREMIUM, method = RequestMethod.GET)
	@ResponseBody
	private String getPremium(@QueryParam("typeOfDriverId") Long typeOfDriverId) {
		double premium = thirdPartyDriverService.calculatePremium(typeOfDriverId);
		PremiumResponse response = new PremiumResponse();
		response.setPremium(premium);
		return responseManager.getResponseString(response);
	}
	
	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_TYPE_OF_DRIVER, method = RequestMethod.GET)
	@ResponseBody
	public String getAllTypeOfDriver(){
		ResponseEntity<AceResponse> result = restTemplate.getForEntity(URIConstants.SERVER_PATH+URIConstants.GET_TYPE_OF_DRIVER_CALLER,AceResponse.class);
		return responseManager.getResponseString(result.getBody());
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.THIRDPARTY_DRIVER, method = RequestMethod.POST)
	public @ResponseBody String buyThirdPartyDriver(@RequestHeader String key, @RequestBody String dto)
			throws IOException, ServiceException, ParseException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("key", key);
		HttpEntity<String> request = new HttpEntity<>(dto,headers);
		ResponseEntity<AceResponse> result =  restTemplate.postForEntity(URIConstants.SERVER_PATH+URIConstants.THIRDPARTY_DRIVER_CALLER, request, AceResponse.class);
		return responseManager.getResponseString(result.getBody());
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_THIRD_PARTY_DRIVER, method = RequestMethod.GET)
	public @ResponseBody String getProposalbyReferenceNo(@QueryParam("driverCodeNo") String driverCodeNo)
			throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Search Third Party Driver Proposal By driverCodeNo.");
		ResponseEntity<AceResponse> result = restTemplate.getForEntity(URIConstants.SERVER_PATH+URIConstants.GET_THIRD_PARTY_DRIVER_CALLER+"?driverCodeNo={driverCodeNo}",AceResponse.class,driverCodeNo);
		return responseManager.getResponseString(result.getBody());
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_THIRD_PARTY_DRIVER_RECEIPT, method = RequestMethod.GET)
	@ResponseBody
	public byte[] printReceipt(HttpServletResponse response, @RequestParam(name = "id") Long id)
			throws IOException, JRException {
		logger.info("Start Print Receipt function");
		ResponseEntity<byte[]> result = restTemplate.getForEntity(URIConstants.SERVER_PATH+URIConstants.GET_THIRD_PARTY_DRIVER_RECEIPT_CALLER+"?id={id}",byte[].class,id);
		response.setHeader("Content-Disposition", "inline; filename=myanma_insurnace.pdf");
		return result.getBody();
	}

}

class PremiumResponse {
	private double premium;

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}
}
