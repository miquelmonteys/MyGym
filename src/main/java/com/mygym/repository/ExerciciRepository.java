package com.mygym.repository;

import com.mygym.models.Exercici;
import java.util.List;
import java.util.Optional;

import com.mygym.models.Rutina;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciciRepository extends MongoRepository<Exercici, String> {
    List<Exercici> findByNom(String nom);
    //Metodes per obtenir exercicis

    Optional<Exercici> findById(ObjectId id);
}
