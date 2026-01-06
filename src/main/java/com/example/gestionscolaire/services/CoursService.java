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

    // 🔹 Lister tous les cours
    public List<Cours> findAll() {
        return coursRepo.findAll();
    }

    // 🔹 Sauvegarder un cours
    public Cours save(Cours cours) {
        return coursRepo.save(cours);
    }

    // 🔹 Trouver par id
    public Cours findById(Long id) {
        return coursRepo.findById(id).orElseThrow();
    }

    // 🔹 Supprimer
    public void deleteById(Long id) {
        coursRepo.deleteById(id);
    }

    // 🔹 Vérifier si le code existe
    public boolean existsByCode(String code) {
        return coursRepo.existsByCode(code);
    }

    // 🔹 Cours par filière
    public List<Cours> findByFiliereId(Long filiereId) {
        return coursRepo.findByFiliereId(filiereId);
    }
}
