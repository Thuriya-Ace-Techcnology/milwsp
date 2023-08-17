package org.ace.insurance.system.rta.service.interfaces;

import org.ace.insurance.system.rta.RTALocation;
import org.ace.java.component.persistence.exception.DAOException;

public interface IRTALocationService {
	public RTALocation findByCode(String rtaLocationCode) throws DAOException;
}
