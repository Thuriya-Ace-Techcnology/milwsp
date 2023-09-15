package org.ace.insurance.common.plans.services.interfaces;

import java.util.List;

import org.ace.insurance.common.plans.Plans;


public interface IPlansService {

	List<Plans> getPlanList();
	
	Plans findById(String id);

}
