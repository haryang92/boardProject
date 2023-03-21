package com.example.Board.domain.repository;

import com.example.Board.domain.entity.BoardEntity;

import java.util.*;

public class JpaBoardRepository implements BoardRepository{

    private static Map<Long, BoardEntity> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public BoardEntity save(BoardEntity boardEntity) {
        boardEntity.setId(++sequence);
        store.put(boardEntity.getId(), boardEntity);
        return boardEntity;
    }

    @Override
    public Optional<BoardEntity> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<BoardEntity> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(Long id) {
    }
}
