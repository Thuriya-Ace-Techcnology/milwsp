package org.ace.ws.calculatorController;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.fire.floor.Floor;
import org.ace.insurance.system.fire.floor.service.interfaces.IFloorService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.factory.FireCalculatorFactory;
import org.ace.ws.model.premiumCal.ContentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FloorController extends BaseController {

	@Resource(name = "FloorService")
	private IFloorService floorService;

	@RequestMapping(value = URIConstants.FLOOR_LIST, method = RequestMethod.POST)
	private @ResponseBody String getFloorList() {
		String response;
		List<ContentDTO> floorDTOList = new ArrayList<ContentDTO>();
		List<Floor> floorList = floorService.findAllFloor();
		if (floorList != null && !floorList.isEmpty()) {
			floorDTOList = FireCalculatorFactory.convertFloorDTOList(floorList);
		}
		response = responseManager.getResponseString(floorDTOList);
		return response;
	}
}
