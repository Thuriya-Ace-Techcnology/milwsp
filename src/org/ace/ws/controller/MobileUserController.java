package org.ace.ws.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.ace.insurance.common.Utils;
import org.ace.insurance.system.common.securityQuestion.service.interfaces.ISecurityQuestionService;
import org.ace.insurance.system.mobileUser.MobileUser;
import org.ace.insurance.system.mobileUser.securityAnswer.SecurityAnswer;
import org.ace.insurance.system.mobileUser.service.interfaces.IMobileUserService;
import org.ace.java.component.StatusType;
import org.ace.java.component.SystemException;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.factory.MobileUserFactory;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.mobileUser.MU001;
import org.ace.ws.model.mobileUser.SQA001;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MobileUserController extends BaseController {

	@Resource(name = "MobileUserService")
	private IMobileUserService mobileUserService;

	@Resource(name = "SecurityQuestionService")
	private ISecurityQuestionService securityQuestionService;

	private static final Logger logger = Logger.getLogger(MobileUserController.class);

	@CrossOrigin
	@RequestMapping(value = URIConstants.REGISTER, method = RequestMethod.POST)
	@ResponseBody
	public String addMobileUser(@RequestHeader String key, @RequestBody MU001 mu001) throws ServiceException {
		logger.info("Start Create Mobile User.");
		String response = null;
		try {
			if (key.toString().equals(Constants.getApikey())) {
				if (mu001 != null && mu001.getMobileNumber() != null && mu001.getPassword() != null) {
					MobileUser mobileUser = mobileUserService.findMobileUserByEmailAndMobileNo(mu001.getMobileNumber(),mu001.getEmail());
					if (mobileUser != null) {
						response = responseManager.getResponseString(ResponseStatus.EXISTING_USER.getLabel());
					} else {
						mobileUser = MobileUserFactory.convertMobileUser(mu001);
						mobileUser.setActivatedCode(Utils.getRandomActivatedCode());
						for (SQA001 sqa001 : mu001.getSqa001List()) {
							if (sqa001.getSecurityQuestionId() != null) {
								SecurityAnswer securityAnswer = new SecurityAnswer();
								securityAnswer.setSecurityQuestion(securityQuestionService.findById(sqa001.getSecurityQuestionId()));
								securityAnswer.setSecurityAnswer(sqa001.getSecurityAnswer());
								mobileUser.addSecurityAnswer(securityAnswer);
							}
						}
						mobileUserService.addNewMobileUser(mobileUser);
						Runnable run = () -> {
							try {
								mobileUserService.calltoOthersServerAPI(mu001);
							}catch(ConnectException conn) {
								logger.info("Can't connect to API Server!!! Connection Problem!");
							}
							catch (ClientProtocolException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						};
						new Thread(run).start();
						response = responseManager.getResponseString(ResponseStatus.SUCCESS.getLabel());
					}
				} else {
					response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
				}
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}

		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.INTERNAL_SERVER_ERROR.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Create Mobile User.");
		return response;
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public String updateMobileUser(@RequestHeader String key, @RequestBody MU001 mu001) throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Update Mobile User.");
		String response = null;
		MU001 mu002 = new MU001();
		try {
			if (key != null && key.toString().equals(Constants.getApikey())) {
				if (mu001 != null ) {
					MobileUser mobileUser = mobileUserService.findMobileUserById(mu001.getId());
					if (mobileUser == null) {
						response = responseManager.getResponseString(ResponseStatus.INVALIDUSER.getLabel());
					} else {
						mobileUser.setFirstName(mu001.getFirstName());
						mobileUser.setLastName(mu001.getLastName());
						mobileUserService.updateMobileUser(mobileUser);
						mu002 = MobileUserFactory.convertMobileUserDTO(mobileUser);
						mu002.setResponseStatus(ResponseStatus.SUCCESS);
						response = responseManager.getResponseString(mu002);
					}
				} else {
					mu001.setResponseStatus(ResponseStatus.FAIL);
					response = responseManager.getResponseString(mu001);
				}
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.INTERNAL_SERVER_ERROR.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.ACTIVATE, method = RequestMethod.POST)
	@ResponseBody
	public String activateMobileUser(@RequestHeader String key, @RequestBody MU001 mu001) throws ServiceException {
		logger.info("Start Activate Mobile User.");
		String response = null;
		try {
			if (key.toString().equals(Constants.getApikey())) {
				if (mu001 != null && (mu001.getEmail() != null || mu001.getMobileNumber() != null) && mu001.getActivatedCode() != null) {
					MobileUser mobileUser = mobileUserService.findMobileUserByEmailAndMobileNo(mu001.getMobileNumber(), mu001.getEmail());
					if (mobileUser == null) {
						response = responseManager.getResponseString(ResponseStatus.FAIL.getLabel());
					} else {
						if (mu001.getActivatedCode().equals(mobileUser.getActivatedCode())) {
							mobileUser.setActivate(true);
							mobileUser.setActivatedDate(new Date());
							mobileUserService.updateMobileUser(mobileUser);
							response = responseManager.getResponseString(ResponseStatus.SUCCESS.getLabel());
						} else {
							response = responseManager.getResponseString(ResponseStatus.FAIL.getLabel());
						}
					}
				} else {
					response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
				}
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.INTERNAL_SERVER_ERROR.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Activate Mobile User.");
		return response;
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.LOGIN, method = RequestMethod.POST)
	public @ResponseBody String login(@RequestHeader String key, @RequestBody MU001 mu001param) throws ServiceException {
		logger.info("Start Login.");
		String response = null;
		MU001 mu001 = new MU001();
		try {
			if (key.toString().equals(Constants.getApikey())) {
				if (mu001param != null && (mu001param.getEmail() != null || mu001param.getMobileNumber() != null) && mu001param.getPassword() != null) {
					MobileUser mobileUser = mobileUserService.findMobileUserByEmailAndMobileNo(mu001param.getMobileNumber(), mu001param.getEmail());
					if (mobileUser == null) {
						mu001.setResponseStatus(ResponseStatus.INVALIDUSER);
						response = responseManager.getResponseString(mu001);
					} else {
						mobileUser = mobileUserService.authenticate(mu001param.getMobileNumber(), mu001param.getEmail(), mu001param.getPassword());
						if (mobileUser == null) {
							mu001.setResponseStatus(ResponseStatus.WRONG_PASSWORD);
							response = responseManager.getResponseString(mu001);
						} else {
						/*	if (mobileUser.isActivate()) {
								mu001 = MobileUserFactory.convertMobileUserDTO(mobileUser);
								mu001.setResponseStatus(ResponseStatus.SUCCESS);
								response = responseManager.getResponseString(mu001);
							} else {
								mu001.setResponseStatus(ResponseStatus.INACTIVE);
								response = responseManager.getResponseString(mu001);
							}
						*/	
							mu001 = MobileUserFactory.convertMobileUserDTO(mobileUser);
							mu001.setResponseStatus(ResponseStatus.SUCCESS);
							response = responseManager.getResponseString(mu001);
						}
					}
				} else {
					mu001.setResponseStatus(ResponseStatus.INVALID_REQUEST_PARAM);
					response = responseManager.getResponseString(mu001);
				}
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (Exception e) {
			e.printStackTrace();
			mu001.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
			response = responseManager.getResponseString(mu001);
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Login.");
		return response;
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.CHANGE_PASSWORD, method = RequestMethod.POST)
	@ResponseBody
	public String changePasswordMobileUser(@RequestHeader String key, @RequestBody MU001 mu001) throws ServiceException {
		logger.info("Start Change Password Mobile User.");
		String response = null;
		try {
			if (key.toString().equals(Constants.getApikey())) {
				if (mu001 != null && mu001.getMobileNumber() != null && mu001.getPassword() != null && mu001.getNewPassword() != null) {
					MobileUser mobileUser = mobileUserService.authenticate(mu001.getMobileNumber(), mu001.getEmail(), mu001.getPassword());
					if (mobileUser == null) {
						response = responseManager.getResponseString(ResponseStatus.FAIL.getLabel());
					} else {
						mobileUser.setPassword(mu001.getNewPassword());
						mobileUserService.updateMobileUserPassword(mobileUser);
						response = responseManager.getResponseString(ResponseStatus.SUCCESS.getLabel());
					}
				} else {
					response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
				}
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.INTERNAL_SERVER_ERROR.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Change Password Mobile User.");
		return response;
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.FORGET_PASSWORD, method = RequestMethod.POST)
	@ResponseBody
	public String forgetPassword(@RequestHeader String key, @RequestBody MU001 mu001param) throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Forget Password Mobile User.");
		String response = null;
		MU001 mu001 = new MU001();
		try {
			if (key.toString().equals(Constants.getApikey())) {
				if (mu001param != null && mu001param.getMobileNumber() != null) {
					MobileUser mobileUser = mobileUserService.findMobileUserByEmailAndMobileNo(mu001param.getMobileNumber(), mu001param.getEmail());
					if (mobileUser == null) {
						mu001.setResponseStatus(ResponseStatus.INVALIDUSER);
						response = responseManager.getResponseString(mu001);
					} else {
						mobileUser.setActivatedCode(Utils.getRandomActivatedCode());
						mobileUserService.forgetPasswordRequstActicatedCode(mobileUser);
						mu001 = MobileUserFactory.convertMobileUserDTO(mobileUser);
						mu001.setResponseStatus(ResponseStatus.SUCCESS);
						response = responseManager.getResponseString(mu001);
					}
				} else {
					mu001.setResponseStatus(ResponseStatus.INVALID_REQUEST_PARAM);
					response = responseManager.getResponseString(mu001);
				}
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}

		} catch (SystemException e) {
			e.printStackTrace();
			mu001.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
			response = responseManager.getResponseString(mu001);
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Forget Password Mobile User.");
		return response;
	}
	
	@CrossOrigin
	@RequestMapping(value = URIConstants.CHECK_ACTIVATED_CODE, method = RequestMethod.POST)
	@ResponseBody
	public String checkActivatedCode(@RequestHeader String key,@RequestBody MU001 mu001param) throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Check Activated Code Mobile User.");
		String response = null;
		MU001 mu001 = new MU001();
		try {
			if (key.toString().equals(Constants.getApikey())) {
				if (mu001param.getActivatedCode() != null && !mu001param.getActivatedCode().equals("") && mu001param.getMobileNumber() != null && !mu001param.getMobileNumber().equals("") ) {
					MobileUser mobileUser = mobileUserService.findMobileUserByEmailAndMobileNo(mu001param.getMobileNumber(), mu001param.getEmail());
					if (mobileUser == null) {
						mu001.setResponseStatus(ResponseStatus.INVALIDUSER);
						response = responseManager.getResponseString(mu001);
					} else if(mobileUser != null && mobileUser.getActivatedCode().equals(mu001param.getActivatedCode())) {
						mu001 = MobileUserFactory.convertMobileUserDTO(mobileUser);
						mu001.setResponseStatus(ResponseStatus.SUCCESS);
						response = responseManager.getResponseString(mu001);
					}
				} else {
					mu001.setResponseStatus(ResponseStatus.INVALID_REQUEST_PARAM);
					response = responseManager.getResponseString(mu001);
				}
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			mu001.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
			response = responseManager.getResponseString(mu001);
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Forget Password Mobile User.");
		return response;
	}
	
	@CrossOrigin
	@RequestMapping(value = URIConstants.RESET_PASSWORD, method = RequestMethod.POST)
	@ResponseBody
	public String resetPassword(@RequestHeader String key, @RequestBody MU001 mu001) throws ServiceException {
		logger.info("Start Reset Password Mobile User.");
		String response = null;
		try {
			if (key.toString().equals(Constants.getApikey())) {
				if (mu001 != null && mu001.getMobileNumber() != null && mu001.getNewPassword() != null) {
					MobileUser mobileUser = mobileUserService.findMobileUserByEmailAndMobileNo(mu001.getMobileNumber(), mu001.getEmail());
					if (mobileUser == null) {
						response = responseManager.getResponseString(ResponseStatus.INVALIDUSER.getLabel());
					} else {
						mobileUser.setPassword(mu001.getNewPassword());
						mobileUserService.updateMobileUserPassword(mobileUser);
						response = responseManager.getResponseString(ResponseStatus.SUCCESS.getLabel());
					}
				} else
					response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}

		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.INTERNAL_SERVER_ERROR.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Reset Password Mobile User.");
		return response;
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.MOBILE_USER_LIST, method = RequestMethod.POST)
	@ResponseBody
	public String getUserList(@RequestHeader String key) throws ServiceException {
		logger.info("Start Get Mobile User List.");
		String response = null;
		try {
			if (key.toString().equals(Constants.getApikey())) {
				List<MobileUser> mobileUserList = mobileUserService.findAll();
				response = responseManager.getResponseString(mobileUserList);

			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.FAIL.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Get Mobile User List.");
		return response;
	}
	
	@RequestMapping(value = URIConstants.GET_ACTIVATE_CODE_BY_MOBILE_NUMBER, method = RequestMethod.GET)
	@ResponseBody
	public String getActivateCode(@RequestParam(name="phoneNumber") String phoneNumber) {
		logger.info("Start Get Mobile User List.");
		String response = null;
		try {
			if(phoneNumber != null && !phoneNumber.equals("")) {
				MobileUser mobileUser = mobileUserService.findMobileUserByEmailAndMobileNo(phoneNumber,"");
				response = responseManager.getResponseString("ActivatedCode : "+mobileUser.getActivatedCode());

			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.FAIL.getLabel());
		}
		logger.info("End Get Mobile User List.");
		return response;
	}
}
