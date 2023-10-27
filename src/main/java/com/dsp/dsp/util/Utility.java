package com.dsp.dsp.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.Base64.Encoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.dsp.dsp.dto.ConsumerApplicationsResponseDto;
import com.dsp.dsp.dto.FileUploadPathDto;
import com.dsp.dsp.model.ApplicationStatus;
import com.dsp.dsp.model.ApplyType;
import com.dsp.dsp.model.Circle;
import com.dsp.dsp.model.Consumer;
import com.dsp.dsp.model.ConsumerApplication;
import com.dsp.dsp.model.Dc;
import com.dsp.dsp.model.Discom;
import com.dsp.dsp.model.District;
import com.dsp.dsp.model.Division;
import com.dsp.dsp.model.LandAreaUnit;
import com.dsp.dsp.model.LoadUnit;
import com.dsp.dsp.model.NatureOfWork;
import com.dsp.dsp.model.Region;
import com.dsp.dsp.model.SchemeType;
import com.dsp.dsp.model.SubDivision;
import com.dsp.dsp.repository.ApplicationStatusRepository;
import com.dsp.dsp.repository.ApplyTypeRepository;
import com.dsp.dsp.repository.CircleRepository;
import com.dsp.dsp.repository.ConsumerRepository;
import com.dsp.dsp.repository.DcRepository;
import com.dsp.dsp.repository.DiscomRepository;
import com.dsp.dsp.repository.DistrictRepository;
import com.dsp.dsp.repository.DivisionRepository;
import com.dsp.dsp.repository.LandAreaUnitRepository;
import com.dsp.dsp.repository.LoadUnitRepository;
import com.dsp.dsp.repository.NatureOfWorkRepository;
import com.dsp.dsp.repository.RegionRepository;
import com.dsp.dsp.repository.SchemeTypeRepository;
import com.dsp.dsp.repository.SubDivisionRepository;
import com.dsp.dsp.repository.SupplyVoltageRepository;
import com.dsp.dsp.response.Response;

@Component
public class Utility {
	
	@Autowired
	NatureOfWorkRepository natureOfWorkRepository;

	@Autowired
	SupplyVoltageRepository supplyVoltageRepository;

	@Autowired
	SchemeTypeRepository schemeTypeRepository;

	@Autowired
	LoadUnitRepository loadUnitRepository;

	@Autowired
	LandAreaUnitRepository LandAreaUnitRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	DcRepository dcRepository;

	@Autowired
	ApplyTypeRepository applyTypeRepository;

	@Autowired
	RegionRepository regionRepository;

	@Autowired
	CircleRepository circleRepository;

	@Autowired
	DivisionRepository divisionRepository;

	@Autowired
	SubDivisionRepository subDivisionRepository;
	
	@Autowired
	DiscomRepository discomRepository;
	
	@Autowired
	ConsumerRepository consumerRepository ;

	@Autowired
	ApplicationStatusRepository applicationStatusRepository;

	
	public static  String getEncodeData(String decryptData) throws Exception {
		Encoder encoder = Base64.getEncoder();
		String encode = encoder.encodeToString(decryptData.getBytes());
		return encode;
	}
	
	public static  String getDecryptData(String encryptData) throws Exception {
		byte[] decode = Base64.getDecoder().decode(encryptData);
		String decryptCode = new String(decode);
		return decryptCode;
	}

	
      public static Response uploadFile(MultipartFile file,String fileName) throws Exception {
		
		System.out.println("inside uploadFile method");
		
		try {
			System.out.println(file.getContentType());
			if(file.isEmpty()) {
	            return Response.response("Upload file was empty", HttpStatus.BAD_REQUEST, null, null);	
			}
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            double rand=  Math.random();
            String random=String.valueOf(rand);
            random=random.substring(random.indexOf(".")+1);
            
           //For Local
         //  Path path = Paths.get("C:\\dspUploadFile\\"+fileName+"_"+random+"_"+file.getOriginalFilename());

            //For Rooftop
            Path path = Paths.get("D:\\DSP-pdf-upload\\"+fileName+"_"+random+"_"+file.getOriginalFilename());

            Path filePath = Files.write(path, bytes);
            FileUploadPathDto filePathDto = new FileUploadPathDto();
            filePathDto.setFilePath(filePath.toString());
            return Response.response("File upload successfully", HttpStatus.OK, filePathDto, null);
            
        } catch (Exception e) {
        	throw e;
        }
		
    }
      
