package esd.academia.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import esd.academia.model.Batch;
import esd.academia.repository.BatchRepository;
import esd.academia.service.BatchService;

@Service
public class BatchServiceImpl implements BatchService {
	
	private BatchRepository batchRepository;

	public BatchServiceImpl(BatchRepository batchRepository) {
		super();
		this.batchRepository = batchRepository;
	}

	@Override
	public Batch saveBatch(Batch batch) {
		return batchRepository.save(batch);
	}

	@Override
	public List<Batch> saveBatchInBatch(List<Batch> lBatch) {
		return batchRepository.saveAll(lBatch);
	}

	@Override
	public List<Batch> getAllBatches() {
		return batchRepository.findAll();
	}
	
	

}
