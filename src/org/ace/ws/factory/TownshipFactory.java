package org.ace.ws.factory;

import java.util.ArrayList;
import java.util.List;
import org.ace.insurance.system.common.township.Township;
import org.ace.ws.model.location.TownshipDTO;

public class TownshipFactory {
	
	public static Township convertTownship(TownshipDTO townshipDTO) {
		Township result = new Township(townshipDTO);		
		return result;
	}
	public static TownshipDTO convertTownshipDTO(Township township) {
		TownshipDTO townshipDTO = new TownshipDTO(township);		
		return townshipDTO;
	}
	
	public static List<TownshipDTO> convertTownshipDTOList(List<Township> townshipList) {
		List<TownshipDTO> result = new ArrayList<TownshipDTO>();
		if (townshipList != null)
			for (Township t : townshipList) {
				result.add(convertTownshipDTO(t));
			}
		return result;
		
	}
}
