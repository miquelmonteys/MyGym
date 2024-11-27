package com.mygym.services;

import com.mygym.models.Exercici;
import com.mygym.models.Serie;
import com.mygym.repository.ExerciciRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciciService {

    @Autowired
    private ExerciciRepository exerciciRepository;

    public Exercici creaExercici(Exercici exercici) {
        return exerciciRepository.save(exercici);
    }

    public List<Exercici> getExercicis() {
        return exerciciRepository.findAll();
    }

    //He canviat l'string per ObjectID per poder cridar el metode findById correctament
    public Exercici afegirSeriesAExercici(String id, List<Serie> series) {
        Exercici exercici = exerciciRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Exercici no trobat"));
        exercici.getSeries().addAll(series);
        return exerciciRepository.save(exercici);
    }

    //He canviat l'string per ObjectID per poder cridar el metode findById correctament
    public Exercici treureSeriesDeExercici(
        String id,
        List<Integer> indicesSeries
    ) {
        Exercici exercici = exerciciRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Exercici no trobat"));
        List<Serie> series = exercici.getSeries();
        // Eliminar les sèries pels seus índexs
        indicesSeries.forEach(i -> {
            if (i >= 0 && i < series.size()) {
                series.remove(i);
            }
        });
        return exerciciRepository.save(exercici);
    }

    public List<Exercici> findByNom(String nomExercici) {
        return exerciciRepository.findByNom(nomExercici);
    }
}
