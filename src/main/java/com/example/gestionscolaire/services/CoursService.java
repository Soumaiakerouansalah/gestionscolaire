package com.example.gestionscolaire.services;



import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gestionscolaire.entities.Cours;
import com.example.gestionscolaire.repositories.CoursRepository;

@Service
public class CoursService {

    private final CoursRepository coursRepo;

    public CoursService(CoursRepository coursRepo) {
        this.coursRepo = coursRepo;
    }


    public List<Cours> findAll() {
        return coursRepo.findAll();
    }


    public Cours save(Cours cours) {
        return coursRepo.save(cours);
    }


    public Cours findById(Long id) {
        return coursRepo.findById(id).orElseThrow();
    }


    public void deleteById(Long id) {
        coursRepo.deleteById(id);
    }

 
    public boolean existsByCode(String code) {
        return coursRepo.existsByCode(code);
    }

 
    public List<Cours> findByFiliereId(Long filiereId) {
        return coursRepo.findByFiliereId(filiereId);
    }
}
