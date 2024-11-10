package com.mygym.models;

import com.mygym.security.Auditable;
import jakarta.persistence.Id;

public class Exercici extends Auditable{
    @Id
    private String id;
    private String nom;
    private String descripcio;
    private String grupMuscular;

    // Getters i setters
}
