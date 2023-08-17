package org.ace.insurance.aspects;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.medical.MobileMedicalProposal;
import org.ace.insurance.medical.service.interfaces.IMobileMedicalProposalService;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.ws.factory.MobileMedicalProposalFactory;
import org.ace.ws.model.mobileMedicalproposal.MedicalProposalDTO;
import org.apache.http.client.ClientProtocolException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HealthBatch {
	@Resource(name = "MobileMedicalProposalService")
	private IMobileMedicalProposalService medicalService;
	
	//@Scheduled(cron ="5 * * * * *")
	   public void fixedRateSch() throws ClientProtocolException, IOException {
	      List<MobileMedicalProposal> medicalList = medicalService.findRecordsByResponseStatus();
	      if(medicalList != null && !medicalList.isEmpty()) {
	    	  List<MedicalProposalDTO> mdDTOList = MobileMedicalProposalFactory.convertMobileMedicalProposalDTOList(medicalList);
	    	  for(MedicalProposalDTO mtp001 : mdDTOList) {
	    		  ProductTypeRecords productTypeRecords = new ProductTypeRecords();
					productTypeRecords.setProductType("Medical");
					productTypeRecords.setTwoCtwoPorderId(mtp001.getOrderId());
					medicalService.calltoOthersServerAPIForMP(mtp001, productTypeRecords);
	    	  }	    	 
	      }
	   }
}
