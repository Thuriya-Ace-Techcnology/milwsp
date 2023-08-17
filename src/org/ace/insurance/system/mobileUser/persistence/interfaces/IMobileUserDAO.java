package org.ace.insurance.system.mobileUser.persistence.interfaces;

import java.util.List;

import org.ace.insurance.system.common.securityQuestion.SecurityQuestion;
import org.ace.insurance.system.mobileUser.MobileUser;
import org.ace.java.component.persistence.exception.DAOException;

public interface IMobileUserDAO {
	public void insert(MobileUser mobileUser) throws DAOException;

	public void update(MobileUser mobileUser) throws DAOException;

	public void delete(MobileUser MobileUser) throws DAOException;

	public MobileUser findById(String id) throws DAOException;

	public MobileUser findMobileUserByEmailAndMobileNo(String mobileNo, String email) throws DAOException;

	public List<SecurityQuestion> findSecurityQuestionsByEmail(String email) throws DAOException;

	public List<MobileUser> findAllMobileUser() throws DAOException;

}
