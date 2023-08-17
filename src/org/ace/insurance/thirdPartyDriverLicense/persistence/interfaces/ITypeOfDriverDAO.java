package org.ace.insurance.thirdPartyDriverLicense.persistence.interfaces;

import java.util.List;

import org.ace.insurance.thirdPartyDriverLicense.TypeOfDriver;
import org.ace.java.component.persistence.exception.DAOException;

public interface ITypeOfDriverDAO {

	public List<TypeOfDriver> findAll() throws DAOException;

	public TypeOfDriver findById(Long id) throws DAOException;

	public double findPremiumRateById(Long typeOfDriverId);

	public int findYearById(Long typeOfDriverId);

}
