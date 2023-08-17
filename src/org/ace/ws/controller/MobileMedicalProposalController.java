package org.ace.ws.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import javax.xml.rpc.ServiceException;

import org.ace.insurance.common.ErrorCode;
import org.ace.insurance.common.IdType;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.Utils;
import org.ace.insurance.medical.MobileMedicalProposal;
import org.ace.insurance.medical.MobileMedicalProposalInsuredPerson;
import org.ace.insurance.medical.MobileMedicalProposalInsuredPersonAddOn;
import org.ace.insurance.medical.MobileMedicalProposalInsuredPersonBeneficiaries;
import org.ace.insurance.medical.service.interfaces.IMobileMedicalProposalService;
import org.ace.insurance.product.service.interfaces.IProductService;
import org.ace.insurance.system.common.occupation.Occupation;
import org.ace.insurance.system.common.occupation.persistence.interfaces.IOccupationDAO;
import org.ace.insurance.system.common.relationship.RelationShip;
import org.ace.insurance.system.common.relationship.persistence.interfaces.IRelationShipDAO;
import org.ace.insurance.system.common.township.Township;
import org.ace.insurance.system.common.township.persistence.interfaces.ITownshipDAO;
import org.ace.insurance.system.mobileUser.MobileUser;
import org.ace.insurance.system.mobileUser.service.interfaces.IMobileUserService;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.system.productTypeRecords.service.interfaces.IProductTypeRecordsService;
import org.ace.java.component.StatusType;
import org.ace.java.component.SystemException;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.factory.MobileMedicalProposalFactory;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.mobileMedicalproposal.InsuredPersonDTO;
import org.ace.ws.model.mobileMedicalproposal.MedicalProposalDTO;
import org.ace.ws.model.mobileMedicalproposal.MedicalProposalInsuredPersonDTO;
import org.ace.ws.model.mobilePersonalAccidentproposal.MBP001;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@Controller
public class MobileMedicalProposalController extends BaseController {
	@Resource(name = "MobileMedicalProposalService")
	private IMobileMedicalProposalService mobileMedicalProposalService;

	@Resource(name = "ProductTypeRecordsService")
	private IProductTypeRecordsService productTypeRecordsService;

	@Resource(name = "ProductService")
	private IProductService productService;

	@Resource(name = "MobileUserService")
	private IMobileUserService mobileUserService;

	@Resource(name = "TownshipDAO")
	private ITownshipDAO townshipDAO;

	@Resource(name = "OccupationDAO")
	private IOccupationDAO occupationDAO;

	@Resource(name = "RelationShipDAO")
	private IRelationShipDAO relationShipDAO;

	private static final Logger logger = Logger.getLogger(MobileMedicalProposalController.class);

