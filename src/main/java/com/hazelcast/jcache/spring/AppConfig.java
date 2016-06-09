package com.hazelcast.jcache.spring;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Configuration for Spring JCache example
 */
@Configuration
@EnableCaching
public class AppConfig {

    @Bean
    public CacheManager cacheManager() {
        return new JCacheCacheManager();
    }

    @Bean
    public IDummyBean dummyBean() {
        return new DummyBean();
    }
}
