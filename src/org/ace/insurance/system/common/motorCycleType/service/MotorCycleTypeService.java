package org.ace.insurance.system.common.motorCycleType.service;

import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.common.InsuranceType;
import org.ace.insurance.system.common.motorCycleType.MotorCycleType;
import org.ace.insurance.system.common.motorCycleType.persistence.interfaces.IMotorCycleTypeDAO;
import org.ace.insurance.system.common.motorCycleType.service.interfaces.IMotorCycleTypeService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("MotorCycleTypeService")
public class MotorCycleTypeService extends BaseService implements IMotorCycleTypeService {
	@Resource(name = "MotorCycleTypeDAO")
	private IMotorCycleTypeDAO MotorCycleTypeDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public MotorCycleType addNewMotorCycleType(MotorCycleType motorCycleType) {
		try {
			motorCycleType = MotorCycleTypeDAO.insert(motorCycleType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a MotorCycleType", e);
		}
		return motorCycleType;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public MotorCycleType updateMotorCycleType(MotorCycleType motorCycleType) {
		try {
			motorCycleType = MotorCycleTypeDAO.update(motorCycleType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update MotorCycleType", e);
		}
		return motorCycleType;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteMotorCycleType(MotorCycleType motorCycleType) {
		try {
			MotorCycleTypeDAO.delete(motorCycleType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to delete MotorCycleType", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<MotorCycleType> findAllMotorCycleType() {
		List<MotorCycleType> result = null;
		try {
			result = MotorCycleTypeDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find all MotorCycleType", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public MotorCycleType findMotorCycleTypeById(String id) {
		MotorCycleType MotorCycleType = MotorCycleTypeDAO.findById(id);
		return MotorCycleType;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<MotorCycleType> findByCriteria(String criteria) {
		List<MotorCycleType> result = null;
		try {
			result = MotorCycleTypeDAO.findByCriteria(criteria);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find MotorCycleType by criteria " + criteria, e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<MotorCycleType> findByInsuranceType(InsuranceType insuranceType) {
		List<MotorCycleType> result = null;
		try {
			result = MotorCycleTypeDAO.findByInsuranceType(insuranceType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find Cargo Type By InsuranceType" + insuranceType, e);
		}
		return result;
	}
}
