package org.ace.insurance.system.common.relationship.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.common.relationship.RelationShip;
import org.ace.insurance.system.common.relationship.persistence.interfaces.IRelationShipDAO;
import org.ace.insurance.system.common.relationship.service.interfaces.IRelationShipService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.ace.ws.model.RelationShip.RelationShipDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "RelationShipService")
public class RelationShipService extends BaseService implements IRelationShipService {

	@Resource(name = "RelationShipDAO")
	private IRelationShipDAO relationShipDAO;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<RelationShipDTO> findAllRelationShip() {
		List<RelationShipDTO> result = new ArrayList<>();
		try {
			List<RelationShip> rsList = relationShipDAO.findAll();
			for(RelationShip rs : rsList) {
				result.add(new RelationShipDTO(rs));
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find all of RelationShip)", e);
		}
		return result;
	}
}
