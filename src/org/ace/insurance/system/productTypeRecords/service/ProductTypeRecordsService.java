package org.ace.insurance.system.productTypeRecords.service;


import javax.annotation.Resource;

import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.system.productTypeRecords.persistence.interfaces.IProductTypeRecordsDAO;
import org.ace.insurance.system.productTypeRecords.service.interfaces.IProductTypeRecordsService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;

@Service("ProductTypeRecordsService")
public class ProductTypeRecordsService extends BaseService implements IProductTypeRecordsService {

	@Resource(name = "ProductTypeRecordsDAO")
	private IProductTypeRecordsDAO productTypeRecordsDAO;

	@Override
	public ProductTypeRecords insert(ProductTypeRecords productTypeRecords) throws DAOException {
		try {
			return productTypeRecordsDAO.insert(productTypeRecords);
		} catch (DAOException e) {
			throw new DAOException(e.getErrorCode(), e.getMessage(), new Throwable());
		}
	}

	@Override
	public ProductTypeRecords update(ProductTypeRecords productTypeRecords) throws DAOException {
		try {
			return productTypeRecordsDAO.update(productTypeRecords);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update ProductTypeRecords", e);
		}
	}

	@Override
	public void delete(ProductTypeRecords productTypeRecords) throws DAOException {
		try {
			productTypeRecordsDAO.delete(productTypeRecords);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to delete ProductTypeRecords", e);
		}
	}

	@Override
	public ProductTypeRecords findById(String id) throws DAOException {
		ProductTypeRecords result = null;
		try {
			result = productTypeRecordsDAO.findById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a ProductTypeRecords (ID : " + id + ")", e);
		}
		return result;
	}

	@Override
	public ProductTypeRecords findByOrderId(String orderId) throws DAOException {
		ProductTypeRecords result = null;
		try {
			result = productTypeRecordsDAO.findByOrderId(orderId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(),
					"Faield to find a ProductTypeRecords (OrderID : " + orderId + ")", e);
		}
		return result;
	}

