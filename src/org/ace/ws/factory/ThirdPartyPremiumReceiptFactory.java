package org.ace.ws.factory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ace.insurance.common.DateUtils;
import org.ace.insurance.common.UserRecorder;
import org.ace.insurance.product.PaymentStatus;
import org.ace.insurance.system.rta.RTA;
import org.ace.insurance.system.thirdparty.ThirdPartyPremiumReceipt;
import org.ace.insurance.system.twoCtwoP.TwoCTwoPRecords;
import org.ace.ws.model.thirdParty.ThirdPartyPremiumReceiptDTO;
import org.ace.ws.model.thirdParty.ThirdPartyPremiumRecordsDTO;

public class ThirdPartyPremiumReceiptFactory {

	public static ThirdPartyPremiumReceipt convertThirdPartyPremiumReceipt(ThirdPartyPremiumReceiptDTO dto) {

		ThirdPartyPremiumReceipt tpr = new ThirdPartyPremiumReceipt();
		if (dto.getId() != null) {
			tpr.setId(dto.getId());
		}
		tpr.setOwner_name(dto.getOwner_name());
		tpr.setNrc_no(dto.getNrc_no());
		tpr.setAddress(dto.getAddress());
		tpr.setVehicle_no(dto.getVehicle_no());
		tpr.setBook_no(dto.getBook_no());
		tpr.setVehicle_name(dto.getVehicle_name());
		tpr.setVehicle_type(dto.getVehicle_type());
		tpr.setCapacity(dto.getCapacity());
		tpr.setPeriod_from(dto.getPeriod_from());
		tpr.setPeriod_to(dto.getPeriod_to());
		tpr.setPremium_amount(dto.getPremium_amount());
		tpr.setReceipt_date(dto.getReceipt_date());
		tpr.setReceipt_no(dto.getReceipt_no());
		tpr.setVersion(dto.getVersion());
		tpr.setRta_branch(dto.getRta_branch());
		tpr.setBuyDate(dto.getBuy_date());
		tpr.setOrder_id(dto.getOrderId());
		tpr.setPaymentStatus(PaymentStatus.PENDING);
		UserRecorder recorder = new UserRecorder();
		tpr.setRecorder(recorder);
		tpr.setConvert(false);
		return tpr;
	}
	
	public static List<ThirdPartyPremiumReceiptDTO> convertThridPartyPremiumList(List<ThirdPartyPremiumReceipt> receiptList){
		List<ThirdPartyPremiumReceiptDTO> recList = new ArrayList<ThirdPartyPremiumReceiptDTO>();
		for(ThirdPartyPremiumReceipt receipt : receiptList) {
			recList.add(convertThirdPartyPremiumDTOCTOC(receipt));
		}
		return recList;
	}
	public static ThirdPartyPremiumReceiptDTO convertThirdPartyPremiumDTOCTOC(ThirdPartyPremiumReceipt receipt) {
		ThirdPartyPremiumReceiptDTO dto = new ThirdPartyPremiumReceiptDTO();
		if (receipt.getId() != null) {
			dto.setId(receipt.getId());
		}
		dto.setOwner_name(receipt.getOwner_name());
		dto.setNrc_no(receipt.getNrc_no());
		dto.setAddress(receipt.getAddress());
		dto.setVehicle_no(receipt.getVehicle_no());
		dto.setBook_no(receipt.getBook_no());
		dto.setVehicle_name(receipt.getVehicle_name());
		dto.setVehicle_type(receipt.getVehicle_type());
		dto.setCapacity(receipt.getCapacity());
		dto.setPeriod_from(receipt.getPeriod_from());
		dto.setPeriod_to(receipt.getPeriod_to());
		dto.setPremium_amount(receipt.getPremium_amount());
		dto.setReceipt_date(receipt.getReceipt_date());
		dto.setReceipt_no(receipt.getReceipt_no());
		dto.setVersion(receipt.getVersion());
		dto.setRta_branch(receipt.getRta_branch());
		dto.setBuy_date(receipt.getBuyDate());
		dto.setOrderId(receipt.getOrder_id());		
		return dto;
	}
	
