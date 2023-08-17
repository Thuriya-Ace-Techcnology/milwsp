package org.ace.ws.factory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

public class JasperFactory {
	public static String webRootPath = "";
	public static String imgPath="/images/";

	public JasperFactory() {
		super();
		webRootPath = getWebRootPath();
		imgPath=webRootPath+imgPath;
	}

	public String printReceipt(String reportName, Map<String, Object> paramMap) throws UnsupportedEncodingException {
		String pdfDirPath = "pdf-report/" + reportName + "/" + System.currentTimeMillis() + "/";
		String dirPath = webRootPath + pdfDirPath;
		String fileName = reportName + ".pdf";
		List<JasperPrint> jasperList = new ArrayList<JasperPrint>();
		JasperPrint jprint = generateJasperPrint(paramMap, "document-template/" + reportName + ".jrxml",
				new JREmptyDataSource());
		jasperList.add(jprint);
		exportReportToPdfFile(jasperList, dirPath, fileName);
		return pdfDirPath + fileName;
	}

	public static void exportReportToPdfFile(List<JasperPrint> jasperPrints, String dirPath, String fileName) {
		try {
			for (JasperPrint jprint : jasperPrints)
				removeBlankPages(jprint);
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT_LIST, jasperPrints);
			forceMakeDirectory(dirPath);
			OutputStream outputStream = new FileOutputStream(dirPath + fileName);
			exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, outputStream);
			exporter.exportReport();
			outputStream.close();
		} catch (IOException e) {
			/*
			 * throw new SystemException(ErrorCode.SYSTEM_ERROR,
			 * "Failed to export PDF file", e);
			 */
		} catch (JRException e) {
//			throw new SystemException(ErrorCode.SYSTEM_ERROR, "Failed to export PDF file", e);
		}
	}

	protected String getWebRootPath() {
		String systemPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		systemPath = systemPath.substring(1);
		systemPath = systemPath.replace("WEB-INF/classes", "");
		return systemPath;
	}

	protected static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static JasperPrint generateJasperPrint(Map<String, Object> paramMap, String jasperTemplateDirPath,
			JRDataSource dataSource) {
		JasperPrint jprint = null;
		try {
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(jasperTemplateDirPath);
			JasperReport jreport = JasperCompileManager.compileReport(inputStream);
			jprint = JasperFillManager.fillReport(jreport, paramMap, dataSource);
		} catch (JRException e) {
			e.printStackTrace();
		}
		return jprint;
	}

	public static void removeBlankPages(JasperPrint jp) {
		List<JRPrintPage> pages = jp.getPages();
		for (Iterator<JRPrintPage> i = pages.iterator(); i.hasNext();) {
			JRPrintPage page = (JRPrintPage) i.next();
			if (page.getElements().size() == 0)
				i.remove();
		}
	}

	public static void forceMakeDirectory(String fullFilePath) throws IOException {
		forceMakeDirectory(new File(fullFilePath));
	}

	public static void forceMakeDirectory(File file) throws IOException {
		FileUtils.forceMkdir(file);
	}
}
