package org.ace.ws.factory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ace.insurance.common.SaleChannelType;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravel;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravellerInfo;
import org.ace.insurance.specailForeignTravel.SpecialTravellerBeneficaryInfo;
import org.ace.insurance.specailForeignTravel.SpecialTravellerChildInfo;
import org.ace.ws.model.specialForeignTravel.SpecialForeignTravelDTO;

public class SpecailForeignTravelFactory {

	public static SpecialForeignTravel convertSpecailForeignTravel(SpecialForeignTravelDTO spForeignTravelDTO) {
		setActivePolicyEndDate(spForeignTravelDTO);
		return new SpecialForeignTravel(spForeignTravelDTO);
	}

	public static List<SpecialForeignTravelDTO> convertSpecailForeignTravelListDTO(List<SpecialForeignTravel> spList) {
		List<SpecialForeignTravelDTO> dtoList = new ArrayList<>();
		for (SpecialForeignTravel sp : spList) {
			dtoList.add(convertSpecailForeignTravelDTO(sp));
		}
		return dtoList;
	}

	public static SpecialForeignTravelDTO convertSpecailForeignTravelDTO(SpecialForeignTravel sp) {
		SpecialForeignTravelDTO dto = new SpecialForeignTravelDTO();
		dto.setId(sp.getId());
		dto.setActivedPolicyEndDate(sp.getActivedPolicyEndDate() != null ? sp.getActivedPolicyEndDate().getTime() : 0);
		dto.setActivedPolicyStartDate(
				sp.getActivedPolicyStartDate() != null ? sp.getActivedPolicyStartDate().getTime() : 0);
		dto.setTourismType(sp.getTourismType());
		dto.setSumInsured(sp.getSumInsured());
		dto.setSaleChannelType(sp.getSaleChannelType());
		if (SaleChannelType.AGENT.equals(dto.getSaleChannelType())) {
			dto.setAgentId(sp.getAgentId());
		}
		dto.setRegistrationNo(sp.getRegistrationNo());
		dto.setAge(sp.getTravellerInfo().getAge());
		dto.setContactNo(sp.getTravellerInfo().getContactNo());
		dto.setCountryCode(sp.getTravellerInfo().getCountryCode());
		dto.setCurrencyId(sp.getCurrencyId());
		dto.setDateOfBirth(
				sp.getTravellerInfo().getDateOfBirth() != null ? sp.getTravellerInfo().getDateOfBirth().getTime() : 0);
		dto.setEmail(sp.getTravellerInfo().getEmail());
		dto.setFullName(sp.getTravellerInfo().getFullName());
		dto.setGender(sp.getTravellerInfo().getGender());
		dto.setOrderId(sp.getOrderId());
		dto.setAssociationAgentId(sp.getAssociationAgentId());
		dto.setPassportExpireDate(sp.getTravellerInfo().getPassportExpireDate() != null
				? sp.getTravellerInfo().getPassportExpireDate().getTime()
				: 0);
		dto.setPassportNo(sp.getTravellerInfo().getPassportNo());
		dto.setPassportIssuedCountry(sp.getPassportIssuedCountry());
		dto.setPaymentDate(sp.getPaymentDate() != null ? sp.getPaymentDate().getTime() : 0);
		dto.setPeriodMonth(sp.getPeriodMonth());
		dto.setPolicyNo(sp.getPolicyNo());
		dto.setPremium(sp.getPremium());
		dto.setTransactionFees(sp.getTransactionFees());
		dto.setProposalStatus(sp.getProposalStatus());
		dto.setJourneyFrom(sp.getTravellerInfo().getJourneyFrom());
		dto.setMyanmarAddress(sp.getTravellerInfo().getMyanmarAddress());
		dto.setResidentAddress(sp.getTravellerInfo().getResidentAddress());
		dto.setJourneyTo(sp.getTravellerInfo().getJourneyTo());
		dto.setFatherName(sp.getTravellerInfo().getFatherName());
		dto.setRace(sp.getTravellerInfo().getRace());
		dto.setMaritalStatus(sp.getTravellerInfo().getMaritalStatus());
		dto.setDepartureDate(
				sp.getTravellerInfo().getDepartureDate() != null ? sp.getTravellerInfo().getDepartureDate().getTime()
						: 0);
		dto.setOccupation(sp.getTravellerInfo().getOccupation());
		dto.setForeignContactNo(sp.getTravellerInfo().getForeignContactNo());
		dto.setBenContactNo(sp.getBeneficaryInfo().getBenContactNo());
		dto.setBenDateOfBirth(sp.getBeneficaryInfo().getBenDateOfBirth() != null
				? sp.getBeneficaryInfo().getBenDateOfBirth().getTime()
				: 0);
		dto.setBenEmail(sp.getBeneficaryInfo().getBenEmail());
		dto.setBenName(sp.getBeneficaryInfo().getBenName());
		dto.setBenNIDNo(sp.getBeneficaryInfo().getBenNIDNo());
		dto.setBenRelationship(sp.getBeneficaryInfo().getBenRelationship());
		dto.setBenResidentAddress(sp.getBeneficaryInfo().getBenResidentAddress());
		dto.setResidentCountry(sp.getTravellerInfo().getResidentCountry());
		dto.setBenCode(sp.getBeneficaryInfo().getBenCode());
		dto.setBenResidentCountry(sp.getBeneficaryInfo().getBenResidentCountry());
		if (sp.getChildInfo() != null) {
			dto.setcAge(sp.getChildInfo().getcAge());
			dto.setcBirthDate(
					sp.getChildInfo().getcBirthDate() != null ? sp.getChildInfo().getcBirthDate().getTime() : 0);
			dto.setcName(sp.getChildInfo().getcName());
			dto.setcOtherName1(sp.getChildInfo().getcOtherName1());
			dto.setcOtherName2(sp.getChildInfo().getcOtherName2());
			dto.setcRelation(sp.getChildInfo().getcRelation());
			dto.setcStatus(sp.getChildInfo().iscStatus());
			dto.setcGender(sp.getChildInfo().getcGender());
		}
		dto.setBuyerPlatForm(sp.getBuyerPlatForm());
		dto.setPaymentGateWay(sp.getPaymentGateway());
		dto.setSubmittedDate(sp.getSubmittedDate() != null ? sp.getSubmittedDate().getTime() : 0);
		dto.setTpaFee(sp.getTpaFee());
		dto.setAgentName(sp.getAgentName());
		dto.setAgentCommission(sp.getAgentCommission());

		dto.setAgentName(sp.getAgentName());
		dto.setAgentLicenseNo(sp.getAgentLicenseNo());
		dto.setPassportIssuedCountry(sp.getPassportIssuedCountry());
		dto.setPassportIssuedDate(sp.getPassportIssuedDate() != null ? sp.getPassportIssuedDate().getTime() : 0);
		dto.setCancellationReason(dto.getCancellationReason());
		dto.setUlinkStatus(dto.isUlinkStatus());
		dto.setUlinkEdit(dto.isUlinkEdit());
		dto.setOnlineEdit(dto.isOnlineEdit());
		dto.setCoveragePlan(dto.getCoveragePlan());
		dto.setPackages(dto.getPackages());
		dto.setProductId(dto.getProductId());
		return dto;
	}

