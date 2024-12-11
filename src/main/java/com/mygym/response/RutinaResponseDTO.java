package com.mygym.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mygym.models.Rutina;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class RutinaResponseDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String nom;
    private String descripcio;
    private String imagePath;
    private List<String> exercisis;
    private String path = "assets/images/prova1.jpg";
    private Boolean isFavourite;


    public RutinaResponseDTO(){};

    public RutinaResponseDTO(Rutina rutina){
        this.id=rutina.getId();
        this.nom=rutina.getNomRutina();
        this.descripcio=rutina.getDescripcio();
        this.imagePath=rutina.getCodiImatge();
        this.exercisis = rutina.getExercicis().stream().map(ObjectId::toString).collect(Collectors.toList());
    }
}
