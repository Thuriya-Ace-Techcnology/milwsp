package org.ace.ws.controller;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import javax.xml.rpc.ServiceException;

import org.ace.insurance.common.AbstractMynNumConvertor;
import org.ace.insurance.common.DefaultConvertor;
import org.ace.insurance.common.IdType;
import org.ace.insurance.common.PlatFormType;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.QRCodeGenerator;
import org.ace.insurance.product.service.interfaces.IProductService;
import org.ace.insurance.system.mobileUser.MobileUser;
import org.ace.insurance.system.mobileUser.service.interfaces.IMobileUserService;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.system.productTypeRecords.service.interfaces.IProductTypeRecordsService;
import org.ace.insurance.travel.MobileTravelInsuredPerson;
import org.ace.insurance.travel.MobileTravelProposal;
import org.ace.insurance.travel.service.interfaces.IMobileTravelProposalService;
import org.ace.java.component.StatusType;
import org.ace.java.component.SystemException;
import org.ace.java.component.service.PasswordCodecHandler;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.factory.MobileTravelProposalFactory;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.mobilePersonalAccidentproposal.MITravel001;
import org.ace.ws.model.mobilePersonalAccidentproposal.MPAP002;
import org.ace.ws.model.mobiletravelproposal.MIP001;
import org.ace.ws.model.mobiletravelproposal.MTP001;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@CrossOrigin
@Controller
public class MobileTravelProposalController extends BaseController {
	@Resource(name = "MobileTravelProposalService")
	private IMobileTravelProposalService mobileTravelProposalService;

	@Resource(name = "ProductService")
	private IProductService productService;

	@Resource(name = "ProductTypeRecordsService")
	private IProductTypeRecordsService productTypeRecordsService;

	@Resource(name = "MobileUserService")
	private IMobileUserService mobileUserService;

	@Resource(name = "PasswordCodecHandler")
	private PasswordCodecHandler codecHandler;

	private static final Logger logger = Logger.getLogger(MobileTravelProposalController.class);

	@CrossOrigin
	@RequestMapping(value = URIConstants.INSERT_TRAVELPROPOSAL, method = RequestMethod.POST)
	public @ResponseBody String insertMobileTravelProposal(@RequestHeader String key, @RequestBody MTP001 mtp001)
			throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Insert Mobile Travel Proposal.");
		System.out.println("Start Mobile Travel");
		String response = null;
		
		try {
			if (key.toString().equals(Constants.getApikey()) && mtp001 != null) {
				mtp001.setPlatFormType(PlatFormType.MOBILE);
				MobileUser mobileUser = mobileUserService.findMobileUserById(mtp001.getUserId());
				if (mobileUser != null) {
					List<MobileTravelProposal> findRecords = mobileTravelProposalService
							.findByOrderId(mtp001.getOrderId());
					if (findRecords == null || findRecords.isEmpty()) {
						boolean checkID = false;
						boolean govPersonCheck = false;
						for (MIP001 mip : mtp001.getInsuredPersonList()) {
							govPersonCheck = govPersonCheck(mip);
							checkID = checkIdStatus(mip);
							if (govPersonCheck == true || checkID == true) {
								break;
							}
						}
						if (govPersonCheck == true) {
							logger.info("Goverment Person require all goverment information.");
							AceResponse aceResponse = new AceResponse();
							aceResponse.setStatus(HttpStatus.BAD_REQUEST);
							aceResponse.setMessage("Goverment Person require all goverment information.");
							return response = responseManager.getResponseString(aceResponse);
						}
						if (checkID == true) {
							logger.info("Id Type is Passport no but passport no not include.");
							AceResponse aceResponse = new AceResponse();
							aceResponse.setStatus(HttpStatus.BAD_REQUEST);
							aceResponse.setMessage("Id Type is Passport no but passport no not include.");
							return response = responseManager.getResponseString(aceResponse);
						}
						System.out.println("Start addNewTravel method");
						mtp001 = MobileTravelProposalFactory.convertMobileTravelProposalDTO(mobileTravelProposalService
								.addNewTravelProposal(MobileTravelProposalFactory.convertMobileTravelProposal(mtp001)));
						
						System.out.println("End addNewTravel method");
						ProductTypeRecords productTypeRecords = new ProductTypeRecords();
						productTypeRecords.setProductType("Travel");
						productTypeRecords.setTwoCtwoPorderId(mtp001.getOrderId());
						System.out.println("Start productTypeRecord method");
						productTypeRecordsService.insert(productTypeRecords);
						System.out.println("End productTypeRecord method");
						mtp001.setResponseStatus(ResponseStatus.SUCCESS);
						response = responseManager.getResponseString(mtp001);
						return response;
					} else if (checkFindRecords(findRecords, mtp001)) {
						if (mtp001.getTransactionFees() != 0) {
							findRecords.get(0).setTransactionFees(mtp001.getTransactionFees());
						}
						mobileTravelProposalService.updatePremuimAmount(findRecords.get(0),
								mtp001.getInsuredPersonList());
						mtp001 = MobileTravelProposalFactory.convertMobileTravelProposalDTO(
								mobileTravelProposalService.updateNewTravleProposal(findRecords.get(0)));
						MPAP002 mpap002 = new MPAP002();
						mpap002.setResponseStatus(ResponseStatus.SUCCESS);
						mpap002.setTransactionId(mtp001.getTransactionId());
						response = responseManager.getResponseString(mpap002);
					} else {
						MPAP002 mpap002 = new MPAP002();
						mpap002.setResponseStatus(ResponseStatus.SUCCESS);
						mpap002.setTransactionId(findRecords.get(0).getOrderId());
						return responseManager.getResponseString(mpap002);
					}
				} else {
					return responseManager.getResponseString(ResponseStatus.INVALIDUSER.getLabel());
				}
			}
			response = responseManager.getResponseString(mtp001);
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.INTERNAL_SERVER_ERROR.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Insert Mobile Travel Proposal.");
		return response;
	}

