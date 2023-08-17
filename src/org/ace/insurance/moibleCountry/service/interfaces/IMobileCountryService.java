package org.ace.insurance.moibleCountry.service.interfaces;
import java.util.List;

import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.MobileCountry;

public interface IMobileCountryService {
	public List<MobileCountry> findAll() throws DAOException;
	
}
