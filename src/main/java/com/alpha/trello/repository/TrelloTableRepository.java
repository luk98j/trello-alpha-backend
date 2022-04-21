package com.alpha.trello.repository;

import com.alpha.trello.entity.TrelloTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrelloTableRepository extends JpaRepository<TrelloTable,Long> {

}
