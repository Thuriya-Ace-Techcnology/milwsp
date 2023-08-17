package org.ace.insurance.system.appversion.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.appversion.AppVersion;
import org.ace.insurance.system.appversion.MobileType;
import org.ace.insurance.system.appversion.persistence.interfaces.IAppVersionDAO;
import org.ace.insurance.system.appversion.service.interfaces.IAppVersionService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "AppVersionService")
public class AppVersionService extends BaseService implements IAppVersionService {
	@Resource(name = "AppVersionDAO")
	private IAppVersionDAO appVersionDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public String findLatestAppVersion(MobileType type) throws SystemException {
		String appVersion = "";
		try {
			appVersion = appVersionDAO.findLatestAppVersion(type);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find latest AppVersions.", e);
		}
		return appVersion;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<AppVersion> findAllAppVersion() {
		List<AppVersion> app = new ArrayList<>();
		try {
			app = appVersionDAO.findAllAppVersion();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Not Found", e);
		}
		return app;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public AppVersion upDateByAppVersion(MobileType type, String appVersion) {
		AppVersion result = null;
		try {
			result = appVersionDAO.updateByAppVersion(type, appVersion);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a AppVersion ", e);
		}
		return result;
	}

	@Override
	public AppVersion findById(String id) throws DAOException {
		AppVersion result = null;
		try {
			result = appVersionDAO.findById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a AppVersion (ID : " + id + ")",
					e);
		}
		return result;
	}

	

}
