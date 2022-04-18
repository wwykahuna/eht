package com.eht.evetrade.config;

import com.eht.evetrade.config.properties.CaffeineCacheProperties;
import com.eht.evetrade.service.cache.CaffeineCacheManager;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.caffeine.CaffeineCache;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EnableCaching
@Configuration
@EnableConfigurationProperties(CaffeineCacheProperties.class)
public class CaffeineCacheConfig {

    @Autowired
    private CaffeineCacheProperties cacheProperties;


    @Bean
    public CacheManager cacheManager() {
        Map<String, String> cacheSpecs = cacheProperties.getSpecMap();
        CaffeineCacheManager manager = new CaffeineCacheManager(cacheSpecs);
        if (cacheSpecs != null && !cacheSpecs.isEmpty()) {
            List<CaffeineCache> caches = cacheSpecs.entrySet().stream()
                    .map(entry -> new CaffeineCache(entry.getKey(), Caffeine.from(entry.getValue()).build()))
                    .collect(Collectors.toList());
            manager.setCaches(caches);
        }
        return manager;
    }
}
