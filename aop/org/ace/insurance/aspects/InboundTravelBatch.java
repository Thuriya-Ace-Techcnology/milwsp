package org.ace.insurance.aspects;

import java.io.IOException;

import javax.annotation.Resource;

import org.ace.insurance.common.TourismType;
import org.ace.insurance.specailForeignTravel.service.interfaces.ISpecialForeignTravelService;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InboundTravelBatch {
	@Resource(name = "SpecialForeignTravelService")
	private ISpecialForeignTravelService specialForeignTravelService;

	
	private static final Logger logger = Logger.getLogger(InboundTravelBatch.class);
	
	public void cloudToCoreBatch() throws ClientProtocolException, IOException {
		logger.info("Start SpecialForeignTravelBatch Batch");
		specialForeignTravelService.batchProcess(TourismType.INBOUND);
		logger.info("End SpecialForeignTravelBatch Batch");
	}
}
