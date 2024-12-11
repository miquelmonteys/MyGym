package com.mygym.request;

import com.mygym.models.ExerciciDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class HistoricRutinaRequestDTO {

    private List<ExerciciDetail> exercicis;


    public HistoricRutinaRequestDTO(){};
}
