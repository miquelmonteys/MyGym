package com.mygym.controllers;

import com.mygym.models.Rutina;
import com.mygym.models.User;
import com.mygym.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
        /*if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        */

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


    @GetMapping("/{id}/favorites")
    public ResponseEntity<Set<Rutina>> getFavoriteRoutines(@PathVariable ObjectId id) {
        User user = userService.getUserById(id).orElse(null);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user.getRutinesFavoritos());
        }

    @PostMapping("/{id}/favorites/{routineId}")
    public ResponseEntity<Void> addFavoriteRoutine(@PathVariable ObjectId id, @PathVariable ObjectId routineId) {
        User user = userService.getUserById(id).orElse(null);
        Rutina rutina = userService.findRoutineById(routineId);
        if (user == null || rutina == null) return ResponseEntity.notFound().build();

        userService.addFavoriteRoutine(user, rutina);
        return ResponseEntity.ok().build();
    }

    //No acaba de borrar el que be a ser les rutines de favoritos.
    @DeleteMapping("/{id}/favorites/{routineId}")
        public ResponseEntity<Void> removeFavoriteRoutine(@PathVariable ObjectId id, @PathVariable ObjectId routineId) {
            User user = userService.getUserById(id).orElse(null);
            Rutina rutina = userService.findRoutineById(routineId);
            if (user == null || rutina == null) return ResponseEntity.notFound().build();

            userService.removeFavoriteRoutine(user, rutina);
            return ResponseEntity.ok().build();
        }
    }

