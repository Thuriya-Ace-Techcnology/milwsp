package org.ace.insurance.specailForeignTravel.persistence;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.specailForeignTravel.OutboundPercent;
import org.ace.insurance.specailForeignTravel.OutboundTPAPremiumRate;
import org.ace.insurance.specailForeignTravel.persistence.interfaces.IOutboundTPAPremiumRateDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.springframework.stereotype.Repository;

@Repository("OutboundTPAPremiumRateDAO")
public class OutboundTPAPremiumRateDAO extends BasicDAO implements IOutboundTPAPremiumRateDAO {

	@Override
	public OutboundTPAPremiumRate findByName(OutboundPercent name) {
		OutboundTPAPremiumRate result;
		try {
			Query q = em.createNamedQuery("OutboundTPAPremiumRate.findByName");
			q.setParameter("name", name);
			result =(OutboundTPAPremiumRate) q.getSingleResult();
		} catch (PersistenceException pe) {
			throw translate("Failed to find OutboundTPAPremiumRate by Name", pe);
		}
		
		return result;
	}

	
	

}
