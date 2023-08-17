package org.ace.insurance.system.twoCtwoP.service.interfaces;

import java.util.List;

import org.ace.insurance.system.twoCtwoP.TwoCTwoPRecords;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.TwoCTwoPDTO.TwoCTwoPResponseDTO;

public interface ITwoCTwoPRecordsService {
	public TwoCTwoPRecords insert(TwoCTwoPRecords twoC2PRecords) throws DAOException;

	public TwoCTwoPRecords update(TwoCTwoPRecords twoC2PRecords) throws DAOException;

	public void delete(TwoCTwoPRecords twoC2PRecords) throws DAOException;

	public TwoCTwoPRecords findById(String id) throws DAOException;
	
	public TwoCTwoPResponseDTO findByOrderId(String orderId) throws DAOException;

	public List<TwoCTwoPRecords> findAllTwoCTwoPRecords() throws DAOException;
	
}
