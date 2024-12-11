package com.mygym.services;

import com.mygym.models.Rutina;
import com.mygym.models.User;
import com.mygym.repository.RutinaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mygym.repository.UserRepository;
import com.mygym.request.RutinaRequestDTO;
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
    @Autowired
    private UserRepository userRepository;

    public Rutina creaRutina(RutinaRequestDTO rutina) {
        Rutina r = new Rutina(rutina);
        Rutina r2 = rutinaRepository.save(r);
        User u = userService.getFirstUser();
        u.addRutinaPersonal(r.getId());
        userRepository.save(u);
        return r2;
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

    public Optional<RutinaResponseDTO> findByIdRutina(ObjectId id) {
        return Optional.of(new RutinaResponseDTO(rutinaRepository.findById(id).get()));
    }

    public List<RutinaResponseDTO> getRutinesDefault() {
        return rutinaRepository.findByIsDefaultTrue().stream().map(rutina -> new RutinaResponseDTO(rutina)).collect(Collectors.toList());
    }

    public List<RutinaResponseDTO> getRutinesPropies() {
        User u = userService.getFirstUser();
        List<Rutina> rutines = rutinaRepository.findAllByIdIn(u.getRutinesPropies());
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
