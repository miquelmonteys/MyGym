package com.mygym.repository;

import com.mygym.models.Rutina;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RutinaRepository extends MongoRepository<Rutina, ObjectId> {
    List<Rutina> findByNomRutina(String nomRutina);

    List<Rutina> findByCodiImatge(String codiImatge);
    //Metodes per obtenir Rutines
    //NOU

    /*
    List<Rutina> findByNomContaining(String nom);

    List<Rutina> findByGrupMuscular(String grupMuscular);

    List<Rutina> findByNomContainingAndGrupMuscular(String nom, String grupMuscular);
    */

}
