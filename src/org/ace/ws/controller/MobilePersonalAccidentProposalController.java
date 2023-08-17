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

import org.ace.insurance.common.AbstractMynNumConvertor;
import org.ace.insurance.common.DefaultConvertor;
import org.ace.insurance.common.ErrorCode;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.SystemConstants;
import org.ace.insurance.common.UploadFileConfig;
import org.ace.insurance.common.Utils;
import org.ace.insurance.personalAccident.GInsuredPersonAddon;
import org.ace.insurance.personalAccident.GInsuredPersonBeneficiaries;
import org.ace.insurance.personalAccident.MobilePersonalAccidentInsuredPerson;
import org.ace.insurance.personalAccident.MobilePersonalAccidentProposal;
import org.ace.insurance.personalAccident.service.interfaces.IMobilePersonalAccidentProposalService;
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
import org.ace.ws.factory.MobilePersonalAccidentProposalFactory;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.mobilePersonalAccidentproposal.MBP001;
import org.ace.ws.model.mobilePersonalAccidentproposal.MPAP001;
import org.ace.ws.model.mobilePersonalAccidentproposal.PAIP001;
import org.ace.ws.model.mobilePersonalAccidentproposal.PAInsuredPerson001;
import org.ace.ws.model.mobilePersonalAccidentproposal.PAInsuredPerson002;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

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
public class MobilePersonalAccidentProposalController extends BaseController {
	@Resource(name = "MobilePersonalAccidentProposalService")
	private IMobilePersonalAccidentProposalService mobilePersonalAccidentProposalService;

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

	private static final Logger logger = Logger.getLogger(MobilePersonalAccidentProposalController.class);

	@RequestMapping(value = URIConstants.INSERT_FILE, method = RequestMethod.POST)
	public @ResponseBody void insertFile(@RequestParam("fileList") List<MultipartFile> fileList,
			@RequestParam("name") String name) throws IOException {
		String n;
		List<String> nameList = new ArrayList<>();
		for (MultipartFile file : fileList) {
			n = file.getOriginalFilename();
			String filejpgName = n.replace("HEIC", "jpg");
			nameList.add(filejpgName);
		}
	}

