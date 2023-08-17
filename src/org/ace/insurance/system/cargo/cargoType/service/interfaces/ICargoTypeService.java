package org.ace.insurance.system.cargo.cargoType.service.interfaces;

import java.util.List;

import org.ace.insurance.system.cargo.cargoType.CargoType;

public interface ICargoTypeService {

	public List<CargoType> findAllCargoType();

	public CargoType findCargoTypeById(String id);

	public List<CargoType> findByCriteria(String criteria);

	public List<CargoType> findByInsuranceType(String insuranceType);
}
