package com.example.springbootshiro.cache;

import com.example.springbootshiro.utils.BeanUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 * @Author 麦田守望者
 * @Date 2020/8/23 16:06
 * @Version 1.0
 */
public class MyRedisCache<K,V> implements Cache<K,V> {

    private String cacheName;

    public MyRedisCache(String cacheName) {
        this.cacheName = cacheName;
    }

    public MyRedisCache() {
    }

    @Override
    public V get(K k) throws CacheException {
        return (V)getRedisTemplate().opsForHash().get(this.cacheName,k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println(this.cacheName+"========"+k.toString());
        getRedisTemplate().opsForHash().put(this.cacheName,k.toString(),v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        System.out.println("==============remove=============");
        System.out.println(k.toString());
        return (V) getRedisTemplate().opsForHash().delete(this.cacheName,k.toString());
    }

    @Override
    public void clear() throws CacheException {
        System.out.println("============clear================");
        getRedisTemplate().delete(this.cacheName);
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(this.cacheName).intValue();
    }

    @Override
    public Set<K> keys() {
        return getRedisTemplate().opsForHash().keys(this.cacheName);
    }

    @Override
    public Collection<V> values() {
        return getRedisTemplate().opsForHash().values(this.cacheName);
    }

    public RedisTemplate getRedisTemplate(){
        Object redisTemplate = BeanUtil.getBean("redisTemplate");
        return (RedisTemplate)redisTemplate;
    }
}
