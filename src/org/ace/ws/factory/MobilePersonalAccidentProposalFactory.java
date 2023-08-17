package org.ace.ws.factory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.personalAccident.GInsuredPersonAddon;
import org.ace.insurance.personalAccident.GInsuredPersonBeneficiaries;
import org.ace.insurance.personalAccident.MobilePersonalAccidentInsuredPerson;
import org.ace.insurance.personalAccident.MobilePersonalAccidentProposal;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.mobilePersonalAccidentproposal.MBP001;
import org.ace.ws.model.mobilePersonalAccidentproposal.MIPA001;
import org.ace.ws.model.mobilePersonalAccidentproposal.MPAP001;
import org.ace.ws.model.mobilePersonalAccidentproposal.PAIP001;
import org.ace.ws.model.mobilePersonalAccidentproposal.PAInsuredPerson001;
import org.ace.ws.model.mobilePersonalAccidentproposal.PAInsuredPerson002;

public class MobilePersonalAccidentProposalFactory {
	public static MobilePersonalAccidentProposal convertMobilePAProposal(MPAP001 mpap001) {

		MobilePersonalAccidentProposal result = new MobilePersonalAccidentProposal(mpap001);
		return result;
	}

	public static MPAP001 convertMobilePAProposalDTO(MobilePersonalAccidentProposal paProposal) throws UnsupportedEncodingException {

		MPAP001 dto = new MPAP001();
		dto.setId(paProposal.getId());
		dto.setCurrencyId(paProposal.getCurrencyId());
		dto.setPeriodMonth(paProposal.getPeriodMonth());
		dto.setTransactionId(paProposal.getTransactionId());
		dto.setUserId(paProposal.getUserId());
		dto.setProposalNo(paProposal.getProposalNo());
		dto.setOrderId(paProposal.getOrderId());
		dto.setDeleteStatus(paProposal.isDeleteStatus());
		dto.setPolicyNo(paProposal.getPolicyNo());
		dto.setPaymentTypeId(paProposal.getPaymentTypeId());
		dto.setPaymentDate(paProposal.getPaymentDate() == null ? "" : paProposal.getPaymentDate().toString());
		dto.setSubmittedDate(paProposal.getSubmittedDate().getTime());
		dto.setProposalStatus(paProposal.getProposalStatus());
		dto.setActivedPolicyStartDate(paProposal.getActivedPolicyStartDate().getTime());
		dto.setTransactionFees(paProposal.getTransactionFees());
		dto.setActivedPolicyEndDate(paProposal.getActivedPolicyEndDate().getTime());
		dto.setResponseStatus(ResponseStatus.SUCCESS);

		List<PAIP001> insuredPersonList = new ArrayList<PAIP001>();
		for (MobilePersonalAccidentInsuredPerson p : paProposal.getInsuredPersonList()) {
			PAIP001 pDTO = new PAIP001();
			pDTO.setId(p.getId());
			pDTO.setInitialId(p.getInitialId() == null ? p.getInitialId() : URLEncoder.encode(p.getInitialId(), "UTF-8"));
			pDTO.setFirstName(p.getFirstName() == null ? p.getFirstName() : URLEncoder.encode(p.getFirstName(), "UTF-8"));
			pDTO.setMiddleName(p.getMiddleName() == null ? p.getMiddleName() : URLEncoder.encode(p.getMiddleName(), "UTF-8"));
			pDTO.setLastName(p.getLastName() == null ? p.getLastName() : URLEncoder.encode(p.getLastName(), "UTF-8"));
			pDTO.setFatherName(p.getFatherName() == null ? p.getFatherName() : URLEncoder.encode(p.getFatherName(), "UTF-8"));
			pDTO.setIdNo(p.getIdNo());
			pDTO.setDateOfBirth(p.getDateOfBirth().getTime());
			pDTO.setAge(p.getAge());
			pDTO.setSumInsured(p.getSumInsured());
			pDTO.setPremium(p.getPremium());
			pDTO.setGender(p.getGender());
			pDTO.setIdType(p.getIdType());
			pDTO.setAddress(p.getAddress() == null ? p.getAddress() : URLEncoder.encode(p.getAddress(), "UTF-8"));
			pDTO.setProductId(p.getProductId());
			pDTO.setOccupationId(p.getOccupationId());
			pDTO.setTownshipId(p.getTownshipId());
			insuredPersonList.add(pDTO);
			for (GInsuredPersonAddon addOn : p.getInsuredPersonAddOnList()) {
				MIPA001 addOnDto = new MIPA001();
				addOnDto.setId(addOn.getId());
				addOnDto.setPremium(addOn.getPremium());
				addOnDto.setSumInsured(addOn.getSumInsured());
				addOnDto.setAddOnId(addOn.getAddOnId());
				insuredPersonList.get(0).getInsuredPersonAddOnList().add(addOnDto);
			}

			for (GInsuredPersonBeneficiaries beneficiary : p.getInsuredPersonBeneficiariesList()) {
				MBP001 beneficDto = new MBP001();
				beneficDto.setId(beneficiary.getId());
				beneficDto.setMiddleName(beneficiary.getMiddleName() == null ? beneficiary.getMiddleName() : URLEncoder.encode(beneficiary.getMiddleName(), "UTF-8"));
				beneficDto.setLastName(beneficiary.getLastName() == null ? beneficiary.getLastName() : URLEncoder.encode(beneficiary.getLastName(), "UTF-8"));
				beneficDto.setFirstName(beneficiary.getFirstName() == null ? beneficiary.getFirstName() : URLEncoder.encode(beneficiary.getFirstName(), "UTF-8"));
				beneficDto.setInitialId(beneficiary.getInitialId() == null ? beneficiary.getInitialId() : URLEncoder.encode(beneficiary.getInitialId(), "UTF-8"));
				beneficDto.setIdNo(beneficiary.getIdNo());
				beneficDto.setIdType(beneficiary.getIdType());
				beneficDto.setGender(beneficiary.getGender());
				beneficDto.setAddress(beneficiary.getAddress() == null ? beneficiary.getAddress() : URLEncoder.encode(beneficiary.getAddress(), "UTF-8"));
				beneficDto.setDateOfBirth(beneficiary.getDateOfBirth().getTime());
				beneficDto.setRelationshipId(beneficiary.getRelationshipId());
				beneficDto.setTownshipId(beneficiary.getTownshipId());
				beneficDto.setPercentage(beneficiary.getPercentage());
				insuredPersonList.get(0).getInsuredPersonBeneficiariesList().add(beneficDto);

			}

		}
		dto.setInsuredPersonList(insuredPersonList);
		return dto;
	}

