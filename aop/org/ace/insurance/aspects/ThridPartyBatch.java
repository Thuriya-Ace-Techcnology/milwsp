package org.ace.insurance.aspects;

import java.io.IOException;

import javax.annotation.Resource;

import org.ace.insurance.system.thirdparty.service.interfaces.IThirdPartyPremiumReceiptSerivce;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Component;

@Component
public class ThridPartyBatch {

	@Resource(name = "ThirdPartyPremiumReceiptService")
	private IThirdPartyPremiumReceiptSerivce thirdPartyService;

	//@Scheduled(cron = "5 * * * * *")
	public void fixedRateSch() throws ClientProtocolException, IOException {
		thirdPartyService.batchProcess();
	}
}
