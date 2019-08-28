package com.hazelcast.imdg.microservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/r")
public class ProxyController {

    private final CachingService service;

    public ProxyController(CachingService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") Long id) {
        return service.getPerson(id);
    }
}
