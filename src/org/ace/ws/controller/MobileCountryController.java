package org.ace.ws.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.ace.insurance.moibleCountry.service.interfaces.IMobileCountryService;
import org.ace.java.component.StatusType;
import org.ace.java.component.SystemException;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.Constants;
import org.ace.ws.model.MobileCountry;
import org.ace.ws.model.ResponseStatus;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MobileCountryController extends BaseController{
	private static final Logger logger = Logger.getLogger(MobileCountryController.class);
	
	@Resource(name = "MobileCountryService")
	private IMobileCountryService mobileCountrySerice;
	
	@RequestMapping(value = URIConstants.GET_MOBILE_COUNTRY_LIST, method = RequestMethod.GET)
	public @ResponseBody String getMobileCountryList(@RequestHeader String key) throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Select Mobile Country List.");
		AceResponse aceResponse = new AceResponse();
		try {
			if (!key.toString().equals(Constants.getApikey())) {
				aceResponse.setMessage(ResponseStatus.INVALIDUSER.getLabel());
				return responseManager.getResponseString(aceResponse);
			}
			List<MobileCountry> mobileCountry = mobileCountrySerice.findAll();
			if (mobileCountry != null) {
				aceResponse.setCode(200);
				aceResponse.setMessage("Success");
				aceResponse.setStatus(HttpStatus.OK);
				aceResponse.setData(mobileCountry);
			} else {
				aceResponse.setCode(200);
				aceResponse.setMessage("Success");
				aceResponse.setStatus(HttpStatus.OK);
				aceResponse.setData("{\"responseStatus\":\"EMPTY\"}");
			}
		} catch (SystemException e) {
			e.printStackTrace();
			aceResponse.setData(ResponseStatus.INTERNAL_SERVER_ERROR.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Moible Country.");
		return responseManager.getResponseString(aceResponse);
	}
	@RequestMapping(value = URIConstants.GET_OUTBOUND_MOBILE_COUNTRY_LIST, method = RequestMethod.GET)
	public @ResponseBody String getOutboundMobileCountryList(@RequestHeader String key) throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Select Mobile Country List.");
		AceResponse aceResponse = new AceResponse();
		try {
			if (!key.toString().equals(Constants.getApikey())) {
				aceResponse.setMessage(ResponseStatus.INVALIDUSER.getLabel());
				return responseManager.getResponseString(aceResponse);
			}
			List<MobileCountry> mobileCountry = mobileCountrySerice.findAll();
			mobileCountry.removeIf(mc -> mc.getShortTwoCode().equals("USA"));
			mobileCountry.removeIf(mc -> mc.getShortTwoCode().equals("CAN"));
			if (mobileCountry != null) {
				aceResponse.setCode(200);
				aceResponse.setMessage("Success");
				aceResponse.setStatus(HttpStatus.OK);
				aceResponse.setData(mobileCountry);
			} else {
				aceResponse.setCode(200);
				aceResponse.setMessage("Success");
				aceResponse.setStatus(HttpStatus.OK);
				aceResponse.setData("{\"responseStatus\":\"EMPTY\"}");
			}
		} catch (SystemException e) {
			e.printStackTrace();
			aceResponse.setData(ResponseStatus.INTERNAL_SERVER_ERROR.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Moible Country.");
		return responseManager.getResponseString(aceResponse);
	}
	
}
