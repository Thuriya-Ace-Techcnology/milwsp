package org.ace.insurance.system.fire.paeRate.service;

import javax.annotation.Resource;

import org.ace.insurance.system.fire.paeRate.persistence.interfaces.IPaeRateDAO;
import org.ace.insurance.system.fire.paeRate.service.interfaces.IPaeRateService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "PaeRateService")
public class PaeRateService extends BaseService implements IPaeRateService {
	@Resource(name = "PaeRateDAO")
	private IPaeRateDAO paeRateDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public double findPAERateByClassAndAge(String buildingClassId, int buildingAge) {
		double result = 0.0;
		try {
			result = paeRateDAO.findRateByClassAndAge(buildingClassId, buildingAge);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find PAE Amount by BuildingClassID and BuildingAge" + null, e);
		}
		return result;
	}

}
