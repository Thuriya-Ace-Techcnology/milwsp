package org.ace.ws.factory;

import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.system.rta.RTA;
import org.ace.insurance.system.rta.TempRTA;
import org.ace.ws.model.thirdParty.RTADTO;

public class RTAConverter {
	public static List<RTA> rtaDTOToModelConverter(List<RTADTO> rtaDTOList){
		List<RTA> rtaList = new ArrayList<>();
		rtaDTOList.stream().filter(rtaDto-> rtaDto != null).forEach(rtaDto->{
			RTA rta = new RTA();
			rta.setReg_no(rtaDto.getRegNo());
			rta.setMake_model(rtaDto.getMakeModel());
			rta.setType(rtaDto.getType());
			rta.setType_8(rtaDto.getType8());
			rta.setOwner(rtaDto.getOwner());
			rta.setLocation(rtaDto.getLocation());
			rta.setName(rtaDto.getName());
			rta.setNrc_no(rtaDto.getNrcNo());
			rta.setHouse_no(rtaDto.getHouseNo());
			rta.setRd_st(rtaDto.getRdSt());
			rta.setQtr(rtaDto.getQtr());
			rta.setTsp(rtaDto.getTsp());
			rta.setPayload(rtaDto.getPayload());
			rta.setIrg(rtaDto.getiRg());
			rta.setD_e(rtaDto.getDe());
			rta.setStatus(rta.getStatus());
			rtaList.add(rta);
		});
		return rtaList;
	}
	public static List<TempRTA> rtaDTOToTempModelConverter(List<RTADTO> rtaDTOList){
		List<TempRTA> rtaList = new ArrayList<>();
		rtaDTOList.stream().filter(rtaDto-> rtaDto != null).forEach(rtaDto->{
			TempRTA rta = new TempRTA();
			rta.setReg_no(rtaDto.getRegNo());
			rta.setMake_model(rtaDto.getMakeModel());
			rta.setType(rtaDto.getType());
			rta.setType_8(rtaDto.getType8());
			rta.setOwner(rtaDto.getOwner());
			rta.setLocation(rtaDto.getLocation());
			rta.setName(rtaDto.getName());
			rta.setNrc_no(rtaDto.getNrcNo());
			rta.setHouse_no(rtaDto.getHouseNo());
			rta.setRd_st(rtaDto.getRdSt());
			rta.setQtr(rtaDto.getQtr());
			rta.setTsp(rtaDto.getTsp());
			rta.setPayload(rtaDto.getPayload());
			rta.setIrg(rtaDto.getiRg());
			rta.setD_e(rtaDto.getDe());
			rta.setStatus(rta.getStatus());
			rtaList.add(rta);
		});
		return rtaList;
	}
	
	/*
	 * public static List<RTADTO> rtaModelToDTOListConverter(List<RTA> rtaList){
	 * List<RTADTO> dtoList = new ArrayList<RTADTO>(); for(RTA rta : rtaList) {
	 * dtoList.add(rtaModelToDTOConverter(rta)); } return dtoList; }
	 * 
	 * public static RTADTO rtaModelToDTOConverter(RTA rta) { RTADTO dto = new
	 * RTADTO(); dto.setRegNo(rta.getReg_no());
	 * dto.setMakeModel(rta.getMake_model()); dto.setType(rta.getType());
	 * dto.setType8(rta.getType_8()); dto.setOwner(rta.getOwner());
	 * dto.setLocation(rta.getLocation()); dto.setName(rta.getName());
	 * dto.setNrcNo(rta.getNrc_no()); dto.setHouseNo(rta.getHouse_no());
	 * dto.setRdSt(rta.getRd_st()); dto.setQtr(rta.getQtr());
	 * dto.setTsp(rta.getTsp()); dto.setPayload(rta.getPayload());
	 * dto.setiRg(rta.getIrg()); dto.setDe(rta.getD_e());
	 * dto.setStatus(rta.getStatus()); return dto; }
	 */
}
