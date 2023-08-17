package org.ace.insurance.specailForeignTravel.persistence;

import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.specailForeignTravel.OutboundAssociationAgent;
import org.ace.insurance.specailForeignTravel.persistence.interfaces.IOutboundAssociationAgentDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.springframework.stereotype.Repository;

@Repository("OutboundAssociationAgentDAO")
public class OutboundAssociationAgentDAO extends BasicDAO implements IOutboundAssociationAgentDAO{

	@Override
	public Optional<OutboundAssociationAgent> findByLicenceNoAndPassword(String licenceNo, String password) {
		OutboundAssociationAgent result = null;
		try {
			Query q = em.createNamedQuery("OutboundAssociationAgent.findByLicenceNoAndPassword");
			q.setParameter("licenceNo", licenceNo);
			q.setParameter("password", password);
			result = (OutboundAssociationAgent) q.getSingleResult();
		}catch (PersistenceException e) {
			return Optional.ofNullable(result);
		}
		return Optional.of(result);
	}
	
}
