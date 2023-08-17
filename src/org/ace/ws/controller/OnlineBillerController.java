package org.ace.ws.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.onlineBiller.service.interfaces.IOnlineBillerProposalService;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.system.productTypeRecords.service.interfaces.IProductTypeRecordsService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.factory.OnlineBillerProposalFactory;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.onlineBiller.OnlineBillerBuyerDailyReportFor2c2pDTO;
import org.ace.ws.model.onlineBiller.OnlineBillerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
public class OnlineBillerController extends BaseController{

	@Resource(name = "OnlineBillerProposalService")
	private IOnlineBillerProposalService onlineBillerService;
	
	@Resource(name = "ProductTypeRecordsService")
	private IProductTypeRecordsService productTypeRecordsService;
	
	
	@PostMapping(path = URIConstants.ONLINEBILLERCORETOCLOUD,consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody String getCallApI(@RequestBody Map<String,List<OnlineBillerDTO>> body) throws UnsupportedEncodingException, ParseException {
		List<OnlineBillerDTO> dtoList=  body.get("onlineBillerDTOList");
		for(OnlineBillerDTO mtp001 : dtoList){
			onlineBillerService.addCoreOnlineBillerProposal(OnlineBillerProposalFactory.convertOnlineBillerProposal(mtp001));
		}
		return "Success";
	}
	
	@RequestMapping(value = URIConstants.POST_ONLINE_BILLER_BY_INVOICE_NO, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> findOnlineProductByInvoiceNo(@RequestBody OnlineBillerDTO onlineBillerDTO) {
		AceResponse response = new AceResponse();
		OnlineBillerDTO dto = null;
		if(null != onlineBillerDTO.getInvoiceNo() && !onlineBillerDTO.getInvoiceNo().equals("")) {
			dto = OnlineBillerProposalFactory.convertOnlineBillerToDTO(onlineBillerService.findOnlineBillerByInvoiceNo(onlineBillerDTO.getInvoiceNo()));
		}else if( null != onlineBillerDTO.getPolicyNo() && !onlineBillerDTO.getPolicyNo().equals("")) {
			dto = OnlineBillerProposalFactory.convertOnlineBillerToDTO(onlineBillerService.findOnlineBillerByInvoiceNo(onlineBillerDTO.getPolicyNo()));
		}else {
			response.setStatus(HttpStatus.OK);
			response.setMessage("Please type policyNo or InvoiceNo !!!");
			return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.OK);
		}
		if(dto ==null) {
			response.setStatus(HttpStatus.OK);
			response.setMessage("Not Found!!!");
		}else if(dto.isBought()) {
			response.setStatus(HttpStatus.OK);
			response.setMessage("Already Bought!!!");
		}else {
			response.setStatus(HttpStatus.OK);
			response.setMessage("Success!!!");
			response.setData(dto);
		}
		
		return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.OK);
	}
	
	@RequestMapping(value = URIConstants.POST_ONLINE_BILLER_BUYER, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> buyOnlineProduct(@RequestBody OnlineBillerDTO onlineBillerDTO) {
		AceResponse response = new AceResponse();
		boolean status = false;
		if(null == onlineBillerDTO.getInvoiceNo() || onlineBillerDTO.getInvoiceNo().isEmpty() || onlineBillerDTO.getInvoiceNo().equals("")) {
			status = true;
		}
		if(null == onlineBillerDTO.getPolicyNo() || onlineBillerDTO.getPolicyNo().isEmpty() || onlineBillerDTO.getPolicyNo().equals("")) {
			status = true;
		}
		if(onlineBillerDTO.getPremium() == 0) {
			status = true;
		}
		if(onlineBillerDTO.getSumInsured()==0) {
			status = true;
		}
		if(status == true) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessage("Some values are required.");
		}else {
				if(onlineBillerDTO.getBuyerPlatForm().equals(BuyerPlatForm.INAPP)){
					onlineBillerDTO.setProposalStatus(ProposalStatus.PENDING);
				}else {
					SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
					onlineBillerDTO.setProposalStatus(ProposalStatus.ISSUED);
					onlineBillerDTO.setPaymentDate(fmt1.format(new Date()));
					onlineBillerService.updateOnlineBillingStatus(onlineBillerDTO.getInvoiceNo());
				}
				onlineBillerService.addNewOnlineBillerProduct(OnlineBillerProposalFactory.convertOnlineBillerDTOToEntity(onlineBillerDTO));
				ProductTypeRecords productTypeRecords = new ProductTypeRecords();
				productTypeRecords.setProductType("Online Biller");
				productTypeRecords.setTwoCtwoPorderId(onlineBillerDTO.getOrderId() == null ? ""+System.currentTimeMillis() : onlineBillerDTO.getOrderId());
				productTypeRecordsService.insert(productTypeRecords );
				response.setStatus(HttpStatus.OK);
				response.setMessage("Success!!!");
		
		}
		
		return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.OK);
	}
	
	@RequestMapping(value = URIConstants.POST_ONLINE_BILLER_BY_DATE, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> findOnlineProductByDateandPlatForm(@RequestBody OnlineBillerBuyerDailyReportFor2c2pDTO obDto) {
		AceResponse response = new AceResponse();
		List<OnlineBillerBuyerDailyReportFor2c2pDTO> dto = OnlineBillerProposalFactory.convertOnlineBillerBuyerListToDailyReportList(onlineBillerService.findOnlineBillerByDate(obDto.getFromDate(),obDto.getToDate(),obDto.getBuyerPlatForm()));
		if(dto ==null) {
			response.setStatus(HttpStatus.OK);
			response.setMessage("Not Found!!!");
		}else {
			response.setStatus(HttpStatus.OK);
			response.setMessage("Success!!!");
			response.setData(dto);
		}
		
		return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.OK);
	}
}
