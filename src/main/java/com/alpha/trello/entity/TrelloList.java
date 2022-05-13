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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "trello_list_trello_card",
            joinColumns = @JoinColumn(name = "list_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id"))
    private Set<TrelloCard> trelloCardSet = new HashSet<>();

    public TrelloList() {
    }

    public TrelloList(String title) {
        this.title = title;
    }

}
