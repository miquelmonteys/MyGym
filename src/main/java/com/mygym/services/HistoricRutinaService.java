package com.mygym.services;

import com.mygym.models.HistoricRutina;
import com.mygym.repository.HistoricRutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HistoricRutinaService {
    @Autowired
    private HistoricRutinaRepository historicRutinaRepository;

    public HistoricRutina creaHistoric(HistoricRutina historicRutina) {
        return historicRutinaRepository.save(historicRutina);
    }

    public Optional<HistoricRutina> getHistorics(String id) {
        // Suposant que guardem l'històric per usuari o rutina
        return historicRutinaRepository.findById(id);
    }
}
