package org.ace.ws.factory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ace.insurance.onlineBiller.OnlineBillerBuyer;
import org.ace.ws.model.onlineBiller.OnlineBiller;
import org.ace.ws.model.onlineBiller.OnlineBillerBuyerDailyReportFor2c2pDTO;
import org.ace.ws.model.onlineBiller.OnlineBillerDTO;

public class OnlineBillerProposalFactory {
	public static OnlineBiller convertOnlineBillerProposal(OnlineBillerDTO dto) {
		OnlineBiller result = new OnlineBiller(dto);
		return result;
	}

	public static List<OnlineBillerDTO> convertOnlineBillerByMap(OnlineBillerDTO dto) {
		List<OnlineBillerDTO> result = new ArrayList<OnlineBillerDTO>();
		OnlineBillerDTO onlineBillerDTO = new OnlineBillerDTO();

		onlineBillerDTO.setId(dto.getId());
		onlineBillerDTO.setInvoiceNo(dto.getInvoiceNo());
		onlineBillerDTO.setPolicyNo(dto.getPolicyNo());
		onlineBillerDTO.setPolicyOwnerName(dto.getPolicyOwnerName());
		onlineBillerDTO.setAgentName(dto.getAgentName());
		onlineBillerDTO.setLisceneceNo(dto.getLisceneceNo());
		onlineBillerDTO.setInterest(dto.getInterest());
		onlineBillerDTO.setDepartment(dto.getDepartment());
		onlineBillerDTO.setPremium(dto.getPremium());
		onlineBillerDTO.setSumInsured(dto.getSumInsured());
		onlineBillerDTO.setLocked(dto.isLocked());
		onlineBillerDTO.setActivedPolicyStartDate(dto.getActivedPolicyStartDate());
		onlineBillerDTO.setActivedPolicyEndDate(dto.getActivedPolicyEndDate());
		onlineBillerDTO.setSubmittedDate(dto.getSubmittedDate());
		onlineBillerDTO.setServiceCharges(dto.getServiceCharges());

		result.add(onlineBillerDTO);
		return result;
	}

	public static List<OnlineBillerDTO> convertOnlineBillerBuyerListToDTO(List<OnlineBillerBuyer> buyerList) {
		List<OnlineBillerDTO> dtoList = null;
		if (buyerList != null && !buyerList.isEmpty()) {
			dtoList = new ArrayList<>();
			for (OnlineBillerBuyer buyer : buyerList) {
				dtoList.add(convertOnlineBillerBuyerToDTO(buyer));
			}
		}
		return dtoList;
	}

	private static OnlineBillerDTO convertOnlineBillerBuyerToDTO(OnlineBillerBuyer buyer) {
		OnlineBillerDTO dto = null;
		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
		if (buyer != null) {
			dto = new OnlineBillerDTO();
			dto.setActivedPolicyEndDate(
					buyer.getActivedPolicyEndDate() != null ? fmt1.format(buyer.getActivedPolicyEndDate()) : null);
			dto.setActivedPolicyStartDate(
					buyer.getActivedPolicyStartDate() != null ? fmt1.format(buyer.getActivedPolicyStartDate()) : null);
			dto.setAgentName(buyer.getAgentName());
			dto.setDepartment(buyer.getDepartment());
			dto.setId(buyer.getId());
			dto.setInterest(buyer.getInterest());
			dto.setInvoiceNo(buyer.getInvoiceNo());
			dto.setLisceneceNo(buyer.getInvoiceNo());
			dto.setOrderId(buyer.getOrderId());
			dto.setBuyerPlatForm(buyer.getBuyerPlatForm());
			dto.setProposalStatus(buyer.getProposalStatus());
			dto.setPolicyNo(buyer.getPolicyNo());
			dto.setPolicyOwnerName(buyer.getPolicyOwnerName());
			dto.setPremium(buyer.getPremium());
			dto.setSumInsured(buyer.getSumInsured());
			dto.setStampFees(buyer.getStampfees());
			dto.setPaymentDate(buyer.getPaymentDate() != null ? fmt1.format(buyer.getPaymentDate()) : null);
			dto.setServiceCharges(buyer.getServiceCharges());
		}

		return dto;
	}

