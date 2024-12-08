package com.mygym.services;

import com.mygym.models.Exercici;
import com.mygym.models.Serie;
import com.mygym.request.ExerciciSerieRequestDTO;
import com.mygym.response.ExerciciSerieResponseDTO;
import com.mygym.repository.ExerciciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciciSerieService {

    @Autowired
    private ExerciciRepository exerciciRepository;

    /**
     * Afegeix una sèrie a un exercici.
     *
     * @param exerciciId L'ID de l'exercici.
     * @param request    La petició que conté la informació de la sèrie.
     * @return La resposta amb l'estat actualitzat de l'exercici.
     */
    public ExerciciSerieResponseDTO addSerieToExercici(String exerciciId, ExerciciSerieRequestDTO request) {
        Optional<Exercici> optionalExercici = exerciciRepository.findById(exerciciId);

        if (optionalExercici.isEmpty()) {
            throw new IllegalArgumentException("Exercici amb ID " + exerciciId + " no trobat.");
        }

        Exercici exercici = optionalExercici.get();

        // Crear una nova sèrie a partir del request
        Serie serie = new Serie();

        // Afegir la sèrie a l'exercici
        if (exercici.getSeries() == null) {
            exercici.setSeries(new ArrayList<>());
        }
        exercici.getSeries().add(serie);

        // Guardar l'exercici actualitzat
        Exercici updatedExercici = exerciciRepository.save(exercici);

        // Construir la resposta
        return mapToResponse(updatedExercici);
    }


    public ExerciciSerieResponseDTO getExerciciWithSeries(String exerciciId) {
        Optional<Exercici> optionalExercici = exerciciRepository.findById(exerciciId);

        if (optionalExercici.isEmpty()) {
            throw new IllegalArgumentException("Exercici amb ID " + exerciciId + " no trobat.");
        }

        return mapToResponse(optionalExercici.get());
    }

    private ExerciciSerieResponseDTO mapToResponse(Exercici exercici) {
        ExerciciSerieResponseDTO response = new ExerciciSerieResponseDTO();
        response.setId(exercici.getId().toString());
        response.setNom(exercici.getNom());
        response.setGrupMuscular(exercici.getGrupMuscular());
        response.setSerie(exercici.getSeries().size());
        return response;
    }
}
