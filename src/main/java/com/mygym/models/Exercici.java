package com.mygym.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mygym.security.Auditable;
import jakarta.persistence.Id;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "exercicis")
public class Exercici extends Auditable {
    @JsonSerialize(using = ToStringSerializer.class)
    @Id
    private ObjectId id;
    private String nom;
    private String descripcio;



    private String grupMuscular;

    private List<Serie> series;

    // Constructor sense paràmetres (necessari per a JPA)
    public Exercici() {
        this.series = new ArrayList<>();
    }
    // Constructor amb paràmetres
    public Exercici(ObjectId id, String nom, String descripcio, String grupMuscular) {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.grupMuscular = grupMuscular;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getGrupMuscular() {
        return grupMuscular;
    }

    public void setGrupMuscular(String grupMuscular) {
        this.grupMuscular = grupMuscular;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    // Getters i setters
    public ObjectId getId() {
        return id;
    }
}