	@CrossOrigin
	@RequestMapping(value = URIConstants.INSERT_MEDICAL_PROPOSAL, method = RequestMethod.POST)
	public @ResponseBody String insertMedicalProposal(@RequestHeader String key, @RequestBody MedicalProposalDTO dto)
			throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Insert Mobile Medical Proposal.");
		String response = null;
		try {
			if (key.toString().equals(Constants.getApikey()) && dto != null) {
				MobileUser mobileUser = mobileUserService.findMobileUserById(dto.getUserId());
				if (mobileUser != null) {
					List<MobileMedicalProposal> findRecords = mobileMedicalProposalService
							.findByOrderId(dto.getOrderId());
					if (findRecords == null || findRecords.isEmpty()) {
						for (MedicalProposalInsuredPersonDTO mip : dto.getInsuredPersonList()) {
							if (mip.getIdType() == null) {
								logger.info("Id Type is Passport no but passport no not include.");
								AceResponse aceResponse = new AceResponse();
								aceResponse.setStatus(HttpStatus.BAD_REQUEST);
								aceResponse.setMessage("Id Type is Passport no but passport no not include.");
								return response = responseManager.getResponseString(aceResponse);
							}
							if (mip.getIdType().equals(IdType.PASSPORTNO)
									&& (null == mip.getIdNo() || mip.getIdNo().equals(""))) {
								logger.info("Id Type is Passport no but passport no not include.");
								AceResponse aceResponse = new AceResponse();
								aceResponse.setStatus(HttpStatus.BAD_REQUEST);
								aceResponse.setMessage("Id Type is Passport no but passport no not include.");
								return response = responseManager.getResponseString(aceResponse);
							}
							if (mip.getUnit() > 3) {
								logger.info("Insured Person Unit must not over 3 units.");
								AceResponse aceResponse = new AceResponse();
								aceResponse.setStatus(HttpStatus.BAD_REQUEST);
								aceResponse.setMessage("Insured Person Unit must not over 3 units.");
								return response = responseManager.getResponseString(aceResponse);

							}
						}
						dto = MobileMedicalProposalFactory.convertMobileMedicalProposalDTO(mobileMedicalProposalService
								.addNewMedicalProposal(MobileMedicalProposalFactory.convertMobileMedicalProposal(dto)));
						ProductTypeRecords productTypeRecords = new ProductTypeRecords();
						productTypeRecords.setProductType("Medical");
						productTypeRecords.setTwoCtwoPorderId(dto.getOrderId());
						productTypeRecordsService.insert(productTypeRecords);

						dto.setResponseStatus(ResponseStatus.SUCCESS);
						response = responseManager.getResponseString(dto);
						return response;
					} else if (checkFindRecords(findRecords, dto)) {
						if (dto.getTransactionFees() != 0) {
							findRecords.get(0).setTransactionFees(dto.getTransactionFees());
						}
						mobileMedicalProposalService.updatePremuimAmount(findRecords.get(0),
								dto.getInsuredPersonList());
						dto = MobileMedicalProposalFactory.convertMobileMedicalProposalDTO(
								mobileMedicalProposalService.updateNewMedicalProposal(findRecords.get(0)));
						MedicalProposalDTO mpap002 = new MedicalProposalDTO();
						mpap002.setResponseStatus(ResponseStatus.SUCCESS);
						mpap002.setTransactionId(dto.getTransactionId());
						mpap002.setTransactionFees(dto.getTransactionFees());
						response = responseManager.getResponseString(mpap002);
					} else {
						MedicalProposalDTO mpap002 = new MedicalProposalDTO();
						mpap002.setResponseStatus(ResponseStatus.SUCCESS);
						mpap002.setTransactionId(findRecords.get(0).getOrderId());
						return responseManager.getResponseString(mpap002);
					}
				} else {
					return responseManager.getResponseString(ResponseStatus.INVALIDUSER.getLabel());
				}
				// response = responseManager.getResponseString(dto);
			} else {
				logger.info("API key  is incorrect or null.");
				AceResponse aceResponse = new AceResponse();
				aceResponse.setStatus(HttpStatus.BAD_REQUEST);
				aceResponse.setMessage("API key  is incorrect or null.");
				return response = responseManager.getResponseString(aceResponse);
			}

		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.INTERNAL_SERVER_ERROR.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Insert Mobile Medical Proposal.");
		return response;
	}

