/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.common.branch.service;

import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.common.branch.Branch;
import org.ace.insurance.system.common.branch.persistence.interfaces.IBranchDAO;
import org.ace.insurance.system.common.branch.service.interfaces.IBranchService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "BranchService")
public class BranchService extends BaseService implements IBranchService {

	@Resource(name = "BranchDAO")
	private IBranchDAO branchDAO;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Branch> findAllBranch() {
		List<Branch> result = null;
		try {
			result = branchDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find all of Branch)", e);
		}
		return result;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Branch findBranchById(String branchId) {
		Branch result = null;
		try {
			result = branchDAO.findById(branchId);
		}catch(DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find Branch)", e);
		}
		
		return result;
	}

}