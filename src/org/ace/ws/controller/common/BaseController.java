package org.ace.ws.controller.common;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.ace.insurance.common.ResponseManager;
import org.springframework.context.ApplicationContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ace.java.component.idgen.service.interfaces.ICustomIDGenerator;

public class BaseController {
	@Resource(name = "ResponseManager")
	public ResponseManager responseManager;

	@Resource(name = "CustomIDGenerator")
	protected ICustomIDGenerator customIDGenerator;
	
	public ApplicationContext context() {
		return new ClassPathXmlApplicationContext("spring-beans.xml");
	}
	public void createFile(File file, byte[] content) {
		try {
			/* At First : Create directory of target file */
			String filePath = file.getPath();

			int lastIndex = filePath.lastIndexOf("\\") + 1;
			FileUtils.forceMkdir(new File(filePath.substring(0, lastIndex)));

			/* Create target file */
			FileOutputStream outputStream = new FileOutputStream(file);
			IOUtils.write(content, outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	protected String getPrefix(Class cla) {
		return customIDGenerator.getPrefix(cla);
	}

	private static final String COMPANY_ICON = "report-template/ggilogo.jpg";
	private static final String COMPANY_ADDRESS = "report-template/ggiaddress.jpg";
	private static final String LETTER_FOODER = "report-template/footer.jpg";

	public static String getCompanyIcon() {
		return COMPANY_ICON;
	}

	public static String getCompanyAddress() {
		return COMPANY_ADDRESS;
	}

	public static String getLetterFooter() {
		return LETTER_FOODER;
	}
}
