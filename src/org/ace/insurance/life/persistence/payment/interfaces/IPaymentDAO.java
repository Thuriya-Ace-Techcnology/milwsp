package org.ace.insurance.life.persistence.payment.interfaces;

import java.util.List;

import org.ace.insurance.life.dao.entities.Payment;
import org.ace.insurance.life.dao.entities.TLF;
import org.ace.java.component.persistence.exception.DAOException;

public interface IPaymentDAO {
	
	public Payment update(Payment payment) throws DAOException;
	
	public Payment insert(Payment payment) throws DAOException;
	
	public String findCheckOfAccountNameByCode(String accountName, String branchId, String currencyId) throws DAOException;
	
	
	public String findCCOAByCode(String acCode, String branchId, String currencyId) throws DAOException;
	
	public void insertTLFList(List<TLF> tlfList) throws DAOException;


}
