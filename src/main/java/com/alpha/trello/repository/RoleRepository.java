package com.alpha.trello.repository;
import java.util.Optional;

import com.alpha.trello.model.authentication.ERole;
import com.alpha.trello.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}