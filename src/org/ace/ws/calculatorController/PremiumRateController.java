package org.ace.ws.calculatorController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ace.insurance.common.KeyFactorIDConfig;
import org.ace.insurance.common.plans.services.interfaces.IPlansService;
import org.ace.insurance.life.KeyFactorChecker;
import org.ace.insurance.life.dao.entities.InsuredPersonKeyFactorValue;
import org.ace.insurance.life.dto.InsuredPersonInfoDTO;
import org.ace.insurance.product.PremiumCalData;
import org.ace.insurance.product.Product;
import org.ace.insurance.product.service.interfaces.IPremiumCalculatorService;
import org.ace.insurance.product.service.interfaces.IProductService;
import org.ace.insurance.system.common.keyfactor.KeyFactor;
import org.ace.insurance.system.common.keyfactor.service.interfaces.IKeyFactorService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.premiumCal.PRO001;
import org.ace.ws.model.premiumCal.ResultPremium;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PremiumRateController extends BaseController {

	@Resource(name = "PremiumCalculatorService")
	private IPremiumCalculatorService premiumCalculatorService;
	
	@Resource(name = "ProductService")
	private IProductService productService;
	
	
	@Resource(name = "PlansService")
	private IPlansService plansService;
	
	@Resource(name = "KeyFactorService")
	private IKeyFactorService keyFactorService;
	
	List<InsuredPersonKeyFactorValue> keyFactorValueList = null;

	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_PREMIUM, method = RequestMethod.POST)
	@ResponseBody
	private String getPremium(@RequestParam(name = "productId") String productId,
			@RequestParam(name = "planId") String planId,
			@RequestParam(name = "age") int age,
			@RequestParam(name = "month") int month) {	
		
 		AceResponse aceResponse = new AceResponse();
		Double premium = null;
		Double premiumRate;
		Product product = productService.findProductById(productId);
	    keyFactorValueList = new ArrayList<InsuredPersonKeyFactorValue>();
		if (product.getKeyFactorList() != null) {
			for (KeyFactor kf : product.getKeyFactorList()) {
				InsuredPersonKeyFactorValue insKf = new InsuredPersonKeyFactorValue(kf);
				insKf.setKeyFactor(kf);
				keyFactorValueList.add(insKf);
			}
		}		
		setKeyFactorValue(planId , age);
		Map<KeyFactor, String> keyfactorValueMap = new HashMap<KeyFactor, String>();
		for (InsuredPersonKeyFactorValue insukf : keyFactorValueList) {
			keyfactorValueMap.put(insukf.getKeyFactor(), insukf.getValue());
		}	
		premiumRate = premiumCalculatorService.findPremiumRate(keyfactorValueMap, product);
		premium = premiumCalculatorService.calulatePremium(premiumRate, product, new PremiumCalData(null, null, null,null));
		if(month == 6) {
			premium = premium * 0.75;
		}
		aceResponse.setMessage("Success");
		aceResponse.setStatus(HttpStatus.OK);
		aceResponse.setData(premium);
		return responseManager.getResponseString(aceResponse);
		
	} 
	
	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_LIFE_PREMIUM, method = RequestMethod.POST)
	@ResponseBody
	private String getLifeProductPremium(@RequestBody PRO001 pro001) {
		List<ResultPremium> resultList = premiumCalculatorService.calculatePremium(pro001);
		if(resultList.size()>0) {
			if(resultList.get(0).getPremium()== 0.0) {
				return responseManager.getResponseString(new ArrayList<ResultPremium>());
			}
		}
		return responseManager.getResponseString(resultList);
	}
	
	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_HEALTH_PREMIUM, method = RequestMethod.POST)
	@ResponseBody
	private String getHealthProductPremium(@RequestBody PRO001 pro001) {
		List<ResultPremium> resultList = premiumCalculatorService.calculateHealthPremium(pro001);
		if(resultList.size()>0) {
			if(resultList.get(0).getPremium()== 0.0) {
				return responseManager.getResponseString(new ArrayList<ResultPremium>());
			}
		}
		return responseManager.getResponseString(resultList);
	}


	private void setKeyFactorValue(String planId , int age) {
		for (InsuredPersonKeyFactorValue vehKF : keyFactorValueList) {
			KeyFactor kf = vehKF.getKeyFactor();
			if (KeyFactorChecker.isPlan(kf)) {
				vehKF.setValue(planId);
			} else if (KeyFactorChecker.isAge(kf)) {
				vehKF.setValue(age + "");
			}
		}
	}
	
	
	
}
