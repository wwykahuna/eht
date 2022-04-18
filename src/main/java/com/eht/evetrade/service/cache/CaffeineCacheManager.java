package com.eht.evetrade.service.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;

import java.util.HashMap;
import java.util.Map;

public class CaffeineCacheManager extends SimpleCacheManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaffeineCacheManager.class);

    private Map<String, String> cacheSpecs = new HashMap<String, String>();

    public CaffeineCacheManager(Map<String, String> cacheSpecs) {
        super();
        if (cacheSpecs != null && !cacheSpecs.isEmpty()) {
            this.cacheSpecs.putAll(cacheSpecs);
        }
    }

    @Override
    protected Cache getMissingCache(String name) {
        String cfg = this.cacheSpecs.get(name);
        if (StringUtils.isEmpty(cfg)) {
            cfg = this.cacheSpecs.get("default");
            if (cfg == null) {
                cfg = "initialCapacity=100,maximumSize=5000,expireAfterWrite=120s";
            }
        }
        return new CaffeineCache(name, Caffeine.from(cfg).build());
    }
}
