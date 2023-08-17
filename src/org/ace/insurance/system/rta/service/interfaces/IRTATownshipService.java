package org.ace.insurance.system.rta.service.interfaces;

import org.ace.insurance.system.rta.RTATownship;
import org.ace.java.component.persistence.exception.DAOException;

public interface IRTATownshipService {
	public RTATownship findByCode(String rtaLocationCode) throws DAOException;
}
