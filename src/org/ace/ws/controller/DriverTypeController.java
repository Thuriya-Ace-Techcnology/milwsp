package org.ace.ws.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.common.driverType.DriverType;
import org.ace.insurance.system.common.driverType.service.interfaces.IDriverTypeService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DriverTypeController  extends BaseController{
	
	@Resource(name="DriverTypeService")
	private IDriverTypeService service;
	@RequestMapping(value = URIConstants.GET_DRIVER_TYPE, method = RequestMethod.GET)
	@ResponseBody
	public String getAllDriverType() {
		String response = null;
		List<DriverType> receipt = new ArrayList<>();
		receipt.addAll(service.findAllDriverType());
		response = responseManager.getResponseString(receipt);
		return response;
	}
}
