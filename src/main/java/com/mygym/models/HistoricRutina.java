package com.mygym.models;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mygym.request.HistoricRutinaRequestDTO;
import com.mygym.request.RutinaRequestDTO;
import com.mygym.security.Auditable;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@Document(collection = "historicRutines")
public class HistoricRutina extends Auditable {

    @JsonSerialize(using = ToStringSerializer.class)
    @Id
    private ObjectId id;

    private Date data;
    private List<ExerciciDetail> exercicis;

    // Constructor sense paràmetres
    public HistoricRutina() {
    }

    // Constructor amb paràmetres
    public HistoricRutina(ObjectId id, Date data, List<ExerciciDetail> exercicis) {
        this.id = id;
        this.data = data;
        this.exercicis = exercicis;
    }

    public HistoricRutina(HistoricRutinaRequestDTO historic) {
        this.data = new Date();
        this.exercicis = historic.getExercicis();
    }

}

