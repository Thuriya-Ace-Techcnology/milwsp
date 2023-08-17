package org.ace.insurance.specailForeignTravel.service.interfaces;


import java.util.Optional;

import org.ace.insurance.specailForeignTravel.OutboundAssociationAgent;

public interface IOutboundAssociationAgentService {
	 Optional<OutboundAssociationAgent> findByLicenceNoAndPassword(String licenceNo, String password); 

}
