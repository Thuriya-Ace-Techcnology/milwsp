package org.ace.insurance.specailForeignTravel.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.PaymentGateway;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.TableName;
import org.ace.insurance.common.TourismType;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravel;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravellerInfo;
import org.ace.insurance.specailForeignTravel.SpecialTravellerBeneficaryInfo;
import org.ace.insurance.specailForeignTravel.persistence.interfaces.ISpecialForeignTravelDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("SpecialForeignTravelDAO")
public class SpecialForeignTravelDAO extends BasicDAO implements ISpecialForeignTravelDAO{

	private void insertProcessLog(SpecialForeignTravel spForeignTravel) {
		insertProcessLog(TableName.SPECIAL_FOREIGN_TRAVEL, spForeignTravel.getId());
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(SpecialForeignTravel spForeignTravel) throws DAOException {
		try {
			em.persist(spForeignTravel);
			//insertProcessLog(spForeignTravel);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert SpecialForeignTravel", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<SpecialForeignTravel> findByOrderId(String orderId) {
		List<SpecialForeignTravel> result;
		try {
			Query q = em.createNamedQuery("SpecialForeignTravel.findByOrderId");
			q.setParameter("orderId", orderId);
			q.setParameter("deleteStatus", false);
			result = q.getResultList();
		} catch (PersistenceException pe) {
			throw translate("Failed to find SpecialForeignTravel by orderId", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(SpecialForeignTravel specialForeignTravel) {
		try {
			em.merge(specialForeignTravel);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update SpecialForeignTravel by TransactionID", pe);
		}
	}

	@Override
	public List<SpecialForeignTravel> findProposalByPolicyNoOrPassportNo(String countryCode,String passportNo,TourismType tourismType) {
		List<SpecialForeignTravel> result = null;
		try {
			Query q = em.createNamedQuery("SpecialForeignTravel.findByPassportNoAndCountryCode");
			q.setParameter("deleteStatus", false);
			q.setParameter("proposalStatus", ProposalStatus.ISSUED);
			q.setParameter("passportNo", passportNo);
			q.setParameter("countryCode", countryCode.trim());
			q.setParameter("tourismType", tourismType);
			result = q.getResultList();
		} catch (PersistenceException pe) {
			throw translate("Failed to find SpecialForeignTravel by orderId", pe);
		}catch (Exception pe) {
			System.out.println(pe.getMessage());
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public SpecialForeignTravel updateByPaymentStatus(String order_id,String processBy,String proposalNo) {
		SpecialForeignTravel result = new SpecialForeignTravel();
		try {
			
			Query q = em.createNamedQuery("SpecialForeignTravel.updateByPaymentStatus");
			q.setParameter("proposalStatus", ProposalStatus.ISSUED);
			q.setParameter("paymentDate", new Date());
			//q.setParameter("gateWay", PaymentGateway.valueOf(processBy));
			q.setParameter("orderId", order_id);
			q.setParameter("policyNo", proposalNo);
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update SpecialForeignTravel", pe);
		}

		return result;
	}

	@Override
	public SpecialForeignTravel findById(String id) {
		SpecialForeignTravel result;
		try {
			Query q = em.createNamedQuery("SpecialForeignTravel.findById");
			q.setParameter("deleteStatus", false);
			q.setParameter("id", id);
			result =(SpecialForeignTravel) q.getSingleResult();
		} catch (PersistenceException pe) {
			throw translate("Failed to find SpecialForeignTravel by Id", pe);
		}
		return result;
	}

	@Override
	public List<SpecialForeignTravel> findByFromToDate(String fromDate, String toDate,TourismType tourismType) {
		List<Object[]> resultO = null;
		List<SpecialForeignTravel> result = null;
		try {
			Query q = em.createNativeQuery("SELECT t.* FROM SPECIAL_FOREIGN_TRAVEL t WHERE t.paymentDate >= '"+fromDate+"' and t.paymentDate <= '"+toDate+" 23:59:59' and t.deleteStatus = '"+false+"' and t.proposalStatus = '"+ProposalStatus.ISSUED+"' and t.tourismType = '"+tourismType+"' order by t.paymentDate desc");
			resultO = q.getResultList();
			if(resultO != null && !resultO.isEmpty()) {
				result = new ArrayList<>();
				for(Object[] o : resultO) {
					SpecialForeignTravel sp = new SpecialForeignTravel();
					SpecialForeignTravellerInfo spInfo = new SpecialForeignTravellerInfo();
					SpecialTravellerBeneficaryInfo benInfo = new SpecialTravellerBeneficaryInfo();
					spInfo.setFullName(o[28].toString());
					spInfo.setJourneyFrom(o[30].toString());
					spInfo.setPassportNo(o[33].toString());
					spInfo.setDateOfBirth((Date)o[26]);
					spInfo.setAge((int)o[23]);
					sp.setOrderId(o[5].toString());
					sp.setPaymentDate((Date)o[13]);
					sp.setPaymentGateway(PaymentGateway.valueOf(o[7].toString()));
					sp.setPeriodMonth((int)o[8]);
					sp.setPolicyNo(o[9].toString());
					sp.setPremium((double)o[10]);
					sp.setProposalStatus(ProposalStatus.valueOf(o[11].toString()));
					sp.setTransactionFees((double)o[14]);
					sp.setTravellerInfo(spInfo);
					sp.setBeneficaryInfo(benInfo);
					result.add(sp);
				}
			}
		} catch (PersistenceException pe) {
			throw translate("Failed to find SpecialForeignTravel by orderId", pe);
		} 
		return result;
	}

	@Override
	public List<SpecialForeignTravel> findOnlineBillerByPaymentStatus(TourismType tourismType) {
		List<SpecialForeignTravel> result = null;
		try {
			Query q = em.createNamedQuery("SpecialForeignTravel.findByProposalStatusAndTourismType");
			q.setParameter("proposalStatus", ProposalStatus.ISSUED);
			q.setParameter("tourismType",tourismType);
			q.setParameter("isCovert", false);
			result =  q.getResultList();
			em.flush();
		} catch (NoResultException pe) {
			
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

}