	@CrossOrigin
	@RequestMapping(value = URIConstants.INSERT_PA_PROPOSAL, method = RequestMethod.POST)
	public @ResponseBody String insertPAProposal(@RequestHeader String key,
			@RequestParam("fileList") List<MultipartFile> fileList, @RequestParam("proposalDto") String dto)
			throws IOException, ServiceException {
		Map<String, String> proposalUploadedFileMap;
		AceResponse aceResponse = new AceResponse();
		String response = null;
		Gson gson = new Gson();
		MPAP001 mpap001 = gson.fromJson(dto, MPAP001.class);
		logger.info("Start Insert Mobile Personal Accident Proposal.");
		String filePath = new String();
		String attachmentId;
		try {
			if (key.toString().equals(Constants.getApikey()) && mpap001 != null && fileList != null) {
				MobileUser mobileUser = mobileUserService.findMobileUserById(mpap001.getUserId());
				if (mobileUser != null) {
					proposalUploadedFileMap = new HashMap<String, String>();
					attachmentId = customIDGenerator.getNextTransactionId(SystemConstants.M_ATTACHMENT_NO);
					String path = "/upload/PA-proposal/" + attachmentId + "/";
					if (fileList != null) {
						if (mpap001.getInsuredPersonList() != null && mpap001.getInsuredPersonList().size() > 0) {
							List<PAIP001> personList = new ArrayList<>();
							for (PAIP001 person : mpap001.getInsuredPersonList()) {
								List<MBP001> beneficiaryList = new ArrayList<>();
								for (MBP001 beneficiary : person.getInsuredPersonBeneficiariesList()) {
									if (beneficiary.getNrcFrontName() != null) {
										for (MultipartFile mf : fileList) {
											if (beneficiary.getNrcFrontName().replace(".HEIC", ".jpg")
													.equals(mf.getOriginalFilename().replace(".HEIC", ".jpg"))) {
												String filename = System.currentTimeMillis()
														+ mf.getOriginalFilename().replace(".HEIC", ".jpg");
												filePath = path + filename;
												beneficiary.setNrcFrontImage(mf.getBytes());
											}
										}
									}
									if (beneficiary.getNrcBackName() != null) {
										for (MultipartFile mf : fileList) {
											if (beneficiary.getNrcBackName().replace(".HEIC", ".jpg")
													.equals(mf.getOriginalFilename().replace(".HEIC", ".jpg"))) {
												String filename = System.currentTimeMillis()
														+ mf.getOriginalFilename().replace(".HEIC", ".jpg");
												filePath = path + filename;
												beneficiary.setNrcBackImage(mf.getBytes());
											}
										}
									}
									beneficiaryList.add(beneficiary);
								}
								person.setInsuredPersonBeneficiariesList(beneficiaryList);
								if (fileList.size() == 2) {
									for (int size = 0; size < 2; size++) {
										MultipartFile mf = fileList.get(size);
										if (size == 0) {
											String filename = System.currentTimeMillis()
													+ mf.getOriginalFilename().replace("HEIC", "jpg");
											filePath = path + filename;
											person.setNrcFrontName(filename);
											person.setNrcFrontfilePath(filePath);
											person.setNrcFrontImage(mf.getBytes());
										}
										if (size == 1) {
											String filename = System.currentTimeMillis()
													+ mf.getOriginalFilename().replace(".HEIC", ".jpg");
											filePath = path + filename;
											person.setNrcBackName(filename);
											person.setNrcBackfilePath(filePath);
											person.setNrcBackImage(mf.getBytes());
										}
									}
								}
								personList.add(person);
							}
							mpap001.setInsuredPersonList(personList);
						}
					}
					List<MobilePersonalAccidentProposal> findRecords = mobilePersonalAccidentProposalService
							.findByOrderId(mpap001.getOrderId());
					if (findRecords == null || findRecords.isEmpty()) {
						mpap001 = MobilePersonalAccidentProposalFactory
								.convertMobilePAProposalDTO(mobilePersonalAccidentProposalService.addNewPAProposal(
										MobilePersonalAccidentProposalFactory.convertMobilePAProposal(mpap001)));
						ProductTypeRecords productTypeRecords = new ProductTypeRecords();
						productTypeRecords.setProductType("PA");
						productTypeRecords.setTwoCtwoPorderId(mpap001.getOrderId());
						productTypeRecordsService.insert(productTypeRecords);
						aceResponse.setMessage("Success");
						aceResponse.setStatus(HttpStatus.OK);
						aceResponse.setData(mpap001);
						response = responseManager.getResponseString(aceResponse);
					} else if (checkFindRecords(findRecords, mpap001)) {
						if (mpap001.getTransactionFees() != 0) {
							findRecords.get(0).setTransactionFees(mpap001.getTransactionFees());
						}
						if (mpap001.getInsuredPersonList() != null && mpap001.getInsuredPersonList().size() > 0) {
							mobilePersonalAccidentProposalService.updatePremuimAmount(findRecords.get(0),
									mpap001.getInsuredPersonList());
						}
						mpap001 = MobilePersonalAccidentProposalFactory.convertMobilePAProposalDTO(
								mobilePersonalAccidentProposalService.updateNewPAProposal(findRecords.get(0)));
						aceResponse.setMessage("Success");
						aceResponse.setStatus(HttpStatus.OK);
						aceResponse.setData(mpap001);
						response = responseManager.getResponseString(aceResponse);

					} else {
						aceResponse.setMessage("Success");
						aceResponse.setStatus(HttpStatus.OK);
						aceResponse.setData(findRecords.get(0).getOrderId());
						response = responseManager.getResponseString(aceResponse);
					}
					return response;
				} else {
					aceResponse.setMessage(ResponseStatus.INVALIDUSER.getLabel());
					return responseManager.getResponseString(aceResponse);
				}
			}
			aceResponse.setMessage(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			response = responseManager.getResponseString(aceResponse);
		} catch (

		SystemException e) {
			e.printStackTrace();
			aceResponse.setMessage(ResponseStatus.FAIL.getLabel());
			response = responseManager.getResponseString(aceResponse);
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Insert Mobile Personal Accident Proposal.");
		return response;
	}

	@RequestMapping(value = URIConstants.PA_POLICY_LIST_BY_USER, method = RequestMethod.GET)
	public @ResponseBody String getPAProposalList(@RequestHeader String key, @QueryParam("count") Integer count,
			@QueryParam("userId") String userId) throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Select PA Proposal List by useridwithrowcount.");
		String response = null;
		try {
			MobileUser mobileUser = mobileUserService.findMobileUserById(userId);
			if (mobileUser != null) {
				List<PAInsuredPerson002> pAPersonDtoList = new ArrayList<>();
				List<PAInsuredPerson001> mobilePAPersonList = mobilePersonalAccidentProposalService
						.findByMobileUserWithRowCount(userId, count);
				pAPersonDtoList = MobilePersonalAccidentProposalFactory.convertMobilePADTOList(mobilePAPersonList);
				response = responseManager.getResponseString(pAPersonDtoList);
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALIDUSER.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.INTERNAL_SERVER_ERROR.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Select PA Proposal List by useridwithrowcount.");
		return response;
	}

	@RequestMapping(value = URIConstants.PA_POLICY_LIST_BY_REFNO, method = RequestMethod.GET)
	public @ResponseBody String getPAProposalbyReferenceNo(@RequestHeader String key,
			@QueryParam("referenceNo") String referenceNo) throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Select  Mobile Personal Accident Proposal By referanceNo.");
		String response = null;
		referenceNo = referenceNo.replace("\"", "").replace("{", "").replace("}", "");
		referenceNo = referenceNo.replace("\n", "");
		referenceNo = referenceNo.replace("\t", "");
		referenceNo = referenceNo.replace(" ", "");
		referenceNo = referenceNo.replace("referenceNo:", "");
		MPAP001 mpa001;
		try {
			MobilePersonalAccidentProposal mobilePAProposal = mobilePersonalAccidentProposalService
					.findProposalByRefNo(referenceNo);
			if (mobilePAProposal != null) {
				mpa001 = MobilePersonalAccidentProposalFactory.convertMobilePAProposalDTO(mobilePAProposal);
				response = responseManager.getResponseString(mpa001);
			} else {
				response = "{\"responseStatus\":\"EMPTY\"}";
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(ResponseStatus.INTERNAL_SERVER_ERROR.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}
		logger.info("End Select PA Proposal List by RefNo.");
		return response;
	}

	@RequestMapping(value = URIConstants.PA_POLICY_LETTER, method = RequestMethod.GET)
	public void getPAPolicyCertificatePDF(HttpServletResponse response, @QueryParam("referenceNo") String referenceNo) {

		try {
			Map<String, Object> paramMap = new HashMap<>();
			MobilePersonalAccidentProposal mobilePAProposal = mobilePersonalAccidentProposalService
					.findProposalByRefNo(referenceNo);
			if (ProposalStatus.ISSUED.equals(mobilePAProposal.getProposalStatus())) {
				for (MobilePersonalAccidentInsuredPerson person : mobilePAProposal.getInsuredPersonList()) {

					GInsuredPersonAddon addOn = new GInsuredPersonAddon();
					if (person.getInsuredPersonAddOnList() != null) {
						addOn = person.getInsuredPersonAddOnList().get(0);
					}
					Township township = townshipDAO.findById(person.getTownshipId());
					Occupation occupation = occupationDAO.findById(person.getOccupationId());

					String residentAddress;
					if (person.getAddress() == null || township == null) {
						residentAddress = "";
					} else {
						residentAddress = person.getAddress() + ", " + township.getFullTownShip();
					}

					AbstractMynNumConvertor convertor = new DefaultConvertor();
					double sumInsured = person.getSumInsured();
					double premium = person.getPremium() + addOn.getPremium();
					paramMap.put("policyNo", mobilePAProposal.getPolicyNo() == null ? "-" :mobilePAProposal.getPolicyNo());
					paramMap.put("insuredPersonName", person.getFullName());
					paramMap.put("fatherName", person.getFatherName());
					paramMap.put("idNo", person.getIdNo() != null ? person.getIdNo() : "-");
					paramMap.put("occupation", occupation.getName());
					// paramMap.put("ggimage", getCompanyIcon());
					// paramMap.put("companyAddress", getCompanyAddress());

					paramMap.put("address", residentAddress + "( " + person.getPhoneNumber() + ")");
					paramMap.put("sumInsured", org.ace.insurance.common.Utils.formattedCurrency(sumInsured));
					paramMap.put("premium", org.ace.insurance.common.Utils.formattedCurrency(premium));

					paramMap.put("sumInsuredInWord", convertor.getNameWithDecimal(sumInsured));
					paramMap.put("premiumInWord", convertor.getNameWithDecimal(premium));

					paramMap.put("policyStartDate", org.ace.insurance.common.Utils
							.getDateFormatString(mobilePAProposal.getActivedPolicyStartDate()));
					paramMap.put("policyEndDate", org.ace.insurance.common.Utils
							.getDateFormatString(mobilePAProposal.getActivedPolicyEndDate()));
					paramMap.put("period", mobilePAProposal.getPeriodMonth());
					paramMap.put("beneficiaryList", populateBeneficiariesList(person));
					paramMap.put("logo_imgPath","document-template/images/MI-logo.png");
					InputStream inputStream = Thread.currentThread().getContextClassLoader()
							.getResourceAsStream("document-template/MOBILE_PA_PolicyLetter.jrxml");
					JasperDesign design = JRXmlLoader.load(inputStream);
					JasperReport jreport = JasperCompileManager.compileReport(design);
					JasperPrint jprint = JasperFillManager.fillReport(jreport, paramMap, new JREmptyDataSource());
					response.setContentType("application/x-download");
					response.setHeader("Content-Disposition", "inline; filename=personalAccident.pdf");
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

	public List<MBP001> populateBeneficiariesList(MobilePersonalAccidentInsuredPerson person) {
		List<MBP001> beneficiariesList = new ArrayList<>();
		// if (person.getInsuredPersonBeneficiariesList().size() > 0) {
		for (GInsuredPersonBeneficiaries beneficiaries : person.getInsuredPersonBeneficiariesList()) {
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
					+ beneficiaries.getPhoneNumber() + " )";
			beneficiary.setAddress(residentAddress);
			float percentage = beneficiaries.getPercentage();
			beneficiary.setPercentage(percentage);
			beneficiariesList.add(beneficiary);

		}
		// }else {
		// beneficiariesList= new ArrayList<>();
		// }
		return beneficiariesList;

	}

	public String getUploadPath() {
		return UploadFileConfig.getUploadFilePathHome();
	}

	private boolean checkFindRecords(List<MobilePersonalAccidentProposal> findRecords, MPAP001 mpap001) {
		if (findRecords != null) {
			if (findRecords.get(0).getUserId().equals(mpap001.getUserId())
					&& findRecords.get(0).getOrderId().equals(mpap001.getOrderId())) {
				return true;
			}
		}
		return false;
	}
	// )
}
