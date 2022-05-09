package com.alpha.trello.repository;

import com.alpha.trello.entity.TrelloTable;
import com.alpha.trello.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrelloTableRepository extends JpaRepository<TrelloTable,Long> {
    Optional<TrelloTable> findById(Long id);
}
