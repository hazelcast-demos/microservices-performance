package org.hazelcast.microservices;

import javax.cache.annotation.CacheResult;
import org.springframework.stereotype.Service;

@Service
public class CachingService {

    private final PersonRepository repo;

    public CachingService(PersonRepository repo) {
        this.repo = repo;
    }

    // No list put in the cache
    // https://github.com/spring-projects/spring-framework/issues/19777

    @CacheResult(cacheName = "service")
    public Person getPerson(Long id) {
        return repo.findById(id).orElse(null);
    }
}
