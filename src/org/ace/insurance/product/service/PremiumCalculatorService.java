package org.ace.insurance.product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ace.insurance.common.ErrorCode;
import org.ace.insurance.common.InsuranceType;
import org.ace.insurance.common.KeyFactorIDConfig;
import org.ace.insurance.product.PremiumCalData;
import org.ace.insurance.product.PremiumRateType;
import org.ace.insurance.product.Product;
import org.ace.insurance.product.StampFeeRateType;
import org.ace.insurance.product.persistence.interfaces.IPremiumCalculatorDAO;
import org.ace.insurance.product.service.interfaces.IPremiumCalculatorService;
import org.ace.insurance.product.service.interfaces.IProductService;
import org.ace.insurance.system.common.addon.AddOn;
import org.ace.insurance.system.common.addon.service.interfaces.IAddOnService;
import org.ace.insurance.system.common.bmiChart.service.interfaces.IBMIService;
import org.ace.insurance.system.common.keyfactor.KeyFactor;
import org.ace.insurance.system.common.keyfactor.service.interfaces.IKeyFactorService;
import org.ace.insurance.system.common.paymenttype.service.interfaces.IPaymentTypeService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.ace.ws.model.premiumCal.ADO001;
import org.ace.ws.model.premiumCal.PRO001;
import org.ace.ws.model.premiumCal.ResultPremium;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "PremiumCalculatorService")
public class PremiumCalculatorService extends BaseService implements IPremiumCalculatorService {

	@Resource(name = "PremiumCalculatorDAO")
	private IPremiumCalculatorDAO premiumCalculatorDAO;

	@Resource(name = "ProductService")
	private IProductService productSerivce;

	@Resource(name = "KeyFactorService")
	private IKeyFactorService keyFactorService;

	@Resource(name = "AddOnService")
	private IAddOnService addOnService;

	@Resource(name = "PaymentTypeService")
	private IPaymentTypeService paymentTypeService;

	@Resource(name = "PremiumCalculatorService")
	private IPremiumCalculatorService premiumCalculatorService;

