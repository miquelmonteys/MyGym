package com.mygym.controllers;

import com.mygym.models.Exercici;
import com.mygym.models.Rutina;
import com.mygym.models.Serie;
import com.mygym.services.ExerciciService;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercicis")
public class ExerciciController {

    @Autowired
    private ExerciciService exerciciService;

    @PostMapping
    public Exercici creaExercici(@RequestBody Exercici exercici) {
        String nomExercici = exercici.getNom();

        List<Exercici> exercicisExistents = exerciciService.findByNom(
            nomExercici
        );

        int maxNumero = 0;
        for (Exercici ex : exercicisExistents) {
            String codiImatge = ex.getCodiImatge();
            if (
                codiImatge != null && codiImatge.startsWith(nomExercici + "_")
            ) {
                try {
                    int numero = Integer.parseInt(
                        codiImatge.substring(nomExercici.length() + 1)
                    );
                    if (numero > maxNumero) {
                        maxNumero = numero;
                    }
                } catch (NumberFormatException e) {}
            }
        }

        String nouCodiImatge = nomExercici + "_" + (maxNumero + 1);
        exercici.setCodiImatge(nouCodiImatge);

        return exerciciService.creaExercici(exercici);
    }

    @GetMapping
    public List<Exercici> getExercicis() {
        return exerciciService.getExercicis();
    }

    @GetMapping("/{id}")
    public Optional<Exercici> getExercici(@PathVariable ObjectId id) { return exerciciService.findByIdExercici(id); }

    @PutMapping("/{id}")
    public Exercici afegirSeriesAExercici(
        @PathVariable String id,
        @RequestBody List<Serie> series
    ) {
        return exerciciService.afegirSeriesAExercici(id, series);
    }

    @PutMapping("/{id}/removeSeries")
    public Exercici treureSeriesDeExercici(
        @PathVariable String id,
        @RequestBody List<Integer> indicesSeries
    ) {
        return exerciciService.treureSeriesDeExercici(id, indicesSeries);
    }
}
