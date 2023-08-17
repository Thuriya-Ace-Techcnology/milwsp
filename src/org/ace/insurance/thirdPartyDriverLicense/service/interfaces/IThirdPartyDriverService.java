package org.ace.insurance.thirdPartyDriverLicense.service.interfaces;

import java.util.List;

import org.ace.insurance.thirdPartyDriverLicense.ThirdPartyDriverProposal;

public interface IThirdPartyDriverService {

	public double calculatePremium(Long typeOfDriverId);

	public ThirdPartyDriverProposal updateByPaymentStatus(String order_id);

	public List<ThirdPartyDriverProposal> findByOrderId(String order_id);

}
