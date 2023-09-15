package org.ace.ws.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;
import javax.ws.rs.QueryParam;
import javax.xml.rpc.ServiceException;
import org.ace.insurance.common.Utils;
import org.ace.insurance.life.dto.BeneficiariesInfoDTO;
import org.ace.insurance.life.dto.LifePolicyDTO;
import org.ace.insurance.life.lifePolicy.service.interfaces.ILifePolicyService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.ace.ws.controller.common.JasperTemplate;
import org.ace.ws.factory.JasperFactory;
import org.ace.ws.model.AceResponse;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@CrossOrigin
@Controller
public class SeamanController extends BaseController {

	@Resource(name = "LifePolicyService")
	private ILifePolicyService lifePolicyService;

	private static final Logger logger = Logger.getLogger(SeamanController.class);

	/* POST seaman policy list find by CDC NO */
	@RequestMapping(value = URIConstants.GET_SEAMAN_POL_BY_CDCNO, method = RequestMethod.POST)
	public @ResponseBody String getSeamanPolicyByCdcNo(@RequestHeader String key, @RequestParam(name = "cdcNo", required = true) String cdcNo,
			@RequestParam(name = "productId", required = false) String productId)
			throws ServiceException, UnsupportedEncodingException, SystemException {
		logger.info("Start Select Seaman Policy By CDC No.");
		AceResponse aceResponse = new AceResponse();
		List<LifePolicyDTO> seamanPolicyList = lifePolicyService.findSeamanPolicyByCDCNo(cdcNo, productId);
		if (seamanPolicyList != null) {
			aceResponse.setData(seamanPolicyList);
			aceResponse.setMessage("Success");
			aceResponse.setStatus(HttpStatus.OK);
		} else {
			aceResponse.setMessage("Empty Data");
			aceResponse.setStatus(HttpStatus.NOT_FOUND);
		}
		logger.info("End Select Seaman Policy  List by CDC No.");
		return responseManager.getResponseString(aceResponse);
	}

	
	/* GET Seaman Net Premium */
	@RequestMapping(value = URIConstants.GET_SEAMAN_NETPREMIUM, method = RequestMethod.POST)
	public @ResponseBody String getSeamanNetPremium(@RequestHeader String key, @QueryParam("premium") double premium)
			throws ServiceException, UnsupportedEncodingException, SystemException {
		logger.info("Start get NetPremium.");
		AceResponse aceResponse = new AceResponse();
		double netPremium = lifePolicyService.getSeamanNetPremium(premium);
		if (netPremium != 0.0) {
			aceResponse.setData(netPremium);
			aceResponse.setMessage("Success");
			aceResponse.setStatus(HttpStatus.OK);
		} else {
			aceResponse.setMessage("Empty Data");
			aceResponse.setStatus(HttpStatus.NOT_FOUND);
		}
		logger.info("End retriving Net Premium.");
		return responseManager.getResponseString(aceResponse);
	}
	
	
	/* Print Seaman Policy Certificate and T&C */
	@RequestMapping(value = URIConstants.GET_SEAMAN_POLICY_CERTIFICATE, method = RequestMethod.GET)
	public ResponseEntity<byte[]> printReceipt(HttpServletResponse response, @QueryParam("policyId") String policyId)
			throws IOException, JRException {
		logger.info("Start Print Receipt function");
		byte[] bb = null;
		LifePolicyDTO policyDTO = lifePolicyService.findByPolicyId(policyId);
		if (policyDTO != null) {
			logger.info("Receipt object is not null");
			Map<String, Object> policyParam = new HashMap<>();
			ArrayList<LifePolicyDTO> policy = new ArrayList<>();
			policy.add(policyDTO);

			List<BeneficiariesInfoDTO> beneifitPersonDTOList = policyDTO.getPolicyInsuredPersonDTOList().get(0)
					.getBeneficiariesInfoDTOList();
			int size = beneifitPersonDTOList.size();
			policyParam.put("milogo", "document-template/images/MI-logo.png");
			policyParam.put("policyNo", policyDTO.getPolicyNo());
			policyParam.put("startDate", Utils.formattedDate(new Date(policyDTO.getPolicyStartDate())));
			policyParam.put("endDate", Utils.formattedDate(new Date(policyDTO.getPolicyEndDate())));
			policyParam.put("paymentType", policyDTO.getPaymentType());
			policyParam.put("periodOfYear", policyDTO.getPeriodOfYear());
			policyParam.put("size", size);

			/* InsuredPerson Information */
			policyParam.put("premium",
					Utils.getCurrencyFormatString(policyDTO.getPolicyInsuredPersonDTOList().get(0).getPremium()));
			policyParam.put("sumInsured",
					Utils.getCurrencyFormatString(policyDTO.getPolicyInsuredPersonDTOList().get(0).getSumInsured()));
			policyParam.put("insuredName", policyDTO.getPolicyInsuredPersonDTOList().get(0).getInsuredName());
			policyParam.put("passportNo", policyDTO.getPolicyInsuredPersonDTOList().get(0).getPassportNo());
			policyParam.put("dateOfBirth", Utils.formattedDate(new Date(policyDTO.getPolicyInsuredPersonDTOList().get(0).getDateOfBirth())));
			policyParam.put("age", policyDTO.getPolicyInsuredPersonDTOList().get(0).getAge());
			policyParam.put("insuredAddress", policyDTO.getPolicyInsuredPersonDTOList().get(0).getInsuResidentAddress());
			policyParam.put("cdcNo", policyDTO.getPolicyInsuredPersonDTOList().get(0).getCdcNo());
			policyParam.put("planType", policyDTO.getPolicyInsuredPersonDTOList().get(0).getPlanType());
			policyParam.put("insuredId", policyDTO.getPolicyInsuredPersonDTOList().get(0).getFullIdNo());
			policyParam.put("cdcNo", policyDTO.getPolicyInsuredPersonDTOList().get(0).getCdcNo());
			policyParam.put("oceanlinerName", policyDTO.getPolicyInsuredPersonDTOList().get(0).getOceanlinerName());
			policyParam.put("vesselName", policyDTO.getPolicyInsuredPersonDTOList().get(0).getVesselName());
			policyParam.put("position", policyDTO.getPolicyInsuredPersonDTOList().get(0).getPosition());

			List<BeneficiariesInfoDTO> benefitPersonList = policyDTO.getPolicyInsuredPersonDTOList().get(0).getBeneficiariesInfoDTOList();
			policyParam.put("listDataSource", new JRBeanCollectionDataSource(benefitPersonList));
			
			List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
			JasperPrint polJasper = JasperFactory.generateJasperPrint(policyParam, JasperTemplate.SEAMAN_ONLINE_POLICY_CERTIFICATE, new JREmptyDataSource());
			jasperPrintList.add(polJasper);
			
			if(size >1) {
				JasperPrint polAttJasper = JasperFactory.generateJasperPrint(policyParam, JasperTemplate.SEAMAN_ONLINE_POLICY_BENE_ATTACH_CERTIFICATE, new JREmptyDataSource());
				jasperPrintList.add(polAttJasper);
			}
			
			JasperPrint termsAndConJasper = JasperFactory.generateJasperPrint(policyParam, JasperTemplate.SEAMEN_ONLINE_TAndC, new JREmptyDataSource());
			jasperPrintList.add(termsAndConJasper);

			bb = generateReport(jasperPrintList);
			
			response.setContentType(MediaType.APPLICATION_PDF_VALUE);
			response.setHeader("Content-Disposition", "inline; filename=myanma_insurnace.pdf");
			logger.info("printRecipt Function End");

		}

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bb, HttpStatus.NOT_ACCEPTABLE);

		return responseEntity;
	}

	public byte[] generateReport(List<JasperPrint> jasperPrintList) throws JRException {
		// throw the JasperPrint Objects in a list
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setCreatingBatchModeBookmarks(true);
		exporter.setConfiguration(configuration);
		exporter.exportReport();
		return baos.toByteArray();
	}

}
