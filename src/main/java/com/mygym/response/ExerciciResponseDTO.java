package com.mygym.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciciResponseDTO {
    private String Id;
    private String nom;
    private String descripcio;

    private String grupMuscular;

    public ExerciciResponseDTO(){};

    public ExerciciResponseDTO(String Id, String nom, String descripcio, String grupMuscular){
        this.Id=Id;
        this.nom=nom;
        this.descripcio=descripcio;
        this.grupMuscular=grupMuscular;
    }
}
