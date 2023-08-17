/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.common.township.service.interfaces;

import java.util.List;

import org.ace.insurance.system.common.province.Province;
import org.ace.insurance.system.common.township.Township;

public interface ITownshipService {
//	public void addNewTownship(Township township);
	
	public Township addNewTownship(Township township);

	public void updateTownship(Township township);

	public void deleteTownship(Township township);

	public Township findTownshipById(String id);

	public List<Township> findTownshipByProvince(Province province);

	public List<Township> findAllTownship();

	public List<Township> findByCriteria(String criteria);
}
