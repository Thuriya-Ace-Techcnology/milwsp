package org.ace.insurance.system.appversion.persistence.interfaces;

import java.util.List;

import org.ace.insurance.system.appversion.AppVersion;
import org.ace.insurance.system.appversion.MobileType;
import org.ace.java.component.persistence.exception.DAOException;

public interface IAppVersionDAO {

	public String findLatestAppVersion(MobileType type) throws DAOException;

	public List<AppVersion> findAllAppVersion();

	public AppVersion updateByAppVersion(MobileType type, String appVersion);

	public AppVersion findById(String id) throws DAOException;
}
