package com.mygym.controllers;

import com.mygym.models.HistoricRutina;
import com.mygym.services.HistoricRutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/historics")
public class HistoricRutinaController {
    @Autowired
    private HistoricRutinaService historicRutinaService;

    @PostMapping
    public HistoricRutina creaHistoric(@RequestBody HistoricRutina historicRutina) {
        return historicRutinaService.creaHistoric(historicRutina);
    }

    @GetMapping("/{id}")
    public Optional<HistoricRutina> getHistòrics(@PathVariable String id) {
        return historicRutinaService.getHistorics(id);
    }
}
