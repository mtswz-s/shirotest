package com.example.springbootshiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @Author 麦田守望者
 * @Date 2020/8/23 15:58
 * @Version 1.0
 */
public class MyRedisCacheManager implements CacheManager {
    /**
     * 参数s为缓存名称例如：authorizationCache
     * @param s
     * @param <K>
     * @param <V>
     * @return
     * @throws CacheException
     */
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        System.out.println(s);
        return new MyRedisCache<K,V>(s);
    }
}
