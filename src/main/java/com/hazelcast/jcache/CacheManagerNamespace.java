package com.hazelcast.jcache;


import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.net.URI;
import java.util.Properties;

import static com.hazelcast.cache.HazelcastCachingProvider.propertiesByLocation;

public class CacheManagerNamespace {

    public static String CACHE_NAME = "city";

    public static void main(String[] args) {

        // using client version of JCache Provider
        System.setProperty("hazelcast.jcache.provider.type", "client");

        CachingProvider provider = Caching.getCachingProvider();
        // using Hazelcast API to supply cacheManager with configuration
        Properties properties = propertiesByLocation("classpath:hazelcast-client.xml");

        // getting cacheManager with given namespace (URI) and properties
        CacheManager cacheManager = provider.getCacheManager(URI.create("USCacheManager"), null, properties);
        CacheManager cacheManager2 = provider.getCacheManager(URI.create("EuroCacheManager"), null, properties);

        Cache<Integer, String> cache = cacheManager.getCache(CACHE_NAME);
        cache.put(1, "New York");
        cache.put(2, "Atlanta");
        cache.put(3, "Boston");

        Cache<Integer, String> cache2 = cacheManager2.getCache(CACHE_NAME);
        cache2.put(1, "London");
        cache2.put(2, "Paris");
        cache2.put(3, "Lisbon");

        System.out.println("Finished loading cache");
    }
}
