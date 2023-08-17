package org.ace.insurance.specailForeignTravel.service.interfaces;

import java.util.List;

import org.ace.insurance.common.TourismType;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravel;
import org.ace.ws.model.specialForeignTravel.SpecialForeignTravelDTO;

public interface ISpecialForeignTravelService {
	public SpecialForeignTravel addNewOrUpdateSpecailForeignTravel(SpecialForeignTravel specialForeignTravel);

	public List<SpecialForeignTravel> findByOrderId(String orderId);

	public List<SpecialForeignTravelDTO> findProposalByPolicyNoOrPassportNo(String countryCode,String passportNo,TourismType tourismType);

	public SpecialForeignTravel updateByPaymentStatus(String order_id,String processBy);
	
	public SpecialForeignTravel outboundUpdateByPaymentStatus(String order_id,String processBy);

	public SpecialForeignTravelDTO findById(String id);

	public List<SpecialForeignTravelDTO> finByFromToDate(String fromDate, String toDate,TourismType tourismType);

	public void batchProcess(TourismType tourismType);
}
