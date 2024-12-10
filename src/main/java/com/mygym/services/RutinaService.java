package com.mygym.services;

import com.mygym.models.Rutina;
import com.mygym.models.User;
import com.mygym.repository.RutinaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mygym.response.RutinaResponseDTO;
import org.bson.types.ObjectId;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RutinaService {

    @Autowired
    private RutinaRepository rutinaRepository;
    @Autowired
    private UserService userService;

    public Rutina creaRutina(Rutina rutina) {
        return rutinaRepository.save(rutina);
    }

    public List<Rutina> getRutines() {
        return rutinaRepository.findAll();
    }

    public Rutina afegirExercicisARutina(
        ObjectId id,
        List<ObjectId> exercicis
    ) {
        Rutina rutina = rutinaRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Rutina no trobada"));
        rutina.setExercicis(exercicis);
        return rutinaRepository.save(rutina);
    }

    public Rutina treureExercicisDeRutina(ObjectId id, List<String> exercicis) {
        Rutina rutina = rutinaRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Rutina no trobada"));
        rutina.getExercicis().removeAll(exercicis);
        return rutinaRepository.save(rutina);
    }

    public List<Rutina> findByNomRutina(String nomRutina) {
        return rutinaRepository.findByNomRutina(nomRutina);
    }

    public List<RutinaResponseDTO> getRutinesDefault() {
        return rutinaRepository.findByIsDefaultTrue().stream().map(rutina -> new RutinaResponseDTO(rutina)).collect(Collectors.toList());
    }

    public List<RutinaResponseDTO> getRutinesPropies() {
        User u = userService.getFirstUser();
        List<Rutina> rutines = rutinaRepository.findMultipleById(u.getRutinesPropies());
        return rutines.stream().map(rutina -> new RutinaResponseDTO(rutina)).collect(Collectors.toList());
    }


    /*
    public List<Rutina> searchRutines(String nom, String grupMuscular) {
        if (nom != null && grupMuscular != null) {
            return rutinaRepository.findByNomContainingAndGrupMuscular(nom, grupMuscular);
        } else if (nom != null) {
            return rutinaRepository.findByNomContaining(nom);
        } else if (grupMuscular != null) {
            return rutinaRepository.findByGrupMuscular(grupMuscular);
        } else {
            return rutinaRepository.findAll();
        }
    }

     */
}
