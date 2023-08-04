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
import com.dsp.dsp.dto.CredentialsDto;
import com.dsp.dsp.dto.DcAcceptOrDcChangeDto;
import com.dsp.dsp.dto.DemandFeeCalculationResponseDto;
import com.dsp.dsp.dto.DiscomUserRegDto;
import com.dsp.dsp.dto.ErpSurveySubmitDto;
import com.dsp.dsp.dto.FileUploadPathDto;
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
			if (!listOfConsumerApplication.isEmpty()) {
				return Response.response("Data found successfully", HttpStatus.OK, listOfConsumerApplication, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);
		}
		return Response.response("Data not found", HttpStatus.OK, listOfConsumerApplication, null);
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

		// For checking Application Scheme Type is Supervision
		if (schemeType.get().getSchemeTypeName().equalsIgnoreCase("Supervision")
				&& estimate.getSchemeCode().equalsIgnoreCase("SCCW")) {

			// For Checking Application's Nature Of Work is Colony Electrification (Illegal) (5L)
			// In this case Load Unit calculations are performed (KW and KVA)

			if (consumerApplication.getNatureOfWorkId() == 5L) {

				demandFeeCalculationDto.setConsumerApplicationNumber(consumerAppNo);
				demandFeeCalculationDto.setSuperVisionAmount(estimate.getSupervisionCost());

				// For Checking Application Load is KW
				if (consumerApplication.getLoadRequested() != null && consumerApplication.getLoadUnitId() == 1L) {

					Double supervisionAmount = estimate.getSupervisionCost();
					Double kwLoadAmount = (double) (consumerApplication.getLoadRequested() * 15567);
					Double totalAmountWithoutGst = supervisionAmount + kwLoadAmount;

					demandFeeCalculationDto.setKwLoadAmount(round(kwLoadAmount, 2));

					// For Checking Application GST Number Start with 23 or Not
					if (consumerApplication.getGstNo() == null || consumerApplication.getGstNo() != null
							&& consumerApplication.getGstNo().startsWith("23")) {

						Double cgstAmount = totalAmountWithoutGst * cgstRate;
						Double sgstAmount = totalAmountWithoutGst * sgstRate;
						Double totalAmountWithGst = totalAmountWithoutGst + cgstAmount + sgstAmount;

						demandFeeCalculationDto.setCgst(round(cgstAmount, 2));
						demandFeeCalculationDto.setSgst(round(sgstAmount, 2));
						demandFeeCalculationDto.setTotalAmount(round(totalAmountWithGst, 2));
					} else {
						Double igstAmount = totalAmountWithoutGst * igstRate;
						Double totalAmountWithGst = totalAmountWithoutGst + igstAmount;
						demandFeeCalculationDto.setTotalAmount(round(totalAmountWithGst, 2));
						demandFeeCalculationDto.setIgst(round(igstAmount, 2));
					}

				}
				// For Checking Application Load is KVA
				else if (consumerApplication.getLoadRequested() != null && consumerApplication.getLoadUnitId() == 2L) {

					Double supervisionAmount = estimate.getSupervisionCost();
					Double kvaLoadAmount = (double) (consumerApplication.getLoadRequested() * 12.5 * 15567);
					Double totalAmountWithoutGst = supervisionAmount + kvaLoadAmount;

					demandFeeCalculationDto.setKvaLoadAmount(round(kvaLoadAmount, 2));

					// For Checking Application GST Number Start with 23 or Not
					// 23 hoga to bs cgst+sgst lgega otherwise igst
					if (consumerApplication.getGstNo() == null || consumerApplication.getGstNo() != null
							&& consumerApplication.getGstNo().startsWith("23")) {

						Double cgstAmount = totalAmountWithoutGst * cgstRate;
						Double sgstAmount = totalAmountWithoutGst * sgstRate;
						Double totalAmountWithGst = totalAmountWithoutGst + cgstAmount + sgstAmount;

						demandFeeCalculationDto.setCgst(round(cgstAmount, 2));
						demandFeeCalculationDto.setSgst(round(sgstAmount, 2));
						demandFeeCalculationDto.setTotalAmount(round(totalAmountWithGst, 2));
					} else {
						Double igstAmount = totalAmountWithoutGst * igstRate;
						Double totalAmountWithGst = totalAmountWithoutGst + igstAmount;
						demandFeeCalculationDto.setTotalAmount(round(totalAmountWithGst, 2));
						demandFeeCalculationDto.setIgst(round(igstAmount, 2));
					}

				}
			} else {

				// For Checking Application's Nature Of Work is Other then "Colony Electrification
				// (Illegal)"
				// In this case Load Unit calculations are not performed (KW and KVA) (5L)

				demandFeeCalculationDto.setConsumerApplicationNumber(consumerAppNo);
				demandFeeCalculationDto.setSuperVisionAmount(estimate.getSupervisionCost());
				Double totalAmountWithoutGst = estimate.getSupervisionCost();

				// For Checking Application GST Number Start with 23 or Not
				// 23 hoga to bs cgst+sgst lgega otherwise igst
				if (consumerApplication.getGstNo() == null
						|| consumerApplication.getGstNo() != null && consumerApplication.getGstNo().startsWith("23")) {

					Double cgstAmount = totalAmountWithoutGst * cgstRate;
					Double sgstAmount = totalAmountWithoutGst * sgstRate;
					Double totalAmountWithGst = totalAmountWithoutGst + cgstAmount + sgstAmount;

					demandFeeCalculationDto.setCgst(round(cgstAmount, 2));
					demandFeeCalculationDto.setSgst(round(sgstAmount, 2));
					demandFeeCalculationDto.setTotalAmount(round(totalAmountWithGst, 2));
				} else {
					Double igstAmount = totalAmountWithoutGst * igstRate;
					Double totalAmountWithGst = totalAmountWithoutGst + igstAmount;
					demandFeeCalculationDto.setTotalAmount(round(totalAmountWithGst, 2));
					demandFeeCalculationDto.setIgst(round(igstAmount, 2));
				}

			}
			return Response.response("Demand Fee Genrated", HttpStatus.OK, demandFeeCalculationDto, null);
		}

		return Response.response("Not A Spervision Application", HttpStatus.NOT_FOUND, null, null);
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();
		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
