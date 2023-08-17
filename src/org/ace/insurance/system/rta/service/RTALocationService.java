package org.ace.insurance.system.rta.service;

import javax.annotation.Resource;

import org.ace.insurance.system.rta.RTALocation;
import org.ace.insurance.system.rta.persistence.interfaces.IRTALocationDAO;
import org.ace.insurance.system.rta.service.interfaces.IRTALocationService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
@Service("RTALocationService")
public class RTALocationService  extends BaseService implements IRTALocationService {
	@Resource(name = "RTALocationDAO")
	private IRTALocationDAO rtaLocationDAO;
	
	@Override
	public RTALocation findByCode(String rtaLocationCode) throws DAOException {
		RTALocation result = null;
		try {
			result = rtaLocationDAO.findByCode(rtaLocationCode);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a RTA (REG_Location_CODE : " + rtaLocationCode + ")",e);
		}
		return result;
	}
}
