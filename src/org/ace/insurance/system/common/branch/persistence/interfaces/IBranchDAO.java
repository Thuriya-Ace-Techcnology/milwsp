/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.common.branch.persistence.interfaces;

import java.util.List;

import org.ace.insurance.system.common.branch.Branch;
import org.ace.java.component.persistence.exception.DAOException;

public interface IBranchDAO {

	public Branch findByCode(String code) throws DAOException;

	public List<Branch> findAll() throws DAOException;
}
