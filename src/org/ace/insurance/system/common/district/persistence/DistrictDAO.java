package org.ace.insurance.system.common.district.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.common.district.District;
import org.ace.insurance.system.common.district.persistence.interfaces.IDistrictDAO;
import org.ace.insurance.system.common.province.Province;
import org.ace.insurance.system.common.township.Township;
import org.ace.insurance.system.common.township.persistence.interfaces.ITownshipDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("DistrictDAO")
public class DistrictDAO extends BasicDAO implements IDistrictDAO{
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<District> findByProvince(Province province) throws DAOException {
		List<District> result = null;
		try {
			String query = "SELECT d.* FROM district d WHERE d.PROVINCEID = '"+province.getId()+"' "					
		     		+ "OR d.PROVINCEID in (select p.ID from province p where p.PROVINCENO = '"+province.getProvinceNo()+"')";
			Query q = em.createNativeQuery(query,District.class);
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find District", pe);
		}
		return result;
	}

}
