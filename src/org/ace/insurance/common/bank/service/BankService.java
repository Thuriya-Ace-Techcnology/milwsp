/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.common.bank.service;

import java.util.List;


import javax.annotation.Resource;

import org.ace.insurance.common.Bank;
import org.ace.insurance.common.bank.persistence.interfaces.IBankDAO;
import org.ace.insurance.common.bank.service.interfaces.IBankService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "BankService")
public class BankService extends BaseService implements IBankService {

	@Resource(name = "BankDAO")
	private IBankDAO bankDAO;


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Bank findBankById(String id) {
		Bank result = null;
		try {
			result = bankDAO.findById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find a Bank (ID : " + id + ")", e);
		}
		return result;
	}


}
