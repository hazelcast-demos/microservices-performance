package org.hazelcast.microservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/d")
public class PersonController {

    private final PersonRepository repo;

    public PersonController(PersonRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public List<Person> getAllPersons() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") Long id) {
        return repo.findById(id).orElse(null);
    }
}
