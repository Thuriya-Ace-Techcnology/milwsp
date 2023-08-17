package org.ace.insurance.system.common.relationship.persistence.interfaces;

import java.util.List;

import org.ace.insurance.system.common.relationship.RelationShip;
import org.ace.java.component.persistence.exception.DAOException;

public interface IRelationShipDAO {

	List<RelationShip> findAll() throws DAOException;

	public RelationShip findById(String id) throws DAOException;
}
