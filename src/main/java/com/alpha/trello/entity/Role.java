package com.alpha.trello.entity;
import javax.persistence.*;


import com.alpha.trello.model.authentication.ERole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column
    private ERole name;

    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }
}