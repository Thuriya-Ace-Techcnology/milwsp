package org.ace.insurance.system.common.driverType.service.interfaces;

import java.util.List;

import org.ace.insurance.common.InsuranceType;
import org.ace.insurance.system.common.driverType.DriverType;

public interface IDriverTypeService {
	public DriverType addNewDriverType(DriverType driverType);

	public DriverType updateDriverType(DriverType driverType);

	public void deleteDriverType(DriverType driverType);

	public List<DriverType> findAllDriverType();

	public DriverType findDriverTypeById(String id);

	public List<DriverType> findByCriteria(String criteria);

	public List<DriverType> findByInsuranceType(InsuranceType insuranceType);
}
