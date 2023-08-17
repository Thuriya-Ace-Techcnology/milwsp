package org.ace.insurance.system.productTypeRecords.service.interfaces;

import java.util.List;

import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.TwoCTwoPDTO.OnlineProductRecordListDTO;
import org.ace.ws.model.mobilePersonalAccidentproposal.MPAP001;
import org.ace.ws.model.mobiletravelproposal.MTP001;
import org.ace.ws.model.specialForeignTravel.SpecialForeignTravelDTO;
import org.ace.ws.model.thirdParty.TPDProposalDTO;
import org.ace.ws.model.thirdParty.ThirdPartyPremiumRecordsDTO;

public interface IProductTypeRecordsService {
	public ProductTypeRecords insert(ProductTypeRecords productTypeRecords) throws DAOException;

	public ProductTypeRecords update(ProductTypeRecords productTypeRecords) throws DAOException;

	public void delete(ProductTypeRecords productTypeRecords) throws DAOException;

	public ProductTypeRecords findById(String id) throws DAOException;

	public ProductTypeRecords findByOrderId(String orderId) throws DAOException;

	public List<OnlineProductRecordListDTO> collectAllProduct(List<ThirdPartyPremiumRecordsDTO> thirdPartyDTOList,
			List<MTP001> travelDTOList, List<MPAP001> personalAccidenDTOList, List<SpecialForeignTravelDTO> dtoList,
			List<TPDProposalDTO> thirdPartyDriverList);

}