	/*
	 * @Override public List<OnlineProductRecordListDTO>
	 * collectAllProduct(List<ThirdPartyPremiumRecordsDTO> thirdPartyDTOList,
	 * List<MTP001> travelDTOList, List<MPAP001> personalAccidenDTOList,
	 * List<SpecialForeignTravelDTO> dtoList, List<TPDProposalDTO>
	 * thirdPartyDriverList) { List<OnlineProductRecordListDTO> recordList = new
	 * ArrayList<>(); if (thirdPartyDTOList != null) { for
	 * (ThirdPartyPremiumRecordsDTO thirdPartyDTO : thirdPartyDTOList) {
	 * OnlineProductRecordListDTO records = new OnlineProductRecordListDTO();
	 * records.setBuy_date(thirdPartyDTO.getBuy_date());
	 * records.setOrderId(thirdPartyDTO.getOrderId());
	 * records.setPeriod_from(thirdPartyDTO.getPeriod_from());
	 * records.setPeriod_to(thirdPartyDTO.getPeriod_to());
	 * records.setPremium_amount(thirdPartyDTO.getPremium_amount());
	 * records.setReceipt_date(thirdPartyDTO.getReceipt_date());
	 * records.setReceipt_no(thirdPartyDTO.getReceipt_no());
	 * records.setRta_branch(thirdPartyDTO.getRta_branch());
	 * records.setVehicle_no(thirdPartyDTO.getVehicle_no());
	 * records.setPaymentStatus(thirdPartyDTO.getPaymentStatus());
	 * records.setDiffAmount(thirdPartyDTO.getDiffAmount());
	 * records.setTwoCtwoPCharges(thirdPartyDTO.getTwoCtwoPCharges());
	 * records.setProductType("TPL Online"); recordList.add(records); } } if
	 * (travelDTOList != null) { for (MTP001 travelDTO : travelDTOList) {
	 * OnlineProductRecordListDTO records = new OnlineProductRecordListDTO();
	 * records.setBuy_date(travelDTO.getPaymentDate());
	 * records.setOrderId(travelDTO.getOrderId());
	 * records.setPaymentStatus(travelDTO.getProposalStatus().getLabel());
	 * records.setPremium_amount(travelDTO.getInsuredPersonList().get(0).getPremium(
	 * ) + ""); records.setProductType("Travel");
	 * records.setReceipt_no(travelDTO.getTransactionId()); recordList.add(records);
	 * } } if (personalAccidenDTOList != null) { for (MPAP001 personalAccidentDTO :
	 * personalAccidenDTOList) { OnlineProductRecordListDTO records = new
	 * OnlineProductRecordListDTO();
	 * records.setBuy_date(personalAccidentDTO.getPaymentDate());
	 * records.setOrderId(personalAccidentDTO.getOrderId());
	 * records.setPaymentStatus(personalAccidentDTO.getProposalStatus().getLabel());
	 * records.setPremium_amount(personalAccidentDTO.getInsuredPersonList().get(0).
	 * getPremium() + ""); records.setProductType("PA");
	 * records.setReceipt_no(personalAccidentDTO.getTransactionId());
	 * recordList.add(records); } }
	 * 
	 * if (dtoList != null) { for (SpecialForeignTravelDTO dto : dtoList) {
	 * OnlineProductRecordListDTO records = new OnlineProductRecordListDTO();
	 * LocalDate paymentDate =
	 * Instant.ofEpochMilli(dto.getPaymentDate()).atZone(ZoneId.systemDefault())
	 * .toLocalDate(); LocalDate dateOfBirth =
	 * Instant.ofEpochMilli(dto.getDateOfBirth()).atZone(ZoneId.systemDefault())
	 * .toLocalDate(); records.setBuy_date(paymentDate.getDayOfMonth() + " " +
	 * paymentDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " "
	 * + paymentDate.getYear()); records.setOrderId(dto.getOrderId());
	 * records.setPaymentStatus(dto.getProposalStatus().getLabel());
	 * records.setPremium_amount(dto.getPremium() + "");
	 * records.setDiffAmount(dto.getTransactionFees() + "");
	 * records.setJourneyFrom(dto.getJourneyFrom());
	 * records.setFullName(dto.getFullName());
	 * records.setPassportNo(dto.getPassportNo());
	 * records.setPeriodMonth(dto.getPeriodMonth());
	 * records.setPremium_amount(dto.getPremium() + "");
	 * records.setTransactionFees(dto.getTransactionFees());
	 * records.setProductType("SPECAILFOREIGNTRAVELLER");
	 * records.setAge(dto.getAge());
	 * records.setDateOfBirth(dateOfBirth.getDayOfMonth() + " " +
	 * dateOfBirth.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " "
	 * + dateOfBirth.getYear()); records.setPoloicyNo(dto.getPolicyNo());
	 * recordList.add(records); } }
	 * 
	 * if (thirdPartyDriverList != null) { TPDInfoDTO driverInfoDto; for
	 * (TPDProposalDTO dto : thirdPartyDriverList) { OnlineProductRecordListDTO
	 * records = new OnlineProductRecordListDTO();
	 * records.setBuy_date(dto.getPaymentDate());
	 * records.setOrderId(dto.getOrderId());
	 * records.setPaymentStatus(dto.getProposalStatus().getLabel());
	 * records.setPremium_amount(String.valueOf(dto.getDriverInfoList().get(0).
	 * getPremium())); records.setProductType("THIRDPARTYDRIVER");
	 * records.setTransactionFees(0); if(dto.getDriverInfoList() != null) {
	 * driverInfoDto = dto.getDriverInfoList().get(0);
	 * records.setPeriod_from(driverInfoDto.getStartDate());
	 * records.setPeriod_to(driverInfoDto.getEndDate()); } recordList.add(records);
	 * 
	 * } } return recordList; }
	 */

}
