package org.ace.insurance.specailForeignTravel.service.interfaces;


import java.io.IOException;
import java.util.List;

import org.ace.insurance.common.TourismType;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravel;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravelHistory;
import org.ace.ws.model.outboundTravel.OutboundTravelDTO;
import org.apache.http.client.ClientProtocolException;

public interface IOutboundTravelService {

	public void batchOutboundProcess(TourismType tourismType);
	
	public void calltoOthersServerAPIForOutbound(OutboundTravelDTO dto) throws ClientProtocolException, IOException;
	
	public void updateResponseStatusByOrderId(String orderId);
	
	public List<SpecialForeignTravel> findByOrderId(OutboundTravelDTO dto);
	
	
	
}
