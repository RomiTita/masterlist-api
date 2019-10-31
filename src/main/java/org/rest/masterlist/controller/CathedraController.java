package org.rest.masterlist.controller;

import lombok.extern.slf4j.Slf4j;
import org.rest.masterlist.model.Cathedra;
import org.rest.masterlist.repository.CathedraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CathedraController {

    @Autowired
    private CathedraRepository repository;

    @PostMapping("cathedra/addCathedra")
    public Cathedra addCathedra(@RequestBody Cathedra cathedra) {
        log.info("trying to ADD a new cathedra");
        return repository.save(cathedra);
    }

    @GetMapping("cathedra/getAllcathedras")
    public List<Cathedra> getAllcathedras() {
        log.info("trying to GET all cathedras");
        return repository.findAll();
    }

}
