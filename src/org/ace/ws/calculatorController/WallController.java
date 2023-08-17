package org.ace.ws.calculatorController;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.fire.wall.Wall;
import org.ace.insurance.system.fire.wall.service.interfaces.IWallService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.factory.FireCalculatorFactory;
import org.ace.ws.model.premiumCal.ContentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WallController extends BaseController {
	@Resource(name = "WallService")
	private IWallService wallService;

	@RequestMapping(value = URIConstants.WALL_LIST, method = RequestMethod.POST)
	private @ResponseBody String getWallList() {
		String response;
		List<ContentDTO> wallDTOList = new ArrayList<ContentDTO>();
		List<Wall> wallList = wallService.findAllWall();
		if (wallList != null && !wallList.isEmpty()) {
			wallDTOList = FireCalculatorFactory.convertWallDTOList(wallList);
		}
		response = responseManager.getResponseString(wallDTOList);
		return response;
	}
}
