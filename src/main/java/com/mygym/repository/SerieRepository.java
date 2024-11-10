package com.mygym.repository;

import com.mygym.models.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SerieRepository extends MongoRepository<Serie, Integer> {
    //Metodes per obtenir series
}
