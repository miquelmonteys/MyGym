package com.mygym.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;


import java.time.ZonedDateTime;
import java.util.*;

@Getter
@Setter
public class User {

    @JsonSerialize(using = ToStringSerializer.class)
    @Id
    private ObjectId id;

    @NotBlank
    @Size(max = 50)
    private String username;

    private List<ObjectId> rutinesFavoritos = new ArrayList<>();

    private List<ObjectId> rutinesPropies = new ArrayList<>();

    private String name;

    private String phoneNumber;

    private String title;

    private boolean hasPfp = false;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    private Set<Role> roles = new HashSet<>();

    private String token;

    private Date dateToken;

    private ZonedDateTime lastAccess;


    private String restorePassword;

    private ZonedDateTime restorePasswordDate;

    private boolean isNew = true;

    private boolean hasAccess = true;



    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void addRutinaPersonal(ObjectId rutinaId){
        this.rutinesPropies.add(rutinaId);
    }

    /* AFEGIR CRIDA DE FAVORITOS, AUTHENTICATION ES UNA CLASSE FETA DE JAVA (S'HA DE PASSAR.)*/





}