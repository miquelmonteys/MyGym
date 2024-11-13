package com.mygym.models;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mygym.security.Auditable;
import jakarta.persistence.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Document(collection = "historicRutines")
public class HistoricRutina extends Auditable {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private Date data;
    private double tempsTotal;
    private List<Exercici> exercicis;

    // Constructor sense paràmetres
    public HistoricRutina() {
    }

    // Constructor amb paràmetres
    public HistoricRutina(ObjectId id, Date data, double tempsTotal) {
        this.id = id;
        this.data = data;
        this.tempsTotal = tempsTotal;
    }

    // Getters i setters

    public void setData(Date data) {
        this.data = data;
    }
}

