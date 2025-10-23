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
    public List<Teaching> getTeachings() {
        return service.getTeachings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teaching> getTeaching(@PathVariable Long id) {
        return service.getTeaching(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Teaching create(@RequestBody Teaching teaching) {
        return service.saveTeaching(teaching);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teaching> update(@PathVariable Long id, @RequestBody Teaching teaching) {
        return service.getTeaching(id)
                .map(existing -> {
                    teaching.setId(id);
                    return ResponseEntity.ok(service.saveTeaching(teaching));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.getTeaching(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteTeaching(id);
        return ResponseEntity.noContent().build();
    }
}