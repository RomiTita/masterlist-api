package org.rest.masterlist.controller;

import lombok.extern.slf4j.Slf4j;
import org.rest.masterlist.model.Commission;
import org.rest.masterlist.repository.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CommissionController {

    @Autowired
    private CommissionRepository repository;

    @PostMapping("commission/addCommission")
    public Commission addCommission(@RequestBody Commission commission) {
        log.info("trying to ADD a new commission");
        return repository.save(commission);
    }

    @GetMapping("commission/getAllCommissions")
    public List<Commission> getAllcathedras() {
        log.info("trying to GET all Commissions");
        return repository.findAll();
    }
}