	public static ThirdPartyPremiumRecordsDTO convertThirdPartyPremiumDTO(ThirdPartyPremiumReceipt receipt,TwoCTwoPRecords records) {

		ThirdPartyPremiumRecordsDTO dto = new ThirdPartyPremiumRecordsDTO();
		dto.setOwner_name(receipt.getOwner_name());
		dto.setNrc_no(receipt.getNrc_no());
		dto.setAddress(receipt.getAddress());
		dto.setVehicle_no(receipt.getVehicle_no());
		dto.setPeriod_from(receipt.getPeriod_from());
		dto.setPeriod_to(receipt.getPeriod_to());
		dto.setReceipt_date(receipt.getReceipt_date());
		dto.setReceipt_no(receipt.getReceipt_no());
		dto.setRta_branch(receipt.getRta_branch());
		dto.setBuy_date(receipt.getBuyDate());
		dto.setOrderId(receipt.getOrder_id());
		dto.setPaymentStatus(receipt.getPaymentStatus().getLabel());
		double premium =(double) Double.valueOf(receipt.getPremium_amount());
		double twoCtwoPamount = 0;
		if(records.getProcess_by().equals("MP")) {
			twoCtwoPamount = 400;
		}else {
			twoCtwoPamount = 500;
		}
		double diffAmount= premium- twoCtwoPamount;
		dto.setTwoCtwoPCharges(String.valueOf(twoCtwoPamount));
		dto.setDiffAmount(String.valueOf(diffAmount));
		dto.setPremium_amount(receipt.getPremium_amount());
		return dto;
	}
	
	
	public static ThirdPartyPremiumRecordsDTO convertThirdPartyPremiumReportDTO(ThirdPartyPremiumReceipt receipt,TwoCTwoPRecords records) throws ParseException  {
		ThirdPartyPremiumRecordsDTO dto = new ThirdPartyPremiumRecordsDTO();
		LocalDate dateTo = LocalDate.parse(receipt.getPeriod_to(),DateTimeFormatter.ofPattern("yyyy-dd-MM"));
		//Date periodFrom=formatter.parse(receipt.getPeriod_from());
		//Date periodTo=formatter.parse(receipt.getPeriod_to());
		//Date buyDate=formatter.parse(receipt.getBuyDate());
		
		dto.setOwner_name(receipt.getOwner_name());
		dto.setNrc_no(receipt.getNrc_no());
		dto.setAddress(receipt.getAddress());
		dto.setVehicle_no(receipt.getVehicle_no());
		//dto.setPeriod_from(formatter.format(periodFrom));
		dto.setPeriod_to(dateTo.toString());
		//dto.setBuy_date(formatter.format(buyDate));
		
		dto.setReceipt_date(receipt.getReceipt_date());
		dto.setReceipt_no(receipt.getReceipt_no());
		dto.setRta_branch(receipt.getRta_branch());
		
		dto.setOrderId(receipt.getOrder_id());
		dto.setPaymentStatus(receipt.getPaymentStatus().getLabel());
		double premium =(double) Double.valueOf(receipt.getPremium_amount());
		double twoCtwoPamount = 0;
		if(records.getProcess_by().equals("MP")) {
			twoCtwoPamount = 400;
		}else {
			twoCtwoPamount = 500;
		}
		double diffAmount= premium- twoCtwoPamount;
		dto.setTwoCtwoPCharges(String.valueOf(twoCtwoPamount));
		dto.setDiffAmount(String.valueOf(diffAmount));
		dto.setPremium_amount(receipt.getPremium_amount());
		return dto;
	}
	
	
	
	public static ThirdPartyPremiumReceipt convertThirdPartyPremiumReceiptDTOByRTA(RTA rta) {
		ThirdPartyPremiumReceipt receipt = new ThirdPartyPremiumReceipt();
		if(rta != null) {
			
			receipt.setOwner_name(rta.getName());
			receipt.setNrc_no(rta.getNrc_no());
			receipt.setAddress(rta.getTsp());
			receipt.setVehicle_no(rta.getReg_no());
			receipt.setBook_no("");
			receipt.setVehicle_name(rta.getMake_model());
			receipt.setVehicle_type(rta.getType_8());
			receipt.setCapacity(rta.getPayload());
			receipt.setPeriod_from(rta.getIrg());
			receipt.setPeriod_to(rta.getD_e());
			receipt.setPremium_amount("");
			receipt.setReceipt_date("");
			receipt.setReceipt_no("");
			receipt.setVersion(rta.getVersion());
			receipt.setRta_branch(rta.getLocation());
		}
		return receipt;
	}
}
