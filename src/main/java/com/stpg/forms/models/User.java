package com.stpg.forms.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.stpg.forms.security.Auditable;
import lombok.Getter;
import lombok.Setter;



import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class User extends Auditable {
    @Id
    private String id;

    @NotBlank
    @Size(max = 50)
    private String username;

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


}