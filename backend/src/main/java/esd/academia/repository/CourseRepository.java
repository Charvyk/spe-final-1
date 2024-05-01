package esd.academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import esd.academia.model.Course;
import jakarta.transaction.Transactional;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	@Modifying
	@Transactional
	@Query(value = "delete from course_prerequisite where prereq_course_id = :id", nativeQuery = true)
	void deleteCoursePreReq(@Param("id") long id);
	
	@Modifying
	@Transactional
	@Query(value = "delete from course where course_id = :id", nativeQuery = true)
	void deleteCourse(@Param("id") long id);

}
