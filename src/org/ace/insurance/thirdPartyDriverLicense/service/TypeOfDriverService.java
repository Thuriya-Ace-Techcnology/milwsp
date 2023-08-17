package org.ace.insurance.thirdPartyDriverLicense.service;

import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.thirdPartyDriverLicense.TypeOfDriver;
import org.ace.insurance.thirdPartyDriverLicense.persistence.interfaces.ITypeOfDriverDAO;
import org.ace.insurance.thirdPartyDriverLicense.service.interfaces.ITypeOfDriverService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("TypeOfDriverService")
public class TypeOfDriverService implements ITypeOfDriverService {

	@Resource(name = "TypeOfDriverDAO")
	private ITypeOfDriverDAO typeOfDriverDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public List<TypeOfDriver> findAllTypeOfDriver() {
		List<TypeOfDriver> result = null;
		try {
			result = typeOfDriverDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find all TypeOfDriver", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public TypeOfDriver findById(Long id) {
		TypeOfDriver result = null;
		try {
			result = typeOfDriverDAO.findById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find TypeOfDriver", e);
		}
		return result;
	}

}
