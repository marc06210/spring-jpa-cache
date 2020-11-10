package com.mgu.cache.configuration;

import org.springframework.util.Assert;

public class CarModelKey implements Comparable<CarModelKey> {
    private String technicalId;
    private String name;
    private String year;
    
    public CarModelKey() {
    }
    public CarModelKey(String technicalId, String name, String year) {
        super();
        Assert.isTrue(technicalId!=null || (name!=null && year!=null), "Key parameters are inconsistent");
        this.technicalId = technicalId;
        this.name = name;
        this.year = year;
    }
    
    // getters and setters
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
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CarModelKey) {
            CarModelKey key = (CarModelKey)obj;
            if(this.technicalId!=null)
                return this.technicalId.equals(key.getTechnicalId());
            else
                return this.name.equals(key.getName()) && this.year.equals(key.getYear());
        }
        return super.equals(obj);
    }
    @Override
    public int compareTo(CarModelKey o) {
        if(o.equals(this))
            return 0;
        else {
            // o is the key present in the cache, so it contains the 3 elements
            return (this.technicalId!=null) 
                    ? this.technicalId.compareTo(o.getTechnicalId())
                    : this.getName().compareTo(o.getName());
        }
    }
}
