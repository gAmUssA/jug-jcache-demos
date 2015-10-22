package com.hazelcast.jcache;

import com.hazelcast.jcache.listener.MyCacheEntryEventFilter;
import com.hazelcast.jcache.listener.MyCacheEntryListener;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.*;
import javax.cache.event.CacheEntryEventFilter;
import javax.cache.spi.CachingProvider;

/**
 * Cache Data Example
 */
public class CapitalsCacheListener {

    private static final String CACHING_PROVIDER_HAZELCAST =
        "com.hazelcast.cache.HazelcastCachingProvider";

    public static final boolean IS_OLD_VALUE_REQUIRED = false;
    public static final boolean IS_SYNCHRONOUS = true;
    public static final Factory<? extends CacheEntryEventFilter> NO_FILTER = null;

    public static void main(String[] args) {

        // Acquire the default cache provider
        //CachingProvider cachingProvider = Caching.getCachingProvider();
        CachingProvider cachingProvider = Caching.getCachingProvider(CACHING_PROVIDER_HAZELCAST);

        // Acquire the default cache manager
        CacheManager manager = cachingProvider.getCacheManager();

        // Define a cache
        MutableConfiguration<String, String> cacheConfig =
            new MutableConfiguration<String, String>().setStoreByValue(true)
                .setTypes(String.class, String.class);

        // Create the cache
        Cache<String, String> cache = manager.createCache("capitals", cacheConfig);

        // Create the Listener

        //You can put CacheEntryEventFilter in here as well...
        CacheEntryListenerConfiguration listenerConfigurationWithFilter =
            new MutableCacheEntryListenerConfiguration(
                FactoryBuilder.factoryOf(MyCacheEntryListener.class),
                FactoryBuilder.factoryOf(MyCacheEntryEventFilter.class),
                IS_OLD_VALUE_REQUIRED,
                IS_SYNCHRONOUS);

        CacheEntryListenerConfiguration listenerConfiguration =
            new MutableCacheEntryListenerConfiguration(
                FactoryBuilder.factoryOf(MyCacheEntryListener.class),
                NO_FILTER,
                IS_OLD_VALUE_REQUIRED,
                IS_SYNCHRONOUS);


        cache.registerCacheEntryListener(listenerConfiguration);

        // Enter some Capitals
        cache.put("UK", "London");
        cache.put("France", "Paris");
        cache.put("Spain", "Madrid");
        cache.put("Belgium", "Brussels");
        cache.put("Germany", "Berlin");

    }

}
