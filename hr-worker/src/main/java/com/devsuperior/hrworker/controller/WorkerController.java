package com.devsuperior.hrworker.controller;

import com.devsuperior.hrworker.model.Worker;
import com.devsuperior.hrworker.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/workers", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkerController {

    private static final Logger logger = LoggerFactory.getLogger(WorkerController.class);

    private final Environment environment;

    private final WorkerRepository workerRepository;

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        return ResponseEntity.ok(workerRepository.findAll());
    }

    @GetMapping("/{workerId}")
    public ResponseEntity<Worker> getWorker(@PathVariable Long workerId) {
        logger.info("PORT = " + environment.getProperty("local.server.port"));
        return ResponseEntity.ok(workerRepository.findById(workerId).get());
    }
}
