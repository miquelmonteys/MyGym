package com.mygym.request;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
public class RutinaRequestDTO {
    private String Id;
    private String nom;
    private String descripcio;
    private List<ExerciciSerieRequestDTO> exercicis; //Guardats per ID d'exercici

    private Double descans;

    private Double duracio;


    public RutinaRequestDTO(){};
    public RutinaRequestDTO(String Id, String nom, String descripcio, List<ExerciciSerieRequestDTO> exercicis, Double descans, Double duracio){
        this.Id=Id;
        this.nom=nom;
        this.descripcio=descripcio;
        this.exercicis=exercicis;
        this.descans=descans;
        this.duracio=duracio;
    }
}
