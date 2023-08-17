package org.ace.insurance.system.common.relationship.service.interfaces;

import java.util.List;

import org.ace.ws.model.RelationShip.RelationShipDTO;

public interface IRelationShipService {

	List<RelationShipDTO> findAllRelationShip();

}
