package org.ace.ws.controller;


import java.io.File;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.ace.insurance.system.rta.service.interfaces.IRTAService;
import org.ace.ws.client.URIConstants;
import org.ace.ws.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class RTAController extends BaseController {

	@Resource(name = "RTAService")
	private IRTAService rtaService;

	@Autowired
	ServletContext context;
	
	@RequestMapping(value = URIConstants.POST_RTA_DATA_lIST, method = RequestMethod.POST,consumes = {"multipart/form-data"})
	@ResponseBody
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile inputFile,@RequestParam("name") String dto) {
		  if (!inputFile.isEmpty()) {
			   try {
			    String originalFilename = inputFile.getOriginalFilename();
			    File destinationFile = new File(context.getRealPath("/WEB-INF/uploaded")+  File.separator + originalFilename);
			    inputFile.transferTo(destinationFile);
			    rtaService.accessFileRead(destinationFile);
			    return new ResponseEntity<>(responseManager.getResponseString("Successfully Inserted."), HttpStatus.OK);
			   } catch (Exception e) {    
			    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			   }
		  }else{
		   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		  }
	}
	
	@RequestMapping(value = URIConstants.POST_RTA_lIST, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> uploadWithNonFile() {
		 
			   try {
			    File destinationFile = new File("D:\\TAT\\TAT\\MI\\ThirdParty\\RTA data\\Yangon_Vehicle_7-2021.mdb");
			    rtaService.accessFileRead(destinationFile);
			    return new ResponseEntity<>(responseManager.getResponseString("Successfully Inserted."), HttpStatus.OK);
			   } catch (Exception e) {    
			    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			   }
		 
	}
}