package org.ace.ws.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.ace.insurance.system.common.occupation.Occupation;
import org.ace.insurance.system.common.occupation.service.interfaces.IOccupationService;
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
public class OccupationController extends BaseController {

	@Resource(name = "OccupationService")
	private IOccupationService occupationService;

	@RequestMapping(value = URIConstants.GET_OCCUPATION_LIST, method = RequestMethod.POST)
	public @ResponseBody String getOccupationList(@RequestHeader String key) throws ServiceException {
		{

			String response = null;
			try {
				if (key.toString().equals(Constants.getApikey())) {
					List<Occupation> occupationList = occupationService.findAllOccupation();
					response = responseManager.getResponseString(occupationList);
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

}
