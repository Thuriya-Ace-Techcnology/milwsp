package org.ace.insurance.system.common.driverType.persistence.interfaces;

import java.util.List;

import org.ace.insurance.common.InsuranceType;
import org.ace.insurance.system.common.driverType.DriverType;
import org.ace.java.component.persistence.exception.DAOException;

public interface IDriverTypeDAO {
	public DriverType insert(DriverType driverType) throws DAOException;

	public DriverType update(DriverType driverType) throws DAOException;

	public void delete(DriverType driverType) throws DAOException;

	public List<DriverType> findAll() throws DAOException;

	public DriverType findById(String id) throws DAOException;

	public List<DriverType> findByCriteria(String criteria) throws DAOException;

	public List<DriverType> findByInsuranceType(InsuranceType insuranceType) throws DAOException;
}
