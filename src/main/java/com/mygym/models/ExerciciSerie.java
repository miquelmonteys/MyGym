package com.mygym.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "exercicis_series")
public class ExerciciSerie {

    @Id
    private String id;
    private String exerciciId;  // Referència a l'ID de l'Exercici
    private String nomExercici; // Nom descriptiu de l'Exercici
    private List<Serie> series;  // Les sèries associades

    // Constructor
    public ExerciciSerie() {}

    public ExerciciSerie(String exerciciId, String nomExercici, List<Serie> series) {
        this.exerciciId = exerciciId;
        this.nomExercici = nomExercici;
        this.series = series;
    }

    // Getters i Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getExerciciId() { return exerciciId; }
    public void setExerciciId(String exerciciId) { this.exerciciId = exerciciId; }

    public String getNomExercici() { return nomExercici; }
    public void setNomExercici(String nomExercici) { this.nomExercici = nomExercici; }

    public List<Serie> getSeries() { return series; }
    public void setSeries(List<Serie> series) { this.series = series; }
}
