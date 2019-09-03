package com.hazelcast.imdg.microservices;

import com.hazelcast.config.Config;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.session.HazelcastSessionManager;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class HazelcastDemo {

    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config("hazelcastInstance");
        MapConfig databaseConfig = new MapConfig("database");
        config.addMapConfig(databaseConfig);
        MapConfig serviceConfig = new MapConfig("service");
        serviceConfig.setInMemoryFormat(InMemoryFormat.OBJECT);
        config.addMapConfig(serviceConfig);
        MapConfig sessionConfig = new MapConfig("empty_session_replication");
        config.addMapConfig(sessionConfig);
        config.setProperty("jmx.enabled", "true");
        return Hazelcast.getOrCreateHazelcastInstance(config);
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> customizeTomcat(HazelcastInstance hazelcastInstance) {
        return (factory) -> {
            factory.addContextCustomizers(context -> {
                HazelcastSessionManager manager = new HazelcastSessionManager();
                manager.setSticky(false);
                manager.setHazelcastInstanceName("hazelcastInstance");
                context.setManager(manager);
            });
        };
    }

    @Bean
    public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
        return new HazelcastCacheManager(hazelcastInstance);
    }

    public static void main(String[] args) {
        SpringApplication.run(HazelcastDemo.class, args);
    }
}