package com.hazelcast.jcache.spring;

import java.util.concurrent.TimeUnit;

public class DummyBean implements IDummyBean {

    @Override
    public String getCity() {
        System.out.println("getCity() called!");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Tallinn";
    }
}
