package org.ace.ws.calculatorController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ace.insurance.common.KeyFactorIDConfig;
import org.ace.insurance.product.service.interfaces.IPremiumCalculatorService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.premiumCal.PRO001;
import org.ace.ws.model.premiumCal.ResultPremium;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PremiumRateController extends BaseController {

	@Resource(name = "PremiumCalculatorService")
	private IPremiumCalculatorService premiumCalculatorService;

	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_PREMIUM, method = RequestMethod.POST)
	@ResponseBody
	private String getPremium(@RequestBody PRO001 pro001) {
		List<ResultPremium> resultList = premiumCalculatorService.calculatePremium(pro001);
		if(resultList.size()>0) {
			if(resultList.get(0).getPremium()== 0.0) {
				return responseManager.getResponseString(new ArrayList<ResultPremium>());
			}
		}
		return responseManager.getResponseString(resultList);
	}

	@SuppressWarnings("unused")
	private boolean isMotorUSDProduct(String productId) {
		boolean result = false;
		List<String> usdProductIdList = new ArrayList<String>();
		usdProductIdList.add(KeyFactorIDConfig.getBusUSDId());
		usdProductIdList.add(KeyFactorIDConfig.getGoodCarryingUSDId());
		usdProductIdList.add(KeyFactorIDConfig.getLocalTaxiUSDId());
		usdProductIdList.add(KeyFactorIDConfig.getMeterTaxiUSDId());
		usdProductIdList.add(KeyFactorIDConfig.getMobilePlantUSDId());
		usdProductIdList.add(KeyFactorIDConfig.getPrivateCarUSDId());
		usdProductIdList.add(KeyFactorIDConfig.getMotorCycleUSDId());
		usdProductIdList.add(KeyFactorIDConfig.getPaidDriverId());
		if (usdProductIdList.contains(productId)) {
			result = true;
		}
		return result;
	}

	@SuppressWarnings("unused")
	private double calculateFidelityPremium(double sumInsured, Map<String, String> keyFactorDTOMap) {
		double premium = 0.0;
		for (String keyFactorId : keyFactorDTOMap.keySet()) {
			if (KeyFactorIDConfig.getIsOfficialKFId().equals(keyFactorId)) {
				if (keyFactorDTOMap.get(keyFactorId).equals("1")) {
					premium = (sumInsured / 100) * 1;
				} else {
					premium = (sumInsured / 100) * 2;
				}
			}
		}
		return premium;
	}

	@SuppressWarnings("unused")
	private double calculateCashInSafePremium(double sumInsured, Map<String, String> keyFactorDTOMap) {
		double premium = 0.0;
		int rate = 0;
		for (String keyFactorId : keyFactorDTOMap.keySet()) {
			if (KeyFactorIDConfig.getIsGpvBamlKfId().equals(keyFactorId) && keyFactorDTOMap.get(keyFactorId).equals("1")) {
				rate = 5;
				for (String kfid : keyFactorDTOMap.keySet()) {
					if (KeyFactorIDConfig.getIsImportantKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
						rate = rate - 1;
					}
					if (KeyFactorIDConfig.getIsSafePlaceKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
						rate = rate - 1;
					}
					if (KeyFactorIDConfig.getIsSecurityKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
						rate = rate - 1;
					}
				}
				break;
			} else {
				rate = 10;
				for (String kfid : keyFactorDTOMap.keySet()) {
					if (KeyFactorIDConfig.getIsImportantKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
						rate = rate - 1;
					}
					if (KeyFactorIDConfig.getIsSafePlaceKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
						rate = rate - 1;
					}
					if (KeyFactorIDConfig.getIsSecurityKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
						rate = rate - 1;
					}

					if (KeyFactorIDConfig.getIsSystematicallyKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
						rate = rate - 1;
					}

					if (KeyFactorIDConfig.getIsNearanceKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
						rate = rate - 1;
					}
				}
				break;
			}
		}
		float exactRate = (float) rate / 10;
		premium = (sumInsured * exactRate) / 100;
		return premium;
	}

	@SuppressWarnings("unused")
	private double calculateCashInTransitPremium(double sumInsured, Map<String, String> keyFactorDTOMap) {
		double premium = 0.0;
		float rate = 0.0f;
		int distanceMile = 0;
		for (String keyFactorId : keyFactorDTOMap.keySet()) {
			if (KeyFactorIDConfig.getDistanceMileKFId().equals(keyFactorId)) {
				distanceMile = Integer.parseInt(keyFactorDTOMap.get(keyFactorId));
				if (distanceMile <= 10) {
					rate = 0.27f;
					for (String kfid : keyFactorDTOMap.keySet()) {
						if (KeyFactorIDConfig.getIsSecurityKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
							rate = rate - 0.02f;
						}
						if (KeyFactorIDConfig.getIsOwnVehUseKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
							rate = rate - 0.02f;
						}
						if (KeyFactorIDConfig.getIsBoxSaveKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
							rate = rate - 0.02f;
						}
						if (KeyFactorIDConfig.getIsPublicWayKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
							rate = rate - 0.02f;
						}
						if (KeyFactorIDConfig.getIsMainPlaceKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
							rate = rate - 0.02f;
						}
					}
				} else if (distanceMile <= 15) {
					rate = 0.54f;
					for (String kfid : keyFactorDTOMap.keySet()) {
						if (KeyFactorIDConfig.getIsSecurityKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
							rate = rate - 0.04f;
						}
						if (KeyFactorIDConfig.getIsOwnVehUseKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
							rate = rate - 0.04f;
						}
						if (KeyFactorIDConfig.getIsBoxSaveKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
							rate = rate - 0.04f;
						}
						if (KeyFactorIDConfig.getIsPublicWayKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
							rate = rate - 0.04f;
						}
						if (KeyFactorIDConfig.getIsMainPlaceKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
							rate = rate - 0.04f;
						}
					}
				} else if (distanceMile <= 20) {
					rate = 0.81f;
					for (String kfid : keyFactorDTOMap.keySet()) {
						if (KeyFactorIDConfig.getIsSecurityKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
							rate = rate - 0.06f;
						}
						if (KeyFactorIDConfig.getIsOwnVehUseKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
							rate = rate - 0.06f;
						}
						if (KeyFactorIDConfig.getIsBoxSaveKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
							rate = rate - 0.06f;
						}
						if (KeyFactorIDConfig.getIsPublicWayKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
							rate = rate - 0.06f;
						}
						if (KeyFactorIDConfig.getIsMainPlaceKfId().equals(kfid) && keyFactorDTOMap.get(kfid).equals("1")) {
							rate = rate - 0.06f;
						}
					}
				} else if (distanceMile > 20) {
					rate = 0.90f;
				}
				break;
			}
		}
		premium = (sumInsured * rate) / 1000;
		return premium;
	}

}
