package com.alpha.trello.repository;

import com.alpha.trello.entity.TrelloTodoTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrelloTodoTaskRepository extends JpaRepository<TrelloTodoTask, Long> {
}
