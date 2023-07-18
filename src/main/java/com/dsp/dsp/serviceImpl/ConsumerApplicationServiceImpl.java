package com.dsp.dsp.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dsp.dsp.dto.ConsumerApplicationDto;
import com.dsp.dsp.dto.FileUploadPathDto;
import com.dsp.dsp.dto.PendingForGeoLocationApplicationDto;
import com.dsp.dsp.model.Consumer;
import com.dsp.dsp.model.ConsumerApplication;
import com.dsp.dsp.repository.ApplicationStatusRepository;
import com.dsp.dsp.repository.ConsumerApplicationRepository;
import com.dsp.dsp.repository.ConsumerRepository;
import com.dsp.dsp.response.Response;
import com.dsp.dsp.service.ConsumerApplicationService;
import com.dsp.dsp.util.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ConsumerApplicationServiceImpl implements ConsumerApplicationService{

	@Autowired
	ConsumerApplicationRepository consumerApplicationRepository;

	@Autowired
	ConsumerRepository consumerRepository;

	@Autowired
	ApplicationStatusRepository applicationStatusRepository;

	@Override
	@org.springframework.transaction.annotation.Transactional
	public Response submit(String consumerApplicationDto, MultipartFile tAndCPpermissionFile,
			MultipartFile reraPermissionFile, MultipartFile groupPermissionFile, MultipartFile registryFile,
			MultipartFile nOCfile, MultipartFile administrativeFile, MultipartFile gstFile,
			MultipartFile khasraKhatoniFile) {

		try {
			if(consumerApplicationDto!=null) {
				ObjectMapper objectMapper = new ObjectMapper();
				ConsumerApplicationDto consumerApplicationDtoParse = objectMapper.readValue(consumerApplicationDto, ConsumerApplicationDto.class);
				Long natureOfWorkId = consumerApplicationDtoParse.getNatureOfWorkId();
				Response checkValidation = checkValidation(consumerApplicationDtoParse,gstFile);

				if(checkValidation==null) {
					ConsumerApplication consumerApplication = new ConsumerApplication();
					Long schemeTypeId = consumerApplicationDtoParse.getSchemeTypeId();

					String createApplicationIdBySchemeType = createApplicationIdBySchemeType(schemeTypeId);

					if(createApplicationIdBySchemeType==null) {
						return Response.response("Scheme type id incorrect", HttpStatus.BAD_REQUEST, null, null);	
					}		
					return   checkNatureOfWork(natureOfWorkId,consumerApplicationDtoParse,consumerApplication,createApplicationIdBySchemeType, tAndCPpermissionFile,
							reraPermissionFile,  groupPermissionFile,  registryFile,
							nOCfile,  administrativeFile,  gstFile,
							khasraKhatoniFile);
				}
				else {
					return checkValidation;
				}
			} 
			return Response.response("consumerApplicationDto should be not null", HttpStatus.BAD_REQUEST, null, null);
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);
		}
	}

	private Response setLineShiftingGov(ConsumerApplicationDto consumerApplicationDtoParse, ConsumerApplication consumerApplication, String createApplicationIdBySchemeType, MultipartFile administrativeFile, MultipartFile gstFile) throws Exception {

		try {  	  
			Long ht11kv = consumerApplicationDtoParse.getHt11KV();
			Long ht132kv = consumerApplicationDtoParse.getHt132KV();
			Long ht33kv = consumerApplicationDtoParse.getHt33KV();
			Long dtr = consumerApplicationDtoParse.getDtr();
			Long lt = consumerApplicationDtoParse.getLt();
			Long ptr = consumerApplicationDtoParse.getPtr();

			if(administrativeFile==null ) {
				return Response.response("Administrative File should not be null", HttpStatus.BAD_REQUEST, null, null);	  
			}

			if(ht11kv==null && ht132kv==null && ht33kv==null && dtr==null && lt==null && ptr==null) {
				return Response.response("Supply voltage should not be null", HttpStatus.BAD_REQUEST, null, null);
			}

			Response administrativeUploadFile = Utility.uploadFile(administrativeFile, "ADMINISTRATIVE_FILE");
			if(administrativeUploadFile.getStatus()==200){
				FileUploadPathDto fileUploadPathDto=	 (FileUploadPathDto) administrativeUploadFile.getObject();
				consumerApplication.setAdministrativeFilePath(fileUploadPathDto.getFilePath());  
			}
			else {
				return administrativeUploadFile;
			}

			Response gstUploadFile = Utility.uploadFile(gstFile, "GST_FILE");
			if(gstUploadFile.getStatus()==200){
				FileUploadPathDto fileUploadPathDto=	 (FileUploadPathDto) gstUploadFile.getObject();
				consumerApplication.setGstFilePath(fileUploadPathDto.getFilePath());  
			}
			else {
				return gstUploadFile;
			}
			consumerApplication.setConsumerApplicationId(createApplicationIdBySchemeType);
			consumerApplication.setNatureOfWorkId(consumerApplicationDtoParse.getNatureOfWorkId());
			consumerApplication.setHt11KV(consumerApplicationDtoParse.getHt11KV());
			consumerApplication.setHt132KV(consumerApplicationDtoParse.getHt132KV());
			consumerApplication.setHt33KV(consumerApplicationDtoParse.getHt33KV());
			consumerApplication.setDtr( consumerApplicationDtoParse.getDtr());
			consumerApplication.setLt(consumerApplicationDtoParse.getLt());
			consumerApplication.setSchemeTypeId(consumerApplicationDtoParse.getSchemeTypeId());
			consumerApplication.setConsumerId(consumerApplicationDtoParse.getConsumerId());
			consumerApplication.setGuardianName(consumerApplicationDtoParse.getGuardianName());
			consumerApplication.setAddress(consumerApplicationDtoParse.getAddress());
			consumerApplication.setPincode(consumerApplicationDtoParse.getPincode());
			consumerApplication.setDistrictId(consumerApplicationDtoParse.getDistrictId());
			consumerApplication.setDcId( consumerApplicationDtoParse.getDcId());
			consumerApplication.setWorkLocationAddr(consumerApplicationDtoParse.getWorkLocationAddr());
			consumerApplication.setDescriptionOfWork(consumerApplicationDtoParse.getDescriptionOfWork());
			consumerApplication.setGstNo(consumerApplicationDtoParse.getGstNo());
			consumerApplication.setCreatedTime(LocalDateTime.now().toString());
			consumerApplication.setIsActive(true);
			ConsumerApplication saveConsumerApplication = consumerApplicationRepository.save(consumerApplication);
			return Response.response("Submit sucessfully", HttpStatus.OK, saveConsumerApplication, null);

		} catch (Exception  e) {		
			throw e;
		}	
	}

	private Response setLineShiftingNonGov(ConsumerApplicationDto consumerApplicationDtoParse, ConsumerApplication consumerApplication, String createApplicationIdBySchemeType, MultipartFile gstFile) throws Exception {
		try {

			String ivrsNo = consumerApplicationDtoParse.getIvrsNo();
			Long ht11kv = consumerApplicationDtoParse.getHt11KV();
			Long ht132kv = consumerApplicationDtoParse.getHt132KV();
			Long ht33kv = consumerApplicationDtoParse.getHt33KV();
			Long dtr = consumerApplicationDtoParse.getDtr();
			Long lt = consumerApplicationDtoParse.getLt();
			Long ptr = consumerApplicationDtoParse.getPtr();

			if(ivrsNo==null ) {
				return Response.response("Ivrs number should not be null", HttpStatus.BAD_REQUEST, ivrsNo, null);
			}

			if(ht11kv==null && ht132kv==null && ht33kv==null && dtr==null && lt==null && ptr==null) {
				return Response.response("Supply voltage should not be null", HttpStatus.BAD_REQUEST, null, null);
			}

			Response gstUploadFile = Utility.uploadFile(gstFile, "GST_FILE");
			if(gstUploadFile.getStatus()==200){
				FileUploadPathDto fileUploadPathDto=	 (FileUploadPathDto) gstUploadFile.getObject();
				consumerApplication.setGstFilePath(fileUploadPathDto.getFilePath());  
			}
			else {
				return gstUploadFile;
			}
			consumerApplication.setConsumerApplicationId(createApplicationIdBySchemeType);
			consumerApplication.setIvrsNo(ivrsNo);
			consumerApplication.setNatureOfWorkId(consumerApplicationDtoParse.getNatureOfWorkId());
			consumerApplication.setHt11KV(consumerApplicationDtoParse.getHt11KV());
			consumerApplication.setHt132KV(consumerApplicationDtoParse.getHt132KV());
			consumerApplication.setHt33KV(consumerApplicationDtoParse.getHt33KV());
			consumerApplication.setDtr( consumerApplicationDtoParse.getDtr());
			consumerApplication.setLt(consumerApplicationDtoParse.getLt());
			consumerApplication.setSchemeTypeId(consumerApplicationDtoParse.getSchemeTypeId());
			consumerApplication.setConsumerId(consumerApplicationDtoParse.getConsumerId());
			consumerApplication.setGuardianName(consumerApplicationDtoParse.getGuardianName());
			consumerApplication.setAddress(consumerApplicationDtoParse.getAddress());
			consumerApplication.setPincode(consumerApplicationDtoParse.getPincode());
			consumerApplication.setDistrictId(consumerApplicationDtoParse.getDistrictId());
			consumerApplication.setDcId( consumerApplicationDtoParse.getDcId());
			consumerApplication.setWorkLocationAddr(consumerApplicationDtoParse.getWorkLocationAddr());
			consumerApplication.setDescriptionOfWork(consumerApplicationDtoParse.getDescriptionOfWork());
			consumerApplication.setGstNo(consumerApplicationDtoParse.getGstNo());
			consumerApplication.setCreatedTime(LocalDateTime.now().toString());
			consumerApplication.setIsActive(true);
			ConsumerApplication saveConsumerApplication = consumerApplicationRepository.save(consumerApplication);
			return Response.response("Submit sucessfully", HttpStatus.OK, saveConsumerApplication, null);

		} catch (Exception  e) {	
			throw e;
		}		
	}

	private Response setNewServiceConnection(ConsumerApplicationDto consumerApplicationDtoParse, ConsumerApplication consumerApplication, String createApplicationIdBySchemeType, MultipartFile gstFile) throws Exception {

		try {
			Long loadRequested = consumerApplicationDtoParse.getLoadRequested();	  
			if(loadRequested==null) {
				return Response.response("Load requested should not be null", HttpStatus.BAD_REQUEST, loadRequested, null);	  
			}
			Long loadUnitId = consumerApplicationDtoParse.getLoadUnitId();

			if(loadUnitId==null) {
				return Response.response("Load unit id should not be null", HttpStatus.BAD_REQUEST, loadUnitId, null);
			}

			String landArea = consumerApplicationDtoParse.getLandArea();
			if(landArea==null) {
				return Response.response("Land area should not be null", HttpStatus.BAD_REQUEST, landArea, null);
			}

			Long landAreaUnitId = consumerApplicationDtoParse.getLandAreaUnitId();        	  
			if(landAreaUnitId==null) {
				return Response.response("Land area unit should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId, null);	  
			} 
			Response gstUploadFile = Utility.uploadFile(gstFile, "GST_FILE");
			if(gstUploadFile.getStatus()==200){
				FileUploadPathDto fileUploadPathDto=	 (FileUploadPathDto) gstUploadFile.getObject();
				consumerApplication.setGstFilePath(fileUploadPathDto.getFilePath());  
			}
			else {
				return gstUploadFile;
			}
			consumerApplication.setConsumerApplicationId(createApplicationIdBySchemeType);
			consumerApplication.setNatureOfWorkId(consumerApplicationDtoParse.getNatureOfWorkId());
			consumerApplication.setLoadRequested(loadRequested);
			consumerApplication.setLoadUnitId(loadUnitId);
			consumerApplication.setSchemeTypeId(consumerApplicationDtoParse.getSchemeTypeId());
			consumerApplication.setConsumerId(consumerApplicationDtoParse.getConsumerId());
			consumerApplication.setGuardianName(consumerApplicationDtoParse.getGuardianName());
			consumerApplication.setAddress(consumerApplicationDtoParse.getAddress());
			consumerApplication.setLandArea(landArea);
			consumerApplication.setLandAreaUnitId(landAreaUnitId);
			consumerApplication.setPincode(consumerApplicationDtoParse.getPincode());
			consumerApplication.setDistrictId(consumerApplicationDtoParse.getDistrictId());
			consumerApplication.setDcId( consumerApplicationDtoParse.getDcId());
			consumerApplication.setWorkLocationAddr(consumerApplicationDtoParse.getWorkLocationAddr());
			consumerApplication.setDescriptionOfWork(consumerApplicationDtoParse.getDescriptionOfWork());
			consumerApplication.setGstNo(consumerApplicationDtoParse.getGstNo());
			consumerApplication.setCreatedTime(LocalDateTime.now().toString());
			consumerApplication.setIsActive(true);
			ConsumerApplication saveConsumerApplication = consumerApplicationRepository.save(consumerApplication);
			return Response.response("Submit sucessfully", HttpStatus.OK, saveConsumerApplication, null);

		} catch (Exception  e) {	
			throw e;
		}
	}

	private Response setColonyElectrificationLegal(ConsumerApplicationDto consumerApplicationDtoParse,
			ConsumerApplication consumerApplication, String createApplicationIdBySchemeType, MultipartFile gstFile,MultipartFile tAndCPpermissionFile,MultipartFile reraFile, MultipartFile groupPermissionFile) throws Exception {

		try {
			Long loadRequested = consumerApplicationDtoParse.getLoadRequested();	  
			if(loadRequested==null) {
				return Response.response("Load requested should not be null", HttpStatus.BAD_REQUEST, loadRequested, null);	  
			}
			Long loadUnitId = consumerApplicationDtoParse.getLoadUnitId();

			if(loadUnitId==null) {
				return Response.response("Load unit id should not be null", HttpStatus.BAD_REQUEST, loadUnitId, null);
			}

			String landArea = consumerApplicationDtoParse.getLandArea();
			if(landArea==null) {
				return Response.response("Land area should not be null", HttpStatus.BAD_REQUEST, landArea, null);
			}

			Long landAreaUnitId = consumerApplicationDtoParse.getLandAreaUnitId();        	  
			if(landAreaUnitId==null) {
				return Response.response("Land area unit should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId, null);
			}
			String noOfPlots = consumerApplicationDtoParse.getNoOfPlots();
			if(noOfPlots==null) {
				return Response.response("Number of plots should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId, null);

			}
			Long applyTypeId = consumerApplicationDtoParse.getApplyTypeId();

			if(applyTypeId==null) {
				return Response.response("Apply type id should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId, null); 
			}

			if(tAndCPpermissionFile==null) {
				return Response.response("T&CP permission file should not be null", HttpStatus.BAD_REQUEST, null, null);

			}

			if(reraFile==null) {
				return Response.response("Rera permission file should not be null", HttpStatus.BAD_REQUEST, null, null);

			}

			if(applyTypeId.equals(2L) && groupPermissionFile==null) {
				return Response.response("Group permission file file should not be null", HttpStatus.BAD_REQUEST, null, null);
			}

			Response tAndCpUploadFile = Utility.uploadFile(tAndCPpermissionFile, "T_and_CP_FILE");
			if(tAndCpUploadFile.getStatus()==200){
				FileUploadPathDto fileUploadPathDto=	 (FileUploadPathDto) tAndCpUploadFile.getObject();
				consumerApplication.setTAndCPpermissionFilePath(fileUploadPathDto.getFilePath());  
			}
			else {
				return tAndCpUploadFile;
			}

			Response reraUploadFile = Utility.uploadFile(reraFile, "RERA_FILE");
			if(reraUploadFile.getStatus()==200){
				FileUploadPathDto fileUploadPathDto=	 (FileUploadPathDto) reraUploadFile.getObject();
				consumerApplication.setReraPermissionFilePath(fileUploadPathDto.getFilePath());  
			}
			else {
				return reraUploadFile;
			} 

			if(applyTypeId.equals(2L)) {

				Response groupUploadFile = Utility.uploadFile(groupPermissionFile, "GROUP_FILE");
				if(groupUploadFile.getStatus()==200){
					FileUploadPathDto fileUploadPathDto=	 (FileUploadPathDto) groupUploadFile.getObject();
					consumerApplication.setGrouppermissionFilePath(fileUploadPathDto.getFilePath());  
				}
				else {
					return groupUploadFile;
				}  		  
			}  	  
			Response gstUploadFile = Utility.uploadFile(gstFile, "GST_FILE");
			if(gstUploadFile.getStatus()==200){
				FileUploadPathDto fileUploadPathDto=	 (FileUploadPathDto) gstUploadFile.getObject();
				consumerApplication.setGstFilePath(fileUploadPathDto.getFilePath());  
			}
			else {
				return gstUploadFile;
			}		  
			consumerApplication.setConsumerApplicationId(createApplicationIdBySchemeType);	
			consumerApplication.setNatureOfWorkId(consumerApplicationDtoParse.getNatureOfWorkId());
			consumerApplication.setLoadRequested(loadRequested);
			consumerApplication.setLoadUnitId(loadUnitId);
			consumerApplication.setSchemeTypeId(consumerApplicationDtoParse.getSchemeTypeId());
			consumerApplication.setConsumerId(consumerApplicationDtoParse.getConsumerId());
			consumerApplication.setGuardianName(consumerApplicationDtoParse.getGuardianName());
			consumerApplication.setAddress(consumerApplicationDtoParse.getAddress());
			consumerApplication.setLandArea(landArea);
			consumerApplication.setLandAreaUnitId(landAreaUnitId);
			consumerApplication.setPincode(consumerApplicationDtoParse.getPincode());
			consumerApplication.setDistrictId(consumerApplicationDtoParse.getDistrictId());
			consumerApplication.setDcId( consumerApplicationDtoParse.getDcId());
			consumerApplication.setWorkLocationAddr(consumerApplicationDtoParse.getWorkLocationAddr());
			consumerApplication.setDescriptionOfWork(consumerApplicationDtoParse.getDescriptionOfWork());
			consumerApplication.setGstNo(consumerApplicationDtoParse.getGstNo());
			consumerApplication.setCreatedTime(LocalDateTime.now().toString());
			consumerApplication.setIsActive(true);
			ConsumerApplication saveConsumerApplication = consumerApplicationRepository.save(consumerApplication);
			return Response.response("Submit sucessfully", HttpStatus.OK, saveConsumerApplication, null);

		} catch (Exception  e) {		
			throw e;
		}		
	}

	private Response setColonyElectrificationIllegal(ConsumerApplicationDto consumerApplicationDtoParse,
			ConsumerApplication consumerApplication, String createApplicationIdBySchemeType, MultipartFile gstFile,MultipartFile registryFile,MultipartFile nOCfile, MultipartFile groupPermissionFile) throws Exception {

		try {

			Long loadRequested = consumerApplicationDtoParse.getLoadRequested();	  
			if(loadRequested==null) {
				return Response.response("Load requested should not be null", HttpStatus.BAD_REQUEST, loadRequested, null);  
			}
			Long loadUnitId = consumerApplicationDtoParse.getLoadUnitId();      	  
			if(loadUnitId==null) {
				return Response.response("Load unit id should not be null", HttpStatus.BAD_REQUEST, loadUnitId, null);
			}

			String landArea = consumerApplicationDtoParse.getLandArea();
			if(landArea==null) {
				return Response.response("Land area should not be null", HttpStatus.BAD_REQUEST, landArea, null);  
			}

			Long landAreaUnitId = consumerApplicationDtoParse.getLandAreaUnitId();        	  
			if(landAreaUnitId==null) {
				return Response.response("Land area unit should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId, null);	  
			}
			String noOfPlots = consumerApplicationDtoParse.getNoOfPlots();
			if(noOfPlots==null) {
				return Response.response("Number of plots should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId, null);  	  
			}
			Long applyTypeId = consumerApplicationDtoParse.getApplyTypeId();

			if(applyTypeId==null) {
				return Response.response("Apply type id should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId, null);
			}

			if(registryFile==null) {
				return Response.response("Registry permission file should not be null", HttpStatus.BAD_REQUEST, null, null);
			}

			if(nOCfile==null) {
				return Response.response("NOC permission file should not be null", HttpStatus.BAD_REQUEST, null, null);

			}

			if(applyTypeId.equals(2L) && groupPermissionFile==null) {
				return Response.response("Group permission file file should not be null", HttpStatus.BAD_REQUEST, null, null); 
			}

			Response registryUploadFile = Utility.uploadFile(registryFile, "REGISTRY_FILE");
			if(registryUploadFile.getStatus()==200){
				FileUploadPathDto fileUploadPathDto=	 (FileUploadPathDto) registryUploadFile.getObject();
				consumerApplication.setRegistryFilePath(fileUploadPathDto.getFilePath());  
			}
			else {
				return registryUploadFile;
			}

			Response nocUploadFile = Utility.uploadFile(nOCfile, "NOC_FILE");
			if(nocUploadFile.getStatus()==200){
				FileUploadPathDto fileUploadPathDto=	 (FileUploadPathDto) nocUploadFile.getObject();
				consumerApplication.setNOCfilePath(fileUploadPathDto.getFilePath());  
			}
			else {
				return nocUploadFile;
			} 	 
			if(applyTypeId.equals(2L)) {

				Response groupUploadFile = Utility.uploadFile(groupPermissionFile, "GROUP_FILE");
				if(groupUploadFile.getStatus()==200){
					FileUploadPathDto fileUploadPathDto=	 (FileUploadPathDto) groupUploadFile.getObject();
					consumerApplication.setGrouppermissionFilePath(fileUploadPathDto.getFilePath());  
				}
				else {
					return groupUploadFile;
				}        		  
			}

			Response gstUploadFile = Utility.uploadFile(gstFile, "GST_FILE");
			if(gstUploadFile.getStatus()==200){
				FileUploadPathDto fileUploadPathDto=	 (FileUploadPathDto) gstUploadFile.getObject();
				consumerApplication.setGstFilePath(fileUploadPathDto.getFilePath());  
			}
			else {
				return gstUploadFile;
			}
			consumerApplication.setConsumerApplicationId(createApplicationIdBySchemeType);
			consumerApplication.setNatureOfWorkId(consumerApplicationDtoParse.getNatureOfWorkId());
			consumerApplication.setLoadRequested(loadRequested);
			consumerApplication.setLoadUnitId(loadUnitId);
			consumerApplication.setSchemeTypeId(consumerApplicationDtoParse.getSchemeTypeId());
			consumerApplication.setConsumerId(consumerApplicationDtoParse.getConsumerId());
			consumerApplication.setGuardianName(consumerApplicationDtoParse.getGuardianName());
			consumerApplication.setAddress(consumerApplicationDtoParse.getAddress());
			consumerApplication.setLandArea(landArea);
			consumerApplication.setLandAreaUnitId(landAreaUnitId);
			consumerApplication.setPincode(consumerApplicationDtoParse.getPincode());
			consumerApplication.setDistrictId(consumerApplicationDtoParse.getDistrictId());
			consumerApplication.setDcId( consumerApplicationDtoParse.getDcId());
			consumerApplication.setWorkLocationAddr(consumerApplicationDtoParse.getWorkLocationAddr());
			consumerApplication.setDescriptionOfWork(consumerApplicationDtoParse.getDescriptionOfWork());
			consumerApplication.setGstNo(consumerApplicationDtoParse.getGstNo());
			consumerApplication.setCreatedTime(LocalDateTime.now().toString());
			consumerApplication.setIsActive(true);
			ConsumerApplication saveConsumerApplication = consumerApplicationRepository.save(consumerApplication);
			return Response.response("Submit sucessfully", HttpStatus.OK, saveConsumerApplication, null);

		} catch (Exception  e) {			
			throw e;
		}		
	}

	private Response setOYT(ConsumerApplicationDto consumerApplicationDtoParse,
			ConsumerApplication consumerApplication, String createApplicationIdBySchemeType, MultipartFile gstFile,MultipartFile khasraKhatoniFile) throws Exception {

		try {
			String khasra = consumerApplicationDtoParse.getKhasra();	  
			if(khasra==null) {
				return Response.response("Khasra should not be null", HttpStatus.BAD_REQUEST, khasra, null);

			}
			String khatoni = consumerApplicationDtoParse.getKhatoni();        	  
			if(khatoni==null) {
				return Response.response("Khatoni should not be null", HttpStatus.BAD_REQUEST, khatoni, null);
			}

			String landArea = consumerApplicationDtoParse.getLandArea();
			if(landArea==null) {
				return Response.response("Land area should not be null", HttpStatus.BAD_REQUEST, landArea, null);	  
			} 	  
			Long landAreaUnitId = consumerApplicationDtoParse.getLandAreaUnitId();

			if(landAreaUnitId==null) {
				return Response.response("Land area unit should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId, null);	  
			}	  

			if(khasraKhatoniFile==null) {
				return Response.response("Khasra-Khatoni file should not be null", HttpStatus.BAD_REQUEST, null, null);  
			}

			Response khasraKhatoniUploadFile = Utility.uploadFile(khasraKhatoniFile, "KHASRA-KHATONI_FILE");
			if(khasraKhatoniUploadFile.getStatus()==200){
				FileUploadPathDto fileUploadPathDto=	 (FileUploadPathDto) khasraKhatoniUploadFile.getObject();
				consumerApplication.setKhasraKhatoniFilePath(fileUploadPathDto.getFilePath());  
			}
			else {
				return khasraKhatoniUploadFile;
			}        	  
			Response gstUploadFile = Utility.uploadFile(gstFile, "GST_FILE");
			if(gstUploadFile.getStatus()==200){
				FileUploadPathDto fileUploadPathDto=	 (FileUploadPathDto) gstUploadFile.getObject();
				consumerApplication.setGstFilePath(fileUploadPathDto.getFilePath());  
			}
			else {
				return gstUploadFile;
			}
			consumerApplication.setConsumerApplicationId(createApplicationIdBySchemeType);
			consumerApplication.setNatureOfWorkId(consumerApplicationDtoParse.getNatureOfWorkId());
			consumerApplication.setSchemeTypeId(consumerApplicationDtoParse.getSchemeTypeId());
			consumerApplication.setConsumerId(consumerApplicationDtoParse.getConsumerId());
			consumerApplication.setGuardianName(consumerApplicationDtoParse.getGuardianName());
			consumerApplication.setKhasra(consumerApplicationDtoParse.getKhasra());
			consumerApplication.setKhatoni(consumerApplicationDtoParse.getKhatoni());
			consumerApplication.setAddress(consumerApplicationDtoParse.getAddress());
			consumerApplication.setLandArea(landArea);
			consumerApplication.setLandAreaUnitId(landAreaUnitId);
			consumerApplication.setPincode(consumerApplicationDtoParse.getPincode());
			consumerApplication.setDistrictId(consumerApplicationDtoParse.getDistrictId());
			consumerApplication.setDcId( consumerApplicationDtoParse.getDcId());
			consumerApplication.setWorkLocationAddr(consumerApplicationDtoParse.getWorkLocationAddr());
			consumerApplication.setDescriptionOfWork(consumerApplicationDtoParse.getDescriptionOfWork());
			consumerApplication.setGstNo(consumerApplicationDtoParse.getGstNo());
			consumerApplication.setCreatedTime(LocalDateTime.now().toString());
			consumerApplication.setIsActive(true);
			ConsumerApplication saveConsumerApplication = consumerApplicationRepository.save(consumerApplication);
			return Response.response("Submit sucessfully", HttpStatus.OK, saveConsumerApplication, null);

		} catch (Exception  e) {			
			throw e;
		}		
	}

	private String createApplicationIdBySchemeType(Long schemeTypeId) {
		String localDateTime = LocalDateTime.now().toString();//2023-07-11T10:31:28.279
		String localDateTimeReplace = localDateTime.replaceAll("\\-|\\:|[a-zA-z]|\\.", "");
		String substringDateTime = localDateTimeReplace.substring(0,localDateTimeReplace.length()-3);
		String consumerApplicationId;	  

		if(schemeTypeId==1L) {
			return consumerApplicationId=	"DP"+substringDateTime;
		}
		if(schemeTypeId==2L) {
			return	consumerApplicationId=	"DS"+substringDateTime;

		}       
		if(schemeTypeId==3L) {
			return	consumerApplicationId=	"SV"+substringDateTime;
		}      
		else {
			return null;
		}
	}

	public Response  checkValidation(ConsumerApplicationDto consumerApplicationDtoParse, MultipartFile gstFile) throws Exception{

		try {
			Long schemeTypeId = consumerApplicationDtoParse.getSchemeTypeId();					 
			if(schemeTypeId==null) {
				return Response.response("Scheme type id should not be null", HttpStatus.BAD_REQUEST, null, null);
			}    	
			Long natureOfWorkId = consumerApplicationDtoParse.getNatureOfWorkId();
			if(natureOfWorkId==null) {
				return Response.response("Nature of work id should not be null", HttpStatus.BAD_REQUEST, natureOfWorkId, null);

			}
			if(natureOfWorkId>6) {
				return Response.response("Nature of work id incorrect", HttpStatus.BAD_REQUEST, natureOfWorkId, null);
			}				
			Long consumerId = consumerApplicationDtoParse.getConsumerId();
			if(consumerId==null) {
				return Response.response("Consumer id should not be null", HttpStatus.BAD_REQUEST, consumerId, null);
			}

			Long districtId = consumerApplicationDtoParse.getDistrictId();
			if(districtId==null) {
				return Response.response("District id should not be null", HttpStatus.BAD_REQUEST, districtId, null);
			}

			Long pincode = consumerApplicationDtoParse.getPincode();				
			if(pincode==null) {
				return Response.response("Pin code should not be null", HttpStatus.BAD_REQUEST, districtId, null);
			}

			Boolean checkedWorkLocation = consumerApplicationDtoParse.getCheckedWorkLocation();				
			if(checkedWorkLocation==null) {
				return Response.response("Checked box for work location is null", HttpStatus.BAD_REQUEST, null, null);
			}

			String workLocationAddr = consumerApplicationDtoParse.getWorkLocationAddr();				
			if(checkedWorkLocation.equals(false) && workLocationAddr!=null ) {
				return Response.response("You are doing wrong because you did not check the condition for work location address details and you are insert details", HttpStatus.BAD_REQUEST, workLocationAddr, null);
			}

			if(checkedWorkLocation.equals(true) && workLocationAddr==null ) {
				return Response.response("Work location address should not be null", HttpStatus.BAD_REQUEST, null, null);
			}

			String descriptionOfWork = consumerApplicationDtoParse.getDescriptionOfWork();
			if( descriptionOfWork==null ) {
				return Response.response("Short descripton of work should not be null", HttpStatus.BAD_REQUEST, descriptionOfWork, null);
			}      
			String guardianName = consumerApplicationDtoParse.getGuardianName();
			if(guardianName==null ) {
				return Response.response("Guardian name should not be null", HttpStatus.BAD_REQUEST, guardianName, null);
			}
			Boolean checkedGSTfile = consumerApplicationDtoParse.getCheckedGSTfile();
			if(checkedGSTfile==null) {
				return Response.response("Checked box for GST is null", HttpStatus.BAD_REQUEST, null, null);
			}
			String gstNo = consumerApplicationDtoParse.getGstNo();

			if( checkedGSTfile.equals(true) && gstFile==null  ) {
				return Response.response("GST file should not be null", HttpStatus.BAD_REQUEST, null, null);
			}  

			if( checkedGSTfile.equals(true) && gstNo==null  ||  checkedGSTfile.equals(true) && gstNo.isEmpty()) {
				return Response.response("GST number should not be null", HttpStatus.BAD_REQUEST, null, null);
			}  

			if(checkedGSTfile.equals(false) && gstFile!=null  || checkedGSTfile.equals(false) && gstNo!=null ||  checkedGSTfile.equals(false) && !gstNo.isEmpty()) {
				return Response.response("You are doing wrong because you did not check the condition for GST details and you are insert details", HttpStatus.BAD_REQUEST, null, null);
			}  
			String address = consumerApplicationDtoParse.getAddress();
			if( address==null) {
				return Response.response("Address should not be null", HttpStatus.BAD_REQUEST, address, null);
			}
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}	
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public Response update(String consumerApplicationDto, MultipartFile tAndCPpermissionFile,
			MultipartFile reraPermissionFile, MultipartFile groupPermissionFile, MultipartFile registryFile,
			MultipartFile nOCfile, MultipartFile administrativeFile, MultipartFile gstFile,
			MultipartFile khasraKhatoniFile) {

		try {
			if(consumerApplicationDto!=null) {

				ObjectMapper objectMapper = new ObjectMapper();

				ConsumerApplicationDto consumerApplicationDtoParse = objectMapper.readValue(consumerApplicationDto, ConsumerApplicationDto.class);

				Response checkValidation = checkValidation(consumerApplicationDtoParse,gstFile);

				if(checkValidation==null) {	
					Long natureOfWorkId = consumerApplicationDtoParse.getNatureOfWorkId();

					Long consumerId = consumerApplicationDtoParse.getConsumerId();
					String consumerApplicationId = consumerApplicationDtoParse.getConsumerApplicationId();

					if(consumerApplicationId==null) {
						return Response.response("Consumer application id should not be null", HttpStatus.BAD_REQUEST, consumerApplicationId, null);
					}
					ConsumerApplication consumerApplication = consumerApplicationRepository.getConsumerApplicationByConsumerIdAndConsumerApplicationNumber(consumerId,consumerApplicationId);

					if(consumerApplication==null) {
						return Response.response("Data not found for this application id", HttpStatus.NOT_FOUND, consumerApplicationId, null);
					}					
					return checkNatureOfWork(natureOfWorkId,consumerApplicationDtoParse,consumerApplication,consumerApplicationId, tAndCPpermissionFile,
							reraPermissionFile,  groupPermissionFile,  registryFile,
							nOCfile,  administrativeFile,  gstFile,
							khasraKhatoniFile);				
				}
				else {
					return checkValidation;
				}
			} 
			return Response.response("consumerApplicationDto should be not null", HttpStatus.BAD_REQUEST, null, null);
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);
		}
	}

	private Response checkNatureOfWork(Long natureOfWorkId, ConsumerApplicationDto consumerApplicationDtoParse, ConsumerApplication consumerApplication, String consumerApplicationId, MultipartFile tAndCPpermissionFile, MultipartFile reraPermissionFile, MultipartFile groupPermissionFile, MultipartFile registryFile, MultipartFile nOCfile, MultipartFile administrativeFile, MultipartFile gstFile, MultipartFile khasraKhatoniFile) throws Exception{

		if(natureOfWorkId.equals(1L)) {			
			return setLineShiftingGov(consumerApplicationDtoParse,consumerApplication,consumerApplicationId,administrativeFile,gstFile);	    	
		}			    
		if(natureOfWorkId.equals(2L)) {			    	
			return	setLineShiftingNonGov	(consumerApplicationDtoParse,consumerApplication,consumerApplicationId,gstFile);			    	
		}           
		if(natureOfWorkId.equals(3L)) {			    	
			return setNewServiceConnection(consumerApplicationDtoParse,consumerApplication,consumerApplicationId,gstFile);			    	
		}			    
		if(natureOfWorkId.equals(4L)) {			    	
			return	setColonyElectrificationLegal(consumerApplicationDtoParse,consumerApplication,consumerApplicationId,gstFile,tAndCPpermissionFile,reraPermissionFile,groupPermissionFile);			    	
		}          
		if(natureOfWorkId.equals(5L)) {
			return	setColonyElectrificationIllegal(consumerApplicationDtoParse,consumerApplication,consumerApplicationId,gstFile,registryFile,nOCfile,groupPermissionFile);
		}
		if(natureOfWorkId.equals(6L)) {
			return	setOYT(consumerApplicationDtoParse,consumerApplication,consumerApplicationId,gstFile,khasraKhatoniFile);    	
		}
		return null;
	}

	@Override
	public Response pendingForGeoLocationApplication(String mobileNo) {

		Consumer consumer = consumerRepository.findByMobileNumber(mobileNo);
		if(consumer == null) {
			return Response.response("Consumer Not Found In This Mobile Number", 
					HttpStatus.NOT_FOUND, null, null);
		}

		List<ConsumerApplication> list = consumerApplicationRepository.findByConsumerId(consumer.getConsumerId());
		List<PendingForGeoLocationApplicationDto> dtos = new ArrayList<>();

		if(list.isEmpty()) {
			return Response.response("No Application For This Consumer Number", 
					HttpStatus.NOT_FOUND, null, null);
		}

		list.forEach(l->{

			if(l.getApplicationStatusId()==4L) {
				PendingForGeoLocationApplicationDto applicationDto = new PendingForGeoLocationApplicationDto();
				applicationDto.setConsumerApplicationId(l.getConsumerApplicationId());
				applicationDto.setApplicationStatus(applicationStatusRepository.findById(l.getApplicationStatusId()).get().getApplicationStatusName());

				dtos.add(applicationDto);
			}
		});

		if(dtos.isEmpty()) {
			return Response.response("No Application Pending for GeoLocation", 
					HttpStatus.NOT_FOUND, null, null);
		}
		
		return Response.response("Pending Application For GeoLocation in This Consumer Number", 
				HttpStatus.OK, dtos, null);
	}
}
