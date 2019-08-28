package com.hazelcast.imdg.microservices;

import com.hazelcast.config.CacheConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.MapConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
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
    public JCacheManagerCustomizer customizer() {
        return cacheManager -> {
            var config = new CacheConfig<Long, Person>("service");
            config.setKeyType(Long.class);
            config.setValueType(Person.class);
            cacheManager.createCache("service", config);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(HazelcastDemo.class, args);
    }
}