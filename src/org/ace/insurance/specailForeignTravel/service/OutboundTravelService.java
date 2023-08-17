package org.ace.insurance.specailForeignTravel.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import com.google.gson.Gson;
import org.ace.insurance.common.TourismType;
import org.ace.insurance.moibleCountry.persistence.interfaces.IMobileCountryDAO;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravel;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravelHistory;
import org.ace.insurance.specailForeignTravel.persistence.interfaces.IOutboundTravelDAO;
import org.ace.insurance.specailForeignTravel.service.interfaces.IOutboundTravelService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.factory.OutboundTravelFactory;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.outboundTravel.OutboundTravelDTO;
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

@Service(value = "OutboundTravelService")
public class OutboundTravelService extends BaseService implements IOutboundTravelService {

	
	private static final Logger logger = Logger.getLogger(OutboundTravelService.class);

	@Resource(name = "ID_CONFIG")
	private Properties properties;
	
	@Resource(name = "OutboundTravelDAO")
	private IOutboundTravelDAO outboundTravelDAO;
	
	@Resource(name = "MobileCountryDAO")
	private IMobileCountryDAO mobileCountryDAO;

	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void batchOutboundProcess(TourismType tourismType) {
		List<SpecialForeignTravel> result = null;
		try {
			result = outboundTravelDAO.findOutboundTravelByProposalStatus(tourismType);
			if (result != null && !result.isEmpty()) {
				logger.info("Outbound SpecialForeignTravel is not null");
				List<OutboundTravelDTO> dtoList = OutboundTravelFactory
						.convertOutboundTravelListDTO(result);
				for (OutboundTravelDTO dto : dtoList) {
					try {
						logger.info("Start API Call");
						dto.setPassportIssuedCountry(mobileCountryDAO.findByCountryCode(dto.getCountryCode()));
						calltoOthersServerAPIForOutbound(dto);

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a SpecialForeignTravel ", e);
		}
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void calltoOthersServerAPIForOutbound(OutboundTravelDTO dto) throws ClientProtocolException, IOException {
		logger.info("Start Call To RestCaller Outbound SpecialForeignTravel API");
		CloseableHttpClient client = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(
				URIConstants.CALLTORESTCALLERIPADDRESS + URIConstants.OUTBOUND_TRAVEL_CLOUD_TO_CORE);
		logger.info("URI is : " + httpPost.getURI());
		HashMap<String, OutboundTravelDTO> map = new HashMap<>();
		map.put("OutboundTravelDTO", dto);
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		StringEntity request = new StringEntity(json);
		httpPost.setEntity(request);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		httpPost.setHeader("Accept-Charset", "UTF-8");
		CloseableHttpResponse httpResp = client.execute(httpPost);
		String result = EntityUtils.toString(httpResp.getEntity());
		AceResponse response = gson.fromJson(result, AceResponse.class);
		client.close();
		logger.info("Response Code is " + response.getCode());
		if (response.getCode() == 200) {
	//		updateResponseStatusByOrderId(dto.getOrderId());
		}
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateResponseStatusByOrderId(String orderId) {
		logger.info("SpecialForeignTravel Module UpdateResponseStatusByOrderId Start");
		outboundTravelDAO.updateResponseStatusByOrderId(orderId);
		logger.info("SpecialForeignTravel Module UpdateResponseStatusByOrderId Done");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<SpecialForeignTravel> findByOrderId(OutboundTravelDTO dto) {
		try {
			SpecialForeignTravel result = outboundTravelDAO.findOutboundTravelByOrderSingleId(dto.getOrderId());
			if(result != null ) {
				//Insert History
				outboundTravelDAO.addNewSpeicalForeignTravelHistory(new SpecialForeignTravelHistory(result));
				//Update Record
				SpecialForeignTravel travel = OutboundTravelFactory.convertOutboundTravelDTOToEntity(dto);
				outboundTravelDAO.updateSpecialForeignTravel(travel);
			}
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find  Outbound Travel List by orderId ", e);
		}
		
		return null;
	}

	
	
	
	

	
	
	
	

	

}
