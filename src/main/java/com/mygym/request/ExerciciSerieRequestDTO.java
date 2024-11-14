package com.mygym.request;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciciSerieRequestDTO {
    private String Id;
    private Integer serie;


    public ExerciciSerieRequestDTO(){};

    public ExerciciSerieRequestDTO(String Id, Integer serie){
        this.Id=Id;
        this.serie=serie;
    }
}
