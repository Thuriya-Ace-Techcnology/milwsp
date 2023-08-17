package org.ace.ws.controller;

import javax.annotation.Resource;

import org.ace.insurance.common.OutboundAgentInfo;
import org.ace.insurance.system.agent.service.interfaces.IAgentService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AgentController extends BaseController {

	private static final Logger logger = Logger.getLogger(AgentController.class);

	@Resource(name = "AgentService")
	private IAgentService agentService;

	AceResponse aceResponse = new AceResponse();

	@CrossOrigin
	@RequestMapping(value = URIConstants.POST_OUTBOUND_AGENT_CHECK, method = RequestMethod.POST)
	public @ResponseBody String postOutboundAgentCheck(@RequestHeader String key,
			@RequestBody OutboundAgentInfo oinfo) {
		logger.info("Start Agent Find By LiscenceNo and Password.");
		if (key.toString().equals(Constants.getApikey())) {
			OutboundAgentInfo info = agentService.findAgentByLiscenceNoAndDateOfBirth(oinfo.getAgentCode(),
					oinfo.getPassword());
			logger.info("End Agent Find By LiscenceNo and Password.");
			aceResponse.setMessage("Success");
			aceResponse.setStatus(HttpStatus.OK);
			aceResponse.setData(info == null? "Something Wrong" : info);
			return responseManager.getResponseString(aceResponse);
		} else {
			return ResponseStatus.INVALIDUSER.getLabel();
		}

	}
}
