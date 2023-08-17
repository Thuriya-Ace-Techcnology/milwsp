package org.ace.ws.controller;

/*
 * @author Kyaw Yea Lwin
 * @date 29/06/2020
 */
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.ace.insurance.common.DateUtils;
import org.ace.insurance.common.QRCodeGenerator;
import org.ace.insurance.product.PaymentStatus;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.system.productTypeRecords.service.interfaces.IProductTypeRecordsService;
import org.ace.insurance.system.rta.RTA;
import org.ace.insurance.system.rta.RTAExcelExport;
import org.ace.insurance.system.rta.RTALocation;
import org.ace.insurance.system.rta.RTATownship;
import org.ace.insurance.system.rta.service.interfaces.IRTALocationService;
import org.ace.insurance.system.rta.service.interfaces.IRTAService;
import org.ace.insurance.system.rta.service.interfaces.IRTATownshipService;
import org.ace.insurance.system.thirdparty.ThirdPartyPremiumReceipt;
import org.ace.insurance.system.thirdparty.service.interfaces.IThirdPartyPremiumReceiptSerivce;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.controller.common.JasperTemplate;
import org.ace.ws.factory.JasperFactory;
import org.ace.ws.factory.ThirdPartyPremiumReceiptFactory;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.thirdParty.PremiumBuyerSearchableDTO;
import org.ace.ws.model.thirdParty.ThirdPartyPremiumReceiptDTO;
import org.ace.ws.model.thirdParty.ThirdPartyPremiumRecordsDTO;
import org.apache.log4j.Logger;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@Controller
public class ThirdPartyPremiumReceiptController extends BaseController {

	@Resource(name = "ThirdPartyPremiumReceiptService")
	private IThirdPartyPremiumReceiptSerivce thirdPartyPremiumReceiptSerivce;

	@Resource(name = "ProductTypeRecordsService")
	private IProductTypeRecordsService productTypeRecordsService;

	@Resource(name = "RTAService")
	private IRTAService rtaService;

	@Resource(name = "RTALocationService")
	private IRTALocationService rtaLocationService;

	@Resource(name = "RTATownshipService")
	private IRTATownshipService rtaTownshipService;

	private static final Logger logger = Logger.getLogger(ThirdPartyPremiumReceiptController.class);

