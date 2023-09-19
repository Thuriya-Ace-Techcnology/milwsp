package org.ace.ws.controller;

import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.common.plans.services.interfaces.IPlansService;
import org.ace.insurance.life.dto.PlansDTO;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.AceResponse;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
public class PlansController extends BaseController {
	
	@Resource(name = "PlansService")
	private IPlansService plansService;
	
	private static final Logger logger = Logger.getLogger(PlansController.class);
	
	@GetMapping(value = URIConstants.GET_ALLPLANS)
	private  @ResponseBody String getAllPlans() {
		logger.info("Start Select Plans Data.");
		AceResponse aceResponse = new AceResponse();
		List<PlansDTO> result = plansService.getPlanList();
		if(!result.isEmpty() && result != null ) {
			aceResponse.setData(result);
			aceResponse.setMessage("Success");
			aceResponse.setStatus(HttpStatus.OK);
		}else {
			aceResponse.setMessage("Empty Data");
			aceResponse.setStatus(HttpStatus.NOT_FOUND);
		}
		logger.info("End ISelect Plans Data.");
		return responseManager.getResponseString(aceResponse);
	}

}
