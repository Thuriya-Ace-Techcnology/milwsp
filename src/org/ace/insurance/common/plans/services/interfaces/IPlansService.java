package org.ace.insurance.common.plans.services.interfaces;

import java.util.List;

import org.ace.insurance.common.plans.Plans;
import org.ace.insurance.life.dto.PlansDTO;


public interface IPlansService {

	List<PlansDTO> getPlanList();
	
	Plans findById(String id);

}
