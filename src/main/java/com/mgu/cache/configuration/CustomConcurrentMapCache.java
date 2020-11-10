package com.mgu.cache.configuration;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.lang.Nullable;

import com.mgu.cache.entity.CarModel;

public class CustomConcurrentMapCache implements Cache {
    private final ConcurrentMap<Object, Object> store;
    private final String name;
    
    public CustomConcurrentMapCache(String name) {
        this.name = name;
        store = new ConcurrentSkipListMap<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.store;
    }

    @Override
    public ValueWrapper get(Object key) {
        return this.toValueWrapper(this.store.get(key));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Class<T> type) {
        return (T)this.store.get(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return (T) fromStoreValue(this.store.computeIfAbsent(key, k -> {
            try {
                return toStoreValue(valueLoader.call());
            }
            catch (Throwable ex) {
                throw new ValueRetrievalException(key, valueLoader, ex);
            }
        }));
    }
    
    private void put(Object obj) {
        if(obj instanceof CarModel) {
            CarModel o = (CarModel)obj;
            CarModelKey key = new CarModelKey(o.getTechnicalId(), o.getName(), o.getYear());
            this.store.put(key, o);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void put(Object key, Object value) {
        if(value instanceof List) {
            List<CarModel> l = (List<CarModel>)value;
            l.stream().forEach(this::put);
        } else {
            this.put(toStoreValue(value));
        }
    }

    @Override
    public void evict(Object key) {
        store.remove(key);
    }

    @Override
    public void clear() {
        store.clear();
    }
    
    @Nullable
    protected Cache.ValueWrapper toValueWrapper(@Nullable Object storeValue) {
        return (storeValue != null ? new SimpleValueWrapper(fromStoreValue(storeValue)) : null);
    }
    /**
     * Convert the given value from the internal store to a user value
     * returned from the get method (adapting {@code null}).
     * @param storeValue the store value
     * @return the value to return to the user
     */
    @Nullable
    protected Object fromStoreValue(@Nullable Object storeValue) {
        return storeValue;
    }

    /**
     * Convert the given user value, as passed into the put method,
     * to a value in the internal store (adapting {@code null}).
     * @param userValue the given user value
     * @return the value to store
     */
    protected Object toStoreValue(@Nullable Object userValue) {
        if (userValue == null) {
            throw new IllegalArgumentException(
                    "Cache '" + getName() + "' is configured to not allow null values but null was provided");
        }
        return userValue;
    }
}