package com.mygym.repository;

import com.mygym.models.Exercici;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciciRepository extends MongoRepository<Exercici, String> {
    //Metodes per obtenir exercicis
}