	public static OnlineBillerDTO convertOnlineBillerToDTO(OnlineBiller onlineBiller) {
		OnlineBillerDTO dto = null;
		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
		if (onlineBiller != null) {
			dto = new OnlineBillerDTO();
			dto.setActivedPolicyEndDate(fmt1.format(onlineBiller.getActivedPolicyEndDate()));
			dto.setActivedPolicyStartDate(fmt1.format(onlineBiller.getActivedPolicyStartDate()));
			dto.setAgentName(onlineBiller.getAgentName());
			dto.setDepartment(onlineBiller.getDepartment());
			dto.setId(onlineBiller.getId());
			dto.setInterest(onlineBiller.getInterest());
			dto.setInvoiceNo(onlineBiller.getInvoiceNo());
			dto.setBought(onlineBiller.isBought());
			dto.setLisceneceNo(onlineBiller.getLisceneceNo());
			dto.setPolicyNo(onlineBiller.getPolicyNo());
			dto.setPolicyOwnerName(onlineBiller.getPolicyOwnerName());
			dto.setPremium(onlineBiller.getPremium());
			dto.setStampFees(onlineBiller.getStampFees());
			dto.setSumInsured(onlineBiller.getSumInsured());
			dto.setPaymentDate(
					onlineBiller.getPaymentDate() != null ? fmt1.format(onlineBiller.getPaymentDate()) : null);
		}
		return dto;
	}

	public static OnlineBillerBuyer convertOnlineBillerDTOToEntity(OnlineBillerDTO dto) {
		OnlineBillerBuyer buyer = new OnlineBillerBuyer();
		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			buyer.setActivedPolicyEndDate(
					dto.getActivedPolicyEndDate() != null ? fmt1.parse(dto.getActivedPolicyEndDate()) : null);
			buyer.setActivedPolicyStartDate(
					dto.getActivedPolicyStartDate() != null ? fmt1.parse(dto.getActivedPolicyStartDate()) : null);
			buyer.setPaymentDate(dto.getPaymentDate() != null ? fmt1.parse(dto.getPaymentDate()) : null);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		buyer.setSumInsured(dto.getSumInsured());
		buyer.setStampfees(dto.getStampFees());
		buyer.setOrderId(dto.getOrderId());
		buyer.setAgentName(dto.getAgentName());
		buyer.setBuyerPlatForm(dto.getBuyerPlatForm());
		buyer.setProposalStatus(dto.getProposalStatus());
		buyer.setDepartment(dto.getDepartment());
		buyer.setInterest(dto.getInterest());
		buyer.setInvoiceNo(dto.getInvoiceNo());
		buyer.setLisceneceNo(dto.getLisceneceNo());
		buyer.setPolicyNo(dto.getPolicyNo());
		buyer.setPolicyOwnerName(dto.getPolicyOwnerName());
		buyer.setPremium(dto.getPremium());
		buyer.setSubmittedDate(new Date());
		buyer.setBuyerTempId(dto.getId());
		buyer.setServiceCharges(dto.getServiceCharges());
		return buyer;
	}

	public static List<OnlineBillerBuyerDailyReportFor2c2pDTO> convertOnlineBillerBuyerListToDailyReportList(
			List<OnlineBillerBuyer> findOnlineBillerByDate) {
		List<OnlineBillerBuyerDailyReportFor2c2pDTO> dtoList = null;
		if (findOnlineBillerByDate != null && !findOnlineBillerByDate.isEmpty()) {
			dtoList = new ArrayList<>();
			for (OnlineBillerBuyer buyer : findOnlineBillerByDate) {
				dtoList.add(convertOnlineBillerBuyerListToDailyReport(buyer));
			}
		}
		return dtoList;
	}

	private static OnlineBillerBuyerDailyReportFor2c2pDTO convertOnlineBillerBuyerListToDailyReport(
			OnlineBillerBuyer buyer) {
		OnlineBillerBuyerDailyReportFor2c2pDTO dto = null;
		if (buyer != null) {
			dto = new OnlineBillerBuyerDailyReportFor2c2pDTO();
			dto.setInvoiceNo(buyer.getInvoiceNo());
			dto.setPolicyNo(buyer.getPolicyNo());
			dto.setPolicyOwnerName(buyer.getPolicyOwnerName());
			dto.setStampfees(buyer.getStampfees());
			dto.setDepartment(buyer.getDepartment());
			dto.setProposalStatus(buyer.getProposalStatus());
			dto.setPremium(buyer.getPremium());
			dto.setBuyerPlatForm(buyer.getBuyerPlatForm());
			dto.setSubmittedDate(buyer.getSubmittedDate());
			dto.setOrderId(buyer.getOrderId());
			dto.setPaymentDate(buyer.getPaymentDate());
		}

		return dto;
	}

}
