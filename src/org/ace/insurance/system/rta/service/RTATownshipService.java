package org.ace.insurance.system.rta.service;

import javax.annotation.Resource;

import org.ace.insurance.system.rta.RTATownship;
import org.ace.insurance.system.rta.persistence.interfaces.IRTATownshipDAO;
import org.ace.insurance.system.rta.service.interfaces.IRTATownshipService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;

@Service("RTATownshipService")
public class RTATownshipService extends BaseService implements IRTATownshipService {
	@Resource(name = "RTATownshipDAO")
	private IRTATownshipDAO rtaTownshipDAO;

	@Override
	public RTATownship findByCode(String code) throws DAOException {
		RTATownship result = null;
		try {
			result = rtaTownshipDAO.findByCode(code);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a RTA (REG_Township_CODE : " + code + ")", e);
		}
		return result;
	}
}
