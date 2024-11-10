package com.mygym.services;

import com.mygym.models.Rutina;
import com.mygym.repository.RutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutinaService {
    @Autowired
    private RutinaRepository rutinaRepository;

    public Rutina creaRutina(Rutina rutina) {
        return rutinaRepository.save(rutina);
    }

    public List<Rutina> getRutines() {
        return rutinaRepository.findAll();
    }
    public Rutina afegirExercicisARutina(String id, List<String> exercicis) {
        Rutina rutina = rutinaRepository.findById(id).orElseThrow(() -> new RuntimeException("Rutina no trobada"));
        rutina.setExercicis(exercicis);
        return rutinaRepository.save(rutina);
    }

    public Rutina treureExercicisDeRutina(String id, List<String> exercicis) {
        Rutina rutina = rutinaRepository.findById(id).orElseThrow(() -> new RuntimeException("Rutina no trobada"));
        rutina.getExercicis().removeAll(exercicis);
        return rutinaRepository.save(rutina);
    }
}
