package com.mygym.controllers;

import com.mygym.models.Rutina;
import com.mygym.models.User;
import com.mygym.response.RutinaResponseDTO;
import com.mygym.services.RutinaService;
import com.mygym.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final RutinaService rutinaService;

    @Autowired
    public UserController(UserService userService, RutinaService rutinaService) {
        this.userService = userService;
        this.rutinaService = rutinaService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable ObjectId id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable ObjectId id, @RequestBody User updatedUser) {
        try {
            User user = userService.updateUser(id, updatedUser);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable ObjectId id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/favorites")
    public List<String> getFavoriteRoutines() {
        User user = userService.getFirstUser();
        return user.getRutinesFavoritos().stream().map(ObjectId::toString).collect(Collectors.toList());
    }

    @PostMapping("favorites/{routineId}")
    public ResponseEntity<Void> addFavoriteRoutine(@PathVariable ObjectId routineId) {
        User user = userService.getFirstUser();
        Rutina rutina = userService.findRoutineById(routineId);
        if (user == null || rutina == null) return ResponseEntity.notFound().build();

        userService.addFavoriteRoutine(user, rutina);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("favorites/{routineId}")
        public ResponseEntity<Void> removeFavoriteRoutine(@PathVariable ObjectId routineId) {
            User user = userService.getFirstUser();
            Rutina rutina = userService.findRoutineById(routineId);
            if (user == null || rutina == null) return ResponseEntity.notFound().build();

            userService.removeFavoriteRoutine(user, rutina);
            return ResponseEntity.ok().build();
        }
    }

