package org.ace.insurance.system.productTypeRecords.persistence;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.TableName;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.system.productTypeRecords.persistence.interfaces.IProductTypeRecordsDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("ProductTypeRecordsDAO")
public class ProductTypeRecordsDAO extends BasicDAO implements IProductTypeRecordsDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ProductTypeRecords insert(ProductTypeRecords productTypeRecords) throws DAOException {
		try {
			em.persist(productTypeRecords);
			insertProcessLog(TableName.TWOCTWOP_RECORDS, productTypeRecords.getId());
			return productTypeRecords;
		} catch (PersistenceException pe) {
			throw translate("Failed to insert ProductTypeRecords", pe);
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ProductTypeRecords update(ProductTypeRecords productTypeRecords) throws DAOException {
		try {
			em.merge(productTypeRecords);
			em.flush();
			return productTypeRecords;
		} catch (PersistenceException pe) {
			throw translate("Failed to update ProductTypeRecords", pe);
		}
	}

	@Override
	public void delete(ProductTypeRecords productTypeRecords) throws DAOException {
		try {
			productTypeRecords = em.merge(productTypeRecords);
			em.remove(productTypeRecords);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to delete ProductTypeRecords", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ProductTypeRecords findById(String id) throws DAOException {
		ProductTypeRecords result = null;
		try {
			result = em.find(ProductTypeRecords.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find ProductTypeRecords", pe);
		}
		return result;
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ProductTypeRecords findByOrderId(String orderId) throws DAOException {
		ProductTypeRecords result = null;
		try {
			 Query q = em.createNamedQuery("ProductTypeRecords.findByOrderId");
			 q.setParameter("twoCtwoPorderId", orderId);
			result = (ProductTypeRecords) q.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find ProductTypeRecords", pe);
		}
		return result;
	}

}
