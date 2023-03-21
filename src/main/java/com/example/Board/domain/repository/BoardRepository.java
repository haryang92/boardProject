package com.example.Board.domain.repository;

import com.example.Board.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    BoardEntity save(BoardEntity boardEntity);

    Optional<BoardEntity> findById(Long id);

    List<BoardEntity> findAll();

    void deleteById(Long id);
}
