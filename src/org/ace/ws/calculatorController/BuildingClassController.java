package org.ace.ws.calculatorController;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.fire.buildingclass.BuildingClass;
import org.ace.insurance.system.fire.buildingclass.service.interfaces.IBuildingClassService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.factory.FireCalculatorFactory;
import org.ace.ws.model.premiumCal.BuildingClassCriteriaDTO;
import org.ace.ws.model.premiumCal.ContentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BuildingClassController extends BaseController {
	@Resource(name = "BuildingClassService")
	private IBuildingClassService bClassService;

	@CrossOrigin
	@RequestMapping(value = URIConstants.BUILDING_CLASS_LIST, method = RequestMethod.POST)
	private @ResponseBody String getBuildingClassList() {
		String response;
		List<ContentDTO> bclassDTOList = new ArrayList<ContentDTO>();
		List<BuildingClass> bclassList = bClassService.findAllBuildingClass();
		if (bclassList != null && !bclassList.isEmpty()) {
			bclassDTOList = FireCalculatorFactory.convertBuildingClassDTOList(bclassList);
		}
		response = responseManager.getResponseString(bclassDTOList);
		return response;
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.BUILDING_CLASS_LIST_BY_ROOFWALLFLOOR, method = RequestMethod.POST)
	private @ResponseBody String getBuildingClassListByRoofWallFloor(@RequestBody BuildingClassCriteriaDTO criteria) {
		String response;
		ContentDTO buildingClassDTO = new ContentDTO();
		BuildingClass buildingClass = bClassService.findBuildingClassByRoofWallFloor(criteria.getRoofId(), criteria.getWallId(), criteria.getFloorId());
		if (buildingClass != null) {
			buildingClassDTO = FireCalculatorFactory.convertBuildingClassDTO(buildingClass);
		}
		response = responseManager.getResponseString(buildingClassDTO);
		return response;
	}
}
