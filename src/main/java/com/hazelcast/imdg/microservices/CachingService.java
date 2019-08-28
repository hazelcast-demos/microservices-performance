package com.hazelcast.imdg.microservices;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CachingService {

    private final PersonRepository repo;

    public CachingService(PersonRepository repo) {
        this.repo = repo;
    }

    // No list put in the cache
    // https://github.com/spring-projects/spring-framework/issues/19777

    @Cacheable("service")
    public Person getPerson(Long id) {
        return repo.findById(id).orElse(null);
    }
}