	@RequestMapping(value = URIConstants.POST_THIRDPARTY_PREMIUM_RECEIPT_CHECK, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> checkPremiumReceipt(
			@RequestBody ThirdPartyPremiumReceiptDTO thirdPartyPremiumReceiptDTO) {
		ThirdPartyPremiumReceipt receipt = null;
		if (null != thirdPartyPremiumReceiptDTO.getId() || null != thirdPartyPremiumReceiptDTO.getVehicle_no()) {
			receipt = ThirdPartyPremiumReceiptFactory.convertThirdPartyPremiumReceipt(thirdPartyPremiumReceiptDTO);
			if (thirdPartyPremiumReceiptSerivce.checkPremiumReceipt(receipt)) {
				receipt = null;
			}
		}
		return new ResponseEntity<>(responseManager.getResponseString(receipt), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.POST_THIRDPARTY_PREMIUM_RECEIPT, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> insertPremiumTempoReceipt(
			@RequestBody ThirdPartyPremiumReceiptDTO thirdPartyPremiumReceiptDTO) {
		AceResponse response = new AceResponse();
		String result = checkRequireParam(thirdPartyPremiumReceiptDTO);
		if (result != null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessage(result);
			return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.BAD_REQUEST);
		}
		ThirdPartyPremiumReceipt receipt = null;
		if (null == thirdPartyPremiumReceiptDTO.getId()) {
			thirdPartyPremiumReceiptDTO.setPeriod_from(LocalDate.parse(thirdPartyPremiumReceiptDTO.getPeriod_from(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(1).toString());
			receipt = ThirdPartyPremiumReceiptFactory.convertThirdPartyPremiumReceipt(thirdPartyPremiumReceiptDTO);
			receipt.setPaymentStatus(PaymentStatus.PENDING);
			String orderId = receipt.getOrder_id();
			List<ThirdPartyPremiumReceipt> findRecords = thirdPartyPremiumReceiptSerivce
					.findByDateAndVehicleNo(receipt.getPeriod_from(), receipt.getPeriod_to(), receipt.getVehicle_no());
			if (findRecords.size() != 0) {
				Iterator<ThirdPartyPremiumReceipt> itr = findRecords.iterator();
				List<ThirdPartyPremiumReceipt> removeList = new ArrayList<>();
				while (itr.hasNext() && itr != null) {
					ThirdPartyPremiumReceipt itrNext = itr.next();
					if (itrNext.getPaymentStatus().equals(PaymentStatus.PENDING)
							&& !itrNext.getOrder_id().equals(orderId)) {
						thirdPartyPremiumReceiptSerivce.delete(itrNext);
						removeList.add(itrNext);
					} else if (itrNext.getOrder_id().equals(orderId)) {
						receipt.setId(itrNext.getId());
					}
				}
				if (removeList.size() > 0) {
					for (ThirdPartyPremiumReceipt rec : removeList) {
						findRecords.remove(rec);
					}
				}
			}

			if (findRecords.size() == 0) {
				thirdPartyPremiumReceiptDTO = ThirdPartyPremiumReceiptFactory.convertThirdPartyPremiumDTOCTOC(
						thirdPartyPremiumReceiptSerivce.insert(ThirdPartyPremiumReceiptFactory
								.convertThirdPartyPremiumReceipt(thirdPartyPremiumReceiptDTO)));
				ProductTypeRecords productTypeRecords = new ProductTypeRecords();
				productTypeRecords.setProductType("TPL Online");
				productTypeRecords.setTwoCtwoPorderId(receipt.getOrder_id());
				productTypeRecordsService.insert(productTypeRecords);

			}

		}
		String orderId = receipt.getOrder_id();
		Runnable tpRun = () -> thirdPartyPremiumReceiptSerivce.postDataSyncProcess(orderId);
		new Thread(tpRun).start();
		response.setStatus(HttpStatus.OK);
		response.setMessage("Successfully inserted!!!");
		response.setData(receipt);
		return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.OK);
	}

	@RequestMapping(value = URIConstants.GET_THIRDPARTY_PREMIUM_RECEIPT, method = RequestMethod.GET)
	@ResponseBody
	public String findPremiumReceiptById(@RequestParam(name = "id") String id) {
		String response = null;
		ThirdPartyPremiumReceiptDTO premiumDTO = null;
		if (null != id) {
			ThirdPartyPremiumReceipt receipt = thirdPartyPremiumReceiptSerivce.findById(id);
			premiumDTO = calculateReceipt(receipt, premiumDTO);
		}
		response = responseManager.getResponseString(premiumDTO);
		return response;
	}

	@RequestMapping(value = URIConstants.GET_THIRDPARTY_PREMIUM_BUYERS, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> findPremiumBuyerByDates(@RequestParam(name = "fromDate") String fromDate,
			@RequestParam(name = "toDate") String toDate, @RequestParam(name = "branch") String branch) {
		if (!DateUtils.isValidFormat("yyyy-MM-dd", fromDate) || !DateUtils.isValidFormat("yyyy-MM-dd", toDate)) {
			return new ResponseEntity<>("Date format wrong.(eg,'2020-01-30').", HttpStatus.BAD_REQUEST);
		}
		List<ThirdPartyPremiumRecordsDTO> premiumDTO = thirdPartyPremiumReceiptSerivce.findByFromToDate(fromDate,
				toDate, branch, "other");
		return new ResponseEntity<>(responseManager.getResponseString(premiumDTO), HttpStatus.OK);
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = URIConstants.GET_THIRDPARTY_PREMIUM_RECEIPT_BY_VEHICLENO, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> findPremiumReceiptByVehicleNo(@RequestParam(name = "vehicle_no") String vehicle_no) {
		AceResponse response = new AceResponse();
		ThirdPartyPremiumReceiptDTO premiumDTO = null;
		if (null != vehicle_no && !vehicle_no.equals("")) {
			ThirdPartyPremiumReceipt receipt = thirdPartyPremiumReceiptSerivce.findByVehicleNo(vehicle_no);
			RTA rta = rtaService.findByVehicleNo(vehicle_no);
			if (receipt == null && rta == null) {
				response.setStatus(HttpStatus.NOT_FOUND);
				response.setMessage("Sorry, this Vehicle No is no register:" + vehicle_no);
				return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.NOT_FOUND);
			}
			if (receipt == null) {
				LocalDate dateTo = LocalDate.parse(rta.getD_e(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				LocalDate dateStart = LocalDate.parse("2021-01-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				if (rta == null) {
					response.setStatus(HttpStatus.NOT_FOUND);
					response.setMessage("Sorry,Not found for this Vehicle No :" + vehicle_no);
					return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.NOT_FOUND);
				}
				if (dateStart.isAfter(dateTo)) {
					response.setStatus(HttpStatus.NOT_FOUND);
					response.setMessage("Not Allow!Go to Myanma Insurance head office.");
					return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.NOT_FOUND);
				}
				if (LocalDate.now().isAfter(dateTo.plusYears(5))) {
					response.setStatus(HttpStatus.NOT_FOUND);
					response.setMessage("Due date is over 5 years.Go to Myanma Insurance head office.");
					return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.NOT_FOUND);
				}
				ThirdPartyPremiumReceipt receipt1 = ThirdPartyPremiumReceiptFactory
						.convertThirdPartyPremiumReceiptDTOByRTA(rta);
				premiumDTO = calculateReceipt(receipt1, premiumDTO);
			} else {
				premiumDTO = calculateReceipt(receipt, premiumDTO);
			}
			String rta_branch = premiumDTO.getRta_branch();
			if (rta_branch != null) {
				if (receipt != null) {
					if (rta_branch.contains("-")) {
						rta_branch = rta_branch.substring(0, rta_branch.indexOf("-"));
					}
				}
				RTALocation rtaLocation = rtaLocationService.findByCode(rta_branch);

				if(rtaLocation !=null) {
					premiumDTO.setRta_branch(rtaLocation.getDescription());
				}
			}
			String rta_address = premiumDTO.getAddress();
			if (rta_address != null) {
				RTATownship rtaTownship = rtaTownshipService.findByCode(rta_address);
				if (rtaTownship != null) {
					premiumDTO.setAddress(rtaTownship.getDescription());
				}
			} else {
				premiumDTO.setAddress("");
			}
		} else {
			response.setStatus(HttpStatus.NOT_FOUND);
			response.setMessage("Sorry,Not found for this Vehicle No :" + vehicle_no);
			return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.NOT_FOUND);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage("Success");
		response.setData(premiumDTO);
		return new ResponseEntity<>(responseManager.getResponseString(response), HttpStatus.OK);
	}

	@RequestMapping(value = URIConstants.GET_THIRDPARTY_PREMIUM_RECEIPT_FINDALL, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> findAllPremiumReceipt() {
		List<ThirdPartyPremiumReceipt> receipt = new ArrayList<>();
		receipt.addAll(thirdPartyPremiumReceiptSerivce.findAllThirdPartyPremiumReceipt());
		return new ResponseEntity<>(responseManager.getResponseString(receipt), HttpStatus.OK);
	}

	@RequestMapping(value = URIConstants.POST_THIRDPARTY_PREMIUM_CALCULATE, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> calculatePremium(@RequestBody ThirdPartyPremiumReceiptDTO thirdPartyPremiumReceiptDTO)
			throws ParseException {
		ThirdPartyPremiumReceipt receipt = ThirdPartyPremiumReceiptFactory
				.convertThirdPartyPremiumReceipt(thirdPartyPremiumReceiptDTO);
		ThirdPartyPremiumReceiptDTO premiumDTO = thirdPartyPremiumReceiptSerivce.calculatePremiumReceipt(receipt);
		return new ResponseEntity<>(responseManager.getResponseString(premiumDTO), HttpStatus.OK);
	}

	@RequestMapping(value = URIConstants.DELETE_THIRDPARTY_PREMIUM_RECEIPT, method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> deletePremiumReceipt(
			@RequestBody ThirdPartyPremiumReceiptDTO thirdPartyPremiumReceiptDTO) {
		ThirdPartyPremiumReceipt receipt = ThirdPartyPremiumReceiptFactory
				.convertThirdPartyPremiumReceipt(thirdPartyPremiumReceiptDTO);
		thirdPartyPremiumReceiptSerivce.delete(receipt);
		return new ResponseEntity<>(responseManager.getResponseString("Successfully Deleted"), HttpStatus.OK);
	}

	@RequestMapping(value = URIConstants.POST_THIRDPARTY_PREMIUM_EXCEL_PATH, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> premiumBuyerExcelDownload(
			@RequestBody PremiumBuyerSearchableDTO premiumBuyerSearchableDTO) {
		List<ThirdPartyPremiumRecordsDTO> premiumDTO = null;
		ByteArrayOutputStream stream = null;
		try {
			premiumDTO = thirdPartyPremiumReceiptSerivce.findByFromToDate(premiumBuyerSearchableDTO.getFromDate(),
					premiumBuyerSearchableDTO.getToDate(), premiumBuyerSearchableDTO.getBranch(),
					premiumBuyerSearchableDTO.getConvert());
			stream = RTAExcelExport.createWorkBook(premiumDTO);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (stream == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(new MediaType("application", "force-download"));
		resHeader.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=TPLPremiumBuyerList.xls");
		return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()), resHeader, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_PRINT_THIRDPARTY_PREMIUM_RECEIPT, method = RequestMethod.GET)
	@ResponseBody
	public byte[] printReceipt(HttpServletResponse response, @RequestParam(name = "vehicle_no") String vehicle_no)
			throws IOException, JRException {
		logger.info("Start Print Receipt function");
		byte[] bb = null;
		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fmt2 = new SimpleDateFormat("MM-yyyy");
		DecimalFormat decimalFormat = new DecimalFormat("#,###.0");
		Date date;
		String made = "-";
		ThirdPartyPremiumReceipt receipt = thirdPartyPremiumReceiptSerivce.findByVehicleNo(vehicle_no);
		String vehicleNo = receipt.getVehicle_no();
		RTA rta = rtaService.findByVehicleNo(vehicle_no);
		receipt.setType(rta.getType());
		String fullAddress="";
		if(receipt.getOrder_id() == null && receipt.isConvert()) {//For Core Data
			fullAddress=receipt.getAddress();
		}else {
			receipt.setAddress(rta.getAddress());
			String townshipCode = rta.getTsp();
			// error_3_15
			RTATownship rtaTownship = rtaTownshipService.findByCode(townshipCode);
			
			fullAddress = rtaTownship != null ? receipt.getAddress() + "," + rtaTownship.getDescription() + "."
					:  " - ";
		}
		
		if (receipt != null && receipt.getPaymentStatus().equals(PaymentStatus.SUCCESS)) {
			logger.info("Receipt object is not null");
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("buyDate", receipt.getBuyDate());
			paramMap.put("orderId", receipt.getOrder_id());
			paramMap.put("receiptNo", receipt.getBook_no() == null ? "-" : receipt.getBook_no());
			paramMap.put("ownerName", receipt.getOwner_name());
			paramMap.put("NRCNo",
					(receipt.getNrc_no() == null || receipt.getNrc_no().equals("")) ? "-" : receipt.getNrc_no());
			paramMap.put("vehicleNo", vehicleNo);
			paramMap.put("capacity", receipt.getCapacity());
			paramMap.put("rtaBranch", receipt.getRta_branch());
			paramMap.put("made", made);
			paramMap.put("type", receipt.getType());
			paramMap.put("address", fullAddress);
			try {
				date = fmt1.parse(receipt.getPeriod_from());
				paramMap.put("periodFrom", fmt2.format(date));
				date = fmt1.parse(receipt.getPeriod_to());
				paramMap.put("periodTo", fmt2.format(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			paramMap.put("premiumAmount",
					decimalFormat.format(Double.parseDouble(receipt.getPremium_amount())).concat(" MMK"));
			BufferedImage qrcode = QRCodeGenerator.generateQRCodeImage(receipt.getBook_no());
			paramMap.put("qrcode", qrcode);
			logger.info("receipt paramMap is fine");
			paramMap.put("logo_imgPath", "document-template/images/MI-logo.png");
			JasperPrint print = JasperFactory.generateJasperPrint(paramMap,
					JasperTemplate.THIRDPARTYP_PREMIUM_BUY_PRINT, new JREmptyDataSource());
			response.setContentType(MediaType.APPLICATION_PDF_VALUE);
			response.setHeader("Content-Disposition", "inline; filename=thirdPartyPremiumReceiptPrint.pdf");
			final OutputStream outputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(print, outputStream);
			List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
			jasperPrintList.add(print);
			bb = generateReport(jasperPrintList);
		}
		logger.info("printRecipt Function End");
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

	@CrossOrigin
	@RequestMapping(value = URIConstants.GET_RECORDS_HISTORY, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> premiumRecordsHistory(@RequestParam(name = "vehicle_no") String vehicle_no)
			throws IOException, JRException {
		List<ThirdPartyPremiumReceipt> receipt = thirdPartyPremiumReceiptSerivce.findReceiptListByVehicleNo(vehicle_no);
		AceResponse response = new AceResponse();
		response.setStatus(HttpStatus.OK);
		response.setMessage("Success");
		response.setData(receipt);
		return new ResponseEntity<>(responseManager.getResponseString(response), response.getStatus());
	}

	private ThirdPartyPremiumReceiptDTO calculateReceipt(ThirdPartyPremiumReceipt receipt,
			ThirdPartyPremiumReceiptDTO premiumDTO) {
		if (receipt != null) {
			premiumDTO = new ThirdPartyPremiumReceiptDTO(receipt);
			ThirdPartyPremiumReceiptDTO result = thirdPartyPremiumReceiptSerivce.calculatePremiumReceipt(receipt);
			premiumDTO.setPeriod_to(receipt.getPeriod_to());
			premiumDTO.setTotal_month(result.getTotal_month());
			premiumDTO.setPremiumTotalAmount(result.getPremiumTotalAmount());
			premiumDTO.setNextPremiumBuyDate(result.getNextPremiumBuyDate());
		}
		return premiumDTO;
	}

	private String checkRequireParam(ThirdPartyPremiumReceiptDTO thirdPartyPremiumReceiptDTO) {
		StringBuffer result = new StringBuffer();
		if (thirdPartyPremiumReceiptDTO.getPeriod_from() == null || thirdPartyPremiumReceiptDTO.getPeriod_to() == null
				|| !DateUtils.isValidFormat("yyyy-MM-dd", thirdPartyPremiumReceiptDTO.getPeriod_from())
				|| !DateUtils.isValidFormat("yyyy-MM-dd", thirdPartyPremiumReceiptDTO.getPeriod_to())) {
			result.append("Date format wrong.(eg,\'2020-01-30\').");
		}
		if (thirdPartyPremiumReceiptDTO.getOwner_name() == null
				|| thirdPartyPremiumReceiptDTO.getOwner_name().equals("")) {
			result.append("Owner Name  can\'t be empty.");
		}
		if (thirdPartyPremiumReceiptDTO.getVehicle_no() == null
				|| thirdPartyPremiumReceiptDTO.getVehicle_no().equals("")
				|| thirdPartyPremiumReceiptDTO.getVehicle_type() == null
				|| thirdPartyPremiumReceiptDTO.getVehicle_type().equals("")
				|| thirdPartyPremiumReceiptDTO.getCapacity() == null
				|| thirdPartyPremiumReceiptDTO.getCapacity().equals("")) {
			result.append("Vehicle No or Vehicle Type or Capacity can\'t be empty.");
		}
		if (thirdPartyPremiumReceiptDTO.getPremium_amount() == null
				|| thirdPartyPremiumReceiptDTO.getPremium_amount().equals("")) {
			result.append("Premium amount can't be empty.");
		}
		if (thirdPartyPremiumReceiptDTO.getOrderId() == null || thirdPartyPremiumReceiptDTO.getOrderId() == "") {
			result.append("OrderId can\'t be empty");
		}
		if (thirdPartyPremiumReceiptDTO.getRta_branch() == null
				|| thirdPartyPremiumReceiptDTO.getRta_branch().equals("")) {
			result.append("RTA branch can\'t be null ");
		}
		return result.length() == 0 ? null : String.valueOf(result);
	}
}
