package com.mygym.controllers;

import com.mygym.models.Exercici;
import com.mygym.models.Rutina;
import com.mygym.repository.*;
import com.mygym.repository.RutinaRepository;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private static final String IMAGE_BASE_FOLDER = "C:/MyGym/";

    @Autowired
    private ExerciciRepository exerciciRepository;

    @Autowired
    private RutinaRepository rutinaRepository;

    @GetMapping("/exercici/{id}")
    public ResponseEntity<Resource> getExerciciImage(@PathVariable String id) {
        Exercici exercici = exerciciRepository
            .findById(id)
            .stream()
            .findFirst()
            .orElse(null);
        return getImageResponseEntity(exercici.getCodiImatge());
    }

    @GetMapping("/rutina/{id}")
    public ResponseEntity<Resource> getRutinaImage(@PathVariable ObjectId id) {
        Rutina rutina = rutinaRepository
            .findById(id)
            .stream()
            .findFirst()
            .orElse(null);

        return getImageResponseEntity(rutina.getCodiImatge());
    }

    //FALTA VEURE BÉ COM RETORNAR LA IMATGE, SI NOMÉS AMB EL CODI EN TENIM PROU I ARREGLAR BÉ AQUESTES FUNCIONS

    private ResponseEntity<Resource> getImageResponseEntity(String codiImatge) {
        if (codiImatge == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            // Construïm el camí complet de la imatge
            Path imagePath = Paths.get(IMAGE_BASE_FOLDER + codiImatge + ".jpg");

            // Creem el recurs URL per la imatge
            Resource resource = new UrlResource(imagePath.toUri());

            // Verifiquem si el recurs existeix i és llegible
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Imatge tipus JPEG
                    .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.internalServerError().body(null); // Error en la URL
        }
    }
}
