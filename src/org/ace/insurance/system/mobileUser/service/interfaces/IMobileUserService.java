package org.ace.insurance.system.mobileUser.service.interfaces;

import java.io.IOException;
import java.util.List;

import org.ace.insurance.system.common.securityQuestion.SecurityQuestion;
import org.ace.insurance.system.mobileUser.MobileUser;
import org.ace.ws.model.mobileUser.MU001;
import org.apache.http.client.ClientProtocolException;

public interface IMobileUserService {
	public void addNewMobileUser(MobileUser mobileUser);

	public void updateMobileUser(MobileUser mobileUser);

	public void updateMobileUserPassword(MobileUser mobileUser);

	public void deleteMobileUser(MobileUser mobileUser);

	public MobileUser authenticate(String mobileNo,String usercode, String password);

	public MobileUser findMobileUserById(String id);

	public MobileUser findMobileUserByEmailAndMobileNo(String phoneNo,String email);

	public List<SecurityQuestion> findSecurityQuestionByEmail(String email);

	public List<MobileUser> findAll();

	public void forgetPasswordRequstActicatedCode(MobileUser mobileUser);

	public void calltoOthersServerAPI(MU001 mu001)throws ClientProtocolException, IOException;

}
