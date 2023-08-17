/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.fire.roof.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.fire.roof.Roof;
import org.ace.insurance.system.fire.roof.persistence.interfaces.IRoofDAO;
import org.ace.insurance.system.fire.roof.service.interfaces.IRoofService;
import org.ace.insurance.system.fire.wall.Wall;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;

@Service(value = "RoofService")
public class RoofService extends BaseService implements IRoofService {

	@Resource(name = "RoofDAO")
	private IRoofDAO roofDAO;

	public List<Roof> findAllRoof() {
		List<Roof> result = null;
		try {
			List<Roof> roofResult = roofDAO.findAll();
			result = getFixedRoofList(roofResult);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find all of Roof)", e);
		}
		return result;
	}

	public Roof findRoofById(String id) {
		Roof result = null;
		try {
			result = roofDAO.findById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a Roof (ID : " + id + ")", e);
		}
		return result;
	}
	
	private List<Roof> getFixedRoofList(List<Roof> result){
		List<Roof> wallList = new ArrayList<>();
		String[] wallString = new String[] {"ISSYS0260001000000000231032013","ISSYS0260001000000000731032013","ISSYS0260001000000000531032013",
											"ISSYS0260001000000000931032013","ISSYS0260001000000000331032013","ISSYS026001000000001020022014",
											"ISSYS0260001000000000831032013","ISSYS0260001000000000631032013","ISSYS0260001000000000431032013",
											"ISSYS0260001000000000131032013"
		};
		for(Roof wall : result) {
			for(String wallId : wallString) {
				if(wall.getId()==wallId || wall.getId().trim().equals(wallId)) {
					wallList.add(wall);
				}
			}
		}
		return wallList;
	}
}