package org.ace.insurance.system.common.district.persistence.interfaces;

import java.util.List;

import org.ace.insurance.system.common.district.District;
import org.ace.insurance.system.common.province.Province;
import org.ace.java.component.persistence.exception.DAOException;

public interface IDistrictDAO {
	public List<District> findByProvince(Province province) throws DAOException;

}
