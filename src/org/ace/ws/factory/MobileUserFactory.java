package org.ace.ws.factory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.system.mobileUser.MobileUser;
import org.ace.insurance.system.mobileUser.securityAnswer.SecurityAnswer;
import org.ace.ws.model.mobileUser.MU001;
import org.ace.ws.model.mobileUser.SQA001;

public class MobileUserFactory {
	public static MobileUser convertMobileUser(MU001 mobileUserDTO) {
		MobileUser muser = new MobileUser(mobileUserDTO);
		return muser;
	}

	public static MU001 convertMobileUserDTO(MobileUser mobileUser) throws UnsupportedEncodingException {
		MU001 muserDTO = new MU001();
		muserDTO.setId(mobileUser.getId());
		//muserDTO.setFirstName(mobileUser.getFirstName() == null ? mobileUser.getFirstName() : URLEncoder.encode(mobileUser.getFirstName(), "UTF-8"));
		//muserDTO.setLastName(mobileUser.getLastName() == null ? mobileUser.getLastName() : URLEncoder.encode(mobileUser.getLastName(), "UTF-8"));
		muserDTO.setFirstName(mobileUser.getFirstName());
		muserDTO.setLastName(mobileUser.getLastName());
		muserDTO.setMobileNumber(mobileUser.getMobileNumber());
		muserDTO.setEmail(mobileUser.getEmail());
		muserDTO.setDateOfBirth(mobileUser.getDateOfBirth());
		muserDTO.setGender(mobileUser.getGender());
		muserDTO.setContractType(mobileUser.getContractType());
		//muserDTO.setPassword(mobileUser.getPassword());
		muserDTO.setActivate(mobileUser.isActivate());
		muserDTO.setVersion(mobileUser.getVersion());
		List<SQA001> sqa001List = new ArrayList<SQA001>();
		for (SecurityAnswer a : mobileUser.getSecurityAnswerList()) {
			sqa001List.add(new SQA001(a));
		}
		muserDTO.setSqa001List(sqa001List);
		return muserDTO;
	}

	public static MobileUser convertMobileUser(MU001 mobileUserDTO, MobileUser mobileUser) {
		mobileUser.setId(mobileUserDTO.getId());
		mobileUser.setFirstName(mobileUserDTO.getFirstName());
		mobileUser.setLastName(mobileUserDTO.getLastName());
		mobileUser.setEmail(mobileUserDTO.getEmail());
		mobileUser.setPassword(mobileUserDTO.getPassword());
		mobileUser.setActivatedCode(mobileUserDTO.getActivatedCode());
		mobileUser.setActivate(mobileUserDTO.isActivate());
		mobileUser.setActivatedDate(mobileUserDTO.getActivatedDate());
		mobileUser.setVersion(mobileUserDTO.getVersion());
		return mobileUser;
	}

	public static List<MU001> convertMobileUserDTOList(List<MobileUser> mobileUserlist) throws UnsupportedEncodingException {
		List<MU001> moibleUserDTOlist = new ArrayList<MU001>();
		for (MobileUser mobileUser : mobileUserlist) {
			MU001 mobileUserDTO = convertMobileUserDTO(mobileUser);
			moibleUserDTOlist.add(mobileUserDTO);
		}
		return moibleUserDTOlist;
	}
}
