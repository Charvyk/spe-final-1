package esd.academia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esd.academia.model.Faculty;
import esd.academia.service.FacultyService;

@RestController
@RequestMapping("/faculty")
@CrossOrigin("http://localhost:3000/")
public class FacultyController {
	
	private FacultyService facultyService;
	
	public FacultyController(FacultyService facultyService) {
		super();
		this.facultyService = facultyService;
	}
	
	@GetMapping("test")
	public ResponseEntity<String> test(){
		return new ResponseEntity<String>("HelloFacultyController", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Faculty>> getAllFaculty(){
		List<Faculty> res = null;
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			List<Faculty> lFac = facultyService.getFaculties();	
			res = lFac;
		}
		catch (Exception e) {
			System.out.println("Error: "+e.getClass());
			System.out.println("Error: "+e.getMessage());
			System.out.println("Error: "+e.getStackTrace());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}		
		return new ResponseEntity<List<Faculty>>(res, status);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Faculty> getFacultyById(@PathVariable long id){
		Faculty res = null;
		HttpStatus status = HttpStatus.ACCEPTED;		
		try {
			Optional<Faculty> fac = facultyService.getFacultyById(id);
			if(fac.isPresent()) {
				res = fac.get();
			}
			else {
				status = HttpStatus.NOT_FOUND;
			}
		}
		catch (Exception e) {
			System.out.println("Error: "+e.getClass());
			System.out.println("Error: "+e.getMessage());
			status = HttpStatus.NOT_FOUND;
		}		
		return new ResponseEntity<Faculty>(res, status);	
	}
	
	@PostMapping(path = "/add")
	public ResponseEntity<String> addFaculty(@RequestBody Faculty faculty){
		System.out.println("Adding: "+faculty);
		String msg = "Successfully Added!";
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			Faculty newFac = facultyService.saveFaculty(faculty);		
			System.out.println("Added: "+newFac);
		}
		catch (Exception e) {
			System.out.println("Error: "+e.getClass());
			System.out.println("Error: "+e.getMessage());
			msg = "Something went wrong - "+e.getMessage();
			status = HttpStatus.UNPROCESSABLE_ENTITY;
		}
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);		
	}
	
	@PostMapping(path="/addMultiple")
	public ResponseEntity<String> addMulFac(@RequestBody List<Faculty> facs){
		facs.forEach(fac -> {			
			Faculty newF = this.facultyService.saveFaculty(fac);
			System.out.println(newF);
		});
		return new ResponseEntity<String>("Done",HttpStatus.OK);
	}
	
	@PostMapping(path = "/update")
	public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty){
		Faculty fac = new Faculty();		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(faculty.getFaculty_id()==0) {
			headers.add("issue", "Employee ID is blank");
			return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .headers(headers)
	                .body(fac);				
		}
		if(faculty.getFirstname().isEmpty() || faculty.getEmail().isEmpty() || faculty.getTitle().isEmpty()) {
			headers.add("issue", "Name/Email/Title is blank");
			return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .headers(headers)
	                .body(fac);						
		}
		System.out.println("Update Request: "+faculty);
		HttpStatus status;
		try {
			Faculty newFac = facultyService.updateFacultyById(faculty.getFaculty_id(), faculty);
			if(newFac!=null) {
				System.out.println("Updated: "+newFac);	
				fac = newFac;
				status = HttpStatus.ACCEPTED;
			}
			else {
				System.out.println("Not updated.");	
				headers.add("issue", "DB Issue");
				return ResponseEntity
		                .status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .headers(headers)
		                .body(fac);				
			}			
		}
		catch (Exception e) {
			System.out.println("Error: "+e.getClass());
			System.out.println("Error: "+e.getMessage());
			headers.add("issue", "Could not update");
			status = HttpStatus.UNPROCESSABLE_ENTITY;
		}
		return new ResponseEntity<Faculty>(fac, status);		
	}

}



























