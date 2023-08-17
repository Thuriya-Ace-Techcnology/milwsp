/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.fire.buildingclass.persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.fire.buildingclass.BuildingClass;
import org.ace.insurance.system.fire.buildingclass.persistence.interfaces.IBuildingClassDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("BuildingClassDAO")
public class BuildingClassDAO extends BasicDAO implements IBuildingClassDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public BuildingClass findById(String id) throws DAOException {
		BuildingClass result = null;
		try {
			result = em.find(BuildingClass.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find BuildingClass", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<BuildingClass> findAll() throws DAOException {
		List<BuildingClass> result = null;
		try {
			Query q = em.createNamedQuery("BuildingClass.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of BuildingClass", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public BuildingClass findByRoofWallFloor(String roof, String wall, String floor) throws DAOException {
		BuildingClass result = null;
		try {
			Query q = em.createNamedQuery("BuildingClass.findByRoofWallFloorId");
			q.setParameter("roof", roof);
			q.setParameter("wall", wall);
			q.setParameter("floor", floor);
			result = (BuildingClass) q.getSingleResult();
			em.flush();
		} catch (NoResultException e) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find BuildingClass by Roof,Wall,Floor", pe);
		}
		return result;
	}

}
