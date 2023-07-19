package com.dsp.dsp.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dsp.dsp.model.ApplyType;
import com.dsp.dsp.model.Circle;
import com.dsp.dsp.model.Dc;
import com.dsp.dsp.model.District;
import com.dsp.dsp.model.Division;
import com.dsp.dsp.model.LandAreaUnit;
import com.dsp.dsp.model.LoadRequested;
import com.dsp.dsp.model.NatureOfWork;
import com.dsp.dsp.model.Region;
import com.dsp.dsp.model.SchemeType;
import com.dsp.dsp.model.SubDivision;
import com.dsp.dsp.model.SupplyVoltage;
import com.dsp.dsp.repository.ApplyTypeRepository;
import com.dsp.dsp.repository.CircleRepository;
import com.dsp.dsp.repository.DcRepository;
import com.dsp.dsp.repository.DistrictRepository;
import com.dsp.dsp.repository.DivisionRepository;
import com.dsp.dsp.repository.LandAreaUnitRepository;
import com.dsp.dsp.repository.LoadRequestedRepository;
import com.dsp.dsp.repository.NatureOfWorkRepository;
import com.dsp.dsp.repository.RegionRepository;
import com.dsp.dsp.repository.SchemeTypeRepository;
import com.dsp.dsp.repository.SubDivisionRepository;
import com.dsp.dsp.repository.SupplyVoltageRepository;
import com.dsp.dsp.response.Response;

@RestController
@RequestMapping("/master")
public class MasterController {
	@Autowired
	NatureOfWorkRepository natureOfWorkRepository;

	@Autowired
	SupplyVoltageRepository supplyVoltageRepository;

	@Autowired
	SchemeTypeRepository schemeTypeRepository;

	@Autowired
	LoadRequestedRepository loadRequestedRepository;

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

