package org.ace.ws.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.ace.insurance.system.common.securityQuestion.SecurityQuestion;
import org.ace.insurance.system.common.securityQuestion.service.interfaces.ISecurityQuestionService;
import org.ace.java.component.StatusType;
import org.ace.java.component.SystemException;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityQuestionController extends BaseController {
	private static final Logger logger = Logger.getLogger(SecurityQuestionController.class);

	@Resource(name = "SecurityQuestionService")
	private ISecurityQuestionService securityQuestionService;

	@RequestMapping(value = URIConstants.GET_SECURITYQUESTION_LIST, method = RequestMethod.POST)
	public @ResponseBody String getSecurityQuestionList(@RequestHeader String key) throws ServiceException {
		logger.info("Start Get SecurityQuestion List.");
		String response = null;
		try {
			if (key != null && key.toString().equals(Constants.getApikey())) {
				List<SecurityQuestion> securityQList = securityQuestionService.findAllSecurityQuestionList();
				response = responseManager.getResponseString(securityQList);
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM);
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.FAIL.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Get SecurityQuestion List.");
		return response;
	}

}
