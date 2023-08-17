package org.ace.insurance.aspects;


import java.io.IOException;

import javax.annotation.Resource;

import org.ace.insurance.common.TourismType;
import org.ace.insurance.specailForeignTravel.service.interfaces.IOutboundTravelService;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OutboundTravelBatch {
	@Resource(name = "OutboundTravelService")
	private IOutboundTravelService outboundTravelService;

	
	private static final Logger logger = Logger.getLogger(OutboundTravelBatch.class);
	
	
	//@Scheduled(cron = "5 */20 * * * *")
	public void cloudToCoreBatch() throws ClientProtocolException, IOException {
		logger.info("Start Outbound Travel Batch CLOUD To CORE ... ");
		outboundTravelService.batchOutboundProcess(TourismType.OUTBOUND);
		logger.info("End Outbound Travel Batch CLOUD To CORE ...");
	}
}
