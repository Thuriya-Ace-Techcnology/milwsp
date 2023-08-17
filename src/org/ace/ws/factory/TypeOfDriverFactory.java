package org.ace.ws.factory;

import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.thirdPartyDriverLicense.TypeOfDriver;
import org.ace.ws.model.thirdParty.TypeOfDriverDTO;

public class TypeOfDriverFactory {

	public static List<TypeOfDriverDTO> convertToDTO(List<TypeOfDriver> typeOfDriverList) {
		TypeOfDriverDTO dto = new TypeOfDriverDTO();
		List<TypeOfDriverDTO> dtoList = new ArrayList<TypeOfDriverDTO>();
		for (TypeOfDriver typeOfDriver : typeOfDriverList) {
			dto.setId(typeOfDriver.getId());
			dto.setName(typeOfDriver.getName());
			dto.setDescription(typeOfDriver.getDescription());
			dto.setPremiumRate(typeOfDriver.getPremiumRate());
			dto.setYear(typeOfDriver.getYear());

			dtoList.add(dto);
		}
		return dtoList;
	}
}