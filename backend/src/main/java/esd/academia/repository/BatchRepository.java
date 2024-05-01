package esd.academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import esd.academia.model.Batch;

public interface BatchRepository extends JpaRepository<Batch, String> {

}
