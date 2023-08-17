package org.ace.ws.factory;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ace.insurance.common.CoveragePlan;
import org.ace.insurance.common.MaritalStatus;
import org.ace.insurance.common.Packages;
import org.ace.insurance.common.SaleChannelType;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravel;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravellerInfo;
import org.ace.insurance.specailForeignTravel.SpecialTravellerBeneficaryInfo;
import org.ace.insurance.specailForeignTravel.SpecialTravellerChildInfo;
import org.ace.ws.model.outboundTravel.OutboundTravelDTO;

public class OutboundTravelFactory {


	public static List<OutboundTravelDTO> convertOutboundTravelListDTO(List<SpecialForeignTravel> spList){
		List<OutboundTravelDTO> dtoList = new ArrayList<>();
		for(SpecialForeignTravel sp : spList) {
			dtoList.add(convertOutboundTravelEntityToDTO(sp));
		}
		return dtoList;
	}
	
	
	
	public static OutboundTravelDTO convertOutboundTravelEntityToDTO(SpecialForeignTravel sp) {
		OutboundTravelDTO dto = new OutboundTravelDTO();
		dto.setId(sp.getId());
		dto.setCloudId(sp.getId());
		dto.setPassportIssuedCountry(sp.getPassportIssuedCountry());
		dto.setRegistrationNo(sp.getRegistrationNo());
		dto.setTransactionFees(sp.getTransactionFees());
		dto.setPremium(sp.getPremium());
		dto.setProposalNo(sp.getPolicyNo());
		dto.setPolicyNo(sp.getPolicyNo());
		dto.setSumInsured(sp.getSumInsured());
		dto.setDeleteStatus(sp.isDeleteStatus());
		dto.setConvert(sp.isConvert());
		dto.setOrderId(sp.getOrderId());
		dto.setSubmittedDate(sp.getSubmittedDate() != null ? sp.getSubmittedDate().getTime() : 0);
		dto.setPaymentDate(sp.getPaymentDate() != null ? sp.getPaymentDate().getTime() : 0);
		dto.setActivedPolicyStartDate(sp.getActivedPolicyStartDate() != null ? sp.getActivedPolicyStartDate().getTime() : 0);
		dto.setActivedPolicyEndDate(sp.getActivedPolicyEndDate() != null ? sp.getActivedPolicyEndDate().getTime() : 0);
		dto.setResponseStatus(sp.getResponseStatus());
		dto.setPaymentGateway(sp.getPaymentGateway());;
		dto.setProposalStatus(sp.getProposalStatus());;
		dto.setBuyerPlatForm(sp.getBuyerPlatForm());;
		dto.setTourismType(sp.getTourismType());;
		
		dto.setSaleChannelType(sp.getSaleChannelType());
		if(SaleChannelType.CUSTOMER_DIRECT.equals(sp.getSaleChannelType())) {
			dto.setSaleChannelType(SaleChannelType.WALKIN);
		}
		
		if(SaleChannelType.AGENT.equals(sp.getSaleChannelType())) {
			dto.setAgentId(sp.getAgentId());
			dto.setAgentName(sp.getAgentName());
			dto.setSaleChannelType(SaleChannelType.AGENT);
		}
		dto.setPeriodDay(sp.getPeriodMonth());
		switch (sp.getPeriodMonth()) {
			case 15:
				dto.setCoveragePlan(CoveragePlan.Day15);
				break;
			case 30:
				dto.setCoveragePlan(CoveragePlan.Day30);
				break;
			case 60:
				dto.setCoveragePlan(CoveragePlan.Day60);
				break;
			case 90:
				dto.setCoveragePlan(CoveragePlan.Day90);
				break;
			case 120:
				dto.setCoveragePlan(CoveragePlan.Day120);
				break;
			case 150:
				dto.setCoveragePlan(CoveragePlan.Day150);
				break;
			case 180:
				dto.setCoveragePlan(CoveragePlan.Day180);
				break;
			default:
				break;
		}
		
		if(sp.getSumInsured() == 10000) {
			dto.setPackages(Packages.USD10000);
		}else if (sp.getSumInsured() == 30000) {
			dto.setPackages(Packages.USD30000);
		}else {
			dto.setPackages(Packages.USD50000);
		}
		
		dto.setDepartureDate(sp.getActivedPolicyStartDate() != null ? sp.getActivedPolicyStartDate().getTime() : 0);
		dto.setPassportIssuedDate(sp.getTravellerInfo().getPassportExpireDate() != null ? sp.getTravellerInfo().getPassportExpireDate().getTime() : 0);
		dto.setTpaFee(sp.getTpaFee());
		dto.setAgentCommission(sp.getAgentCommission());
		dto.setSumInsured(sp.getSumInsured());
		
		
		dto.setCurrencyId(sp.getCurrencyId());
		dto.setCountryCode(sp.getTravellerInfo().getCountryCode());
		dto.setFullName(sp.getTravellerInfo().getFullName());
		dto.setDateOfBirth(sp.getTravellerInfo().getDateOfBirth() != null ? sp.getTravellerInfo().getDateOfBirth().getTime() : 0);
		dto.setGender(sp.getTravellerInfo().getGender());
		dto.setPassportNo(sp.getTravellerInfo().getPassportNo());
		dto.setContactNo(sp.getTravellerInfo().getContactNo());
		dto.setMyanmarAddress(sp.getTravellerInfo().getMyanmarAddress());
		dto.setResidentAddress(sp.getTravellerInfo().getResidentAddress());
		dto.setAge(sp.getTravellerInfo().getAge());
		dto.setEmail(sp.getTravellerInfo().getEmail());
		dto.setPassportExpireDate(sp.getTravellerInfo().getPassportExpireDate() != null ? sp.getTravellerInfo().getPassportExpireDate().getTime() : 0);
		dto.setResidentCountry(sp.getTravellerInfo().getResidentCountry());
		dto.setJourneyFrom(sp.getTravellerInfo().getJourneyFrom());
		dto.setJourneyTo(sp.getTravellerInfo().getJourneyTo());
		dto.setFatherName(sp.getTravellerInfo().getFatherName());
		dto.setRace(sp.getTravellerInfo().getRace());
		dto.setMaritalStatus(sp.getTravellerInfo().getMaritalStatus());
		dto.setOccupation(sp.getTravellerInfo().getOccupation());
		dto.setForeignContactNo(sp.getTravellerInfo().getForeignContactNo());
	
		dto.setBenCode(sp.getBeneficaryInfo().getBenCode());
		dto.setBenContactNo(sp.getBeneficaryInfo().getBenContactNo());
		dto.setBenDateOfBirth(sp.getBeneficaryInfo().getBenDateOfBirth() != null ? sp.getBeneficaryInfo().getBenDateOfBirth().getTime() : 0);
		dto.setBenEmail(sp.getBeneficaryInfo().getBenEmail());
		dto.setBenName(sp.getBeneficaryInfo().getBenName());
		dto.setBenNIDNo(sp.getBeneficaryInfo().getBenNIDNo());
		dto.setBenRelationship(sp.getBeneficaryInfo().getBenRelationship());
		dto.setBenResidentAddress(sp.getBeneficaryInfo().getBenResidentAddress());
		dto.setBenResidentCountry(sp.getBeneficaryInfo().getBenResidentCountry());
		
		if(sp.getChildInfo() != null) {
			dto.setcAge(sp.getChildInfo().getcAge());
			dto.setcBirthDate(sp.getChildInfo().getcBirthDate() != null ? sp.getChildInfo().getcBirthDate().getTime() : 0);
			dto.setcGender(sp.getChildInfo().getcGender());
			dto.setcName(sp.getChildInfo().getcName());
			dto.setcOtherName1(sp.getChildInfo().getcOtherName1());
			dto.setcOtherName2(sp.getChildInfo().getcOtherName2());
			dto.setcRelation(sp.getChildInfo().getcRelation());
			dto.setcStatus(sp.getChildInfo().iscStatus());
		}
			
		dto.setVersion(sp.getVersion());
		
		return dto;
	}
	

	
	
