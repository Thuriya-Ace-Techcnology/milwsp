package org.ace.ws.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.ace.insurance.system.common.country.Country;
import org.ace.insurance.system.common.country.service.interfaces.ICountryService;
import org.ace.insurance.system.common.district.District;
import org.ace.insurance.system.common.district.service.interfaces.IDistrictService;
import org.ace.insurance.system.common.province.Province;
import org.ace.insurance.system.common.province.service.interfaces.IProvinceService;
import org.ace.insurance.system.common.township.Township;
import org.ace.insurance.system.common.township.service.interfaces.ITownshipService;
import org.ace.java.component.StatusType;
import org.ace.java.component.SystemException;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.factory.LocationFactory;
import org.ace.ws.factory.TownshipFactory;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.location.LocationDTO;
import org.ace.ws.model.location.TownshipDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LocationSetUpController extends BaseController {
	
	@Resource(name = "DistrictService")
	private IDistrictService districtService;

	@Resource(name = "TownshipService")
	private ITownshipService townshipService;

	@Resource(name = "ProvinceService")
	private IProvinceService provinceService;

	@Resource(name = "CountryService")
	private ICountryService countryService;
	
	private static final Logger logger = Logger.getLogger(LocationSetUpController.class);
	
	@RequestMapping(value = URIConstants.INSERT_TOWNSHIP, method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public @ResponseBody ResponseEntity<String> insertTownship(@RequestHeader String key,@RequestBody TownshipDTO townshipDTO )throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Insert Township .");
		AceResponse response = new AceResponse();
		try {
			if (key.toString().equals(Constants.getApikey()) && townshipDTO != null) {
				Township township = new Township();
				township = TownshipFactory.convertTownship(townshipDTO);
				townshipDTO = TownshipFactory.convertTownshipDTO(townshipService.addNewTownship(township));
				response.setStatus(HttpStatus.OK);
				response.setMessage(ResponseStatus.SUCCESS.getLabel());
				response.setData(townshipDTO);
				return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.OK);
			}
			response.setStatus(HttpStatus.NOT_MODIFIED);
			response.setMessage(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
		} catch (SystemException e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.NOT_MODIFIED);
			response.setMessage(ResponseStatus.FAIL.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Insert Township.");
		return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.NOT_MODIFIED);
	}



	@RequestMapping(value = URIConstants.GET_TOWNSHIP_LIST, method = RequestMethod.POST)
	public @ResponseBody String getTownshipList(@RequestHeader String key) throws ServiceException {
		String response = null;
		List<LocationDTO> townshipDTOList = new ArrayList<LocationDTO>();
		try {
			if (key.toString().equals(Constants.getApikey())) {
				List<Township> townshipList = townshipService.findAllTownship();
				if (townshipList != null && !townshipList.isEmpty()) {
					townshipDTOList = LocationFactory.convertTownshipDTOList(townshipList);
				}
				response = responseManager.getResponseString(townshipDTOList);
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(StatusType.SQL_Exception);
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_TOWNSHIP_LIST_BY_ID, method = RequestMethod.POST)
	public @ResponseBody String getTownshipList(@RequestHeader String key,@RequestBody Province province) throws ServiceException {
		String response = null;
		List<LocationDTO> townshipDTOList = new ArrayList<LocationDTO>();
		try {
			if (key.toString().equals(Constants.getApikey())) {
				List<Township> townshipList = townshipService.findTownshipByProvince(province);
				logger.info("TownShip List Size :"+townshipList.size());
				if (townshipList != null && !townshipList.isEmpty()) {
					townshipDTOList = LocationFactory.convertTownshipDTOList(townshipList);
				}
				response = responseManager.getResponseString(townshipDTOList);
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(StatusType.SQL_Exception);
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}
	
	@RequestMapping(value = URIConstants.GET_PROVINCE_LIST, method = RequestMethod.POST)
	public @ResponseBody String getProvinceList(@RequestHeader String key) throws ServiceException {
		String response = null;
		List<LocationDTO> provinceDTOList = new ArrayList<LocationDTO>();
		try {
			if (key.toString().equals(Constants.getApikey())) {
				List<Province> provinceList = provinceService.findAllProvince();
				if (provinceList != null && !provinceList.isEmpty()) {
					provinceDTOList = LocationFactory.convertProvinceDTOList(provinceList);
				}
				response = responseManager.getResponseString(provinceDTOList);
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(StatusType.SQL_Exception);
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}

	@RequestMapping(value = URIConstants.GET_COUNTRY_LIST, method = RequestMethod.POST)
	public @ResponseBody String getCountryList(@RequestHeader String key) throws ServiceException {
		String response = null;
		List<LocationDTO> countryDTOList = new ArrayList<LocationDTO>();
		try {
			if (key.toString().equals(Constants.getApikey())) {
				List<Country> countryList = countryService.findAllCountry();
				if (countryList != null && !countryList.isEmpty()) {
					countryDTOList = LocationFactory.convertCountryDTOList(countryList);
				}
				response = responseManager.getResponseString(countryDTOList);
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(StatusType.SQL_Exception);
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}
	
	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_DISTRICT_LIST_BY_ID, method = RequestMethod.POST)
	public @ResponseBody String getDistrictListByProvinence(@RequestHeader String key,@RequestBody Province province) throws ServiceException {
		String response = null;
		List<LocationDTO> districtDTOList = new ArrayList<LocationDTO>();
		try {
			if (key.toString().equals(Constants.getApikey())) {
				List<District> districtList = districtService.findDistrictByProvince(province);
				if (districtList != null && !districtList.isEmpty()) {
					districtDTOList = LocationFactory.convertDistrictDTOList(districtList);
				}
				response = responseManager.getResponseString(districtDTOList);
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(StatusType.SQL_Exception);
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}
}
