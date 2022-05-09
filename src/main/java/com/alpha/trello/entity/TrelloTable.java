package com.alpha.trello.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="trello_table")
@Builder
public class TrelloTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "trello_table_trello_list")
    private Set<TrelloList> trelloList = new HashSet<>();
}
