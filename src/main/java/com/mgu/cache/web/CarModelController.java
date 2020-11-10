package com.mgu.cache.web;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mgu.cache.entity.CarModel;
import com.mgu.cache.repository.CarModelRepository;

@RestController
public class CarModelController {

    private CarModelRepository repo;

    public CarModelController(CarModelRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public List<CarModel> list() {
        return this.repo.findAll();
    }

    @GetMapping("/{id}")
    public CarModel listByTechnicalId(@PathVariable("id")String id) {
        return this.repo
                    .findByTechnicalId(id)
                    .orElseThrow(() -> new EntityNotFoundException(id + " not found"));
    }
    
    @GetMapping("/bydata/{id}")
    public CarModel listByDataId(@PathVariable("id")final long id) {
        String name = "Name-"+id;
        String year = "Year-"+id;
        return this.repo
                    .findByNameAndYear(name, year)
                    .orElseThrow(() -> new EntityNotFoundException(id + " not found"));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleWebClientResponseException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
