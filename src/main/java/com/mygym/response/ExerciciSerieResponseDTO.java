package com.mygym.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciciSerieResponseDTO {
    private String Id;
    private String nom;
    private String descripcio;

    private String grupMuscular;

    private Integer serie;


    public ExerciciSerieResponseDTO(){};

    public ExerciciSerieResponseDTO(String Id, String nom, String descripcio, String grupMuscular, Integer serie){
        this.Id=Id;
        this.nom=nom;
        this.descripcio=descripcio;
        this.grupMuscular=grupMuscular;
        this.serie=serie;
    }
}
