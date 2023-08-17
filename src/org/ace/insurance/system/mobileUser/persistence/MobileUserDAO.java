package org.ace.insurance.system.mobileUser.persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.TableName;
import org.ace.insurance.system.common.securityQuestion.SecurityQuestion;
import org.ace.insurance.system.mobileUser.MobileUser;
import org.ace.insurance.system.mobileUser.persistence.interfaces.IMobileUserDAO;
import org.ace.insurance.system.mobileUser.securityAnswer.SecurityAnswer;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("MobileUserDAO")
public class MobileUserDAO extends BasicDAO implements IMobileUserDAO {
	private void insertProcessLog(MobileUser mobileUser) {
		insertProcessLog(TableName.MOBILE_USER, mobileUser.getId());
		for (SecurityAnswer a : mobileUser.getSecurityAnswerList()) {
			insertProcessLog(TableName.SECURITY_ANSWER, a.getId());
		}
	}

	private void updateProcessLog(MobileUser mobileUser) {
		updateProcessLog(TableName.MOBILE_USER, mobileUser.getId());
		for (SecurityAnswer a : mobileUser.getSecurityAnswerList()) {
			updateProcessLog(TableName.SECURITY_ANSWER, a.getId());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(MobileUser mobileUser) throws DAOException {
		try {
			em.persist(mobileUser);
			insertProcessLog(mobileUser);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert MobileUser", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(MobileUser mobileUser) throws DAOException {
		try {
			em.merge(mobileUser);
			updateProcessLog(mobileUser);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MobileUser", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(MobileUser mobileUser) throws DAOException {
		try {
			mobileUser = em.merge(mobileUser);
			em.remove(mobileUser);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MobileUser", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobileUser findById(String id) throws DAOException {
		MobileUser result = null;
		try {
			result = em.find(MobileUser.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MobileUser", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobileUser findMobileUserByEmailAndMobileNo(String mobileNo, String email) throws DAOException {
		MobileUser result = null;
		try {
			StringBuffer sb = new StringBuffer("SELECT m FROM MobileUser m WHERE 1=1 ");
			if(mobileNo != null && !mobileNo.equals("")) {
				sb.append(" AND m.mobileNumber = :mobileNumber ");
			}
			if(email != null && !email.equals("")) {
				sb.append(" AND m.email = :email ");
			}
			Query q = em.createQuery(sb.toString());
			if(mobileNo != null && !mobileNo.equals("")) {
				q.setParameter("mobileNumber", mobileNo);
			}
			if(email != null && !email.equals("")) {
				q.setParameter("email", email);
			}
			result = (MobileUser) q.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find User(Username = " + email + ")", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<SecurityQuestion> findSecurityQuestionsByEmail(String email) throws DAOException {
		List<SecurityQuestion> result = null;
		try {
			Query q = em.createQuery("SELECT q FROM MobileUser m JOIN m.securityAnswerList a JOIN a.securityQuestion q WHERE m.email = :email ");
			q.setParameter("email", email);
			result = q.getResultList();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find User(Username = " + email + ")", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MobileUser> findAllMobileUser() throws DAOException {
		List<MobileUser> result = null;
		try {
			Query q = em.createQuery("SELECT m FROM MobileUser m ORDER BY m.firstName ASC");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of MobileUser", pe);
		}
		return result;
	}

}
