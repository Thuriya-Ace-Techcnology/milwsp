package org.ace.insurance.system.cargo.cargoType.persistence.interfaces;

import java.util.List;

import org.ace.insurance.system.cargo.cargoType.CargoType;
import org.ace.java.component.persistence.exception.DAOException;

public interface ICargoTypeDAO {

	public List<CargoType> findAll() throws DAOException;

	public CargoType findById(String id) throws DAOException;

	public List<CargoType> findByCriteria(String criteria) throws DAOException;

	public List<CargoType> findByInsuranceType(String insuranceType) throws DAOException;
}
