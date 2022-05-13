package com.alpha.trello.repository;

import com.alpha.trello.entity.TrelloList;
import com.alpha.trello.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrelloListRepository extends JpaRepository<TrelloList,Long> {

}
