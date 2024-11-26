package com.mygym.controllers;

import com.mygym.models.Exercici;
import com.mygym.models.Serie;
import com.mygym.services.ExerciciService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercicis")
public class ExerciciController {

    @Autowired
    private ExerciciService exerciciService;

    @PostMapping
    public Exercici creaExercici(@RequestBody Exercici exercici) {
        return exerciciService.creaExercici(exercici);
    }

    @GetMapping
    public List<Exercici> getExercicis() {
        return exerciciService.getExercicis();
    }

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
