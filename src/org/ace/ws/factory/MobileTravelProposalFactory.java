package org.ace.ws.factory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.common.Utils;
import org.ace.insurance.travel.MobileTravelInsuredPerson;
import org.ace.insurance.travel.MobileTravelProposal;
import org.ace.ws.model.mobiletravelproposal.MIP001;
import org.ace.ws.model.mobiletravelproposal.MTP001;

public class MobileTravelProposalFactory {
	public static MobileTravelProposal convertMobileTravelProposal(MTP001 mtp001) {
		MobileTravelProposal result = new MobileTravelProposal(mtp001);
		return result;
	}

	public static MTP001 convertMobileTravelProposalDTO(MobileTravelProposal travelProposal) throws UnsupportedEncodingException {
		MTP001 dto = new MTP001();
		dto.setId(travelProposal.getId());
		dto.setUserId(travelProposal.getUserId());
		dto.setProductId(travelProposal.getProductId());
		dto.setProposalNo(travelProposal.getProposalNo());
		dto.setPolicyNo(travelProposal.getPolicyNo());
		dto.setProposalStatus(travelProposal.getProposalStatus());
		dto.setTransactionId(travelProposal.getTransactionId());
		dto.setOrderId(travelProposal.getOrderId());
		dto.setAirProduct(travelProposal.isAirProduct());
		dto.setOverSea(travelProposal.isOverSea());
		dto.setTransactionFees(travelProposal.getTransactionFees());
		dto.setPaymentDate(travelProposal.getPaymentDate() == null ? "" : travelProposal.getPaymentDate().toString());
		dto.setSubmittedDate(Utils.getDateFormatString(travelProposal.getSubmittedDate()));
		dto.setPlatFormType(travelProposal.getPlatFormType());
		List<MIP001> insuredPersonList = new ArrayList<MIP001>();
		for (MobileTravelInsuredPerson p : travelProposal.getInsuredPersonList()) {
			MIP001 pDTO = new MIP001();
			pDTO.setId(p.getId());
			pDTO.setFirstName(p.getFirstName());
			pDTO.setLastName(p.getLastName());
			pDTO.setIdNo(p.getIdNo());
			//pDTO.setFirstName(URLEncoder.encode(p.getFirstName(), "UTF-8"));
			//pDTO.setLastName(p.getLastName() == null ? p.getLastName() : URLEncoder.encode(p.getLastName(), "UTF-8"));
			//pDTO.setIdNo(p.getIdNo() == null ? p.getIdNo() : URLEncoder.encode(p.getIdNo(), "UTF-8"));
			pDTO.setDepartureDate(Utils.getDateFormatString(p.getDepartureDate()));
			pDTO.setArrivalDate(Utils.getDateFormatString(p.getArrivalDate()));
			pDTO.setRoute(p.getRoute());
			pDTO.setUnit(p.getUnit());
			pDTO.setPremium(p.getPremium());
			pDTO.setOverSeaAmount(p.getOverSeaAmount());
			pDTO.setFlightCover(p.isFlightCover());
			pDTO.setIdType(p.getIdType());
			pDTO.setFlightCoverAmount(p.getFlightCoverAmount());
			insuredPersonList.add(pDTO);
		}
		dto.setInsuredPersonList(insuredPersonList);
		return dto;
	}

	public static List<MTP001> convertMobileTravelProposalDTOList(List<MobileTravelProposal> travelProposalList) throws UnsupportedEncodingException {
		List<MTP001> result = new ArrayList<MTP001>();
		if (travelProposalList != null)
			for (MobileTravelProposal p : travelProposalList) {
				p.getSubmittedDate().setDate(p.getSubmittedDate().getDate());
				result.add(convertMobileTravelProposalDTO(p));
			}
		return result;
	}
}
