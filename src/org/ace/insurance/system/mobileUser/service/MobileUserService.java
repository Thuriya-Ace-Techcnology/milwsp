package org.ace.insurance.system.mobileUser.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.common.EmailUtils;
import org.ace.insurance.system.common.securityQuestion.SecurityQuestion;
import org.ace.insurance.system.mobileUser.MobileUser;
import org.ace.insurance.system.mobileUser.persistence.interfaces.IMobileUserDAO;
import org.ace.insurance.system.mobileUser.securityAnswer.SecurityAnswer;
import org.ace.insurance.system.mobileUser.service.interfaces.IMobileUserService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.ace.java.component.service.PasswordCodecHandler;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.mobileUser.MU001;
import org.ace.ws.model.mobiletravelproposal.MTP001;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

@Service(value = "MobileUserService")
public class MobileUserService extends BaseService implements IMobileUserService {

	@Resource(name = "MobileUserDAO")
	private IMobileUserDAO mobileUserDAO;

	@Resource(name = "PasswordCodecHandler")
	private PasswordCodecHandler codecHandler;

	private void setIDPrefixForInsert(MobileUser mobileUser) {
		String parentPrefix = customIDGenerator.getPrefix(MobileUser.class);
		String childPrefix = customIDGenerator.getPrefix(SecurityAnswer.class);
		mobileUser.setPrefix(parentPrefix);
		for (SecurityAnswer a : mobileUser.getSecurityAnswerList()) {
			a.setPrefix(childPrefix);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addNewMobileUser(MobileUser mobileUser) {
		try {
			setIDPrefixForInsert(mobileUser);
			// mobileUser.setChangePassword(true);
			mobileUser.setPassword(codecHandler.encode(mobileUser.getPassword()));
			mobileUserDAO.insert(mobileUser);
			if(mobileUser.getEmail() != null && !mobileUser.getEmail().equals("")) {
				EmailUtils.sentSignupMail(mobileUser.getEmail(), mobileUser.getName(), mobileUser.getActivatedCode());
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a new MobileUser", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateMobileUser(MobileUser mobileUser) {
		try {
			setIDPrefixForInsert(mobileUser);
			mobileUserDAO.update(mobileUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update a MobileUser", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateMobileUserPassword(MobileUser mobileUser) {
		try {
			mobileUser.setPassword(codecHandler.encode(mobileUser.getPassword()));
			mobileUserDAO.update(mobileUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update a MobileUser", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteMobileUser(MobileUser mobileUser) {
		try {
			mobileUserDAO.delete(mobileUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to delete a MobileUser", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public MobileUser findMobileUserById(String id) {
		MobileUser result = null;
		try {
			result = mobileUserDAO.findById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a MobileUser (ID : " + id + ")", e);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobileUser authenticate(String mobileNo,String email, String password) {
		MobileUser result = null;
		try {
			MobileUser mobileUser = mobileUserDAO.findMobileUserByEmailAndMobileNo(mobileNo,email);
			if (mobileUser != null) {
				String encodedPassword = codecHandler.encode(password);
				if (mobileUser.getPassword().equals(encodedPassword)) {
					result = mobileUser;
				}
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to change passowrd", e);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public MobileUser findMobileUserByEmailAndMobileNo(String mobileNo, String email) {
		MobileUser result = null;
		try {
			result = mobileUserDAO.findMobileUserByEmailAndMobileNo(mobileNo, email);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find MobileUser by Email", e);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<SecurityQuestion> findSecurityQuestionByEmail(String email) {
		List<SecurityQuestion> result = null;
		try {
			result = mobileUserDAO.findSecurityQuestionsByEmail(email);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find MobileUser by Email", e);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<MobileUser> findAll() {
		List<MobileUser> result = null;
		try {
			result = mobileUserDAO.findAllMobileUser();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find MobileUser", e);
		}
		return result;
	}

	@Override
	public void forgetPasswordRequstActicatedCode(MobileUser mobileUser) {
		updateMobileUser(mobileUser);
		if(mobileUser.getEmail() != null && !mobileUser.getEmail().equals("")) {
			EmailUtils.sentSignupMail(mobileUser.getEmail(), mobileUser.getName(), mobileUser.getActivatedCode());
		}
	}

	@Override
	public void calltoOthersServerAPI(MU001 mu001)throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		 HttpPost httpPost = new HttpPost("http://localhost:8899/restcaller/register");
		 HashMap<String, MU001> map = new HashMap<String, MU001>();
		 map.put("mu001", mu001);
		 Gson gson = new Gson();
		    String json = gson.toJson(map);
		    StringEntity request = new StringEntity(json);
		    httpPost.setEntity(request);
		    httpPost.setHeader("Accept", "application/json");
		    httpPost.setHeader("Content-type", "application/json");
		    CloseableHttpResponse httpResp = client.execute(httpPost);
		    String result = EntityUtils.toString(httpResp.getEntity());
		    AceResponse response = gson.fromJson(result, AceResponse.class);
		    client.close();
		    ResponseStatus status = null;
		    if(response.getCode() == 200) {
		    	status = ResponseStatus.SUCCESS;
		    }else if(response.getCode() == 500) {
		    	status = ResponseStatus.FAIL;
		    }
	}

}
