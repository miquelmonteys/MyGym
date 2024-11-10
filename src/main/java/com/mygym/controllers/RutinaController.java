package com.mygym.controllers;

import com.mygym.models.Rutina;
import com.mygym.services.RutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutines")
public class RutinaController {
    @Autowired
    private RutinaService rutinaService;

    @PostMapping
    public Rutina creaRutina(@RequestBody Rutina rutina) {
        return rutinaService.creaRutina(rutina);
    }

    @GetMapping
    public List<Rutina> getRutines() {
        return rutinaService.getRutines();
    }

    @PutMapping("/{id}")
    public Rutina afegirExerciciARutina(@PathVariable String id, @RequestBody List<String> exercicis) {
        return rutinaService.afegirExercicisARutina(id, exercicis);
    }
    @PutMapping("/{id}/removeExercicis")
    public Rutina treureExercicisDeRutina(@PathVariable String id, @RequestBody List<String> exercicis) {
        return rutinaService.treureExercicisDeRutina(id, exercicis);
    }
}
