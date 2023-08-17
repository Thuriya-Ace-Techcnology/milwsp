package org.ace.ws.calculatorController;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.cargo.route.Route;
import org.ace.insurance.system.cargo.route.service.interfaces.IRouteService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.factory.FireCalculatorFactory;
import org.ace.ws.model.premiumCal.ContentDTO;
import org.ace.ws.model.premiumCal.RouteDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RouteController extends BaseController {
	@Resource(name = "RouteService")
	private IRouteService routeService;

	@RequestMapping(value = URIConstants.ROUTE_LIST, method = RequestMethod.POST)
	private @ResponseBody String getRouteList() {
		String response;
		List<ContentDTO> routeDTOList = new ArrayList<ContentDTO>();
		List<Route> routeList = routeService.findAllRoute();
		if (routeList != null && !routeList.isEmpty()) {
			routeDTOList = FireCalculatorFactory.convertRouteDTOList(routeList);
		}
		response = responseManager.getResponseString(routeDTOList);
		return response;
	}
	
	@CrossOrigin
	@RequestMapping(value = URIConstants.ROUTE_BY_INSURANCETYPE_LIST, method = RequestMethod.POST)
	private @ResponseBody String getRouteListByInsuranceType(@RequestBody RouteDTO routeDTO) {
		String response;
		List<RouteDTO> routeDTOList = new ArrayList<RouteDTO>();
		List<Route> routeList = routeService.findRouteByInsuranceType(routeDTO.getInsuranceType());
		if (routeList != null && !routeList.isEmpty()) {
			routeDTOList = FireCalculatorFactory.convertRouteWithInsuranceTypeDTOList(routeList);
		}
		response = responseManager.getResponseString(routeDTOList);
		return response;
	}
}
