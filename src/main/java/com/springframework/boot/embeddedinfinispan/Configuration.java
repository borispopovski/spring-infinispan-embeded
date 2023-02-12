package com.springframework.boot.embeddedinfinispan;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.eviction.EvictionType;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.infinispan.spring.starter.embedded.InfinispanConfigurationCustomizer;
import org.infinispan.spring.starter.embedded.InfinispanGlobalConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@org.springframework.context.annotation.Configuration
public class Configuration {

  @Bean
  @Primary
  public InfinispanGlobalConfigurationCustomizer globalCustomizer() {
    return builder -> builder
        .transport()
        .defaultTransport()
        .clusterName("TEST_CLUSTER").nodeName("TEST_NODE");
  }

  @Bean
  public InfinispanConfigurationCustomizer configurationCustomizer() {
    return builder -> builder.memory().evictionType(EvictionType.COUNT);
  }

  @Bean
  public InfinispanCacheConfigurer cacheConfigurer() {
    return manager -> {
      final org.infinispan.configuration.cache.Configuration ispnConfig = new ConfigurationBuilder()
          .clustering()
          .cacheMode(CacheMode.DIST_SYNC)
          .hash()
          .numOwners(3)
          .build();
      manager.defineConfiguration("testCache", ispnConfig);
    };
  }
}
