package org.ace.ws.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.ace.insurance.system.productfaq.ProductFAQ;
import org.ace.insurance.system.productfaq.service.interfaces.IProductFAQService;
import org.ace.java.component.StatusType;
import org.ace.java.component.SystemException;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.factory.ProductFAQFactory;
import org.ace.ws.model.Constants;
import org.ace.ws.model.ResponseStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductFAQController extends BaseController {
	@Resource(name = "ProductFAQService")
	private IProductFAQService productFAQService;

	@RequestMapping(value = URIConstants.PRODUCT_FAQ_LIST, method = RequestMethod.POST)
	public @ResponseBody String getProductFAQList(@RequestHeader String key) throws ServiceException {
		String response = null;
		try {
			if (key != null && key.toString().equals(Constants.getApikey())) {
				List<ProductFAQ> productFAQList = productFAQService.findAllProductFAQ();
				response = responseManager.getResponseString(ProductFAQFactory.convertProductFAQ001List(productFAQList));
			} else {
				response = responseManager.getResponseString(ResponseStatus.INVALID_REQUEST_PARAM.getLabel());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			response = responseManager.getResponseString(StatusType.SQL_Exception);
			throw new ServiceException(StatusType.SQL_Exception);

		}
		return response;
	}
}
