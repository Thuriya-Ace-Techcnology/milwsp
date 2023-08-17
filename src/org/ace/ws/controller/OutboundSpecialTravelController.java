package org.ace.ws.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import javax.xml.rpc.ServiceException;

import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.common.MobileProductType;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.QRCodeGenerator;
import org.ace.insurance.common.TourismType;
import org.ace.insurance.common.Utils;
import org.ace.insurance.product.service.interfaces.IPremiumCalculatorService;
import org.ace.insurance.specailForeignTravel.OutboundAssociationAgent;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravel;
import org.ace.insurance.specailForeignTravel.service.interfaces.IOutboundAssociationAgentService;
import org.ace.insurance.specailForeignTravel.service.interfaces.IOutboundTPAPremiumRateService;
import org.ace.insurance.specailForeignTravel.service.interfaces.IOutboundTravelService;
import org.ace.insurance.specailForeignTravel.service.interfaces.ISpecialForeignTravelService;
import org.ace.insurance.system.common.currency.Currency;
import org.ace.insurance.system.common.currency.service.interfaces.ICurrencyService;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.system.productTypeRecords.service.interfaces.IProductTypeRecordsService;
import org.ace.java.component.StatusType;
import org.ace.java.component.SystemException;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.controller.common.JasperTemplate;
import org.ace.ws.factory.JasperFactory;
import org.ace.ws.factory.SpecailForeignTravelFactory;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.outboundTravel.OutboundTravelDTO;
import org.ace.ws.model.premiumCal.OutboundResultPremium;
import org.ace.ws.model.premiumCal.PRO001;
import org.ace.ws.model.premiumCal.ResultPremium;
import org.ace.ws.model.specialForeignTravel.SpecialForeignTravelDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@Controller
public class OutboundSpecialTravelController extends BaseController {
	private static final Logger logger = Logger.getLogger(OutboundSpecialTravelController.class);

	@Resource(name = "SpecialForeignTravelService")
	private ISpecialForeignTravelService specialForeignTravelService;

	@Resource(name = "ProductTypeRecordsService")
	private IProductTypeRecordsService productTypeRecordsService;

	@Resource(name = "OutboundTPAPremiumRateService")
	private IOutboundTPAPremiumRateService outboundTPAPremiumRateService;

	@Resource(name = "OutboundTravelService")
	private IOutboundTravelService outboundTravelService;

	@Resource(name = "OutboundAssociationAgentService")
	private IOutboundAssociationAgentService outboundAssociationAgentService;

	@Resource(name = "CurrencyService")
	private ICurrencyService currencyService;

	AceResponse aceResponse;

