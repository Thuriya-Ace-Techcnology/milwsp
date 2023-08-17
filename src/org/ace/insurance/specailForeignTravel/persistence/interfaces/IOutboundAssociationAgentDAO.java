package org.ace.insurance.specailForeignTravel.persistence.interfaces;


import java.util.Optional;

import org.ace.insurance.specailForeignTravel.OutboundAssociationAgent;

public interface IOutboundAssociationAgentDAO {
	 Optional<OutboundAssociationAgent> findByLicenceNoAndPassword(String licenceNo, String password); 
}
