package org.ace.ws.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.ace.insurance.system.productinformation.maincategory.MainCategory;
import org.ace.insurance.system.productinformation.maincategory.service.interfaces.IMainCategoryService;
import org.ace.java.component.StatusType;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.factory.ProductSpecificationFactory;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.mainCategory.MC001;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductSpecificationController extends BaseController {

	@Resource(name = "MainCategoryService")
	private IMainCategoryService categoryService;

	@RequestMapping(value = URIConstants.GET_PRODUCT_SPECIFICATION_BY_PRODUCT_ID, method = RequestMethod.POST)
	public @ResponseBody String getProductSpecificationByProductId(@RequestHeader String key, @RequestBody MC001 mainCategory) throws ServiceException {
		String response = "";
		List<MC001> mainCategoryList = categoryService.findMainCategoryByProductId(mainCategory.getProductId());
		try {
			if (key != null && key.toString().equals(Constants.getApikey())) {
				if (mainCategory != null && mainCategory.getProductId() != null) {
					response = responseManager.makingJSonString(mainCategoryList, null);
				} else {
					response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
				}
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(StatusType.SQL_Exception);
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}

	@RequestMapping(value = URIConstants.PRODUCT_SPECIFICATION_LIST, method = RequestMethod.POST)
	public @ResponseBody String getProductSpecificationList(@RequestHeader String key) throws ServiceException {
		String response = "";
		try {
			if (key != null && key.toString().equals(Constants.getApikey())) {
				List<MainCategory> mainCategoryList = categoryService.findAllMainCategory();
				response = responseManager.makingJSonString(ProductSpecificationFactory.convertMainCategoryDTOList(mainCategoryList), null);
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(StatusType.SQL_Exception);
			throw new ServiceException(StatusType.SQL_Exception);
		}
		return response;
	}
}
