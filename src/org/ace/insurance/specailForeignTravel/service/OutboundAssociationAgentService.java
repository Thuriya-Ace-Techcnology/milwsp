package org.ace.insurance.specailForeignTravel.service;

import java.util.Optional;

import javax.annotation.Resource;

import org.ace.insurance.specailForeignTravel.OutboundAssociationAgent;
import org.ace.insurance.specailForeignTravel.persistence.interfaces.IOutboundAssociationAgentDAO;
import org.ace.insurance.specailForeignTravel.service.interfaces.IOutboundAssociationAgentService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("OutboundAssociationAgentService")
public class OutboundAssociationAgentService extends BaseService implements IOutboundAssociationAgentService {

	private static final Logger logger = Logger.getLogger(OutboundAssociationAgentService.class);

	@Resource(name = "OutboundAssociationAgentDAO")
	private IOutboundAssociationAgentDAO outboundAssociationAgentDAO;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Optional<OutboundAssociationAgent> findByLicenceNoAndPassword(String licenceNo, String password) {
		Optional<OutboundAssociationAgent> result;
		try {
			result = outboundAssociationAgentDAO.findByLicenceNoAndPassword(licenceNo, password);
			logger.info("OutboundAssociationAgentService :: findByLicenceNoAndPassword method invoked :: ");
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), String.format("Faield to find OutboundAssociationAgent by licenceNo %s and password %s", licenceNo, password), e);
		}
		return result;
	}
}
