package org.ace.insurance.system.thirdparty.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ace.insurance.product.service.interfaces.IPremiumCalculatorService;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.system.productTypeRecords.dto.ProductTypeRecordsDTO;
import org.ace.insurance.system.productTypeRecords.service.interfaces.IProductTypeRecordsService;
import org.ace.insurance.system.rta.service.interfaces.IRTAService;
import org.ace.insurance.system.thirdparty.ThirdPartyPremiumReceipt;
import org.ace.insurance.system.thirdparty.persistence.interfaces.IThirdPartyPremiumReceiptDAO;
import org.ace.insurance.system.thirdparty.service.interfaces.IThirdPartyPremiumReceiptSerivce;
import org.ace.insurance.system.twoCtwoP.TwoCTwoPRecords;
import org.ace.insurance.system.twoCtwoP.persistence.interfaes.ITwoCTwoPRecordsDAO;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.factory.ThirdPartyPremiumReceiptFactory;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirm;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirmDTO;
import org.ace.ws.model.premiumCal.PRO001;
import org.ace.ws.model.premiumCal.ResultPremium;
import org.ace.ws.model.thirdParty.ThirdPartyPremiumReceiptDTO;
import org.ace.ws.model.thirdParty.ThirdPartyPremiumRecordsDTO;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

@Service("ThirdPartyPremiumReceiptService")
public class ThirdPartyPremiumReceiptService extends BaseService implements IThirdPartyPremiumReceiptSerivce {

	@Resource(name = "ThirdPartyPremiumReceiptDAO")
	private IThirdPartyPremiumReceiptDAO thirdPartyPremiumReceiptDAO;

	@Resource(name = "PremiumCalculatorService")
	private IPremiumCalculatorService premiumCalculatorService;

	@Resource(name = "ProductTypeRecordsService")
	private IProductTypeRecordsService productTypeRecordsService;

	@Resource(name = "TwoCTwoPRecordsDAO")
	private ITwoCTwoPRecordsDAO twoCTwoPRecordDAO;

	@Resource(name = "RTAService")
	private IRTAService rtaService;

	private static final Logger logger = Logger.getLogger(ThirdPartyPremiumReceiptService.class);

	@Override
	public ThirdPartyPremiumReceipt insert(ThirdPartyPremiumReceipt thirdPartyPremiumReceipt) throws DAOException {
		try {
			thirdPartyPremiumReceipt.setPrefix(getPrefix(ThirdPartyPremiumReceipt.class));
			thirdPartyPremiumReceipt.setBuyDate(LocalDate.now().toString());
			thirdPartyPremiumReceipt.setBook_no("" + Calendar.getInstance().getTimeInMillis());
			return thirdPartyPremiumReceiptDAO.insert(thirdPartyPremiumReceipt);
		} catch (DAOException e) {
			throw new DAOException(e.getErrorCode(), e.getMessage(), new Throwable());
		}
	}

	@Override
	public ThirdPartyPremiumReceipt update(ThirdPartyPremiumReceipt thirdPartyPremiumReceipt) throws DAOException {
		try {
			return thirdPartyPremiumReceiptDAO.update(thirdPartyPremiumReceipt);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update ThirdPartyPremiumReceipt", e);
		}
	}

	@Override
	public void delete(ThirdPartyPremiumReceipt thirdPartyPremiumReceipt) throws DAOException {
		try {
			thirdPartyPremiumReceiptDAO.delete(thirdPartyPremiumReceipt);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to delete ThirdPartyPremiumReceipt", e);
		}
	}

