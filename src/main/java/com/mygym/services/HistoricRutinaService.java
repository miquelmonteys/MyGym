package com.mygym.services;

import com.mygym.models.ExerciciDetailComplet;
import com.mygym.models.HistoricRutina;
import com.mygym.models.Rutina;
import com.mygym.models.User;
import com.mygym.repository.HistoricRutinaRepository;
import com.mygym.request.HistoricRutinaRequestDTO;
import com.mygym.request.RutinaRequestDTO;
import com.mygym.response.RutinaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoricRutinaService {
    @Autowired
    private HistoricRutinaRepository historicRutinaRepository;
    @Autowired
    private ExerciciService exerciciService;

    public HistoricRutina creaHistoric(HistoricRutina historicRutina) {
        return historicRutinaRepository.save(historicRutina);
    }

    public HistoricRutina creaHistoric(HistoricRutinaRequestDTO historic) {
        List<ExerciciDetailComplet> edc = historic.getExercicis().stream().map(exerciciDetail ->
                new ExerciciDetailComplet(exerciciDetail,
                        exerciciService.findByIdExercici(exerciciDetail.getExerciciId()))).collect(Collectors.toList());

        HistoricRutina r = new HistoricRutina(edc);
        return historicRutinaRepository.save(r);
    }

    public List<HistoricRutina> getAllHistorics() {
        return historicRutinaRepository.findAll();
    }
}
