package com.alpha.trello.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "temptable")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TempEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name="name")
    private String name;
}
