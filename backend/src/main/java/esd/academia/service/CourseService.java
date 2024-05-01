package esd.academia.service;

import java.util.List;
import java.util.Optional;

import esd.academia.model.Course;

public interface CourseService {
	
	Course saveCourse(Course course);
	
	List<Course> getAllCourses();
	
	Course updateCourse(Course course);
	
	Optional<Course> getCourseById(long id);
	
	boolean deleteCourse(long id);

}
