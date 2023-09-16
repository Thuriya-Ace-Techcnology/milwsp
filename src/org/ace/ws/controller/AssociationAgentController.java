package org.ace.ws.controller;

import javax.annotation.Resource;
import javax.ws.rs.QueryParam;
import org.ace.insurance.agent.OutboundAssociationAgent;
import org.ace.insurance.system.agent.service.interfaces.IAssociationAgentService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.Constants;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
public class AssociationAgentController extends BaseController {

	private static final Logger logger = Logger.getLogger(AssociationAgentController.class);

	@Resource(name = "AssociationAgentService")
	private IAssociationAgentService associationAgentService;
	
	AceResponse aceResponse = new AceResponse();
	
	@RequestMapping(value = URIConstants.AUTHORIZE_AGENT_CHECK, method = RequestMethod.POST)
	public @ResponseBody String postOutboundAgentCheck(@RequestHeader String key,
			@QueryParam("name") String name, @QueryParam("password") String password) {
		logger.info("Start Authorize Agent Find By Name and Password.");
		if (key.toString().equals(Constants.getApikey())) {
			OutboundAssociationAgent info = associationAgentService.checkAuthorizeAgent(name,password);
			logger.info("End Authorize Agent Find By Name and Password.");
			aceResponse.setMessage("Success");
			aceResponse.setStatus(HttpStatus.OK);
			aceResponse.setData(info == null? "Name and Password does not match!!" : info);
			return responseManager.getResponseString(aceResponse);
		}else {
			aceResponse.setMessage("Empty Data");
			aceResponse.setStatus(HttpStatus.NOT_FOUND);
		}
		logger.info("End find Authorize Agent Find By Name and Password.");
		return responseManager.getResponseString(aceResponse);

	}
}
