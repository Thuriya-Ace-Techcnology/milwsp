package org.ace.ws.calculatorController;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.common.paymenttype.PaymentType;
import org.ace.insurance.system.common.paymenttype.service.interfaces.IPaymentTypeService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.factory.FireCalculatorFactory;
import org.ace.ws.model.premiumCal.ContentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaymentTypeController extends BaseController {

	@Resource(name = "PaymentTypeService")
	private IPaymentTypeService paymentTypeService;

	@RequestMapping(value = URIConstants.PAYMENT_TYPE_LIST, method = RequestMethod.POST)
	private @ResponseBody String getPaymentTypeList() {
		String response;
		List<ContentDTO> paymentTypeDTOList = new ArrayList<ContentDTO>();
		List<PaymentType> paymentTypeList = paymentTypeService.findAllPaymentType();
		if (paymentTypeList != null && !paymentTypeList.isEmpty()) {
			paymentTypeDTOList = FireCalculatorFactory.convertPaymentTypeList(paymentTypeList);
		}
		response = responseManager.getResponseString(paymentTypeDTOList);
		return response;
	}
}
