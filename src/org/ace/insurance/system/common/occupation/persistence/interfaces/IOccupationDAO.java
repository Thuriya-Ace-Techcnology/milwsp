package org.ace.insurance.system.common.occupation.persistence.interfaces;

import java.util.List;

import org.ace.insurance.system.common.occupation.Occupation;
import org.ace.java.component.persistence.exception.DAOException;

public interface IOccupationDAO {

	List<Occupation> findAll() throws DAOException;

	public Occupation findById(String id) throws DAOException;

}
