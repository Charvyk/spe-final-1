package esd.academia.service;

import java.util.List;

import esd.academia.model.Batch;

public interface BatchService {
	Batch saveBatch(Batch batch);
	
	List<Batch> saveBatchInBatch(List<Batch> lBatch);
	
	List<Batch> getAllBatches();
}
