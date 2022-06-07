package com.alpha.trello.repository;

import java.util.List;
import java.util.Optional;

import com.alpha.trello.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    List<User> findAllByTrelloSharedTablesId(Long id);
}