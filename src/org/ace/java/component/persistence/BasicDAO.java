package org.ace.java.component.persistence;

import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.ErrorCode;
import org.ace.insurance.common.KeyFactorIDConfig;
import org.ace.insurance.product.Product;
import org.ace.java.component.persistence.exception.DAOException;
import org.jboss.security.auth.spi.Users.User;

/**
 * @author Zaw Than Oo
 */
public class BasicDAO {

	@PersistenceContext
	protected EntityManager em;

	@Resource(name = "SQL_ERROR_CODE")
	private Properties properties;

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public DAOException translate(String message, SQLException sqlex) {
		String errorCode = properties.getProperty(sqlex.getErrorCode() + "");
		if (errorCode == null || errorCode.equals("")) {
			return new DAOException(ErrorCode.NO_SQL_ERROR_CODE_CONFIG, "There is no SQL ERROR CODE(" + sqlex.getErrorCode() + ") in configuration", sqlex);
		} else {
			return new DAOException(errorCode, message, sqlex);
		}
	}

	public RuntimeException translate(String message, RuntimeException e) {
		e.printStackTrace();
		DAOException dae = null;
		Throwable throwable = e;
		while (throwable != null && !(throwable instanceof SQLException)) {
			throwable = throwable.getCause();
		}
		if (throwable instanceof SQLException) {
			dae = translate(message, (SQLException) throwable);
		}
		if (dae != null) {
			return dae;
		} else {
			return new DAOException(ErrorCode.DAO_RUNTIME_ERROR, e.getMessage(), e);
		}
	}

	protected void insertProcessLog(String tableName, String primaryKey) throws PersistenceException {
		insertProcessLogging(tableName, primaryKey, null);
	}

	protected void insertProcessLog(String tableName, String primaryKey, User user) throws PersistenceException {
		insertProcessLogging(tableName, primaryKey, user);
	}

	private void insertProcessLogging(String tableName, String primaryKey, User user) throws PersistenceException {
		String queryString = "UPDATE " + tableName + " SET CREATEDUSERID = ?, CREATEDDATE = ? WHERE ID = ?";
		Query query = em.createNativeQuery(queryString);
		if (user == null)
			// query.setParameter(1, userProcessService.getLoginUser().getId());
			// else
			// query.setParameter(1, user.getId());
			query.setParameter(2, new Date());
		query.setParameter(3, primaryKey);
		query.executeUpdate();
	}

	protected void updateProcessLog(String tableName, String primaryKey) throws PersistenceException {
		String queryString = "UPDATE " + tableName + " SET UPDATEDUSERID = ?, UPDATEDDATE = ? WHERE ID = ?";
		Query query = em.createNativeQuery(queryString);
		// query.setParameter(1, userProcessService.getLoginUser().getId());
		query.setParameter(2, new Date());
		query.setParameter(3, primaryKey);
		query.executeUpdate();
	}

	protected boolean isFireProduct(String productId) {
		if (productId.equals(KeyFactorIDConfig.getFireBuildingId()) || productId.equals(KeyFactorIDConfig.getFireDelclarationPolicyId())
				|| productId.equals(KeyFactorIDConfig.getFireFurnitureId()) || productId.equals(KeyFactorIDConfig.getFireMachineryId())
				|| productId.equals(KeyFactorIDConfig.getFireStockId())) {
			return true;
		}
		return false;
	}

	protected boolean isPublicLife(Product product) {
		if (product.getId().equals(KeyFactorIDConfig.getPublicLifeId())) {
			return true;
		}
		return false;
	}

	protected boolean isGroupLife(Product product) {
		if (product.getId().equals(KeyFactorIDConfig.getGroupLifeId())) {
			return true;
		}
		return false;
	}

	protected boolean isEmpty(Object value) {
		if (value == null) {
			return true;
		}
		if (value.toString().isEmpty()) {
			return true;
		}
		return false;
	}
}