	public static SpecialForeignTravel updateSpecialForeignInfo(SpecialForeignTravel sp, SpecialForeignTravelDTO dto) {
		sp.setActivedPolicyEndDate(new Date(dto.getActivedPolicyEndDate()));
		sp.setActivedPolicyStartDate(new Date(dto.getActivedPolicyStartDate()));
		SpecialTravellerBeneficaryInfo ben = new SpecialTravellerBeneficaryInfo();
		ben.setBenCode(dto.getBenCode());
		ben.setBenContactNo(dto.getBenContactNo());
		ben.setBenDateOfBirth(new Date(dto.getBenDateOfBirth()));
		ben.setBenEmail(dto.getBenEmail());
		ben.setBenName(dto.getBenName());
		ben.setBenNIDNo(dto.getBenNIDNo());
		ben.setBenRelationship(dto.getBenRelationship());
		ben.setBenResidentAddress(dto.getBenResidentAddress());
		ben.setBenResidentCountry(dto.getBenResidentCountry());
		sp.setBeneficaryInfo(ben);
		SpecialTravellerChildInfo cInfo = new SpecialTravellerChildInfo();
		cInfo.setcAge(dto.getcAge());
		cInfo.setcBirthDate(new Date(dto.getcBirthDate()));
		cInfo.setcGender(dto.getcGender());
		cInfo.setcName(dto.getcName());
		cInfo.setcOtherName1(dto.getcOtherName1());
		cInfo.setcRelation(dto.getcRelation());
		sp.setChildInfo(cInfo);
		sp.setPassportIssuedCountry(dto.getPassportIssuedCountry());
		sp.setPeriodMonth(dto.getPeriodMonth());
		sp.setProposalStatus(dto.getProposalStatus());
		sp.setSumInsured(dto.getSumInsured());
		sp.setRegistrationNo(dto.getRegistrationNo());
		sp.setTransactionFees(dto.getTransactionFees());
		sp.setPremium(dto.getPremium());
		sp.setPeriodMonth(dto.getPeriodMonth());
		sp.setPolicyNo(dto.getPolicyNo());
		sp.setCurrencyId(dto.getCurrencyId());
		sp.setDeleteStatus(dto.isDeleteStatus());
		sp.setAgentId(dto.getAgentId());
		sp.setSaleChannelType(dto.getSaleChannelType());
		sp.setAssociationAgentId(dto.getAssociationAgentId());
		SpecialForeignTravellerInfo tInfo = new SpecialForeignTravellerInfo();
		tInfo.setAge(dto.getAge());
		tInfo.setContactNo(dto.getContactNo());
		tInfo.setCountryCode(dto.getCountryCode());
		tInfo.setDateOfBirth(new Date(dto.getDateOfBirth()));
		tInfo.setEmail(dto.getEmail());
		tInfo.setFullName(dto.getFullName());
		tInfo.setGender(dto.getGender());
		tInfo.setJourneyFrom(dto.getJourneyFrom());
		tInfo.setJourneyTo(dto.getJourneyTo());
		tInfo.setFatherName(dto.getFatherName());
		tInfo.setRace(dto.getRace());
		tInfo.setMaritalStatus(dto.getMaritalStatus());
		tInfo.setDepartureDate(new Date(dto.getDepartureDate()));
		tInfo.setOccupation(dto.getOccupation());
		tInfo.setForeignContactNo(dto.getForeignContactNo());
		tInfo.setMyanmarAddress(dto.getMyanmarAddress());
		tInfo.setPassportExpireDate(new Date(dto.getPassportExpireDate()));
		tInfo.setPassportNo(dto.getPassportNo());
		tInfo.setResidentAddress(dto.getResidentAddress());
		tInfo.setResidentCountry(dto.getResidentCountry());
		sp.setTravellerInfo(tInfo);

		return sp;
	}

