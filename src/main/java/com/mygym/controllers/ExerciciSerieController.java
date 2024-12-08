package com.mygym.controllers;

import com.mygym.request.ExerciciSerieRequestDTO;
import com.mygym.response.ExerciciSerieResponseDTO;
import com.mygym.services.ExerciciSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercicisSeries")
public class ExerciciSerieController {

    @Autowired
    private ExerciciSerieService exerciciSerieService;

    @PostMapping("/{exerciciId}/series")
    public ResponseEntity<ExerciciSerieResponseDTO> addSerieToExercici(
            @PathVariable String exerciciId,
            @RequestBody ExerciciSerieRequestDTO request) {
        ExerciciSerieResponseDTO response = exerciciSerieService.addSerieToExercici(exerciciId, request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{exerciciId}")
    public ResponseEntity<ExerciciSerieResponseDTO> getExerciciWithSeries(@PathVariable String exerciciId) {
        ExerciciSerieResponseDTO response = exerciciSerieService.getExerciciWithSeries(exerciciId);
        return ResponseEntity.ok(response);
    }
}
