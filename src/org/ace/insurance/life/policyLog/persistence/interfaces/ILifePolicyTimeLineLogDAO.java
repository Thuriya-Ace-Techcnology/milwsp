package org.ace.insurance.life.policyLog.persistence.interfaces;

import org.ace.insurance.life.dao.entities.LifePolicyIdLog;
import org.ace.insurance.life.dao.entities.LifePolicyTimeLineLog;
import org.ace.java.component.persistence.exception.DAOException;

public interface ILifePolicyTimeLineLogDAO {


	public void addLifePolicyTimeLineLog(LifePolicyTimeLineLog lifePolicyTimeLineLog) throws DAOException;
	public void addLifePolicyIdLog(LifePolicyIdLog lifePolicyIdLog) throws DAOException;



}
