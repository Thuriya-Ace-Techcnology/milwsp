package org.ace.insurance.system.rta.persistence.interfaces;
/*
 * @author Kyaw Yea Lwin
 * @date 14/07/2020
 */
import java.util.List;

import org.ace.insurance.system.rta.RTA;
import org.ace.insurance.system.rta.TempRTA;
import org.ace.java.component.persistence.exception.DAOException;

public interface IRTADAO {
	public RTA insert(RTA rta) throws DAOException;
	
	public void insertList(List<RTA> rtaList) throws DAOException;
	
	public void insertRTATempList(List<TempRTA> rtaList) throws DAOException;

	public RTA update(RTA rta) throws DAOException;

	public void delete(RTA rta) throws DAOException;

	public RTA findById(String id) throws DAOException;
	
	public RTA findByVehicleNo(String vehicleNo) throws DAOException;

	public List<RTA> findAllRTA() throws DAOException;

	public TempRTA insertRTATemp(TempRTA rta) throws DAOException;
}
