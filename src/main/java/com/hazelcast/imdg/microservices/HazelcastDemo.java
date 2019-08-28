package com.hazelcast.imdg.microservices;

import com.hazelcast.config.Config;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class HazelcastDemo {

    @Bean
    public Config config() {
        var config = new Config("hazelcastInstance");
        var databaseConfig = new MapConfig("database");
        config.addMapConfig(databaseConfig);
        var serviceConfig = new MapConfig("service");
        serviceConfig.setInMemoryFormat(InMemoryFormat.OBJECT);
        config.addMapConfig(serviceConfig);
        config.setProperty("jmx.enabled", "true");
        return config;
    }

    @Bean
    public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
        return new HazelcastCacheManager(hazelcastInstance);
    }

    public static void main(String[] args) {
        SpringApplication.run(HazelcastDemo.class, args);
    }
}