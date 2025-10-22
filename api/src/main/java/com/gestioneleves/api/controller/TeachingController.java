package com.gestioneleves.api.controller;

import com.gestioneleves.api.entity.Teaching;
import com.gestioneleves.api.service.TeachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachings")
public class TeachingController {

    @Autowired
    private TeachingService service;

    @GetMapping
    public List<Teaching> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teaching> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Teaching create(@RequestBody Teaching teaching) {
        return service.save(teaching);
    }

    @PutMapping
    public ResponseEntity<Teaching> update(@PathVariable Long id, @RequestBody Teaching teaching) {
        return service.findById(id)
                .map(existing -> {
                    teaching.setId(id);
                    return ResponseEntity.ok(service.save(teaching));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}