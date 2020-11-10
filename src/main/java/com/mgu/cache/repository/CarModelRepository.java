package com.mgu.cache.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mgu.cache.entity.CarModel;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    public Optional<CarModel> findById(long id);
    
    @Cacheable(value = "carmodel", keyGenerator = "CarModelKeyGenerator")
    public Optional<CarModel> findByTechnicalId(String technicalId);
    
    @Cacheable(value = "carmodel", keyGenerator = "CarModelKeyGenerator")
    public Optional<CarModel> findByNameAndYear(String name, String year);
}
