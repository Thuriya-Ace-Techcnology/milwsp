package org.ace.insurance.travel.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.ace.insurance.common.InsuranceType;
import org.ace.insurance.common.PaymentGateway;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.SystemConstants;
import org.ace.insurance.product.Product;
import org.ace.insurance.product.persistence.interfaces.IProductDAO;
import org.ace.insurance.system.common.paymenttype.persistence.interfaces.IPaymentTypeDAO;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.system.productTypeRecords.dto.ProductTypeRecordsDTO;
import org.ace.insurance.system.productTypeRecords.service.interfaces.IProductTypeRecordsService;
import org.ace.insurance.travel.MobileTravelInsuredPerson;
import org.ace.insurance.travel.MobileTravelProposal;
import org.ace.insurance.travel.persistence.interfaces.IMobileTravelProposalDAO;
import org.ace.insurance.travel.service.interfaces.IMobileTravelProposalService;
import org.ace.java.component.SystemException;
import org.ace.java.component.idgen.exception.CustomIDGeneratorException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.factory.MobileTravelProposalFactory;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirm;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirmDTO;
import org.ace.ws.model.mobiletravelproposal.MIP001;
import org.ace.ws.model.mobiletravelproposal.MTP001;
import org.ace.ws.model.transaction.PaymentResponse;
import org.ace.ws.model.transaction.TwoC2P001;
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

@Service(value = "MobileTravelProposalService")
public class MobileTravelProposalService extends BaseService implements IMobileTravelProposalService {

	@Resource(name = "ID_CONFIG")
	private Properties properties;

	@Resource(name = "MobileTravelProposalDAO")
	private IMobileTravelProposalDAO mobileTravelProposalDAO;

	@Resource(name = "ProductDAO")
	private IProductDAO productDAO;

	@Resource(name = "PaymentTypeDAO")
	private IPaymentTypeDAO paymentTypeDAO;

	@Resource(name = "ProductTypeRecordsService")
	private IProductTypeRecordsService productTypeRecordsService;
	
