package com.mygym.models;

import com.mygym.security.Auditable;

public class Serie extends Auditable {
    private int repeticions;
    private double pes;
    private int descans;

    // Constructor sense paràmetres
    public Serie() {
    }

    // Constructor amb paràmetres
    public Serie(int pes, int repeticions, int descans) {
        this.pes = pes;
        this.repeticions = repeticions;
        this.descans = descans;
    }
    // Getters i setters
}
