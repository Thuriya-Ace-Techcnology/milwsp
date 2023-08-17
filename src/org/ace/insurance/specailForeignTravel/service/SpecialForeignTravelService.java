package org.ace.insurance.specailForeignTravel.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.SystemConstants;
import org.ace.insurance.common.TourismType;
import org.ace.insurance.moibleCountry.persistence.interfaces.IMobileCountryDAO;
import org.ace.insurance.specailForeignTravel.SpecialForeignTravel;
import org.ace.insurance.specailForeignTravel.persistence.interfaces.ISpecialForeignTravelDAO;
import org.ace.insurance.specailForeignTravel.service.interfaces.ISpecialForeignTravelService;
import org.ace.java.component.SystemException;
import org.ace.java.component.idgen.exception.CustomIDGeneratorException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.factory.SpecailForeignTravelFactory;
import org.ace.ws.model.AceResponse;
import org.ace.ws.model.specialForeignTravel.SpecialForeignTravelDTO;
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

@Service(value = "SpecialForeignTravelService")
public class SpecialForeignTravelService extends BaseService implements ISpecialForeignTravelService {

	private static final Logger logger = Logger.getLogger(SpecialForeignTravelService.class);

	@Resource(name = "ID_CONFIG")
	private Properties properties;

	@Resource(name = "SpecialForeignTravelDAO")
	private ISpecialForeignTravelDAO specialForeignTravelDAO;

	@Resource(name = "MobileCountryDAO")
	private IMobileCountryDAO mobileCountryDAO;

	@Override
	public SpecialForeignTravel addNewOrUpdateSpecailForeignTravel(SpecialForeignTravel specialForeignTravel) {
		try {
			if (specialForeignTravel.getId() == null) {

				specialForeignTravel.setProposalStatus(ProposalStatus.PENDING);
				specialForeignTravel.setDeleteStatus(false);
				specialForeignTravel.setSubmittedDate(new Date());
				setIDPrefixForInsert(specialForeignTravel);
				specialForeignTravelDAO.insert(specialForeignTravel);
			} else {
				specialForeignTravel.setSubmittedDate(new Date());
				specialForeignTravelDAO.update(specialForeignTravel);
			}
			return specialForeignTravel;
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a new MobilePersonalAccidentProposal", e);
		} catch (CustomIDGeneratorException e) {
			throw new SystemException(e.getErrorCode(), "Faield to add a new MobilePersonalAccidentProposal", e);
		}
	}

	private void setIDPrefixForInsert(SpecialForeignTravel specialForeignTravel) {
		String tProposalPrefix = customIDGenerator.getPrefix(SpecialForeignTravel.class);
		specialForeignTravel.setPrefix(tProposalPrefix);
	}

	@Override
	public List<SpecialForeignTravel> findByOrderId(String orderId) {
		List<SpecialForeignTravel> result = null;
		try {
			result = specialForeignTravelDAO.findByOrderId(orderId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(),
					"Faield to find a SpecialForeignTravel by mobileUser (ORderID : " + orderId + ")", e);
		}
		return result;
	}

	@Override
	public List<SpecialForeignTravelDTO> findProposalByPolicyNoOrPassportNo(String countryCode, String passportNo,TourismType tourismType) {
		List<SpecialForeignTravelDTO> result = null;
		try {
			result = SpecailForeignTravelFactory.convertSpecailForeignTravelListDTO(
					specialForeignTravelDAO.findProposalByPolicyNoOrPassportNo(countryCode, passportNo,tourismType));
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(),
					"Faield to find a SpecialForeignTravel by mobileUser (ORderID : " + passportNo + ")", e);
		}
		return result;
	}

	@Override
	public SpecialForeignTravel updateByPaymentStatus(String order_id, String processBy) {
		SpecialForeignTravel result = null;
		try {
			String proposalNo = customIDGenerator.getNextId(SystemConstants.M_SPECIAL_FOREIGN_TRAVEL_NO, "");
			result = specialForeignTravelDAO.updateByPaymentStatus(order_id, processBy, proposalNo);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a specialForeignTravelDAO ", e);
		}
		return result;
	}
	
	@Override
	public SpecialForeignTravel outboundUpdateByPaymentStatus(String order_id, String processBy) {
		SpecialForeignTravel result = null;
		try {
			String proposalNo = customIDGenerator.getNextId(SystemConstants.M_OUTBOUND_SPECIAL_FOREIGN_TRAVEL_NO, "");
			result = specialForeignTravelDAO.updateByPaymentStatus(order_id, processBy, proposalNo);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a specialForeignTravelDAO ", e);
		}
		return result;
	}

	@Override
	public SpecialForeignTravelDTO findById(String id) {
		SpecialForeignTravelDTO result = null;
		try {
			SpecialForeignTravel travel = specialForeignTravelDAO.findById(id);
			String passportIssuedCountry = mobileCountryDAO
					.findByCountryCode(travel.getTravellerInfo().getCountryCode());
			travel.setPassportIssuedCountry(passportIssuedCountry);
			result = SpecailForeignTravelFactory.convertSpecailForeignTravelDTO(travel);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a specialForeignTravelDAO ", e);
		}
		return result;
	}

	@Override
	public List<SpecialForeignTravelDTO> finByFromToDate(String fromDate, String toDate, TourismType tourismType) {
		List<SpecialForeignTravelDTO> result = null;
		try {
			List<SpecialForeignTravel> travel = specialForeignTravelDAO.findByFromToDate(fromDate, toDate, tourismType);
			if (travel != null) {
				result = SpecailForeignTravelFactory.convertSpecailForeignTravelListDTO(travel);
			}

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a specialForeignTravelDAO ", e);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void batchProcess(TourismType tourismType) {
		List<SpecialForeignTravel> result = null;
		try {
			result = specialForeignTravelDAO.findOnlineBillerByPaymentStatus(tourismType);
			if (result != null && !result.isEmpty()) {
				logger.info("SpecialForeignTravel FindOnlineBillerByPaymentStatus is not null");
				List<SpecialForeignTravelDTO> dtoList = SpecailForeignTravelFactory
						.convertSpecailForeignTravelListDTO(result);
				for (SpecialForeignTravelDTO dto : dtoList) {
					try {
						logger.info("Start API Call");
						dto.setPassportIssuedCountry(mobileCountryDAO.findByCountryCode(dto.getCountryCode()));
						calltoOthersServerAPI(dto);

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a SpecialForeignTravel ", e);
		}
	}

	public void calltoOthersServerAPI(SpecialForeignTravelDTO dto) throws ClientProtocolException, IOException {
		logger.info("Start Call To RestCaller SpecialForeignTravel API");
		CloseableHttpClient client = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(
				URIConstants.CALLTORESTCALLERIPADDRESS + URIConstants.SPECIAL_FOREIGN_CLOUD_TO_CORE);
		logger.info("URI is : " + httpPost.getURI());
		HashMap<String, SpecialForeignTravelDTO> map = new HashMap<>();
		map.put("InboundTravelDTO", dto);
		
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
			updateResponseStatusByOrderId(dto.getOrderId());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateResponseStatusByOrderId(String orderId) {
		logger.info("SpecialForeignTravel Module UpdateResponseStatusByOrderId Start");
		specialForeignTravelDAO.updateResponseStatusByOrderId(orderId);
		logger.info("SpecialForeignTravel Module UpdateResponseStatusByOrderId Done");
	}
	
	
}
