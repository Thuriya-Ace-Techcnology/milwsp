package org.ace.insurance.system.common.motorCycleType.service.interfaces;

import java.util.List;

import org.ace.insurance.common.InsuranceType;
import org.ace.insurance.system.common.motorCycleType.MotorCycleType;

public interface IMotorCycleTypeService {
	public MotorCycleType addNewMotorCycleType(MotorCycleType motorCycleType);

	public MotorCycleType updateMotorCycleType(MotorCycleType motorCycleType);

	public void deleteMotorCycleType(MotorCycleType motorCycleType);

	public List<MotorCycleType> findAllMotorCycleType();

	public MotorCycleType findMotorCycleTypeById(String id);

	public List<MotorCycleType> findByCriteria(String criteria);

	public List<MotorCycleType> findByInsuranceType(InsuranceType insuranceType);
}
