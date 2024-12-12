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

    @GetMapping("/rutina/{id}")
    public ResponseEntity<Resource> getRutinaImage(@PathVariable ObjectId id) {
        Rutina rutina = rutinaRepository
                .findById(id)
                .stream()
                .findFirst()
                .orElse(null);
        return getImageResponseEntity(rutina.getCodiImatge());
    }

    @GetMapping("/exercici/{id}")
    public ResponseEntity<Resource> getExerciciImage(@PathVariable String id) {
        Exercici exercici = exerciciRepository
            .findById(id)
            .stream()
            .findFirst()
            .orElse(null);
        return getImageResponseEntity(exercici.getCodiImatge());
    }




    private ResponseEntity<Resource> getImageResponseEntity(String codiImatge) {
        if (codiImatge == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            Path imagePath = Paths.get(IMAGE_BASE_FOLDER + codiImatge + ".jpg");
            Resource resource = new UrlResource(imagePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
