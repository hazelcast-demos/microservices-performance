package com.hazelcast.imdg.microservices;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HazelcastDemo {

    @Bean
    public Config config() {
        var config = new Config("hazelcastInstance");
        var databaseConfig = new MapConfig("database");
        config.addMapConfig(databaseConfig);
        config.setProperty("jmx.enabled", "true");
        return config;
    }

    public static void main(String[] args) {
        SpringApplication.run(HazelcastDemo.class, args);
    }
}