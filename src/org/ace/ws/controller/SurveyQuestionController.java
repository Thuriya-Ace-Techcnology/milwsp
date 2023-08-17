package org.ace.ws.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.rpc.ServiceException;

import org.ace.insurance.productprocess.ProductProcessDTO;
import org.ace.insurance.productprocess.service.interfaces.IProductProcessService;
import org.ace.insurance.surveyquestion.SurveyQuestion;
import org.ace.insurance.surveyquestion.SurveyQuestionDTO;
import org.ace.insurance.surveyquestion.service.interfaces.ISurveyQuestionService;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
public class SurveyQuestionController extends BaseController {

	@Resource(name = "SurveyQuestionService")
	private ISurveyQuestionService surveyQuestionService;

	@Resource(name = "ProductProcessService")
	private IProductProcessService productProcessService;
	private static final Logger logger = Logger.getLogger(SurveyQuestionController.class);

    @RequestMapping(value = URIConstants.GET_SURVEYQUESTION_LIST, method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public @ResponseBody String getSurveyQuestionList(@RequestHeader String key) throws ServiceException {

		logger.info("Start Select RelationShip Data.");

		AceResponse aceResponse = new AceResponse();
		try {
			if (key.toString().equals(Constants.getApikey())) {
				List<SurveyQuestion> result = surveyQuestionService.findAllSurveyQuestion();

				List<SurveyQuestionDTO> response = new ArrayList<SurveyQuestionDTO>();
				if (result != null)
					for (SurveyQuestion p : result) {
						SurveyQuestionDTO dto = new SurveyQuestionDTO(p);
						response.add(dto);
					}

				if (!response.isEmpty() && response != null) {
					aceResponse.setData(response);
					aceResponse.setMessage("Success");
					aceResponse.setStatus(HttpStatus.OK);
				} else {
					aceResponse.setMessage("Empty Data");
					aceResponse.setStatus(HttpStatus.NOT_FOUND);
				}
			} else {
				logger.info("API key  is incorrect or null.");
				aceResponse.setStatus(HttpStatus.BAD_REQUEST);
				aceResponse.setMessage("API key  is incorrect or null.");
				return responseManager.getResponseString(aceResponse);
			}
		} catch (SystemException e) {
			e.printStackTrace();
			responseManager.getResponseString(ResponseStatus.INTERNAL_SERVER_ERROR.getLabel());
			throw new ServiceException(StatusType.SQL_Exception);
		}

		logger.info("End ISelect RelationShip Data.");
		return responseManager.getResponseString(aceResponse);

	}

	@RequestMapping(value = URIConstants.GET_PRODUCT_PROCESS_LIST, method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public @ResponseBody String getProductProcessList(@RequestHeader String key,
			@QueryParam("productId") String productId, @QueryParam("processId") String processId)
			throws ServiceException, UnsupportedEncodingException {
		logger.info("Start Select  Product Process for Survey.");
		AceResponse aceResponse = new AceResponse();
		List<ProductProcessDTO> response = new ArrayList<ProductProcessDTO>();
		if (key.toString().equals(Constants.getApikey())) {
			response = productProcessService.findOldConfPP(productId, processId);
			aceResponse.setData(response);
			aceResponse.setMessage("Success");
			aceResponse.setStatus(HttpStatus.OK);
		} else {
			logger.info("API key  is incorrect or null.");
			aceResponse.setStatus(HttpStatus.BAD_REQUEST);
			aceResponse.setMessage("API key  is incorrect or null.");
			return responseManager.getResponseString(aceResponse);
		}
		return responseManager.getResponseString(aceResponse);
	}
}
