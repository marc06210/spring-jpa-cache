package com.mgu.cache.configuration;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.SimpleKeyGenerator;

public class CarModelKeyGenerator extends SimpleKeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        if(method.getName().equals("findByTechnicalId")) 
            return new CarModelKey((String)params[0], null, null);
        else if(method.getName().equals("findByNameAndYear")) 
            return new CarModelKey(null, (String)params[0], (String)params[1]);
        return super.generate(target, method, params);
    }
}