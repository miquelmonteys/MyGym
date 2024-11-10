package com.mygym.models;

import com.mygym.security.Auditable;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "exercicis")
public class Exercici extends Auditable{
    @Id
    private String id;
    private String nom;
    private String descripcio;
    private String grupMuscular;

    private List<Serie> series;

    public <E> List<E> getSeries() {
        return (List<E>) series;
    }



    // Getters i setters
}

