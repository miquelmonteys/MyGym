package com.mygym.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ExerciciDetail {

    private ObjectId exerciciId;
    private int series;
    private List<Integer> descans;
    private List<Integer> pes;
    private List<Integer> reps;

}
