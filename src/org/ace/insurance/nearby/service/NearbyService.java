package org.ace.insurance.nearby.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.common.Location;
import org.ace.insurance.common.NearestBranchCalculater;
import org.ace.insurance.nearby.service.interfaces.INearByService;
import org.ace.insurance.system.common.branch.Branch;
import org.ace.insurance.system.common.branch.service.interfaces.IBranchService;
import org.ace.java.component.service.BaseService;
import org.ace.ws.model.nearby.NearByDTO;
import org.springframework.stereotype.Service;

@Service(value = "NearbyService")
public class NearbyService extends BaseService implements INearByService{

	@Resource(name = "BranchService")
	private IBranchService branchService;
	@Override
	public List<Branch> findNearByBranch(NearByDTO nearByDTO) {
		List<Branch> branchList = new ArrayList<Branch>();
		Location customerLocation = new Location(nearByDTO.getLatitude(), nearByDTO.getLongitude());
		List<Branch> resultList = branchService.findAllBranch();
		resultList.stream().filter(result -> result.getLatitude() != 0.0 && result.getLongitude() != 0.0).forEach(result ->{
			Location branchLocation = new Location(result.getLatitude(),result.getLongitude());
			double resultRange = NearestBranchCalculater.distance(customerLocation, branchLocation);
			if(resultRange <= nearByDTO.getRange()) {
				branchList.add(result);
			}
		});
		return branchList;
	}
	
	

}
