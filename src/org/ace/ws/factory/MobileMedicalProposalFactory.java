package org.ace.ws.factory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.medical.MobileMedicalProposal;
import org.ace.ws.model.mobileMedicalproposal.MedicalProposalDTO;

public class MobileMedicalProposalFactory {
	public static MobileMedicalProposal convertMobileMedicalProposal(MedicalProposalDTO dto) {
		MobileMedicalProposal result = new MobileMedicalProposal(dto);
		return result;
	}

	public static MedicalProposalDTO convertMobileMedicalProposalDTO(MobileMedicalProposal medicialProposal) throws UnsupportedEncodingException {

		MedicalProposalDTO dto = new MedicalProposalDTO(medicialProposal);
		return dto;
	}

	public static List<MedicalProposalDTO> convertMobileMedicalProposalDTOList(List<MobileMedicalProposal> medicalProposalList) throws UnsupportedEncodingException {
		List<MedicalProposalDTO> result = new ArrayList<MedicalProposalDTO>();
		if (medicalProposalList != null)
			for (MobileMedicalProposal p : medicalProposalList) {
				result.add(convertMobileMedicalProposalDTO(p));
			}
		return result;
	}
}
