/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.common.bank.persistence.interfaces;

import java.util.List;

import org.ace.insurance.common.Bank;
import org.ace.java.component.persistence.exception.DAOException;

public interface IBankDAO {


	public Bank findById(String id) throws DAOException;


}
