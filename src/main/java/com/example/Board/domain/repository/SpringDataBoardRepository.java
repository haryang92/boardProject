package com.example.Board.domain.repository;

import com.example.Board.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataBoardRepository extends JpaRepository<BoardEntity, Long>, BoardRepository {

}
