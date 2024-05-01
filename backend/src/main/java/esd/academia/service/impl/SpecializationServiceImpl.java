package esd.academia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import esd.academia.model.Specialization;
import esd.academia.repository.SpecializationRepository;
import esd.academia.service.SpecializationService;

@Service
public class SpecializationServiceImpl implements SpecializationService {
	
	private SpecializationRepository specializationRepository;

	public SpecializationServiceImpl(SpecializationRepository specializationRepository) {
		super();
		this.specializationRepository = specializationRepository;
	}

	@Override
	public Specialization saveSpecialization(Specialization specialization) {
		return this.specializationRepository.save(specialization);
	}

	@Override
	public List<Specialization> getAllSpecs() {
		return this.specializationRepository.findAll();
	}

	@Override
	public Optional<Specialization> getById(long id) {
		return this.specializationRepository.findById(id);
	}

	@Override
	public Specialization updateSpecialization(Specialization specialization) {
		Optional<Specialization> spec = this.specializationRepository.findById(specialization.getSpec_id());
		if(spec.isPresent()) {
			this.specializationRepository.save(specialization);
		}
		return null;
	}

	@Override
	public List<Specialization> addBatchSpec(List<Specialization> lSpec) {
		return this.specializationRepository.saveAll(lSpec);
	}
	
	

}
