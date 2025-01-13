package com.example.globaldorm.config;

import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheEvictionScheduler {
    // line (10-21) modified from https://www.baeldung.com/spring-boot-evict-cache (baeldung, 2024)
    private final CacheManager cacheManager;

    public CacheEvictionScheduler(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Scheduled(fixedRate=3600000)
    public void evictExpiredCaches() {
        cacheManager.getCacheNames().forEach(cacheName -> {
            cacheManager.getCache(cacheName).clear();
        });
    }
}
