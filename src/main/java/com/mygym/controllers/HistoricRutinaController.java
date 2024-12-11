package com.mygym.controllers;

import com.mygym.models.HistoricRutina;
import com.mygym.models.Rutina;
import com.mygym.request.HistoricRutinaRequestDTO;
import com.mygym.request.RutinaRequestDTO;
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

    @PostMapping("")
    public HistoricRutina creaHistoric(@RequestBody HistoricRutinaRequestDTO historicRutina) {
        return historicRutinaService.creaHistoric(historicRutina);
    }
    public ResponseEntity<List<HistoricRutina>> getAllHistorics() {
        List<HistoricRutina> historics = historicRutinaService.getAllHistorics();
        return ResponseEntity.ok(historics);
    }
}
