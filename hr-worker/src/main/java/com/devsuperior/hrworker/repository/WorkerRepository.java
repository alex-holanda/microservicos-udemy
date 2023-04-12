package com.devsuperior.hrworker.repository;

import com.devsuperior.hrworker.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
