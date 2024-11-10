package com.mygym.models;
import com.mygym.security.Auditable;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "historicRutines")
public class HistoricRutina extends Auditable {
    @Id
    private String id;
    private String data;
    private double tempsTotal;
    private List<Exercici> exercicis;

    // Getters i setters

}

