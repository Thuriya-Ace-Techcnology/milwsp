package org.ace.insurance.specailForeignTravel.persistence.interfaces;

import java.util.List;

import org.ace.insurance.common.TourismType;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravel;
import org.ace.java.component.persistence.exception.DAOException;

public interface ISpecialForeignTravelDAO {
	public void insert(SpecialForeignTravel spForeignTravel) throws DAOException;

	public List<SpecialForeignTravel> findByOrderId(String orderId);

	public void update(SpecialForeignTravel specialForeignTravel);

	public List<SpecialForeignTravel> findProposalByPolicyNoOrPassportNo(String countryCode,String passportNo,TourismType tourismType);

	public SpecialForeignTravel updateByPaymentStatus(String order_id,String processBy,String proposalNo);

	public SpecialForeignTravel findById(String id);

	public List<SpecialForeignTravel> findByFromToDate(String fromDate, String toDate,TourismType tourismType);

	public List<SpecialForeignTravel> findOnlineBillerByPaymentStatus(TourismType tourismType);

	public void updateResponseStatusByOrderId(String orderId);
}
