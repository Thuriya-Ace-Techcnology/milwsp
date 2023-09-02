package org.ace.insurance.system.twoCtwoP.persistence.interfaes;

import java.util.List;


import org.ace.insurance.system.twoCtwoP.TwoCTwoPRecords;
import org.ace.java.component.persistence.exception.DAOException;

public interface ITwoCTwoPRecordsDAO {
	public TwoCTwoPRecords insert(TwoCTwoPRecords twoC2PRecords) throws DAOException;

	public TwoCTwoPRecords update(TwoCTwoPRecords twoC2PRecords) throws DAOException;

	public void delete(TwoCTwoPRecords twoC2PRecords) throws DAOException;

	public TwoCTwoPRecords findById(String id) throws DAOException;

	public TwoCTwoPRecords findByOrderId(String orderId) throws DAOException;
	
	public List<TwoCTwoPRecords> findAllTwoCTwoPRecords() throws DAOException;

}
