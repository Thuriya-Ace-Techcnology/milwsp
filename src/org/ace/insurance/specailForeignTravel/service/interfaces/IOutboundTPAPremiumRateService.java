package org.ace.insurance.specailForeignTravel.service.interfaces;

import java.util.List;

import org.ace.insurance.specailForeignTravel.OutboundPercent;
import org.ace.insurance.specailForeignTravel.OutboundTPAPremiumRate;
import org.ace.ws.model.premiumCal.OutboundResultPremium;
import org.ace.ws.model.premiumCal.ResultPremium;

public interface IOutboundTPAPremiumRateService {

	public OutboundTPAPremiumRate findByName(OutboundPercent name);
	
	List<OutboundResultPremium> findOutboundPremium(List<ResultPremium> resultList);
}
