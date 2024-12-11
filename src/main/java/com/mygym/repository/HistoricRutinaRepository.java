package com.mygym.repository;

import com.mygym.models.HistoricRutina;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.bson.types.ObjectId;

public interface HistoricRutinaRepository extends MongoRepository<HistoricRutina, ObjectId> {
}

