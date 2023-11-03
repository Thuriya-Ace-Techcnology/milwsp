package org.ace.ws.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import javax.xml.rpc.ServiceException;

import com.google.gson.Gson;

import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.common.Name;
import org.ace.insurance.common.OutboundAgentInfo;
import org.ace.insurance.common.ResidentAddress;
import org.ace.insurance.common.Utils;
import org.ace.insurance.common.plans.services.interfaces.IPlansService;
import org.ace.insurance.life.KeyFactorChecker;
import org.ace.insurance.life.dao.entities.InsuredPersonBeneficiaries;
import org.ace.insurance.life.dao.entities.InsuredPersonKeyFactorValue;
import org.ace.insurance.life.dao.entities.LifeProposal;
import org.ace.insurance.life.dto.BeneficiariesInfoDTO;
import org.ace.insurance.life.dto.InsuredPersonInfoDTO;
import org.ace.insurance.life.dto.LifePolicyDTO;
import org.ace.insurance.life.dto.LifeProposalDTO;
import org.ace.insurance.life.lifePolicy.service.interfaces.ILifePolicyService;
import org.ace.insurance.life.lifeProposal.service.interfaces.ILifeProposalService;
import org.ace.insurance.product.Product;
import org.ace.insurance.product.service.interfaces.IProductService;
import org.ace.insurance.system.agent.service.interfaces.IAgentService;
import org.ace.insurance.system.agent.service.interfaces.IAssociationAgentService;
import org.ace.insurance.system.common.branch.service.interfaces.IBranchService;
import org.ace.insurance.system.common.keyfactor.KeyFactor;
import org.ace.insurance.system.common.paymenttype.service.interfaces.IPaymentTypeService;
import org.ace.insurance.system.common.relationship.service.interfaces.IRelationShipService;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.system.productTypeRecords.service.interfaces.IProductTypeRecordsService;
import org.ace.java.component.StatusType;
import org.ace.java.component.SystemException;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.controller.common.JasperTemplate;
import org.ace.ws.factory.JasperFactory;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
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

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@CrossOrigin
@Controller
public class AddSeamanProposalController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(AddSeamanProposalController.class);

	@Resource(name = "LifeProposalService")
	private ILifeProposalService lifeProposalService;
	
	@Resource(name = "ProductService")
	private IProductService productService;
	
	@Resource(name = "PaymentTypeService")
	private IPaymentTypeService paymentTypeService;
	
	@Resource(name = "BranchService")
	private IBranchService branchService;
	
	@Resource(name = "RelationShipService")
	private IRelationShipService relationShipService;
	
	@Resource(name = "PlansService")
	private IPlansService plansService;
	
	@Resource(name = "AssociationAgentService")
	private  IAssociationAgentService associationAgentService;
	
	@Resource(name = "ProductTypeRecordsService")
	private  IProductTypeRecordsService productTypeRecordsService;
	
	@Resource(name = "LifePolicyService")
	private ILifePolicyService lifePolicyService;

	AceResponse aceResponse = new AceResponse();
	
	private String productName;

	@RequestMapping(value = URIConstants.ADD_SEAMAN_PROPOSAL, method = RequestMethod.POST)
	public @ResponseBody String addSeamanProposal(@RequestHeader String key,
			@RequestParam("proposalDto") String dto) throws IOException, ServiceException {
		AceResponse aceResponse = new AceResponse();
		String response = null;
		Gson gson = new Gson();
		LifeProposalDTO proposalDTO = gson.fromJson(dto, LifeProposalDTO.class);
		List<BeneficiariesInfoDTO> beneficiariesDTOList = null;
		if(proposalDTO.getBuyerPlatForm().equals(BuyerPlatForm.WEBSITE)) {
			proposalDTO.setSubmittedDate(concatLongDate(proposalDTO.getSubmittedDate()));
			proposalDTO.setStartDate(concatLongDate(proposalDTO.getStartDate()));
			proposalDTO.setEndDate(concatLongDate(proposalDTO.getEndDate()));
			
		}
		proposalDTO.setPaymentType(paymentTypeService.findPaymentTypeById(proposalDTO.getPaymentTypeId()));
		proposalDTO.setBranch(branchService.findBranchById(proposalDTO.getBranchId()));	
		proposalDTO.setAuthorizeAssociation(associationAgentService.findById(proposalDTO.getAuthorizeAssociationId()));
		List<InsuredPersonInfoDTO> proposalInsuredPersonDTOList = new ArrayList<InsuredPersonInfoDTO>();	
		ResidentAddress residentAddress = null;
		Name name = null;
		for(InsuredPersonInfoDTO personInfoDTO : proposalDTO.getProposalInsuredPersonDTOList()) {	
			residentAddress = new ResidentAddress();
			name = new Name();
			beneficiariesDTOList =  new ArrayList<BeneficiariesInfoDTO>();
			name.setFirstName(personInfoDTO.getInsuredName());
			personInfoDTO.setName(name);
			residentAddress.setResidentAddress(personInfoDTO.getInsuResidentAddress());
			personInfoDTO.setResidentAddress(residentAddress);
			if(proposalDTO.getBuyerPlatForm().equals(BuyerPlatForm.WEBSITE)) {
				personInfoDTO.setDateOfBirth(concatLongDate(personInfoDTO.getDateOfBirth()));
			}			
			personInfoDTO.setCdcNo(personInfoDTO.getCdcNo().trim());
			personInfoDTO.setProduct(productService.findProductById(personInfoDTO.getProductId()));
			personInfoDTO.setPlans(plansService.findById(personInfoDTO.getPlanId()));
			personInfoDTO.setSumInsured(personInfoDTO.getPlans().getSI());
			productName = personInfoDTO.getProduct().getName();
			setKeyFactorValue(personInfoDTO);		
			for(BeneficiariesInfoDTO beneficiariesInfoDTO : personInfoDTO.getBeneficiariesInfoDTOList()) {	
				name = new Name();
				residentAddress = new ResidentAddress();
				residentAddress.setResidentAddress(beneficiariesInfoDTO.getBeneResidentAddress());
				beneficiariesInfoDTO.setResidentAddress(residentAddress);
				name.setFirstName(beneficiariesInfoDTO.getBeneName());
				beneficiariesInfoDTO.setName(name);				
				beneficiariesInfoDTO.setRelationShip(relationShipService.findById(beneficiariesInfoDTO.getRelationshipId()));
				beneficiariesDTOList.add(beneficiariesInfoDTO);
			}
			personInfoDTO.setBeneficiariesInfoDTOList(beneficiariesDTOList);			
			proposalInsuredPersonDTOList.add(personInfoDTO);
		}
		
		proposalDTO.setProposalInsuredPersonDTO(proposalInsuredPersonDTOList);
		logger.info("Start Insert Mobile Seaman Proposal.");
		try {
			if (key.toString().equals(Constants.getApikey())) {
				if (proposalDTO != null) {					
					if (proposalDTO.getOrderId() == null || proposalDTO.getOrderId().equals("")) {
						aceResponse.setMessage(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
						return responseManager.getResponseString(aceResponse);
					}
					proposalDTO = new LifeProposalDTO(lifeProposalService.addLifeProposal(
									new LifeProposal(proposalDTO)));				
					ProductTypeRecords productTypeRecords = new ProductTypeRecords();
					productTypeRecords.setProductType(productName);
					productTypeRecords.setTwoCtwoPorderId(proposalDTO.getOrderId());
					productTypeRecordsService.insert(productTypeRecords);
					aceResponse.setMessage("Success");
					aceResponse.setStatus(HttpStatus.OK);
					aceResponse.setData(proposalDTO);
					response = responseManager.getResponseString(aceResponse);
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

	
	
	
	private long concatLongDate(long value) {
		return Long.valueOf(String.valueOf(value) + "000");
	}
	
	private void setKeyFactorValue(InsuredPersonInfoDTO personInfoDTO) {
		for (InsuredPersonKeyFactorValue vehKF : personInfoDTO.getKeyFactorValueList()) {
			KeyFactor kf = vehKF.getKeyFactor();
			if (KeyFactorChecker.isPlan(kf)) {
				vehKF.setValue(personInfoDTO.getPlans().getId() + "");
			} else if (KeyFactorChecker.isAge(kf)) {
				vehKF.setValue(personInfoDTO.getAge() + "");
			}
		}
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	/* POST seaman policy list find by CDC NO */
	@RequestMapping(value = URIConstants.GET_SEAMAN_POL_BY_CDCNO, method = RequestMethod.POST)
	public @ResponseBody String getSeamanPolicyByCdcNo(@RequestHeader String key, @RequestParam(name = "cdcNo", required = true) String cdcNo,
			@RequestParam(name = "productId", required = false) String productId)
			throws ServiceException, UnsupportedEncodingException, SystemException {
		logger.info("Start Select Seaman Policy By CDC No.");
		AceResponse aceResponse = new AceResponse();
		List<LifePolicyDTO> seamanPolicyList = lifePolicyService.findSeamanPolicyByCDCNo(cdcNo, productId);
		if (seamanPolicyList != null) {
			aceResponse.setData(seamanPolicyList);
			aceResponse.setMessage("Success");
			aceResponse.setStatus(HttpStatus.OK);
		} else {
			aceResponse.setMessage("Empty Data");
			aceResponse.setStatus(HttpStatus.NOT_FOUND);
		}
		logger.info("End Select Seaman Policy  List by CDC No.");
		return responseManager.getResponseString(aceResponse);
	}
	
	
	/* Check Maximum SumInsured For Seaman */
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value = URIConstants.CHECK_MAX_SI_FOR_SEAMAN, method = RequestMethod.POST)
	public @ResponseBody String checkMaxSIForSeaman(@RequestHeader String key, @RequestParam(name = "cdcNo") String cdcNo,
			@RequestParam(name = "policyStartDate") long policyStartDate,
			@RequestParam(name = "buyerPlatForm") String buyerPlatForm)
			throws ServiceException, UnsupportedEncodingException, SystemException {
		logger.info("Start to check maximum SI for Seaman.");
		AceResponse aceResponse = new AceResponse();
		List<LifePolicyDTO> seamanPolicyList = lifePolicyService.findSeamanPolicyByCDCNo(cdcNo, null);
		List<LifePolicyDTO> policyDTOList = new ArrayList<LifePolicyDTO>();
		Date policyEndDate = null;
		Date startDate = null;
		if(BuyerPlatForm.WEBSITE.equals(buyerPlatForm)) {			
			policyStartDate = concatLongDate(policyStartDate);			
		}	
		
		if(seamanPolicyList != null) {
			for(LifePolicyDTO policyDTO : seamanPolicyList) {
				policyEndDate = new Date(policyDTO.getPolicyEndDate());
				startDate = new Date(policyStartDate);
				if(policyEndDate.after(startDate) || policyEndDate.equals(startDate)) {
					policyDTOList.add(policyDTO);
				}			
			}
		}
		
		double totalSI = 0.0;		
		if(policyDTOList != null) {
			for(LifePolicyDTO policyDTO : policyDTOList) {
				totalSI += policyDTO.getPolicyInsuredPersonDTOList().get(0).getSumInsured();
			}
		}
		aceResponse.setData(100000000 - totalSI);
		aceResponse.setMessage("Success");
		aceResponse.setStatus(HttpStatus.OK);
		logger.info("End to check maximum SI for Seaman");
		return responseManager.getResponseString(aceResponse);
	}
	
	
	/* Print Seaman Policy Certificate and T&C */
	@RequestMapping(value = URIConstants.GET_SEAMAN_POLICY_CERTIFICATE, method = RequestMethod.GET)
	public ResponseEntity<byte[]> printReceipt(HttpServletResponse response, @QueryParam("policyId") String policyId)
			throws IOException, JRException {
		logger.info("Start Print Receipt function");
		byte[] bb = null;
		LifePolicyDTO policyDTO = lifePolicyService.findByPolicyId(policyId);			
		if (policyDTO.getPolicyNo() != null) {
			logger.info("Receipt object is not null");
			Map<String, Object> policyParam = new HashMap<>();
			ArrayList<LifePolicyDTO> policy = new ArrayList<>();
			policy.add(policyDTO);
			List<BeneficiariesInfoDTO> beneifitPersonDTOList = policyDTO.getPolicyInsuredPersonDTOList().get(0).getBeneficiariesInfoDTOList();
				int size = beneifitPersonDTOList.size();
				policyParam.put("milogo", "document-template/images/MI-logo.png");
				policyParam.put("policyNo", policyDTO.getPolicyNo());
				policyParam.put("startDate", Utils.formattedDate(new Date(policyDTO.getPolicyStartDate())));
				policyParam.put("endDate", Utils.formattedDate(new Date(policyDTO.getPolicyEndDate())));
				policyParam.put("paymentType", policyDTO.getPaymentType());
				policyParam.put("periodOfYear", policyDTO.getPeriodOfYear());
				policyParam.put("size", size);

				/* InsuredPerson Information */
				policyParam.put("premium",
						Utils.getCurrencyFormatString(policyDTO.getPolicyInsuredPersonDTOList().get(0).getSeamanPremium()));
				policyParam.put("sumInsured",
						Utils.getCurrencyFormatString(policyDTO.getPolicyInsuredPersonDTOList().get(0).getSumInsured()));
				policyParam.put("insuredName", policyDTO.getPolicyInsuredPersonDTOList().get(0).getInsuredName());
				policyParam.put("passportNo", policyDTO.getPolicyInsuredPersonDTOList().get(0).getPassportNo());
				policyParam.put("dateOfBirth", Utils.formattedDate(new Date(policyDTO.getPolicyInsuredPersonDTOList().get(0).getDateOfBirth())));
				policyParam.put("age", policyDTO.getPolicyInsuredPersonDTOList().get(0).getAge());
				policyParam.put("insuredAddress", policyDTO.getPolicyInsuredPersonDTOList().get(0).getInsuResidentAddress());
				policyParam.put("cdcNo", policyDTO.getPolicyInsuredPersonDTOList().get(0).getCdcNo());
				policyParam.put("planType", policyDTO.getPolicyInsuredPersonDTOList().get(0).getPlanType());
				policyParam.put("insuredId", policyDTO.getPolicyInsuredPersonDTOList().get(0).getFullIdNo());
				policyParam.put("cdcNo", policyDTO.getPolicyInsuredPersonDTOList().get(0).getCdcNo());
				policyParam.put("oceanlinerName", policyDTO.getPolicyInsuredPersonDTOList().get(0).getOceanlinerName());
				policyParam.put("vesselName", policyDTO.getPolicyInsuredPersonDTOList().get(0).getVesselName());
				policyParam.put("position", policyDTO.getPolicyInsuredPersonDTOList().get(0).getPosition());
				policyParam.put("insuredNrcNo", policyDTO.getPolicyInsuredPersonDTOList().get(0).getFullIdNo());

				List<BeneficiariesInfoDTO> benefitPersonList = policyDTO.getPolicyInsuredPersonDTOList().get(0).getBeneficiariesInfoDTOList();
				policyParam.put("listDataSource", new JRBeanCollectionDataSource(benefitPersonList));
				
				List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
				JasperPrint polJasper = JasperFactory.generateJasperPrint(policyParam, JasperTemplate.SEAMAN_ONLINE_POLICY_CERTIFICATE, new JREmptyDataSource());
				jasperPrintList.add(polJasper);
				
				if(size >1) {
					JasperPrint polAttJasper = JasperFactory.generateJasperPrint(policyParam, JasperTemplate.SEAMAN_ONLINE_POLICY_BENE_ATTACH_CERTIFICATE, new JREmptyDataSource());
					jasperPrintList.add(polAttJasper);
				}
				
				JasperPrint termsAndConJasper = JasperFactory.generateJasperPrint(policyParam, JasperTemplate.SEAMEN_ONLINE_TAndC, new JREmptyDataSource());
				jasperPrintList.add(termsAndConJasper);

				bb = generateReport(jasperPrintList);
				response.setContentType(MediaType.APPLICATION_PDF_VALUE);
				response.setHeader("Content-Disposition", "inline; filename=myanma_insurnace.pdf");
				logger.info("printRecipt Function End");
			} 
			ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bb, HttpStatus.OK);
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

	/* GET  Net Premium */
	@RequestMapping(value = URIConstants.GET_NET_PREMIUM, method = RequestMethod.POST)
	public @ResponseBody String getNetPremium(@RequestHeader String key,@RequestParam(name = "productId") String productId,@RequestParam("premium") double premium)
			throws ServiceException, UnsupportedEncodingException, SystemException {
		logger.info("Start get NetPremium.");
		AceResponse aceResponse = new AceResponse();
		double netPremium = lifeProposalService.getNetPremium(premium,productId);
		if (netPremium != 0.0) {
			aceResponse.setData(netPremium);
			aceResponse.setMessage("Success");
			aceResponse.setStatus(HttpStatus.OK);
		} else {
			aceResponse.setMessage("Empty Data");
			aceResponse.setStatus(HttpStatus.NOT_FOUND);
		}
		logger.info("End retriving Net Premium.");
		return responseManager.getResponseString(aceResponse);
	}
	
	
	


}
