package esd.academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import esd.academia.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    
}
