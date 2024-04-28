package com.example.runnerz.run;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository repository;

    public RunController(RunRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    List<Run> findAll() {
        return repository.findAll();
    }



    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        Optional<Run> byId = repository.findById(id);
        if (byId.isEmpty()) {
            throw new RunNotFoundException()    ;
        } else {
            return byId.get();
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody Run run) {
        repository.create(run);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@Valid @RequestBody Run run, @PathVariable Integer id) {
        repository.update(run, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        repository.delete(id);
    }

}
