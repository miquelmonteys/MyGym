package com.mygym.models;

import com.mygym.security.Auditable;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "rutines")
public class Rutina extends Auditable {
    @Id
    private String id;
    private String nombre;
    private String descripcio;
    private List<String> exercicis; //Guardats per ID d'exercici

    private Double Descans;

    private Double Duracio;

    public void setExercicis(List<String> exercicis) {
        this.exercicis.addAll(exercicis);
    }

    public List<String> getExercicis() {
        return exercicis;
    }

    // Getters i setters


}