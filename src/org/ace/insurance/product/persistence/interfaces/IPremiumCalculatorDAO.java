package org.ace.insurance.product.persistence.interfaces;

import java.util.List;
import java.util.Map;

import org.ace.insurance.product.Product;
import org.ace.insurance.system.common.keyfactor.KeyFactor;
import org.ace.java.component.persistence.exception.DAOException;

public interface IPremiumCalculatorDAO {

	public <T> Double findPremiumRate(Map<KeyFactor, String> keyfatorValueMap, T param) throws DAOException;
	
	public <T> List<Double> findShoreJobPremiumRate(Product product) throws DAOException;

}
