package com.mygym.repository;

import com.mygym.models.HistoricRutina;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoricRutinaRepository extends MongoRepository<HistoricRutina, String> {
    //Metodes per obtenir historic Rutines
}
