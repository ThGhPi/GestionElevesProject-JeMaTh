package com.gestioneleves.api.service;

import com.gestioneleves.api.entity.Teaching;
import com.gestioneleves.api.repository.TeachingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeachingService {
    private final TeachingRepository teachings;

    public TeachingService(TeachingRepository teachings) {
        this.teachings = teachings;
    }

    public List<Teaching> getTeachings() {
        return teachings.findAll();
    }
    public Optional<Teaching> getTeaching(Long id) {
        return teachings.findById(id);
    }
    public Teaching saveTeaching(Teaching teaching) {
        return teachings.save(teaching);
    }
    public void deleteTeaching(Long id) {
        teachings.deleteById(id);
    }
}