package org.ace.insurance.system.common.occupation.service;

import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.common.occupation.Occupation;
import org.ace.insurance.system.common.occupation.persistence.interfaces.IOccupationDAO;
import org.ace.insurance.system.common.occupation.service.interfaces.IOccupationService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "OccupationService")
public class OccupationService extends BaseService implements IOccupationService {

	@Resource(name = "OccupationDAO")
	private IOccupationDAO occupationDAO;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Occupation> findAllOccupation() {
		List<Occupation> result = null;
		try {
			result = occupationDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find all of Branch)", e);
		}
		return result;
	}

}
