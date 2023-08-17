package org.ace.ws.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.ace.insurance.system.appversion.AppVersion;
import org.ace.insurance.system.appversion.MobileType;
import org.ace.insurance.system.appversion.service.interfaces.IAppVersionService;
import org.ace.java.component.StatusType;
import org.ace.java.component.SystemException;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.apache.commons.collections4.map.HashedMap;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppVersionController extends BaseController {
	@Resource(name = "AppVersionService")
	private IAppVersionService appVersionService;

	@RequestMapping(value = URIConstants.GET_ANDROID_LATEST_VERSION, method = RequestMethod.POST)
	public @ResponseBody String getAndroidLatestVersion(@RequestHeader String key) {
		String response = null;
		try {
			if (key.toString().equals(Constants.getApikey())) {
				response = responseManager
						.getResponseString(appVersionService.findLatestAppVersion(MobileType.ANDROID));

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

	@RequestMapping(value = URIConstants.GET_IOS_LATEST_VERSION, method = RequestMethod.POST)
	public @ResponseBody String getIOSLatestVersion(@RequestHeader String key) {
		String response = null;
		try {
			if (key.toString().equals(Constants.getApikey())) {
				response = responseManager.getResponseString(appVersionService.findLatestAppVersion(MobileType.IOS));
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

	@RequestMapping(value = URIConstants.APPVERION, method = RequestMethod.GET)
	public @ResponseBody AceResponse getFindAllAppVersion(@RequestHeader String key) {
		AceResponse response = new AceResponse();
		try {
			if (key.toString().equals(Constants.getApikey())) {
				response.setStatus(HttpStatus.OK);
				response.setMessage("Success");
				List<AppVersion> appVersion = appVersionService.findAllAppVersion();
				if (appVersion.size() > 0) {
					Map<String, String> map = new HashedMap<>();
					map.put("andriodVersion", appVersion.get(0).getAppVersion());
					map.put("iosVersion", appVersion.get(1).getAppVersion());
					response.setData(map);
				}
			} else {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setMessage("Request not found!");
			}
		} catch (SystemException e) {
			e.printStackTrace();
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}

	@RequestMapping(value = URIConstants.UPDATE_BY_APP_VERSION, method = RequestMethod.POST)
	public @ResponseBody AceResponse upDateByAppVersion(@RequestHeader String key,
			@RequestParam(value = "type") String type, @RequestParam(value = "appVersion") String appVersion) {
		AceResponse response = new AceResponse();
		try {
			if (key.toString().equals(Constants.getApikey())) {
				if (type.equalsIgnoreCase("ANDROID")) {
					appVersionService.upDateByAppVersion(MobileType.ANDROID, appVersion);
					response.setStatus(HttpStatus.OK);
					response.setMessage("Success!");
					response.setData("-");
				} else if (type.equalsIgnoreCase("ISO")) {
					appVersionService.upDateByAppVersion(MobileType.IOS, appVersion);
					response.setStatus(HttpStatus.OK);
					response.setMessage("Success!");
					response.setData("");
				} else {
					response.setStatus(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
					response.setMessage("Not Found RequestParam(type)!");
					response.setData("-");
				}
			} else {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setMessage("Fail!");
			}
		} catch (SystemException e) {
			e.printStackTrace();
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}
}
