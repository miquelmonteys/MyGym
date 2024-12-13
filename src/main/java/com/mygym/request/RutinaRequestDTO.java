package com.mygym.request;

import com.mygym.models.Exercici;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RutinaRequestDTO {
    private String nomRutina;
    private List<ObjectId> exercicis;
    private List<Integer> series;


    public RutinaRequestDTO(){};
}
