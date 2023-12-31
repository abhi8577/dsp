package com.dsp.dsp.serviceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dsp.dsp.dto.ApplicationRejectDto;
import com.dsp.dsp.dto.ConsumerApplicationDto;
import com.dsp.dsp.dto.ConsumerApplicationIdDto;
import com.dsp.dsp.dto.ConsumerApplicationUpdateDto;
import com.dsp.dsp.dto.ConsumerApplicationsResponseDto;
import com.dsp.dsp.dto.DiscomToDcDto;
import com.dsp.dsp.dto.DtrPtrDto;
import com.dsp.dsp.dto.FileUploadPathDto;
import com.dsp.dsp.dto.PendingForGeoLocationApplicationDto;
import com.dsp.dsp.dto.RegistrationFeePaymentDetailDto;
import com.dsp.dsp.model.ApplicationRejectedDetails;
import com.dsp.dsp.model.ApplicationStatus;
import com.dsp.dsp.model.ApplyType;
import com.dsp.dsp.model.Circle;
import com.dsp.dsp.model.Consumer;
import com.dsp.dsp.model.ConsumerApplication;
import com.dsp.dsp.model.Dc;
import com.dsp.dsp.model.Discom;
import com.dsp.dsp.model.DiscomUser;
import com.dsp.dsp.model.District;
import com.dsp.dsp.model.Division;
import com.dsp.dsp.model.GeoLocation;
import com.dsp.dsp.model.LandAreaUnit;
import com.dsp.dsp.model.LoadUnit;
import com.dsp.dsp.model.NatureOfWork;
import com.dsp.dsp.model.Region;
import com.dsp.dsp.model.SchemeType;
import com.dsp.dsp.model.SubDivision;
import com.dsp.dsp.repository.ApplicationRejectRepository;
import com.dsp.dsp.repository.ApplicationStatusRepository;
import com.dsp.dsp.repository.ApplyTypeRepository;
import com.dsp.dsp.repository.CircleRepository;
import com.dsp.dsp.repository.ConsumerApplicationRepository;
import com.dsp.dsp.repository.ConsumerRepository;
import com.dsp.dsp.repository.DcRepository;
import com.dsp.dsp.repository.DiscomRepository;
import com.dsp.dsp.repository.DiscomUserRepository;
import com.dsp.dsp.repository.DistrictRepository;
import com.dsp.dsp.repository.DivisionRepository;
import com.dsp.dsp.repository.GeoLocationRepository;
import com.dsp.dsp.repository.LandAreaUnitRepository;
import com.dsp.dsp.repository.LoadUnitRepository;
import com.dsp.dsp.repository.NatureOfWorkRepository;
import com.dsp.dsp.repository.RegionRepository;
import com.dsp.dsp.repository.SchemeTypeRepository;
import com.dsp.dsp.repository.SubDivisionRepository;
import com.dsp.dsp.repository.SupplyVoltageRepository;
import com.dsp.dsp.response.Response;
import com.dsp.dsp.service.ConsumerApplicationService;
import com.dsp.dsp.util.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class ConsumerApplicationServiceImpl implements ConsumerApplicationService {

	@Autowired
	ConsumerApplicationRepository consumerApplicationRepository;

	@Autowired
	ConsumerRepository consumerRepository;

	@Autowired
	ApplicationStatusRepository applicationStatusRepository;

	@Autowired
	GeoLocationRepository geoLocationRepository;

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
	ApplicationRejectRepository applicationRejectRepository;
	
	@Autowired
	DiscomRepository discomRepository;
	
	@Autowired
	Utility utility;

	@Override
	@org.springframework.transaction.annotation.Transactional
	public Response submit(String consumerApplicationDto, MultipartFile tAndCPpermissionFile,
			MultipartFile reraPermissionFile, MultipartFile groupPermissionFile, MultipartFile registryFile,
			MultipartFile nOCfile, MultipartFile administrativeFile, MultipartFile gstFile,
			MultipartFile khasraKhatoniFile) {

		try {
			if (consumerApplicationDto != null) {
				ObjectMapper objectMapper = new ObjectMapper();
				ConsumerApplicationDto consumerApplicationDtoParse = objectMapper.readValue(consumerApplicationDto,
						ConsumerApplicationDto.class);
				Long natureOfWorkId = consumerApplicationDtoParse.getNatureOfWorkId();
				Response checkValidation = checkValidation(consumerApplicationDtoParse, gstFile);

				if (checkValidation == null) {
					ConsumerApplication consumerApplication = new ConsumerApplication();
					Long schemeTypeId = consumerApplicationDtoParse.getSchemeTypeId();

					String createApplicationIdBySchemeType = createApplicationIdBySchemeType(schemeTypeId);

					if (createApplicationIdBySchemeType == null) {
						return Response.response("Scheme type id incorrect", HttpStatus.BAD_REQUEST, null, null);
					}
					return checkNatureOfWork(natureOfWorkId, consumerApplicationDtoParse, consumerApplication,
							createApplicationIdBySchemeType, tAndCPpermissionFile, reraPermissionFile,
							groupPermissionFile, registryFile, nOCfile, administrativeFile, gstFile, khasraKhatoniFile);
				} else {
					return checkValidation;
				}
			}
			return Response.response("consumerApplicationDto should be not null", HttpStatus.BAD_REQUEST, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);
		}
	}

	private Response setLineShiftingGov(ConsumerApplicationDto consumerApplicationDtoParse,
			ConsumerApplication consumerApplication, String createApplicationIdBySchemeType,
			MultipartFile administrativeFile, MultipartFile gstFile) throws Exception {

		try {
			Long ht11kv = null;
			Long ht132kv = null;
			Long ht33kv = null;
			Long dtr = null;
			Long lt = null;
			Long ptr = null;

			List<Long> supplyVoltageIds = consumerApplicationDtoParse.getSupplyVoltageId();
                System.out.println(supplyVoltageIds);
			for (Long id : supplyVoltageIds) {
				switch (id.intValue()) {

				case 3:
					dtr = id;
					break;
				case 4:
					ptr = id;
					break;
				case 5:
					lt = id;
					break;
				case 6:
					ht11kv = id;
					break;
				case 7:
					ht33kv = id;
					break;
				case 8:
					ht132kv = id;
					break;
				// Add more cases for other IDs if needed
				default:
					// Handle unknown IDs
					return Response.response("Supply voltage id invalid", HttpStatus.BAD_REQUEST, null, null);
				}
			}

			if (administrativeFile == null) {
				return Response.response("Administrative File should not be null", HttpStatus.BAD_REQUEST, null, null);
			}

			if (ht11kv == null && ht132kv == null && ht33kv == null && dtr == null && lt == null && ptr == null) {
				return Response.response("Supply voltage should not be null", HttpStatus.BAD_REQUEST, null, null);
			}

			Response administrativeUploadFile = Utility.uploadFile(administrativeFile, "ADMINISTRATIVE_FILE");
			if (administrativeUploadFile.getStatus() == 200) {
				FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) administrativeUploadFile.getObject();
				consumerApplication.setAdministrativeFilePath(fileUploadPathDto.getFilePath());
			} else {
				return administrativeUploadFile;
			}

			if(gstFile!=null) {
			Response gstUploadFile = Utility.uploadFile(gstFile, "GST_FILE");
			if (gstUploadFile.getStatus() == 200) {
				FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) gstUploadFile.getObject();
				consumerApplication.setGstFilePath(fileUploadPathDto.getFilePath());
			} else {
				return gstUploadFile;
			}
		}
			consumerApplication.setConsumerApplicationId(createApplicationIdBySchemeType);
			consumerApplication.setNatureOfWorkId(consumerApplicationDtoParse.getNatureOfWorkId());
			consumerApplication.setHt11KV(ht11kv);
			consumerApplication.setHt132KV(ht132kv);
			consumerApplication.setHt33KV(ht33kv);
			consumerApplication.setDtr(dtr);
			consumerApplication.setLt(lt);
			consumerApplication.setPtr(ptr);
			consumerApplication.setSchemeTypeId(consumerApplicationDtoParse.getSchemeTypeId());
			consumerApplication.setConsumerId(consumerApplicationDtoParse.getConsumerId());
			consumerApplication.setGuardianName(consumerApplicationDtoParse.getGuardianName());
			consumerApplication.setAddress(consumerApplicationDtoParse.getAddress());
			consumerApplication.setPincode(consumerApplicationDtoParse.getPincode());
			consumerApplication.setDistrictId(consumerApplicationDtoParse.getDistrictId());
			//consumerApplication.setDcId(consumerApplicationDtoParse.getDcId());
			consumerApplication.setWorkLocationAddr(consumerApplicationDtoParse.getWorkLocationAddr());
			consumerApplication.setDescriptionOfWork(consumerApplicationDtoParse.getDescriptionOfWork());
			consumerApplication.setGstNo(consumerApplicationDtoParse.getGstNo());
			consumerApplication.setCreatedTime(LocalDateTime.now().toString());
			consumerApplication.setIsActive(true);
			consumerApplication.setApplicationStatusId(4L);
			ConsumerApplication saveConsumerApplication = consumerApplicationRepository.save(consumerApplication);

			return Response.response("Submit sucessfully", HttpStatus.OK, saveConsumerApplication, null);

		} catch (Exception e) {
			throw e;
		}
	}

	private Response setLineShiftingNonGov(ConsumerApplicationDto consumerApplicationDtoParse,
			ConsumerApplication consumerApplication, String createApplicationIdBySchemeType, MultipartFile gstFile)
			throws Exception {
		try {

			String ivrsNo = consumerApplicationDtoParse.getIvrsNo();
			Long ht11kv = null;
			Long ht132kv = null;
			Long ht33kv = null;
			Long dtr = null;
			Long lt = null;
			Long ptr = null;

			List<Long> supplyVoltageIds = consumerApplicationDtoParse.getSupplyVoltageId();

			for (Long id : supplyVoltageIds) {
				switch (id.intValue()) {

				case 3:
					dtr = id;
					break;
				case 4:
					ptr = id;
					break;
				case 5:
					lt = id;
					break;
				case 6:
					ht11kv = id;
					break;
				case 7:
					ht33kv = id;
					break;
				case 8:
					ht132kv = id;
					break;
				// Add more cases for other IDs if needed
				default:
					// Handle unknown IDs
					return Response.response("Supply voltage id invalid", HttpStatus.BAD_REQUEST, null, null);
				}
			}

			if (ivrsNo == null) {
				return Response.response("Ivrs number should not be null", HttpStatus.BAD_REQUEST, ivrsNo, null);
			}

			if (ht11kv == null && ht132kv == null && ht33kv == null && dtr == null && lt == null && ptr == null) {
				return Response.response("Supply voltage should not be null", HttpStatus.BAD_REQUEST, null, null);
			}

			if(gstFile!=null) {
			Response gstUploadFile = Utility.uploadFile(gstFile, "GST_FILE");
			if (gstUploadFile.getStatus() == 200) {
				FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) gstUploadFile.getObject();
				consumerApplication.setGstFilePath(fileUploadPathDto.getFilePath());
			} else {
				return gstUploadFile;
			}
			}
			consumerApplication.setConsumerApplicationId(createApplicationIdBySchemeType);
			consumerApplication.setIvrsNo(ivrsNo);
			consumerApplication.setNatureOfWorkId(consumerApplicationDtoParse.getNatureOfWorkId());
			consumerApplication.setHt11KV(ht11kv);
			consumerApplication.setHt132KV(ht132kv);
			consumerApplication.setHt33KV(ht33kv);
			consumerApplication.setDtr(dtr);
			consumerApplication.setLt(lt);
			consumerApplication.setPtr(ptr);
			consumerApplication.setSchemeTypeId(consumerApplicationDtoParse.getSchemeTypeId());
			consumerApplication.setConsumerId(consumerApplicationDtoParse.getConsumerId());
			consumerApplication.setGuardianName(consumerApplicationDtoParse.getGuardianName());
			consumerApplication.setAddress(consumerApplicationDtoParse.getAddress());
			consumerApplication.setPincode(consumerApplicationDtoParse.getPincode());
			consumerApplication.setDistrictId(consumerApplicationDtoParse.getDistrictId());
		//	consumerApplication.setDcId(consumerApplicationDtoParse.getDcId());
			consumerApplication.setWorkLocationAddr(consumerApplicationDtoParse.getWorkLocationAddr());
			consumerApplication.setDescriptionOfWork(consumerApplicationDtoParse.getDescriptionOfWork());
			consumerApplication.setGstNo(consumerApplicationDtoParse.getGstNo());
			consumerApplication.setCreatedTime(LocalDateTime.now().toString());
			consumerApplication.setIsActive(true);
			consumerApplication.setApplicationStatusId(4L);
			ConsumerApplication saveConsumerApplication = consumerApplicationRepository.save(consumerApplication);
			return Response.response("Submit sucessfully", HttpStatus.OK, saveConsumerApplication, null);

		} catch (Exception e) {
			throw e;
		}
	}

	private Response setNewServiceConnection(ConsumerApplicationDto consumerApplicationDtoParse,
			ConsumerApplication consumerApplication, String createApplicationIdBySchemeType, MultipartFile gstFile)
			throws Exception {

		try {
			Long loadRequested = consumerApplicationDtoParse.getLoadRequested();
			if (loadRequested == null) {
				return Response.response("Load requested should not be null", HttpStatus.BAD_REQUEST, loadRequested,
						null);
			}
			Long loadUnitId = consumerApplicationDtoParse.getLoadUnitId();

			if (loadUnitId == null) {
				return Response.response("Load unit id should not be null", HttpStatus.BAD_REQUEST, loadUnitId, null);
			}

			String landArea = consumerApplicationDtoParse.getLandArea();
			if (landArea == null) {
				return Response.response("Land area should not be null", HttpStatus.BAD_REQUEST, landArea, null);
			}

			Long landAreaUnitId = consumerApplicationDtoParse.getLandAreaUnitId();
			if (landAreaUnitId == null) {
				return Response.response("Land area unit should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId,
						null);
			}
			if(gstFile!=null) {
			Response gstUploadFile = Utility.uploadFile(gstFile, "GST_FILE");
			if (gstUploadFile.getStatus() == 200) {
				FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) gstUploadFile.getObject();
				consumerApplication.setGstFilePath(fileUploadPathDto.getFilePath());
			} else {
				return gstUploadFile;
			}
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
		//	consumerApplication.setDcId(consumerApplicationDtoParse.getDcId());
			consumerApplication.setWorkLocationAddr(consumerApplicationDtoParse.getWorkLocationAddr());
			consumerApplication.setDescriptionOfWork(consumerApplicationDtoParse.getDescriptionOfWork());
			consumerApplication.setGstNo(consumerApplicationDtoParse.getGstNo());
			consumerApplication.setCreatedTime(LocalDateTime.now().toString());
			consumerApplication.setIsActive(true);
			consumerApplication.setApplicationStatusId(4L);
			ConsumerApplication saveConsumerApplication = consumerApplicationRepository.save(consumerApplication);
			return Response.response("Submit sucessfully", HttpStatus.OK, saveConsumerApplication, null);

		} catch (Exception e) {
			throw e;
		}
	}

	private Response setColonyElectrificationLegal(ConsumerApplicationDto consumerApplicationDtoParse,
			ConsumerApplication consumerApplication, String createApplicationIdBySchemeType, MultipartFile gstFile,
			MultipartFile tAndCPpermissionFile, MultipartFile reraFile, MultipartFile groupPermissionFile)
			throws Exception {

		try {
			Long loadRequested = consumerApplicationDtoParse.getLoadRequested();
			if (loadRequested == null) {
				return Response.response("Load requested should not be null", HttpStatus.BAD_REQUEST, loadRequested,
						null);
			}
			Long loadUnitId = consumerApplicationDtoParse.getLoadUnitId();

			if (loadUnitId == null) {
				return Response.response("Load unit id should not be null", HttpStatus.BAD_REQUEST, loadUnitId, null);
			}

			String landArea = consumerApplicationDtoParse.getLandArea();
			if (landArea == null) {
				return Response.response("Land area should not be null", HttpStatus.BAD_REQUEST, landArea, null);
			}

			Long landAreaUnitId = consumerApplicationDtoParse.getLandAreaUnitId();
			if (landAreaUnitId == null) {
				return Response.response("Land area unit should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId,
						null);
			}
			String noOfPlots = consumerApplicationDtoParse.getNoOfPlots();
			if (noOfPlots == null) {
				return Response.response("Number of plots should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId,
						null);

			}
//			Long applyTypeId = consumerApplicationDtoParse.getApplyTypeId();
//
//			if (applyTypeId == null) {
//				return Response.response("Apply type id should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId,
//						null);
//			}

			Long buildingType = consumerApplicationDtoParse.getBuildingType();
			if (buildingType == null) {
				return Response.response("Building type should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId,
						null);
			}

			if (tAndCPpermissionFile == null) {
				return Response.response("T&CP permission file should not be null", HttpStatus.BAD_REQUEST, null, null);

			}

			if (reraFile == null) {
				return Response.response("Rera permission file should not be null", HttpStatus.BAD_REQUEST, null, null);

			}

//			if (applyTypeId.equals(2L) && groupPermissionFile == null) {
//				return Response.response("Group permission file file should not be null", HttpStatus.BAD_REQUEST, null,
//						null);
//			}

			Response tAndCpUploadFile = Utility.uploadFile(tAndCPpermissionFile, "T_and_CP_FILE");
			if (tAndCpUploadFile.getStatus() == 200) {
				FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) tAndCpUploadFile.getObject();
				consumerApplication.setTAndCPpermissionFilePath(fileUploadPathDto.getFilePath());
			} else {
				return tAndCpUploadFile;
			}

			Response reraUploadFile = Utility.uploadFile(reraFile, "RERA_FILE");
			if (reraUploadFile.getStatus() == 200) {
				FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) reraUploadFile.getObject();
				consumerApplication.setReraPermissionFilePath(fileUploadPathDto.getFilePath());
			} else {
				return reraUploadFile;
			}

