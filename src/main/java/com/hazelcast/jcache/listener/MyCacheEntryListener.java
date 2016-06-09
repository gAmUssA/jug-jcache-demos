package com.hazelcast.jcache.listener;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.event.CacheEntryUpdatedListener;

/**
 * Example of an Created Entry Listener
 */
public class MyCacheEntryListener
    implements CacheEntryCreatedListener<String, String>,
    CacheEntryUpdatedListener<String, String> {

    @Override public void onCreated(Iterable<CacheEntryEvent<? extends String, ? extends String>> events)
        throws CacheEntryListenerException {
        for (CacheEntryEvent event : events) {
            System.out.println("Created : " + event.getKey() + " with value : " + event.getValue());
        }
    }

    @Override public void onUpdated(Iterable<CacheEntryEvent<? extends String, ? extends String>> events)
        throws CacheEntryListenerException {
        for (CacheEntryEvent event : events) {
            System.out.println("Updated : " + event.getKey() + " with value : " + event.getValue());
        }
    }
}
