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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import javax.xml.rpc.ServiceException;

import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.common.MobileProductType;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.QRCodeGenerator;
import org.ace.insurance.common.TourismType;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravel;
import org.ace.insurance.specailForeignTravel.service.interfaces.ISpecialForeignTravelService;
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
public class SpecialForeignTravelController extends BaseController {
	private static final Logger logger = Logger.getLogger(SpecialForeignTravelController.class);

	@Resource(name = "SpecialForeignTravelService")
	private ISpecialForeignTravelService specialForeignTravelService;

	@Resource(name = "ProductTypeRecordsService")
	private IProductTypeRecordsService productTypeRecordsService;

	AceResponse aceResponse;

	@CrossOrigin
	@RequestMapping(value = URIConstants.SPECIAL_FOREIGN_TRAVEL, method = RequestMethod.POST)
	public @ResponseBody String buySpecialForeignTravel(@RequestHeader String key,
			@RequestParam("proposalDto") String dto) throws IOException, ServiceException {
		AceResponse aceResponse = new AceResponse();
		String response = null;
		Gson gson = new Gson();
		SpecialForeignTravelDTO spForeignTravelDTO = gson.fromJson(dto, SpecialForeignTravelDTO.class);
		
		if(spForeignTravelDTO.getCurrencyId() == null) {
			aceResponse.setMessage("currencyId is required");
			aceResponse.setStatus(HttpStatus.BAD_REQUEST);
			aceResponse.setCode(400);
			return responseManager.getResponseString(aceResponse);
		}
		
		spForeignTravelDTO.setPassportNo(spForeignTravelDTO.getPassportNo().trim());
		logger.info("Start Insert Mobile Special Foreign Travel Proposal.");
		try {
			if (key.toString().equals(Constants.getApikey())) {
				if (spForeignTravelDTO != null) {

					if (spForeignTravelDTO.getOrderId() == null || spForeignTravelDTO.getOrderId().equals("")) {
						aceResponse.setMessage(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
						return responseManager.getResponseString(aceResponse);
					}
					List<SpecialForeignTravel> findRecords = specialForeignTravelService
							.findByOrderId(spForeignTravelDTO.getOrderId());
					if (findRecords == null || findRecords.isEmpty()) {
						spForeignTravelDTO.setTourismType(TourismType.INBOUND);
						spForeignTravelDTO.setJourneyTo("MYANMAR");
						if (spForeignTravelDTO.getBuyerPlatForm().equals(BuyerPlatForm.WEBSITE)) {
							concatforWeb(spForeignTravelDTO);
						}
						
						spForeignTravelDTO = SpecailForeignTravelFactory.convertSpecailForeignTravelDTO(
								specialForeignTravelService.addNewOrUpdateSpecailForeignTravel(
										SpecailForeignTravelFactory.convertSpecailForeignTravel(spForeignTravelDTO)));
						ProductTypeRecords productTypeRecords = new ProductTypeRecords();

						productTypeRecords.setProductType(MobileProductType.SPECAILFOREIGNTRAVELLER.toString());
						productTypeRecords.setTwoCtwoPorderId(spForeignTravelDTO.getOrderId());
						productTypeRecordsService.insert(productTypeRecords);
						aceResponse.setMessage("Success");
						aceResponse.setStatus(HttpStatus.OK);
						aceResponse.setData(spForeignTravelDTO);
						response = responseManager.getResponseString(aceResponse);
					} else if (findRecords.get(0).getProposalStatus() != ProposalStatus.ISSUED
							&& findRecords.get(0).getOrderId() != spForeignTravelDTO.getOrderId()) {
						spForeignTravelDTO.setTourismType(TourismType.INBOUND);
						spForeignTravelDTO.setJourneyTo("MYANMAR");
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
					return response;
				} else {
					aceResponse.setMessage(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
					return responseManager.getResponseString(aceResponse);
				}
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
	}

	private long concatLongDate(long value) {
		return Long.valueOf(String.valueOf(value) + "000");
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_SPECIAL_FOREIGN_TRAVEL, method = RequestMethod.GET)
	public @ResponseBody String getProposalbyReferenceNo(@RequestHeader String key,
			@QueryParam("countryCode") String countryCode, @QueryParam("passportNo") String passportNo)
			throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Select Special Foreign Travel Proposal By referanceNo.");
		String response = null;
		try {
			List<SpecialForeignTravelDTO> spForeignTravel = specialForeignTravelService
					.findProposalByPolicyNoOrPassportNo(countryCode, passportNo.trim(),TourismType.INBOUND );
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

	@RequestMapping(value = URIConstants.IBT_CORE_TO_CLOUD, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<String> doDataSyncProcess(
			@RequestBody Map<String, SpecialForeignTravelDTO> data) {
		logger.info("Start Call to Special Foreign Travel.");
		Gson gson = new GsonBuilder().create();
		SpecialForeignTravelDTO specialDto = data.get("InboundTravelDTO");
		List<SpecialForeignTravel> proposal = specialForeignTravelService.findByOrderId(specialDto.getOrderId());
		if (proposal != null && !proposal.isEmpty()) {
			for (SpecialForeignTravel sp : proposal) {
				SpecailForeignTravelFactory.updateSpecialForeignInfo(sp, specialDto);
				specialForeignTravelService.addNewOrUpdateSpecailForeignTravel(sp);
			}
		}
		aceResponse = new AceResponse();
		aceResponse.setMessage("Success");
		aceResponse.setStatus(HttpStatus.OK);
		aceResponse.setCode(200);
		String result = gson.toJson(aceResponse);
		return new ResponseEntity<String>(result, aceResponse.getStatus());
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_SPECIAL_FOREIGN_TRAVELLER_RECEIPT, method = RequestMethod.GET)
	@ResponseBody
	public byte[] printReceipt(HttpServletResponse response, @RequestParam(name = "id") String id)
			throws IOException, JRException {
		logger.info("Start Print Receipt function");
		byte[] bb = null;

		SpecialForeignTravelDTO receipt = specialForeignTravelService.findById(id);
		if (receipt != null && receipt.getProposalStatus().equals(ProposalStatus.ISSUED)) {
			logger.info("Receipt object is not null");
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("table1", "document-template/images/img1.png");
			paramMap.put("milogo", "document-template/images/MI-logo.png");
			Map<String, Object> paramMap2 = new HashMap<>();
			paramMap2.put("table1", "document-template/images/img2.png");
			paramMap2.put("milogo", "document-template/images/MI-logo.png");
			Map<String, Object> paramMap3 = new HashMap<>();
			paramMap3.put("milogo", "document-template/images/MI-logo.png");
			Map<String, Object> paramMap4 = new HashMap<>();
			paramMap4.put("milogo", "document-template/images/MI-logo.png");
			Map<String, Object> paramMap5 = new HashMap<>();
			paramMap5.put("milogo", "document-template/images/MI-logo.png");
			Map<String, Object> paramMap6 = new HashMap<>();
			paramMap6.put("benefit", "document-template/images/img4.png");
			paramMap6.put("milogo", "document-template/images/MI-logo.png");
			Map<String, Object> cerParam = new HashMap<>();
			BufferedImage qrcode = QRCodeGenerator
					.generateQRCodeImage("Name:" + receipt.getFullName() + ",policyNo:" + receipt.getPolicyNo()
							+ ",PassportNo:" + receipt.getPassportNo() + ",oid:" + receipt.getOrderId());
			cerParam.put("qrcode", qrcode);
			ArrayList<SpecialForeignTravelDTO> dataList = new ArrayList<>();
			dataList.add(receipt);
			if (receipt.iscStatus() == true) {
				cerParam.put("holderName", receipt.getcName());
				cerParam.put("ppCountry", "Child without passport");
				cerParam.put("childCertificate",
						"Buy for the child travel together with this passport holder \n(Child is not holding a valid passport)");
			} else {
				cerParam.put("holderName", receipt.getFullName());
				cerParam.put("ppCountry", receipt.getPassportNo() + "\n" + receipt.getPassportIssuedCountry());
				cerParam.put("childCertificate", "Buy for yourself (This passport holder)");
			}
			cerParam.put("benefit", "document-template/images/img4.png");
			cerParam.put("periodMonth", receipt.getPeriodMonth());
			cerParam.put("policyNo", receipt.getPolicyNo());
			cerParam.put("journeyFrom", receipt.getJourneyFrom());
			if (StringUtils.isNotEmpty(receipt.getAgentName()) && StringUtils.isNotEmpty(receipt.getAgentLicenseNo())) {
				cerParam.put("agentName", receipt.getAgentName());
			} else {
				cerParam.put("agentName", "[N/A]");
			}
			LocalDate date = Instant.ofEpochMilli(receipt.getPaymentDate()).atZone(ZoneId.systemDefault())
					.toLocalDate();
			String pDate = date.getDayOfMonth() + " " + date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
					+ " " + date.getYear();
			cerParam.put("paymentDate", pDate);
			cerParam.put("milogo", "document-template/images/MI-logo.png");
			cerParam.put("orderId", receipt.getOrderId());
			cerParam.put("insuredDataSource", new JRBeanCollectionDataSource(dataList));
			JasperPrint terms001 = JasperFactory.generateJasperPrint(paramMap,
					JasperTemplate.SPECIAL_FOREIGN_TRAVEL_BUY_PRINT001, new JREmptyDataSource());
			JasperPrint terms002 = JasperFactory.generateJasperPrint(paramMap2,
					JasperTemplate.SPECIAL_FOREIGN_TRAVEL_BUY_PRINT002, new JREmptyDataSource());
			JasperPrint terms003 = JasperFactory.generateJasperPrint(paramMap3,
					JasperTemplate.SPECIAL_FOREIGN_TRAVEL_BUY_PRINT003, new JREmptyDataSource());
			JasperPrint terms004 = JasperFactory.generateJasperPrint(paramMap4,
					JasperTemplate.SPECIAL_FOREIGN_TRAVEL_BUY_PRINT004, new JREmptyDataSource());
			JasperPrint terms005 = JasperFactory.generateJasperPrint(paramMap5,
					JasperTemplate.SPECIAL_FOREIGN_TRAVEL_BUY_PRINT005, new JREmptyDataSource());
			JasperPrint terms006 = JasperFactory.generateJasperPrint(paramMap6,
					JasperTemplate.SPECIAL_FOREIGN_TRAVEL_BUY_PRINT006, new JREmptyDataSource());
			JasperPrint cerPrint = JasperFactory.generateJasperPrint(cerParam,
					JasperTemplate.SPECIAL_FOREIGN_TRAVEL_BUY_CERTIFICATE, new JREmptyDataSource());
			List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
			jasperPrintList.add(cerPrint);
			jasperPrintList.add(terms001);
			jasperPrintList.add(terms002);
			jasperPrintList.add(terms003);
			jasperPrintList.add(terms004);
			jasperPrintList.add(terms005);
			jasperPrintList.add(terms006);
			bb = generateReport(jasperPrintList);
			// response.setContentType("application/x-download");
			response.setContentType(MediaType.APPLICATION_PDF_VALUE);
			response.setHeader("Content-Disposition", "attachment; filename=myanma_insurnace.pdf");
		} else {
			response.setStatus(400);
		}

		logger.info("printRecipt Function End");
		/*
		 * final OutputStream outputStream = response.getOutputStream(); InputStream
		 * inputStream = Thread.currentThread().getContextClassLoader()
		 * .getResourceAsStream(JasperTemplate.SPECIAL_FOREIGN_TRAVEL_BUY_CERTIFICATE);
		 * JasperDesign design = JRXmlLoader.load(inputStream); JasperReport jreport =
		 * JasperCompileManager.compileReport(design); logger.info("Start PDF compile");
		 * JasperPrint jprint = JasperFillManager.fillReport(jreport, cerParam, new
		 * JREmptyDataSource()); JasperExportManager.exportReportToPdfStream(jprint,
		 * outputStream);
		 */
		return bb;
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