	@Resource(name = "PremiumCalculatorService")
	private IPremiumCalculatorService premiumCalculatorService;

	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_OUTBOUND_PREMIUM, method = RequestMethod.POST)
	@ResponseBody
	public String getPremium(@RequestBody PRO001 pro001) {
		List<ResultPremium> resultList = premiumCalculatorService.calculatePremium(pro001);

		List<OutboundResultPremium> outboundPremiumResult = outboundTPAPremiumRateService
				.findOutboundPremium(resultList);

		if (outboundPremiumResult.size() > 0) {
			if (outboundPremiumResult.get(0).getPremium() == 0.0) {
				return responseManager.getResponseString(new ArrayList<OutboundResultPremium>());
			}
		}
		return responseManager.getResponseString(outboundPremiumResult);
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.OUTBOUND_SPECIAL_TRAVEL, method = RequestMethod.POST)
	public @ResponseBody String buySpecialForeignTravel(@RequestHeader String key,
			@RequestParam("proposalDto") String dto) throws IOException, ServiceException {
		AceResponse aceResponse = new AceResponse();
		String response = null;
		Gson gson = new Gson();
		SpecialForeignTravelDTO spForeignTravelDTO = gson.fromJson(dto, SpecialForeignTravelDTO.class);
		spForeignTravelDTO.setPassportNo(spForeignTravelDTO.getPassportNo().trim());
		logger.info("Start Insert Mobile Outbound Special Travel Proposal.");
		try {
			if (key.toString().equals(Constants.getApikey())) {
				if (spForeignTravelDTO != null) {

					if (spForeignTravelDTO.getOrderId() == null || spForeignTravelDTO.getOrderId().equals("")) {
						aceResponse.setMessage(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
						return responseManager.getResponseString(aceResponse);
					}

					if (spForeignTravelDTO.getPassportIssuedCountry().equals("MYANMAR")) {
						if (spForeignTravelDTO.getRegistrationNo() == null
								|| spForeignTravelDTO.getRegistrationNo().equals("")) {
							aceResponse.setMessage(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
							return responseManager.getResponseString(aceResponse);
						}

					}
					if (spForeignTravelDTO.getSaleChannelType() == null) {
						aceResponse.setMessage(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
						return responseManager.getResponseString(aceResponse);
					}

					List<SpecialForeignTravel> findRecords = specialForeignTravelService
							.findByOrderId(spForeignTravelDTO.getOrderId());
					if (findRecords == null || findRecords.isEmpty()) {
						spForeignTravelDTO.setTourismType(TourismType.OUTBOUND);
						if (spForeignTravelDTO.getBuyerPlatForm().equals(BuyerPlatForm.WEBSITE)) {
							concatforWeb(spForeignTravelDTO);
						}

						spForeignTravelDTO = SpecailForeignTravelFactory.convertSpecailForeignTravelDTO(
								specialForeignTravelService.addNewOrUpdateSpecailForeignTravel(
										SpecailForeignTravelFactory.convertSpecailForeignTravel(spForeignTravelDTO)));
						ProductTypeRecords productTypeRecords = new ProductTypeRecords();

						productTypeRecords.setProductType(MobileProductType.OUTBOUNDSPECAILFOREIGNTRAVELLER.toString());
						productTypeRecords.setTwoCtwoPorderId(spForeignTravelDTO.getOrderId());
						productTypeRecordsService.insert(productTypeRecords);
						aceResponse.setMessage("Success");
						aceResponse.setStatus(HttpStatus.OK);
						aceResponse.setData(spForeignTravelDTO);
						response = responseManager.getResponseString(aceResponse);
					} else if (findRecords.get(0).getProposalStatus() != ProposalStatus.ISSUED
							&& findRecords.get(0).getOrderId() != spForeignTravelDTO.getOrderId()) {
						if (spForeignTravelDTO.getBuyerPlatForm().equals(BuyerPlatForm.WEBSITE)) {
							concatforWeb(spForeignTravelDTO);
						}
						spForeignTravelDTO = SpecailForeignTravelFactory.convertSpecailForeignTravelDTO(
								specialForeignTravelService.addNewOrUpdateSpecailForeignTravel(
										SpecailForeignTravelFactory.convertSpecailForeignTravel(spForeignTravelDTO)));
						aceResponse.setMessage("Success");
						aceResponse.setStatus(HttpStatus.OK);
						aceResponse.setData(spForeignTravelDTO);
						response = responseManager.getResponseString(aceResponse);
					} else {
						aceResponse.setMessage("Success");
						aceResponse.setStatus(HttpStatus.OK);
						aceResponse.setData(findRecords);
						response = responseManager.getResponseString(aceResponse);
					}
				}
				return response;
			} else {
				aceResponse.setMessage(ResponseStatus.INVALIDUSER.getLabel());
				return responseManager.getResponseString(aceResponse);
			}
		} catch (SystemException e) {
			e.printStackTrace();
			aceResponse.setMessage(ResponseStatus.FAIL.getLabel());
			response = responseManager.getResponseString(aceResponse);
			throw new ServiceException(StatusType.SQL_Exception);
		}
	}

	private void concatforWeb(SpecialForeignTravelDTO spForeignTravelDTO) {
		spForeignTravelDTO.setBenDateOfBirth(concatLongDate(spForeignTravelDTO.getBenDateOfBirth()));
		spForeignTravelDTO.setActivedPolicyStartDate(concatLongDate(spForeignTravelDTO.getActivedPolicyStartDate()));
		spForeignTravelDTO.setActivedPolicyEndDate(concatLongDate(spForeignTravelDTO.getActivedPolicyEndDate()));
		spForeignTravelDTO.setPassportExpireDate(concatLongDate(spForeignTravelDTO.getPassportExpireDate()));
		spForeignTravelDTO.setDateOfBirth(concatLongDate(spForeignTravelDTO.getDateOfBirth()));
		spForeignTravelDTO.setcBirthDate(concatLongDate(spForeignTravelDTO.getcBirthDate()));
		spForeignTravelDTO.setDepartureDate(concatLongDate(spForeignTravelDTO.getDepartureDate()));
	}

	private long concatLongDate(long value) {
		return Long.valueOf(String.valueOf(value) + "000");
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_OUTBOUND_SPECIAL_TRAVEL, method = RequestMethod.GET)
	public @ResponseBody String getProposalbyReferenceNo(@RequestHeader String key,
			@QueryParam("countryCode") String countryCode, @QueryParam("passportNo") String passportNo)
			throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Select Outbound Special  Travel Proposal By referanceNo.");
		String response = null;
		try {
			List<SpecialForeignTravelDTO> spForeignTravel = specialForeignTravelService
					.findProposalByPolicyNoOrPassportNo(countryCode, passportNo.trim(), TourismType.OUTBOUND);
			if (spForeignTravel != null) {
				response = responseManager.getResponseString(spForeignTravel);
			} else {
				response = "{\"responseStatus\":\"EMPTY\"}";
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.INTERNAL_SERVER_ERROR.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Select Specail Foreign Travel List by RefNo.");
		return response;
	}

//	@RequestMapping(value = URIConstants.IBT_CORE_TO_CLOUD, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
//	public @ResponseBody ResponseEntity<String> doDataSyncProcess(@RequestBody Map<String,SpecialForeignTravelDTO> data)
//			throws ServiceException, UnsupportedEncodingException {
//		logger.info("Start Call to Special Foreign Travel.");
//		Gson gson = new GsonBuilder().create();
//		SpecialForeignTravelDTO specialDto = data.get("InboundTravelDTO"); 
//		List<SpecialForeignTravel> proposal=specialForeignTravelService.findByOrderId(specialDto.getOrderId());
//		if(proposal != null && !proposal.isEmpty()) {
//			for(SpecialForeignTravel sp : proposal) {
//				SpecailForeignTravelFactory.updateSpecialForeignInfo(sp,specialDto);
//				specialForeignTravelService.addNewOrUpdateSpecailForeignTravel(sp);	
//			}
//		}
//		aceResponse = new AceResponse();  
//		aceResponse.setMessage("Success");  
//		aceResponse.setStatus(HttpStatus.OK);
//		aceResponse.setCode(200);
//		String result = gson.toJson(aceResponse);
//	    return new ResponseEntity<String>(result, aceResponse.getStatus());
//	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.OUTBOUND_TRAVEL_CORE_TO_CLOUD, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<String> doDataSyncProcess(@RequestBody Map<String, OutboundTravelDTO> data)
			throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Call to Outbound Travel Core To Cloud.");
		Gson gson = new GsonBuilder().create();
		OutboundTravelDTO outboundDTO = data.get("OutboundTravelDTO");

		if (outboundDTO.getTourismType() == TourismType.OUTBOUND) {
			outboundTravelService.findByOrderId(outboundDTO);
		}

		aceResponse = new AceResponse();
		aceResponse.setMessage("Success");
		aceResponse.setStatus(HttpStatus.OK);
		aceResponse.setCode(200);
		String result = gson.toJson(aceResponse);
		return new ResponseEntity<String>(result, aceResponse.getStatus());
	}
	
	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_ASSOCIATION_TRAVELLING_AGENT, method = RequestMethod.GET)
	public @ResponseBody String getAssociationTravellingAgent(@RequestHeader String key,
			@QueryParam("licenceNo") String licenceNo, @QueryParam("password") String password)
			throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Fetching Association Travelling Agent By licenceNo and password");
		aceResponse = new AceResponse();
		try {
			if (key.toString().equals(Constants.getApikey())) {
				Optional<OutboundAssociationAgent> outboundAssociationAgent=outboundAssociationAgentService.findByLicenceNoAndPassword(licenceNo, password);
				
				if (outboundAssociationAgent.isPresent()) {
					aceResponse.setCode(200);
					aceResponse.setStatus(HttpStatus.OK);
					aceResponse.setData(outboundAssociationAgent);
					return responseManager.getResponseString(aceResponse);
				} else { 
					aceResponse.setCode(400);
					aceResponse.setStatus(HttpStatus.NOT_FOUND);
					return responseManager.getResponseString(aceResponse);
				}
			}else {
				aceResponse.setMessage(ResponseStatus.INVALIDUSER.getLabel());
				return responseManager.getResponseString(aceResponse);
			}
			
		} catch (SystemException e) {
			e.printStackTrace();
			aceResponse.setCode(500);
			aceResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			aceResponse.setMessage("Something Wrong");
			return responseManager.getResponseString(aceResponse);
		}
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_OUTBOUND_SPECIAL_TRAVELLER_RECEIPT, method = RequestMethod.GET)
	public ResponseEntity<byte[]> printReceipt(HttpServletResponse response, @RequestParam(name = "id") String id)
			throws IOException, JRException {
		logger.info("Start Print Receipt function");
		byte[] bb = null;
		SpecialForeignTravelDTO receipt = specialForeignTravelService.findById(id);
		Currency currency = currencyService.findCurrencyById(receipt.getCurrencyId());
		if (receipt != null && receipt.getProposalStatus().equals(ProposalStatus.ISSUED)) {
			logger.info("Receipt object is not null");
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("milogo", "document-template/images/MI-logo.png");
			Map<String, Object> paramMap2 = new HashMap<>();
			paramMap2.put("milogo", "document-template/images/MI-logo.png");
			Map<String, Object> paramMap3 = new HashMap<>();
			paramMap3.put("benefitSchedule1", "document-template/images/benefitSchedule1.PNG");
			paramMap3.put("milogo", "document-template/images/MI-logo.png");
			Map<String, Object> paramMap4 = new HashMap<>();
			paramMap4.put("benefitSchedule2", "document-template/images/benefitSchedule2.PNG");
			paramMap4.put("milogo", "document-template/images/MI-logo.png");
			Map<String, Object> paramMap5 = new HashMap<>();
			paramMap5.put("milogo", "document-template/images/MI-logo.png");
			Map<String, Object> paramMap6 = new HashMap<>();
			paramMap6.put("milogo", "document-template/images/MI-logo.png");
			Map<String, Object> paramMap7 = new HashMap<>();
			paramMap7.put("milogo", "document-template/images/MI-logo.png");
			Map<String, Object> paramMap8 = new HashMap<>();
			paramMap8.put("milogo", "document-template/images/MI-logo.png");
			Map<String, Object> paramMap9 = new HashMap<>();
			paramMap9.put("tableOfBenefit100", "document-template/images/tableOfBenefit100.png");
			paramMap9.put("tableOfBenefit_MMK_1", "document-template/images/outboundMMK100.PNG");
			paramMap9.put("milogo", "document-template/images/MI-logo.png");
			Map<String, Object> paramMap10 = new HashMap<>();
			paramMap10.put("tableOfBenefit300", "document-template/images/tableOfBenefit300.png");
			paramMap10.put("tableOfBenefit500", "document-template/images/tableOfBenefit500.png");
			paramMap10.put("tableOfBenefit_MMK_2", "document-template/images/outboundMMK300.PNG");
			paramMap10.put("tableOfBenefit_MMK_3", "document-template/images/outboundMMK500.PNG");
			paramMap10.put("milogo", "document-template/images/MI-logo.png");
			Map<String, Object> paramMap11 = new HashMap<>();
			paramMap11.put("milogo", "document-template/images/MI-logo.png");

			Map<String, Object> cerParam = new HashMap<>();
			BufferedImage qrcode = QRCodeGenerator
					.generateQRCodeImage("Name:" + receipt.getFullName() + ",policyNo:" + receipt.getPolicyNo()
							+ ",PassportNo:" + receipt.getPassportNo() + ",oid:" + receipt.getOrderId());
			cerParam.put("qrcode", qrcode);
			ArrayList<SpecialForeignTravelDTO> dataList = new ArrayList<>();
			dataList.add(receipt);

			cerParam.put("holderName", receipt.getFullName());
			cerParam.put("footerSign", "document-template/images/footer.PNG");
			cerParam.put("ppCountry", receipt.getPassportNo() + "\n" + receipt.getPassportIssuedCountry());
			cerParam.put("childCertificate", "Buy for yourself (This passport holder)");
			if (currency.getCurrencyCode().equals("USD")) {
				if (receipt.getSumInsured() == 10000) {
					cerParam.put("benefit", "document-template/images/tableOfBenefit100.png");
					cerParam.put("deductibleAmount", "100");
				} else if (receipt.getSumInsured() == 30000) {
					cerParam.put("benefit", "document-template/images/tableOfBenefit300.png");
					cerParam.put("deductibleAmount", "300");
				} else {
					cerParam.put("benefit", "document-template/images/tableOfBenefit500.png");
					cerParam.put("deductibleAmount", "500");
				}
			} else {
				if (receipt.getSumInsured() == 30000000) {
					cerParam.put("benefit", "document-template/images/outboundMMK100.PNG");
					cerParam.put("deductibleAmount", "300,000");
				} else if (receipt.getSumInsured() == 90000000) {
					cerParam.put("benefit", "document-template/images/outboundMMK300.PNG");
					cerParam.put("deductibleAmount", "900,000");
				} else {
					cerParam.put("benefit", "document-template/images/outboundMMK500.PNG");
					cerParam.put("deductibleAmount", "1,500,000");
				}
			}

			cerParam.put("periodMonth", receipt.getPeriodMonth());
			cerParam.put("policyNo", receipt.getPolicyNo());
			cerParam.put("journeyTo", receipt.getJourneyTo() == null ? "[N/A]" : receipt.getJourneyTo());
			if (StringUtils.isNotEmpty(receipt.getAgentName()) && StringUtils.isNotEmpty(receipt.getAgentLicenseNo())) {
				cerParam.put("agentName", receipt.getAgentName());
			} else {
				cerParam.put("agentName", "[N/A]");
			}

			cerParam.put("startDate", receipt.getActivedPolicyStartDate() == 0 ? "[N/A]"
					: Utils.getDateFormatString(new Date(receipt.getActivedPolicyStartDate())));
			cerParam.put("endDate", receipt.getActivedPolicyEndDate() == 0 ? "[N/A]"
					: Utils.getDateFormatString(new Date(receipt.getActivedPolicyEndDate())));

			LocalDate date = Instant.ofEpochMilli(receipt.getPaymentDate()).atZone(ZoneId.systemDefault())
					.toLocalDate();
			String pDate = date.getDayOfMonth() + " " + date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
					+ " " + date.getYear();
			cerParam.put("paymentDate", pDate);
			cerParam.put("premium", Utils.getCurrencyFormatString(receipt.getTotalPremium()));
			cerParam.put("milogo", "document-template/images/MI-logo.png");
			cerParam.put("ulinklogo", "document-template/images/Ulink_Logo.png");
			cerParam.put("insuredDataSource", new JRBeanCollectionDataSource(dataList));

			JasperPrint terms001 = JasperFactory.generateJasperPrint(paramMap,
					JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_PRINT001, new JREmptyDataSource());
			JasperPrint terms002 = JasperFactory.generateJasperPrint(paramMap2,
					JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_PRINT002, new JREmptyDataSource());
			JasperPrint terms003 = JasperFactory.generateJasperPrint(paramMap3,
					JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_PRINT003, new JREmptyDataSource());
			JasperPrint terms004 = JasperFactory.generateJasperPrint(paramMap4,
					JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_PRINT004, new JREmptyDataSource());
			JasperPrint terms007 = JasperFactory.generateJasperPrint(paramMap7,
					JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_PRINT007, new JREmptyDataSource());
			JasperPrint terms008 = JasperFactory.generateJasperPrint(paramMap8,
					JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_PRINT008, new JREmptyDataSource());
			JasperPrint terms0011 = JasperFactory.generateJasperPrint(paramMap11,
					JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_PRINT0011, new JREmptyDataSource());
			JasperPrint terms005 = null;
			JasperPrint terms006 = null;
			JasperPrint terms009 = null;
			JasperPrint terms0010 = null;
			JasperPrint cerPrint = null;
			if (currency.getCurrencyCode().equals("MMK")) {
				terms005 = JasperFactory.generateJasperPrint(paramMap5,
						JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_PRINT005_MMK, new JREmptyDataSource());
				terms006 = JasperFactory.generateJasperPrint(paramMap6,
						JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_PRINT006_MMK, new JREmptyDataSource());
				terms009 = JasperFactory.generateJasperPrint(paramMap9,
						JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_PRINT009_MMK, new JREmptyDataSource());
				terms0010 = JasperFactory.generateJasperPrint(paramMap10,
						JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_PRINT0010_MMK, new JREmptyDataSource());
				cerPrint = JasperFactory.generateJasperPrint(cerParam,
						JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_CERTIFICATE_MMK, new JREmptyDataSource());
			} else {
				terms005 = JasperFactory.generateJasperPrint(paramMap5,
						JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_PRINT005, new JREmptyDataSource());
				terms006 = JasperFactory.generateJasperPrint(paramMap6,
						JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_PRINT006, new JREmptyDataSource());
				terms009 = JasperFactory.generateJasperPrint(paramMap9,
						JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_PRINT009, new JREmptyDataSource());
				terms0010 = JasperFactory.generateJasperPrint(paramMap10,
						JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_PRINT0010, new JREmptyDataSource());
				cerPrint = JasperFactory.generateJasperPrint(cerParam,
						JasperTemplate.OUTBOUND_SPECIAL_TRAVEL_BUY_CERTIFICATE, new JREmptyDataSource());
			}
			List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
			jasperPrintList.add(cerPrint);
			jasperPrintList.add(terms001);
			jasperPrintList.add(terms002);
			jasperPrintList.add(terms003);
			jasperPrintList.add(terms004);
			jasperPrintList.add(terms005);
			jasperPrintList.add(terms006);
			jasperPrintList.add(terms007);
			jasperPrintList.add(terms008);
			jasperPrintList.add(terms009);
			jasperPrintList.add(terms0010);
			jasperPrintList.add(terms0011);

			bb = generateReport(jasperPrintList);
			response.setContentType(MediaType.APPLICATION_PDF_VALUE);
			response.setHeader("Content-Disposition", "inline; filename=myanma_insurnace.pdf");
			logger.info("printRecipt Function End");

			ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bb, HttpStatus.OK);

			return responseEntity;
		}

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bb, HttpStatus.NOT_ACCEPTABLE);

		return responseEntity;
	}

	public byte[] generateReport(List<JasperPrint> jasperPrintList) throws JRException {
		// throw the JasperPrint Objects in a list
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setCreatingBatchModeBookmarks(true);
		exporter.setConfiguration(configuration);
		exporter.exportReport();
		return baos.toByteArray();
	}
}
