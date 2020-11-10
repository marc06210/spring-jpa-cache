package com.mgu.cache.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carmodel_generator")
    @SequenceGenerator(name="carmodel_generator", sequenceName = "carmodel_seq", allocationSize = 1)
    private long id=-1;
    
    private String technicalId;
    private String name;
    private String year;
    
    public CarModel() {
    }
    public CarModel(String technicalId, String name, String year) {
        super();
        this.technicalId = technicalId;
        this.name = name;
        this.year = year;
    }
    // getters and setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTechnicalId() {
        return technicalId;
    }
    public void setTechnicalId(String technicalId) {
        this.technicalId = technicalId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
}