	public static LocalDate convertToLocalDate(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static Date convertToDate(LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert);
	}

	public static void setActivePolicyEndDate(SpecialForeignTravelDTO spForeignTravelDTO) {
		if (spForeignTravelDTO.getActivedPolicyStartDate() != 0) {

			LocalDate convertedDate = convertToLocalDate(new Date(spForeignTravelDTO.getActivedPolicyStartDate()));

			switch (spForeignTravelDTO.getPeriodMonth()) {
			case 5:
				spForeignTravelDTO.setActivedPolicyEndDate(convertToDate(convertedDate.plusDays(4)).getTime());
				break;
			case 10:
				spForeignTravelDTO.setActivedPolicyEndDate(convertToDate(convertedDate.plusDays(9)).getTime());
				break;
			case 15:
				spForeignTravelDTO.setActivedPolicyEndDate(convertToDate(convertedDate.plusDays(14)).getTime());
				break;
			case 30:
				spForeignTravelDTO.setActivedPolicyEndDate(convertToDate(convertedDate.plusDays(29)).getTime());
				break;
			case 60:
				spForeignTravelDTO.setActivedPolicyEndDate(convertToDate(convertedDate.plusDays(59)).getTime());
				break;
			case 90:
				spForeignTravelDTO.setActivedPolicyEndDate(convertToDate(convertedDate.plusDays(89)).getTime());
				break;
			case 120:
				spForeignTravelDTO.setActivedPolicyEndDate(convertToDate(convertedDate.plusDays(119)).getTime());
				break;
			case 150:
				spForeignTravelDTO.setActivedPolicyEndDate(convertToDate(convertedDate.plusDays(149)).getTime());
				break;
			case 180:
				spForeignTravelDTO.setActivedPolicyEndDate(convertToDate(convertedDate.plusDays(179)).getTime());
				break;
			default:
				break;
			}
		}
	}

}
