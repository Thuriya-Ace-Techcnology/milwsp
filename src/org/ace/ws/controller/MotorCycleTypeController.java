package org.ace.ws.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.common.motorCycleType.MotorCycleType;
import org.ace.insurance.system.common.motorCycleType.service.interfaces.IMotorCycleTypeService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MotorCycleTypeController extends BaseController{
	
	@Resource(name="MotorCycleTypeService")
	private IMotorCycleTypeService service;
	@RequestMapping(value = URIConstants.GET_MOTORCYCLE_TYPE, method = RequestMethod.GET)
	@ResponseBody
	public String getAllDriverType() {
		String response = null;
		List<MotorCycleType> receipt = new ArrayList<>();
		receipt.addAll(service.findAllMotorCycleType());
		response = responseManager.getResponseString(receipt);
		return response;
	}
}
