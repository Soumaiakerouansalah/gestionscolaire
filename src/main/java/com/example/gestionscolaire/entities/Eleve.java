package com.example.gestionscolaire.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;

import lombok.Data;


@Entity
@Data

public class Eleve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;

@ManyToMany
@JoinTable(
    name = "eleve_cours",
    joinColumns = @JoinColumn(name = "eleve_id"),
    inverseJoinColumns = @JoinColumn(name = "cours_id")
)
private List<Cours> cours = new ArrayList<>();

   @OneToOne(
    mappedBy = "eleve",
    cascade = CascadeType.ALL,
    orphanRemoval = true
)
private DossierAdministratif dossierAdministratif;

}
