package org.ace.insurance.resourceQuestion;

import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.surveyquestion.ResourceQuestion;

public class ResourceQuestionFactory {
	

	public static List<ResourceQuestionDTO> convertDTOList(List<ResourceQuestion> resList){
		List<ResourceQuestionDTO> resDTOList = new ArrayList<>();
		for(ResourceQuestion res : resList) {
			resDTOList.add(convertDTO(res));
		}
		return resDTOList;
	}

	private static ResourceQuestionDTO convertDTO(ResourceQuestion res) {
		ResourceQuestionDTO dto = null;
		if(res != null) {
			dto = new ResourceQuestionDTO();
			dto.setId(res.getId());
			dto.setName(res.getName());
		}
		return dto;
	}
}
