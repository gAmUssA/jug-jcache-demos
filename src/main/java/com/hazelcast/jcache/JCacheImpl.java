package com.hazelcast.jcache;

import com.hazelcast.cache.ICache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

/**
 * Created by vikgamov on 2/8/16.
 */
public class JCacheImpl {
    //private static final String CACHING_PROVIDER_CLASS = "org.ehcache.jcache.JCacheCachingProvider";
     private static final String CACHING_PROVIDER_CLASS = "com.hazelcast.cache.HazelcastCachingProvider";
    // private static final String CACHING_PROVIDER_CLASS = "com.tangosol.coherence.jcache.CoherenceBasedCachingProvider";

    public static void main(String[] args) {
        //CachingProvider cachingProvider = Caching.getCachingProvider();
        CachingProvider cachingProvider = Caching.getCachingProvider(CACHING_PROVIDER_CLASS);
        Iterable<CachingProvider> cachingProviders = Caching.getCachingProviders();
        for (CachingProvider provider : cachingProviders) {
            System.out.println(provider.toString());
        }
        MutableConfiguration<Integer, String> configuration = new MutableConfiguration<>();
        configuration.setTypes(Integer.class, String.class)
                .setManagementEnabled(true)
                .setStatisticsEnabled(true);


        CacheManager cacheManager = cachingProvider.getCacheManager();
        Cache<Integer, String> test = cacheManager.createCache("test", configuration);
        test.put(1, "NY");
        test.put(2, "OH");
        test.put(3, "FL");

    }
}
