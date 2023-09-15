package org.ace.insurance.common.plans.persistences.interfaces;

import java.util.List;

import org.ace.insurance.common.plans.Plans;

public interface IPlansDAO {

	List<Plans> findAll();
	
	Plans findById(String id);

}
