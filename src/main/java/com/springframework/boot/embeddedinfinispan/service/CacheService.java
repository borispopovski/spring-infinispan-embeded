package com.springframework.boot.embeddedinfinispan.service;

import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

  private static final String CACHE_NAME = "testCache";

  private final EmbeddedCacheManager cacheManager;

  @Autowired
  public CacheService(EmbeddedCacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }

  @Cacheable(value = "testCache", key = "#key", unless ="#result==null")
  public String getName(String key){
    return cacheManager.getCache(CACHE_NAME).get(key).toString();
  }

  @CachePut(value = "testCache", key = "#key")
  public String addName(String key, String name){
    cacheManager.getCache(CACHE_NAME).put(key, name);
    return cacheManager.getCache(CACHE_NAME).get(key).toString();
  }
}
