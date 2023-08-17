package org.ace.insurance.system.twoCtwoP.service;

import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.twoCtwoP.TwoCTwoPRecords;
import org.ace.insurance.system.twoCtwoP.persistence.interfaes.ITwoCTwoPRecordsDAO;
import org.ace.insurance.system.twoCtwoP.service.interfaces.ITwoCTwoPRecordsService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.ace.ws.model.TwoCTwoPDTO.TwoCTwoPResponseDTO;
import org.springframework.stereotype.Service;

@Service("TwoCTwoPRecordsService")
public class TwoCTwoPRecordsService extends BaseService implements ITwoCTwoPRecordsService{

	@Resource(name = "TwoCTwoPRecordsDAO")
	private ITwoCTwoPRecordsDAO twoC2PRecordsDAO;
	
	@Override
	public TwoCTwoPRecords insert(TwoCTwoPRecords twoC2PRecords) throws DAOException {
		try {
			twoC2PRecords.setPrefix(getPrefix(TwoCTwoPRecords.class));
			return twoC2PRecordsDAO.insert(twoC2PRecords);
		} catch (DAOException e) {
			throw new DAOException(e.getErrorCode(), e.getMessage(), new Throwable());
		}
	}

	@Override
	public TwoCTwoPRecords update(TwoCTwoPRecords twoC2PRecords) throws DAOException {
		try {
			return twoC2PRecordsDAO.update(twoC2PRecords);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update twoC2PRecords", e);
		}
	}

	@Override
	public void delete(TwoCTwoPRecords twoC2PRecords) throws DAOException {
		try {
			twoC2PRecordsDAO.delete(twoC2PRecords);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to delete twoC2PRecordsDAO", e);
		}
	}

	@Override
	public TwoCTwoPRecords findById(String id) throws DAOException {
		TwoCTwoPRecords result = null;
		try {
			result = twoC2PRecordsDAO.findById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a TwoCTwoPRecords (ID : " + id + ")",
					e);
		}
		return result;
	}

	@Override
	public List<TwoCTwoPRecords> findAllTwoCTwoPRecords() throws DAOException {
		List<TwoCTwoPRecords> result = null;
		try {
			result = twoC2PRecordsDAO.findAllTwoCTwoPRecords();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a TwoCTwoPRecords ", e);
		}
		return result;
	}

	@Override
	public TwoCTwoPResponseDTO findByOrderId(String orderId) throws DAOException {
		TwoCTwoPResponseDTO twoCTwoPResponseDTO = null;
		TwoCTwoPRecords result = null;
		try {
			result = twoC2PRecordsDAO.findByOrderId(orderId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a TwoCTwoPRecords (OrderID : " + orderId + ")",
					e);
		}
		
		if(result != null) {
			twoCTwoPResponseDTO = new TwoCTwoPResponseDTO();
			twoCTwoPResponseDTO.setAmount(result.getAmount());
			twoCTwoPResponseDTO.setOrder_id(result.getOrder_id());
			twoCTwoPResponseDTO.setCurrency(result.getCurrency());
			twoCTwoPResponseDTO.setApproval_code(result.getApproval_code());
			twoCTwoPResponseDTO.setPayment_status(result.getPayment_status());
			twoCTwoPResponseDTO.setTransaction_ref(result.getTransaction_ref());
			twoCTwoPResponseDTO.setPayment_channel(result.getPayment_channel());
			twoCTwoPResponseDTO.setRequest_timestamp(result.getRequest_timestamp());
			twoCTwoPResponseDTO.setChannel_response_code(result.getChannel_response_code());
			twoCTwoPResponseDTO.setChannel_response_desc(result.getChannel_response_desc());
			twoCTwoPResponseDTO.setProcess_by(result.getProcess_by());
		}
		return twoCTwoPResponseDTO;
	}
	

}
