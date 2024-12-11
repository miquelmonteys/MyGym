package com.mygym.services;

import com.mygym.models.Rutina;
import com.mygym.models.User;
import com.mygym.repository.RutinaRepository;
import com.mygym.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RutinaRepository rutinaRepository;

    @Autowired
    public UserService(UserRepository userRepository, RutinaRepository rutinaRepository) {
        this.userRepository = userRepository;
        this.rutinaRepository = rutinaRepository;
    }

    // Codi per a favoritos
    public Rutina findRoutineById(ObjectId rutinaId) {
        return rutinaRepository.findById(rutinaId).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        user.setNew(true);
        return userRepository.save(user);
    }

    public User updateUser(ObjectId id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setRoles(updatedUser.getRoles());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public void deleteUser(ObjectId id) {
        userRepository.deleteById(id);
    }

    /* Part favoritos */

    // Codi per a afegir a favoritos una nova rutina.
    public void addFavoriteRoutine(User user, Rutina rutina) {
        user.getRutinesFavoritos().add(rutina.getId());
        userRepository.save(user);
    }

    // Codi per a borrar de favoritos la rutina.
    public void removeFavoriteRoutine(User user, Rutina rutina) {
        user.getRutinesFavoritos().removeIf(r -> r.equals(rutina.getId()));
        userRepository.save(user); // Guarda l'usuari actualitzat a la base de dades
    }

    public User getFirstUser() {
        return userRepository.findAll().stream().findFirst().orElse(null);
    }
}
