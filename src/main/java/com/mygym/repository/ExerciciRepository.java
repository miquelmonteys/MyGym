package com.mygym.repository;

import com.mygym.models.Exercici;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciciRepository extends MongoRepository<Exercici, String> {
    List<Exercici> findByNom(String nom);
    //Metodes per obtenir exercicis
}
