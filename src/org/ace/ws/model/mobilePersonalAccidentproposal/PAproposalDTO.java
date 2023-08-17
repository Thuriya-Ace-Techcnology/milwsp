package org.ace.ws.model.mobilePersonalAccidentproposal;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PAproposalDTO {

	private List<MultipartFile> fileList;
	private String proposalDto;

	public List<MultipartFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<MultipartFile> fileList) {
		this.fileList = fileList;
	}

	public String getProposalDto() {
		return proposalDto;
	}

	public void setProposalDto(String proposalDto) {
		this.proposalDto = proposalDto;
	}

}
