package org.ace.insurance.common.plans.persistences;

import java.util.List;


import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.plans.Plans;
import org.ace.insurance.common.plans.persistences.interfaces.IPlansDAO;
import org.ace.insurance.product.Product;
import org.ace.java.component.persistence.BasicDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("PlansDAO")
public class PlansDAO extends BasicDAO implements IPlansDAO{

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Plans> findAll() {
		List<Plans> result = null;
		try {
			Query q = em.createNamedQuery("Plans.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Plans Type", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Plans findById(String id) {
		Plans result = null;
		try {
			result = em.find(Plans.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Product", pe);
		}
		return result;
		
	}

}
