package com.mygym.repository;

import com.mygym.models.ExerciciSerie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciciSerieReposistory extends MongoRepository<ExerciciSerie, String> {

}
