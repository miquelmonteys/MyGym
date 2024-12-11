package com.mygym.repository;

import com.mygym.models.Rutina;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RutinaRepository extends MongoRepository<Rutina, ObjectId> {
    List<Rutina> findByNomRutina(String nomRutina);

    Optional<Rutina> findById(ObjectId id);

    List<Rutina> findByIsDefaultTrue();

    List<Rutina> findMultipleById(List<ObjectId> id);

    List<Rutina> findByCodiImatge(String codiImatge);
    //Metodes per obtenir Rutines
    //NOU

    /*
    List<Rutina> findByNomContaining(String nom);

    List<Rutina> findByGrupMuscular(String grupMuscular);

    List<Rutina> findByNomContainingAndGrupMuscular(String nom, String grupMuscular);
    */

}
