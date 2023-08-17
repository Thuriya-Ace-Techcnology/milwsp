package org.ace.insurance.system.rta.persistence.interfaces;

import org.ace.insurance.system.rta.RTATownship;
import org.ace.java.component.persistence.exception.DAOException;

public interface IRTATownshipDAO {
	public RTATownship findByCode(String code) throws DAOException;
}
