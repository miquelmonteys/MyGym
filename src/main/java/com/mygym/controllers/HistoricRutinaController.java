package com.mygym.controllers;

import com.mygym.models.HistoricRutina;
import com.mygym.services.HistoricRutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historics")
public class HistoricRutinaController {
    @Autowired
    private HistoricRutinaService historicRutinaService;

    @PostMapping
    public ResponseEntity<HistoricRutina> creaHistoric(@RequestBody HistoricRutina historicRutina) {
        HistoricRutina createdHistoric = historicRutinaService.creaHistoric(historicRutina);
        return ResponseEntity.ok(createdHistoric);
    }
    public ResponseEntity<List<HistoricRutina>> getAllHistorics() {
        List<HistoricRutina> historics = historicRutinaService.getAllHistorics();
        return ResponseEntity.ok(historics);
    }
    @GetMapping("/{id}")
    public ResponseEntity<HistoricRutina> getHistoricById(@PathVariable String id) {
        return historicRutinaService.getHistoricsByID(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoric(@PathVariable String id) {
        historicRutinaService.deleteHistoric(id);
        return ResponseEntity.noContent().build();
    }
}
