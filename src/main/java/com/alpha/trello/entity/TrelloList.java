package com.alpha.trello.entity;

import lombok.*;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="trello_list")
public class TrelloList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    public TrelloList() {
    }

    public TrelloList(String title, Integer table) {
        this.title = title;
    }

}
