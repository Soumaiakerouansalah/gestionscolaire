package com.example.gestionscolaire.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class DossierAdministratif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroInscription;
    private LocalDate dateCreation;

    @OneToOne
    @JoinColumn(name = "eleve_id")
    private Eleve eleve;
}
