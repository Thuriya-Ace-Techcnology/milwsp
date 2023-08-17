package org.ace.insurance.system.productinformation.maincategory.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.productinformation.maincategory.MainCategory;
import org.ace.insurance.system.productinformation.maincategory.persistence.interfaces.IMainCategoryDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.mainCategory.MC001;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("MainCategoryDAO")
public class MainCategoryDAO extends BasicDAO implements IMainCategoryDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(MainCategory mainCategory) throws DAOException {
		try {
			em.persist(mainCategory);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert MainCategory", pe);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public MainCategory update(MainCategory mainCategory) throws DAOException {
		try {
			mainCategory = em.merge(mainCategory);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MainCategory", pe);
		}
		return mainCategory;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(MainCategory mainCategory) throws DAOException {
		try {
			mainCategory = em.merge(mainCategory);
			em.remove(mainCategory);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MainCategory", pe);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public MainCategory findById(String id) throws DAOException {
		MainCategory result = null;
		try {
			result = em.find(MainCategory.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MainCategory", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<MainCategory> findAll() throws DAOException {
		List<MainCategory> result = null;
		try {
			Query q = em.createNamedQuery("MainCategory.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of MainCategory", pe);
		}
		return result;
	}

	// join m.product p join m.textList t join m.subCategoryList s
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MC001> findMainCategoryByProductId(String productId) {
		List<MC001> result = null;
		try {
			StringBuilder query = new StringBuilder();
			query.append("Select New " + MC001.class.getName() + "(m)");
			query.append(" from MainCategory m ");
			if (productId != null) {
				query.append(" where m.product.id=:productId");
			}
			Query q = em.createQuery(query.toString());
			if (productId != null) {
				q.setParameter("productId", productId);
			}
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MainCategory By ProductId", pe);
		}
		return result;
	}

}