package org.ace.insurance.system.common.motorCycleType.persistence.interfaces;

import java.util.List;

import org.ace.insurance.common.InsuranceType;
import org.ace.insurance.system.common.motorCycleType.MotorCycleType;
import org.ace.java.component.persistence.exception.DAOException;

public interface IMotorCycleTypeDAO {
	public MotorCycleType insert(MotorCycleType motorCycleType) throws DAOException;

	public MotorCycleType update(MotorCycleType motorCycleType) throws DAOException;

	public void delete(MotorCycleType motorCycleType) throws DAOException;

	public List<MotorCycleType> findAll() throws DAOException;

	public MotorCycleType findById(String id) throws DAOException;

	public List<MotorCycleType> findByCriteria(String criteria) throws DAOException;

	public List<MotorCycleType> findByInsuranceType(InsuranceType insuranceType) throws DAOException;
}
