package esd.academia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import esd.academia.model.Faculty;
import esd.academia.repository.FacultyRepository;
import esd.academia.service.FacultyService;

@Service
public class FacultyServiceImpl implements FacultyService {
	
	private FacultyRepository facultyRepo;

	public FacultyServiceImpl(FacultyRepository facultyRepo) {
		super();
		this.facultyRepo = facultyRepo;
	}

	@Override
	public Faculty saveFaculty(Faculty faculty) {
		return facultyRepo.save(faculty);
	}

	@Override
	public List<Faculty> getFaculties() {
		return facultyRepo.findAll();
	}

	@Override
	public Optional<Faculty> getFacultyById(long id) {
		return facultyRepo.findById(id);
	}

	@Override
	public Faculty updateFacultyById(long id, Faculty faculty) {
		Faculty retrievedFac = null;
		Optional<Faculty> fac = facultyRepo.findById(id);
		if(fac.isPresent()) {
			retrievedFac = facultyRepo.save(faculty);
		}
		return retrievedFac;
	}
	
}
