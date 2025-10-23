package com.gestioneleves.api.service;

import com.gestioneleves.api.entity.AppUser;
import com.gestioneleves.api.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    private final AppUserRepository appUsers;

    public AppUserService(AppUserRepository appUsers) {
        this.appUsers = appUsers;
    }

    public List<AppUser> getAppUsers() {
        return appUsers.findAll();
    }

    public Optional<AppUser> getAppUser(Long id) {
        return appUsers.findById(id);
    }

    public AppUser saveAppUser(AppUser appUser) {
        return appUsers.save(appUser);
    }

    public void deleteAppUser(Long id) {
        appUsers.deleteById(id);
    }
}
