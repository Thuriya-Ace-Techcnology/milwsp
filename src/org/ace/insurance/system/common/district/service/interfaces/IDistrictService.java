package org.ace.insurance.system.common.district.service.interfaces;

import java.util.List;

import org.ace.insurance.system.common.district.District;
import org.ace.insurance.system.common.province.Province;

public interface IDistrictService {
	public List <District> findDistrictByProvince(Province province);
}
