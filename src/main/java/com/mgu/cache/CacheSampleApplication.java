package com.mgu.cache;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;

import com.mgu.cache.entity.CarModel;
import com.mgu.cache.repository.CarModelRepository;

@SpringBootApplication
@EnableCaching
public class CacheSampleApplication {

    @Autowired
    private CarModelRepository repo;
    
    public static void main(String[] args) {
        SpringApplication.run(CacheSampleApplication.class, args);
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void initTable( ) {
        repo.deleteAll();
        
        IntStream.range(0, 100)
            .mapToObj(i -> {
                CarModel curr = new CarModel();
                curr.setTechnicalId("TI-"+i);
                curr.setName("Name-"+i);
                curr.setYear("Year-"+i);
                return curr;
            }).forEach(repo::save);
//            System.out.println(cacheMgr.getCache("currencies").getClass().getName());
    }
}
