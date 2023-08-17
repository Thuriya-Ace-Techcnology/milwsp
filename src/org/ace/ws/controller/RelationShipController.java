package org.ace.ws.controller;

import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.common.relationship.service.interfaces.IRelationShipService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.RelationShip.RelationShipDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
public class RelationShipController extends BaseController{
	
	@Resource(name = "RelationShipService")
	private IRelationShipService relationShipService;
	
	private static final Logger logger = Logger.getLogger(RelationShipController.class);
	
	@GetMapping(value = URIConstants.GET_ALLRELATIONSHIP)
	private  @ResponseBody String getAllRelationShip() {
		logger.info("Start Select RelationShip Data.");
		AceResponse aceResponse = new AceResponse();
		List<RelationShipDTO> result = relationShipService.findAllRelationShip();
		if(!result.isEmpty() && result != null ) {
			aceResponse.setData(result);
			aceResponse.setMessage("Success");
			aceResponse.setStatus(HttpStatus.OK);
		}else {
			aceResponse.setMessage("Empty Data");
			aceResponse.setStatus(HttpStatus.NOT_FOUND);
		}
		logger.info("End ISelect RelationShip Data.");
		return responseManager.getResponseString(aceResponse);
	}
}