	private static final Logger logger = Logger.getLogger(MobileTravelProposalService.class);
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobileTravelProposal addNewTravelProposal(MobileTravelProposal mobileTravelProposal) {
		try {
			logger.info("Start AddNewTravelProposal");
			Product product = productDAO.findById(mobileTravelProposal.getProductId());
			logger.info("Product Result : "+product.getInsuranceType());
			String proposalNo = null;
			String tranId = null;
			if (InsuranceType.TRAVEL_INSURANCE.equals(product.getInsuranceType())) {
				logger.info("Start Travel Insurance ");
				proposalNo = customIDGenerator.getNextId(SystemConstants.M_TRAVEL_PROPOSAL_NO, product.getProductGroup().getProposalNoPrefix());
				logger.info("Travel Insurance Proposal NO:"+proposalNo);
			} else if (InsuranceType.PERSON_TRAVEL.equals(product.getInsuranceType())) {
				proposalNo = customIDGenerator.getNextId(SystemConstants.M_PERSON_TRAVEL_PROPOSAL_NO, product.getProductGroup().getProposalNoPrefix());
			}
			System.out.println("ProposalNo : "+proposalNo);
			tranId = customIDGenerator.getNextTransactionId(SystemConstants.M_TRANSACTION_NO);
			System.out.println("TranId : "+tranId);
			logger.info("Transaction Id NO : "+tranId);
			mobileTravelProposal.setProposalNo(proposalNo);
			mobileTravelProposal.setTransactionId(tranId);
			mobileTravelProposal.setProposalStatus(ProposalStatus.PENDING);
			mobileTravelProposal.setSubmittedDate(new Date());
			mobileTravelProposal.setPaymentType(paymentTypeDAO.findById(customIDGenerator.getPropertyValue(SystemConstants.LUMPSUM)));
			setIDPrefixForInsert(mobileTravelProposal);
			mobileTravelProposalDAO.insert(mobileTravelProposal);
			return mobileTravelProposal;
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a new MobileTravelProposal", e);
		} catch (CustomIDGeneratorException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a new MobileTravelProposal", e);
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobileTravelProposal addCoreNewTravelProposal(MobileTravelProposal mobileTravelProposal) {
		try {
			logger.info("Start AddCoreNewTravelProposal");
			String tranId = null;
			tranId = customIDGenerator.getNextTransactionId(SystemConstants.M_TRANSACTION_NO);
			logger.info("Transaction Id NO : "+tranId);
			mobileTravelProposal.setTransactionId(tranId);
			mobileTravelProposal.setPaymentType(paymentTypeDAO.findById(customIDGenerator.getPropertyValue(SystemConstants.LUMPSUM)));
			setIDPrefixForInsert(mobileTravelProposal);
			mobileTravelProposalDAO.insert(mobileTravelProposal);
			return mobileTravelProposal;
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a new MobileTravelProposal", e);
		} catch (CustomIDGeneratorException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a new MobileTravelProposal", e);
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobileTravelProposal updateNewTravleProposal(MobileTravelProposal mobileTravelProposal) {
		try {
			mobileTravelProposalDAO.update(mobileTravelProposal);
			return mobileTravelProposal;
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update a new MobileTravelProposal", e);
		} catch (CustomIDGeneratorException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update a new MobileTravelProposal", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MobileTravelProposal> findByMobileUserWithRowCount(String mobileUserId, Integer rowCount) {
		List<MobileTravelProposal> result = new ArrayList<MobileTravelProposal>();
		try {
			List<MobileTravelProposal> resultFalseList = mobileTravelProposalDAO.findByMobileUserWithRowCount(mobileUserId, rowCount);
			for (MobileTravelProposal resultFalse : resultFalseList) {
				if (!resultFalse.getSubmittedDate().equals(new Date()) && resultFalse.getProposalStatus() == null) {
					mobileTravelProposalDAO.updateDeleteStatusByPaymentStatus(resultFalse.getOrderId());
				} else {
					result.add(resultFalse);
				}
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a MobileTravelProposal by mobileUser (ID : " + mobileUserId + ")", e);
		}
		return result;
	}

	private void setIDPrefixForInsert(MobileTravelProposal travelProposal) {
		String tProposalPrefix = customIDGenerator.getPrefix(MobileTravelProposal.class);
		String ePrefix = customIDGenerator.getPrefix(MobileTravelInsuredPerson.class);
		travelProposal.setPrefix(tProposalPrefix);
		for (MobileTravelInsuredPerson e : travelProposal.getInsuredPersonList()) {
			e.setPrefix(ePrefix);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateTravelProposal(TwoC2P001 TwoC2P001) {
		PaymentResponse response = TwoC2P001.getPaymentResponse();
		try {
			MobileTravelProposal mTravelProposal = mobileTravelProposalDAO.findMobileTravelProposalByTransactionCode(response.getUniqueTransactionCode());
			String successResponseCodes = properties.getProperty(Constants.SUCCESS_RESPONSE_CODE);
			List<String> successResponseCodeList = Arrays.asList(successResponseCodes.split("\\s*,\\s*"));
			if (ProposalStatus.PENDING.equals(mTravelProposal.getProposalStatus()) && successResponseCodeList.contains(response.getRespCode())) {
				Product product = productDAO.findById(mTravelProposal.getProductId());
				String policyNo = customIDGenerator.getNextId(SystemConstants.M_TRAVEL_POLICY_NO, product.getProductGroup().getProposalNoPrefix());
				mTravelProposal.setPolicyNo(policyNo);
				mTravelProposal.setPaymentDate(new Date());
				mTravelProposal.setProposalStatus(ProposalStatus.ISSUED);
			}
			mTravelProposal.setPaymentGateway(PaymentGateway.TWOC2P);
			mTravelProposal.setTwoC2PResponse(response);
			mobileTravelProposalDAO.update(mTravelProposal);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update a MobileTravelProposal by TransactionID (ID : " + response.getUniqueTransactionCode() + ")", e);
		}
	}

	@Override
	public List<MobileTravelProposal> findByOrderId(String orderId) {
		List<MobileTravelProposal> result = null;
		try {
			result = mobileTravelProposalDAO.findByOrderId(orderId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a MobileTravelProposal by mobileUser (ORderID : " + orderId + ")", e);
		}
		return result;
	}

	@Override
	public MobileTravelProposal updateByPaymentStatus(String orderId) {
		MobileTravelProposal result = null;
		try {
			result = mobileTravelProposalDAO.updateByPaymentStatus(orderId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a MobileTravelProposal ", e);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobileTravelProposal findMobileTravelProposalByTransactionCode(String transactionId) {
		MobileTravelProposal result = null;
		try {
			result = mobileTravelProposalDAO.findMobileTravelProposalByTransactionCode(transactionId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a MobileTravelProposal by mobileUser (transactionId : " + transactionId + ")", e);
		}
		return result;
	}

	@Override
	public void updatePremuimAmount(MobileTravelProposal mobileTravelProposal, List<MIP001> insuredPersonList) {
		for (MIP001 mip : insuredPersonList) {
			for(MobileTravelInsuredPerson mtip : mobileTravelProposal.getInsuredPersonList()) {
				if(mtip.getId().equals(mip.getId())){
					mtip.setPremium(mip.getPremium());
					mtip.setUnit(mip.getUnit());
				}
			}
		}
	}

	@Override
	public List<MTP001> findByFromToDate(String fromDate, String toDate,String convert) {
		List<MTP001> recordsDTOList = null;
		try {
			recordsDTOList = MobileTravelProposalFactory.convertMobileTravelProposalDTOList(mobileTravelProposalDAO.findByFromToDate(fromDate,toDate,convert));
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a MobileTravelProposal by Date (fromDate : " + fromDate + ")"+" and (toDate :"+toDate+")", e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return recordsDTOList;
	}

	@Override
	public void calltoOthersServerAPI(MTP001 mtp001, ProductTypeRecords productTypeRecords) throws ClientProtocolException, IOException{
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(URIConstants.CALLTORESTCALLERIPADDRESS+URIConstants.TPL_TVL_CLOUD_TO_CORE);
	    HashMap<String, MTP001> map = new HashMap<String, MTP001>();
	    ProductTypeRecordsDTO recordsDTO = new ProductTypeRecordsDTO(productTypeRecords);
	    mtp001.setRecordsDTO(recordsDTO);
	    map.put("mtp001", mtp001);
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
	    }else if(response.getCode() == 500) {
	    	status = ResponseStatus.FAIL;
	    }
	    this.updateResponseStatusByOrderId(mtp001.getOrderId(), status);
	}

	@Override
	public MobileTravelProposal updateResponseStatusByOrderId(String orderId, ResponseStatus responseStatus) {
		return mobileTravelProposalDAO.updateResponseStatusByOrderId(orderId, responseStatus);
	}

	@Override
	public List<MobileTravelProposal> findRecordsByResponseStatus(){
		List<MobileTravelProposal> resultList= null;
		 resultList = mobileTravelProposalDAO.findRecordsByResponseStatus();
		return resultList;
	}

	@Override
	public void postDataSyncProcess(String order_id) throws UnsupportedEncodingException {
		MTP001 mtp001 = MobileTravelProposalFactory.convertMobileTravelProposalDTO(findByOrderId(order_id).get(0));
		ProductTypeRecords productType = productTypeRecordsService.findByOrderId(order_id);
		try {
			calltoOthersServerAPI(mtp001, productType);
		}catch(ConnectException conn) {
			updateResponseStatusByOrderId(order_id, ResponseStatus.REQUEST_NOT_FOUND);
			logger.info("Can't connect to API Server!!! Connection Problem!");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCovertedStatusByOderId(PaymentOrderConfirmDTO paymentOrderConfirm) {
		for(PaymentOrderConfirm orderId : paymentOrderConfirm.getPaymentOrder()) {
			if(orderId != null) {
				mobileTravelProposalDAO.updateConvertedStatusByOrderId(orderId);
			}
		}
	}
	

}
