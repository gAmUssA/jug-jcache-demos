package com.hazelcast.jcache.processor;

import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.MutableEntry;
import java.io.Serializable;

public class UpperCaseEntryProcessor implements EntryProcessor<String, String, Object>, Serializable {
    @Override public Object process(MutableEntry<String, String> entry, Object... arguments)
        throws EntryProcessorException {
        if (entry.exists()) {
            entry.setValue(entry.getValue().toUpperCase());
        }
        return null;
    }
}
