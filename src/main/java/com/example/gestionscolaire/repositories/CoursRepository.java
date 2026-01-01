package com.example.gestionscolaire.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestionscolaire.entities.Cours;

public interface CoursRepository extends JpaRepository<Cours, Long> {

List<Cours> findByFiliereId(Long filiereId);
  boolean existsByCode(String code);

}




