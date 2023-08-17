package org.ace.insurance.moibleCountry.persistence.interfaces;

import java.util.List;

import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.MobileCountry;

public interface IMobileCountryDAO {
	public List<MobileCountry> findAll() throws DAOException;

	public String findByCountryCode(String countryCode);
}
