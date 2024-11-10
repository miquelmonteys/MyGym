package com.mygym.repository;

import com.mygym.models.Rutina;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RutinaRepository extends MongoRepository<Rutina, String> {
    //Metodes per obtenir Rutines
}
