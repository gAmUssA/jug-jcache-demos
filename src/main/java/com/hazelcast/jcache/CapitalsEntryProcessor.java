package com.hazelcast.jcache;

import com.hazelcast.jcache.processor.UpperCaseEntryProcessor;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.processor.EntryProcessor;
import javax.cache.spi.CachingProvider;

/**
 * Caching Provider Example
 */
public class CapitalsEntryProcessor {

    private static final String CACHING_PROVIDER_HAZELCAST =
        "com.hazelcast.cache.HazelcastCachingProvider";


    public static void main(String[] args) {

        // Acquire an explicit cache provider
        CachingProvider cachingProvider = Caching.getCachingProvider(CACHING_PROVIDER_HAZELCAST);

        // Acquire the default cache manager
        CacheManager manager = cachingProvider.getCacheManager();

        // Define a cache
        MutableConfiguration<String, String> cacheConfig =
            new MutableConfiguration<String, String>()
                .setStoreByValue(true)
                .setTypes(String.class, String.class);

        // Create the cache
        Cache<String, String> cache = manager.createCache("capitals", cacheConfig);

        // Enter some Capitals
        cache.put("UK", "London");
        cache.put("France", "Paris");
        cache.put("Spain", "Madrid");
        cache.put("Belgium", "Brussels");
        cache.put("Germany", "Berlin");

        // Convert Capitals to Upper Case
        EntryProcessor<String, String, Object> entryProcessor =
            new UpperCaseEntryProcessor();
        cache.invoke("Belgium", entryProcessor);

        // Now Check capital
        System.out.println("Capital of Belgium is : " + cache.get("Belgium"));

    }

}
