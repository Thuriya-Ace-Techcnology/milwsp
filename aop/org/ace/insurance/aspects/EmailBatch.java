package org.ace.insurance.aspects;

import org.ace.insurance.common.EmailInfo;
import org.ace.insurance.common.EmailUtils;
import org.joda.time.LocalDate;
import org.springframework.scheduling.annotation.Scheduled;

public class EmailBatch {
	
	//@Scheduled(cron ="0 15 13 * * ?")
	public static void sendEmailByBatch() throws Exception {
		EmailInfo emailInfo = new EmailInfo();
		emailInfo.setToEmail("kyawyealwin2017@gmail.com,zarlithanwin@gmail.com");
		emailInfo.setSubject("Daily MI premium buyers report");
		emailInfo.setUserName("RTA");
		emailInfo.setCcEmail("kyawyealwin@thuriyaacetechnology.com,heinsoe@thuriyaacetechnology.com");
		emailInfo.setFilePath("D:\\ExcelDailyExport\\MIP-records-"+LocalDate.now()+".xls");
		EmailUtils.sendEmailToRTA(emailInfo);
	}
}
