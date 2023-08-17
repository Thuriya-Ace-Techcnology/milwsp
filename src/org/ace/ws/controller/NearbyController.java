package org.ace.ws.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.ace.insurance.nearby.service.interfaces.INearByService;
import org.ace.insurance.system.common.branch.Branch;
import org.ace.java.component.StatusType;
import org.ace.java.component.SystemException;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.Constants;
import org.ace.ws.model.nearby.NearByDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NearbyController extends BaseController {

	@Resource(name = "NearbyService")
	private INearByService nearByService;

	private static final Logger logger = Logger.getLogger(NearbyController.class);

	@RequestMapping(value = URIConstants.POST_NEARBY_BRANCH, method = RequestMethod.POST)
	private @ResponseBody String findNearBranchByLatLong(@RequestHeader String key, @RequestBody NearByDTO nearByDTO) throws ServiceException {
		logger.info("Start do findNearBranchByLatLong.");
		AceResponse aceResponse = new AceResponse();
		try {
			if (key.toString().equals(Constants.getApikey())) {
				if (nearByDTO.getRange() == 0.0) {
					aceResponse.setStatus(HttpStatus.BAD_REQUEST);
					aceResponse.setMessage("Require Range Value");
					return responseManager.getResponseString(aceResponse);
				}
				List<Branch> branchList = nearByService.findNearByBranch(nearByDTO);
				if (branchList.isEmpty()) {
					aceResponse.setStatus(HttpStatus.BAD_REQUEST);
					aceResponse.setMessage("Not valid in range.");
				} else {
					aceResponse.setStatus(HttpStatus.OK);
					aceResponse.setMessage("Success");
					aceResponse.setData(branchList);
				}
			} else {
				aceResponse.setStatus(HttpStatus.BAD_REQUEST);
				aceResponse.setMessage("Key is wrong.");
			}
			
		} catch (SystemException e) {
			e.printStackTrace();
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End findNearBranchByLatLong.");
		return responseManager.getResponseString(aceResponse);
	}
}
