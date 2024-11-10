package com.mygym.models;
import com.mygym.security.Auditable;
import jakarta.persistence.Id;

import java.util.List;

public class HistoricRutina extends Auditable {
    @Id
    private String id;
    private String data;
    private double tempsTotal;
    private List<Exercici> exercicis;

    // Getters i setters

}