	public static SpecialForeignTravel convertOutboundTravelDTOToEntity(OutboundTravelDTO dto) {
		SpecialForeignTravel s = new SpecialForeignTravel();
		s.setId(dto.getId() == null ? null : dto.getId());
		s.setPassportIssuedCountry(dto.getPassportIssuedCountry() == null ? null : dto.getPassportIssuedCountry());
		s.setRegistrationNo(dto.getRegistrationNo() == null ? null : dto.getRegistrationNo());
		s.setTransactionFees(dto.getTransactionFees());
		s.setPremium(dto.getPremium());
		s.setPolicyNo(dto.getPolicyNo() == null ? null : dto.getPolicyNo());
		s.setSumInsured(dto.getSumInsured());
		s.setDeleteStatus(dto.isDeleteStatus());
		s.setConvert(dto.isConvert());
		s.setOrderId(dto.getOrderId() == null ? null : dto.getOrderId());
		s.setSubmittedDate(dto.getSubmittedDate() == 0 ? null : new Date(dto.getSubmittedDate()));
		s.setPaymentDate(dto.getPaymentDate() == 0 ? null : new Date(dto.getPaymentDate()));
		s.setActivedPolicyStartDate(dto.getActivedPolicyStartDate() == 0 ? null : new Date(dto.getActivedPolicyStartDate()));
		s.setActivedPolicyEndDate(dto.getActivedPolicyEndDate() == 0 ? null : new Date(dto.getActivedPolicyEndDate()));
		s.setSaleChannelType(dto.getSaleChannelType() == null ? null : dto.getSaleChannelType());
		s.setResponseStatus(dto.getResponseStatus() == null ? null : dto.getResponseStatus());
		s.setPaymentGateway(dto.getPaymentGateway() == null ? null : dto.getPaymentGateway());
		s.setProposalStatus(dto.getProposalStatus() == null ? null : dto.getProposalStatus());
		s.setBuyerPlatForm(dto.getBuyerPlatForm() == null ? null : dto.getBuyerPlatForm());
		s.setTourismType(dto.getTourismType() == null ? null : dto.getTourismType());
		if(SaleChannelType.AGENT.equals(dto.getSaleChannelType())) {
			s.setAgentId(dto.getAgentId());
		}
		s.setCurrencyId(dto.getCurrencyId() == null ? null : dto.getCurrencyId());
		
		SpecialForeignTravellerInfo travellerInfo = new SpecialForeignTravellerInfo();
			
		travellerInfo.setCountryCode(dto.getCountryCode() == null ? null : dto.getCountryCode());
		travellerInfo.setFullName(dto.getFullName() == null ? null : dto.getFullName());
		travellerInfo.setDateOfBirth(dto.getDateOfBirth() == 0 ? null : new Date(dto.getDateOfBirth()));
		travellerInfo.setGender(dto.getGender() == null ? null : dto.getGender());
		travellerInfo.setPassportNo(dto.getPassportNo() == null ? null : dto.getPassportNo());
		travellerInfo.setContactNo(dto.getContactNo() == null ? null : dto.getContactNo());
		travellerInfo.setMyanmarAddress(dto.getMyanmarAddress() == null ? null : dto.getMyanmarAddress());
		travellerInfo.setResidentAddress(dto.getResidentAddress() == null ? null : dto.getResidentAddress());
		travellerInfo.setAge(dto.getAge());
		travellerInfo.setEmail(dto.getEmail() == null ? null : dto.getEmail());
		travellerInfo.setPassportExpireDate(dto.getPassportExpireDate() == 0 ? null : new Date(dto.getPassportExpireDate()));
		travellerInfo.setResidentCountry(dto.getResidentCountry() == null ? null : dto.getResidentCountry());
		travellerInfo.setJourneyFrom(dto.getJourneyFrom() == null ? null : dto.getJourneyFrom());
		travellerInfo.setJourneyTo(dto.getJourneyTo() == null ? null : dto.getJourneyTo());
		travellerInfo.setFatherName(dto.getFatherName() == null ? null : dto.getFatherName());
		travellerInfo.setRace(dto.getRace() == null ? null : dto.getRace());
		travellerInfo.setMaritalStatus(dto.getMaritalStatus());
		travellerInfo.setDepartureDate(dto.getDepartureDate() == 0 ? null : new Date(dto.getDepartureDate()));
		travellerInfo.setOccupation(dto.getOccupation() == null ? null : dto.getOccupation());
		travellerInfo.setForeignContactNo(dto.getForeignContactNo() == null ? null : dto.getForeignContactNo());
		s.setTravellerInfo(travellerInfo);
		
		SpecialTravellerBeneficaryInfo benInfo = new SpecialTravellerBeneficaryInfo();
		
		benInfo.setBenCode(dto.getBenCode() == null ? null : dto.getBenCode());
		benInfo.setBenContactNo(dto.getBenContactNo() == null ? null : dto.getBenContactNo());
		benInfo.setBenDateOfBirth(dto.getBenDateOfBirth() == 0 ? null : new Date(dto.getBenDateOfBirth()));
		benInfo.setBenEmail(dto.getBenEmail() == null ? null : dto.getBenEmail());
		benInfo.setBenName(dto.getBenName() == null ? null :dto.getBenName());
		benInfo.setBenNIDNo(dto.getBenNIDNo() == null ? null : dto.getBenNIDNo());
		benInfo.setBenRelationship(dto.getBenRelationship() == null ? null : dto.getBenRelationship());
		benInfo.setBenResidentAddress(dto.getBenResidentAddress() == null ? null : dto.getBenResidentAddress());
		benInfo.setBenResidentCountry(dto.getBenResidentCountry() == null ? null : dto.getBenResidentCountry());
		s.setBeneficaryInfo(benInfo);
		
		SpecialTravellerChildInfo childInfo = new SpecialTravellerChildInfo();
		
		childInfo.setcAge(dto.getcAge());
		childInfo.setcBirthDate(dto.getcBirthDate() == 0 ? null : new Date(dto.getcBirthDate()));
		childInfo.setcGender(dto.getcGender() == null ? null : dto.getcGender());
		childInfo.setcName(dto.getcName() == null ? null : dto.getcName());
		childInfo.setcOtherName1(dto.getcOtherName1() == null ? null : dto.getcOtherName1());
		childInfo.setcOtherName2(dto.getcOtherName2() == null ? null : dto.getcOtherName2());
		childInfo.setcRelation(dto.getcRelation() == null ? null : dto.getcRelation());
		childInfo.setcStatus(dto.iscStatus());
		s.setChildInfo(childInfo);
		
		s.setVersion(dto.getVersion());
		
		return s;
	}
	
		
}
