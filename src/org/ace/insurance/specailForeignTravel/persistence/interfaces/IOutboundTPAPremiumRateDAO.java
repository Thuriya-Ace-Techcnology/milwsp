package org.ace.insurance.specailForeignTravel.persistence.interfaces;

import org.ace.insurance.specailForeignTravel.OutboundPercent;
import org.ace.insurance.specailForeignTravel.OutboundTPAPremiumRate;

public interface IOutboundTPAPremiumRateDAO {

	public OutboundTPAPremiumRate findByName(OutboundPercent name);
}
