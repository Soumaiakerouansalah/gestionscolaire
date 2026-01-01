package com.example.gestionscolaire.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestionscolaire.entities.DossierAdministratif;

public interface DossierRepository extends JpaRepository<DossierAdministratif, Long> {}
