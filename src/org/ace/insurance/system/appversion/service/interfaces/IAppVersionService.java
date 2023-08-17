package org.ace.insurance.system.appversion.service.interfaces;

import java.util.List;

import org.ace.insurance.system.appversion.AppVersion;
import org.ace.insurance.system.appversion.MobileType;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;

public interface IAppVersionService {

	public String findLatestAppVersion(MobileType type) throws SystemException;

	public List<AppVersion> findAllAppVersion();

	public AppVersion upDateByAppVersion(MobileType type, String appVersion);

	public AppVersion findById(String id)throws DAOException;

}
