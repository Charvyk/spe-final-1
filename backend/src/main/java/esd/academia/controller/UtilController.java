package esd.academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esd.academia.model.Batch;
import esd.academia.model.Specialization;
import esd.academia.service.BatchService;
import esd.academia.service.SpecializationService;

@RestController
@RequestMapping("/utils")
@CrossOrigin("http://localhost:3000/")
public class UtilController {
	
	@Autowired
	private BatchService batchService;
	
	@Autowired
	private SpecializationService specializationService;
	
	
	public UtilController(BatchService batchService, SpecializationService specializationService) {
		super();
		this.batchService = batchService;
		this.specializationService = specializationService;
	}
	
	@GetMapping(path = "getAllSpec")
	public ResponseEntity<List<Specialization>> getAllSpecs(){
		List<Specialization> res = null;
		try {
			res = this.specializationService.getAllSpecs();
			return new ResponseEntity<List<Specialization>>(res, HttpStatus.ACCEPTED);	
		}
		catch (Exception e) {
			return new ResponseEntity<List<Specialization>>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}				
	}



	@GetMapping(path = "getAllBatches")
	public ResponseEntity<List<Batch>> getAllBatches(){
		List<Batch> res = null;
		try {
			res = batchService.getAllBatches();
			return new ResponseEntity<List<Batch>>(res, HttpStatus.ACCEPTED);	
		}
		catch (Exception e) {
			return new ResponseEntity<List<Batch>>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}				
	}
	
	@PostMapping(path = "/addSpec")
	public ResponseEntity<Specialization> addSpec(@RequestBody Specialization spec){
		if(spec.getCode()==null||spec.getName()==null||spec.getCredits_req()==0) {
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.add("issue", "blank code or name or credits");
			return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .headers(headers)
	                .body(new Specialization());
		}
		try {
			Specialization nb = this.specializationService.saveSpecialization(spec);
			return new ResponseEntity<Specialization>(nb, HttpStatus.OK);
		}
		catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.add("issue", "duplicate spec code?");
			return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .headers(headers)
	                .body(new Specialization());
		}			
	}
	
	
	@PostMapping(path = "/addBatch")
	public ResponseEntity<Batch> addBatch(@RequestBody Batch batch){
		if(batch.getDegree()==null||batch.getYear()==0) {
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.add("issue", "blank degree or year");
			return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .headers(headers)
	                .body(new Batch());
		}
		try {
			Batch nb = batchService.saveBatch(batch);
			return new ResponseEntity<Batch>(nb, HttpStatus.ACCEPTED);
		}
		catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.add("issue", "duplicate batch");
			return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .headers(headers)
	                .body(new Batch());
		}			
	}
	
	@PostMapping(path = "/addSpecMultiple")
	public ResponseEntity<String> addSpecMultiple(@RequestBody List<Specialization> lSpec){
		this.specializationService.	addBatchSpec(lSpec);	
		return new ResponseEntity<String>("done", HttpStatus.ACCEPTED);				
	}
	
	@PostMapping(path = "/addBatchMultiple")
	public ResponseEntity<String> addBatchMultiple(@RequestBody List<Batch> lBatch){
		batchService.saveBatchInBatch(lBatch);		
		return new ResponseEntity<String>("done", HttpStatus.ACCEPTED);				
	}	

}
