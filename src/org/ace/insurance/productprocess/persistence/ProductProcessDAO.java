package org.ace.insurance.productprocess.persistence;

import java.util.EnumSet;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.productprocess.ActiveStatus;
import org.ace.insurance.productprocess.ProductProcess;
import org.ace.insurance.productprocess.ProductProcessDTO;
import org.ace.insurance.productprocess.persistence.interfaces.IProductProcessDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 * 
 ***************************************************************************************/

@Repository("ProductProcessDAO")
public class ProductProcessDAO extends BasicDAO implements IProductProcessDAO {

	/**
	 * @see org.ace.insurance.medical.productprocess.persistence.interfaces.IProductProcessDAO
	 *      #insert(org.ace.insurance.medical.productprocess.ProductProcess)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(ProductProcess productProcess) throws DAOException {
		try {
			em.persist(productProcess);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert ProductProcess", pe);
		}
	}

	/**
	 * @see org.ace.insurance.medical.productprocess.persistence.interfaces.IProductProcessDAO
	 *      #update(org.ace.insurance.medical.productprocess.ProductProcess)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(ProductProcess productProcess) throws DAOException {
		try {
			em.merge(productProcess);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update ProductProcess", pe);
		}
	}

	/**
	 * @see org.ace.insurance.medical.productprocess.persistence.interfaces.IProductProcessDAO
	 *      #delete(org.ace.insurance.medical.productprocess.ProductProcess)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(ProductProcess productProcess) throws DAOException {
		try {
			productProcess = em.merge(productProcess);
			em.remove(productProcess);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update ProductProcess", pe);
		}
	}

	/**
	 * @see org.ace.insurance.medical.productProcess.persistence.interfaces.IProductProcessDAO
	 *      #findAll()
	 * @return List of ProductProcess
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ProductProcess> findAll() throws DAOException {
		List<ProductProcess> result = null;
		try {
			Query q = em.createNamedQuery("ProductProcess.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of ProductProcess", pe);
		}
		return result;
	}

	/**
	 * @see org.ace.insurance.medical.productProcess.persistence.interfaces.IProductProcessDAO
	 *      #findAll()
	 * @return List of ProductProcess
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ProductProcess> findProPByActiveStatus() throws DAOException {
		List<ProductProcess> result = null;
		try {
			result = em.createQuery("SELECT p FROM ProductProcess p").getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find ProductProcess by active status", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<ProductProcess> findProPActivateConfigure() throws DAOException {
		List<ProductProcess> result = null;
		try {

			Query query = em.createQuery("SELECT p FROM ProductProcess p WHERE p.activeStatus  IN :statusList");
			query.setParameter("statusList", EnumSet.of(ActiveStatus.ACTIVATE, ActiveStatus.CONFIGURE));
			result = query.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find ProductProcess by active status", pe);
		}
		return result;
	}

	/**
	 * @see org.ace.insurance.medical.productProcess.persistence.interfaces.IProductProcessDAO
	 *      #findById(String productProcessId)
	 * @return ProductProcess
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ProductProcess findById(String productProcessId) throws DAOException {
		ProductProcess result = null;
		try {
			Query q = em.createNamedQuery("ProductProcess.findById");
			q.setParameter("id", productProcessId);
			result = (ProductProcess) q.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find ProductProcess by Id", pe);
		}
		return result;
	}

	/**
	 * @see org.ace.insurance.medical.productProcess.persistence.interfaces.IProductProcessDAO
	 *      #findProductProcessBySurveyQuestionId(String surveyQuestionId)
	 * @return List of ProductProcess
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ProductProcess> findProductProcessBySurveyQuestionId(String surveyQuestionId) throws DAOException {
		List<ProductProcess> result = null;
		try {
			Query q = em.createQuery("SELECT p FROM ProductProcess p JOIN p.productProcessQuestionLinkList ppq  Where ppq.surveyQuestion.id =:surveyQuestionId");
			q.setParameter("surveyQuestionId", surveyQuestionId);
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of ProductProcess by surveyQuestionId : " + surveyQuestionId, pe);
		}
		return result;
	}

	/**
	 * @see org.ace.insurance.medical.productProcess.persistence.interfaces.IProductProcessDAO
	 *      #findPPCountByQuId(String surveyQuestionId)
	 * @return int
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public int findPPCountByQuId(String surveyQuestionId) throws DAOException {
		int count = 0;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT  count(p) FROM ProductProcess p JOIN p.productProcessQuestionLinkList ppq  Where ppq.surveyQuestion.id = :queId and ");
			sb.append(" ppq.productProcess.activeStatus IN :statusLists");
			Query query = em.createQuery(sb.toString());
			query.setParameter("queId", surveyQuestionId);
			query.setParameter("statusLists", EnumSet.of(ActiveStatus.ACTIVATE, ActiveStatus.CONFIGURE));

			Number num = (Number) query.getSingleResult();
			count = num.intValue();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find count of ProductProcess by surveyQuestionId : " + surveyQuestionId, pe);
		}
		return count;
	}

	/**
	 * @see org.ace.insurance.medical.productProcess.persistence.interfaces.IProductProcessDAO
	 *      #findPPCountByQuId(String surveyQuestionId)
	 * @return ProductProcess
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ProductProcessDTO> findOldConfPP(String productId, String processId) throws DAOException {
		List<ProductProcessDTO> result = null;
		try {
			StringBuffer str = new StringBuffer();
			str.append("SELECT NEW " + ProductProcessDTO.class.getName());
			str.append(
					" (p.product.productContent.description,p.process.name,ppq.surveyQuestion.description,ppq.surveyQuestion.id,ppq.surveyQuestion.inputType) FROM ProductProcess p  JOIN p.productProcessQuestionLinkList ppq  WHERE p.product.id = :productID AND p.process.id = :processID ");
			str.append(" AND p.activeStatus = :configureStatus");

			Query query = em.createQuery(str.toString());
			query.setParameter("productID", productId);
			query.setParameter("processID", processId);
			query.setParameter("configureStatus", ActiveStatus.ACTIVATE);

			result = query.getResultList();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find  configure of old ProductProcess by productId : " + productId + ", processId : " + processId, pe);
		}
		return result;
	}

	/**
	 * @see org.ace.insurance.medical.productProcess.persistence.interfaces.IProductProcessDAO
	 *      #findConfigAndDeactivatePP(String productId, String processId)
	 * @return List of ProductProcess
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ProductProcess> findConfigAndDeactivatePP(String productId, String processId) throws DAOException {
		List<ProductProcess> result = null;
		try {
			Query query = em.createQuery("SELECT p FROM ProductProcess p WHERE p.product.id = :productID and p.process.id = :processID and  p.activeStatus != activeStatus ");
			query.setParameter("productID", productId);
			query.setParameter("processID", processId);
			query.setParameter("activeStatus", ActiveStatus.ACTIVATE);
			result = query.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find  configure and  deactivate old ProductProcess by productId : " + productId + " and processId : " + processId, pe);
		}
		return result;
	}

	/**
	 * @see org.ace.insurance.medical.productProcess.persistence.interfaces.IProductProcessDAO
	 *      #findPPByProductAndProcess(String productId, String processId)
	 * @return List of ProductProcess
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<String> findPNoByProductAndProcess(String productId, String processId) throws DAOException {
		List<String> result = null;
		try {
			Query query = em.createQuery("SELECT p.questionSetNo FROM ProductProcess p WHERE p.product.id = :productID and p.process.id = :processID ");
			query.setParameter("productID", productId);
			query.setParameter("processID", processId);
			result = query.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find QueSetNo by productId And ProcessId: " + productId + " and processId : " + processId, pe);
		}
		return result;
	}

}
