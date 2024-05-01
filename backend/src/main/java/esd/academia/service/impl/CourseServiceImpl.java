package esd.academia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import esd.academia.model.Course;
import esd.academia.repository.CourseRepository;
import esd.academia.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	private CourseRepository courseRepo;
	
	public CourseServiceImpl(CourseRepository courseRepo) {
		super();
		this.courseRepo = courseRepo;
	}

	@Override
	public Course saveCourse(Course course) {
		return courseRepo.save(course);
	}

	@Override
	public List<Course> getAllCourses() {
		List<Course> courses = null;
		try {
			courses = courseRepo.findAll();
			System.out.println("Hi");
		}
		catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
		}
		return courses;
	}

	@Override
	public Course updateCourse(Course course) {
		Optional<Course> retrievedCourse = courseRepo.findById(course.getCourse_id());
		Course out = null;
		if(retrievedCourse.isPresent()) {
			out = courseRepo.save(course);
		}
		return out;
	}

	@Override
	public Optional<Course> getCourseById(long id) {
		return courseRepo.findById(id);
	}

	@Override
	public boolean deleteCourse(long id) {
		try {
			courseRepo.deleteCoursePreReq(id);
			courseRepo.deleteCourse(id);
//			courseRepo.deleteById(id);
			return true;
		}
		catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			return false;
		}
	}

}
