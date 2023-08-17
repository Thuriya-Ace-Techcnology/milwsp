package org.ace.insurance.system.common.district.service;

import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.common.district.District;
import org.ace.insurance.system.common.district.persistence.interfaces.IDistrictDAO;
import org.ace.insurance.system.common.district.service.interfaces.IDistrictService;
import org.ace.insurance.system.common.province.Province;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;

@Service(value = "DistrictService")
public class DistrictService extends BaseService implements IDistrictService {
	
	@Resource(name = "DistrictDAO")
	private IDistrictDAO districtDAO;
	
	public List<District> findDistrictByProvince(Province province) {
		List<District> result = null;
		try {
			result = districtDAO.findByProvince(province);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find District by province " + province.getName(), e);
		}
		return result;
	}

}
