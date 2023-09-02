package org.ace.insurance.system.rta.service.interfaces;

import java.io.File;
/*
 * @author Kyaw Yea Lwin
 * @date 14/07/2020
 */
import java.util.List;

import org.ace.insurance.system.rta.RTA;
import org.ace.java.component.persistence.exception.DAOException;

public interface IRTAService {
	public RTA insert(RTA rta) throws DAOException;

	public RTA update(RTA rta) throws DAOException;

	public void delete(RTA rta) throws DAOException;

	public RTA findById(String id) throws DAOException;
	
	public RTA findByVehicleNo(String vehicleNo) throws DAOException;

	public List<RTA> findAllRTA() throws DAOException;

	/* public List<RTADTO> accessFileRead(File destinationFile); */
	
	public String findKgByVehicleNo(String vehicleNo)throws DAOException;
}
