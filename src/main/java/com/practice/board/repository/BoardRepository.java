package com.practice.board.repository;

import com.practice.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {


    Page<Board> findByTitleContainingOrContentContainingOrWriterContaining
            (String title, String content, String writer, Pageable pageable);

    List<Board> findByTitle(String title);
    List<Board> findByWriter(String writer);
    List<Board> findByContent(String content);
    List<Board> findByTitleOrWriter(String title, String writer);

}
