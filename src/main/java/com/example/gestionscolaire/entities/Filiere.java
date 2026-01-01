package com.example.gestionscolaire.entities;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

import lombok.Data;


@Data
@Entity
public class Filiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
      @Column(unique = true, nullable = false)
    private String code;
    private String nom;

    @OneToMany(mappedBy = "filiere")
    @JsonIgnore
    private List<Eleve> eleves = new ArrayList<>();

@OneToMany(
    mappedBy = "filiere",
    cascade = CascadeType.ALL,
    orphanRemoval = true
)
private List<Cours> cours = new ArrayList<>();

}
