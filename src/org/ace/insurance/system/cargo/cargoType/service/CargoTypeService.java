package org.ace.insurance.system.cargo.cargoType.service;

import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.cargo.cargoType.CargoType;
import org.ace.insurance.system.cargo.cargoType.persistence.interfaces.ICargoTypeDAO;
import org.ace.insurance.system.cargo.cargoType.service.interfaces.ICargoTypeService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("CargoTypeService")
public class CargoTypeService extends BaseService implements ICargoTypeService {

	@Resource(name = "CargoTypeDAO")
	private ICargoTypeDAO cargoTypeDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public List<CargoType> findAllCargoType() {
		List<CargoType> result = null;
		try {
			result = cargoTypeDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find all cargoType", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CargoType findCargoTypeById(String id) {
		CargoType cargoType = cargoTypeDAO.findById(id);
		return cargoType;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<CargoType> findByCriteria(String criteria) {
		List<CargoType> result = null;
		try {
			result = cargoTypeDAO.findByCriteria(criteria);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find CargiType by criteria " + criteria, e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<CargoType> findByInsuranceType(String insuranceType) {
		List<CargoType> result = null;
		try {
			result = cargoTypeDAO.findByInsuranceType(insuranceType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find CargiType by insuranceType " + insuranceType, e);
		}
		return result;
	}
}
