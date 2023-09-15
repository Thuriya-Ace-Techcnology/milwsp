package org.ace.insurance.life.policyLog.service;

import javax.annotation.Resource;

import org.ace.insurance.life.dao.entities.LifePolicyIdLog;
import org.ace.insurance.life.dao.entities.LifePolicyTimeLineLog;
import org.ace.insurance.life.policyLog.persistence.interfaces.ILifePolicyTimeLineLogDAO;
import org.ace.insurance.life.policyLog.service.interfaces.ILifePolicyTimeLineLogService;
import org.ace.java.component.SystemException;
import org.ace.java.component.idgen.service.interfaces.ICustomIDGenerator;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("LifePolicyTimeLineLogService")
public class LifePolicyTimeLineLogService implements ILifePolicyTimeLineLogService {
	@Resource(name = "LifePolicyTimeLineLogDAO")
	private ILifePolicyTimeLineLogDAO lifePolicyTimeLineLogDAO;

	@Resource(name = "CustomIDGenerator")
	private ICustomIDGenerator customIDGenerator;


	@Transactional(propagation = Propagation.REQUIRED)
	public void addLifePolicyIdLog(LifePolicyIdLog lifePolicyIdLog) {
		try {
			lifePolicyTimeLineLogDAO.addLifePolicyIdLog(lifePolicyIdLog);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to add a new LifePolicyIdLog", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addLifePolicyTimeLineLog(LifePolicyTimeLineLog lifePolicyTimeLineLog) {
		try {
			lifePolicyTimeLineLogDAO.addLifePolicyTimeLineLog(lifePolicyTimeLineLog);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to add a new LifePolicyTimeLineLog", e);
		}
	}




}
