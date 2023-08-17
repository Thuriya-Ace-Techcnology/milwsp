package org.ace.insurance.personalAccident.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.SystemConstants;
import org.ace.insurance.personalAccident.GInsuredPersonAddon;
import org.ace.insurance.personalAccident.MobilePersonalAccidentInsuredPerson;
import org.ace.insurance.personalAccident.MobilePersonalAccidentProposal;
import org.ace.insurance.personalAccident.persistence.interfaces.IMobilePersonalAccidentProposalDAO;
import org.ace.insurance.personalAccident.service.interfaces.IMobilePersonalAccidentProposalService;
import org.ace.insurance.product.Product;
import org.ace.insurance.product.persistence.interfaces.IProductDAO;
import org.ace.insurance.system.common.paymenttype.persistence.interfaces.IPaymentTypeDAO;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.system.productTypeRecords.service.interfaces.IProductTypeRecordsService;
import org.ace.java.component.SystemException;
import org.ace.java.component.idgen.exception.CustomIDGeneratorException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.factory.MobilePersonalAccidentProposalFactory;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirm;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirmDTO;
import org.ace.ws.model.mobilePersonalAccidentproposal.MIPA001;
import org.ace.ws.model.mobilePersonalAccidentproposal.MPAP001;
import org.ace.ws.model.mobilePersonalAccidentproposal.PAIP001;
import org.ace.ws.model.mobilePersonalAccidentproposal.PAInsuredPerson001;
import org.ace.ws.model.transaction.PaymentResponse;
import org.ace.ws.model.transaction.TwoC2P001;
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

@Service(value = "MobilePersonalAccidentProposalService")
public class MobilePersonalAccidentProposalService extends BaseService implements IMobilePersonalAccidentProposalService {

	@Resource(name = "ID_CONFIG")
	private Properties properties;

	@Resource(name = "MobilePersonalAccidentProposalDAO")
	private IMobilePersonalAccidentProposalDAO mobilePersonalAccidentProposalDAO;

	@Resource(name = "ProductDAO")
	private IProductDAO productDAO;

	@Resource(name = "PaymentTypeDAO")
	private IPaymentTypeDAO paymentTypeDAO;

