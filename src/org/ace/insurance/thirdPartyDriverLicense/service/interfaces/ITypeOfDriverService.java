package org.ace.insurance.thirdPartyDriverLicense.service.interfaces;

import java.util.List;

import org.ace.insurance.thirdPartyDriverLicense.TypeOfDriver;

public interface ITypeOfDriverService {

	public List<TypeOfDriver> findAllTypeOfDriver();

	public TypeOfDriver findById(Long id);

}
