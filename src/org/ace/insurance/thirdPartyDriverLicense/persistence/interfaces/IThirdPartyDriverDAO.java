package org.ace.insurance.thirdPartyDriverLicense.persistence.interfaces;

import java.util.List;

import org.ace.insurance.thirdPartyDriverLicense.ThirdPartyDriverProposal;
import org.ace.java.component.persistence.exception.DAOException;

public interface IThirdPartyDriverDAO {

	public ThirdPartyDriverProposal updateByPaymentStatus(String order_id, String paymentDate, String policyNo);

	public List<ThirdPartyDriverProposal> findByOrderId(String order_id);

}
