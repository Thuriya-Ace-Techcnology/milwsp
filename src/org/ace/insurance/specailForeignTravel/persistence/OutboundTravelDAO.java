package org.ace.insurance.specailForeignTravel.persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.TourismType;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravel;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravelHistory;
import org.ace.insurance.specailForeignTravel.persistence.interfaces.IOutboundTravelDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("OutboundTravelDAO")
public class OutboundTravelDAO extends BasicDAO implements IOutboundTravelDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<SpecialForeignTravel> findOutboundTravelByProposalStatus(TourismType tourismType) {
		List<SpecialForeignTravel> result = null;
		try {
			Query q = em.createNamedQuery("SpecialForeignTravel.findByProposalStatusAndTourismType");
			q.setParameter("proposalStatus", ProposalStatus.ISSUED);
			q.setParameter("tourismType",tourismType);
			q.setParameter("isCovert", false);
			result =  q.getResultList();
			em.flush();
		} catch (NoResultException pe) {
			throw translate("Failed to find SpecialForeignTravel by proposal status", pe);
			
		}
		return result;
	}

	@Override
	public void updateResponseStatusByOrderId(String orderId) {
		try {
			Query q = em.createNamedQuery("SpecialForeignTravel.updateConvertStatusByOrderId");
			q.setParameter("convert",true);
			q.setParameter("orderId", orderId);
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update SpecialForeignTravel", pe);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<SpecialForeignTravel> findOutboundTravelByOrderId(String orderId) {
		List<SpecialForeignTravel> result = null;
		try {
			Query q = em.createNamedQuery("SpecialForeignTravel.findByOrderId");
			q.setParameter("orderId", orderId);
			q.setParameter("deleteStatus", false);
			result =  q.getResultList();
			em.flush();
		} catch (NoResultException pe) {
			throw translate("Failed to find SpecialForeignTravel by order id", pe);
			
		}
		return result;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public SpecialForeignTravel findOutboundTravelByOrderSingleId(String orderId) {
		SpecialForeignTravel result = null;
		try {
			Query q = em.createNamedQuery("SpecialForeignTravel.findByOrderId");
			q.setParameter("orderId", orderId);
			q.setParameter("deleteStatus", false);
			result =  (SpecialForeignTravel) q.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			throw translate("Failed to find SpecialForeignTravel by order id", pe);
			
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateSpecialForeignTravel(SpecialForeignTravel travel) {
		try {
			Query q = em.createNamedQuery("SpecialForeignTravel.updateByNative");
			q.setParameter("registrationNo",travel.getRegistrationNo());
			q.setParameter("transactionFees",travel.getTransactionFees());
			q.setParameter("premium",travel.getPremium());
			q.setParameter("periodMonth",travel.getPeriodMonth());
			q.setParameter("policyNo",travel.getPolicyNo());
			q.setParameter("currencyId",travel.getCurrencyId());
			q.setParameter("sumInsured",travel.getSumInsured());
			q.setParameter("deleteStatus",travel.isDeleteStatus());
			q.setParameter("isConvert",travel.isConvert());
			q.setParameter("tpaFee",travel.getTpaFee());
			q.setParameter("agentCommission",travel.getAgentCommission());
			q.setParameter("agentId",travel.getAgentId());
			q.setParameter("agentName",travel.getAgentName());
			q.setParameter("orderId",travel.getOrderId());
			q.setParameter("submittedDate",travel.getSubmittedDate());
			q.setParameter("paymentDate",travel.getPaymentDate());
			q.setParameter("activedPolicyStartDate",travel.getActivedPolicyStartDate());
			q.setParameter("activedPolicyEndDate",travel.getActivedPolicyEndDate());
			q.setParameter("saleChannelType",travel.getSaleChannelType());
			q.setParameter("responseStatus",travel.getResponseStatus());
			q.setParameter("paymentGateway",travel.getPaymentGateway());
			q.setParameter("proposalStatus",travel.getProposalStatus());
			q.setParameter("buyerPlatForm",travel.getBuyerPlatForm());
			q.setParameter("tourismType",travel.getTourismType());
			q.setParameter("version",travel.getVersion());
			q.setParameter("countryCode",travel.getTravellerInfo().getCountryCode());
			q.setParameter("fullName",travel.getTravellerInfo().getFullName());
			q.setParameter("dateOfBirth",travel.getTravellerInfo().getDateOfBirth());
			q.setParameter("gender",travel.getTravellerInfo().getGender());
			q.setParameter("passportNo",travel.getTravellerInfo().getPassportNo());
			q.setParameter("contactNo",travel.getTravellerInfo().getContactNo());
			q.setParameter("myanmarAddress",travel.getTravellerInfo().getMyanmarAddress());
			q.setParameter("residentAddress",travel.getTravellerInfo().getResidentAddress());
			q.setParameter("age",travel.getTravellerInfo().getAge());
			q.setParameter("email",travel.getTravellerInfo().getEmail());
			q.setParameter("passportExpireDate",travel.getTravellerInfo().getPassportExpireDate());
			q.setParameter("residentCountry",travel.getTravellerInfo().getResidentCountry());
			q.setParameter("journeyFrom",travel.getTravellerInfo().getJourneyFrom());
			q.setParameter("journeyTo",travel.getTravellerInfo().getJourneyTo());
			q.setParameter("fatherName",travel.getTravellerInfo().getFatherName());
			q.setParameter("race",travel.getTravellerInfo().getRace());
			q.setParameter("maritalStatus",travel.getTravellerInfo().getMaritalStatus());
			q.setParameter("departureDate",travel.getTravellerInfo().getDepartureDate());
			q.setParameter("occupation",travel.getTravellerInfo().getOccupation());
			q.setParameter("foreignContactNo",travel.getTravellerInfo().getForeignContactNo());
			q.setParameter("benName",travel.getBeneficaryInfo().getBenName());
			q.setParameter("benDateOfBirth",travel.getBeneficaryInfo().getBenDateOfBirth());
			q.setParameter("benNIDNo",travel.getBeneficaryInfo().getBenNIDNo());
			q.setParameter("benRelationship", travel.getBeneficaryInfo().getBenRelationship());
			q.setParameter("benResidentAddress", travel.getBeneficaryInfo().getBenResidentAddress());
			q.setParameter("benContactNo", travel.getBeneficaryInfo().getBenContactNo());
			q.setParameter("benEmail", travel.getBeneficaryInfo().getBenEmail());
			q.setParameter("benCode", travel.getBeneficaryInfo().getBenCode());
			q.setParameter("benResidentCountry", travel.getBeneficaryInfo().getBenResidentCountry());
			q.setParameter("cAge", travel.getChildInfo().getcAge());
			q.setParameter("cName", travel.getChildInfo().getcName());
			q.setParameter("cGender", travel.getChildInfo().getcGender());
			q.setParameter("cRelation", travel.getChildInfo().getcRelation());
			q.setParameter("cBirthDate", travel.getChildInfo().getcBirthDate());
			q.setParameter("cOtherName1", travel.getChildInfo().getcOtherName1());
			q.setParameter("cOtherName2", travel.getChildInfo().getcOtherName2());
			q.setParameter("cStatus", travel.getChildInfo().iscStatus());
			q.setParameter("recorder", travel.getRecorder());
			q.setParameter("id", travel.getId());
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update SpecialForeignTravel", pe);
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addNewSpeicalForeignTravelHistory(SpecialForeignTravelHistory travelHistory) {
		try {
			em.persist(travelHistory);
			em.flush();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public SpecialForeignTravel findById(String id) {
		SpecialForeignTravel result = null;
		try {
			Query q = em.createNamedQuery("SpecialForeignTravel.findById");
			q.setParameter("id", id);
			q.setParameter("deleteStatus", false);
			result =  (SpecialForeignTravel) q.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			throw translate("Failed to find SpecialForeignTravel by id ", pe);
		}
		return result;
	}

	

	

	


}
