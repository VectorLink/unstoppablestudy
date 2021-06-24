package com.unstoppable.unstoppablestudy.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface StudyLocalCache {
    public static final Map<String,Integer> STRING_COUNT_CACHE=new ConcurrentHashMap<>();
}
