/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.common.bank.persistence;



import javax.persistence.PersistenceException;


import org.ace.insurance.common.Bank;
import org.ace.insurance.common.bank.persistence.interfaces.IBankDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("BankDAO")
public class BankDAO extends BasicDAO implements IBankDAO {


	@Transactional(propagation = Propagation.REQUIRED)
	public Bank findById(String id) throws DAOException {
		Bank result = null;
		try {
			result = em.find(Bank.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Bank", pe);
		}
		return result;
	}

}
