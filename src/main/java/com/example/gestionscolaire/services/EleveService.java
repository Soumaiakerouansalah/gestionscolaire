package com.example.gestionscolaire.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestionscolaire.entities.Cours;
import com.example.gestionscolaire.entities.DossierAdministratif;
import com.example.gestionscolaire.entities.Eleve;
import com.example.gestionscolaire.entities.Filiere;
import com.example.gestionscolaire.repositories.CoursRepository;
import com.example.gestionscolaire.repositories.EleveRepository;
import com.example.gestionscolaire.repositories.FiliereRepository;

@Service
public class EleveService {

    @Autowired
    private EleveRepository eleveRepo;

    @Autowired
    private CoursRepository coursRepo;

    @Autowired
private FiliereRepository filiereRepo;

public void saveEleve(Eleve eleve, List<Long> coursIds) {

    Filiere filiere = filiereRepo
            .findById(eleve.getFiliere().getId())
            .orElseThrow();

    eleve.setFiliere(filiere);

    eleve = eleveRepo.save(eleve);

    if (eleve.getCours() == null) {
        eleve.setCours(new ArrayList<>());
    }
    eleve.getCours().clear();

    if (coursIds != null && !coursIds.isEmpty()) {
        eleve.getCours().addAll(coursRepo.findAllById(coursIds));
    }

    if (eleve.getDossierAdministratif() == null) {

        DossierAdministratif dossier = new DossierAdministratif();
        dossier.setEleve(eleve);
        dossier.setDateCreation(LocalDate.now());

        String codeFiliere = filiere.getCode();
        int annee = LocalDate.now().getYear();

        dossier.setNumeroInscription(
            codeFiliere + "-" + annee + "-" + eleve.getId()
        );

        eleve.setDossierAdministratif(dossier);
    }

    eleveRepo.save(eleve);
}



 // 🔹 Liste des élèves
    public List<Eleve> findAll() {
        return eleveRepo.findAll();
    }

    // 🔹 Trouver par id
    public Eleve findById(Long id) {
        return eleveRepo.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        eleveRepo.deleteById(id);
    }


    public void updateEleve(Eleve eleve) {

        Eleve existing = eleveRepo.findById(eleve.getId())
                .orElseThrow();

        existing.setNom(eleve.getNom());
        existing.setPrenom(eleve.getPrenom());

        eleveRepo.save(existing);
    }
}
