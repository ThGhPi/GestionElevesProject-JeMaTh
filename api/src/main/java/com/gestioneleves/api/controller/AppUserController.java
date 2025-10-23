package com.gestioneleves.api.controller;

import com.gestioneleves.api.entity.AppUser;
import com.gestioneleves.api.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app-users")
public class AppUserController {

    @Autowired
    private AppUserService service;

    @GetMapping
    public List<AppUser> getAppUsers() {
        return service.getAppUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getAppUser(@PathVariable Long id) {
        return service.getAppUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AppUser create(@RequestBody AppUser appUser) {
        return service.saveAppUser(appUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> update(@PathVariable Long id, @RequestBody AppUser appUser) {
        return service.getAppUser(id)
                .map(existing -> {
                    appUser.setId(id);
                    return ResponseEntity.ok(service.saveAppUser(appUser));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.getAppUser(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteAppUser(id);
        return ResponseEntity.noContent().build();
    }
}
