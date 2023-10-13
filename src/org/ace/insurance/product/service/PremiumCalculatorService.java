package org.ace.insurance.product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ace.insurance.common.ErrorCode;
import org.ace.insurance.common.InsuranceType;
import org.ace.insurance.common.KeyFactorIDConfig;
import org.ace.insurance.common.Utils;
import org.ace.insurance.life.KeyFactorChecker;
import org.ace.insurance.life.dao.entities.PolicyInsuredPersonAddon;
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
		Product product = null;
		if (param instanceof Product) {
			product = (Product) param;
			type = product.getPremiumRateType();
			basedAmount = product.getBasedAmount();
		} else if (param instanceof AddOn) {
			AddOn addOn = (AddOn) param;
			type = addOn.getPremiumRateType();
			basedAmount = addOn.getBasedAmount();
		}
		switch (type) {
			case BASED_ON_OWN_SUMINSURED: {
				premiumRate = (premiumRate * data.getSuminsured()) / basedAmount;
			}
				break;

			case BASED_ON_MAINCOVER_SUMINSURED: {
				if(KeyFactorChecker.isGovernmentShortTermEndowment(product.getId())) {
					// calculating to get one month premium according to SI
					premiumRate = premiumRate * 12;
				} else {
					premiumRate = (premiumRate * data.getMainCoverSuminsured()) / basedAmount;
				}
			}
				break;

			case PER_UNIT: {
				premiumRate = premiumRate * data.getUnit();
			}
				break;

			case BASED_ON_PREMIUM: {
				premiumRate = (premiumRate * data.getMainCoverPremium()) / basedAmount;
			}
				break;

			case USER_DEFINED_PREMIUM:
			case FIXED:
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
	public <T> List<Double> findShoreJobPremiumRate(Product param) throws SystemException {
		List<Double> result = null;
		try {
			result = premiumCalculatorDAO.findShoreJobPremiumRate(param);
			if (result == null) {
				throw new SystemException(ErrorCode.NO_PREMIUM_RATE, "There is no premiumn.");
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find a ProductPremiumRate", e);
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
		Double premium = 0.0;
		Double premiumRate = 0.0;
		Double addonPremium = 0.0;
		double sumInsured = 0.0;
		List<Double> premiumRateList;
		Product product = productSerivce.findProductById(pro001.getProductId());
		if (product == null) {
			return resultList;
		}		
		Map<KeyFactor, String> keyfactorValueMap = new HashMap<KeyFactor, String>();
		Map<String, String> keyFactorDTOMap = pro001.getKeyFactorMap();		
		KeyFactor keyfactor = null;	
		int age = 0;
		if(keyFactorDTOMap != null) {			
			for (String keyfactorId : keyFactorDTOMap.keySet()) {
				keyfactor = keyFactorService.findKeyFactorById(keyfactorId);
				keyfactorValueMap.put(keyfactor, keyFactorDTOMap.get(keyfactorId));	
				if(KeyFactorChecker.isPublicLife(product) && KeyFactorChecker.isFixedAge(keyfactor)) {
					age =  Integer.parseInt(keyFactorDTOMap.get(KeyFactorIDConfig.getFixedAgeId()));
				}
			}		
		}
		if(KeyFactorChecker.isPublicLife(product)) {
			int height = pro001.getFeet() * 12 + pro001.getInches();
			keyfactor = keyFactorService.findKeyFactorById(KeyFactorIDConfig.getPoundId());
			keyfactorValueMap.put(keyfactor, String.valueOf(calculatePounds(height,pro001.getWeight(),age)));
			
		}
		if (KeyFactorChecker.isSportMan(product) || KeyFactorChecker.isSnakeBite(product.getId()) || KeyFactorChecker.isSeaMenLife(product.getId())) {
			premiumRate = premiumCalculatorService.findPremiumRate(keyfactorValueMap, product);
			premium = premiumCalculatorService.calulatePremium(premiumRate, product, new PremiumCalData(null, null, null, (double) pro001.getUnit()));

		} else if (KeyFactorChecker.isShoreJobLife(product.getId())) {
			premiumRateList = premiumCalculatorService.findShoreJobPremiumRate(product);
			premiumRate = (pro001.getPeriodTerm() == 6) ? premiumRateList.get(0) : premiumRateList.get(1);
			premium = premiumCalculatorService.calulatePremium(premiumRate, product,
					new PremiumCalData(null, null, null,(double)pro001.getUnit()));

			if (!(pro001.getPeriodTerm() == 6)) {
				premium = premium * pro001.getPeriodTerm();
			} 
		} else if (KeyFactorChecker.isGovernmentShortTermEndowment(product.getId())) {
			premiumRate = premiumCalculatorService.findPremiumRate(keyfactorValueMap, product);		
			
			// calculating to get one month premium according to SI
			premiumRate = (pro001.getSumInsured() / product.getBasedAmount()) * premiumRate;
			premium = premiumCalculatorService.calulatePremium(premiumRate, product,
					new PremiumCalData(null, pro001.getSumInsured(), null, null));
			premium = premiumCalculatorService.calulateTermPremium(premium,paymentTypeService.findPaymentTypeById(pro001.getPaymentType()).getMonth());		

		} else {
			if(KeyFactorChecker.isPublicTermLife(product.getId())) {
				sumInsured = Double.parseDouble(keyFactorDTOMap.get(KeyFactorIDConfig.getSumInsuredId()));						
			}else {
				sumInsured = pro001.getSumInsured();
			}
			premiumRate = premiumCalculatorService.findPremiumRate(keyfactorValueMap, product);
			premium = premiumCalculatorService.calulatePremium(premiumRate, product,
					new PremiumCalData(null,sumInsured, null, null));
			if(KeyFactorChecker.isShortTermEndowment(product.getId()) || KeyFactorChecker.isPublicLife(product) || KeyFactorChecker.isGovernmentPersonal(product.getId()) ) {
				premium = premiumCalculatorService.calulateTermPremium(premium, paymentTypeService.findPaymentTypeById(pro001.getPaymentType()).getMonth());
			}
		}
		ResultPremium productPremium = new ResultPremium(product.getId().trim(), product.getName(), premium,0.0);
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
				addOnKeyfactorDTOMap = addon001.getKeyFactorMap();
				for (String keyfactorID : addOnKeyfactorDTOMap.keySet()) {						
					keyfactor = keyFactorService.findKeyFactorById(keyfactorID);
					addOnKeyfactorMap.put(keyfactor, addOnKeyfactorDTOMap.get(keyfactorID));						
				}	
				addonPremium = premiumCalculatorService.calculatePremium(addOnKeyfactorMap, addon, new PremiumCalData(null, null, null, 1.0));				
				resultAddonPremium = new ResultPremium(addon.getId().trim(), addon.getName(), addonPremium,0.0);
				resultList.add(resultAddonPremium);
			}
		}
		
		return resultList;
	}
	
	
	@Override
	public List<ResultPremium> calculateHealthPremium(PRO001 pro001) {
		List<ResultPremium> resultList = new ArrayList<ResultPremium>();
		Double premium = 0.0;
		Double addonPremium = 0.0;
		Product product = productSerivce.findProductById(pro001.getProductId());
		if (product == null) {
			return resultList;
		}		
		Map<KeyFactor, String> keyfactorValueMap = new HashMap<KeyFactor, String>();
		Map<String, String> keyFactorDTOMap = pro001.getKeyFactorMap();		
		KeyFactor keyfactor = null;	
		if(keyFactorDTOMap != null) {			
			for (String keyfactorId : keyFactorDTOMap.keySet()) {
				keyfactor = keyFactorService.findKeyFactorById(keyfactorId);
				keyfactorValueMap.put(keyfactor, keyFactorDTOMap.get(keyfactorId));	
			}		
		}		
		premium = premiumCalculatorService.calculatePremium(keyfactorValueMap, product, new PremiumCalData(null, null, null, (double) pro001.getUnit())); 
		ResultPremium productPremium = new ResultPremium(product.getId().trim(), product.getName(), premium,0.0);
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
				addOnKeyfactorDTOMap = addon001.getKeyFactorMap();
				for (String keyfactorID : addOnKeyfactorDTOMap.keySet()) {						
					keyfactor = keyFactorService.findKeyFactorById(keyfactorID);
					addOnKeyfactorMap.put(keyfactor, addOnKeyfactorDTOMap.get(keyfactorID));						
				}	
				addonPremium = premiumCalculatorService.calculatePremium(addOnKeyfactorMap, addon, new PremiumCalData(null, null, null,Double.parseDouble(addon001.getValue())));				
				resultAddonPremium = new ResultPremium(addon.getId().trim(), addon.getName(), addonPremium,0.0);
				resultList.add(resultAddonPremium);
			}
		}
		
		return resultList;
	}
	
	public int calculatePounds(int height, int weight, int age ) {
		if (height < 58) {
			height = 58;
		} else if (height  > 72) {
			height = 72;
		}
		if (age < 20) {
			age = 20;
		} else if (age > 45) {
			age = 45;
		}
		int pounds =  bmiService.findPoundByAgeAndHeight(age,height);
			int calPound;
			if (pounds > 0) {
				if (weight == pounds) {
					pounds = weight - pounds;
				} else if (weight > pounds) {
					calPound = pounds * 20 / 100;
					calPound = pounds + calPound;
					if (calPound >= weight) {
						pounds = 0;
					} else {
						pounds = weight - calPound;
					}
				} else {
					calPound = pounds * 15 / 100;
					calPound = pounds - calPound;
					if (calPound <= weight) {
						pounds = 0;
					} else {
						pounds = weight - calPound;
					}
					pounds = calPound - weight;
				}
			}
			pounds = Math.abs(pounds);
			pounds = (int) Math.ceil(pounds);			
			return pounds;
	}


	@Override
	public Double calulateTermPremium(double premium, int paymentType) throws SystemException {
		return (paymentType * premium) / 12;		
	}

}