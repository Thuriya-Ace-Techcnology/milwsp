package org.ace.insurance.system.appversion.persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.appversion.AppVersion;
import org.ace.insurance.system.appversion.MobileType;
import org.ace.insurance.system.appversion.persistence.interfaces.IAppVersionDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("AppVersionDAO")
public class AppVersionDAO extends BasicDAO implements IAppVersionDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public String findLatestAppVersion(MobileType type) throws DAOException {
		String result = "";
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"SELECT a.appVersion FROM AppVersion a WHERE a.appVersion = (SELECT MAX(v.appVersion) FROM AppVersion v WHERE v.type = :type) AND a.type = :type");
			Query q = em.createQuery(buffer.toString());
			q.setParameter("type", type);
			result =  (String) q.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			result = "";
		} catch (PersistenceException pe) {
			throw translate("Failed to find latest AppVersion by Type", pe);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<AppVersion> findAllAppVersion() {

		List<AppVersion> result = null;
		try {
			Query q = em.createQuery("SELECT a FROM AppVersion a ORDER BY a.type ASC");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of AppVersion", pe);
		}
		return result;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public AppVersion updateByAppVersion(MobileType type, String appVersion) {
		AppVersion result = new AppVersion();

		try {
			Query q = em.createNamedQuery("AppVersion.updateByAppVersion");
			q.setParameter("appVersion", appVersion);
			q.setParameter("type", type);
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update AppVersion", pe);
		}

		return result;
	}

	@Override
	public AppVersion findById(String id) throws DAOException {
		AppVersion result = null;
		try {
			result = em.find(AppVersion.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find AppVersion", pe);
		}
		return result;
	}
}
