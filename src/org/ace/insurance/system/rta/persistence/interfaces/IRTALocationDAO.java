package org.ace.insurance.system.rta.persistence.interfaces;

import org.ace.insurance.system.rta.RTALocation;
import org.ace.java.component.persistence.exception.DAOException;

public interface IRTALocationDAO {
	public RTALocation findByCode(String rtaLocationCode) throws DAOException;
}
