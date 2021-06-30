package com.practice.board.repository;

import com.practice.board.entity.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    EntityManager em;

    @Test
    public void write() throws Exception{
        //given
        Board board = new Board();
        board.setTitle("제목");
        board.setWriter("작성자");
        board.setContent("내용");
        board.setCreatedAt(LocalDateTime.now());

        //when
         Board boardId = boardRepository.save(board);
        System.out.println("result:"+boardId);
    }
    @Test
    public void update() throws Exception{
        //given
        Board board = em.find(Board.class, 1L);

        board.setTitle("수정된 제목");
    }
    @Test
    public void delete() throws Exception{
        //given
        Board board = em.find(Board.class, 1L);

        boardRepository.deleteById(1L);
    }

}