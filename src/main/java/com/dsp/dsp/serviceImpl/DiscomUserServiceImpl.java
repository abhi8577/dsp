package com.dsp.dsp.serviceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dsp.dsp.dto.ChangePasswordDto;
import com.dsp.dsp.dto.ConsumerApplicationsResponseDto;
import com.dsp.dsp.dto.CredentialsDto;
import com.dsp.dsp.dto.DcAcceptOrDcChangeDto;
import com.dsp.dsp.dto.DemandFeeCalculationResponseDto;
import com.dsp.dsp.dto.DiscomUserRegDto;
import com.dsp.dsp.dto.ErpSurveySubmitDto;
import com.dsp.dsp.dto.FileUploadPathDto;
import com.dsp.dsp.dto.UpdateJELoadByDiscomUserDto;
import com.dsp.dsp.model.ConsumerAppDCChange;
import com.dsp.dsp.model.ConsumerApplication;
import com.dsp.dsp.model.DiscomUser;
import com.dsp.dsp.model.ERPEstimate;
import com.dsp.dsp.model.SchemeType;
import com.dsp.dsp.repository.ConsumerAppDCChangeRepository;
import com.dsp.dsp.repository.ConsumerApplicationRepository;
import com.dsp.dsp.repository.DiscomUserRepository;
import com.dsp.dsp.repository.ERPEstimateRepository;
import com.dsp.dsp.repository.LoadUnitRepository;
import com.dsp.dsp.repository.SchemeTypeRepository;
import com.dsp.dsp.response.Response;
import com.dsp.dsp.service.DiscomUserService;
import com.dsp.dsp.util.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DiscomUserServiceImpl implements DiscomUserService {

	@Autowired
	DiscomUserRepository discomUserRepository;

	@Autowired
	ConsumerApplicationRepository consumerApplicationRepository;

	@Autowired
	ConsumerAppDCChangeRepository consumerAppDCChangeRepository;

	@Autowired
	ERPEstimateRepository erpEstimateRepository;

	@Autowired
	SchemeTypeRepository schemeTypeRepository;

	@Autowired
	LoadUnitRepository loadUnitRepository;
	
	@Autowired
	Utility utility;

	@Override
	public Response save(DiscomUserRegDto discomUserRegDto) {
		try {

			if (discomUserRegDto == null) {

				return Response.response("User request should not be null", HttpStatus.BAD_REQUEST, null, null);

			}

			String accessLevel = discomUserRegDto.getAccessLevel();
			Long circleId = discomUserRegDto.getCircleId();
			Long dcId = discomUserRegDto.getDcId();
			Long designationId = discomUserRegDto.getDesignationId();
			Long discomId = discomUserRegDto.getDiscomId();
			Long divId = discomUserRegDto.getDivId();
			String email = discomUserRegDto.getEmail();
			Long feederId = discomUserRegDto.getFeederId();
			String mobileNo = discomUserRegDto.getMobileNo();
			String password = discomUserRegDto.getPassword();
			Long regionId = discomUserRegDto.getRegionId();
			Long subDivisionId = discomUserRegDto.getSubDivisionId();
			Long substationId = discomUserRegDto.getSubstationId();
			String userId = discomUserRegDto.getUserId();
			String userName = discomUserRegDto.getUserName();
			Long roleId = discomUserRegDto.getRoleId();

			if (roleId == null) {
				return Response.response("Role id should not be null", HttpStatus.BAD_REQUEST, null, null);

			}
			if (userId == null || userId.isEmpty()) {

				return Response.response("User id should not be null", HttpStatus.BAD_REQUEST, null, null);

			}

			if (mobileNo == null || mobileNo.isEmpty()) {
				return Response.response("Mobile number should not be null", HttpStatus.BAD_REQUEST, null, null);

			}

			if (password == null || password.isEmpty()) {
				return Response.response("Password should not be null", HttpStatus.BAD_REQUEST, null, null);

			}

			if (userName == null || userName.isEmpty()) {

				return Response.response("User name should not be null", HttpStatus.BAD_REQUEST, null, null);

			}

			if (accessLevel == null || accessLevel.isEmpty()) {
				return Response.response("Access level should not be null", HttpStatus.BAD_REQUEST, null, null);

			}

			String trimUserName = userName.replaceAll("\\s+", " ").trim().toUpperCase();

			DiscomUser findByUserId = discomUserRepository.findByUserId(userId);

			if (findByUserId != null) {
				return Response.response("User id already exist", HttpStatus.OK, findByUserId, null);

			}
			Encoder encoder = Base64.getEncoder();
			String encodePass = encoder.encodeToString(password.getBytes());
			DiscomUser discomUser = new DiscomUser();
			discomUser.setAccessLevel(accessLevel);
			discomUser.setCircleId(circleId);
			discomUser.setCreatedTime(LocalDateTime.now().toString());
			discomUser.setDcId(dcId);
			discomUser.setDesignationId(designationId);
			discomUser.setDiscomId(discomId);
			discomUser.setDivId(divId);
			discomUser.setEmail(email);
			discomUser.setFeederId(feederId);
			discomUser.setIsActive(true);
			discomUser.setMobileNo(mobileNo);
			discomUser.setPassword(encodePass);
			discomUser.setRegionId(regionId);
			discomUser.setSubDivisionId(subDivisionId);
			discomUser.setSubstationId(substationId);
			discomUser.setUserId(userId);
			discomUser.setUserName(trimUserName);
			discomUser.setRoleId(roleId);
			DiscomUser save = discomUserRepository.save(discomUser);

			return Response.response("Discom user data saved", HttpStatus.OK, save, null);

		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}

	@Override
	public Response getLoginDetails(CredentialsDto credentialsDto) {

		try {
			String id = credentialsDto.getId();
			String password = credentialsDto.getPassword();

			if (id == null || id.isEmpty()) {
				return Response.response("Id should not be null", HttpStatus.BAD_REQUEST, null, null);

			}

			if (password == null || password.isEmpty()) {
				return Response.response("Password should not be null", HttpStatus.BAD_REQUEST, null, null);

			}

			DiscomUser findByUserId = discomUserRepository.findByUserId(id);

			if (findByUserId == null) {
				return Response.response("Discom user not found", HttpStatus.NOT_FOUND, null, null);

			}
			String decryptData = Utility.getDecryptData(findByUserId.getPassword());

			if (decryptData.equals(password)) {
				return Response.response("Login successfully", HttpStatus.OK, findByUserId, null);
			} else {
				return Response.response("Invalid credentials", HttpStatus.NOT_FOUND, null, null);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}

	@Override
	public Response changePwd(ChangePasswordDto changePasswordDto) {
		try {
			Optional<DiscomUser> discomUser = discomUserRepository.findById(changePasswordDto.getId());
			if (discomUser.isPresent()) {

				DiscomUser discomUser2 = discomUser.get();
				String encodePass = Utility.getEncodeData(changePasswordDto.getPassword());
				discomUser2.setPassword(encodePass);

				discomUser2 = discomUserRepository.save(discomUser2);

				return Response.response("Password Successfully changed", HttpStatus.OK, discomUser2, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.response("Id Not Found", HttpStatus.NOT_FOUND, null, null);
	}

	@Override
	public Response dsicomUserDetails(String mobileNo) {

		DiscomUser discomUser = discomUserRepository.findByMobileNo(mobileNo);

		if (discomUser == null) {
			return Response.response("Discom User Not Found", HttpStatus.NOT_FOUND, null, null);
		}
		return Response.response("Discom User Details", HttpStatus.OK, discomUser, null);
	}

	@Override
	public Response applicationDetailsByDcForDiscomUser(Long dcId) {

		List<ConsumerApplication> listOfConsumerApplication = new ArrayList<ConsumerApplication>();
		try {
			listOfConsumerApplication = consumerApplicationRepository.findByDcId(dcId);
			if (listOfConsumerApplication.isEmpty()) {
				return Response.response("Data not found", HttpStatus.OK, listOfConsumerApplication, null);
			}
			List<ConsumerApplicationsResponseDto> listOfConsumerApplicationsResponseDto =new ArrayList();
			
			for(ConsumerApplication ca :listOfConsumerApplication) {
				if(ca!=null) {
				ConsumerApplicationsResponseDto consumerApplicationSetDto = utility.consumerApplicationSetDto(ca);
				listOfConsumerApplicationsResponseDto.add(consumerApplicationSetDto);
			}
			}
			return Response.response("Data found successfully", HttpStatus.OK, listOfConsumerApplicationsResponseDto, null);

		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);
		}
	}

	@Override
	public Response acceptAppOrChangeDc(DcAcceptOrDcChangeDto dcAcceptOrDcChangeDto) {

		ConsumerApplication consumerApplication = consumerApplicationRepository
				.findByConsumerApplicationId(dcAcceptOrDcChangeDto.getConsumerApplicationNumber());
		if (consumerApplication == null) {
			return Response.response("Consumer Application Not Found", HttpStatus.NOT_FOUND, null, null);
		}
		if (dcAcceptOrDcChangeDto.getIsDcChange()) {
			ConsumerAppDCChange appDCChange = new ConsumerAppDCChange();
			appDCChange.setConsumerApplicationId(dcAcceptOrDcChangeDto.getConsumerApplicationNumber());
			appDCChange.setOldDcId(dcAcceptOrDcChangeDto.getOldDcId());
			appDCChange.setNewDcId(dcAcceptOrDcChangeDto.getNewdDcId());
			appDCChange.setReason(dcAcceptOrDcChangeDto.getDcChangedReason());
			appDCChange.setUpdatedBy(dcAcceptOrDcChangeDto.getUpdatedBy());
			appDCChange.setUpdatedTime(LocalDateTime.now().toString());

			ConsumerAppDCChange save = consumerAppDCChangeRepository.save(appDCChange);
			if (save != null) {
				consumerApplication.setDcId(dcAcceptOrDcChangeDto.getNewdDcId());
				ConsumerApplication application = consumerApplicationRepository.save(consumerApplication);
			}
			return Response.response("DC Changed", HttpStatus.OK, appDCChange, null);
		} else {
			consumerApplication.setApplicationStatusId(7L);
			ConsumerApplication application = consumerApplicationRepository.save(consumerApplication);
			return Response.response("Application Accepted At Dc End", HttpStatus.OK, application, null);
		}
	}

	@Override
	public Response erpSurveySubmit(String erpSurveySubmitDto, MultipartFile eRPEstimateFile) {

		try {
			if (erpSurveySubmitDto == null) {
				return Response.response("ERP Submit Request Not Found ", HttpStatus.NOT_FOUND, null, null);
			}

			ObjectMapper objectMapper = new ObjectMapper();
			ErpSurveySubmitDto erp = objectMapper.readValue(erpSurveySubmitDto, ErpSurveySubmitDto.class);

			if (erp.getConsumerApplicationNumber() == null) {
				return Response.response("Please Send Consumer Application Number", HttpStatus.NOT_FOUND, null, null);
			}

			ConsumerApplication consumerApplication = consumerApplicationRepository
					.findByConsumerApplicationId(erp.getConsumerApplicationNumber());
			if (consumerApplication == null) {
				return Response.response("Consumer Application Not Found", HttpStatus.NOT_FOUND, null, null);
			}

			ERPEstimate estimate = erpEstimateRepository.findByProjectNumber(erp.getProjectNumber());
			if (estimate != null) {
				return Response.response("This ERP Project Number Is Already Exist", HttpStatus.CONFLICT, null, null);
			}

			ERPEstimate erpEstimate = new ERPEstimate();
			BeanUtils.copyProperties(erp, erpEstimate);

			Response erpUploadFile = Utility.uploadFile(eRPEstimateFile,
					"ERP_ESTIMATE-" + consumerApplication.getConsumerApplicationId());
			if (erpUploadFile.getStatus() == 200) {
				FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) erpUploadFile.getObject();
				erpEstimate.setErpEstimateFilePath(fileUploadPathDto.getFilePath());
			} else {
				return erpUploadFile;
			}
			erpEstimate.setCreatedTime(LocalDateTime.now().toString());
			ERPEstimate save = erpEstimateRepository.save(erpEstimate);
			if (save != null) {
				consumerApplication.setErpProjectNumber(save.getProjectNumber());
				consumerApplication.setApplicationStatusId(12L);
				consumerApplicationRepository.save(consumerApplication);
			} else {
				return Response.response("Data Not Saved In ERP Table", HttpStatus.CONFLICT, null, null);
			}
			return Response.response("Data Saved", HttpStatus.OK, save, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);
		}
	}

	@Override
	public Response demandFeeCalculation(String consumerAppNo) {

		Double cgstRate = 0.09; // 9% CGST rate
		Double sgstRate = 0.09; // 9% CGST rate
		Double igstRate = 0.18; // 18% CGST+SGST rate
		DemandFeeCalculationResponseDto demandFeeCalculationDto = new DemandFeeCalculationResponseDto();
		ConsumerApplication consumerApplication = consumerApplicationRepository
				.findByConsumerApplicationId(consumerAppNo);
		if (consumerApplication == null) {
			return Response.response("Consumer Application Not Found", HttpStatus.NOT_FOUND, null, null);
		}
		ERPEstimate estimate = erpEstimateRepository.findByProjectNumber(consumerApplication.getErpProjectNumber());
		if (estimate == null) {
			return Response.response("ERP Survey Not Done For This Application Number ", HttpStatus.NOT_FOUND, null,
					null);
		}
		Optional<SchemeType> schemeType = schemeTypeRepository.findById(consumerApplication.getSchemeTypeId());
		Double supervisionAmount = estimate.getSupervisionCost();
		Long loadRequest = consumerApplication.getLoadRequested();
		Long loadUnitId = consumerApplication.getLoadUnitId();
		String gstNo = consumerApplication.getGstNo();
		Boolean ptrDtrCheckBox = consumerApplication.getPtrDtrCheckBox();

		Long natureOfWorkId = consumerApplication.getNatureOfWorkId();
		if (natureOfWorkId > 6 || natureOfWorkId < 1) {
			return Response.response("Invalid nature of work id in consumer application", HttpStatus.NOT_FOUND, null,
					null);
		}
		demandFeeCalculationDto.setConsumerApplicationNumber(consumerAppNo);
		demandFeeCalculationDto.setSuperVisionAmount(supervisionAmount);
		demandFeeCalculationDto.setKwLoadAmount(0.0);
		demandFeeCalculationDto.setKvaLoadAmount(0.0);
		demandFeeCalculationDto.setIgst(0.0);
		demandFeeCalculationDto.setEstimatedAmount(0.0);
		// For checking Application Scheme Type is Supervision OYT
		if (schemeType.get().getSchemeTypeName().equalsIgnoreCase("Supervision")
				&& estimate.getSchemeCode().equalsIgnoreCase("OYT")) {
			System.out.println("inside estimate oyt");
			checkGstStartWith23orNot(gstNo, supervisionAmount, cgstRate, sgstRate, igstRate, 0.0,
					demandFeeCalculationDto);
			return Response.response("Demand Fee Genrated", HttpStatus.OK, demandFeeCalculationDto, null);
		}
		// For checking Application Scheme Type is Supervision SCCW
		else if (schemeType.get().getSchemeTypeName().equalsIgnoreCase("Supervision")
				&& estimate.getSchemeCode().equalsIgnoreCase("SCCW")) {
			// For Checking Nature Of Work And RequestLoad
			demandFeeCalculationDto = checkNatureOfWorkAndRequestLoad(loadRequest, loadUnitId, natureOfWorkId,
					ptrDtrCheckBox, demandFeeCalculationDto);
			// For Checking Application GST Number Start with 23 or Not
			checkGstStartWith23orNot(gstNo, supervisionAmount, cgstRate, sgstRate, igstRate, 0.0,
					demandFeeCalculationDto);
			return Response.response("Demand Fee Genrated", HttpStatus.OK, demandFeeCalculationDto, null);
		}
		// For checking Application Scheme Type is Deposite
		else if (schemeType.get().getSchemeTypeName().equalsIgnoreCase("Deposit")
				&& estimate.getSchemeCode().equalsIgnoreCase("Deposit")) {
			Double estimateAmount = estimate.getEstimatedCost();
			demandFeeCalculationDto.setEstimatedAmount(estimateAmount);

			// For Checking Nature Of Work And RequestLoad
			demandFeeCalculationDto = checkNatureOfWorkAndRequestLoad(loadRequest, loadUnitId, natureOfWorkId,
					ptrDtrCheckBox, demandFeeCalculationDto);
			// For Checking Application GST Number Start with 23 or Not
			checkGstStartWith23orNot(gstNo, supervisionAmount, cgstRate, sgstRate, igstRate, estimateAmount,
					demandFeeCalculationDto);
			return Response.response("Demand Fee Genrated", HttpStatus.OK, demandFeeCalculationDto, null);
		}
		return Response.response("Not a spervision or deposit application", HttpStatus.NOT_FOUND, null, null);
	}

	private void checkGstStartWith23orNot(String gstNo, Double supervisionAmount, Double cgstRate, Double sgstRate,
			Double igstRate, Double estimateAmount, Object demandFeeCalculationDto) {
		DemandFeeCalculationResponseDto dto = (DemandFeeCalculationResponseDto) demandFeeCalculationDto;
		if (gstNo == null || gstNo != null && gstNo.startsWith("23")) {
			Double kwLoadAmount = dto.getKwLoadAmount();
			Double kvaLoadAmount = dto.getKvaLoadAmount();
			Double cgstAmount = supervisionAmount * cgstRate;
			Double sgstAmount = supervisionAmount * sgstRate;
			System.out.println(
					"supervisionAmount , cgstAmount , sgstAmount , kwLoadAmount , kvaLoadAmount , estimateAmount = "
							+ supervisionAmount + " , " + cgstAmount + " , " + sgstAmount + " , " + kwLoadAmount + " , "
							+ kvaLoadAmount + " , " + estimateAmount);
			Double totalAmountWithGst = supervisionAmount + cgstAmount + sgstAmount + kwLoadAmount + kvaLoadAmount
					+ estimateAmount;
			dto.setCgst(round(cgstAmount, 2));
			dto.setSgst(round(sgstAmount, 2));
			dto.setTotalAmount(round(totalAmountWithGst, 2));
		} else {
			Double igstAmount = supervisionAmount * igstRate;
			Double totalAmountWithGst = supervisionAmount + igstAmount + estimateAmount;
			dto.setTotalAmount(round(totalAmountWithGst, 2));
			dto.setIgst(round(igstAmount, 2));
		}
	}

	DemandFeeCalculationResponseDto checkNatureOfWorkAndRequestLoad(Long loadRequest, Long loadUnitId,
			Long natureOfWorkId, Boolean ptrDtrCheckBox, DemandFeeCalculationResponseDto demandFeeCalculationDto) {
		double kwLoadAmount = 0.0;
		double kvaLoadAmount = 0.0;
		// check nature of work
		if (natureOfWorkId == 5) {// Colony Electrification(Illegal)
			// check load request for kw
			if (loadRequest != null && loadUnitId == 1L) {
				if (loadRequest < 400) {
					kwLoadAmount = loadRequest * 15567.0;
				} else {
					kwLoadAmount = loadRequest * 1.25 * 850;
				}
				kwLoadAmount = round(kwLoadAmount, 2);
				demandFeeCalculationDto.setKvaLoadAmount(kvaLoadAmount);
				demandFeeCalculationDto.setKwLoadAmount(kwLoadAmount);
				return demandFeeCalculationDto;
			}
			// check load request for kva
			else if (loadRequest != null && loadUnitId == 2L) {

				if (loadRequest < 500) {
					kvaLoadAmount = loadRequest * 15567.0;
				} else {
					kvaLoadAmount = loadRequest * 1.25 * 850;
				}
				kvaLoadAmount = round(kvaLoadAmount, 2);
				demandFeeCalculationDto.setKwLoadAmount(kwLoadAmount);
				demandFeeCalculationDto.setKvaLoadAmount(kvaLoadAmount);
				return demandFeeCalculationDto;
			}
		}
		// check nature of work
		else if (natureOfWorkId == 4) {// Colony Electrification(Legal)
			// check load request for kw
			if (loadRequest != null && loadUnitId == 1L) {
				kwLoadAmount = loadRequest * 1.25 * 850;
				kwLoadAmount = round(kwLoadAmount, 2);
				demandFeeCalculationDto.setKvaLoadAmount(kvaLoadAmount);
				demandFeeCalculationDto.setKwLoadAmount(kwLoadAmount);
				return demandFeeCalculationDto;
			}
			// check load request for kva
			else if (loadRequest != null && loadUnitId == 2L) {
				kvaLoadAmount = loadRequest * 850;
				kvaLoadAmount = round(kvaLoadAmount, 2);
				demandFeeCalculationDto.setKwLoadAmount(kwLoadAmount);
				demandFeeCalculationDto.setKvaLoadAmount(kvaLoadAmount);
				return demandFeeCalculationDto;
			}
		}
		// check nature of work
		else if (natureOfWorkId == 3) {// New Service Connection (Extension)
			// check load request for kw
			if (loadRequest != null && loadUnitId == 1L) {
				if (ptrDtrCheckBox) {
					kwLoadAmount = loadRequest * 1.25 * 850;
					kwLoadAmount = round(kwLoadAmount, 2);
				}
				demandFeeCalculationDto.setKvaLoadAmount(kvaLoadAmount);
				demandFeeCalculationDto.setKwLoadAmount(kwLoadAmount);
				return demandFeeCalculationDto;
			}
			// check load request for kva
			else if (loadRequest != null && loadUnitId == 2L) {

				if (ptrDtrCheckBox) {
					kvaLoadAmount = loadRequest * 850.0;
					kvaLoadAmount = round(kvaLoadAmount, 2);
				}
				demandFeeCalculationDto.setKwLoadAmount(kwLoadAmount);
				demandFeeCalculationDto.setKvaLoadAmount(kvaLoadAmount);
				return demandFeeCalculationDto;
			}
		}
		// Other then "Colony Electrification (Illegal)"
		// In this case Load Unit calculations are not performed (KW and KVA) (5L)
		return demandFeeCalculationDto;
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();
		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	@Override
	public Response updateJELoadByDiscomUser(UpdateJELoadByDiscomUserDto dto) {

		ConsumerApplication consumerApplication = consumerApplicationRepository
				.findByConsumerApplicationId(dto.getConsumerApplicationNumber());
		if (consumerApplication == null) {
			return Response.response("Consumer Application Not Found", HttpStatus.NOT_FOUND, null, null);
		}

		consumerApplication.setJeLoad(dto.getJeLoad());
		consumerApplication.setJeLoadUnitKwYaKva(dto.getJeLoadUnitKwYaKva());
		ConsumerApplication save = consumerApplicationRepository.save(consumerApplication);
		
		return Response.response("Consumer Application Updated", HttpStatus.OK, save, null);
	}

}
