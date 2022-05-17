package com.alpha.trello.repository;

import com.alpha.trello.entity.TrelloComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrelloCommentRepository extends JpaRepository<TrelloComment, Long> {
}