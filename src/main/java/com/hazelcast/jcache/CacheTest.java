package com.hazelcast.jcache;


import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

public class CacheTest {
    public static void main(String[] args) {
        System.setProperty("hazelcast.jcache.provider.type", "server");

        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        Cache<Integer, String> cache = cacheManager.getCache("test");
        cache.put(1, "Tokyo");
        cache.put(2, "Paris");
        cache.put(3, "New York");
        System.out.println("Finished loading cache");
    }
}
