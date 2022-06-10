package com.alpha.trello.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="trello_todo_task")
public class TrelloTodoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String Name;

    @Column
    private Boolean checked;
}
