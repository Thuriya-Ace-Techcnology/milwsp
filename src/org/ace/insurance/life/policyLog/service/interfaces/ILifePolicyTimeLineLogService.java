package org.ace.insurance.life.policyLog.service.interfaces;

import org.ace.insurance.life.dao.entities.LifePolicyIdLog;
import org.ace.insurance.life.dao.entities.LifePolicyTimeLineLog;

public interface ILifePolicyTimeLineLogService {
	
	public void addLifePolicyTimeLineLog(LifePolicyTimeLineLog lifePolicyTimeLineLog) ;
	
	public void addLifePolicyIdLog(LifePolicyIdLog lifePolicyIdLog);
}
