package com.mgu.cache.configuration;

import java.util.Arrays;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {
    @Bean("CarModelKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new CarModelKeyGenerator();
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        Cache carModelCache = new CustomConcurrentMapCache("carmodel");
        cacheManager.setCaches(Arrays.asList(carModelCache));
        return cacheManager;
    }
}