//			if (applyTypeId.equals(2L)) {
//
//				Response groupUploadFile = Utility.uploadFile(groupPermissionFile, "GROUP_FILE");
//				if (groupUploadFile.getStatus() == 200) {
//					FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) groupUploadFile.getObject();
//					consumerApplication.setGrouppermissionFilePath(fileUploadPathDto.getFilePath());
//				} else {
//					return groupUploadFile;
//				}
//			}
			if(gstFile!=null) {
			Response gstUploadFile = Utility.uploadFile(gstFile, "GST_FILE");
			if (gstUploadFile.getStatus() == 200) {
				FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) gstUploadFile.getObject();
				consumerApplication.setGstFilePath(fileUploadPathDto.getFilePath());
			} else {
				return gstUploadFile;
			}
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
			//consumerApplication.setDcId(consumerApplicationDtoParse.getDcId());
			consumerApplication.setWorkLocationAddr(consumerApplicationDtoParse.getWorkLocationAddr());
			consumerApplication.setDescriptionOfWork(consumerApplicationDtoParse.getDescriptionOfWork());
			consumerApplication.setGstNo(consumerApplicationDtoParse.getGstNo());
			consumerApplication.setCreatedTime(LocalDateTime.now().toString());
			consumerApplication.setIsActive(true);
			consumerApplication.setApplicationStatusId(4L);
			consumerApplication.setBuildingType(buildingType);
			ConsumerApplication saveConsumerApplication = consumerApplicationRepository.save(consumerApplication);
			return Response.response("Submit sucessfully", HttpStatus.OK, saveConsumerApplication, null);

		} catch (Exception e) {
			throw e;
		}
	}

	private Response setColonyElectrificationIllegal(ConsumerApplicationDto consumerApplicationDtoParse,
			ConsumerApplication consumerApplication, String createApplicationIdBySchemeType, MultipartFile gstFile,
			MultipartFile registryFile, MultipartFile nOCfile, MultipartFile groupPermissionFile) throws Exception {

		try {

			Long loadRequested = consumerApplicationDtoParse.getLoadRequested();
			if (loadRequested == null) {
				return Response.response("Load requested should not be null", HttpStatus.BAD_REQUEST, loadRequested,
						null);
			}
			Long loadUnitId = consumerApplicationDtoParse.getLoadUnitId();
			if (loadUnitId == null) {
				return Response.response("Load unit id should not be null", HttpStatus.BAD_REQUEST, loadUnitId, null);
			}

			String landArea = consumerApplicationDtoParse.getLandArea();
			if (landArea == null) {
				return Response.response("Land area should not be null", HttpStatus.BAD_REQUEST, landArea, null);
			}

			Long landAreaUnitId = consumerApplicationDtoParse.getLandAreaUnitId();
			if (landAreaUnitId == null) {
				return Response.response("Land area unit should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId,
						null);
			}
			String noOfPlots = consumerApplicationDtoParse.getNoOfPlots();
			if (noOfPlots == null) {
				return Response.response("Number of plots should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId,
						null);
			}
			Long applyTypeId = consumerApplicationDtoParse.getApplyTypeId();

			
			Long colonyType = consumerApplicationDtoParse.getColonyType();
			if (colonyType == null) {
				return Response.response("Colony type should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId,
						null);
			}
			if (registryFile == null) {
				return Response.response("Registry permission file should not be null", HttpStatus.BAD_REQUEST, null,
						null);
			}

			if (nOCfile == null) {
				return Response.response("NOC permission file should not be null", HttpStatus.BAD_REQUEST, null, null);

			}
			if(colonyType.equals(2L)) {
				if (applyTypeId == null) {
					return Response.response("you selected colony type undeclared so apply type id should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId,
							null);
				}
			}

			if (applyTypeId.equals(2L) && groupPermissionFile == null) {
				return Response.response("Group permission file should not be null", HttpStatus.BAD_REQUEST, null,
						null);
			}

			Response registryUploadFile = Utility.uploadFile(registryFile, "REGISTRY_FILE");
			if (registryUploadFile.getStatus() == 200) {
				FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) registryUploadFile.getObject();
				consumerApplication.setRegistryFilePath(fileUploadPathDto.getFilePath());
			} else {
				return registryUploadFile;
			}

			Response nocUploadFile = Utility.uploadFile(nOCfile, "NOC_FILE");
			if (nocUploadFile.getStatus() == 200) {
				FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) nocUploadFile.getObject();
				consumerApplication.setNOCfilePath(fileUploadPathDto.getFilePath());
			} else {
				return nocUploadFile;
			}
			if (applyTypeId.equals(2L)) {

				Response groupUploadFile = Utility.uploadFile(groupPermissionFile, "GROUP_FILE");
				if (groupUploadFile.getStatus() == 200) {
					FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) groupUploadFile.getObject();
					consumerApplication.setGrouppermissionFilePath(fileUploadPathDto.getFilePath());
				} else {
					return groupUploadFile;
				}
			}
			if(gstFile!=null) {
			Response gstUploadFile = Utility.uploadFile(gstFile, "GST_FILE");
			if (gstUploadFile.getStatus() == 200) {
				FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) gstUploadFile.getObject();
				consumerApplication.setGstFilePath(fileUploadPathDto.getFilePath());
			} else {
				return gstUploadFile;
			}
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
		//	consumerApplication.setDcId(consumerApplicationDtoParse.getDcId());
			consumerApplication.setWorkLocationAddr(consumerApplicationDtoParse.getWorkLocationAddr());
			consumerApplication.setDescriptionOfWork(consumerApplicationDtoParse.getDescriptionOfWork());
			consumerApplication.setGstNo(consumerApplicationDtoParse.getGstNo());
			consumerApplication.setCreatedTime(LocalDateTime.now().toString());
			consumerApplication.setIsActive(true);
			consumerApplication.setApplicationStatusId(4L);
			consumerApplication.setColonyType(colonyType);
			consumerApplication.setApplyTypeId(applyTypeId);
			ConsumerApplication saveConsumerApplication = consumerApplicationRepository.save(consumerApplication);
			return Response.response("Submit sucessfully", HttpStatus.OK, saveConsumerApplication, null);

		} catch (Exception e) {
			throw e;
		}
	}

	private Response setOYT(ConsumerApplicationDto consumerApplicationDtoParse, ConsumerApplication consumerApplication,
			String createApplicationIdBySchemeType, MultipartFile gstFile, MultipartFile khasraKhatoniFile)
			throws Exception {

		try {
			String khasra = consumerApplicationDtoParse.getKhasra();
			if (khasra == null) {
				return Response.response("Khasra should not be null", HttpStatus.BAD_REQUEST, khasra, null);

			}
			String khatoni = consumerApplicationDtoParse.getKhatoni();
			if (khatoni == null) {
				return Response.response("Khatoni should not be null", HttpStatus.BAD_REQUEST, khatoni, null);
			}

			String landArea = consumerApplicationDtoParse.getLandArea();
			if (landArea == null) {
				return Response.response("Land area should not be null", HttpStatus.BAD_REQUEST, landArea, null);
			}
			Long landAreaUnitId = consumerApplicationDtoParse.getLandAreaUnitId();

			if (landAreaUnitId == null) {
				return Response.response("Land area unit should not be null", HttpStatus.BAD_REQUEST, landAreaUnitId,
						null);
			}

			if (khasraKhatoniFile == null) {
				return Response.response("Khasra-Khatoni file should not be null", HttpStatus.BAD_REQUEST, null, null);
			}

			Response khasraKhatoniUploadFile = Utility.uploadFile(khasraKhatoniFile, "KHASRA-KHATONI_FILE");
			if (khasraKhatoniUploadFile.getStatus() == 200) {
				FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) khasraKhatoniUploadFile.getObject();
				consumerApplication.setKhasraKhatoniFilePath(fileUploadPathDto.getFilePath());
			} else {
				return khasraKhatoniUploadFile;
			}
			if(gstFile!=null) {
			Response gstUploadFile = Utility.uploadFile(gstFile, "GST_FILE");
			if (gstUploadFile.getStatus() == 200) {
				FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) gstUploadFile.getObject();
				consumerApplication.setGstFilePath(fileUploadPathDto.getFilePath());
			} else {
				return gstUploadFile;
			}
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
		//	consumerApplication.setDcId(consumerApplicationDtoParse.getDcId());
			consumerApplication.setWorkLocationAddr(consumerApplicationDtoParse.getWorkLocationAddr());
			consumerApplication.setDescriptionOfWork(consumerApplicationDtoParse.getDescriptionOfWork());
			consumerApplication.setGstNo(consumerApplicationDtoParse.getGstNo());
			consumerApplication.setCreatedTime(LocalDateTime.now().toString());
			consumerApplication.setIsActive(true);
			consumerApplication.setApplicationStatusId(4L);
			ConsumerApplication saveConsumerApplication = consumerApplicationRepository.save(consumerApplication);
			return Response.response("Submit sucessfully", HttpStatus.OK, saveConsumerApplication, null);

		} catch (Exception e) {
			throw e;
		}
	}

	private String createApplicationIdBySchemeType(Long schemeTypeId) {
		String localDateTime = LocalDateTime.now().toString();// 2023-07-11T10:31:28.279
		String localDateTimeReplace = localDateTime.replaceAll("\\-|\\:|[a-zA-z]|\\.", "");
		String substringDateTime = localDateTimeReplace.substring(0, localDateTimeReplace.length());
		String consumerApplicationId;

		if (schemeTypeId == 1L) {
			return consumerApplicationId = "DP" + substringDateTime;
		}
		if (schemeTypeId == 2L) {
			return consumerApplicationId = "DS" + substringDateTime;

		}
		if (schemeTypeId == 3L) {
			return consumerApplicationId = "SV" + substringDateTime;
		} else {
			return null;
		}
	}

	public Response checkValidation(ConsumerApplicationDto consumerApplicationDtoParse, MultipartFile gstFile)
			throws Exception {

		try {
			Long dcId = consumerApplicationDtoParse.getDcId();
			if (dcId == null) {
				return Response.response("Dc id should not be null", HttpStatus.BAD_REQUEST, null, null);
			}	
			Long schemeTypeId = consumerApplicationDtoParse.getSchemeTypeId();
			if (schemeTypeId == null) {
				return Response.response("Scheme type id should not be null", HttpStatus.BAD_REQUEST, null, null);
			}
			Long natureOfWorkId = consumerApplicationDtoParse.getNatureOfWorkId();
			if (natureOfWorkId == null) {
				return Response.response("Nature of work id should not be null", HttpStatus.BAD_REQUEST, natureOfWorkId,
						null);

			}
			if (natureOfWorkId > 6) {
				return Response.response("Nature of work id incorrect", HttpStatus.BAD_REQUEST, natureOfWorkId, null);
			}
			Long consumerId = consumerApplicationDtoParse.getConsumerId();
			if (consumerId == null) {
				return Response.response("Consumer id should not be null", HttpStatus.BAD_REQUEST, consumerId, null);
			}

			Long districtId = consumerApplicationDtoParse.getDistrictId();
			if (districtId == null) {
				return Response.response("District id should not be null", HttpStatus.BAD_REQUEST, districtId, null);
			}

			Long pincode = consumerApplicationDtoParse.getPincode();
			if (pincode == null) {
				return Response.response("Pin code should not be null", HttpStatus.BAD_REQUEST, districtId, null);
			}

			Boolean checkedWorkLocation = consumerApplicationDtoParse.getCheckedWorkLocation();
			if (checkedWorkLocation == null) {
				return Response.response("Checked box for work location is null", HttpStatus.BAD_REQUEST, null, null);
			}
			
			String workLocationAddr = consumerApplicationDtoParse.getWorkLocationAddr();

			if(checkedWorkLocation.equals(true)) {
	        	  
	        	   if(workLocationAddr == null || workLocationAddr.trim().isEmpty()) {
	   				return Response.response("Work location address should not be null", HttpStatus.BAD_REQUEST, null, null);
	        	   }
	           }
	           else if(checkedWorkLocation.equals(false)) {
	        	  
	        	   if(workLocationAddr==null) {
	        		   workLocationAddr=null;  
	        	   }
	        	   else if(!workLocationAddr.isEmpty()){
	        		   return Response.response(
	      						"You are doing wrong because you did not check the condition for work location address details and you are inserting details",
	      						HttpStatus.BAD_REQUEST, null, null);  
	        	   }
	        	   workLocationAddr=null;
	           }		

			String descriptionOfWork = consumerApplicationDtoParse.getDescriptionOfWork();
			if (descriptionOfWork == null) {
				return Response.response("Short descripton of work should not be null", HttpStatus.BAD_REQUEST,
						descriptionOfWork, null);
			}
			String guardianName = consumerApplicationDtoParse.getGuardianName();
			if (guardianName == null) {
				return Response.response("Guardian name should not be null", HttpStatus.BAD_REQUEST, guardianName,
						null);
			}
			Boolean checkedGSTfile = consumerApplicationDtoParse.getCheckedGSTfile();
			if (checkedGSTfile == null) {
				return Response.response("Checked box for GST is null", HttpStatus.BAD_REQUEST, null, null);
			}
			
			String gstNo = consumerApplicationDtoParse.getGstNo();
          
			if(checkedGSTfile.equals(true)) {
        	   if(gstFile == null) {
   				return Response.response("GST file should not be null", HttpStatus.BAD_REQUEST, null, null);
        	   }
        	   if(gstNo == null || gstNo.trim().isEmpty()) {
   				return Response.response("GST number should not be null", HttpStatus.BAD_REQUEST, null, null);
        	   }
           }
           else if(checkedGSTfile.equals(false)) {
        	  
        	   if(gstFile != null) {
        		   return Response.response(
   						"You are doing wrong because you did not check the condition for GST check box and you are sending gst file",
   						HttpStatus.BAD_REQUEST, null, null);  
        		   }
        	   if(gstNo==null) {
        		   gstNo=null;  
        	   }
        	   else if(!gstNo.isEmpty()){
        		   return Response.response(
      						"You are doing wrong because you did not check the condition for GST check box and you are inserting gst number",
      						HttpStatus.BAD_REQUEST, null, null);  
        	   }
        	 
        	   gstFile=null;
           }
			
			String address = consumerApplicationDtoParse.getAddress();
			if (address == null) {
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
			if (consumerApplicationDto != null) {

				ObjectMapper objectMapper = new ObjectMapper();

				ConsumerApplicationDto consumerApplicationDtoParse = objectMapper.readValue(consumerApplicationDto,
						ConsumerApplicationDto.class);

				Response checkValidation = checkValidation(consumerApplicationDtoParse, gstFile);

				if (checkValidation == null) {
					Long natureOfWorkId = consumerApplicationDtoParse.getNatureOfWorkId();

					Long consumerId = consumerApplicationDtoParse.getConsumerId();
					String consumerApplicationId = consumerApplicationDtoParse.getConsumerApplicationId();

					if (consumerApplicationId == null) {
						return Response.response("Consumer application id should not be null", HttpStatus.BAD_REQUEST,
								consumerApplicationId, null);
					}
					ConsumerApplication consumerApplication = consumerApplicationRepository
							.getConsumerApplicationByConsumerIdAndConsumerApplicationNumber(consumerId,
									consumerApplicationId);

					if (consumerApplication == null) {
						return Response.response("Data not found for this application id", HttpStatus.NOT_FOUND,
								consumerApplicationId, null);
					}
					return checkNatureOfWork(natureOfWorkId, consumerApplicationDtoParse, consumerApplication,
							consumerApplicationId, tAndCPpermissionFile, reraPermissionFile, groupPermissionFile,
							registryFile, nOCfile, administrativeFile, gstFile, khasraKhatoniFile);
				} else {
					return checkValidation;
				}
			}
			return Response.response("consumerApplicationDto should be not null", HttpStatus.BAD_REQUEST, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);
		}
	}

	private Response checkNatureOfWork(Long natureOfWorkId, ConsumerApplicationDto consumerApplicationDtoParse,
			ConsumerApplication consumerApplication, String consumerApplicationId, MultipartFile tAndCPpermissionFile,
			MultipartFile reraPermissionFile, MultipartFile groupPermissionFile, MultipartFile registryFile,
			MultipartFile nOCfile, MultipartFile administrativeFile, MultipartFile gstFile,
			MultipartFile khasraKhatoniFile) throws Exception {
		DiscomToDcDto discomToDcDto = getDiscomAndRegionAndCircleAndDivisionByDcId(consumerApplicationDtoParse.getDcId());
	      if(discomToDcDto==null) {
				return Response.response("Dc id invalid", HttpStatus.NOT_FOUND, null, null);
	         }
	      consumerApplication.setDiscomId(discomToDcDto.getDiscomId());
	      consumerApplication.setRegionId(discomToDcDto.getRegionId());
	      consumerApplication.setCircleId(discomToDcDto.getCircleId());
	      consumerApplication.setDivisionId(discomToDcDto.getDivisionId());
	      consumerApplication.setSubdivisionId(discomToDcDto.getSubDivisionId());
	      consumerApplication.setDcId(discomToDcDto.getDcId());
		
	      if (natureOfWorkId.equals(1L)) {
			return setLineShiftingGov(consumerApplicationDtoParse, consumerApplication, consumerApplicationId,
					administrativeFile, gstFile);
		}
		if (natureOfWorkId.equals(2L)) {
			return setLineShiftingNonGov(consumerApplicationDtoParse, consumerApplication, consumerApplicationId,
					gstFile);
		}
		if (natureOfWorkId.equals(3L)) {
			return setNewServiceConnection(consumerApplicationDtoParse, consumerApplication, consumerApplicationId,
					gstFile);
		}
		if (natureOfWorkId.equals(4L)) {
			return setColonyElectrificationLegal(consumerApplicationDtoParse, consumerApplication,
					consumerApplicationId, gstFile, tAndCPpermissionFile, reraPermissionFile, groupPermissionFile);
		}
		if (natureOfWorkId.equals(5L)) {
			return setColonyElectrificationIllegal(consumerApplicationDtoParse, consumerApplication,
					consumerApplicationId, gstFile, registryFile, nOCfile, groupPermissionFile);
		}
		if (natureOfWorkId.equals(6L)) {
			return setOYT(consumerApplicationDtoParse, consumerApplication, consumerApplicationId, gstFile,
					khasraKhatoniFile);
		}
		return null;
	}

	@Override
	public Response pendingForGeoLocationApplication(String mobileNo) {

		Consumer consumer = consumerRepository.findByMobileNumber(mobileNo);
		if (consumer == null) {
			return Response.response("Consumer Not Found In This Mobile Number", HttpStatus.NOT_FOUND, null, null);
		}

		List<ConsumerApplication> list = consumerApplicationRepository.findByConsumerId(consumer.getConsumerId());
		List<PendingForGeoLocationApplicationDto> dtos = new ArrayList<>();

		if (list.isEmpty()) {
			return Response.response("No Application For This Consumer Number", HttpStatus.NOT_FOUND, null, null);
		}

		list.forEach(l -> {

			if (l.getApplicationStatusId() == 4L) {
				PendingForGeoLocationApplicationDto applicationDto = new PendingForGeoLocationApplicationDto();
				applicationDto.setConsumerApplicationId(l.getConsumerApplicationId());
				applicationDto.setApplicationStatus(applicationStatusRepository.findById(l.getApplicationStatusId())
						.get().getApplicationStatusName());

				dtos.add(applicationDto);
			}
		});

		if (dtos.isEmpty()) {
			return Response.response("No Application Pending for GeoLocation", HttpStatus.NOT_FOUND, null, null);
		}

		return Response.response("Pending Application For GeoLocation in This Consumer Number", HttpStatus.OK, dtos,
				null);
	}

	@Override
	public Response addGeoLocation(String geoLocationAddForm, MultipartFile startDoc, MultipartFile endDoc) {

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			GeoLocation geoLocation = objectMapper.readValue(geoLocationAddForm, GeoLocation.class);

			String consumerApplicationNo = geoLocation.getConsumerApplicationNo();

			if (consumerApplicationNo == null) {
				return Response.response("Please Fill Consumer Application Number", HttpStatus.NOT_FOUND, null, null);
			}

			if (startDoc == null) {
				return Response.response("Please Attach Start Document", HttpStatus.NOT_FOUND, null, null);
			}

			if (endDoc == null) {
				return Response.response("Please Attach End Document", HttpStatus.NOT_FOUND, null, null);
			}

			GeoLocation findByConsumerApplicationNo = geoLocationRepository
					.findByConsumerApplicationNo(consumerApplicationNo);
			if (findByConsumerApplicationNo != null) {
				return Response.response("Geo Location Already Captured For This Application Number",
						HttpStatus.CONFLICT, null, null);
			}
			GeoLocation location = new GeoLocation();

			Response startDocumentFile;
			Response endDocumentFile;
			try {
				startDocumentFile = Utility.uploadFile(startDoc, "START-" + consumerApplicationNo);
				if (startDocumentFile.getStatus() == 200) {
					FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) startDocumentFile.getObject();
					location.setStartDocPath(fileUploadPathDto.getFilePath());
				} else {
					return startDocumentFile;
				}

				endDocumentFile = Utility.uploadFile(endDoc, "END-" + consumerApplicationNo);
				if (endDocumentFile.getStatus() == 200) {
					FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) endDocumentFile.getObject();
					location.setEndDocPath(fileUploadPathDto.getFilePath());
				} else {
					return endDocumentFile;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			location.setConsumerApplicationNo(consumerApplicationNo);
			location.setStartingLatitude(geoLocation.getStartingLatitude());
			location.setStartingLongitude(geoLocation.getStartingLongitude());
			location.setEndingLatitude(geoLocation.getEndingLatitude());
			location.setEndingLongitude(geoLocation.getEndingLongitude());
			location.setIsActive(true);
			location.setCreatedTime(LocalDateTime.now().toString());

			GeoLocation save = geoLocationRepository.save(location);

			if (save != null) {
				ConsumerApplication consumerApplication = consumerApplicationRepository
						.findByConsumerApplicationId(consumerApplicationNo);
				if (consumerApplication != null) {
					consumerApplication.setApplicationStatusId(5L);
					consumerApplicationRepository.save(consumerApplication);
				}
				return Response.response("Geo Location Captured", HttpStatus.OK, save, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}

		return Response.response("Geo Location Not Captured", HttpStatus.OK, null, null);
	}

	@Override
	public Response getRegistrationFeePaymentDetailByConsumerApplicationNumber(
			ConsumerApplicationIdDto consumerApplicationIdDto) {

		RegistrationFeePaymentDetailDto registrationFeePaymentDetailDto = new RegistrationFeePaymentDetailDto();

		try {
			String consumerApplicationId = consumerApplicationIdDto.getConsumerApplicationId();
			if (consumerApplicationId == null) {
				return Response.response("Consumer application id should not be null", HttpStatus.BAD_REQUEST,
						consumerApplicationId, null);
			}
			ConsumerApplication findByConsumerApplicationId = consumerApplicationRepository
					.findByConsumerApplicationId(consumerApplicationId);
			if (findByConsumerApplicationId == null) {
				return Response.response("Data not found for this application id", HttpStatus.NOT_FOUND,
						consumerApplicationId, null);
			}

			Consumer consumerDetailByConsumerId = consumerRepository
					.getConsumerDetailByConsumerId(findByConsumerApplicationId.getConsumerId());

			if (consumerDetailByConsumerId == null) {
				return Response
						.response(
								"Consumer detail not found for this consumer id ("
										+ findByConsumerApplicationId.getConsumerId() + ")",
								HttpStatus.NOT_FOUND, consumerApplicationId, null);

			}
			registrationFeePaymentDetailDto
					.setConsumerApplicationId(findByConsumerApplicationId.getConsumerApplicationId());
			registrationFeePaymentDetailDto.setConsumerName(consumerDetailByConsumerId.getConsumerName());
			registrationFeePaymentDetailDto.setEmail(consumerDetailByConsumerId.getEmail());
			// BigDecimal bigDecimal = new BigDecimal(1180.00);
			// BigDecimal fees = bigDecimal.setScale(2,RoundingMode.HALF_UP);
			registrationFeePaymentDetailDto.setFees("1180.00");
			registrationFeePaymentDetailDto.setMobileNo(consumerDetailByConsumerId.getMobileNumber());
			// registrationFeePaymentDetailDto.setOrderId("ODR_"+Utility.getRandomNumber());
			registrationFeePaymentDetailDto.setPaymentParticular("Registration Fees");
			return Response.response("Data found sucessfully", HttpStatus.OK, registrationFeePaymentDetailDto, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);
		}
	}

	@Override
	public Response getConsumerApplications(String mobileNo) {

		try {
			Consumer consumer = consumerRepository.findByMobileNumber(mobileNo);
			if (consumer == null) {
				return Response.response("Consumer Not Found In This Mobile Number", HttpStatus.NOT_FOUND, null, null);
			}
			List<ConsumerApplication> list = consumerApplicationRepository.findByConsumerId(consumer.getConsumerId());
			List<ConsumerApplicationsResponseDto> responseList = new ArrayList<>();

			if (list.isEmpty()) {
				return Response.response("No Application For This Consumer Number", HttpStatus.NOT_FOUND, null, null);
			}
			for (ConsumerApplication application : list) {
				if(application!=null) {
				ConsumerApplicationsResponseDto consumerApplicationSetDto = utility.consumerApplicationSetDto(application);
				responseList.add(consumerApplicationSetDto);
				}

			}
			return Response.response("Pending Application For GeoLocation in This Consumer Number", HttpStatus.OK,
					responseList, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}

	@Override
	public Response updateDtrPtr(DtrPtrDto dtrPtrDto) {
		String consumerApplicationId = dtrPtrDto.getConsumerApplicationId();
		Boolean dtrPtr = dtrPtrDto.getDtrPtr();
		if (consumerApplicationId != null && dtrPtr != null) {

			ConsumerApplication findByConsumerApplicationId = consumerApplicationRepository
					.findByConsumerApplicationId(consumerApplicationId);
			if (findByConsumerApplicationId == null) {
				return Response.response("Consumer application not found for this consumer application id",
						HttpStatus.NOT_FOUND, dtrPtrDto, null);
			}
			findByConsumerApplicationId.setPtrDtrCheckBox(dtrPtr);
			consumerApplicationRepository.save(findByConsumerApplicationId);
			return Response.response("PTR/DTR update successfully", HttpStatus.OK, dtrPtrDto, null);
		}
		return Response.response("Consumer application id and PTR/DTR should not be null", HttpStatus.BAD_REQUEST,
				dtrPtrDto, null);
	}

	// @Scheduled(fixedRate = 15000)
	@Scheduled(cron = "0 50 23 * * ?")
	public void updateOytStatusAfter72Hours() {
		System.out.println("Enter In Scheduler");

		List<ConsumerApplication> list = consumerApplicationRepository.findAll();
		list.forEach(l -> {

			if (l.getSchemeTypeId() == 3L && l.getNatureOfWorkId() == 6L) {

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
				LocalDateTime now = LocalDateTime.now();
				LocalDateTime updatedTime = LocalDateTime.parse(l.getUpdatedTime(), formatter);

				// Calculate the duration between updatedTime and now
				Duration duration = Duration.between(updatedTime, now);

				// Check if the duration is more than 72 hours (72 * 60 minutes)
				if (duration.toMinutes() > (72 * 60)) {

					if (l.getApplicationStatusId() == 20L) {
						System.out.println("Updating Status to 21 :" + l.getConsumerApplicationId());
						l.setApplicationStatusId(21L);
						l.setUpdatedTime(now.toString());
						consumerApplicationRepository.save(l);
					}
				}
			}
		});
	}

	@Override
	public Response rejectApplication(ApplicationRejectDto applicationRejectDto) {
		String applicationNo = applicationRejectDto.getApplicationNo();
		String rejectRemark = applicationRejectDto.getRejectRemark();
		Long rejectBy = applicationRejectDto.getRejectBy();
		ApplicationRejectedDetails applicationRejectedDetails = new ApplicationRejectedDetails();

		try {
			if (applicationNo != null && rejectRemark != null && rejectBy != null) {
				ConsumerApplication findByConsumerApplicationId = consumerApplicationRepository
						.findByConsumerApplicationId(applicationNo);

				if (findByConsumerApplicationId == null) {
					return Response.response("Consumer application not found", HttpStatus.NOT_FOUND,
							applicationRejectDto, null);
				}
				applicationRejectedDetails.setApplicationNo(applicationNo);
				applicationRejectedDetails.setRejectBy(rejectBy);
				applicationRejectedDetails.setRejectRemark(rejectRemark);
				applicationRejectedDetails.setCreationDate(LocalDateTime.now().toString());
				applicationRejectRepository.save(applicationRejectedDetails);

				findByConsumerApplicationId.setApplicationStatusId(29L);
				consumerApplicationRepository.save(findByConsumerApplicationId);
				return Response.response("Consumer application rejected", HttpStatus.OK, applicationRejectedDetails,
						null);
			} else {
				return Response.response("Consumer application id,reject remark and reject by should not be null",
						HttpStatus.BAD_REQUEST, applicationRejectDto, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);
		}

		// return null;
	}

	@Override
	public Response consumerApplicationUpdateByMKMY(String consumerApplicationUpdateDto, MultipartFile khasraFile,
			MultipartFile samagraFile) {
		try {
			if (consumerApplicationUpdateDto == null) {
				return Response.response("consumerApplicationUpdateDto should be not null", HttpStatus.BAD_REQUEST,
						null, null);
			}

			ObjectMapper objectMapper = new ObjectMapper();
			ConsumerApplicationUpdateDto consumerApplicationDtoParse = objectMapper
					.readValue(consumerApplicationUpdateDto, ConsumerApplicationUpdateDto.class);
			ConsumerApplication consumerApplication = new ConsumerApplication();

			Response khasraUploadFile = Utility.uploadFile(khasraFile, "KHASRA_FILE");
			if (khasraUploadFile.getStatus() == 200) {
				FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) khasraUploadFile.getObject();
				consumerApplication.setKhasraKhatoniFilePath(fileUploadPathDto.getFilePath());
			} else {
				return khasraUploadFile;
			}

			Response samagraUploadFile = Utility.uploadFile(samagraFile, "SAMAGRA_FILE");
			if (samagraUploadFile.getStatus() == 200) {
				FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) samagraUploadFile.getObject();
				consumerApplication.setSamagraFilePath(fileUploadPathDto.getFilePath());
			} else {
				return samagraUploadFile;
			}
			
			String ConsumerApplicationNumber = createApplicationIdBySchemeType(consumerApplicationDtoParse.getSchemeTypeId());
			
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}

	}

	@Override
	public Response consumerApplicationByApplicationNo(String applicationNo) {
		ConsumerApplicationsResponseDto responseDTO = new ConsumerApplicationsResponseDto();
		try {
			ConsumerApplication findByConsumerApplicationId = consumerApplicationRepository.findByConsumerApplicationId(applicationNo);
			if (findByConsumerApplicationId == null) {
				return Response.response("Consumer application not found", HttpStatus.NOT_FOUND,
						null, null);
			}
			
			ConsumerApplicationsResponseDto consumerApplicationSetDto = utility.consumerApplicationSetDto(findByConsumerApplicationId);
			return Response.response("Consumer application found", HttpStatus.OK,consumerApplicationSetDto, null);
		} catch (Exception e) {
			e.printStackTrace();
	return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}
	
	DiscomToDcDto getDiscomAndRegionAndCircleAndDivisionByDcId(Long dcId) {
		DiscomToDcDto discomToDcDto =new DiscomToDcDto();
		
		Region region=null;
		Circle circle=null;
		SubDivision subDiv=null;	
		Division div=null;	

		Dc dc = dcRepository.findByDcId(dcId);
		if(dc==null) {
			return null;
		}
		 
		if(dc!=null && dc.getSubDivId()!=null) {
			discomToDcDto.setDcId(dc.getDcId());
			discomToDcDto.setSubDivisionId(dc.getSubDivId());
			subDiv= subDivisionRepository.findBySubDivId(dc.getSubDivId());
			 
		}
         if(subDiv!=null && subDiv.getDivisionId()!=null) {
        	 discomToDcDto.setDivisionId(subDiv.getDivisionId());
        	 div= divisionRepository.findByDivId(subDiv.getDivisionId());
		  }
         if(div!=null && div.getCircleId()!=null) {
        	 discomToDcDto.setCircleId(div.getCircleId());
        	circle=circleRepository.findByCircleId(div.getCircleId());
		  }
         
         if(circle!=null && circle.getRegionCode()!=null) {
        	 discomToDcDto.setRegionId(circle.getRegionCode());
        	 region=regionRepository.findByRegionCode(circle.getRegionCode());		
        	 }
         if(region!=null && region.getDiscomId()!=null) {
        	 discomToDcDto.setDiscomId(region.getDiscomId());
        	 }
         return discomToDcDto;
	}
}