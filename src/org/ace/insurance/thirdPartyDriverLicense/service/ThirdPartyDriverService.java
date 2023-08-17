package org.ace.insurance.thirdPartyDriverLicense.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.common.IdType;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.SystemConstants;
import org.ace.insurance.system.common.branch.persistence.interfaces.IBranchDAO;
import org.ace.insurance.system.common.currency.service.interfaces.ICurrencyService;
import org.ace.insurance.thirdPartyDriverLicense.ThirdPartyDriverInfo;
import org.ace.insurance.thirdPartyDriverLicense.ThirdPartyDriverProposal;
import org.ace.insurance.thirdPartyDriverLicense.persistence.interfaces.IThirdPartyDriverDAO;
import org.ace.insurance.thirdPartyDriverLicense.persistence.interfaces.ITypeOfDriverDAO;
import org.ace.insurance.thirdPartyDriverLicense.service.interfaces.IThirdPartyDriverService;
import org.ace.insurance.thirdPartyDriverLicense.service.interfaces.ITypeOfDriverService;
import org.ace.java.component.SystemException;
import org.ace.java.component.idgen.exception.CustomIDGeneratorException;
import org.ace.java.component.idgen.service.interfaces.ICustomIDGenerator;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.factory.ThirdPartyDriverFactory;
import org.ace.ws.model.thirdParty.TPDProposalDTO;
import org.springframework.stereotype.Service;

@Service("ThirdPartyDriverService")
public class ThirdPartyDriverService implements IThirdPartyDriverService {

	@Resource(name = "ThirdPartyDriverDAO")
	private IThirdPartyDriverDAO thirdPartyDriverDAO;

	@Resource(name = "TypeOfDriverDAO")
	private ITypeOfDriverDAO typeOfDriverDAO;

	@Resource(name = "CustomIDGenerator")
	protected ICustomIDGenerator customIDGenerator;

	@Resource(name = "BranchDAO")
	protected IBranchDAO branchDAO;

	@Resource(name = "CurrencyService")
	protected ICurrencyService currencyService;

	@Resource(name = "TypeOfDriverService")
	protected ITypeOfDriverService typeOfDriverService;

	@Override
	public double calculatePremium(Long typeOfDriverId) {
		double premiumRate = typeOfDriverDAO.findPremiumRateById(typeOfDriverId);
		int year = typeOfDriverDAO.findYearById(typeOfDriverId);
		return premiumRate * year;
	}

	@Override
	public ThirdPartyDriverProposal updateByPaymentStatus(String order_id) {
		ThirdPartyDriverProposal result = null;
		try {
			String policyNo = customIDGenerator.getNextId(SystemConstants.THIRD_PARTY_POLICY_ID_GEN, "");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String paymentDate = dateFormat.format(new Date());
			result = thirdPartyDriverDAO.updateByPaymentStatus(order_id, paymentDate, policyNo);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a thirdPartyDriverDAO ", e);
		}
		return result;
	}

	@Override
	public List<ThirdPartyDriverProposal> findByOrderId(String order_id) {
		return thirdPartyDriverDAO.findByOrderId(order_id);
	}

}
