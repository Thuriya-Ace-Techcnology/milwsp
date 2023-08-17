package org.ace.insurance.aspects;


import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.personalAccident.MobilePersonalAccidentProposal;
import org.ace.insurance.personalAccident.service.interfaces.IMobilePersonalAccidentProposalService;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.ws.factory.MobilePersonalAccidentProposalFactory;
import org.ace.ws.model.mobilePersonalAccidentproposal.MPAP001;
import org.apache.http.client.ClientProtocolException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PersonalAccidentBatch {
	
	@Resource(name = "MobilePersonalAccidentProposalService")
	private IMobilePersonalAccidentProposalService mobilePersonalAccidentProposalService;
	
//	@Scheduled(cron ="5 * * * * *")
	   public void fixedRateSch() throws ClientProtocolException, IOException {
	      List<MobilePersonalAccidentProposal> paProposalList = mobilePersonalAccidentProposalService.findRecordsByResponseStatus();
	      if(paProposalList != null && !paProposalList.isEmpty()) {
	    	  List<MPAP001> personalAccidentList =  MobilePersonalAccidentProposalFactory.convertMobilePAProposalDTOList(paProposalList);
	    	  for(MPAP001 mtp001 : personalAccidentList) {
	    		  ProductTypeRecords productTypeRecords = new ProductTypeRecords();
					productTypeRecords.setProductType("Travel");
					productTypeRecords.setTwoCtwoPorderId(mtp001.getOrderId());
					mobilePersonalAccidentProposalService.calltoOthersServerAPI(mtp001, productTypeRecords);
	    	  }	    	 
	      }
	   }
}
