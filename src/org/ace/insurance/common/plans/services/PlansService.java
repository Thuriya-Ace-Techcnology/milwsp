package org.ace.insurance.common.plans.services;

import java.util.List;


import javax.annotation.Resource;

import org.ace.insurance.common.plans.Plans;
import org.ace.insurance.common.plans.persistences.interfaces.IPlansDAO;
import org.ace.insurance.common.plans.services.interfaces.IPlansService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "PlansService")
public class PlansService extends BaseService implements IPlansService{
	
	@Resource(name = "PlansDAO")
	private IPlansDAO plansDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Plans> getPlanList() {
		List<Plans> result = null;
		try {
			result = plansDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of Plans Type)", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Plans findById(String id) {
		Plans result = null;
		try {
			result = plansDAO.findById(id);	
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find  Plan Type)", e);
		}
		return result;

	}

}
