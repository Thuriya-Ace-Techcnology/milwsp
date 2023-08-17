package org.ace.insurance.aspects;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.travel.MobileTravelProposal;
import org.ace.insurance.travel.service.interfaces.IMobileTravelProposalService;
import org.ace.ws.factory.MobileTravelProposalFactory;
import org.ace.ws.model.mobiletravelproposal.MTP001;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Component;

@Component
public class TravelBatch {
	
	@Resource(name = "MobileTravelProposalService")
	private IMobileTravelProposalService mobileTravelProposalService;
	
	//@Scheduled(cron ="5 * * * * *")
	   public void fixedRateSch() throws ClientProtocolException, IOException {
	      List<MobileTravelProposal> travelProposalList = mobileTravelProposalService.findRecordsByResponseStatus();
	      if(travelProposalList != null && !travelProposalList.isEmpty()) {
	    	  List<MTP001> mtp001List =  MobileTravelProposalFactory.convertMobileTravelProposalDTOList(travelProposalList);
	    	  for(MTP001 mtp001 : mtp001List) {
	    		  ProductTypeRecords productTypeRecords = new ProductTypeRecords();
					productTypeRecords.setProductType("Travel");
					productTypeRecords.setTwoCtwoPorderId(mtp001.getOrderId());
					mobileTravelProposalService.calltoOthersServerAPI(mtp001, productTypeRecords);
	    	  }	    	 
	      }
	   }
}
