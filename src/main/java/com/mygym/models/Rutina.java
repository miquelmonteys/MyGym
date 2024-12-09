package com.mygym.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mygym.security.Auditable;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "rutines")
public class Rutina extends Auditable {

    @JsonSerialize(using = ToStringSerializer.class)
    @Id
    private ObjectId id;

    private String nomRutina;
    private String descripcio;
    @JsonSerialize(using = ToStringSerializer.class)
    private List<ObjectId> exercicis; //Guardats per ID d'exercici
    private String codiImatgeRutina; //nomRutina_1

    private Double descans;

    private Double duracio;

    private Boolean isDefault;

    // Constructor sense paràmetres (necessari per a MongoDB i Spring Data)
    public Rutina() {
        this.exercicis = new ArrayList<>();
    }

    // Constructor amb paràmetres
    public Rutina(
        ObjectId id,
        String nomRutina,
        String descripcio,
        List<ObjectId> exercicis,
        Double descans,
        Double duracio,
        String codiImatgeRutina
    ) {
        this.id = id;
        this.nomRutina = nomRutina;
        this.descripcio = descripcio;
        this.exercicis = exercicis != null ? exercicis : new ArrayList<>();
        this.descans = descans;
        this.duracio = duracio;
        this.codiImatgeRutina = codiImatgeRutina;
    }

    // Constructor amb paràmetres
    public Rutina(String nom, String descripcio) {
        this.nomRutina = nom;
        this.descripcio = descripcio;
        this.exercicis = new ArrayList<>();
        this.codiImatgeRutina = nom;
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

    public String getNomRutina() {
        return nomRutina;
    }

    public void setNom(String nom) {
        this.nomRutina = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public String getCodiImatge() {
        return codiImatgeRutina;
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

    public void setCodiImatge(String codiImatge) {
        this.codiImatgeRutina = codiImatge;
    }
}
