package org.ace.insurance.medical.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.SystemConstants;
import org.ace.insurance.medical.MobileMedicalProposal;
import org.ace.insurance.medical.MobileMedicalProposalInsuredPerson;
import org.ace.insurance.medical.MobileMedicalProposalInsuredPersonAddOn;
import org.ace.insurance.medical.persistence.interfaces.IMobileMedicalProposalDAO;
import org.ace.insurance.medical.service.interfaces.IMobileMedicalProposalService;
import org.ace.insurance.product.Product;
import org.ace.insurance.product.persistence.interfaces.IProductDAO;
import org.ace.insurance.system.common.paymenttype.persistence.interfaces.IPaymentTypeDAO;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.system.productTypeRecords.dto.ProductTypeRecordsDTO;
import org.ace.insurance.system.productTypeRecords.service.interfaces.IProductTypeRecordsService;
import org.ace.java.component.SystemException;
import org.ace.java.component.idgen.exception.CustomIDGeneratorException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.factory.MobileMedicalProposalFactory;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirm;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirmDTO;
import org.ace.ws.model.mobileMedicalproposal.InsuredPersonDTO;
import org.ace.ws.model.mobileMedicalproposal.MedicalAddOnDTO;
import org.ace.ws.model.mobileMedicalproposal.MedicalProposalDTO;
import org.ace.ws.model.mobileMedicalproposal.MedicalProposalInsuredPersonDTO;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

@Service(value = "MobileMedicalProposalService")
public class MobileMedicalProposalService extends BaseService implements IMobileMedicalProposalService {

	@Resource(name = "ID_CONFIG")
	private Properties properties;

	@Resource(name = "MobileMedicalProposalDAO")
	private IMobileMedicalProposalDAO mobileMedicalProposalDAO;

	@Resource(name = "ProductDAO")
	private IProductDAO productDAO;

	@Resource(name = "PaymentTypeDAO")
	private IPaymentTypeDAO paymentTypeDAO;
	
