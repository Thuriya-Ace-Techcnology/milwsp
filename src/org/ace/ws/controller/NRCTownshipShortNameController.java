package org.ace.ws.controller;

import java.util.List;
import javax.xml.rpc.ServiceException;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.AceResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import org.ace.insurance.system.common.township.service.interfaces.ITownshipService;
import org.ace.java.component.StatusType;
import org.ace.java.component.SystemException;
import org.ace.ws.model.ResponseStatus;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

@CrossOrigin
@Controller
public class NRCTownshipShortNameController extends BaseController{
	
	@Resource(name = "TownshipService")
	private ITownshipService townshipService;
	
	private static final Logger logger = Logger.getLogger(NRCTownshipShortNameController.class);
	
	@RequestMapping(value = URIConstants.GET_TSP_SHORTNAME_LIST_BY_PROVINCE, method = RequestMethod.POST)
	public @ResponseBody String getTspShortNameByProvinceNo(@RequestHeader String key,
			@RequestParam("provinceNo") String provinceNo) throws ServiceException {
		AceResponse aceResponse = new AceResponse();
		logger.info("Start Select Township Short Name by provinceNO .");
		AceResponse response = new AceResponse();
		try {
				List<String> shortNameList = townshipService.findTspShortNameENGByProvinceNo(provinceNo);
				if (shortNameList != null && !shortNameList.isEmpty()) {
					aceResponse.setData(shortNameList);
					aceResponse.setMessage("Success");
					aceResponse.setStatus(HttpStatus.OK);
				}else {
					aceResponse.setMessage("Empty Data");
					aceResponse.setStatus(HttpStatus.NOT_FOUND);
				}
				logger.info("End ISelect Township Short Name.");
		}catch (SystemException e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.NOT_MODIFIED);
			response.setMessage(ResponseStatus.FAIL.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return responseManager.getResponseString(aceResponse);
	}

}
