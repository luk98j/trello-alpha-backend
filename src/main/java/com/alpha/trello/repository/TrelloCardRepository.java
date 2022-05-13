package com.alpha.trello.repository;

import com.alpha.trello.entity.TrelloCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrelloCardRepository extends JpaRepository<TrelloCard, Long> {
}