	@Override
	public ThirdPartyPremiumReceipt findById(String id) throws DAOException {
		ThirdPartyPremiumReceipt result = null;
		try {
			result = thirdPartyPremiumReceiptDAO.findById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a ThirdPartyPremiumReceipt (ID : " + id + ")",
					e);
		}
		return result;
	}

	@Override
	public ThirdPartyPremiumReceipt findRecordsByOrderId(String orderId) throws DAOException {
		ThirdPartyPremiumReceipt result = null;
		try {
			result = thirdPartyPremiumReceiptDAO.findRecordsByOrderId(orderId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(),
					"Faield to find a ThirdPartyPremiumReceipt (OrederID : " + orderId + ")", e);
		}
		return result;
	}

	@Override
	public ThirdPartyPremiumReceipt findByVehicleNo(String vehicle_no) throws DAOException {
		ThirdPartyPremiumReceipt result = null;
		try {
			result = thirdPartyPremiumReceiptDAO.findByVehicleNo(vehicle_no);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(),
					"Faield to find a ThirdPartyPremiumReceipt (Vehicle No : " + vehicle_no + ")", e);
		}
		return result;
	}

	@Override
	public List<ThirdPartyPremiumReceipt> findAllThirdPartyPremiumReceipt() throws DAOException {
		List<ThirdPartyPremiumReceipt> result = null;
		try {
			result = thirdPartyPremiumReceiptDAO.findAllThirdPartyPremiumReceipt();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a ThirdPartyPremiumReceipt ", e);
		}
		return result;
	}

	@Override
	public boolean checkPremiumReceipt(ThirdPartyPremiumReceipt receipt) {
		ThirdPartyPremiumReceipt result = null;
		if (receipt.getId() != null) {
			result = findById(receipt.getId());
		} else if (receipt.getVehicle_no() != null) {
			result = findByVehicleNo(receipt.getVehicle_no());
		}
		LocalDate toDate = LocalDate.parse(result.getPeriod_to(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		long days = ChronoUnit.DAYS.between(LocalDate.now(), toDate);
		if (days > 90) {
			return true;
		}
		return false;
	}

	/*
	 * 2year 11 month is 35 month So month variable is 35 ((month /12) *
	 * result.getPremium()) + ((month % 12) >= 10 ? result.getPremium() :
	 * ((result.getPremium() /10) * (month %12) )); (month /12) *
	 * result.getPremium()) ==> 2 year (month % 12) >= 10 check the month if month
	 * is over 9 result.getPremium() OR premium amount is base on 10 month so 1 year
	 * premium amount is 10000. so 1 month is 1000 if 11 month is 10000. so
	 * (result.getPremium() /10) * (month %12)
	 */

	@Override
	public ThirdPartyPremiumReceiptDTO calculatePremiumReceipt(ThirdPartyPremiumReceipt receipt) {
		PRO001 pro001 = new PRO001();
		Map<String, String> keyFactorMap = new HashedMap<>();
		switch (receipt.getVehicle_type()) {
		case "MOTOR_COMMERCIAL":
			pro001.setProductId("ISPRD003001000000012218062020");
			keyFactorMap.put("ISSYS013001000000008117062020", receipt.getCapacity());
			keyFactorMap.put("ISSYS013001000000009118062020", "VEHICLE_COMMERCIAL");
			pro001.setKeyFactorMap(keyFactorMap);
			break;
		case "MOTOR_PRIVATE":
			pro001.setProductId("ISPRD003001000000012218062020");
			keyFactorMap.put("ISSYS013001000000008117062020", receipt.getCapacity());
			keyFactorMap.put("ISSYS013001000000009118062020", "VEHICLE_PRIVATE");
			pro001.setKeyFactorMap(keyFactorMap);
			break;
		case "TRUCK_COMMERCIAL":
			pro001.setProductId("ISPRD003001000000012318062020");
			keyFactorMap.put("ISSYS0130001000000000229032013", receipt.getCapacity());
			keyFactorMap.put("ISSYS013001000000009118062020", "VEHICLE_COMMERCIAL");
			pro001.setKeyFactorMap(keyFactorMap);
			break;
		case "TURCK_PRIVATE":
			pro001.setProductId("ISPRD003001000000012318062020");
			keyFactorMap.put("ISSYS0130001000000000229032013", receipt.getCapacity());
			keyFactorMap.put("ISSYS013001000000009118062020", "VEHICLE_PRIVATE");
			pro001.setKeyFactorMap(keyFactorMap);
			break;
		default:
			pro001.setProductId("ISPRD003001000000012218062020");
			keyFactorMap.put("ISSYS013001000000008117062020", receipt.getCapacity());
			keyFactorMap.put("ISSYS013001000000009118062020", "VEHICLE_COMMERCIAL");
			pro001.setKeyFactorMap(keyFactorMap);
			break;
		}

		List<ResultPremium> premiumResult = premiumCalculatorService.calculatePremium(pro001);
		String periodTo = null;
		if (isValidDateFormat(receipt.getPeriod_to())) {
			periodTo = receipt.getPeriod_to();
		} else {
			periodTo = receipt.getPeriod_to().substring(0, receipt.getPeriod_to().length() - 8);
			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
			Date date;
			try {
				date = sdf.parse(periodTo);
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				periodTo = sdf.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		LocalDate dateTo = LocalDate.parse(periodTo, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		receipt.setPeriod_to(dateTo.toString());
		LocalDate date1 = dateTo;
		double premiumAmount = 0;
		String truckKg = "0";
		int month = 0;
		YearMonth nowYearMonth = YearMonth.now();
		YearMonth expireDateYM = YearMonth.of(dateTo.getYear(), dateTo.getMonthValue());
		if (receipt.getVehicle_type().equals("TURCK_PRIVATE") || receipt.getVehicle_type().equals("TRUCK_COMMERCIAL")) {
			truckKg = rtaService.findKgByVehicleNo(receipt.getVehicle_no()).substring(0, 3);
		}
		if (dateTo.isBefore(LocalDate.now()) || expireDateYM.minusMonths(3).equals(nowYearMonth)
				|| expireDateYM.minusMonths(3).isBefore(nowYearMonth)) {
			if (receipt.getVehicle_type().equals("MOTOR_PRIVATE")
					|| (truckKg.equals("350") && receipt.getVehicle_type().equals("TURCK_PRIVATE"))) {
				do {
					date1 = date1.plusYears(2);
					month += 24;
					premiumAmount += (premiumResult.get(0).getPremium() * 2);
				} while (LocalDate.now().isAfter(date1));
				YearMonth expireDateYM2 = YearMonth.of(date1.getYear(), date1.getMonthValue());
				if (date1.minusMonths(3).isBefore(LocalDate.now()) || expireDateYM2.minusMonths(3).equals(nowYearMonth)
						|| expireDateYM2.minusMonths(3).isBefore(nowYearMonth)) {
					date1 = date1.plusYears(2);
					month += 24;
					premiumAmount += (premiumResult.get(0).getPremium() * 2);
				}

			} else {
				do {
					date1 = date1.plusYears(1);
					month += 12;
					premiumAmount += (premiumResult.get(0).getPremium());
				} while (LocalDate.now().isAfter(date1));
				YearMonth expireDateYM1 = YearMonth.of(date1.getYear(), date1.getMonthValue());
				if (date1.minusMonths(3).isBefore(LocalDate.now()) || expireDateYM1.minusMonths(3).equals(nowYearMonth)
						|| expireDateYM1.minusMonths(3).isBefore(nowYearMonth)) {
					date1 = date1.plusYears(1);
					month += 12;
					premiumAmount += (premiumResult.get(0).getPremium());
				}
			}
		}

		ThirdPartyPremiumReceiptDTO premiumDTO = new ThirdPartyPremiumReceiptDTO();
		premiumDTO.setResultPremium(premiumResult);
		premiumDTO.setPremiumTotalAmount(premiumAmount);
		premiumDTO.setTotal_month(month);
		premiumDTO.setNextPremiumBuyDate(date1.toString());
		return premiumDTO;
	}

	private boolean isValidDateFormat(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		try {
			format.parse(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ThirdPartyPremiumRecordsDTO> findByFromToDate(String fromDate, String toDate, String branch,
			String convert) {
		List<ThirdPartyPremiumRecordsDTO> receiptDTO = new ArrayList<>();
		List<ThirdPartyPremiumReceipt> receiptList = new ArrayList<>();
		switch (branch.toUpperCase()) {
		case "Y":
			branch = "District Office(West of Yangon)";
			break;
		case "Y1":
			branch = "District Office(South of Yangon)";
			break;
		case "Y2":
			branch = "District Office(North of Yangon)";
			break;
		case "Y3":
			branch = "District Office(East of Yangon)";
			break;
		case "Y4":
			branch = "Regional Office(Yoar Thar Kyi)";
			break;
		default:
			break;
		}
		receiptList.addAll(thirdPartyPremiumReceiptDAO.findByFromToDate(fromDate, toDate, branch, convert));
		receiptList.stream().forEach(receipt -> {
			TwoCTwoPRecords records = twoCTwoPRecordDAO.findByOrderId(receipt.getOrder_id());
			receiptDTO.add(ThirdPartyPremiumReceiptFactory.convertThirdPartyPremiumDTO(receipt, records));
		});
		return receiptDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ThirdPartyPremiumRecordsDTO> findByFromToDateForReport(String fromDate, String toDate, String branch,
			String convert) {
		List<ThirdPartyPremiumRecordsDTO> receiptDTO = new ArrayList<>();
		List<ThirdPartyPremiumReceipt> receiptList = new ArrayList<>();
		switch (branch.toUpperCase()) {
		case "Y":
			branch = "District Office(West of Yangon)";
			break;
		case "Y1":
			branch = "District Office(South of Yangon)";
			break;
		case "Y2":
			branch = "District Office(North of Yangon)";
			break;
		case "Y3":
			branch = "District Office(East of Yangon)";
			break;
		case "Y4":
			branch = "Regional Office(Yoar Thar Kyi)";
			break;
		default:
			break;
		}
		receiptList.addAll(thirdPartyPremiumReceiptDAO.findByFromToDate(fromDate, toDate, branch, convert));
		receiptList.stream().forEach(receipt -> {
			TwoCTwoPRecords records = twoCTwoPRecordDAO.findByOrderId(receipt.getOrder_id());
			try {
				receiptDTO.add(ThirdPartyPremiumReceiptFactory.convertThirdPartyPremiumReportDTO(receipt, records));
			} catch (ParseException e) {

				e.printStackTrace();
			}
		});
		return receiptDTO;
	}

	@Override
	public List<ThirdPartyPremiumReceipt> findReceiptListByVehicleNo(String vehicle_no) throws DAOException {
		List<ThirdPartyPremiumReceipt> result = null;
		try {
			result = thirdPartyPremiumReceiptDAO.findReceiptListByVehicleNo(vehicle_no);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a ThirdPartyPremiumReceipt ", e);
		}
		return result;
	}

	@Override
	public List<ThirdPartyPremiumReceipt> findByDateAndVehicleNo(String fromDate, String toDate, String vehicle_no) {
		List<ThirdPartyPremiumReceipt> result = null;
		try {
			result = thirdPartyPremiumReceiptDAO.findByDateAndVehicleNo(fromDate, toDate, vehicle_no);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a ThirdPartyPremiumReceipt ", e);
		}
		return result;
	}

	@Override
	public ThirdPartyPremiumReceipt updateByPaymentStatus(String orderId) {
		ThirdPartyPremiumReceipt result = null;
		try {
			result = thirdPartyPremiumReceiptDAO.updateByPaymentStatus(orderId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a ThirdPartyPremiumReceipt ", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void postDataSyncProcess(String orderId) {
		logger.info("Start Runnable Interface");
		ThirdPartyPremiumReceiptDTO thirdPartyPremiumReceiptDTOC = ThirdPartyPremiumReceiptFactory
				.convertThirdPartyPremiumDTOCTOC(findRecordsByOrderId(orderId));

		logger.info("FindRecordsByOrderId is Success");
		ProductTypeRecords productType = productTypeRecordsService.findByOrderId(orderId);
		logger.info("FindProductTypeByOrderId is Success");
		try {
			logger.info("Start Call to Other Server");
			calltoOthersServerAPIThirdPartyPremiumReceipt(thirdPartyPremiumReceiptDTOC, productType);
			logger.info("Finish call to Other Server");
		} catch (IOException e) {
			logger.info("Throw error: When call to Other Server");
			updateResponseStatusByOrderId(thirdPartyPremiumReceiptDTOC.getOrderId(), ResponseStatus.REQUEST_NOT_FOUND);
			e.printStackTrace();
		}
	}

	@Override
	public void calltoOthersServerAPIThirdPartyPremiumReceipt(ThirdPartyPremiumReceiptDTO thirdPartyPremiumReceiptDTO,
			ProductTypeRecords productTypeRecords) throws ClientProtocolException, IOException {
		logger.info("Start Call To RestCaller ThirdParty API");
		CloseableHttpClient client = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(URIConstants.CALLTORESTCALLERIPADDRESS + URIConstants.TPL_CLOUD_TO_CORE);
		logger.info("URI is : " + httpPost.getURI());
		HashMap<String, ThirdPartyPremiumReceiptDTO> map = new HashMap<String, ThirdPartyPremiumReceiptDTO>();
		ProductTypeRecordsDTO recordsDTO = new ProductTypeRecordsDTO(productTypeRecords);
		thirdPartyPremiumReceiptDTO.setRecordsDTO(recordsDTO);
		map.put("thirdPartyPremiumReceiptDTO", thirdPartyPremiumReceiptDTO);
		logger.info("First Param address is : " + thirdPartyPremiumReceiptDTO);
		logger.info("Second address is : " + productTypeRecords);
		Gson gson = new Gson();
		String json = gson.toJson(map);
		StringEntity request = new StringEntity(json);
		httpPost.setEntity(request);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		CloseableHttpResponse httpResp = client.execute(httpPost);
		String result = EntityUtils.toString(httpResp.getEntity());
		AceResponse response = gson.fromJson(result, AceResponse.class);
		client.close();
		ResponseStatus status = null;
		logger.info("Response Code is " + response.getCode());
		if (response.getCode() == 200) {
			status = ResponseStatus.SUCCESS;
		} else {
			status = ResponseStatus.FAIL;
		}
		this.updateResponseStatusByOrderId(thirdPartyPremiumReceiptDTO.getOrderId(), status);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateResponseStatusByOrderId(String orderId, ResponseStatus status) {
		thirdPartyPremiumReceiptDAO.updateResponseStatusByOrderId(orderId, status);
	}

	@Override
	public List<ThirdPartyPremiumReceipt> findRecordsByResponseStatus() {
		List<ThirdPartyPremiumReceipt> resultList = null;
		resultList = thirdPartyPremiumReceiptDAO.findRecordsByResponseStatus();
		return resultList;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void batchProcess() throws ClientProtocolException, IOException {
		List<ThirdPartyPremiumReceipt> thirdPartyList = findRecordsByResponseStatus();
		if (thirdPartyList != null && !thirdPartyList.isEmpty()) {
			List<ThirdPartyPremiumReceiptDTO> tpList = ThirdPartyPremiumReceiptFactory
					.convertThridPartyPremiumList(thirdPartyList);
			for (ThirdPartyPremiumReceiptDTO mtp001 : tpList) {
				ProductTypeRecords productTypeRecords = new ProductTypeRecords();
				productTypeRecords.setProductType("TPL Online");
				productTypeRecords.setTwoCtwoPorderId(mtp001.getOrderId());
				calltoOthersServerAPIThirdPartyPremiumReceipt(mtp001, productTypeRecords);
			}
		}
	}

	@Override
	public void updateCovertedStatusByOderId(PaymentOrderConfirmDTO paymentOrderConfirm) {
		for (PaymentOrderConfirm orderId : paymentOrderConfirm.getPaymentOrder()) {
			if (orderId != null) {
				thirdPartyPremiumReceiptDAO.updateConvertedStatusByOrderId(orderId);
			}
		}
	}

}
