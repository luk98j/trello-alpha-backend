package com.alpha.trello.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="trello_todo")
public class TrelloTodo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String Name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "trello_todo_trello_todo_task",
            joinColumns = @JoinColumn(name = "todo_id"),
            inverseJoinColumns = @JoinColumn(name = "todo_task_id"))
    private Set<TrelloTodoTask> trelloTodoTaskSet = new HashSet<>();
}