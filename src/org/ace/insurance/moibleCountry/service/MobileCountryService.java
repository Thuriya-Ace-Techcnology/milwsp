package org.ace.insurance.moibleCountry.service;

import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.moibleCountry.persistence.interfaces.IMobileCountryDAO;
import org.ace.insurance.moibleCountry.service.interfaces.IMobileCountryService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.ace.ws.model.MobileCountry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "MobileCountryService")
public class MobileCountryService extends BaseService implements IMobileCountryService{

	@Resource(name = "MobileCountryDAO")
	private IMobileCountryDAO mobileCountryDAO;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MobileCountry> findAll() throws DAOException {
		 try {
			return mobileCountryDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a new MobilePersonalAccidentProposal", e);
		}
	}

}
