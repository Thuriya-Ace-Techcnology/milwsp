package org.ace.insurance.life.payment.service.interfaces;

import java.util.List;

import org.ace.insurance.life.dao.entities.TlfData;
import org.ace.insurance.system.common.branch.Branch;
import org.ace.java.component.SystemException;

public interface ITlfProcessor {
	
	public void handleTlfProcess(List<TlfData> dataList, Branch branch) throws SystemException;

}
