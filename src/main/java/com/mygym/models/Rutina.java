package com.mygym.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mygym.security.Auditable;
import jakarta.persistence.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "rutines")
public class Rutina extends Auditable {
    @JsonSerialize(using = ToStringSerializer.class)
    @Id
    private ObjectId id;
    private String nom;
    private String descripcio;
    private List<ObjectId> exercicis; //Guardats per ID d'exercici

    private Double descans;

    private Double duracio;

    // Constructor sense paràmetres (necessari per a MongoDB i Spring Data)
    public Rutina() {
        this.exercicis = new ArrayList<>();
    }

    // Constructor amb paràmetres
    public Rutina(ObjectId id, String nom, String descripcio, List<ObjectId> exercicis, Double descans, Double duracio) {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.exercicis = exercicis != null ? exercicis : new ArrayList<>();
        this.descans = descans;
        this.duracio = duracio;
    }

    // Constructor amb paràmetres
    public Rutina(String nom, String descripcio) {
        this.nom = nom;
        this.descripcio = descripcio;
        this.exercicis = new ArrayList<>();
    }
    public void setExercicis(List<ObjectId> exercicis) {
        this.exercicis.addAll(exercicis);
    }

    public void addExercici(ObjectId exerciciId) {
        this.exercicis.add(exerciciId);
    }

    public List<ObjectId> getExercicis() {
        return exercicis;
    }

    // Getters i setters

    public ObjectId getId() {
        return id;
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

    public Double getDescans() {
        return descans;
    }

    public void setDescans(Double descans) {
        this.descans = descans;
    }

    public Double getDuracio() {
        return duracio;
    }

    public void setDuracio(Double duracio) {
        this.duracio = duracio;
    }
}