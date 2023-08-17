package org.ace.ws.calculatorController;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.common.currency.Currency;
import org.ace.insurance.system.common.currency.service.interfaces.ICurrencyService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.factory.FireCalculatorFactory;
import org.ace.ws.model.premiumCal.ContentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CurrencyController extends BaseController {
	@Resource(name = "CurrencyService")
	private ICurrencyService currencyService;

	@RequestMapping(value = URIConstants.CURRENCY_LIST, method = RequestMethod.POST)
	private @ResponseBody String getCurrencyList() {
		String response;
		List<ContentDTO> currencyDTOList = new ArrayList<ContentDTO>();
		List<Currency> currencyList = currencyService.findAllCurrency();
		if (currencyList != null && !currencyList.isEmpty()) {
			currencyDTOList = FireCalculatorFactory.convertCurrencyList(currencyList);
		}
		response = responseManager.getResponseString(currencyDTOList);
		return response;
	}
}
