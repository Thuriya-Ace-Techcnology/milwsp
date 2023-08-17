package org.ace.insurance.system.fire.paeRate.persistence.interfaces;

import org.ace.java.component.persistence.exception.DAOException;

public interface IPaeRateDAO {
	public double findRateByClassAndAge(String buildingClassId, int buildingAge) throws DAOException;
}
