package com.example.gestionscolaire.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestionscolaire.entities.Eleve;

public interface EleveRepository extends JpaRepository<Eleve, Long> {}
