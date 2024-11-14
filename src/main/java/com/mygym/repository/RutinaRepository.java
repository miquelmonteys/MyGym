package com.mygym.repository;

import com.mygym.models.Rutina;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RutinaRepository extends MongoRepository<Rutina, ObjectId> {
    //Metodes per obtenir Rutines
    //NOU

    /*
    List<Rutina> findByNomContaining(String nom);

    List<Rutina> findByGrupMuscular(String grupMuscular);

    List<Rutina> findByNomContainingAndGrupMuscular(String nom, String grupMuscular);
    */

}
