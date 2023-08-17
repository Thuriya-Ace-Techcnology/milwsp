package org.ace.ws.factory;

import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.thirdPartyDriverLicense.ThirdPartyDriverInfo;
import org.ace.insurance.thirdPartyDriverLicense.ThirdPartyDriverProposal;
import org.ace.ws.model.thirdParty.TPDInfoDTO;
import org.ace.ws.model.thirdParty.TPDProposalDTO;

public class ThirdPartyDriverFactory {

	public static ThirdPartyDriverProposal convertThirdPartyDriverProposal(TPDProposalDTO dto) {
		ThirdPartyDriverProposal proposal = new ThirdPartyDriverProposal();
		List<ThirdPartyDriverInfo> infoList = new ArrayList<ThirdPartyDriverInfo>();
		ThirdPartyDriverInfo info = new ThirdPartyDriverInfo();
		proposal.setOrderId(dto.getOrderId());

		for (TPDInfoDTO infoDto : dto.getDriverInfoList()) {

			info.setIdType(infoDto.getIdType());
			info.setIdNo(infoDto.getIdNo());
			info.setName(infoDto.getName());
			info.setAddress(infoDto.getAddress());
			info.setDriverCodeNo(infoDto.getDriverCodeNo());
			info.setContactNo(infoDto.getContactNo());
			info.setDob(infoDto.getDob());
			info.setStartDate(infoDto.getStartDate());
			info.setEndDate(infoDto.getEndDate());
			info.setPremium(infoDto.getPremium());
			info.setPeriodOfYear(infoDto.getPeriodOfYear());

			infoList.add(info);
		}
		proposal.setThirdPartyDriverInfo(infoList);
		return proposal;
	}

	public static TPDProposalDTO convertThirdPartyDriverDTO(ThirdPartyDriverProposal proposal) {
		TPDProposalDTO dto = new TPDProposalDTO();
		dto.setSubmittedDate(proposal.getSubmittedDate());
		dto.setCurrencyId(proposal.getCurrency().getId());
		dto.setOrderId(proposal.getOrderId());
		dto.setProposalNo(proposal.getProposalNo());
		dto.setProposalStatus(proposal.getProposalStatus());
		dto.setPaymentDate(proposal.getPaymentDate());
		dto.setBranchId(proposal.getBranch().getId());

		TPDInfoDTO infoDto = new TPDInfoDTO();
		List<TPDInfoDTO> infoList = new ArrayList<TPDInfoDTO>();

		for (ThirdPartyDriverInfo info : proposal.getThirdPartyDriverInfo()) {

			infoDto.setIdType(info.getIdType());
			infoDto.setIdNo(info.getIdNo());
			infoDto.setName(info.getName());
			infoDto.setAddress(info.getAddress());
			infoDto.setDriverCodeNo(info.getDriverCodeNo());
			infoDto.setContactNo(info.getContactNo());
			infoDto.setDob(info.getDob());
			infoDto.setStartDate(info.getStartDate());
			infoDto.setEndDate(info.getEndDate());
			infoDto.setPremium(info.getPremium());
			infoDto.setPeriodOfYear(info.getPeriodOfYear());
			infoDto.setTypeOfDriverId(info.getTypeOfDriver().getId());

			infoList.add(infoDto);

		}
		dto.setDriverInfoList(infoList);
		return dto;

	}

	public static List<TPDProposalDTO> convertThirdPartyDriverListDTO(
			List<ThirdPartyDriverProposal> thirdPartyDriverProposalList) {
		List<TPDProposalDTO> dtoList = new ArrayList<>();
		List<TPDInfoDTO> infoList = new ArrayList<>();
		for (ThirdPartyDriverProposal proposal : thirdPartyDriverProposalList) {
			TPDProposalDTO dto = convertThirdPartyDriverDTO(proposal);
			TPDInfoDTO infoDto = new TPDInfoDTO();
			dto.setSubmittedDate(proposal.getSubmittedDate());
			dto.setCurrencyId(proposal.getCurrency().getId());
			dto.setOrderId(proposal.getOrderId());
			dto.setProposalNo(proposal.getProposalNo());
			dto.setProposalStatus(proposal.getProposalStatus());
			dto.setPaymentDate(proposal.getPaymentDate());
			dto.setBranchId(proposal.getBranch().getId());
			dto.setId(proposal.getId());
			for (ThirdPartyDriverInfo info : proposal.getThirdPartyDriverInfo()) {

				infoDto.setIdType(info.getIdType());
				infoDto.setIdNo(info.getIdNo());
				infoDto.setName(info.getName());
				infoDto.setAddress(info.getAddress());
				infoDto.setDriverCodeNo(info.getDriverCodeNo());
				infoDto.setContactNo(info.getContactNo());
				infoDto.setDob(info.getDob());
				infoDto.setStartDate(info.getStartDate());
				infoDto.setEndDate(info.getEndDate());
				infoDto.setPremium(info.getPremium());
				infoDto.setPeriodOfYear(info.getPeriodOfYear());
				infoDto.setTypeOfDriverId(info.getTypeOfDriver().getId());

				infoList.add(infoDto);
			}
			dto.setDriverInfoList(infoList);
			dtoList.add(dto);
		}

		return dtoList;
	}

}
