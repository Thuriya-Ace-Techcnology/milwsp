package org.ace.insurance.system.common.relationship.service.interfaces;

import java.util.List;

import org.ace.insurance.system.common.relationship.RelationShip;
import org.ace.ws.model.RelationShip.RelationShipDTO;

public interface IRelationShipService {

	List<RelationShipDTO> findAllRelationShip();
	
	RelationShip findById(String id);

}