	@Resource(name = "ProductTypeRecordsService")
	private IProductTypeRecordsService productTypeRecordsService;
	

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobileMedicalProposal addNewMedicalProposal(MobileMedicalProposal mobileMedicalProposal) {
		try {

			Product product = productDAO.findById(mobileMedicalProposal.getMedicalProposalInsuredPersonList().get(0).getProductId());
			String proposalNo = null;
			String tranId = null;
			proposalNo = customIDGenerator.getNextId(SystemConstants.M_MEDICAL_PROPOSAL_NO, product.getProductGroup().getProposalNoPrefix());
			tranId = customIDGenerator.getNextTransactionId(SystemConstants.M_TRANSACTION_NO);
			mobileMedicalProposal.setProposalNo(proposalNo);
			mobileMedicalProposal.setTransactionId(tranId);
			mobileMedicalProposal.setProposalStatus(ProposalStatus.PENDING);
			mobileMedicalProposal.setDeleteStatus(false);
			mobileMedicalProposal.setSubmittedDate(new Date());
			mobileMedicalProposalDAO.insert(mobileMedicalProposal);
			return mobileMedicalProposal;
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a new MobilePersonalAccidentProposal", e);
		} catch (CustomIDGeneratorException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a new MobileMedicalProposal", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<MobileMedicalProposal> findByOrderId(String orderId) {
		List<MobileMedicalProposal> result = null;
		try {
			result = mobileMedicalProposalDAO.findByOrderId(orderId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a MobileMedicalProposal by mobileUser (ORderID : " + orderId + ")", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updatePremuimAmount(MobileMedicalProposal mobileMedicalProposal, List<MedicalProposalInsuredPersonDTO> insuredPersonList) {
		for (MedicalProposalInsuredPersonDTO mip : insuredPersonList) {
			for (MobileMedicalProposalInsuredPerson mtip : mobileMedicalProposal.getMedicalProposalInsuredPersonList()) {
				if (mtip.getId().equals(mip.getId())) {
					mtip.setBasicPremium(mip.getBasicPremium());
					mtip.setUnit(mip.getUnit());
					mtip.setAddOnPremium(mip.getAddOnPremium());
					for(MobileMedicalProposalInsuredPersonAddOn addOn: mtip.getInsuredPersonAddOnList()) {
						for(MedicalAddOnDTO addOnDTO : mip.getInsuredPersonAddOnList()) {
							if(addOn.getId().equals(addOnDTO.getId())) {
								addOn.setPremium(addOnDTO.getPremium());
								addOn.setUnit(addOnDTO.getUnit());
							}
						}
					}
				}
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public MobileMedicalProposal updateNewMedicalProposal(MobileMedicalProposal mobileMedicalProposal) {
		try {
			mobileMedicalProposalDAO.update(mobileMedicalProposal);
			return mobileMedicalProposal;
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update a new MobileMedicalProposal", e);
		} catch (CustomIDGeneratorException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update a new MobileMedicalProposal", e);
		}

	}

	@SuppressWarnings("deprecation")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<InsuredPersonDTO> findByMobileUser(String mobileUserId) {
		List<InsuredPersonDTO> result = new ArrayList<InsuredPersonDTO>();
		try {
			List<InsuredPersonDTO> resultFalseList = mobileMedicalProposalDAO.findByMobileUser(mobileUserId);
			for (InsuredPersonDTO resultFalse : resultFalseList) {
				if (!resultFalse.getSubmittedDate().equals(new Date()) && resultFalse.getProposalStatus() == null) {
					mobileMedicalProposalDAO.updateDeleteStuatusByPaymentStatus(resultFalse.getOrderId());
				} else {
					resultFalse.getSubmittedDate().setDate(resultFalse.getSubmittedDate().getDate());
					result.add(resultFalse);
				}
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a MobilePersonalAccidentProposal by mobileUser (ID : " + mobileUserId + ")", e);
		}
		return result;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public MobileMedicalProposal findProposalByRefNo(String refNo) {
		MobileMedicalProposal result = new MobileMedicalProposal();
		try {
			MobileMedicalProposal resultFalse = mobileMedicalProposalDAO.findHealthProposalByRefNo(refNo);
			if (resultFalse != null && !resultFalse.getSubmittedDate().equals(new Date()) && resultFalse.getProposalStatus() == null) {
				mobileMedicalProposalDAO.updateDeleteStuatusByPaymentStatus(resultFalse.getOrderId());
			} else {
				return resultFalse;
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a MobileHealthProposal by RefNo", e);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobileMedicalProposal updateByPaymentStatus(String orderId) {
		MobileMedicalProposal result = null;
		try {
			result = mobileMedicalProposalDAO.updateByPaymentStatus(orderId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a mobilePersonalAccidentProposalDAO ", e);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void calltoOthersServerAPIForMP(MedicalProposalDTO medicalProposalDTO, ProductTypeRecords productTypeRecords)
			throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(URIConstants.CALLTORESTCALLERIPADDRESS+URIConstants.TPL_MD_CLOUD_TO_CORE);
	    HashMap<String, MedicalProposalDTO> map = new HashMap<String, MedicalProposalDTO>();
	    ProductTypeRecordsDTO recordsDTO = new ProductTypeRecordsDTO(productTypeRecords);
	    medicalProposalDTO.setRecordsDTO(recordsDTO);
	    map.put("medicalProposalDTO", medicalProposalDTO);
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
	    if(response.getCode() == 200) {
	    	status = ResponseStatus.SUCCESS;
	    }else{
	    	status = ResponseStatus.FAIL;
	    }
	    this.updateResponseStatusByOrderId(medicalProposalDTO.getOrderId(), status);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateResponseStatusByOrderId(String orderId, ResponseStatus status) {
		mobileMedicalProposalDAO.updateResponseStatusByOrderId(orderId, status);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MobileMedicalProposal> findRecordsByResponseStatus() {
		return mobileMedicalProposalDAO.findRecordsByResponseStatus();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void postDataSyncProcess(String order_id) throws UnsupportedEncodingException {
		MedicalProposalDTO MP001 = MobileMedicalProposalFactory.convertMobileMedicalProposalDTO(findByOrderId(order_id).get(0));
		ProductTypeRecords productType = productTypeRecordsService.findByOrderId(order_id);
		try {
			calltoOthersServerAPIForMP(MP001, productType);
		} catch (IOException e) {	
			updateResponseStatusByOrderId(order_id, ResponseStatus.REQUEST_NOT_FOUND);
			e.printStackTrace();
		}		
	}
	@Override
	public List<MedicalProposalDTO> fndByFromToDate(String fromDate, String toDate,String convert) {
		List<MedicalProposalDTO> result = null;
		try {
			result = MobileMedicalProposalFactory.convertMobileMedicalProposalDTOList(mobileMedicalProposalDAO.findByFromToDate(fromDate,toDate,convert));
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a mobileMedicalProposalDAO by mobileUser (fromDate : " + fromDate + ")"+" and (toDate : "+toDate+" ) ", e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void updateCovertedStatusByOderId(PaymentOrderConfirmDTO paymentOrderConfirm) {
		for(PaymentOrderConfirm orderId : paymentOrderConfirm.getPaymentOrder()) {
			if(orderId != null) {
				mobileMedicalProposalDAO.updateConvertedStatusByOrderId(orderId);
			}
		}
	}
}