	@Resource(name = "BMIService")
	private IBMIService bmiService;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public <T> Double calculatePremium(Map<KeyFactor, String> keyfatorValueMap, T param, PremiumCalData data) throws SystemException {
		Double result = null;
		try {
			result = premiumCalculatorDAO.findPremiumRate(keyfatorValueMap, param);
			if (result != null && result > 0) {
				result = calulatePremium(result, param, data);
			} else {
				return result = 0.0;
				// throw new SystemException(ErrorCode.NO_PREMIUM_RATE,
				// keyfatorValueMap, "There is no premiumn.");
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a ProductPremiumRate", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public <T> Double calulatePremium(double premiumRate, T param, PremiumCalData data) throws SystemException {
		PremiumRateType type = null;
		Double basedAmount = null;
		if (param instanceof Product) {
			Product product = (Product) param;
			type = product.getPremiumRateType();
			basedAmount = product.getBasedAmount();
		} else if (param instanceof AddOn) {
			AddOn addOn = (AddOn) param;
			type = addOn.getPremiumRateType();
			basedAmount = addOn.getBasedAmount();
		}
		switch (type) {
			case USER_DEFINED_PREMIUM:
				break;
			default:
				break;
		}
		return premiumRate;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public <T> Double findPremiumRate(Map<KeyFactor, String> keyfatorValueMap, T param) throws SystemException {
		Double result = null;
		try {
			result = premiumCalculatorDAO.findPremiumRate(keyfatorValueMap, param);
			if (result == null || result < 0) {
				throw new SystemException(ErrorCode.NO_PREMIUM_RATE, keyfatorValueMap, "There is no premiumn.");
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a ProductPremiumRate", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Double calculateStampFee(Product product, PremiumCalData data) throws SystemException {
		double stampFee = 0.0;
		double stampFeeRate = product.getStampFee();
		StampFeeRateType type = product.getStampFeeRateType();
		if (type != null)
			switch (type) {
				case BASEDONSI: {
					int stampFeeCount = (int) (data.getMainCoverSuminsured() / product.getStampFeeBasedAmount());
					stampFee = stampFeeRate * stampFeeCount;
				}
					break;
				case BASEONUNIT: {
					stampFee = stampFeeRate * data.getUnit();
				}
					break;
				case FIXED: {
					stampFee = stampFeeRate;
				}
					break;
				default:
					break;
			}
		return stampFee;
	}

	@Override
	public List<ResultPremium> calculatePremium(PRO001 pro001) {
		List<ResultPremium> resultList = new ArrayList<ResultPremium>();
		Double basicPremium = 0.0;
		Double addonPremium = 0.0;
		double mainCoverSumInsured = 0;
		int unit = 1;
		Product product = productSerivce.findProductById(pro001.getProductId());
		if (product == null) {
			return resultList;
		}
		Map<KeyFactor, String> keyfactorValueMap = new HashMap<KeyFactor, String>();
		Map<String, String> keyFactorDTOMap = pro001.getKeyFactorMap();

		// If public life do these function
		if (product.getId().trim().equals(KeyFactorIDConfig.getPublicLifeId())) {
			// int weight =
			// Integer.parseInt(keyFactorDTOMap.get(KeyFactorIDConfig.getWeightId()));
			// int feet =
			// Integer.parseInt(keyFactorDTOMap.get(KeyFactorIDConfig.getFeetId()));
			// int inches =
			// Integer.parseInt(keyFactorDTOMap.get(KeyFactorIDConfig.getInchesId()));
			// int height = feet * 12 + inches;
			// int age =
			// Integer.parseInt(keyFactorDTOMap.get(KeyFactorIDConfig.getFixedAgeId()));
			// String pounds = calculatePounds(weight, height, age);
			keyFactorDTOMap.put(KeyFactorIDConfig.getPoundId(), "1");
			keyFactorDTOMap.remove(KeyFactorIDConfig.getFeetId());
			keyFactorDTOMap.remove(KeyFactorIDConfig.getInchesId());
			keyFactorDTOMap.remove(KeyFactorIDConfig.getWeightId());
		}

		KeyFactor keyfactor = null;
		InsuranceType insuranceType = product.getInsuranceType();
		for (String keyfactorId : keyFactorDTOMap.keySet()) {
			if (insuranceType.equals(InsuranceType.MOTOR) || insuranceType.equals(InsuranceType.TRAVEL_INSURANCE)) {
				keyfactor = keyFactorService.findKeyFactorById(keyfactorId);
				keyfactorValueMap.put(keyfactor, keyFactorDTOMap.get(keyfactorId));
				
			}
			else if (!KeyFactorIDConfig.getSumInsuredId().trim().equals(keyfactorId) && !KeyFactorIDConfig.getUnitKFId().trim().equals(keyfactorId)) {
				keyfactor = keyFactorService.findKeyFactorById(keyfactorId);
				keyfactorValueMap.put(keyfactor, keyFactorDTOMap.get(keyfactorId));
			}
		}

		if (product.getPremiumRateType().equals(PremiumRateType.PER_UNIT)) {
			unit = Integer.parseInt(keyFactorDTOMap.get(KeyFactorIDConfig.getUnitKFId()));
			basicPremium = premiumCalculatorService.calculatePremium(keyfactorValueMap, product, new PremiumCalData(null, null, null, (double) unit));

		} else {
			if (keyFactorDTOMap.get(KeyFactorIDConfig.getSumInsuredId()) != null) {
				mainCoverSumInsured = Double.parseDouble(keyFactorDTOMap.get(KeyFactorIDConfig.getSumInsuredId()));
			}
			basicPremium = premiumCalculatorService.calculatePremium(keyfactorValueMap, product, new PremiumCalData(null, mainCoverSumInsured, null, null));
		}

		ResultPremium productPremium = new ResultPremium(product.getId().trim(), product.getName(), basicPremium,0.0);
		resultList.add(productPremium);

		/** Addon Premium */
		ResultPremium resultAddonPremium = null;
		AddOn addon = null;
		if (pro001.getAddOnList() != null) {
			Map<KeyFactor, String> addOnKeyfactorMap = null;
			Map<String, String> addOnKeyfactorDTOMap = null;
			for (ADO001 addon001 : pro001.getAddOnList()) {
				addOnKeyfactorMap = new HashMap<KeyFactor, String>();
				addOnKeyfactorDTOMap = new HashMap<String, String>();
				addon = addOnService.findAddOnById(addon001.getAddOnId());
				if (addon.isBaseOnKeyFactor()) {
					addOnKeyfactorDTOMap = addon001.getKeyFactorMap();
					for (String keyfactorID : addOnKeyfactorDTOMap.keySet()) {
						if (!KeyFactorIDConfig.getSumInsuredId().trim().equals(keyfactorID) && !KeyFactorIDConfig.getUnitKFId().trim().equals(keyfactorID)) {
							keyfactor = keyFactorService.findKeyFactorById(keyfactorID);
							addOnKeyfactorMap.put(keyfactor, addOnKeyfactorDTOMap.get(keyfactorID));
						}
					}
				}

				if (addon.getPremiumRateType().equals(PremiumRateType.PER_UNIT)) {
					addOnKeyfactorDTOMap = addon001.getKeyFactorMap();
					unit = Integer.parseInt(addOnKeyfactorDTOMap.get(KeyFactorIDConfig.getUnitKFId()));
					addonPremium = premiumCalculatorService.calculatePremium(addOnKeyfactorMap, addon, new PremiumCalData(null, mainCoverSumInsured, basicPremium, (double) unit));
				} else {
					String sumInsuredStr = addOnKeyfactorDTOMap.get(KeyFactorIDConfig.getSumInsuredId());
					double sumInsured = 0;
					if (sumInsuredStr != null)
						sumInsured = Double.parseDouble(addOnKeyfactorDTOMap.get(KeyFactorIDConfig.getSumInsuredId()));
					addonPremium = premiumCalculatorService.calculatePremium(addOnKeyfactorMap, addon, new PremiumCalData(sumInsured, mainCoverSumInsured, basicPremium, null));
				}

				resultAddonPremium = new ResultPremium(addon.getId().trim(), addon.getName(), addonPremium,0.0);
				resultList.add(resultAddonPremium);
			}
		}
		return resultList;
	}

	private String calculatePounds(int weight, int height, int age) {
		int pounds = 0;

		if (height < 58) {
			height = 58;
		} else if (height > 72) {
			height = 72;
		}
		if (age < 20) {
			age = 20;
		} else if (age > 45) {
			age = 45;
		}
		int bmiWeight = bmiService.findPoundByAgeAndHeight(age, height);
		int calPound = 0;
		if (bmiWeight > 0) {
			if (weight == bmiWeight) {
				pounds = weight - bmiWeight;
			} else if (weight > bmiWeight) {
				calPound = bmiWeight * 20 / 100;
				calPound = bmiWeight + calPound;

				if (calPound >= weight) {
					bmiWeight = 0;
				} else {
					bmiWeight = weight - calPound;
				}
			} else {
				calPound = bmiWeight * 15 / 100;
				calPound = bmiWeight - calPound;
				if (calPound <= weight) {
					bmiWeight = 0;
				} else {
					bmiWeight = weight - calPound;
				}
				bmiWeight = calPound - weight;
			}
		}
		bmiWeight = Math.abs(bmiWeight);
		pounds = (int) Math.ceil(bmiWeight);

		return pounds + "";
	}

}