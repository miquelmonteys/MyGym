package com.mygym.controllers;

import com.mygym.models.Rutina;
import com.mygym.request.RutinaRequestDTO;
import com.mygym.response.RutinaResponseDTO;
import com.mygym.services.RutinaService;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rutines")
public class RutinaController {

    @Autowired
    private RutinaService rutinaService;

    @PostMapping("")
    public Rutina creaRutina(@RequestBody RutinaRequestDTO rutina) {
        return rutinaService.creaRutina(rutina);
    }

    @GetMapping
    public List<Rutina> getRutines() {
        return rutinaService.getRutines();
    }

    @GetMapping("/default")
    public List<RutinaResponseDTO> getRutinesDefault() {
        return rutinaService.getRutinesDefault();
    }

    @GetMapping("/propies")
    public List<RutinaResponseDTO> getRutinesPropies() {
        return rutinaService.getRutinesPropies();
    }
    @GetMapping("/{id}")
    public Optional<RutinaResponseDTO> getRutina(@PathVariable ObjectId id) { return rutinaService.findByIdRutina(id); }

    @PutMapping("/{id}")
    public Rutina afegirExerciciARutina(
            @PathVariable ObjectId id,
            @RequestBody List<ObjectId> exercicis
    ) {
        return rutinaService.afegirExercicisARutina(id, exercicis);
    }

    @PutMapping("/{id}/removeExercicis")
    public Rutina treureExercicisDeRutina(
            @PathVariable ObjectId id,
            @RequestBody List<String> exercicis
    ) {
        return rutinaService.treureExercicisDeRutina(id, exercicis);
    }
    // @GetMapping("/{id}/imatge")
    // public ResponseEntity<String> getImatgePath(@PathVariable Long id) {
    //     try {
    //         String imagePath = rutinaService.getImatgePath(id);
    //         return ResponseEntity.ok(imagePath);
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
    //             e.getMessage()
    //         );
    //     }
    // }
    //NOU

    /*
    @GetMapping("/search")
    public ResponseEntity<List<Rutina>> searchRutines(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String grupMuscular) {
        List<Rutina> rutines = rutinaService.searchRutines(nom, grupMuscular);
        return ResponseEntity.ok(rutines);
    }
*/
}