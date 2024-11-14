package com.mygym.response;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;

@Getter
@Setter
public class RutinaResponseDTO {
    private String id; // ID de la rutina
    private String nom; // Nom de la rutina
    private String descripcio; // Descripció de la rutina
    private Path imagePath;


    public RutinaResponseDTO(){};

    public RutinaResponseDTO(String Id, String nom, String descripcio, Path imagePath){
        this.id=Id;
        this.nom=nom;
        this.descripcio=descripcio;
        this.imagePath=imagePath;
    }
}
