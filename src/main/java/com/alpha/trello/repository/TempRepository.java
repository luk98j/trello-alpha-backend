package com.alpha.trello.repository;

import com.alpha.trello.dto.TempDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempRepository extends JpaRepository<TempDto, Integer> {
}
