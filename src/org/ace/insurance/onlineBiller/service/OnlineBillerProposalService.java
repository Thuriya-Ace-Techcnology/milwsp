package org.ace.insurance.onlineBiller.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.onlineBiller.OnlineBillerBuyer;
import org.ace.insurance.onlineBiller.persistence.interfaces.IOnlineBillerProposalDAO;
import org.ace.insurance.onlineBiller.service.interfaces.IOnlineBillerProposalService;
import org.ace.java.component.SystemException;
import org.ace.java.component.idgen.exception.CustomIDGeneratorException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.factory.OnlineBillerProposalFactory;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.onlineBiller.OnlineBiller;
import org.ace.ws.model.onlineBiller.OnlineBillerDTO;
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

@Service(value = "OnlineBillerProposalService")
public class OnlineBillerProposalService extends BaseService implements IOnlineBillerProposalService {

    @Resource(name = "OnlineBillerProposalDAO")
    private IOnlineBillerProposalDAO onlineBillerProposalDAO;
    
    private static final Logger logger = Logger.getLogger(OnlineBillerProposalService.class);

	@Transactional(propagation = Propagation.REQUIRED)
	public OnlineBiller addCoreOnlineBillerProposal(OnlineBiller onlineBiller) {
		try {
			logger.info("Start Core OnlineBillerProposal");
			onlineBillerProposalDAO.insert(onlineBiller);
			return onlineBiller;
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a new OnlineBillerService", e);
		} catch (CustomIDGeneratorException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a new OnlineBillerService", e);
		}
	}
	
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OnlineBiller findOnlineBillerByInvoiceNo(String invoiceNo) throws DAOException {
    	OnlineBiller result = null;
        try {
            result = onlineBillerProposalDAO.findOnlineBillerByInvoiceNo(invoiceNo);
            
        } catch (DAOException e) {
            throw new SystemException(e.getErrorCode(), "Faield to find a OnlineBillerService by invoiceNo ", e);
        }
        return result;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<OnlineBillerBuyer> findOnlineBillerByDate(Date fromDate,Date toDate,BuyerPlatForm buyPlatForm) throws DAOException {
    	List<OnlineBillerBuyer> result = null;
        try {
            result = onlineBillerProposalDAO.findOnlineBillerByDate(fromDate,toDate,buyPlatForm);
            
        } catch (DAOException e) {
            throw new SystemException(e.getErrorCode(), "Faield to find a OnlineBillerService by invoiceNo ", e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addNewOnlineBillerProduct(OnlineBillerBuyer buyer) {
        try {
            buyer.setPrefix(getPrefix(OnlineBillerBuyer.class));
            onlineBillerProposalDAO.addNewOnlineBillerProduct(buyer);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateOnlineBillingStatus(String invoiceNo) {
        int result = 0;
        try {
             result = onlineBillerProposalDAO.updateTempOnlineBillerStatus(invoiceNo);
        } catch (DAOException e) {
            e.printStackTrace();
        }    
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OnlineBillerBuyer updateByPaymentStatus(String orderId)throws DAOException {
        OnlineBillerBuyer result = null;
        try {
        	logger.info("Start UpdateByPaymentStatus");
            result = onlineBillerProposalDAO.updateByPaymentStatus(orderId);
            logger.info("DAO findByOrderId");
            OnlineBillerBuyer buyer = onlineBillerProposalDAO.findOnlineBillerByOrderId(orderId);
            logger.info("Update Biller module");
            updateOnlineBillingStatus(buyer.getInvoiceNo());
            logger.info("End UpdateByPaymentStatus");
        } catch (DAOException e) {
            throw new SystemException(e.getErrorCode(), "Faield to find a OnlineBillerService ", e);
        }
        return result;
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public OnlineBillerBuyer findByOrderId(String orderId) {
		OnlineBillerBuyer result = null;
        try {
            result = onlineBillerProposalDAO.findOnlineBillerByOrderId(orderId);
            
        } catch (DAOException e) {
            throw new SystemException(e.getErrorCode(), "Faield to find a OnlineBillerService by invoiceNo ", e);
        }
        return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void batchProcess() {
		List<OnlineBillerBuyer> result = null;
        try {
            result = onlineBillerProposalDAO.findOnlineBillerByPaymentStatus();
            if(result != null && !result.isEmpty()) {
            	logger.info("Online Biller FindOnlineBillerByPaymentStatus is not null");
            	List<OnlineBillerDTO> obList = OnlineBillerProposalFactory.convertOnlineBillerBuyerListToDTO(result);
    			for (OnlineBillerDTO ob : obList) {
    				try {
    					logger.info("Start API Call");
						calltoOthersServerAPIThirdPartyPremiumReceipt(ob);
					} catch (IOException e) {
						e.printStackTrace();
					}
    			}
            }
            
        } catch (DAOException e) {
            throw new SystemException(e.getErrorCode(), "Faield to find a OnlineBillerService ", e);
        }
	}
	
	public void calltoOthersServerAPIThirdPartyPremiumReceipt(OnlineBillerDTO obDTO) throws ClientProtocolException, IOException {
		logger.info("Start Call To RestCaller OnlineBiller API");
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpPost httpPost = new HttpPost(URIConstants.CALLTORESTCALLERIPADDRESS+URIConstants.ONLINE_BILLER_CLOUD_TO_CORE);
		logger.info("URI is : "+httpPost.getURI());
		HashMap<String, OnlineBillerDTO> map = new HashMap<>();
		map.put("onlineBillerDTO", obDTO);
		logger.info("First Param address is : "+obDTO);
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
		logger.info("Response Code is "+response.getCode());
		if (response.getCode() == 200) {
			this.updateResponseStatusByOrderId(obDTO.getOrderId());
		} 
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateResponseStatusByOrderId(String orderId) {
		logger.info("Online Biller Module UpdateResponseStatusByOrderId Start");
		onlineBillerProposalDAO.updateResponseStatusByOrderId(orderId);
		logger.info("Online Biller Module UpdateResponseStatusByOrderId Done");
	}
}