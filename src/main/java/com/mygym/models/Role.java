package com.mygym.models;


import com.mygym.models.ennum.ERole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Role {

    public Role(ERole name) {
        this.name = name;
    }

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    private ERole name;
}