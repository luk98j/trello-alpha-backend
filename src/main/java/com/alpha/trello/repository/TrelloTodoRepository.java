package com.alpha.trello.repository;

import com.alpha.trello.entity.TrelloTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrelloTodoRepository extends JpaRepository<TrelloTodo, Long> {
}