package org.ace.insurance.life.lifePolicy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.ace.insurance.common.SystemConstants;
import org.ace.insurance.life.dao.entities.LifePolicy;
import org.ace.insurance.life.dao.entities.LifeProposal;
import org.ace.insurance.life.dto.LifePolicyDTO;
import org.ace.insurance.life.lifePolicy.service.interfaces.ILifePolicyService;
import org.ace.insurance.life.persistence.policy.interfaces.ILifePolicyDAO;
import org.ace.java.component.SystemException;
import org.ace.java.component.idgen.service.interfaces.ICustomIDGenerator;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "LifePolicyService")
public class LifePolicyService implements ILifePolicyService {
	
	@Resource(name="LifePolicyDAO")
	private ILifePolicyDAO  lifePolicyDAO;
	
	@Resource(name="CustomIDGenerator")	
	private ICustomIDGenerator customIDGenerator;

	@Transactional(propagation = Propagation.REQUIRED)
	public void addNewLifePolicy(LifePolicy lifePolicy) throws SystemException {
		try {
			lifePolicyDAO.insert(lifePolicy);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to add a new LifePolicy", e);
		}
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public LifePolicy activateLifePolicy(LifeProposal lifeProposal) {
		LifePolicy lifePolicy = null;
		try {
			lifePolicy = lifePolicyDAO.findByProposalId(lifeProposal.getId());
			setPolicyNo(lifePolicy);
			lifePolicy.setCoverageDate(lifeProposal.getEndDate());
			lifePolicy.setLastPaymentTerm(1);
			lifePolicy.setCommenmanceDate(new Date());
			lifePolicyDAO.update(lifePolicy);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to update a LifePolicy", e);
		}
		return lifePolicy;
	}
	
	private void setPolicyNo(LifePolicy lifePolicy) {
		String policyNo = null;
		String productId = lifePolicy.getPolicyInsuredPersonList().get(0).getProduct().getId().trim();	
		policyNo = customIDGenerator.getCustomNextId(SystemConstants.LIFE_POLICY_NO, productId);		
		lifePolicy.setPolicyNo(policyNo);
	}
	

	/* Find Seaman Policy By CDC No */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<LifePolicyDTO> findSeamanPolicyByCDCNo(String cdcNo, String productId) {
		List<LifePolicyDTO> result = new ArrayList<>();
		try {
			List<LifePolicy> seamanPolicyList = lifePolicyDAO.findSeamanPolicyByCdcNo(cdcNo, productId);			
			for(LifePolicy policy : seamanPolicyList) {
				result.add(new LifePolicyDTO(policy));
				
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a Seaman Policy by mobileUser (cdcNo : " + cdcNo + ")", e);
		}
		return result;
	}
	/* Find with Policy Id  to print certificate */
	@Transactional(propagation = Propagation.REQUIRED)
	public LifePolicyDTO findByPolicyId(String id) {
		LifePolicyDTO result = new LifePolicyDTO();
		try{
			result = new LifePolicyDTO(lifePolicyDAO.findSeamanPolicyByPolicyId(id));
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a Seaman Policy by( policy id : " + id + ")", e);
		}
		return result;
		
	}
	
}
