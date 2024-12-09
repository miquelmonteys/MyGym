package com.mygym.controllers;

import com.mygym.models.Rutina;
import com.mygym.response.RutinaResponseDTO;
import com.mygym.services.RutinaService;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rutines")
public class RutinaController {

    @Autowired
    private RutinaService rutinaService;

    @PostMapping
    public Rutina creaRutina(@RequestBody Rutina rutina) {
        String nomRutina = rutina.getNomRutina();

        List<Rutina> rutinesExistents = rutinaService.findByNomRutina(
            nomRutina
        );

        int maxNumero = 0;
        for (Rutina r : rutinesExistents) {
            String codiImatge = r.getCodiImatgeRutina();
            if (codiImatge != null && codiImatge.startsWith(nomRutina + "_")) {
                try {
                    int numero = Integer.parseInt(
                        codiImatge.substring(nomRutina.length() + 1)
                    );
                    if (numero > maxNumero) {
                        maxNumero = numero;
                    }
                } catch (NumberFormatException e) {}
            }
        }

        String nouCodiImatge = nomRutina + "_" + (maxNumero + 1);
        rutina.setCodiImatge(nouCodiImatge);

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
