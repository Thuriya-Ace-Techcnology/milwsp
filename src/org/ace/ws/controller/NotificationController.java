package org.ace.ws.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.ace.insurance.common.DateUtils;
import org.ace.insurance.system.notification.Notification;
import org.ace.insurance.system.notification.NotificationCriteria;
import org.ace.insurance.system.notification.service.interfaces.INotificationService;
import org.ace.java.component.StatusType;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.interfaces.IDataRepository;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NotificationController extends BaseController {
	@Resource(name = "DataRepository")
	private IDataRepository<Notification> notificationRepo;

	@Resource(name = "NotificationService")
	private INotificationService notificationService;

	@RequestMapping(value = URIConstants.GET_NOTIFICATION_LIST, method = RequestMethod.POST)
	public @ResponseBody String getNotificationList(@RequestHeader String key) throws ServiceException {
		String response;
		try {
			if (key.toString().equals(Constants.getApikey())) {
				List<Notification> NotificationList = notificationRepo.findAll(Notification.class);
				response = responseManager.getResponseString(NotificationList);
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(StatusType.SQL_Exception);
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}

	@RequestMapping(value = URIConstants.GET_NOTIFICATION_BY_ID, method = RequestMethod.POST)
	public @ResponseBody String getNotificationById(@RequestHeader String key, @RequestBody String id) throws ServiceException {
		String response;
		try {
			if (key.toString().equals(Constants.getApikey())) {
				id = id.replace("\"", "").replace("{", "").replace("}", "");
				id = id.replace("id:", "");
				Notification notification = notificationRepo.findById(Notification.class, id);
				if (notification == null) {
					response = responseManager.getResponseString(StatusType.OBJECT_NOT_FOUND);
				} else {
					response = responseManager.getResponseString(notification);
				}
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(StatusType.SQL_Exception);
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}

	@RequestMapping(value = URIConstants.GET_NOTIFICATION_BY_DATE, method = RequestMethod.POST)
	public @ResponseBody String getNotificationByDate(@RequestHeader String key, @RequestBody String date) throws ServiceException {
		String response;
		try {
			if (key.toString().equals(Constants.getApikey())) {
				date = date.replace("\"", "").replace("{", "").replace("}", "");
				date = date.replace("date:", "");
				System.out.println("Date : " + date);
				NotificationCriteria criteria = new NotificationCriteria(true, DateUtils.substractDay(new Date(), 7), DateUtils.formatStringToDate(date), true, true);
				List<Notification> NotificationList = notificationService.findAllNotificationByCriteria(criteria);
				response = responseManager.getResponseString(NotificationList);
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(StatusType.SQL_Exception);
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}

}
