package com.alpha.trello.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(	name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "username")
    private String username;

//    @NotBlank
//    @Size(max = 50)
//    private String country;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    @Column(name="last_name")
    private String lastName;

//    @JsonFormat(pattern="yyyy-mm-dd")
//    @Column(name="birth_date")
//    private Date birthDate;

    @NotBlank
    @Size(max = 120)
    @Column
    private String password;

//    @Column
//    private Boolean enabled;

//    @Column(name="confirmationID")
//    private String confirmationID;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_trello_table",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "table_id"))
    private Set<TrelloTable> trelloTables = new HashSet<>();

//    @JsonFormat(pattern="yyyy-mm-dd")
//    @Column(name="timestamp")
//    private Date timeStamp;

    public User() {
    }

    public User(String username, String password, String name, String lastName) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
    }

}