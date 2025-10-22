package com.gestioneleves.api.service;

import com.gestioneleves.api.entity.Teaching;
import com.gestioneleves.api.repository.TeachingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeachingService {
    private TeachingRepository teachings;

    public TeachingService(TeachingRepository teachings) {
        this.teachings = teachings;
    }

    public List<Teaching> findAll() {
        return teachings.findAll();
    }
    public Optional<Teaching> findById(Long id) {
        return teachings.findById(id);
    }
    public Teaching save(Teaching teaching) {
        return teachings.save(teaching);
    }
    public void deleteById(Long id) {
        teachings.deleteById(id);
    }
}