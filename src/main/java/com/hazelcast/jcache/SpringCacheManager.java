package com.hazelcast.jcache;

import com.hazelcast.jcache.spring.AppConfig;
import com.hazelcast.jcache.spring.IDummyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import java.util.concurrent.TimeUnit;

/**
 * Example of Spring Cache manager configuration
 */
public class SpringCacheManager {
    public static void main(String[] args) {
        System.setProperty("hazelcast.jcache.provider.type", "client");
        ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);

        IDummyBean dummy = (IDummyBean) applicationContext.getBean("dummyBean");

        long start1 = System.nanoTime();
        String city = dummy.getCity();
        long end1 = System.nanoTime();
        System.out.println("First call tool: " + TimeUnit.NANOSECONDS.toMillis(end1 - start1) + " millis");

        long start2 = System.nanoTime();
        city = dummy.getCity();
        long end2 = System.nanoTime();
        System.out.println("Second call tool: " + TimeUnit.NANOSECONDS.toMillis(end2 - start2) + " millis");

    }
}
