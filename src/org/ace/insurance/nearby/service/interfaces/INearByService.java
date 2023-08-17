package org.ace.insurance.nearby.service.interfaces;

import java.util.List;

import org.ace.insurance.system.common.branch.Branch;
import org.ace.ws.model.nearby.NearByDTO;

public interface INearByService {
	public List<Branch> findNearByBranch(NearByDTO nearByDTO);
}