	@Resource(name = "ProductTypeRecordsService")
	private IProductTypeRecordsService productTypeRecordsService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobilePersonalAccidentProposal addNewPAProposal(MobilePersonalAccidentProposal mobilePAProposal) {
		try {
			// SimpleDateFormat formattedDate = new
			// SimpleDateFormat("yyyy/MM/dd");
			// Calendar c = Calendar.getInstance();
			// c.add(Calendar.DAY_OF_MONTH, 1); // number of days to add
			// String t = (String) (formattedDate.format(c.getTime()));
			// Date date1 = null;
			// try {
			// date1 = new SimpleDateFormat("yyyy/MM/dd").parse(t);
			// } catch (ParseException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			Product product = productDAO.findById(mobilePAProposal.getInsuredPersonList().get(0).getProductId());
			String proposalNo = null;
			String tranId = null;
			proposalNo = customIDGenerator.getNextId(SystemConstants.M_PA_PROPOSAL_NO, product.getProductGroup().getProposalNoPrefix());
			System.out.println("SA : proposalNo "+proposalNo);
			tranId = customIDGenerator.getNextTransactionId(SystemConstants.M_TRANSACTION_NO);
			System.out.println("SA : Transaction No  "+proposalNo);
			mobilePAProposal.setProposalNo(proposalNo);
			mobilePAProposal.setTransactionId(tranId);
			mobilePAProposal.setProposalStatus(ProposalStatus.PENDING);
			mobilePAProposal.setDeleteStatus(false);
			mobilePAProposal.setSubmittedDate(new Date());
			setIDPrefixForInsert(mobilePAProposal);
			System.out.println("Before Insert");
			mobilePersonalAccidentProposalDAO.insert(mobilePAProposal);
			System.out.println("After Insert");
			return mobilePAProposal;
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a new MobilePersonalAccidentProposal", e);
		} catch (CustomIDGeneratorException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a new MobilePersonalAccidentProposal", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobilePersonalAccidentProposal updateNewPAProposal(MobilePersonalAccidentProposal mobilePAProposal) {
		try {
			mobilePersonalAccidentProposalDAO.update(mobilePAProposal);
			return mobilePAProposal;
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update a new MobilePersonalAccidentProposal", e);
		} catch (CustomIDGeneratorException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update a new MobilePersonalAccidentProposal", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public MobilePersonalAccidentProposal findProposalByRefNo(String refNo) {
		MobilePersonalAccidentProposal result = null;
		try {
			MobilePersonalAccidentProposal resultFalse = mobilePersonalAccidentProposalDAO.findPAProposalByRefNo(refNo);

			if (!resultFalse.getSubmittedDate().equals(new Date()) && resultFalse.getProposalStatus() == null) {
				mobilePersonalAccidentProposalDAO.updateDeleteStuatusByPaymentStatus(resultFalse.getOrderId());
			} else {
				return resultFalse;
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a MobilePersonalAccidentProposal by RefNo", e);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<PAInsuredPerson001> findByMobileUserWithRowCount(String mobileUserId, Integer rowCount) {
		List<PAInsuredPerson001> result = new ArrayList<PAInsuredPerson001>();
		try {
			List<PAInsuredPerson001> resultFalseList = mobilePersonalAccidentProposalDAO.findByMobileUserWithRowCount(mobileUserId, rowCount);
			for (PAInsuredPerson001 resultFalse : resultFalseList) {
				if (!resultFalse.getSubmittedDate().equals(new Date()) && resultFalse.getProposalStatus() == null) {
					mobilePersonalAccidentProposalDAO.updateDeleteStuatusByPaymentStatus(resultFalse.getOrderId());
				} else {
					result.add(resultFalse);
				}
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a MobilePersonalAccidentProposal by mobileUser (ID : " + mobileUserId + ")", e);
		}
		return result;
	}

	private void setIDPrefixForInsert(MobilePersonalAccidentProposal paproposal) {
		String tProposalPrefix = customIDGenerator.getPrefix(MobilePersonalAccidentProposal.class);
		String ePrefix = customIDGenerator.getPrefix(MobilePersonalAccidentProposal.class);
		paproposal.setPrefix(tProposalPrefix);
		for (MobilePersonalAccidentInsuredPerson e : paproposal.getInsuredPersonList()) {
			e.setPrefix(ePrefix);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updatePAProposal(TwoC2P001 TwoC2P001) {
		PaymentResponse response = TwoC2P001.getPaymentResponse();
		try {
			MobilePersonalAccidentProposal mPAProposal = mobilePersonalAccidentProposalDAO.findMobilePAProposalByTransactionCode(response.getUniqueTransactionCode());
			String successResponseCodes = properties.getProperty(Constants.SUCCESS_RESPONSE_CODE);
			String paymentExpiredCodes = properties.getProperty(Constants.PAYMENT_EXPIRED_CODE);
			String paymentRejectCodes = properties.getProperty(Constants.PAYMENT_REJECT_CODE);
			List<String> successResponseCodeList = Arrays.asList(successResponseCodes.split("\\s*,\\s*"));
			List<String> paymentExpiredCodeList = Arrays.asList(paymentExpiredCodes.split("\\s*,\\s*"));
			List<String> paymentRejectCodeList = Arrays.asList(paymentRejectCodes.split("\\s*,\\s*"));
			/** Payment Expired */
			if (paymentExpiredCodeList.contains(response.getRespCode())) {
				mPAProposal.setProposalStatus(ProposalStatus.PAYMENT_EXPIRED);
			}
			/** Paid Success */
			else if (successResponseCodeList.contains(response.getRespCode())) {
				Product product = productDAO.findById(mPAProposal.getInsuredPersonList().get(0).getProductId());
				String policyNo = customIDGenerator.getNextId(SystemConstants.M_PA_POLICY_NO, product.getProductGroup().getProposalNoPrefix());
				mPAProposal.setPolicyNo(policyNo);
				mPAProposal.setPaymentDate(new Date());
				mPAProposal.setProposalStatus(ProposalStatus.ISSUED);
			}
			/** Payment Reject */
			else if (paymentRejectCodeList.contains(response.getRespCode())) {
				mPAProposal.setProposalStatus(ProposalStatus.PAYMENT_REJECT);

			}
			/** Payment Pending */
			else {
				mPAProposal.setProposalStatus(ProposalStatus.PAYMENT_PENDING);
			}

			// mPAProposal.setPaymentGateway(PaymentGateway.TWOC2P);
			// mPAProposal.setTwoC2PResponse(response);
			mobilePersonalAccidentProposalDAO.update(mPAProposal);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to update a MobilePAProposal by TransactionID (ID : " + response.getUniqueTransactionCode() + ")", e);
		}
	}

	@Override
	public List<MobilePersonalAccidentProposal> findByOrderId(String orderId) {
		List<MobilePersonalAccidentProposal> result = null;
		try {
			result = mobilePersonalAccidentProposalDAO.findByOrderId(orderId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a MobilePersonalAccidentProposal by mobileUser (ORderID : " + orderId + ")", e);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobilePersonalAccidentProposal updateByPaymentStatus(String orderId) {
		MobilePersonalAccidentProposal result = null;
		try {
			result = mobilePersonalAccidentProposalDAO.updateByPaymentStatus(orderId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a mobilePersonalAccidentProposalDAO ", e);
		}
		return result;
	}

	@Override
	public void updatePremuimAmount(MobilePersonalAccidentProposal mobilePersonalAccidentProposal, List<PAIP001> insuredPersonList) {
		for (PAIP001 pai : insuredPersonList) {
			for(MobilePersonalAccidentInsuredPerson mpaip : mobilePersonalAccidentProposal.getInsuredPersonList()) {
				if(mpaip.getId().equals(pai.getId())) {
					mpaip.setPremium(pai.getPremium() != 0 ? pai.getPremium() : mpaip.getPremium());
					mpaip.setSumInsured(pai.getSumInsured()!= 0 ? pai.getSumInsured() : mpaip.getSumInsured());
					for(GInsuredPersonAddon iPA :  mpaip.getInsuredPersonAddOnList()) {
						for(MIPA001 mp001: pai.getInsuredPersonAddOnList()) {
							if(iPA.getId().equals(mp001.getId())) {
								iPA.setPremium(mp001.getPremium() != 0 ? mp001.getPremium() : iPA.getPremium() );
								iPA.setSumInsured(mp001.getSumInsured() != 0 ? mp001.getSumInsured() : iPA.getSumInsured());
							}
						}
					}
				}
			}
		}
	}

	@Override
	public List<MPAP001> fndByFromToDate(String fromDate, String toDate,String convert) {
		List<MPAP001> result = null;
		try {
			result = MobilePersonalAccidentProposalFactory.convertMobilePAProposalDTOList(mobilePersonalAccidentProposalDAO.findByFromToDate(fromDate,toDate,convert));
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a MobilePersonalAccidentProposal by mobileUser (fromDate : " + fromDate + ")"+" and (toDate : "+toDate+" ) ", e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void calltoOthersServerAPI(MPAP001 mpap001, ProductTypeRecords productTypeRecords) throws ClientProtocolException, IOException{
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(URIConstants.CALLTORESTCALLERIPADDRESS+URIConstants.TPL_PA_CLOUD_TO_CORE);
	    HashMap<String, MPAP001> map = new HashMap<String, MPAP001>();
	    //ProductTypeRecordsDTO recordsDTO = new ProductTypeRecordsDTO(productTypeRecords);
	    //mpap001.setRecordsDTO(recordsDTO);
	    map.put("mpap001", mpap001);
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
	    this.updateResponseStatusByOrderId(mpap001.getOrderId(), status);
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MobilePersonalAccidentProposal> findRecordsByResponseStatus(){
		List<MobilePersonalAccidentProposal> resultList= null;
		 resultList = mobilePersonalAccidentProposalDAO.findRecordsByResponseStatus();
		return resultList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateResponseStatusByOrderId(String orderId, ResponseStatus status) {
		 mobilePersonalAccidentProposalDAO.updateResponseStatusByOrderId(orderId, status);
	}

	@Override
	public void postDataSyncProcess(String order_id) throws UnsupportedEncodingException {
		MPAP001 dto = MobilePersonalAccidentProposalFactory.convertMobilePAProposalDTO(findByOrderId(order_id).get(0));
		ProductTypeRecords productType = productTypeRecordsService.findByOrderId(order_id);
		try {
			calltoOthersServerAPI(dto,productType);
		} catch (ClientProtocolException e) {
			updateResponseStatusByOrderId(order_id, ResponseStatus.REQUEST_NOT_FOUND);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCovertedStatusByOderId(PaymentOrderConfirmDTO paymentOrderConfirm) {
		for(PaymentOrderConfirm orderId : paymentOrderConfirm.getPaymentOrder()) {
			if(orderId != null) {
				mobilePersonalAccidentProposalDAO.updateConvertedStatusByOrderId(orderId);
			}
		}
	}
	
	
}
