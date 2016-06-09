package com.hazelcast.jcache;

import com.hazelcast.jcache.loader.MyCacheLoader;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Cache Data Example
 */
public class CapitalsLoader {

    private static final String CACHING_PROVIDER_HAZELCAST =
        "com.hazelcast.cache.HazelcastCachingProvider";

    public static void main(String[] args) {

        // Acquire the default cache provider
        CachingProvider cachingProvider = Caching.getCachingProvider();
        // CachingProvider cachingProvider = Caching.getCachingProvider(CACHING_PROVIDER_HAZELCAST);

        // Acquire the default cache manager
        CacheManager manager = cachingProvider.getCacheManager();

        // Define a cache
        MutableConfiguration<String, String> cacheConfig =
            new MutableConfiguration<String, String>().setStoreByValue(true)
                .setTypes(String.class, String.class)
                .setCacheLoaderFactory(FactoryBuilder.factoryOf(MyCacheLoader.class))
                .setReadThrough(true);


        // Create the cache
        Cache<String, String> cache = manager.createCache("capitals", cacheConfig);

        // I forgot, what's the Capital of Portugal?
        System.out.println("Capital of Portugal is : " + cache.get("Portugal"));
        System.out
            .println("Capitals of UK and France are: " + cache.getAll(new HashSet<>(Arrays.asList("UK", "France"))));

    }

}
