package org.ace.insurance.aspects;

import java.io.IOException;

import javax.annotation.Resource;

import org.ace.insurance.onlineBiller.service.interfaces.IOnlineBillerProposalService;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OnlineBillerBatch {
	@Resource(name = "OnlineBillerProposalService")
	private IOnlineBillerProposalService onlineBillerService;

	
	private static final Logger logger = Logger.getLogger(OnlineBillerBatch.class);
	
	
	public void cloudToCoreBatch() throws ClientProtocolException, IOException {
		logger.info("Start Online Biller Batch");
		onlineBillerService.batchProcess();
		logger.info("End Online Biller Batch");
	}
}
