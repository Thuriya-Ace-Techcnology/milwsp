package org.ace.ws.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.ace.insurance.system.common.branch.Branch;
import org.ace.insurance.system.common.branch.service.interfaces.IBranchService;
import org.ace.java.component.StatusType;
import org.ace.java.component.SystemException;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BranchController extends BaseController {
	@Resource(name = "BranchService")
	private IBranchService branchService;

	@RequestMapping(value = URIConstants.GET_BRANCH_LIST, method = RequestMethod.POST)
	public @ResponseBody String getBranchList(@RequestHeader String key) throws ServiceException {
		String response = null;
		try {
			if (key.toString().equals(Constants.getApikey())) {
				List<Branch> branchList = branchService.findAllBranch();
				response = responseManager.getResponseString(branchList);
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