	@RequestMapping(value = URIConstants.MEDICAL_POLICY_LIST_BY_USER, method = RequestMethod.GET)
	public @ResponseBody String getMedicalProposalList(@RequestHeader String key, @QueryParam("userId") String userId)
			throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Select Medical Proposal List by useridwithrowcount.");
		String response = null;
		try {
			MobileUser mobileUser = mobileUserService.findMobileUserById(userId);
			if (mobileUser != null) {
				List<InsuredPersonDTO> mobileMedicalPersonList = mobileMedicalProposalService.findByMobileUser(userId);
				response = responseManager.getResponseString(mobileMedicalPersonList);
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALIDUSER.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.INTERNAL_SERVER_ERROR.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Select Medical Proposal List by useridwithrowcount.");
		return response;
	}

	private boolean checkFindRecords(List<MobileMedicalProposal> findRecords, MedicalProposalDTO mpap001) {
		if (findRecords != null) {
			if (findRecords.get(0).getUserId().equals(mpap001.getUserId())
					&& findRecords.get(0).getOrderId().equals(mpap001.getOrderId())) {
				return true;
			}
		}
		return false;
	}

	@RequestMapping(value = URIConstants.MEDICAL_POLICY_LIST_BY_REFNO, method = RequestMethod.GET)
	public @ResponseBody String getMedicalProposalbyReferenceNo(@RequestHeader String key,
			@QueryParam("referenceNo") String referenceNo) throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Select  Mobile Medical Proposal By referanceNo.");
		String response = null;
		referenceNo = referenceNo.replace("\"", "").replace("{", "").replace("}", "");
		referenceNo = referenceNo.replace("\n", "");
		referenceNo = referenceNo.replace("\t", "");
		referenceNo = referenceNo.replace(" ", "");
		referenceNo = referenceNo.replace("referenceNo:", "");
		MedicalProposalDTO mpa001;
		try {
			MobileMedicalProposal mobileMedicalProposal = mobileMedicalProposalService.findProposalByRefNo(referenceNo);
			if (mobileMedicalProposal != null) {
				mpa001 = MobileMedicalProposalFactory.convertMobileMedicalProposalDTO(mobileMedicalProposal);
				response = responseManager.getResponseString(mpa001);
			} else {
				response = "{\"responseStatus\":\"EMPTY\"}";
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.INTERNAL_SERVER_ERROR.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Select Medical Proposal List by RefNo.");
		return response;
	}

	@RequestMapping(value = URIConstants.HEALTH_POLICY_LETTER, method = RequestMethod.GET)
	public void getHealthPolicyCertificatePDF(HttpServletResponse response,
			@QueryParam("referenceNo") String referenceNo) throws IOException {

		try {
			Map<String, Object> paramMap = new HashMap<>();
			MobileMedicalProposal mobileMedicalProposal = mobileMedicalProposalService.findProposalByRefNo(referenceNo);
			if (ProposalStatus.ISSUED.equals(mobileMedicalProposal.getProposalStatus())) {
				for (MobileMedicalProposalInsuredPerson person : mobileMedicalProposal
						.getMedicalProposalInsuredPersonList()) {
					MobileMedicalProposalInsuredPersonAddOn addOn = new MobileMedicalProposalInsuredPersonAddOn();
					if (person.getInsuredPersonAddOnList() != null && !person.getInsuredPersonAddOnList().isEmpty()) {
						addOn = person.getInsuredPersonAddOnList().get(0);
					}
					Township township = townshipDAO.findById(person.getTownshipId());
					Occupation occupation = occupationDAO.findById(person.getOccupationId());

					String residentAddress;
					if (person.getAddress() == null || township == null) {
						residentAddress = "-";
					} else {
						residentAddress = person.getAddress() + ", " + township.getFullTownShip();
					}
					for (MobileMedicalProposalInsuredPersonBeneficiaries insuredPersonBeneficiaries : person
							.getInsuredPersonBeneficiariesList()) {
						paramMap.put("bName", insuredPersonBeneficiaries.getFullName());
						paramMap.put("bIdNo", insuredPersonBeneficiaries.getIdNo() != null
								? insuredPersonBeneficiaries.getIdType() + " " + insuredPersonBeneficiaries.getIdNo()
								: "-");
						paramMap.put("brelationshipId", insuredPersonBeneficiaries.getRelationshipId() == null ? "-"
								: insuredPersonBeneficiaries.getRelationshipId());
						paramMap.put("bAddress", insuredPersonBeneficiaries.getAddress() == null ? "-"
								: insuredPersonBeneficiaries.getAddress());
						paramMap.put("bPhoneNo", insuredPersonBeneficiaries.getPhoneNo() == null ? "-"
								: insuredPersonBeneficiaries.getPhoneNo());
						paramMap.put("bAge", insuredPersonBeneficiaries.getDateOfBirth() == null ? "-"
								: (Utils.getDateFormatString(insuredPersonBeneficiaries.getDateOfBirth())));
					}
					double sumInsured = person.getAddOnPremium() + person.getBasicPremium();
					double premium = person.getBasicPremium() + addOn.getPremium();
					paramMap.put("policyNo",
							mobileMedicalProposal.getPolicyNo() == null ? "-" : mobileMedicalProposal.getPolicyNo());
					paramMap.put("periodMonth", mobileMedicalProposal.getPeriodMonth());
					paramMap.put("paymentTypeId", mobileMedicalProposal.getPaymentTypeId());
					paramMap.put("insuredPersonName", person.getFullName());
					paramMap.put("fatherName", person.getFatherName() == null ? "-" : person.getFatherName());
					paramMap.put("unit", person.getUnit());
					paramMap.put("totalUnit", "" + person.getUnit());
					paramMap.put("age", person.getAge());
					paramMap.put("phoneNo", person.getPhoneNo() == null ? "-" : person.getPhoneNo());
					paramMap.put("idNo", person.getIdNo() != null ? person.getIdType() + " " + person.getIdNo() : "-");
					paramMap.put("occupation", occupation != null ? occupation.getName() : "-");
					paramMap.put("address", residentAddress);
					paramMap.put("sumInsured", org.ace.insurance.common.Utils.formattedCurrency(sumInsured));
					paramMap.put("premium", org.ace.insurance.common.Utils.formattedCurrency(premium));
					paramMap.put("policyStartDate", org.ace.insurance.common.Utils
							.getDateFormatString(mobileMedicalProposal.getActivedPolicyStartDate()));
					paramMap.put("policyEndDate", org.ace.insurance.common.Utils
							.getDateFormatString(mobileMedicalProposal.getActivedPolicyEndDate()));
					paramMap.put("period", mobileMedicalProposal.getPeriodMonth());
					paramMap.put("logo_imgPath", "document-template/images/MI-logo.png");
					InputStream inputStream = Thread.currentThread().getContextClassLoader()
							.getResourceAsStream("document-template/MOBILE_Health_PolicyLetter.jrxml");
					JasperDesign design = JRXmlLoader.load(inputStream);
					JasperReport jreport = JasperCompileManager.compileReport(design);
					JasperPrint jprint = JasperFillManager.fillReport(jreport, paramMap, new JREmptyDataSource());
					response.setContentType("application/x-download");
					response.setHeader("Content-Disposition", "inline; filename=health.pdf");
					final OutputStream outputStream = response.getOutputStream();

					JasperExportManager.exportReportToPdfStream(jprint, outputStream);
				}

			} else {
				logger.info("This Process pending");
			}
		} catch (JRException ex) {
			logger.info("file jrxml.");
			throw new SystemException(ErrorCode.SYSTEM_ERROR, "Failed to export PDF file", ex);
		} catch (IOException ex) {
			logger.info(" Sever error");

		}

	}

	public List<MBP001> populateBeneficiariesList(MobileMedicalProposalInsuredPerson person) {
		List<MBP001> beneficiariesList = new ArrayList<>();
		for (MobileMedicalProposalInsuredPersonBeneficiaries beneficiaries : person
				.getInsuredPersonBeneficiariesList()) {
			MBP001 beneficiary = new MBP001();
			beneficiary.setFullName(beneficiaries.getFullName());
			beneficiary.setIdNo(beneficiaries.getIdNo());
			beneficiary.setAge(Utils.getAgeForNextYear(beneficiaries.getDateOfBirth()));
			beneficiary.setDateOFBirth(beneficiaries.getDateOfBirth());
			beneficiary.setAgeAndDateOfBirth(beneficiary.getAge() + " / "
					+ org.ace.insurance.common.Utils.getDateFormatString(beneficiary.getDateOFBirth()));
			RelationShip relationship = relationShipDAO.findById(beneficiaries.getRelationshipId());
			beneficiary.setRelationshipId(relationship.getName());
			Township township = townshipDAO.findById(beneficiaries.getTownshipId());
			String residentAddress = beneficiaries.getAddress() + "," + township.getFullTownShip() + "( "
					+ beneficiaries.getPhoneNo() + " )";
			beneficiary.setAddress(residentAddress);
			float percentage = beneficiaries.getPercentage();
			beneficiary.setPercentage(percentage);
			beneficiariesList.add(beneficiary);

		}
		return beneficiariesList;

	}
}
