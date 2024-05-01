package esd.academia.service;

import java.util.List;
import java.util.Optional;

import esd.academia.model.Faculty;

public interface FacultyService {
	
	Faculty saveFaculty(Faculty faculty);
	
	List<Faculty> getFaculties();
	
	Optional<Faculty> getFacultyById(long id);
	
	Faculty updateFacultyById(long id, Faculty faculty);

}
