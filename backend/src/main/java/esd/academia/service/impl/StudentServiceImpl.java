package esd.academia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import esd.academia.model.Student;
import esd.academia.repository.StudentRepository;
import esd.academia.service.StudentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
	
	private StudentRepository studentRepo;

	public StudentServiceImpl(StudentRepository studentRepo) {
		super();
		this.studentRepo = studentRepo;
	}

	@Override
	public Student saveStudent(Student student) {
		log.info("Running saveStudent");
		return studentRepo.save(student);
	}

	@Override
	public List<Student> getStudents() {
		log.info("Running getStudents");
		return studentRepo.findAll();
	}

	@Override
	public Optional<Student> getStudentById(long id) {
		log.info("Running getStudentById");
		return studentRepo.findById(id);
	}

	@Override
	public Student updateStudentById(long id, Student student) {
		log.info("Running updateStudentById");
		Student retrievedStudent = null;
		Optional<Student> stud = studentRepo.findById(id);
		if(stud.isPresent()) {
			retrievedStudent = studentRepo.save(student);
		}
		return retrievedStudent;
	}

	@Override
	public Student deleteStudentById(long id) {
		log.info("Running deleteStudentById");
		Student retrievedSudent = null;
		Optional<Student> s = getStudentById(id);
		if(s.isPresent()) {
			retrievedSudent = s.get();
			studentRepo.deleteById(id);
		}

		return retrievedSudent;
	}

}