	public static List<MPAP001> convertMobilePAProposalDTOList(List<MobilePersonalAccidentProposal> paProposalList) throws UnsupportedEncodingException {
		List<MPAP001> result = new ArrayList<MPAP001>();
		if (paProposalList != null)
			for (MobilePersonalAccidentProposal p : paProposalList) {
				result.add(convertMobilePAProposalDTO(p));
			}
		return result;
	}

	public static List<PAInsuredPerson002> convertMobilePADTOList(List<PAInsuredPerson001> paProposalList) throws UnsupportedEncodingException {
		List<PAInsuredPerson002> result = new ArrayList<PAInsuredPerson002>();
		if (paProposalList != null) {
			for (PAInsuredPerson001 p : paProposalList) {
				PAInsuredPerson002 person2 = new PAInsuredPerson002();
				person2.setInsuredPersonName((p.getInitialId() == null ? "" : p.getInitialId()) + " " + (p.getFirstName() == null ? "" : p.getFirstName()) + " "
						+ (p.getMiddleName() == null ? "" : p.getMiddleName()) + " " + (p.getLastName() == null ? "" : p.getLastName()));
				person2.setPolicyNo(p.getPolicyNo());
				person2.setPremium(p.getPremium() + p.getAddOnPremium());
				person2.setProposalNo(p.getProposalNo());
				person2.setCurrencyId(p.getCurrencyId());
				person2.setOrderId(p.getOrderId());
				person2.setProposalStatus(p.getProposalStatus());
				person2.setSubmittedDate(p.getSubmittedDate().getTime());
				person2.setSumInsured(p.getSumInsured());
				person2.setTransactionFees(p.getTransactionFees());
				result.add(person2);
			}

		}
		return result;
	}

	// public static List<PAInsuredPerson001>
	// convertMobilePAInsurancedPersonDTOList(List<PAInsuredPerson001>
	// paPersonList) throws UnsupportedEncodingException {
	// List<PAInsuredPerson001> resultList = new
	// ArrayList<PAInsuredPerson001>();
	// if (paPersonList != null)
	// for (PAInsuredPerson001 p : paPersonList) {
	//
	// PAInsuredPerson001 dto = new PAInsuredPerson001();
	// dto.setInsuredPersonName(p.getInsuredPersonName());
	// dto.setPremium(p.getPremium());
	// dto.setSumInsured(p.getSumInsured());
	// dto.setProposalStatus(p.getProposalStatus());
	// dto.setPolicyNo(p.getPolicyNo());
	// dto.setProposalNo(p.getProposalNo());
	// dto.setSubmittedDate(p.getCreatedDate().getTime());
	// resultList.add(p);
	// }
	// return resultList;
	// }
}