     public static String getRandomNumber() {
    	  String localDateTime = LocalDateTime.now().toString();//2023-07-11T10:31:28.279
  		String localDateTimeReplace = localDateTime.replaceAll("\\-|\\:|[a-zA-z]|\\.", "");
      
  		return localDateTimeReplace;
      }
     
     public static String getUserIPAddress(HttpServletRequest request) {
 		String ipAddress = request.getHeader("X-Forwarded-For");

 		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
 			ipAddress = request.getHeader("Proxy-Client-IP");
 		}
 		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
 			ipAddress = request.getHeader("WL-Proxy-Client-IP");
 		}
 		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
 			ipAddress = request.getRemoteAddr();
 		}
 		System.err.println("IP" + ipAddress);
 		return ipAddress;
 	}
     
     
     public ConsumerApplicationsResponseDto consumerApplicationSetDto(ConsumerApplication consumerApplication) throws Exception{
 		
    	 ConsumerApplicationsResponseDto responseDTO = new ConsumerApplicationsResponseDto();
    	
    	
			if(consumerApplication.getConsumerId()!=null){
					Consumer consumer= consumerRepository.getConsumerDetailByConsumerId(consumerApplication.getConsumerId());
					responseDTO.setConsumerName(consumer==null ? null : consumer.getConsumerName());
				}
				responseDTO.setSr_No(consumerApplication.getSr_No());
				responseDTO.setConsumerApplicationId(consumerApplication.getConsumerApplicationId());
				if (consumerApplication.getNatureOfWorkId() != null) {
					Optional<NatureOfWork> natureOfWork = natureOfWorkRepository.findById(consumerApplication.getNatureOfWorkId());
				responseDTO.setNatureOfWork(natureOfWork.isPresent()?natureOfWork.get().getNatureOfWorkName():null);
				}
				responseDTO.setDtr(consumerApplication.getDtr());
				responseDTO.setHt11KV(consumerApplication.getHt11KV());
				responseDTO.setHt132KV(consumerApplication.getHt132KV());
				responseDTO.setHt33KV(consumerApplication.getHt33KV());
				responseDTO.setLt(consumerApplication.getLt());
				responseDTO.setPtr(consumerApplication.getPtr());
				
				if (consumerApplication.getSchemeTypeId() != null) {
					Optional<SchemeType> schemeType = schemeTypeRepository.findById(consumerApplication.getSchemeTypeId());
				responseDTO.setSchemeType(schemeType.isPresent()?schemeType.get().getSchemeTypeName():null);
				}
				responseDTO.setConsumerId(consumerApplication.getConsumerId());
				responseDTO.setGuardianName(consumerApplication.getGuardianName());
				responseDTO.setAddress(consumerApplication.getAddress());
				responseDTO.setWorkLocationAddr(consumerApplication.getWorkLocationAddr());
				responseDTO.setPincode(consumerApplication.getPincode());
				
				if (consumerApplication.getDistrictId() != null) {
					Optional<District> district = districtRepository.findById(consumerApplication.getDistrictId());
					responseDTO.setDistrict(district.isPresent()?district.get().getDistrictName():null);
				}
				if (consumerApplication.getDcId() != null) {
					Optional<Dc> dc = dcRepository.findById(consumerApplication.getDcId());
					responseDTO.setDcId(dc.get().getDcId());
				responseDTO.setDcName(dc.isPresent()?dc.get().getDcName():null);
				}
				responseDTO.setDescriptionOfWork(consumerApplication.getDescriptionOfWork());
				responseDTO.setAdministrativeFilePath(consumerApplication.getAdministrativeFilePath());
				responseDTO.setGstNo(consumerApplication.getGstNo());
				responseDTO.setGstFilePath(consumerApplication.getGstFilePath());
				responseDTO.setIvrsNo(consumerApplication.getIvrsNo());
				responseDTO.setLoadRequested(consumerApplication.getLoadRequested());
				
				if (consumerApplication.getLoadUnitId() != null) {
					Optional<LoadUnit> loadUnit = loadUnitRepository.findById(consumerApplication.getLoadUnitId());
				
					responseDTO.setLoadUnit(loadUnit.isPresent()?loadUnit.get().getLoadUnitName():null);
				}
				//if (consumerApplication.getLandAreaUnitId() != null) {
					responseDTO.setLandArea(consumerApplication.getLandArea());
					responseDTO.setLandAreaUnit(consumerApplication.getLandAreaUnitId());
				//	Optional<LandAreaUnit> landAreaUnit = LandAreaUnitRepository.findById(consumerApplication.getLandAreaUnitId());
				//responseDTO.setLandAreaUnit(landAreaUnit.isPresent()?landAreaUnit.get().getLandAreaUnitName():null);
				//}
				responseDTO.setNoOfPlots(consumerApplication.getNoOfPlots());
				if (consumerApplication.getApplyTypeId() != null) {
					Optional<ApplyType> applyType = applyTypeRepository.findById(consumerApplication.getApplyTypeId());
				responseDTO.setApplyType(applyType.isPresent()?applyType.get().getApplyTypeName():null);
				}
				responseDTO.setTAndCPpermissionFilePath(consumerApplication.getTAndCPpermissionFilePath());
				responseDTO.setReraPermissionFilePath(consumerApplication.getReraPermissionFilePath());
				responseDTO.setGrouppermissionFilePath(consumerApplication.getGrouppermissionFilePath());
				responseDTO.setRegistryFilePath(consumerApplication.getRegistryFilePath());
				responseDTO.setNOCfilePath(consumerApplication.getNOCfilePath());
				responseDTO.setKhasra(consumerApplication.getKhasra());
				responseDTO.setKhatoni(consumerApplication.getKhatoni());
				responseDTO.setKhasraKhatoniFilePath(consumerApplication.getKhasraKhatoniFilePath());
				responseDTO.setCreatedTime(consumerApplication.getCreatedTime());
				responseDTO.setIsActive(consumerApplication.getIsActive());
				
				if (consumerApplication.getApplicationStatusId() != null) {
					Optional<ApplicationStatus> applicationStatus = applicationStatusRepository.findById(consumerApplication.getApplicationStatusId());
					if(applicationStatus.isPresent()) {
						responseDTO.setApplicationStatus(applicationStatus.get().getApplicationStatusName());
						responseDTO.setApplicationStatusId(applicationStatus.get().getApplicationStatusId());
					}
			}
				if (consumerApplication.getDiscomId() != null) {
				 Optional<Discom> discom = discomRepository.findById(consumerApplication.getDiscomId());
				responseDTO.setDiscomName(discom.isPresent()?discom.get().getDiscomName():null);
				}
				if (consumerApplication.getRegionId() != null) {
					 Optional<Region> region = regionRepository.findById(consumerApplication.getRegionId());
					responseDTO.setRegionName(region.isPresent()?region.get().getRegionName():null);
					}
				if (consumerApplication.getCircleId() != null) {
					Optional<Circle> circle = circleRepository.findById(consumerApplication.getCircleId());
					responseDTO.setCircleName(circle.isPresent()?circle.get().getCircleName():null);
					}
				if (consumerApplication.getDivisionId() != null) {
					Optional<Division> division = divisionRepository.findById(consumerApplication.getDivisionId());
					responseDTO.setDivisionName(division.isPresent()?division.get().getDivisionName():null);
					}
				if (consumerApplication.getSubdivisionId() != null) {
					 Optional<SubDivision> subDivision = subDivisionRepository.findById(consumerApplication.getSubdivisionId());
					responseDTO.setSubdivisionName(subDivision.isPresent()?subDivision.get().getSubDivisionName():null);
					}
				return responseDTO;
		
			
     }
}
