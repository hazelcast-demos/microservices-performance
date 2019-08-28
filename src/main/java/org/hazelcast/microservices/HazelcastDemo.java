package org.hazelcast.microservices;

import com.hazelcast.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class HazelcastDemo {

    @Bean
    public Config config() {
        var config = new Config("hazelcastInstance");
        var cacheConfig = config.getCacheConfig("service");
        cacheConfig.setManagementEnabled(true);
        cacheConfig.setStatisticsEnabled(true);
        config.setProperty("jmx.enabled", "true");
        return config;
    }

    public static void main(String[] args) {
        SpringApplication.run(HazelcastDemo.class, args);
    }
}