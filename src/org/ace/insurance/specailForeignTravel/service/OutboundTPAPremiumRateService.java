package org.ace.insurance.specailForeignTravel.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.ace.insurance.specailForeignTravel.OutboundPercent;
import org.ace.insurance.specailForeignTravel.OutboundTPAPremiumRate;
import org.ace.insurance.specailForeignTravel.persistence.interfaces.IOutboundTPAPremiumRateDAO;
import org.ace.insurance.specailForeignTravel.service.interfaces.IOutboundTPAPremiumRateService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.ace.ws.model.premiumCal.OutboundResultPremium;
import org.ace.ws.model.premiumCal.ResultPremium;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("OutboundTPAPremiumRateService")
public class OutboundTPAPremiumRateService extends BaseService implements IOutboundTPAPremiumRateService {

	private static final Logger logger = Logger.getLogger(OutboundTPAPremiumRateService.class);

	@Resource(name = "OutboundTPAPremiumRateDAO")
	private IOutboundTPAPremiumRateDAO outboundTPAPremiumRateDAO;
	
	@Override
	public OutboundTPAPremiumRate findByName(OutboundPercent name) {
		OutboundTPAPremiumRate percent;
		try {
			percent = outboundTPAPremiumRateDAO.findByName(name);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a outbound travel  ", e);
		}
		return percent;
	}

	@Override
	public List<OutboundResultPremium> findOutboundPremium(List<ResultPremium> resultList) {
		
		double tpa = findByName(OutboundPercent.TPAFEE).getTpaPercent();
		double ac = findByName(OutboundPercent.AGENTCOMMISSION).getTpaPercent();
		 
		 return resultList.stream().filter(Objects::nonNull)
					.map(a -> new OutboundResultPremium(a.getId(), a.getName(), a.getPremium(),
							a.getPremium() * tpa / 100, a.getPremium() * ac / 100,a.getMainPremium()))
					.map(a -> new OutboundResultPremium(a.getId(), a.getName(), a.getPremium() - (a.getTpaFee() + a.getAgentCommission()), a.getTpaFee(), a.getAgentCommission(),a.getPremium()))
					.collect(Collectors.toList());
	}

}
