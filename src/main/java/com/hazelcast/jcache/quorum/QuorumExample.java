package com.hazelcast.jcache.quorum;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import java.net.URISyntaxException;

public class QuorumExample {

    private static final String CACHE_NAME = "cache-with-quorum";

    public static void main(String[] args) throws URISyntaxException {
        System.setProperty("hazelcast.jcache.provider.type", "client");

        CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();

        Cache<String, String> cache = cacheManager.getCache(CACHE_NAME);

        try {
            cache.put("key", "we have the quorum");
        } catch (Exception expected) {
            System.out.println("Put operation failed with expected QuorumException: " + expected.getMessage());
        }
        Hazelcast.shutdownAll();
    }
}
