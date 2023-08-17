package org.ace.insurance.specailForeignTravel.persistence.interfaces;

import java.util.List;

import org.ace.insurance.common.TourismType;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravel;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravelHistory;

public interface IOutboundTravelDAO {

	public List<SpecialForeignTravel> findOutboundTravelByProposalStatus(TourismType tourismType);
	
	public void updateResponseStatusByOrderId(String orderId);
		
	public List<SpecialForeignTravel> findOutboundTravelByOrderId(String orderId);
	
	SpecialForeignTravel findOutboundTravelByOrderSingleId(String orderId);
	
	public SpecialForeignTravel findById(String id);
	
	public void updateSpecialForeignTravel(SpecialForeignTravel travel);
	
	public void addNewSpeicalForeignTravelHistory(SpecialForeignTravelHistory travelHistory);
	
}
