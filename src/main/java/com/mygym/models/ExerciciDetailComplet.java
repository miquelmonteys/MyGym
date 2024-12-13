package com.mygym.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class ExerciciDetailComplet {

    private Exercici exercici;
    private int series;
    private List<Integer> descans;
    private List<Integer> pes;
    private List<Integer> reps;

    public ExerciciDetailComplet() {}
    public ExerciciDetailComplet(ExerciciDetail exerciciDetail, Optional<Exercici> exercici) {
        this.exercici = exercici.orElse(null);
        this.series = exerciciDetail.getSeries();
        this.descans = exerciciDetail.getDescans();
        this.pes = exerciciDetail.getPes();
        this.reps = exerciciDetail.getReps();
    }

}
