package esd.academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import esd.academia.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
