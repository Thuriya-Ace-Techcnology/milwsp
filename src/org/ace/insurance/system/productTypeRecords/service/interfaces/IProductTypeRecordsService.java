package org.ace.insurance.system.productTypeRecords.service.interfaces;


import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.java.component.persistence.exception.DAOException;

public interface IProductTypeRecordsService {
	public ProductTypeRecords insert(ProductTypeRecords productTypeRecords) throws DAOException;

	public ProductTypeRecords update(ProductTypeRecords productTypeRecords) throws DAOException;

	public void delete(ProductTypeRecords productTypeRecords) throws DAOException;

	public ProductTypeRecords findById(String id) throws DAOException;

	public ProductTypeRecords findByOrderId(String orderId) throws DAOException;

	/*
	 * public List<OnlineProductRecordListDTO>
	 * collectAllProduct(List<ThirdPartyPremiumRecordsDTO> thirdPartyDTOList,
	 * List<MTP001> travelDTOList, List<MPAP001> personalAccidenDTOList,
	 * List<SpecialForeignTravelDTO> dtoList, List<TPDProposalDTO>
	 * thirdPartyDriverList);
	 */

}
