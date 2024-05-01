package esd.academia.service;

import java.util.List;
import java.util.Optional;

import esd.academia.model.Specialization;


public interface SpecializationService {
	
	Specialization saveSpecialization(Specialization specialization);
	
	List<Specialization> getAllSpecs();
	
	Optional<Specialization> getById(long id);
	
	Specialization updateSpecialization(Specialization specialization);
	
	List<Specialization> addBatchSpec(List<Specialization> lSpec);
	
	

}
