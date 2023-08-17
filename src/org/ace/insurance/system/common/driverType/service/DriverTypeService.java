package org.ace.insurance.system.common.driverType.service;

import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.common.InsuranceType;
import org.ace.insurance.system.common.driverType.DriverType;
import org.ace.insurance.system.common.driverType.persistence.interfaces.IDriverTypeDAO;
import org.ace.insurance.system.common.driverType.service.interfaces.IDriverTypeService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("DriverTypeService")
public class DriverTypeService extends BaseService implements IDriverTypeService{
	@Resource(name = "DriverTypeDAO")
	private IDriverTypeDAO DriverTypeDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public DriverType addNewDriverType(DriverType driverType) {
		try {
			driverType = DriverTypeDAO.insert(driverType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a DriverType", e);
		}
		return driverType;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public DriverType updateDriverType(DriverType driverType) {
		try {
			driverType = DriverTypeDAO.update(driverType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update DriverType", e);
		}
		return driverType;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteDriverType(DriverType driverType) {
		try {
			DriverTypeDAO.delete(driverType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to delete DriverType", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<DriverType> findAllDriverType() {
		List<DriverType> result = null;
		try {
			result = DriverTypeDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find all DriverType", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public DriverType findDriverTypeById(String id) {
		DriverType DriverType = DriverTypeDAO.findById(id);
		return DriverType;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<DriverType> findByCriteria(String criteria) {
		List<DriverType> result = null;
		try {
			result = DriverTypeDAO.findByCriteria(criteria);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find DriverType by criteria " + criteria, e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<DriverType> findByInsuranceType(InsuranceType insuranceType) {
		List<DriverType> result = null;
		try {
			result = DriverTypeDAO.findByInsuranceType(insuranceType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find Cargo Type By InsuranceType" + insuranceType, e);
		}
		return result;
	}
}
