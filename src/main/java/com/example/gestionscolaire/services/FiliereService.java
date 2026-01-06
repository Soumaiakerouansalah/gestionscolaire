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

   
    public List<Filiere> findAll() {
        return filiereRepo.findAll();
    }

    public Filiere save(Filiere filiere) throws DataIntegrityViolationException {
        return filiereRepo.save(filiere);
    }

 
    public Filiere findById(Long id) {
        return filiereRepo.findById(id).orElseThrow();
    }


    public void deleteById(Long id) {
        filiereRepo.deleteById(id);
    }
}