	@PostMapping(value = "/travelCoreToCloud",consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody String getCallApI(@RequestBody Map<String,List<MTP001>> body) throws UnsupportedEncodingException, ParseException {
		List<MTP001> dtoList=  body.get("mtp001List");
		MTP001 mtp001 = dtoList.get(0);
		MobileTravelProposalFactory.convertMobileTravelProposalDTO(mobileTravelProposalService
				.addCoreNewTravelProposal(MobileTravelProposalFactory.convertMobileTravelProposal(mtp001)));
		ProductTypeRecords productTypeRecords = new ProductTypeRecords();
		productTypeRecords.setProductType("Travel");
		productTypeRecords.setTwoCtwoPorderId(mtp001.getOrderId());
		productTypeRecordsService.insert(productTypeRecords);
		return "Success";
	}

	@RequestMapping(value = URIConstants.POLICY_LIST_BY_USER, method = RequestMethod.POST)
	public @ResponseBody String getTravelProposalList(
			@RequestHeader(value = "Authorization") List<String> authorization, @RequestHeader String key,
			@RequestBody MITravel001 miTravel) throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Select Travel Proposal List by useridwithrowcount.");
		String response = null;
		ResponseStatus responseStatus = null;
		List<MTP001> mtp001List = new ArrayList<MTP001>();
		try {
			responseStatus = ResponseStatus.SUCCESS;// authorized(key, authorization, userId);
			if (ResponseStatus.SUCCESS.equals(responseStatus)) {
				List<MobileTravelProposal> mobileTravelProposalList = mobileTravelProposalService
						.findByMobileUserWithRowCount(miTravel.getUserId(), miTravel.getCount());
				mtp001List = MobileTravelProposalFactory.convertMobileTravelProposalDTOList(mobileTravelProposalList);
				response = responseManager.getResponseString(mtp001List);
			} else {
				response = responseManager.getResponseString(responseStatus.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.INTERNAL_SERVER_ERROR.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Select Travel Proposal List by useridwithrowcount.");
		return response;
	}

	@RequestMapping(value = URIConstants.TRAVEL_POLICY_LETTER, method = RequestMethod.GET)
	public void getPAPolicyCertificatePDF(HttpServletResponse response,
			@QueryParam("transactionId") String transactionId) {
		logger.info("Start Travel Policy Letter function");
		try {
			MobileTravelProposal mobileTravelProposal = mobileTravelProposalService
					.findMobileTravelProposalByTransactionCode(transactionId);
			List<Map<String, Object>> paramMapList = new ArrayList<>();
			if (ProposalStatus.ISSUED.equals(mobileTravelProposal.getProposalStatus())) {
				logger.info("Proposal Status is ISSUED");
				for (MobileTravelInsuredPerson person : mobileTravelProposal.getInsuredPersonList()) {
					logger.info("Mobile Travel Insured Person work");
					Map<String, Object> paramMap = new HashMap<>();
					AbstractMynNumConvertor convertor = new DefaultConvertor();
					// double sumInsured = person.getSumInsured();
					double premium = person.getPremium();
					double addOn = person.getFlightCoverAmount();
					double overSeaAmount = person.getOverSeaAmount();
					double transFee = mobileTravelProposal.getTransactionFees();
					paramMap.put("policyNo", mobileTravelProposal.getProposalNo());
					paramMap.put("insuredPersonName", person.getFirstName() + " " + person.getLastName());
					paramMap.put("fatherName", person.getFatherName() == null ? "" : person.getFatherName());
					paramMap.put("address", "");
					paramMap.put("idNo", person.getIdNo() != null ? person.getIdNo() : "-");
					paramMap.put("route", person.getRoute());
					paramMap.put("unit", person.getUnit());
					paramMap.put("milogo", "document-template/images/MI-logo.png");
					paramMap.put("orderId", mobileTravelProposal.getOrderId());
					if (mobileTravelProposal.isAirProduct()) {
						paramMap.put("durationCover",
								org.ace.insurance.common.Utils.getDateFormatString(person.getDepartureDate()));
						paramMap.put("travelType",
								mobileTravelProposal.isOverSea() ? "Air Oversea Travel" : "Air Domestic Travel");
					} else if (mobileTravelProposal.isOverSea()) {
						paramMap.put("durationCover",
								"FROM (" + org.ace.insurance.common.Utils.getDateFormatString(person.getDepartureDate())
										+ ") TO ("
										+ org.ace.insurance.common.Utils.getDateFormatString(person.getArrivalDate())
										+ ")");
						paramMap.put("travelType", "Oversea Travel");
					} else {
						paramMap.put("durationCover",
								"FROM (" + org.ace.insurance.common.Utils.getDateFormatString(person.getDepartureDate())
										+ ") TO ("
										+ org.ace.insurance.common.Utils.getDateFormatString(person.getArrivalDate())
										+ ")");
						paramMap.put("travelType", "Domestic Travel");
					}

					BufferedImage qrcode = QRCodeGenerator.generateQRCodeImage(mobileTravelProposal.getProposalNo());
					paramMap.put("qrcode", qrcode);
					// paramMap.put("ggimage", getCompanyIcon());
					// paramMap.put("companyAddress", getCompanyAddress());

					// paramMap.put("address", resi dentAddress + "( " + person.getPhoneNumber() +
					// ")");
					// paramMap.put("sumInsured",
					// org.ace.insurance.common.Utils.formattedCurrency(sumInsured));
					paramMap.put("premium", org.ace.insurance.common.Utils
							.formattedCurrency(premium + addOn + overSeaAmount + transFee));

					// paramMap.put("sumInsuredInWord", convertor.getNameWithDecimal(sumInsured));
					paramMap.put("premiumInWord", convertor.getNameWithDecimal(premium));
					paramMap.put("buyDate",
							org.ace.insurance.common.Utils.getDateFormatString(mobileTravelProposal.getPaymentDate()));
					paramMap.put("date", org.ace.insurance.common.Utils.getDateFormatString(new Date()));

					paramMap.put("policyStartDate",
							org.ace.insurance.common.Utils.getDateFormatString(person.getDepartureDate()));
					paramMap.put("policyEndDate",
							org.ace.insurance.common.Utils.getDateFormatString(person.getArrivalDate()));
					// paramMap.put("period", mobilePAProposal.getPeriodMonth());
					// paramMap.put("beneficiaryList", populateBeneficiariesList(person));
					paramMapList.add(paramMap);
				}
				// getJasperFactory();
				logger.info("Before PDF generate call");
				// mobileTravelProposalService.createRequest();
				response.setContentType("application/x-download");
				response.setHeader("Content-Disposition", "inline; filename=TravelPolicyLetter.pdf");
				final OutputStream outputStream = response.getOutputStream();
				InputStream inputStream = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("document-template/newTamplates/MOBILE_Travel_PolicyLetter.jrxml");
				JasperDesign design = JRXmlLoader.load(inputStream);
				JasperReport jreport = JasperCompileManager.compileReport(design);
				logger.info("Start PDF compile");
				JasperExportManager.exportReportToPdfStream(generateJasperPrint(jreport, paramMapList), outputStream);
			} else {
				logger.info("This Process pending");
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	private JasperPrint generateJasperPrint(JasperReport jreport, List<Map<String, Object>> paramMap)
			throws JRException {
		logger.info("Before Jprint ");
		JasperPrint jprint = JasperFillManager.fillReport(jreport, paramMap.get(0), new JREmptyDataSource());
		for (int i = 1; i < paramMap.size(); i++) {
			JasperPrint jPrintLocal = JasperFillManager.fillReport(jreport, paramMap.get(i), new JREmptyDataSource());
			jprint.addPage(i, jPrintLocal.getPages().get(0));
		}
		logger.info("Done Jprint");
		return jprint;
	}

	private boolean checkFindRecords(List<MobileTravelProposal> findRecords, MTP001 mtp001) {
		if (findRecords != null) {
			if (findRecords.get(0).getUserId().equals(mtp001.getUserId())
					&& findRecords.get(0).getOrderId().equals(mtp001.getOrderId())) {
				return true;
			}
		}
		return false;
	}

	private boolean govPersonCheck(MIP001 mip) {
		boolean govPersonCheck = false;
		if (mip.isGovPerson() == true) {
			if (mip.getGovDate() == null || mip.getGovDate().equals("")) {
				govPersonCheck = true;
			} else if (mip.getGovDepart() == null || mip.getGovDepart().equals("")) {
				govPersonCheck = true;
			} else if (mip.getGovRefNo() == null || mip.getGovRefNo().equals("")) {
				govPersonCheck = true;
			}
		}
		return govPersonCheck;
	}

	private boolean checkIdStatus(MIP001 mip) {
		boolean checkID = false;
		if (mip.getIdType() == null) {
			checkID = true;
		}
		if (mip.getIdType().equals(IdType.PASSPORTNO) && mip.getIdNo() == null) {
			checkID = true;
		}
		if (mip.getIdType().equals(IdType.PASSPORTNO) && mip.getIdNo().equals("")) {
			checkID = true;
		}
		return checkID;
	}
}
