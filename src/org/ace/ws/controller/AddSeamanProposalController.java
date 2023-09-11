package org.ace.ws.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import com.google.gson.Gson;

import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.common.Name;
import org.ace.insurance.common.OutboundAgentInfo;
import org.ace.insurance.common.ResidentAddress;
import org.ace.insurance.life.KeyFactorChecker;
import org.ace.insurance.life.dao.entities.InsuredPersonBeneficiaries;
import org.ace.insurance.life.dao.entities.InsuredPersonKeyFactorValue;
import org.ace.insurance.life.dao.entities.LifeProposal;
import org.ace.insurance.life.dto.BeneficiariesInfoDTO;
import org.ace.insurance.life.dto.InsuredPersonInfoDTO;
import org.ace.insurance.life.dto.LifeProposalDTO;
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
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@Resource(name = "AssociationAgentService")
	private  IAssociationAgentService associationAgentService;
	
	@Resource(name = "ProductTypeRecordsService")
	private  IProductTypeRecordsService productTypeRecordsService;

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
		proposalDTO.setSubmittedDate(concatLongDate(proposalDTO.getSubmittedDate()));
		proposalDTO.setStartDate(concatLongDate(proposalDTO.getStartDate()));
		proposalDTO.setEndDate(concatLongDate(proposalDTO.getEndDate()));
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
			personInfoDTO.setDateOfBirth(concatLongDate(personInfoDTO.getDateOfBirth()));
			personInfoDTO.setCdcNo(personInfoDTO.getCdcNo().trim());
			personInfoDTO.setProduct(productService.findProductById(personInfoDTO.getProductId()));
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
				vehKF.setValue(personInfoDTO.getPlans() + "");
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
	
	
	


}
