package org.ace.insurance.system.fire.paeRate.service.interfaces;

public interface IPaeRateService {
	public double findPAERateByClassAndAge(String buildingClassId, int buildingAge);
}
