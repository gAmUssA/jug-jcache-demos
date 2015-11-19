package com.hazelcast.jcache.spring;

import javax.cache.annotation.CacheResult;

public interface IDummyBean {

    @CacheResult(cacheName = "city")
    String getCity();
}