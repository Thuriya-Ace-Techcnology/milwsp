package org.ace.ws.calculatorController;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.cargo.cargoType.CargoType;
import org.ace.insurance.system.cargo.cargoType.service.interfaces.ICargoTypeService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.factory.FireCalculatorFactory;
import org.ace.ws.model.premiumCal.CargoTypeDTO;
import org.ace.ws.model.premiumCal.ContentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CargoTypeController extends BaseController {
	@Resource(name = "CargoTypeService")
	private ICargoTypeService cargoTypeService;

	// @RequestMapping(value = URIConstants.CATGO_TYPE_LIST, method =
	// RequestMethod.POST)
	// private @ResponseBody String getCargoTypeList() {
	// String response;
	// List<ContentDTO> cargoTypeDTOList = new ArrayList<ContentDTO>();
	// List<CargoType> cargoTypeList = cargoTypeService.findAllCargoType();
	// if (cargoTypeList != null && !cargoTypeList.isEmpty()) {
	// cargoTypeDTOList =
	// FireCalculatorFactory.convertCargoTypeDTOList(cargoTypeList);
	// }
	// response = responseManager.getResponseString(cargoTypeDTOList);
	// return response;
	// }

	@RequestMapping(value = URIConstants.CATGO_TYPE_LIST, method = RequestMethod.POST)
	private @ResponseBody String getCargoTypeList(@RequestBody CargoTypeDTO criteria) {
		String response;
		List<ContentDTO> cargoTypeDTOList = new ArrayList<ContentDTO>();
		List<CargoType> cargoTypeList = cargoTypeService.findByInsuranceType(criteria.getInsuranceType());
		if (cargoTypeList != null && !cargoTypeList.isEmpty()) {
			cargoTypeDTOList = FireCalculatorFactory.convertCargoTypeDTOList(cargoTypeList);
		}
		response = responseManager.getResponseString(cargoTypeDTOList);
		return response;
	}
}
