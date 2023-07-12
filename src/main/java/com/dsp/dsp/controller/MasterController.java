package com.dsp.dsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsp.dsp.model.LandAreaUnit;
import com.dsp.dsp.model.LoadRequested;
import com.dsp.dsp.model.NatureOfWork;
import com.dsp.dsp.model.SchemeType;
import com.dsp.dsp.model.SupplyVoltage;
import com.dsp.dsp.repository.LandAreaUnitRepository;
import com.dsp.dsp.repository.LoadRequestedRepository;
import com.dsp.dsp.repository.NatureOfWorkRepository;
import com.dsp.dsp.repository.SchemeTypeRepository;
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

}
