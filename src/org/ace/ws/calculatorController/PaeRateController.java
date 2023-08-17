package org.ace.ws.calculatorController;

import javax.annotation.Resource;

import org.ace.insurance.system.fire.paeRate.service.interfaces.IPaeRateService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.premiumCal.PAE001;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaeRateController extends BaseController {
	@Resource(name = "PaeRateService")
	private IPaeRateService paeRateService;

	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_PAERATE_BY_CLASS_AGE, method = RequestMethod.POST)
	private @ResponseBody String getPAERate(@RequestBody PAE001 pae001) {
		double paeRate = paeRateService.findPAERateByClassAndAge(pae001.getBuildingClassId(), pae001.getAge());
		String response = responseManager.getResponseString(paeRate);
		return response;
	}

}
