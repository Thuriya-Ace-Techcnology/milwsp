package org.ace.insurance.system.productfaq.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.productfaq.ProductFAQ;
import org.ace.insurance.system.productfaq.persistence.interfaces.IProductFAQDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("ProductFAQDAO")
public class ProductFAQDAO extends BasicDAO implements IProductFAQDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public List<ProductFAQ> findAll() throws DAOException {
		List<ProductFAQ> result = null;
		try {
			Query q = em.createNamedQuery("ProductFAQ.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of ProductFAQ", pe);
		}
		return result;
	}
}
