package org.ace.insurance.product.service.interfaces;

import java.util.List;
import java.util.Map;

import org.ace.insurance.product.PremiumCalData;
import org.ace.insurance.product.Product;
import org.ace.insurance.system.common.keyfactor.KeyFactor;
import org.ace.java.component.SystemException;
import org.ace.ws.model.premiumCal.PRO001;
import org.ace.ws.model.premiumCal.ResultPremium;

public interface IPremiumCalculatorService {

	public <T> Double calculatePremium(Map<KeyFactor, String> keyfatorValueMap, T param, PremiumCalData data) throws SystemException;

	public <T> Double calulatePremium(double premiumRate, T param, PremiumCalData data) throws SystemException;
	
	public  Double calulateTermPremium(double premium, int paymentType) throws SystemException;

	public <T> Double findPremiumRate(Map<KeyFactor, String> keyfatorValueMap, T param) throws SystemException;

	public Double calculateStampFee(Product param, PremiumCalData data) throws SystemException;

	public List<ResultPremium> calculatePremium(PRO001 pro001);
	
	public List<ResultPremium> calculateHealthPremium(PRO001 pro001);
	
	public <T> List<Double> findShoreJobPremiumRate(Product param) throws SystemException;

}