	@GetMapping("/get_all_nature_of_work")
	public Response getAllNatureOfWork() {
		try {
			List<NatureOfWork> findAll = natureOfWorkRepository.findAll();

			if(findAll.isEmpty()) {
				return Response.response("Data not found", HttpStatus.NOT_FOUND, null, null);
			}
			return Response.response("Data  found", HttpStatus.OK, findAll, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}


	}


	@GetMapping("/get_all_supply_voltage")
	public Response getAllSupplyVoltage() {
		try {
			List<SupplyVoltage> findAll = supplyVoltageRepository.findAll();

			if(findAll.isEmpty()) {
				return Response.response("Data not found", HttpStatus.NOT_FOUND, null, null);
			}
			return Response.response("Data  found", HttpStatus.OK, findAll, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}

	}
	@GetMapping("/get_all_scheme_type")
	public Response getAllSchemeType() {
		try {
			List<SchemeType> findAll = schemeTypeRepository.findAll();

			if(findAll.isEmpty()) {
				return Response.response("Data not found", HttpStatus.NOT_FOUND, null, null);
			}
			return Response.response("Data  found", HttpStatus.OK, findAll, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}

	}

	@GetMapping("/get_all_load_requested_unit")
	public Response getAllLoadRequestedUnit() {
		try {
			List<LoadRequested> findAll = loadRequestedRepository.findAll();

			if(findAll.isEmpty()) {
				return Response.response("Data not found", HttpStatus.NOT_FOUND, null, null);
			}
			return Response.response("Data  found", HttpStatus.OK, findAll, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}

	@GetMapping("/get_all_land_area_unit")
	public Response getAllLandAreaUnit() {
		try {
			List<LandAreaUnit> findAll = LandAreaUnitRepository.findAll();

			if(findAll.isEmpty()) {
				return Response.response("Data not found", HttpStatus.NOT_FOUND, null, null);
			}
			return Response.response("Data  found", HttpStatus.OK, findAll, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}

	@GetMapping("/get_all_district")
	public Response getAllDistrict() {
		try {
			List<District> findAll = districtRepository.findAll();

			if(findAll.isEmpty()) {
				return Response.response("Data not found", HttpStatus.NOT_FOUND, null, null);
			}
			return Response.response("Data  found", HttpStatus.OK, findAll, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}




	@GetMapping("/get_all_dc")
	public Response getAllDc() {
		try {
			List<Dc> findAll = dcRepository.findAll();

			if(findAll.isEmpty()) {
				return Response.response("Data not found", HttpStatus.NOT_FOUND, null, null);
			}
			return Response.response("Data  found", HttpStatus.OK, findAll, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}

	@GetMapping("/get_all_apply_type")
	public Response getAllApplyType() {
		try {
			List<ApplyType> findAll = applyTypeRepository.findAll();

			if(findAll.isEmpty()) {
				return Response.response("Data not found", HttpStatus.NOT_FOUND, null, null);
			}
			return Response.response("Data  found", HttpStatus.OK, findAll, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}

	@GetMapping("/get_all_region")
	public Response getAllRegion() {
		try {
			List<Region> findAll = regionRepository.findAll();

			if(findAll.isEmpty()) {
				return Response.response("Data not found", HttpStatus.NOT_FOUND, null, null);
			}
			return Response.response("Data  found", HttpStatus.OK, findAll, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}

	@GetMapping("/get_circle_by_region/{regionCode}")
	public Response getCircleByRegion(@PathVariable(name ="regionCode") Long regionCode) {
		try {
			List<Circle> findAll = circleRepository.findByRegionCode(regionCode);

			if(findAll.isEmpty()) {
				return Response.response("Data not found", HttpStatus.NOT_FOUND, null, null);
			}
			return Response.response("Data  found", HttpStatus.OK, findAll, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}

	@GetMapping("/get_division_by_circle/{circleId}")
	public Response getDivisioneByCircle(@PathVariable(name ="circleId") Long circleCode) {
		try {
			List<Division> findAll = divisionRepository.findByCircleId(circleCode);

			if(findAll.isEmpty()) {
				return Response.response("Data not found", HttpStatus.NOT_FOUND, null, null);
			}
			return Response.response("Data  found", HttpStatus.OK, findAll, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}

	@GetMapping("/get_sub_division_by_division/{divisionId}")
	public Response getSubDivisioneByDivision(@PathVariable(name ="divisionId") Long divisionId) {
		try {
			List<SubDivision> findAll = subDivisionRepository.findByDivisionId(divisionId);

			if(findAll.isEmpty()) {
				return Response.response("Data not found", HttpStatus.NOT_FOUND, null, null);
			}
			return Response.response("Data  found", HttpStatus.OK, findAll, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}

	@GetMapping("/get_dc_by_sub_division/{subDivisionId}")
	public Response getDcBySubDivision(@PathVariable(name ="subDivisionId") Long subDivisionId) {
		try {
			List<Dc> findAll = dcRepository.findBySubDivId(subDivisionId);

			if(findAll.isEmpty()) {
				return Response.response("Data not found", HttpStatus.NOT_FOUND, null, null);
			}
			return Response.response("Data  found", HttpStatus.OK, findAll, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}

	@GetMapping("/get_dc_by_district/{districtId}")
	public Response getDcByDitrictId(@PathVariable(name ="districtId") Long districtId) {
		try {
			List<Dc> findAll = dcRepository.findByDistrictId(districtId);

			if(findAll.isEmpty()) {
				return Response.response("Data not found", HttpStatus.NOT_FOUND, null, null);
			}
			return Response.response("Data  found", HttpStatus.OK, findAll, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}

	@GetMapping("/downloadpdf")
	public ResponseEntity<byte[]> downloadFileForGallery(@RequestParam("path") String filePath)  {
		byte[] readAllBytes = null;
		HttpHeaders headers = null;
		try {
			String file =filePath.replace("\\", "/");

			double random1=  Math.random();
			String random=String.valueOf(random1);
			System.out.println(random);
			random=random.substring(random.indexOf(".")+1);
			Path path = Paths.get(file);
			readAllBytes = Files.readAllBytes(path);
			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.set("Content-Disposition",
					String.format("attachment; filename=document"+".pdf"));
		} catch (Exception e) {

			return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);	 

		}

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(readAllBytes, headers, HttpStatus.OK);	 

		return responseEntity;
	}
}
