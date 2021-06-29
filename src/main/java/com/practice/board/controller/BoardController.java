package com.practice.board.controller;

import com.practice.board.entity.Board;
import com.practice.board.repository.BoardRepository;
import com.practice.board.validator.BoardValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardValidator boardValidator;

    @GetMapping("/list")
    public String board(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }
    @GetMapping("/write")
    public String write(Model model, @RequestParam(required = false) Long id){
        if (id == null){
            model.addAttribute("board", new Board());
        }else{
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/write";
    }
    @PostMapping("/write")
    public String WriteProcess(@Valid Board board, BindingResult bindingResult){
        boardValidator.validate(board, bindingResult);
        if(bindingResult.hasErrors()){
            return "board/write";
        }
        board.setCreatedAt(LocalDateTime.now());
        boardRepository.save(board);
        return "redirect:/board/list";
    }
}
