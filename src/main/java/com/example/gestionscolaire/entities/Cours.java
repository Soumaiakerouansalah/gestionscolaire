package com.example.gestionscolaire.entities;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  @Column(unique = true, nullable = false)
private String code;
;
    private String intitule;

  @ManyToOne
@JoinColumn(name = "filiere_id")
@JsonIgnore
private Filiere filiere;

  @ManyToMany(mappedBy = "cours", cascade = CascadeType.ALL)
  @JsonIgnore
private List<Eleve> eleves = new ArrayList<>();

}
