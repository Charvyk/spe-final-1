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

import esd.academia.model.Course;
import esd.academia.service.CourseService;

@RestController
@RequestMapping("/course")
@CrossOrigin("http://localhost:3000/")
public class CourseController {
	
	private CourseService courseService;
	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}
	
	@GetMapping("all")
	public ResponseEntity<List<Course>> getAllCourses() {
		List<Course> courses = null;
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			courses = this.courseService.getAllCourses();
			return ResponseEntity
					.status(HttpStatus.OK)
					.headers(headers)
					.body(courses);
		}
		catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.headers(headers)
					.body(courses);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable long id) {
		Course course = null;
		try {
			Optional<Course> res = this.courseService.getCourseById(id);
			if(res.isPresent()) {
				course = res.get();
				System.out.println(course);
				return new ResponseEntity<Course>(course, HttpStatus.OK);
			}
			return new ResponseEntity<Course>(course, HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			return new ResponseEntity<Course>(course, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<Course> addCourse(@RequestBody Course course) {
		Course newCourse = null;
		System.out.println("Adding course: "+course.toString());
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			newCourse = this.courseService.saveCourse(course);
			return new ResponseEntity<Course>(newCourse, HttpStatus.OK);
		}
		catch (Exception e) {
			headers.add("issue", e.getMessage());
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.headers(headers)
					.body(newCourse);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
		Course newCourse = null;
		try {
			newCourse = this.courseService.updateCourse(course);
			HttpStatus hs = HttpStatus.OK;
			if (newCourse==null) hs = HttpStatus.NOT_FOUND;
			return new ResponseEntity<Course>(newCourse, hs);
		}
		catch (Exception e) {
			return new ResponseEntity<Course>(newCourse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> deleteCourse(@RequestBody Course course) {
		String msg = "Success";
		try {
			boolean out = this.courseService.deleteCourse(course.getCourse_id());
			HttpStatus hs = HttpStatus.OK;
			if (out==false) { 
				msg = "Fail";
				hs = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			return new ResponseEntity<String>(msg, hs);
		}
		catch (Exception e) {
			msg = "Error";
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
