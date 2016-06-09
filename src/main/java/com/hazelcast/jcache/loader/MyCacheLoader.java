package com.hazelcast.jcache.loader;

import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;
import java.util.HashMap;
import java.util.Map;

/**
 * Sample of a Read Through CacheLoader
 */
public class MyCacheLoader implements CacheLoader<String, String> {

    Map<String, String> capitalsMap = new HashMap<String, String>() {{
        put("Portugal", "Lisbon");
        put("UK", "London");
        put("France", "Paris");
        put("Spain", "Madrid");
        put("Belgium", "Brussels");
        put("Germany", "Berlin");
    }};

    public String load(String key) throws CacheLoaderException {
        System.out.println("loading for key=" + key);
        return getCapitalsDao().get(key);
    }

    /**
     * Async Load Up of a Cache.
     */
    public Map<String, String> loadAll(Iterable<? extends String> keys)
        throws CacheLoaderException {

        System.out.println("loading all");
        // Iterate the keys and maybe batch load from a DB,
        // a Filesystem or some other System completely.
        Map<String, String> result = new HashMap<>();
        keys.forEach(key -> {
            result.put(key, getCapitalsDao().get(key));
        });
        return result;
    }

    private Map<String, String> getCapitalsDao() {
        return this.capitalsMap;
    }
}
