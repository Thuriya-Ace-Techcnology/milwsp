package org.ace.ws.calculatorController;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.fire.buildingOccupation.BuildingOccupation;
import org.ace.insurance.system.fire.buildingOccupation.service.interfaces.IBuildingOccupationService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.factory.FireCalculatorFactory;
import org.ace.ws.model.premiumCal.ContentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BuildingOccupationController extends BaseController {
	@Resource(name = "BuildingOccupationService")
	private IBuildingOccupationService bOccuService;

	@RequestMapping(value = URIConstants.BUILDING_OCCUPATION_LIST, method = RequestMethod.POST)
	private @ResponseBody String getBuildingOccupationList() {
		String response;
		List<ContentDTO> bOccuDTOList = new ArrayList<ContentDTO>();
		List<BuildingOccupation> bOccuList = bOccuService.findAllBuildingOccupation();
		if (bOccuList != null && !bOccuList.isEmpty()) {
			bOccuDTOList = FireCalculatorFactory.convertBuildingOccupationDTOList(bOccuList);
		}
		response = responseManager.getResponseString(bOccuDTOList);
		return response;
	}
}
