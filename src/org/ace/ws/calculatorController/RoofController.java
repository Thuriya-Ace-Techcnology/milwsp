package org.ace.ws.calculatorController;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.fire.roof.Roof;
import org.ace.insurance.system.fire.roof.service.interfaces.IRoofService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.factory.FireCalculatorFactory;
import org.ace.ws.model.premiumCal.ContentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoofController extends BaseController {
	@Resource(name = "RoofService")
	private IRoofService roofService;

	@RequestMapping(value = URIConstants.ROOF_LIST, method = RequestMethod.POST)
	private @ResponseBody String getRoofList() {
		String response;
		List<ContentDTO> roofDTOList = new ArrayList<ContentDTO>();
		List<Roof> roofList = roofService.findAllRoof();
		if (roofList != null && !roofList.isEmpty()) {
			roofDTOList = FireCalculatorFactory.convertRoofDTOList(roofList);
		}
		response = responseManager.getResponseString(roofDTOList);
		return response;
	}
}
