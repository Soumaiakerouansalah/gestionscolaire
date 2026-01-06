package com.example.gestionscolaire.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.gestionscolaire.entities.Filiere;
import com.example.gestionscolaire.repositories.FiliereRepository;

@Service
public class FiliereService {

    private final FiliereRepository filiereRepo;

    public FiliereService(FiliereRepository filiereRepo) {
        this.filiereRepo = filiereRepo;
    }

    // 🔹 Lister toutes les filières
    public List<Filiere> findAll() {
        return filiereRepo.findAll();
    }

    // 🔹 Sauvegarder une filière
    public Filiere save(Filiere filiere) throws DataIntegrityViolationException {
        return filiereRepo.save(filiere);
    }

    // 🔹 Trouver par id
    public Filiere findById(Long id) {
        return filiereRepo.findById(id).orElseThrow();
    }

    // 🔹 Supprimer
    public void deleteById(Long id) {
        filiereRepo.deleteById(id);
    }
}
