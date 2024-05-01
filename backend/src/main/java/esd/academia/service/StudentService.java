package esd.academia.service;

import java.util.List;
import java.util.Optional;

import esd.academia.model.Student;

public interface StudentService {
	
	Student saveStudent(Student student);
	
	List<Student> getStudents();
	
	Optional<Student> getStudentById(long id);
	
	Student updateStudentById(long id, Student student);

	Student deleteStudentById(long id);

}
